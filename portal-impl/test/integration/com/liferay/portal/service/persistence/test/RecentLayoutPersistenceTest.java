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

package com.liferay.portal.service.persistence.test;

import com.liferay.portal.NoSuchRecentLayoutException;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.TransactionalTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.model.RecentLayout;
import com.liferay.portal.service.RecentLayoutLocalServiceUtil;
import com.liferay.portal.service.persistence.RecentLayoutPersistence;
import com.liferay.portal.service.persistence.RecentLayoutUtil;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @generated
 */
public class RecentLayoutPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = RecentLayoutUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<RecentLayout> iterator = _recentLayouts.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		RecentLayout recentLayout = _persistence.create(pk);

		Assert.assertNotNull(recentLayout);

		Assert.assertEquals(recentLayout.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		RecentLayout newRecentLayout = addRecentLayout();

		_persistence.remove(newRecentLayout);

		RecentLayout existingRecentLayout = _persistence.fetchByPrimaryKey(newRecentLayout.getPrimaryKey());

		Assert.assertNull(existingRecentLayout);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addRecentLayout();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		RecentLayout newRecentLayout = _persistence.create(pk);

		newRecentLayout.setMvccVersion(RandomTestUtil.nextLong());

		newRecentLayout.setGroupId(RandomTestUtil.nextLong());

		newRecentLayout.setCompanyId(RandomTestUtil.nextLong());

		newRecentLayout.setUserId(RandomTestUtil.nextLong());

		newRecentLayout.setLayoutSetBranchId(RandomTestUtil.nextLong());

		newRecentLayout.setPlid(RandomTestUtil.nextLong());

		newRecentLayout.setLayoutBranchId(RandomTestUtil.nextLong());

		newRecentLayout.setLayoutRevisionId(RandomTestUtil.nextLong());

		_recentLayouts.add(_persistence.update(newRecentLayout));

		RecentLayout existingRecentLayout = _persistence.findByPrimaryKey(newRecentLayout.getPrimaryKey());

		Assert.assertEquals(existingRecentLayout.getMvccVersion(),
			newRecentLayout.getMvccVersion());
		Assert.assertEquals(existingRecentLayout.getRecentLayoutId(),
			newRecentLayout.getRecentLayoutId());
		Assert.assertEquals(existingRecentLayout.getGroupId(),
			newRecentLayout.getGroupId());
		Assert.assertEquals(existingRecentLayout.getCompanyId(),
			newRecentLayout.getCompanyId());
		Assert.assertEquals(existingRecentLayout.getUserId(),
			newRecentLayout.getUserId());
		Assert.assertEquals(existingRecentLayout.getLayoutSetBranchId(),
			newRecentLayout.getLayoutSetBranchId());
		Assert.assertEquals(existingRecentLayout.getPlid(),
			newRecentLayout.getPlid());
		Assert.assertEquals(existingRecentLayout.getLayoutBranchId(),
			newRecentLayout.getLayoutBranchId());
		Assert.assertEquals(existingRecentLayout.getLayoutRevisionId(),
			newRecentLayout.getLayoutRevisionId());
	}

	@Test
	public void testCountByGroupId() throws Exception {
		_persistence.countByGroupId(RandomTestUtil.nextLong());

		_persistence.countByGroupId(0L);
	}

	@Test
	public void testCountByUserId() throws Exception {
		_persistence.countByUserId(RandomTestUtil.nextLong());

		_persistence.countByUserId(0L);
	}

	@Test
	public void testCountByLayoutBranchId() throws Exception {
		_persistence.countByLayoutBranchId(RandomTestUtil.nextLong());

		_persistence.countByLayoutBranchId(0L);
	}

	@Test
	public void testCountByU_L_P() throws Exception {
		_persistence.countByU_L_P(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong());

		_persistence.countByU_L_P(0L, 0L, 0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		RecentLayout newRecentLayout = addRecentLayout();

		RecentLayout existingRecentLayout = _persistence.findByPrimaryKey(newRecentLayout.getPrimaryKey());

		Assert.assertEquals(existingRecentLayout, newRecentLayout);
	}

	@Test(expected = NoSuchRecentLayoutException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<RecentLayout> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("RecentLayout",
			"mvccVersion", true, "recentLayoutId", true, "groupId", true,
			"companyId", true, "userId", true, "layoutSetBranchId", true,
			"plid", true, "layoutBranchId", true, "layoutRevisionId", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		RecentLayout newRecentLayout = addRecentLayout();

		RecentLayout existingRecentLayout = _persistence.fetchByPrimaryKey(newRecentLayout.getPrimaryKey());

		Assert.assertEquals(existingRecentLayout, newRecentLayout);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		RecentLayout missingRecentLayout = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingRecentLayout);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		RecentLayout newRecentLayout1 = addRecentLayout();
		RecentLayout newRecentLayout2 = addRecentLayout();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRecentLayout1.getPrimaryKey());
		primaryKeys.add(newRecentLayout2.getPrimaryKey());

		Map<Serializable, RecentLayout> recentLayouts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, recentLayouts.size());
		Assert.assertEquals(newRecentLayout1,
			recentLayouts.get(newRecentLayout1.getPrimaryKey()));
		Assert.assertEquals(newRecentLayout2,
			recentLayouts.get(newRecentLayout2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, RecentLayout> recentLayouts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(recentLayouts.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		RecentLayout newRecentLayout = addRecentLayout();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRecentLayout.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, RecentLayout> recentLayouts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, recentLayouts.size());
		Assert.assertEquals(newRecentLayout,
			recentLayouts.get(newRecentLayout.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, RecentLayout> recentLayouts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(recentLayouts.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		RecentLayout newRecentLayout = addRecentLayout();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRecentLayout.getPrimaryKey());

		Map<Serializable, RecentLayout> recentLayouts = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, recentLayouts.size());
		Assert.assertEquals(newRecentLayout,
			recentLayouts.get(newRecentLayout.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = RecentLayoutLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<RecentLayout>() {
				@Override
				public void performAction(RecentLayout recentLayout) {
					Assert.assertNotNull(recentLayout);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		RecentLayout newRecentLayout = addRecentLayout();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RecentLayout.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("recentLayoutId",
				newRecentLayout.getRecentLayoutId()));

		List<RecentLayout> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		RecentLayout existingRecentLayout = result.get(0);

		Assert.assertEquals(existingRecentLayout, newRecentLayout);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RecentLayout.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("recentLayoutId",
				RandomTestUtil.nextLong()));

		List<RecentLayout> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		RecentLayout newRecentLayout = addRecentLayout();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RecentLayout.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"recentLayoutId"));

		Object newRecentLayoutId = newRecentLayout.getRecentLayoutId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("recentLayoutId",
				new Object[] { newRecentLayoutId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingRecentLayoutId = result.get(0);

		Assert.assertEquals(existingRecentLayoutId, newRecentLayoutId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RecentLayout.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"recentLayoutId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("recentLayoutId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		RecentLayout newRecentLayout = addRecentLayout();

		_persistence.clearCache();

		RecentLayout existingRecentLayout = _persistence.findByPrimaryKey(newRecentLayout.getPrimaryKey());

		Assert.assertEquals(Long.valueOf(existingRecentLayout.getUserId()),
			ReflectionTestUtil.<Long>invoke(existingRecentLayout,
				"getOriginalUserId", new Class<?>[0]));
		Assert.assertEquals(Long.valueOf(
				existingRecentLayout.getLayoutSetBranchId()),
			ReflectionTestUtil.<Long>invoke(existingRecentLayout,
				"getOriginalLayoutSetBranchId", new Class<?>[0]));
		Assert.assertEquals(Long.valueOf(existingRecentLayout.getPlid()),
			ReflectionTestUtil.<Long>invoke(existingRecentLayout,
				"getOriginalPlid", new Class<?>[0]));
	}

	protected RecentLayout addRecentLayout() throws Exception {
		long pk = RandomTestUtil.nextLong();

		RecentLayout recentLayout = _persistence.create(pk);

		recentLayout.setMvccVersion(RandomTestUtil.nextLong());

		recentLayout.setGroupId(RandomTestUtil.nextLong());

		recentLayout.setCompanyId(RandomTestUtil.nextLong());

		recentLayout.setUserId(RandomTestUtil.nextLong());

		recentLayout.setLayoutSetBranchId(RandomTestUtil.nextLong());

		recentLayout.setPlid(RandomTestUtil.nextLong());

		recentLayout.setLayoutBranchId(RandomTestUtil.nextLong());

		recentLayout.setLayoutRevisionId(RandomTestUtil.nextLong());

		_recentLayouts.add(_persistence.update(recentLayout));

		return recentLayout;
	}

	private List<RecentLayout> _recentLayouts = new ArrayList<RecentLayout>();
	private RecentLayoutPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}