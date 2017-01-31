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

import com.liferay.gsportal.core.exception.NoSuchEngagementException;
import com.liferay.gsportal.core.model.Engagement;
import com.liferay.gsportal.core.model.impl.EngagementImpl;
import com.liferay.gsportal.core.model.impl.EngagementModelImpl;
import com.liferay.gsportal.core.service.persistence.EngagementPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.CompanyProvider;
import com.liferay.portal.kernel.service.persistence.CompanyProviderWrapper;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the engagement service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EngagementPersistence
 * @see com.liferay.gsportal.core.service.persistence.EngagementUtil
 * @generated
 */
@ProviderType
public class EngagementPersistenceImpl extends BasePersistenceImpl<Engagement>
	implements EngagementPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link EngagementUtil} to access the engagement persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = EngagementImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(EngagementModelImpl.ENTITY_CACHE_ENABLED,
			EngagementModelImpl.FINDER_CACHE_ENABLED, EngagementImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(EngagementModelImpl.ENTITY_CACHE_ENABLED,
			EngagementModelImpl.FINDER_CACHE_ENABLED, EngagementImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(EngagementModelImpl.ENTITY_CACHE_ENABLED,
			EngagementModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PROJECTID =
		new FinderPath(EngagementModelImpl.ENTITY_CACHE_ENABLED,
			EngagementModelImpl.FINDER_CACHE_ENABLED, EngagementImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByProjectId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROJECTID =
		new FinderPath(EngagementModelImpl.ENTITY_CACHE_ENABLED,
			EngagementModelImpl.FINDER_CACHE_ENABLED, EngagementImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByProjectId",
			new String[] { Long.class.getName() },
			EngagementModelImpl.PROJECTID_COLUMN_BITMASK |
			EngagementModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PROJECTID = new FinderPath(EngagementModelImpl.ENTITY_CACHE_ENABLED,
			EngagementModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByProjectId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the engagements where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @return the matching engagements
	 */
	@Override
	public List<Engagement> findByProjectId(long projectId) {
		return findByProjectId(projectId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the engagements where projectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EngagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param start the lower bound of the range of engagements
	 * @param end the upper bound of the range of engagements (not inclusive)
	 * @return the range of matching engagements
	 */
	@Override
	public List<Engagement> findByProjectId(long projectId, int start, int end) {
		return findByProjectId(projectId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the engagements where projectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EngagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param start the lower bound of the range of engagements
	 * @param end the upper bound of the range of engagements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching engagements
	 */
	@Override
	public List<Engagement> findByProjectId(long projectId, int start, int end,
		OrderByComparator<Engagement> orderByComparator) {
		return findByProjectId(projectId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the engagements where projectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EngagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param projectId the project ID
	 * @param start the lower bound of the range of engagements
	 * @param end the upper bound of the range of engagements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching engagements
	 */
	@Override
	public List<Engagement> findByProjectId(long projectId, int start, int end,
		OrderByComparator<Engagement> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROJECTID;
			finderArgs = new Object[] { projectId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PROJECTID;
			finderArgs = new Object[] { projectId, start, end, orderByComparator };
		}

		List<Engagement> list = null;

		if (retrieveFromCache) {
			list = (List<Engagement>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (Engagement engagement : list) {
					if ((projectId != engagement.getProjectId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_ENGAGEMENT_WHERE);

			query.append(_FINDER_COLUMN_PROJECTID_PROJECTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(EngagementModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(projectId);

				if (!pagination) {
					list = (List<Engagement>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Engagement>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first engagement in the ordered set where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching engagement
	 * @throws NoSuchEngagementException if a matching engagement could not be found
	 */
	@Override
	public Engagement findByProjectId_First(long projectId,
		OrderByComparator<Engagement> orderByComparator)
		throws NoSuchEngagementException {
		Engagement engagement = fetchByProjectId_First(projectId,
				orderByComparator);

		if (engagement != null) {
			return engagement;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("projectId=");
		msg.append(projectId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEngagementException(msg.toString());
	}

	/**
	 * Returns the first engagement in the ordered set where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching engagement, or <code>null</code> if a matching engagement could not be found
	 */
	@Override
	public Engagement fetchByProjectId_First(long projectId,
		OrderByComparator<Engagement> orderByComparator) {
		List<Engagement> list = findByProjectId(projectId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last engagement in the ordered set where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching engagement
	 * @throws NoSuchEngagementException if a matching engagement could not be found
	 */
	@Override
	public Engagement findByProjectId_Last(long projectId,
		OrderByComparator<Engagement> orderByComparator)
		throws NoSuchEngagementException {
		Engagement engagement = fetchByProjectId_Last(projectId,
				orderByComparator);

		if (engagement != null) {
			return engagement;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("projectId=");
		msg.append(projectId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchEngagementException(msg.toString());
	}

	/**
	 * Returns the last engagement in the ordered set where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching engagement, or <code>null</code> if a matching engagement could not be found
	 */
	@Override
	public Engagement fetchByProjectId_Last(long projectId,
		OrderByComparator<Engagement> orderByComparator) {
		int count = countByProjectId(projectId);

		if (count == 0) {
			return null;
		}

		List<Engagement> list = findByProjectId(projectId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the engagements before and after the current engagement in the ordered set where projectId = &#63;.
	 *
	 * @param engagementId the primary key of the current engagement
	 * @param projectId the project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next engagement
	 * @throws NoSuchEngagementException if a engagement with the primary key could not be found
	 */
	@Override
	public Engagement[] findByProjectId_PrevAndNext(long engagementId,
		long projectId, OrderByComparator<Engagement> orderByComparator)
		throws NoSuchEngagementException {
		Engagement engagement = findByPrimaryKey(engagementId);

		Session session = null;

		try {
			session = openSession();

			Engagement[] array = new EngagementImpl[3];

			array[0] = getByProjectId_PrevAndNext(session, engagement,
					projectId, orderByComparator, true);

			array[1] = engagement;

			array[2] = getByProjectId_PrevAndNext(session, engagement,
					projectId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Engagement getByProjectId_PrevAndNext(Session session,
		Engagement engagement, long projectId,
		OrderByComparator<Engagement> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ENGAGEMENT_WHERE);

		query.append(_FINDER_COLUMN_PROJECTID_PROJECTID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(EngagementModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(projectId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(engagement);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Engagement> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the engagements where projectId = &#63; from the database.
	 *
	 * @param projectId the project ID
	 */
	@Override
	public void removeByProjectId(long projectId) {
		for (Engagement engagement : findByProjectId(projectId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(engagement);
		}
	}

	/**
	 * Returns the number of engagements where projectId = &#63;.
	 *
	 * @param projectId the project ID
	 * @return the number of matching engagements
	 */
	@Override
	public int countByProjectId(long projectId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PROJECTID;

		Object[] finderArgs = new Object[] { projectId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ENGAGEMENT_WHERE);

			query.append(_FINDER_COLUMN_PROJECTID_PROJECTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(projectId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_PROJECTID_PROJECTID_2 = "engagement.projectId = ?";

	public EngagementPersistenceImpl() {
		setModelClass(Engagement.class);
	}

	/**
	 * Caches the engagement in the entity cache if it is enabled.
	 *
	 * @param engagement the engagement
	 */
	@Override
	public void cacheResult(Engagement engagement) {
		entityCache.putResult(EngagementModelImpl.ENTITY_CACHE_ENABLED,
			EngagementImpl.class, engagement.getPrimaryKey(), engagement);

		engagement.resetOriginalValues();
	}

	/**
	 * Caches the engagements in the entity cache if it is enabled.
	 *
	 * @param engagements the engagements
	 */
	@Override
	public void cacheResult(List<Engagement> engagements) {
		for (Engagement engagement : engagements) {
			if (entityCache.getResult(
						EngagementModelImpl.ENTITY_CACHE_ENABLED,
						EngagementImpl.class, engagement.getPrimaryKey()) == null) {
				cacheResult(engagement);
			}
			else {
				engagement.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all engagements.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(EngagementImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the engagement.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Engagement engagement) {
		entityCache.removeResult(EngagementModelImpl.ENTITY_CACHE_ENABLED,
			EngagementImpl.class, engagement.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Engagement> engagements) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Engagement engagement : engagements) {
			entityCache.removeResult(EngagementModelImpl.ENTITY_CACHE_ENABLED,
				EngagementImpl.class, engagement.getPrimaryKey());
		}
	}

	/**
	 * Creates a new engagement with the primary key. Does not add the engagement to the database.
	 *
	 * @param engagementId the primary key for the new engagement
	 * @return the new engagement
	 */
	@Override
	public Engagement create(long engagementId) {
		Engagement engagement = new EngagementImpl();

		engagement.setNew(true);
		engagement.setPrimaryKey(engagementId);

		engagement.setCompanyId(companyProvider.getCompanyId());

		return engagement;
	}

	/**
	 * Removes the engagement with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param engagementId the primary key of the engagement
	 * @return the engagement that was removed
	 * @throws NoSuchEngagementException if a engagement with the primary key could not be found
	 */
	@Override
	public Engagement remove(long engagementId)
		throws NoSuchEngagementException {
		return remove((Serializable)engagementId);
	}

	/**
	 * Removes the engagement with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the engagement
	 * @return the engagement that was removed
	 * @throws NoSuchEngagementException if a engagement with the primary key could not be found
	 */
	@Override
	public Engagement remove(Serializable primaryKey)
		throws NoSuchEngagementException {
		Session session = null;

		try {
			session = openSession();

			Engagement engagement = (Engagement)session.get(EngagementImpl.class,
					primaryKey);

			if (engagement == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchEngagementException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(engagement);
		}
		catch (NoSuchEngagementException nsee) {
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
	protected Engagement removeImpl(Engagement engagement) {
		engagement = toUnwrappedModel(engagement);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(engagement)) {
				engagement = (Engagement)session.get(EngagementImpl.class,
						engagement.getPrimaryKeyObj());
			}

			if (engagement != null) {
				session.delete(engagement);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (engagement != null) {
			clearCache(engagement);
		}

		return engagement;
	}

	@Override
	public Engagement updateImpl(Engagement engagement) {
		engagement = toUnwrappedModel(engagement);

		boolean isNew = engagement.isNew();

		EngagementModelImpl engagementModelImpl = (EngagementModelImpl)engagement;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (engagement.getCreateDate() == null)) {
			if (serviceContext == null) {
				engagement.setCreateDate(now);
			}
			else {
				engagement.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!engagementModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				engagement.setModifiedDate(now);
			}
			else {
				engagement.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (engagement.isNew()) {
				session.save(engagement);

				engagement.setNew(false);
			}
			else {
				engagement = (Engagement)session.merge(engagement);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !EngagementModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((engagementModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROJECTID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						engagementModelImpl.getOriginalProjectId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PROJECTID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROJECTID,
					args);

				args = new Object[] { engagementModelImpl.getProjectId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PROJECTID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROJECTID,
					args);
			}
		}

		entityCache.putResult(EngagementModelImpl.ENTITY_CACHE_ENABLED,
			EngagementImpl.class, engagement.getPrimaryKey(), engagement, false);

		engagement.resetOriginalValues();

		return engagement;
	}

	protected Engagement toUnwrappedModel(Engagement engagement) {
		if (engagement instanceof EngagementImpl) {
			return engagement;
		}

		EngagementImpl engagementImpl = new EngagementImpl();

		engagementImpl.setNew(engagement.isNew());
		engagementImpl.setPrimaryKey(engagement.getPrimaryKey());

		engagementImpl.setEngagementId(engagement.getEngagementId());
		engagementImpl.setCompanyId(engagement.getCompanyId());
		engagementImpl.setCreateDate(engagement.getCreateDate());
		engagementImpl.setModifiedDate(engagement.getModifiedDate());
		engagementImpl.setCalendarBookingId(engagement.getCalendarBookingId());
		engagementImpl.setClientId(engagement.getClientId());
		engagementImpl.setProjectId(engagement.getProjectId());
		engagementImpl.setTitle(engagement.getTitle());
		engagementImpl.setDescription(engagement.getDescription());
		engagementImpl.setLeadUserId(engagement.getLeadUserId());
		engagementImpl.setLeadName(engagement.getLeadName());
		engagementImpl.setTypeCategoryId(engagement.getTypeCategoryId());
		engagementImpl.setDifficultyId(engagement.getDifficultyId());
		engagementImpl.setProgressStatusId(engagement.getProgressStatusId());
		engagementImpl.setApprovalStatusId(engagement.getApprovalStatusId());

		return engagementImpl;
	}

	/**
	 * Returns the engagement with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the engagement
	 * @return the engagement
	 * @throws NoSuchEngagementException if a engagement with the primary key could not be found
	 */
	@Override
	public Engagement findByPrimaryKey(Serializable primaryKey)
		throws NoSuchEngagementException {
		Engagement engagement = fetchByPrimaryKey(primaryKey);

		if (engagement == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchEngagementException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return engagement;
	}

	/**
	 * Returns the engagement with the primary key or throws a {@link NoSuchEngagementException} if it could not be found.
	 *
	 * @param engagementId the primary key of the engagement
	 * @return the engagement
	 * @throws NoSuchEngagementException if a engagement with the primary key could not be found
	 */
	@Override
	public Engagement findByPrimaryKey(long engagementId)
		throws NoSuchEngagementException {
		return findByPrimaryKey((Serializable)engagementId);
	}

	/**
	 * Returns the engagement with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the engagement
	 * @return the engagement, or <code>null</code> if a engagement with the primary key could not be found
	 */
	@Override
	public Engagement fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(EngagementModelImpl.ENTITY_CACHE_ENABLED,
				EngagementImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Engagement engagement = (Engagement)serializable;

		if (engagement == null) {
			Session session = null;

			try {
				session = openSession();

				engagement = (Engagement)session.get(EngagementImpl.class,
						primaryKey);

				if (engagement != null) {
					cacheResult(engagement);
				}
				else {
					entityCache.putResult(EngagementModelImpl.ENTITY_CACHE_ENABLED,
						EngagementImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(EngagementModelImpl.ENTITY_CACHE_ENABLED,
					EngagementImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return engagement;
	}

	/**
	 * Returns the engagement with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param engagementId the primary key of the engagement
	 * @return the engagement, or <code>null</code> if a engagement with the primary key could not be found
	 */
	@Override
	public Engagement fetchByPrimaryKey(long engagementId) {
		return fetchByPrimaryKey((Serializable)engagementId);
	}

	@Override
	public Map<Serializable, Engagement> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Engagement> map = new HashMap<Serializable, Engagement>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Engagement engagement = fetchByPrimaryKey(primaryKey);

			if (engagement != null) {
				map.put(primaryKey, engagement);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(EngagementModelImpl.ENTITY_CACHE_ENABLED,
					EngagementImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Engagement)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_ENGAGEMENT_WHERE_PKS_IN);

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

			for (Engagement engagement : (List<Engagement>)q.list()) {
				map.put(engagement.getPrimaryKeyObj(), engagement);

				cacheResult(engagement);

				uncachedPrimaryKeys.remove(engagement.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(EngagementModelImpl.ENTITY_CACHE_ENABLED,
					EngagementImpl.class, primaryKey, nullModel);
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
	 * Returns all the engagements.
	 *
	 * @return the engagements
	 */
	@Override
	public List<Engagement> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the engagements.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EngagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of engagements
	 * @param end the upper bound of the range of engagements (not inclusive)
	 * @return the range of engagements
	 */
	@Override
	public List<Engagement> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the engagements.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EngagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of engagements
	 * @param end the upper bound of the range of engagements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of engagements
	 */
	@Override
	public List<Engagement> findAll(int start, int end,
		OrderByComparator<Engagement> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the engagements.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EngagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of engagements
	 * @param end the upper bound of the range of engagements (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of engagements
	 */
	@Override
	public List<Engagement> findAll(int start, int end,
		OrderByComparator<Engagement> orderByComparator,
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

		List<Engagement> list = null;

		if (retrieveFromCache) {
			list = (List<Engagement>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_ENGAGEMENT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ENGAGEMENT;

				if (pagination) {
					sql = sql.concat(EngagementModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Engagement>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Engagement>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the engagements from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Engagement engagement : findAll()) {
			remove(engagement);
		}
	}

	/**
	 * Returns the number of engagements.
	 *
	 * @return the number of engagements
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_ENGAGEMENT);

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
		return EngagementModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the engagement persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(EngagementImpl.class.getName());
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
	private static final String _SQL_SELECT_ENGAGEMENT = "SELECT engagement FROM Engagement engagement";
	private static final String _SQL_SELECT_ENGAGEMENT_WHERE_PKS_IN = "SELECT engagement FROM Engagement engagement WHERE engagementId IN (";
	private static final String _SQL_SELECT_ENGAGEMENT_WHERE = "SELECT engagement FROM Engagement engagement WHERE ";
	private static final String _SQL_COUNT_ENGAGEMENT = "SELECT COUNT(engagement) FROM Engagement engagement";
	private static final String _SQL_COUNT_ENGAGEMENT_WHERE = "SELECT COUNT(engagement) FROM Engagement engagement WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "engagement.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Engagement exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Engagement exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(EngagementPersistenceImpl.class);
}