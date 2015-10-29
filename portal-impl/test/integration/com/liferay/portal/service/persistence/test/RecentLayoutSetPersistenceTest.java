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

import com.liferay.portal.NoSuchRecentLayoutSetException;
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
import com.liferay.portal.model.RecentLayoutSet;
import com.liferay.portal.service.RecentLayoutSetLocalServiceUtil;
import com.liferay.portal.service.persistence.RecentLayoutSetPersistence;
import com.liferay.portal.service.persistence.RecentLayoutSetUtil;
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
public class RecentLayoutSetPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED));

	@Before
	public void setUp() {
		_persistence = RecentLayoutSetUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<RecentLayoutSet> iterator = _recentLayoutSets.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		RecentLayoutSet recentLayoutSet = _persistence.create(pk);

		Assert.assertNotNull(recentLayoutSet);

		Assert.assertEquals(recentLayoutSet.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		RecentLayoutSet newRecentLayoutSet = addRecentLayoutSet();

		_persistence.remove(newRecentLayoutSet);

		RecentLayoutSet existingRecentLayoutSet = _persistence.fetchByPrimaryKey(newRecentLayoutSet.getPrimaryKey());

		Assert.assertNull(existingRecentLayoutSet);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addRecentLayoutSet();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		RecentLayoutSet newRecentLayoutSet = _persistence.create(pk);

		newRecentLayoutSet.setMvccVersion(RandomTestUtil.nextLong());

		newRecentLayoutSet.setGroupId(RandomTestUtil.nextLong());

		newRecentLayoutSet.setCompanyId(RandomTestUtil.nextLong());

		newRecentLayoutSet.setUserId(RandomTestUtil.nextLong());

		newRecentLayoutSet.setLayoutSetId(RandomTestUtil.nextLong());

		newRecentLayoutSet.setLayoutSetBranchId(RandomTestUtil.nextLong());

		_recentLayoutSets.add(_persistence.update(newRecentLayoutSet));

		RecentLayoutSet existingRecentLayoutSet = _persistence.findByPrimaryKey(newRecentLayoutSet.getPrimaryKey());

		Assert.assertEquals(existingRecentLayoutSet.getMvccVersion(),
			newRecentLayoutSet.getMvccVersion());
		Assert.assertEquals(existingRecentLayoutSet.getRecentLayoutSetId(),
			newRecentLayoutSet.getRecentLayoutSetId());
		Assert.assertEquals(existingRecentLayoutSet.getGroupId(),
			newRecentLayoutSet.getGroupId());
		Assert.assertEquals(existingRecentLayoutSet.getCompanyId(),
			newRecentLayoutSet.getCompanyId());
		Assert.assertEquals(existingRecentLayoutSet.getUserId(),
			newRecentLayoutSet.getUserId());
		Assert.assertEquals(existingRecentLayoutSet.getLayoutSetId(),
			newRecentLayoutSet.getLayoutSetId());
		Assert.assertEquals(existingRecentLayoutSet.getLayoutSetBranchId(),
			newRecentLayoutSet.getLayoutSetBranchId());
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
	public void testCountByLayoutSetBranchId() throws Exception {
		_persistence.countByLayoutSetBranchId(RandomTestUtil.nextLong());

		_persistence.countByLayoutSetBranchId(0L);
	}

	@Test
	public void testCountByU_L() throws Exception {
		_persistence.countByU_L(RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong());

		_persistence.countByU_L(0L, 0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		RecentLayoutSet newRecentLayoutSet = addRecentLayoutSet();

		RecentLayoutSet existingRecentLayoutSet = _persistence.findByPrimaryKey(newRecentLayoutSet.getPrimaryKey());

		Assert.assertEquals(existingRecentLayoutSet, newRecentLayoutSet);
	}

	@Test(expected = NoSuchRecentLayoutSetException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<RecentLayoutSet> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("RecentLayoutSet",
			"mvccVersion", true, "recentLayoutSetId", true, "groupId", true,
			"companyId", true, "userId", true, "layoutSetId", true,
			"layoutSetBranchId", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		RecentLayoutSet newRecentLayoutSet = addRecentLayoutSet();

		RecentLayoutSet existingRecentLayoutSet = _persistence.fetchByPrimaryKey(newRecentLayoutSet.getPrimaryKey());

		Assert.assertEquals(existingRecentLayoutSet, newRecentLayoutSet);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		RecentLayoutSet missingRecentLayoutSet = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingRecentLayoutSet);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		RecentLayoutSet newRecentLayoutSet1 = addRecentLayoutSet();
		RecentLayoutSet newRecentLayoutSet2 = addRecentLayoutSet();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRecentLayoutSet1.getPrimaryKey());
		primaryKeys.add(newRecentLayoutSet2.getPrimaryKey());

		Map<Serializable, RecentLayoutSet> recentLayoutSets = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, recentLayoutSets.size());
		Assert.assertEquals(newRecentLayoutSet1,
			recentLayoutSets.get(newRecentLayoutSet1.getPrimaryKey()));
		Assert.assertEquals(newRecentLayoutSet2,
			recentLayoutSets.get(newRecentLayoutSet2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, RecentLayoutSet> recentLayoutSets = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(recentLayoutSets.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		RecentLayoutSet newRecentLayoutSet = addRecentLayoutSet();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRecentLayoutSet.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, RecentLayoutSet> recentLayoutSets = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, recentLayoutSets.size());
		Assert.assertEquals(newRecentLayoutSet,
			recentLayoutSets.get(newRecentLayoutSet.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, RecentLayoutSet> recentLayoutSets = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(recentLayoutSets.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		RecentLayoutSet newRecentLayoutSet = addRecentLayoutSet();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newRecentLayoutSet.getPrimaryKey());

		Map<Serializable, RecentLayoutSet> recentLayoutSets = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, recentLayoutSets.size());
		Assert.assertEquals(newRecentLayoutSet,
			recentLayoutSets.get(newRecentLayoutSet.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = RecentLayoutSetLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<RecentLayoutSet>() {
				@Override
				public void performAction(RecentLayoutSet recentLayoutSet) {
					Assert.assertNotNull(recentLayoutSet);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		RecentLayoutSet newRecentLayoutSet = addRecentLayoutSet();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RecentLayoutSet.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("recentLayoutSetId",
				newRecentLayoutSet.getRecentLayoutSetId()));

		List<RecentLayoutSet> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		RecentLayoutSet existingRecentLayoutSet = result.get(0);

		Assert.assertEquals(existingRecentLayoutSet, newRecentLayoutSet);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RecentLayoutSet.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("recentLayoutSetId",
				RandomTestUtil.nextLong()));

		List<RecentLayoutSet> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		RecentLayoutSet newRecentLayoutSet = addRecentLayoutSet();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RecentLayoutSet.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"recentLayoutSetId"));

		Object newRecentLayoutSetId = newRecentLayoutSet.getRecentLayoutSetId();

		dynamicQuery.add(RestrictionsFactoryUtil.in("recentLayoutSetId",
				new Object[] { newRecentLayoutSetId }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingRecentLayoutSetId = result.get(0);

		Assert.assertEquals(existingRecentLayoutSetId, newRecentLayoutSetId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(RecentLayoutSet.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"recentLayoutSetId"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("recentLayoutSetId",
				new Object[] { RandomTestUtil.nextLong() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		RecentLayoutSet newRecentLayoutSet = addRecentLayoutSet();

		_persistence.clearCache();

		RecentLayoutSet existingRecentLayoutSet = _persistence.findByPrimaryKey(newRecentLayoutSet.getPrimaryKey());

		Assert.assertEquals(Long.valueOf(existingRecentLayoutSet.getUserId()),
			ReflectionTestUtil.<Long>invoke(existingRecentLayoutSet,
				"getOriginalUserId", new Class<?>[0]));
		Assert.assertEquals(Long.valueOf(
				existingRecentLayoutSet.getLayoutSetId()),
			ReflectionTestUtil.<Long>invoke(existingRecentLayoutSet,
				"getOriginalLayoutSetId", new Class<?>[0]));
	}

	protected RecentLayoutSet addRecentLayoutSet() throws Exception {
		long pk = RandomTestUtil.nextLong();

		RecentLayoutSet recentLayoutSet = _persistence.create(pk);

		recentLayoutSet.setMvccVersion(RandomTestUtil.nextLong());

		recentLayoutSet.setGroupId(RandomTestUtil.nextLong());

		recentLayoutSet.setCompanyId(RandomTestUtil.nextLong());

		recentLayoutSet.setUserId(RandomTestUtil.nextLong());

		recentLayoutSet.setLayoutSetId(RandomTestUtil.nextLong());

		recentLayoutSet.setLayoutSetBranchId(RandomTestUtil.nextLong());

		_recentLayoutSets.add(_persistence.update(recentLayoutSet));

		return recentLayoutSet;
	}

	private List<RecentLayoutSet> _recentLayoutSets = new ArrayList<RecentLayoutSet>();
	private RecentLayoutSetPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}