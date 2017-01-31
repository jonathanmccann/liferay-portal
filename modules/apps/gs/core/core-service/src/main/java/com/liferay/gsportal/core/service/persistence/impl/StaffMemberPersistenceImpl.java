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

package com.liferay.gsportal.core.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.gsportal.core.exception.NoSuchStaffMemberException;
import com.liferay.gsportal.core.model.StaffMember;
import com.liferay.gsportal.core.model.impl.StaffMemberImpl;
import com.liferay.gsportal.core.model.impl.StaffMemberModelImpl;
import com.liferay.gsportal.core.service.persistence.StaffMemberPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the staff member service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see StaffMemberPersistence
 * @see com.liferay.gsportal.core.service.persistence.StaffMemberUtil
 * @generated
 */
@ProviderType
public class StaffMemberPersistenceImpl extends BasePersistenceImpl<StaffMember>
	implements StaffMemberPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link StaffMemberUtil} to access the staff member persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = StaffMemberImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(StaffMemberModelImpl.ENTITY_CACHE_ENABLED,
			StaffMemberModelImpl.FINDER_CACHE_ENABLED, StaffMemberImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(StaffMemberModelImpl.ENTITY_CACHE_ENABLED,
			StaffMemberModelImpl.FINDER_CACHE_ENABLED, StaffMemberImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(StaffMemberModelImpl.ENTITY_CACHE_ENABLED,
			StaffMemberModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public StaffMemberPersistenceImpl() {
		setModelClass(StaffMember.class);
	}

	/**
	 * Caches the staff member in the entity cache if it is enabled.
	 *
	 * @param staffMember the staff member
	 */
	@Override
	public void cacheResult(StaffMember staffMember) {
		entityCache.putResult(StaffMemberModelImpl.ENTITY_CACHE_ENABLED,
			StaffMemberImpl.class, staffMember.getPrimaryKey(), staffMember);

		staffMember.resetOriginalValues();
	}

	/**
	 * Caches the staff members in the entity cache if it is enabled.
	 *
	 * @param staffMembers the staff members
	 */
	@Override
	public void cacheResult(List<StaffMember> staffMembers) {
		for (StaffMember staffMember : staffMembers) {
			if (entityCache.getResult(
						StaffMemberModelImpl.ENTITY_CACHE_ENABLED,
						StaffMemberImpl.class, staffMember.getPrimaryKey()) == null) {
				cacheResult(staffMember);
			}
			else {
				staffMember.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all staff members.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(StaffMemberImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the staff member.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(StaffMember staffMember) {
		entityCache.removeResult(StaffMemberModelImpl.ENTITY_CACHE_ENABLED,
			StaffMemberImpl.class, staffMember.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<StaffMember> staffMembers) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (StaffMember staffMember : staffMembers) {
			entityCache.removeResult(StaffMemberModelImpl.ENTITY_CACHE_ENABLED,
				StaffMemberImpl.class, staffMember.getPrimaryKey());
		}
	}

	/**
	 * Creates a new staff member with the primary key. Does not add the staff member to the database.
	 *
	 * @param userId the primary key for the new staff member
	 * @return the new staff member
	 */
	@Override
	public StaffMember create(long userId) {
		StaffMember staffMember = new StaffMemberImpl();

		staffMember.setNew(true);
		staffMember.setPrimaryKey(userId);

		staffMember.setCompanyId(companyProvider.getCompanyId());

		return staffMember;
	}

	/**
	 * Removes the staff member with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userId the primary key of the staff member
	 * @return the staff member that was removed
	 * @throws NoSuchStaffMemberException if a staff member with the primary key could not be found
	 */
	@Override
	public StaffMember remove(long userId) throws NoSuchStaffMemberException {
		return remove((Serializable)userId);
	}

	/**
	 * Removes the staff member with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the staff member
	 * @return the staff member that was removed
	 * @throws NoSuchStaffMemberException if a staff member with the primary key could not be found
	 */
	@Override
	public StaffMember remove(Serializable primaryKey)
		throws NoSuchStaffMemberException {
		Session session = null;

		try {
			session = openSession();

			StaffMember staffMember = (StaffMember)session.get(StaffMemberImpl.class,
					primaryKey);

			if (staffMember == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchStaffMemberException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(staffMember);
		}
		catch (NoSuchStaffMemberException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected StaffMember removeImpl(StaffMember staffMember) {
		staffMember = toUnwrappedModel(staffMember);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(staffMember)) {
				staffMember = (StaffMember)session.get(StaffMemberImpl.class,
						staffMember.getPrimaryKeyObj());
			}

			if (staffMember != null) {
				session.delete(staffMember);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (staffMember != null) {
			clearCache(staffMember);
		}

		return staffMember;
	}

	@Override
	public StaffMember updateImpl(StaffMember staffMember) {
		staffMember = toUnwrappedModel(staffMember);

		boolean isNew = staffMember.isNew();

		Session session = null;

		try {
			session = openSession();

			if (staffMember.isNew()) {
				session.save(staffMember);

				staffMember.setNew(false);
			}
			else {
				staffMember = (StaffMember)session.merge(staffMember);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		entityCache.putResult(StaffMemberModelImpl.ENTITY_CACHE_ENABLED,
			StaffMemberImpl.class, staffMember.getPrimaryKey(), staffMember,
			false);

		staffMember.resetOriginalValues();

		return staffMember;
	}

	protected StaffMember toUnwrappedModel(StaffMember staffMember) {
		if (staffMember instanceof StaffMemberImpl) {
			return staffMember;
		}

		StaffMemberImpl staffMemberImpl = new StaffMemberImpl();

		staffMemberImpl.setNew(staffMember.isNew());
		staffMemberImpl.setPrimaryKey(staffMember.getPrimaryKey());

		staffMemberImpl.setUserId(staffMember.getUserId());
		staffMemberImpl.setCompanyId(staffMember.getCompanyId());
		staffMemberImpl.setEmployeeType(staffMember.getEmployeeType());
		staffMemberImpl.setEmployerName(staffMember.getEmployerName());

		return staffMemberImpl;
	}

	/**
	 * Returns the staff member with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the staff member
	 * @return the staff member
	 * @throws NoSuchStaffMemberException if a staff member with the primary key could not be found
	 */
	@Override
	public StaffMember findByPrimaryKey(Serializable primaryKey)
		throws NoSuchStaffMemberException {
		StaffMember staffMember = fetchByPrimaryKey(primaryKey);

		if (staffMember == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchStaffMemberException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return staffMember;
	}

	/**
	 * Returns the staff member with the primary key or throws a {@link NoSuchStaffMemberException} if it could not be found.
	 *
	 * @param userId the primary key of the staff member
	 * @return the staff member
	 * @throws NoSuchStaffMemberException if a staff member with the primary key could not be found
	 */
	@Override
	public StaffMember findByPrimaryKey(long userId)
		throws NoSuchStaffMemberException {
		return findByPrimaryKey((Serializable)userId);
	}

	/**
	 * Returns the staff member with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the staff member
	 * @return the staff member, or <code>null</code> if a staff member with the primary key could not be found
	 */
	@Override
	public StaffMember fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(StaffMemberModelImpl.ENTITY_CACHE_ENABLED,
				StaffMemberImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		StaffMember staffMember = (StaffMember)serializable;

		if (staffMember == null) {
			Session session = null;

			try {
				session = openSession();

				staffMember = (StaffMember)session.get(StaffMemberImpl.class,
						primaryKey);

				if (staffMember != null) {
					cacheResult(staffMember);
				}
				else {
					entityCache.putResult(StaffMemberModelImpl.ENTITY_CACHE_ENABLED,
						StaffMemberImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(StaffMemberModelImpl.ENTITY_CACHE_ENABLED,
					StaffMemberImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return staffMember;
	}

	/**
	 * Returns the staff member with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param userId the primary key of the staff member
	 * @return the staff member, or <code>null</code> if a staff member with the primary key could not be found
	 */
	@Override
	public StaffMember fetchByPrimaryKey(long userId) {
		return fetchByPrimaryKey((Serializable)userId);
	}

	@Override
	public Map<Serializable, StaffMember> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, StaffMember> map = new HashMap<Serializable, StaffMember>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			StaffMember staffMember = fetchByPrimaryKey(primaryKey);

			if (staffMember != null) {
				map.put(primaryKey, staffMember);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(StaffMemberModelImpl.ENTITY_CACHE_ENABLED,
					StaffMemberImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (StaffMember)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_STAFFMEMBER_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append(String.valueOf(primaryKey));

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (StaffMember staffMember : (List<StaffMember>)q.list()) {
				map.put(staffMember.getPrimaryKeyObj(), staffMember);

				cacheResult(staffMember);

				uncachedPrimaryKeys.remove(staffMember.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(StaffMemberModelImpl.ENTITY_CACHE_ENABLED,
					StaffMemberImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the staff members.
	 *
	 * @return the staff members
	 */
	@Override
	public List<StaffMember> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the staff members.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StaffMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of staff members
	 * @param end the upper bound of the range of staff members (not inclusive)
	 * @return the range of staff members
	 */
	@Override
	public List<StaffMember> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the staff members.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StaffMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of staff members
	 * @param end the upper bound of the range of staff members (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of staff members
	 */
	@Override
	public List<StaffMember> findAll(int start, int end,
		OrderByComparator<StaffMember> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the staff members.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StaffMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of staff members
	 * @param end the upper bound of the range of staff members (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of staff members
	 */
	@Override
	public List<StaffMember> findAll(int start, int end,
		OrderByComparator<StaffMember> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<StaffMember> list = null;

		if (retrieveFromCache) {
			list = (List<StaffMember>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_STAFFMEMBER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_STAFFMEMBER;

				if (pagination) {
					sql = sql.concat(StaffMemberModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<StaffMember>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<StaffMember>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the staff members from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (StaffMember staffMember : findAll()) {
			remove(staffMember);
		}
	}

	/**
	 * Returns the number of staff members.
	 *
	 * @return the number of staff members
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_STAFFMEMBER);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return StaffMemberModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the staff member persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(StaffMemberImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = CompanyProviderWrapper.class)
	protected CompanyProvider companyProvider;
	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_STAFFMEMBER = "SELECT staffMember FROM StaffMember staffMember";
	private static final String _SQL_SELECT_STAFFMEMBER_WHERE_PKS_IN = "SELECT staffMember FROM StaffMember staffMember WHERE userId IN (";
	private static final String _SQL_COUNT_STAFFMEMBER = "SELECT COUNT(staffMember) FROM StaffMember staffMember";
	private static final String _ORDER_BY_ENTITY_ALIAS = "staffMember.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No StaffMember exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(StaffMemberPersistenceImpl.class);
}