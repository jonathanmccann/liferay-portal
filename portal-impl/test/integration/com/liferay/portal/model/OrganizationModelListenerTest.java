/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.model;

import com.liferay.petra.concurrent.NoticeableExecutorService;
import com.liferay.petra.executor.PortalExecutorManager;
import com.liferay.portal.kernel.concurrent.test.TestUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.OrganizationConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.OrganizationTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.ServiceProxyFactory;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.ArrayUtils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Lianne Louie
 */
public class OrganizationModelListenerTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@BeforeClass
	public static void setUpClass() throws Exception {
		_indexer = IndexerRegistryUtil.nullSafeGetIndexer(User.class);

		_portalExecutorManager = ServiceProxyFactory.newServiceTrackedInstance(
			PortalExecutorManager.class, OrganizationModelListener.class,
			"_portalExecutorManager", true);
	}

	@Before
	public void setUp() throws Exception {
		_noticeableExecutorService = _portalExecutorManager.getPortalExecutor(
			OrganizationModelListener.class.getName());

		_childOrganization = OrganizationTestUtil.addOrganization();
		_grandparentOrganization = OrganizationTestUtil.addOrganization();
		_parentOrganization = OrganizationTestUtil.addOrganization();

		_user = UserTestUtil.addUser();

		_indexer.reindex(_user);
	}

	@After
	public void tearDown() throws Exception {
		UserLocalServiceUtil.deleteUser(_user);

		OrganizationLocalServiceUtil.deleteOrganization(_childOrganization);
		OrganizationLocalServiceUtil.deleteOrganization(_parentOrganization);
		OrganizationLocalServiceUtil.deleteOrganization(
			_grandparentOrganization);
	}

	@Test
	public void testAddMultipleParentOrganizations() throws Exception {
		_parentOrganization.setParentOrganizationId(
			_grandparentOrganization.getOrganizationId());

		_parentOrganization = _updateOrganization(_parentOrganization);

		Assert.assertEquals(
			_grandparentOrganization.getOrganizationId(),
			_parentOrganization.getParentOrganizationId());

		UserLocalServiceUtil.addOrganizationUser(
			_childOrganization.getOrganizationId(), _user);

		_childOrganization.setParentOrganizationId(
			_parentOrganization.getOrganizationId());

		_childOrganization = _updateOrganization(_childOrganization);

		Assert.assertEquals(
			_parentOrganization.getOrganizationId(),
			_childOrganization.getParentOrganizationId());

		long parentOrganizationParentId =
			_parentOrganization.getParentOrganizationId();
		long childOrganizationParentId =
			_childOrganization.getParentOrganizationId();

		Document document = _indexer.getDocument(_user);

		String[] indexedParentOrganizationIds = document.getValues(
			"ancestorOrganizationIds");

		Assert.assertTrue(
			ArrayUtils.contains(
				indexedParentOrganizationIds,
				String.valueOf(parentOrganizationParentId)));

		Assert.assertTrue(
			ArrayUtils.contains(
				indexedParentOrganizationIds,
				String.valueOf(childOrganizationParentId)));
	}

	@Test
	public void testAddParentOrganization() throws Exception {
		UserLocalServiceUtil.addOrganizationUser(
			_childOrganization.getOrganizationId(), _user);

		_childOrganization.setParentOrganizationId(
			_parentOrganization.getOrganizationId());

		_childOrganization = _updateOrganization(_childOrganization);

		Assert.assertEquals(
			_parentOrganization.getOrganizationId(),
			_childOrganization.getParentOrganizationId());

		String[] expectedParentOrganizationIds = new String[1];

		expectedParentOrganizationIds[0] = String.valueOf(
			_childOrganization.getParentOrganizationId());

		Document document = _indexer.getDocument(_user);

		String[] indexedParentOrganizationIds = document.getValues(
			"ancestorOrganizationIds");

		Assert.assertArrayEquals(
			expectedParentOrganizationIds, indexedParentOrganizationIds);
	}

	@Test
	public void testRemoveParentOrganization() throws Exception {
		UserLocalServiceUtil.addOrganizationUser(
			_childOrganization.getOrganizationId(), _user);

		_childOrganization.setParentOrganizationId(
			_parentOrganization.getOrganizationId());

		_childOrganization = _updateOrganization(_childOrganization);

		Assert.assertEquals(
			_parentOrganization.getOrganizationId(),
			_childOrganization.getParentOrganizationId());

		_childOrganization.setParentOrganizationId(
			OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID);

		_childOrganization = _updateOrganization(_childOrganization);

		Assert.assertEquals(
			_childOrganization.getParentOrganizationId(),
			OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID);

		Document document = _indexer.getDocument(_user);

		String[] indexedParentOrganizationIds = document.getValues(
			"ancestorOrganizationIds");

		String[] expectedParentOrganizationIds = new String[0];

		Assert.assertArrayEquals(
			expectedParentOrganizationIds, indexedParentOrganizationIds);
	}

	private Organization _updateOrganization(Organization organization)
		throws Exception {

		try {
			Group organizationGroup = organization.getGroup();

			return OrganizationLocalServiceUtil.updateOrganization(
				organization.getCompanyId(), organization.getOrganizationId(),
				organization.getParentOrganizationId(), organization.getName(),
				organization.getType(), organization.getRegionId(),
				organization.getCountryId(), organization.getStatusId(),
				organization.getComments(), false, null,
				organizationGroup.isSite(), null);
		}
		finally {
			Thread.sleep(TestUtil.LONG_WAIT);

			_noticeableExecutorService.shutdown();

			Assert.assertTrue(
				_noticeableExecutorService.awaitTermination(
					10, TimeUnit.MINUTES));
		}
	}

	private static Organization _childOrganization;
	private static Organization _grandparentOrganization;
	private static Indexer<User> _indexer;
	private static NoticeableExecutorService _noticeableExecutorService;
	private static Organization _parentOrganization;
	private static PortalExecutorManager _portalExecutorManager;
	private static User _user;

}