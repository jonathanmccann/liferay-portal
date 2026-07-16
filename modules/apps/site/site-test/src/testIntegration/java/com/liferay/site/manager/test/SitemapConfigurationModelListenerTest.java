/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.manager.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;
import com.liferay.site.configuration.manager.SitemapConfigurationManager;
import com.liferay.site.constants.SitemapConstants;
import com.liferay.site.storage.helper.SitemapStorageHelper;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Cheryl Tang
 */
@RunWith(Arquillian.class)
public class SitemapConfigurationModelListenerTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE);

	@BeforeClass
	public static void setUpClass() throws Exception {
		long companyId = TestPropsValues.getCompanyId();

		_originalCachedGenerationEnabled =
			_sitemapConfigurationManager.cachedGenerationCompanyEnabled(
				companyId);
		_originalCompanySitemapGroupIds =
			_sitemapConfigurationManager.getCompanySitemapGroupIds(companyId);
		_originalCompanySitemapObjectDefinitionIds =
			_sitemapConfigurationManager.getCompanySitemapObjectDefinitionIds(
				companyId);
		_originalIncludeCategories =
			_sitemapConfigurationManager.includeCategoriesCompanyEnabled(
				companyId);
		_originalIncludePages =
			_sitemapConfigurationManager.includePagesCompanyEnabled(companyId);
		_originalIncludeWebContent =
			_sitemapConfigurationManager.includeWebContentCompanyEnabled(
				companyId);
		_originalXMLSitemapIndexEnabled =
			_sitemapConfigurationManager.xmlSitemapIndexCompanyEnabled(
				companyId);
		_originalXMLSitemapIndexMode =
			_sitemapConfigurationManager.xmlSitemapIndexMode(companyId);
		_originalXMLSitemapRegenerationDay =
			_sitemapConfigurationManager.xmlSitemapRegenerationDay(companyId);
		_originalXMLSitemapRegenerationFrequency =
			_sitemapConfigurationManager.xmlSitemapRegenerationFrequency(
				companyId);
		_originalXMLSitemapRegenerationTime =
			_sitemapConfigurationManager.xmlSitemapRegenerationTime(companyId);
		_originalXMLSitemapRegenerationTimeZoneId =
			_sitemapConfigurationManager.xmlSitemapRegenerationTimeZoneId(
				companyId);

		_originalName = PrincipalThreadLocal.getName();

		PrincipalThreadLocal.setName(TestPropsValues.getUserId());
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		_sitemapConfigurationManager.saveSitemapCompanyConfiguration(
			_originalCachedGenerationEnabled, TestPropsValues.getCompanyId(),
			ArrayUtil.toArray(_originalCompanySitemapGroupIds),
			ArrayUtil.toArray(_originalCompanySitemapObjectDefinitionIds),
			_originalIncludeCategories, _originalIncludePages,
			_originalIncludeWebContent, _originalXMLSitemapIndexEnabled,
			_originalXMLSitemapIndexMode, _originalXMLSitemapRegenerationDay,
			_originalXMLSitemapRegenerationFrequency,
			_originalXMLSitemapRegenerationTime,
			_originalXMLSitemapRegenerationTimeZoneId);

		PrincipalThreadLocal.setName(_originalName);
	}

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();
	}

	@After
	public void tearDown() throws Exception {
		_sitemapStorageHelper.deleteSitemaps(TestPropsValues.getCompanyId());
	}

	@Test
	public void testCachedGenerationModeKeepsExistingFilesOnConfigSave()
		throws Exception {

		long companyId = TestPropsValues.getCompanyId();

		_saveSitemapCompanyConfiguration(false);

		_sitemapStorageHelper.storeSitemapFile(
			companyId, _group.getGroupId(), "<urlset/>");

		Assert.assertTrue(_sitemapStorageHelper.hasSitemapFiles(companyId));

		_saveSitemapCompanyConfiguration(true);

		Assert.assertTrue(_sitemapStorageHelper.hasSitemapFiles(companyId));
	}

	@Test
	public void testOnDemandGenerationModeClearsFilesOnConfigSave()
		throws Exception {

		long companyId = TestPropsValues.getCompanyId();

		_sitemapStorageHelper.storeSitemapFile(
			companyId, _group.getGroupId(), "<urlset/>");

		_saveSitemapCompanyConfiguration(true);

		Assert.assertTrue(_sitemapStorageHelper.hasSitemapFiles(companyId));

		_saveSitemapCompanyConfiguration(false);

		Assert.assertFalse(_sitemapStorageHelper.hasSitemapFiles(companyId));
	}

	private void _saveSitemapCompanyConfiguration(
			boolean cachedGenerationEnabled)
		throws Exception {

		_sitemapConfigurationManager.saveSitemapCompanyConfiguration(
			cachedGenerationEnabled, TestPropsValues.getCompanyId(),
			new long[0], new long[0], true, true, true, true,
			SitemapConstants.INDEX_MODE_ASSET_TYPE, StringPool.BLANK,
			SitemapConstants.REGENERATION_FREQUENCY_DAILY, "03:30", "UTC");
	}

	private static boolean _originalCachedGenerationEnabled;
	private static Long[] _originalCompanySitemapGroupIds;
	private static Long[] _originalCompanySitemapObjectDefinitionIds;
	private static boolean _originalIncludeCategories;
	private static boolean _originalIncludePages;
	private static boolean _originalIncludeWebContent;
	private static String _originalName;
	private static boolean _originalXMLSitemapIndexEnabled;
	private static String _originalXMLSitemapIndexMode;
	private static String _originalXMLSitemapRegenerationDay;
	private static String _originalXMLSitemapRegenerationFrequency;
	private static String _originalXMLSitemapRegenerationTime;
	private static String _originalXMLSitemapRegenerationTimeZoneId;

	@Inject
	private static SitemapConfigurationManager _sitemapConfigurationManager;

	@DeleteAfterTestRun
	private Group _group;

	@Inject
	private SitemapStorageHelper _sitemapStorageHelper;

}