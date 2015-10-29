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

package com.liferay.portal.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.NoSuchRecentLayoutSetException;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.MVCCModel;
import com.liferay.portal.model.RecentLayoutSet;
import com.liferay.portal.model.impl.RecentLayoutSetImpl;
import com.liferay.portal.model.impl.RecentLayoutSetModelImpl;
import com.liferay.portal.service.persistence.RecentLayoutSetPersistence;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the recent layout set service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RecentLayoutSetPersistence
 * @see com.liferay.portal.service.persistence.RecentLayoutSetUtil
 * @generated
 */
@ProviderType
public class RecentLayoutSetPersistenceImpl extends BasePersistenceImpl<RecentLayoutSet>
	implements RecentLayoutSetPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link RecentLayoutSetUtil} to access the recent layout set persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = RecentLayoutSetImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(RecentLayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			RecentLayoutSetModelImpl.FINDER_CACHE_ENABLED,
			RecentLayoutSetImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(RecentLayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			RecentLayoutSetModelImpl.FINDER_CACHE_ENABLED,
			RecentLayoutSetImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(RecentLayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			RecentLayoutSetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(RecentLayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			RecentLayoutSetModelImpl.FINDER_CACHE_ENABLED,
			RecentLayoutSetImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(RecentLayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			RecentLayoutSetModelImpl.FINDER_CACHE_ENABLED,
			RecentLayoutSetImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			RecentLayoutSetModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(RecentLayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			RecentLayoutSetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the recent layout sets where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching recent layout sets
	 */
	@Override
	public List<RecentLayoutSet> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the recent layout sets where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RecentLayoutSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of recent layout sets
	 * @param end the upper bound of the range of recent layout sets (not inclusive)
	 * @return the range of matching recent layout sets
	 */
	@Override
	public List<RecentLayoutSet> findByGroupId(long groupId, int start, int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the recent layout sets where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RecentLayoutSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of recent layout sets
	 * @param end the upper bound of the range of recent layout sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching recent layout sets
	 */
	@Override
	public List<RecentLayoutSet> findByGroupId(long groupId, int start,
		int end, OrderByComparator<RecentLayoutSet> orderByComparator) {
		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the recent layout sets where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RecentLayoutSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of recent layout sets
	 * @param end the upper bound of the range of recent layout sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching recent layout sets
	 */
	@Override
	public List<RecentLayoutSet> findByGroupId(long groupId, int start,
		int end, OrderByComparator<RecentLayoutSet> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID;
			finderArgs = new Object[] { groupId, start, end, orderByComparator };
		}

		List<RecentLayoutSet> list = null;

		if (retrieveFromCache) {
			list = (List<RecentLayoutSet>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (RecentLayoutSet recentLayoutSet : list) {
					if ((groupId != recentLayoutSet.getGroupId())) {
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
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_RECENTLAYOUTSET_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(RecentLayoutSetModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<RecentLayoutSet>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<RecentLayoutSet>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Returns the first recent layout set in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching recent layout set
	 * @throws NoSuchRecentLayoutSetException if a matching recent layout set could not be found
	 */
	@Override
	public RecentLayoutSet findByGroupId_First(long groupId,
		OrderByComparator<RecentLayoutSet> orderByComparator)
		throws NoSuchRecentLayoutSetException {
		RecentLayoutSet recentLayoutSet = fetchByGroupId_First(groupId,
				orderByComparator);

		if (recentLayoutSet != null) {
			return recentLayoutSet;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRecentLayoutSetException(msg.toString());
	}

	/**
	 * Returns the first recent layout set in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching recent layout set, or <code>null</code> if a matching recent layout set could not be found
	 */
	@Override
	public RecentLayoutSet fetchByGroupId_First(long groupId,
		OrderByComparator<RecentLayoutSet> orderByComparator) {
		List<RecentLayoutSet> list = findByGroupId(groupId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last recent layout set in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching recent layout set
	 * @throws NoSuchRecentLayoutSetException if a matching recent layout set could not be found
	 */
	@Override
	public RecentLayoutSet findByGroupId_Last(long groupId,
		OrderByComparator<RecentLayoutSet> orderByComparator)
		throws NoSuchRecentLayoutSetException {
		RecentLayoutSet recentLayoutSet = fetchByGroupId_Last(groupId,
				orderByComparator);

		if (recentLayoutSet != null) {
			return recentLayoutSet;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRecentLayoutSetException(msg.toString());
	}

	/**
	 * Returns the last recent layout set in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching recent layout set, or <code>null</code> if a matching recent layout set could not be found
	 */
	@Override
	public RecentLayoutSet fetchByGroupId_Last(long groupId,
		OrderByComparator<RecentLayoutSet> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<RecentLayoutSet> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the recent layout sets before and after the current recent layout set in the ordered set where groupId = &#63;.
	 *
	 * @param recentLayoutSetId the primary key of the current recent layout set
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next recent layout set
	 * @throws NoSuchRecentLayoutSetException if a recent layout set with the primary key could not be found
	 */
	@Override
	public RecentLayoutSet[] findByGroupId_PrevAndNext(long recentLayoutSetId,
		long groupId, OrderByComparator<RecentLayoutSet> orderByComparator)
		throws NoSuchRecentLayoutSetException {
		RecentLayoutSet recentLayoutSet = findByPrimaryKey(recentLayoutSetId);

		Session session = null;

		try {
			session = openSession();

			RecentLayoutSet[] array = new RecentLayoutSetImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, recentLayoutSet,
					groupId, orderByComparator, true);

			array[1] = recentLayoutSet;

			array[2] = getByGroupId_PrevAndNext(session, recentLayoutSet,
					groupId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected RecentLayoutSet getByGroupId_PrevAndNext(Session session,
		RecentLayoutSet recentLayoutSet, long groupId,
		OrderByComparator<RecentLayoutSet> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_RECENTLAYOUTSET_WHERE);

		query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

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
			query.append(RecentLayoutSetModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(recentLayoutSet);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<RecentLayoutSet> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the recent layout sets where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (RecentLayoutSet recentLayoutSet : findByGroupId(groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(recentLayoutSet);
		}
	}

	/**
	 * Returns the number of recent layout sets where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching recent layout sets
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_RECENTLAYOUTSET_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "recentLayoutSet.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(RecentLayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			RecentLayoutSetModelImpl.FINDER_CACHE_ENABLED,
			RecentLayoutSetImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByUserId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(RecentLayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			RecentLayoutSetModelImpl.FINDER_CACHE_ENABLED,
			RecentLayoutSetImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] { Long.class.getName() },
			RecentLayoutSetModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(RecentLayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			RecentLayoutSetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the recent layout sets where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching recent layout sets
	 */
	@Override
	public List<RecentLayoutSet> findByUserId(long userId) {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the recent layout sets where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RecentLayoutSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of recent layout sets
	 * @param end the upper bound of the range of recent layout sets (not inclusive)
	 * @return the range of matching recent layout sets
	 */
	@Override
	public List<RecentLayoutSet> findByUserId(long userId, int start, int end) {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the recent layout sets where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RecentLayoutSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of recent layout sets
	 * @param end the upper bound of the range of recent layout sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching recent layout sets
	 */
	@Override
	public List<RecentLayoutSet> findByUserId(long userId, int start, int end,
		OrderByComparator<RecentLayoutSet> orderByComparator) {
		return findByUserId(userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the recent layout sets where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RecentLayoutSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of recent layout sets
	 * @param end the upper bound of the range of recent layout sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching recent layout sets
	 */
	@Override
	public List<RecentLayoutSet> findByUserId(long userId, int start, int end,
		OrderByComparator<RecentLayoutSet> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { userId, start, end, orderByComparator };
		}

		List<RecentLayoutSet> list = null;

		if (retrieveFromCache) {
			list = (List<RecentLayoutSet>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (RecentLayoutSet recentLayoutSet : list) {
					if ((userId != recentLayoutSet.getUserId())) {
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
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_RECENTLAYOUTSET_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(RecentLayoutSetModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (!pagination) {
					list = (List<RecentLayoutSet>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<RecentLayoutSet>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Returns the first recent layout set in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching recent layout set
	 * @throws NoSuchRecentLayoutSetException if a matching recent layout set could not be found
	 */
	@Override
	public RecentLayoutSet findByUserId_First(long userId,
		OrderByComparator<RecentLayoutSet> orderByComparator)
		throws NoSuchRecentLayoutSetException {
		RecentLayoutSet recentLayoutSet = fetchByUserId_First(userId,
				orderByComparator);

		if (recentLayoutSet != null) {
			return recentLayoutSet;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRecentLayoutSetException(msg.toString());
	}

	/**
	 * Returns the first recent layout set in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching recent layout set, or <code>null</code> if a matching recent layout set could not be found
	 */
	@Override
	public RecentLayoutSet fetchByUserId_First(long userId,
		OrderByComparator<RecentLayoutSet> orderByComparator) {
		List<RecentLayoutSet> list = findByUserId(userId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last recent layout set in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching recent layout set
	 * @throws NoSuchRecentLayoutSetException if a matching recent layout set could not be found
	 */
	@Override
	public RecentLayoutSet findByUserId_Last(long userId,
		OrderByComparator<RecentLayoutSet> orderByComparator)
		throws NoSuchRecentLayoutSetException {
		RecentLayoutSet recentLayoutSet = fetchByUserId_Last(userId,
				orderByComparator);

		if (recentLayoutSet != null) {
			return recentLayoutSet;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRecentLayoutSetException(msg.toString());
	}

	/**
	 * Returns the last recent layout set in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching recent layout set, or <code>null</code> if a matching recent layout set could not be found
	 */
	@Override
	public RecentLayoutSet fetchByUserId_Last(long userId,
		OrderByComparator<RecentLayoutSet> orderByComparator) {
		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<RecentLayoutSet> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the recent layout sets before and after the current recent layout set in the ordered set where userId = &#63;.
	 *
	 * @param recentLayoutSetId the primary key of the current recent layout set
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next recent layout set
	 * @throws NoSuchRecentLayoutSetException if a recent layout set with the primary key could not be found
	 */
	@Override
	public RecentLayoutSet[] findByUserId_PrevAndNext(long recentLayoutSetId,
		long userId, OrderByComparator<RecentLayoutSet> orderByComparator)
		throws NoSuchRecentLayoutSetException {
		RecentLayoutSet recentLayoutSet = findByPrimaryKey(recentLayoutSetId);

		Session session = null;

		try {
			session = openSession();

			RecentLayoutSet[] array = new RecentLayoutSetImpl[3];

			array[0] = getByUserId_PrevAndNext(session, recentLayoutSet,
					userId, orderByComparator, true);

			array[1] = recentLayoutSet;

			array[2] = getByUserId_PrevAndNext(session, recentLayoutSet,
					userId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected RecentLayoutSet getByUserId_PrevAndNext(Session session,
		RecentLayoutSet recentLayoutSet, long userId,
		OrderByComparator<RecentLayoutSet> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_RECENTLAYOUTSET_WHERE);

		query.append(_FINDER_COLUMN_USERID_USERID_2);

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
			query.append(RecentLayoutSetModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(recentLayoutSet);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<RecentLayoutSet> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the recent layout sets where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByUserId(long userId) {
		for (RecentLayoutSet recentLayoutSet : findByUserId(userId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(recentLayoutSet);
		}
	}

	/**
	 * Returns the number of recent layout sets where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching recent layout sets
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_USERID;

		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_RECENTLAYOUTSET_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

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

	private static final String _FINDER_COLUMN_USERID_USERID_2 = "recentLayoutSet.userId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LAYOUTSETBRANCHID =
		new FinderPath(RecentLayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			RecentLayoutSetModelImpl.FINDER_CACHE_ENABLED,
			RecentLayoutSetImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByLayoutSetBranchId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LAYOUTSETBRANCHID =
		new FinderPath(RecentLayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			RecentLayoutSetModelImpl.FINDER_CACHE_ENABLED,
			RecentLayoutSetImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByLayoutSetBranchId", new String[] { Long.class.getName() },
			RecentLayoutSetModelImpl.LAYOUTSETBRANCHID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_LAYOUTSETBRANCHID = new FinderPath(RecentLayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			RecentLayoutSetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByLayoutSetBranchId", new String[] { Long.class.getName() });

	/**
	 * Returns all the recent layout sets where layoutSetBranchId = &#63;.
	 *
	 * @param layoutSetBranchId the layout set branch ID
	 * @return the matching recent layout sets
	 */
	@Override
	public List<RecentLayoutSet> findByLayoutSetBranchId(long layoutSetBranchId) {
		return findByLayoutSetBranchId(layoutSetBranchId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the recent layout sets where layoutSetBranchId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RecentLayoutSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param layoutSetBranchId the layout set branch ID
	 * @param start the lower bound of the range of recent layout sets
	 * @param end the upper bound of the range of recent layout sets (not inclusive)
	 * @return the range of matching recent layout sets
	 */
	@Override
	public List<RecentLayoutSet> findByLayoutSetBranchId(
		long layoutSetBranchId, int start, int end) {
		return findByLayoutSetBranchId(layoutSetBranchId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the recent layout sets where layoutSetBranchId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RecentLayoutSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param layoutSetBranchId the layout set branch ID
	 * @param start the lower bound of the range of recent layout sets
	 * @param end the upper bound of the range of recent layout sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching recent layout sets
	 */
	@Override
	public List<RecentLayoutSet> findByLayoutSetBranchId(
		long layoutSetBranchId, int start, int end,
		OrderByComparator<RecentLayoutSet> orderByComparator) {
		return findByLayoutSetBranchId(layoutSetBranchId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the recent layout sets where layoutSetBranchId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RecentLayoutSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param layoutSetBranchId the layout set branch ID
	 * @param start the lower bound of the range of recent layout sets
	 * @param end the upper bound of the range of recent layout sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching recent layout sets
	 */
	@Override
	public List<RecentLayoutSet> findByLayoutSetBranchId(
		long layoutSetBranchId, int start, int end,
		OrderByComparator<RecentLayoutSet> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LAYOUTSETBRANCHID;
			finderArgs = new Object[] { layoutSetBranchId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_LAYOUTSETBRANCHID;
			finderArgs = new Object[] {
					layoutSetBranchId,
					
					start, end, orderByComparator
				};
		}

		List<RecentLayoutSet> list = null;

		if (retrieveFromCache) {
			list = (List<RecentLayoutSet>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (RecentLayoutSet recentLayoutSet : list) {
					if ((layoutSetBranchId != recentLayoutSet.getLayoutSetBranchId())) {
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
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_RECENTLAYOUTSET_WHERE);

			query.append(_FINDER_COLUMN_LAYOUTSETBRANCHID_LAYOUTSETBRANCHID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(RecentLayoutSetModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(layoutSetBranchId);

				if (!pagination) {
					list = (List<RecentLayoutSet>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<RecentLayoutSet>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Returns the first recent layout set in the ordered set where layoutSetBranchId = &#63;.
	 *
	 * @param layoutSetBranchId the layout set branch ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching recent layout set
	 * @throws NoSuchRecentLayoutSetException if a matching recent layout set could not be found
	 */
	@Override
	public RecentLayoutSet findByLayoutSetBranchId_First(
		long layoutSetBranchId,
		OrderByComparator<RecentLayoutSet> orderByComparator)
		throws NoSuchRecentLayoutSetException {
		RecentLayoutSet recentLayoutSet = fetchByLayoutSetBranchId_First(layoutSetBranchId,
				orderByComparator);

		if (recentLayoutSet != null) {
			return recentLayoutSet;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("layoutSetBranchId=");
		msg.append(layoutSetBranchId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRecentLayoutSetException(msg.toString());
	}

	/**
	 * Returns the first recent layout set in the ordered set where layoutSetBranchId = &#63;.
	 *
	 * @param layoutSetBranchId the layout set branch ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching recent layout set, or <code>null</code> if a matching recent layout set could not be found
	 */
	@Override
	public RecentLayoutSet fetchByLayoutSetBranchId_First(
		long layoutSetBranchId,
		OrderByComparator<RecentLayoutSet> orderByComparator) {
		List<RecentLayoutSet> list = findByLayoutSetBranchId(layoutSetBranchId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last recent layout set in the ordered set where layoutSetBranchId = &#63;.
	 *
	 * @param layoutSetBranchId the layout set branch ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching recent layout set
	 * @throws NoSuchRecentLayoutSetException if a matching recent layout set could not be found
	 */
	@Override
	public RecentLayoutSet findByLayoutSetBranchId_Last(
		long layoutSetBranchId,
		OrderByComparator<RecentLayoutSet> orderByComparator)
		throws NoSuchRecentLayoutSetException {
		RecentLayoutSet recentLayoutSet = fetchByLayoutSetBranchId_Last(layoutSetBranchId,
				orderByComparator);

		if (recentLayoutSet != null) {
			return recentLayoutSet;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("layoutSetBranchId=");
		msg.append(layoutSetBranchId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRecentLayoutSetException(msg.toString());
	}

	/**
	 * Returns the last recent layout set in the ordered set where layoutSetBranchId = &#63;.
	 *
	 * @param layoutSetBranchId the layout set branch ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching recent layout set, or <code>null</code> if a matching recent layout set could not be found
	 */
	@Override
	public RecentLayoutSet fetchByLayoutSetBranchId_Last(
		long layoutSetBranchId,
		OrderByComparator<RecentLayoutSet> orderByComparator) {
		int count = countByLayoutSetBranchId(layoutSetBranchId);

		if (count == 0) {
			return null;
		}

		List<RecentLayoutSet> list = findByLayoutSetBranchId(layoutSetBranchId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the recent layout sets before and after the current recent layout set in the ordered set where layoutSetBranchId = &#63;.
	 *
	 * @param recentLayoutSetId the primary key of the current recent layout set
	 * @param layoutSetBranchId the layout set branch ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next recent layout set
	 * @throws NoSuchRecentLayoutSetException if a recent layout set with the primary key could not be found
	 */
	@Override
	public RecentLayoutSet[] findByLayoutSetBranchId_PrevAndNext(
		long recentLayoutSetId, long layoutSetBranchId,
		OrderByComparator<RecentLayoutSet> orderByComparator)
		throws NoSuchRecentLayoutSetException {
		RecentLayoutSet recentLayoutSet = findByPrimaryKey(recentLayoutSetId);

		Session session = null;

		try {
			session = openSession();

			RecentLayoutSet[] array = new RecentLayoutSetImpl[3];

			array[0] = getByLayoutSetBranchId_PrevAndNext(session,
					recentLayoutSet, layoutSetBranchId, orderByComparator, true);

			array[1] = recentLayoutSet;

			array[2] = getByLayoutSetBranchId_PrevAndNext(session,
					recentLayoutSet, layoutSetBranchId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected RecentLayoutSet getByLayoutSetBranchId_PrevAndNext(
		Session session, RecentLayoutSet recentLayoutSet,
		long layoutSetBranchId,
		OrderByComparator<RecentLayoutSet> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_RECENTLAYOUTSET_WHERE);

		query.append(_FINDER_COLUMN_LAYOUTSETBRANCHID_LAYOUTSETBRANCHID_2);

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
			query.append(RecentLayoutSetModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(layoutSetBranchId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(recentLayoutSet);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<RecentLayoutSet> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the recent layout sets where layoutSetBranchId = &#63; from the database.
	 *
	 * @param layoutSetBranchId the layout set branch ID
	 */
	@Override
	public void removeByLayoutSetBranchId(long layoutSetBranchId) {
		for (RecentLayoutSet recentLayoutSet : findByLayoutSetBranchId(
				layoutSetBranchId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(recentLayoutSet);
		}
	}

	/**
	 * Returns the number of recent layout sets where layoutSetBranchId = &#63;.
	 *
	 * @param layoutSetBranchId the layout set branch ID
	 * @return the number of matching recent layout sets
	 */
	@Override
	public int countByLayoutSetBranchId(long layoutSetBranchId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_LAYOUTSETBRANCHID;

		Object[] finderArgs = new Object[] { layoutSetBranchId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_RECENTLAYOUTSET_WHERE);

			query.append(_FINDER_COLUMN_LAYOUTSETBRANCHID_LAYOUTSETBRANCHID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(layoutSetBranchId);

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

	private static final String _FINDER_COLUMN_LAYOUTSETBRANCHID_LAYOUTSETBRANCHID_2 =
		"recentLayoutSet.layoutSetBranchId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_U_L = new FinderPath(RecentLayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			RecentLayoutSetModelImpl.FINDER_CACHE_ENABLED,
			RecentLayoutSetImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByU_L",
			new String[] { Long.class.getName(), Long.class.getName() },
			RecentLayoutSetModelImpl.USERID_COLUMN_BITMASK |
			RecentLayoutSetModelImpl.LAYOUTSETID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_L = new FinderPath(RecentLayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			RecentLayoutSetModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_L",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns the recent layout set where userId = &#63; and layoutSetId = &#63; or throws a {@link NoSuchRecentLayoutSetException} if it could not be found.
	 *
	 * @param userId the user ID
	 * @param layoutSetId the layout set ID
	 * @return the matching recent layout set
	 * @throws NoSuchRecentLayoutSetException if a matching recent layout set could not be found
	 */
	@Override
	public RecentLayoutSet findByU_L(long userId, long layoutSetId)
		throws NoSuchRecentLayoutSetException {
		RecentLayoutSet recentLayoutSet = fetchByU_L(userId, layoutSetId);

		if (recentLayoutSet == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(", layoutSetId=");
			msg.append(layoutSetId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchRecentLayoutSetException(msg.toString());
		}

		return recentLayoutSet;
	}

	/**
	 * Returns the recent layout set where userId = &#63; and layoutSetId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @param layoutSetId the layout set ID
	 * @return the matching recent layout set, or <code>null</code> if a matching recent layout set could not be found
	 */
	@Override
	public RecentLayoutSet fetchByU_L(long userId, long layoutSetId) {
		return fetchByU_L(userId, layoutSetId, true);
	}

	/**
	 * Returns the recent layout set where userId = &#63; and layoutSetId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param layoutSetId the layout set ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching recent layout set, or <code>null</code> if a matching recent layout set could not be found
	 */
	@Override
	public RecentLayoutSet fetchByU_L(long userId, long layoutSetId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { userId, layoutSetId };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_U_L,
					finderArgs, this);
		}

		if (result instanceof RecentLayoutSet) {
			RecentLayoutSet recentLayoutSet = (RecentLayoutSet)result;

			if ((userId != recentLayoutSet.getUserId()) ||
					(layoutSetId != recentLayoutSet.getLayoutSetId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_RECENTLAYOUTSET_WHERE);

			query.append(_FINDER_COLUMN_U_L_USERID_2);

			query.append(_FINDER_COLUMN_U_L_LAYOUTSETID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(layoutSetId);

				List<RecentLayoutSet> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_U_L, finderArgs,
						list);
				}
				else {
					RecentLayoutSet recentLayoutSet = list.get(0);

					result = recentLayoutSet;

					cacheResult(recentLayoutSet);

					if ((recentLayoutSet.getUserId() != userId) ||
							(recentLayoutSet.getLayoutSetId() != layoutSetId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_U_L,
							finderArgs, recentLayoutSet);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_U_L, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (RecentLayoutSet)result;
		}
	}

	/**
	 * Removes the recent layout set where userId = &#63; and layoutSetId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param layoutSetId the layout set ID
	 * @return the recent layout set that was removed
	 */
	@Override
	public RecentLayoutSet removeByU_L(long userId, long layoutSetId)
		throws NoSuchRecentLayoutSetException {
		RecentLayoutSet recentLayoutSet = findByU_L(userId, layoutSetId);

		return remove(recentLayoutSet);
	}

	/**
	 * Returns the number of recent layout sets where userId = &#63; and layoutSetId = &#63;.
	 *
	 * @param userId the user ID
	 * @param layoutSetId the layout set ID
	 * @return the number of matching recent layout sets
	 */
	@Override
	public int countByU_L(long userId, long layoutSetId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_U_L;

		Object[] finderArgs = new Object[] { userId, layoutSetId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_RECENTLAYOUTSET_WHERE);

			query.append(_FINDER_COLUMN_U_L_USERID_2);

			query.append(_FINDER_COLUMN_U_L_LAYOUTSETID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(layoutSetId);

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

	private static final String _FINDER_COLUMN_U_L_USERID_2 = "recentLayoutSet.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_L_LAYOUTSETID_2 = "recentLayoutSet.layoutSetId = ?";

	public RecentLayoutSetPersistenceImpl() {
		setModelClass(RecentLayoutSet.class);
	}

	/**
	 * Caches the recent layout set in the entity cache if it is enabled.
	 *
	 * @param recentLayoutSet the recent layout set
	 */
	@Override
	public void cacheResult(RecentLayoutSet recentLayoutSet) {
		entityCache.putResult(RecentLayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			RecentLayoutSetImpl.class, recentLayoutSet.getPrimaryKey(),
			recentLayoutSet);

		finderCache.putResult(FINDER_PATH_FETCH_BY_U_L,
			new Object[] {
				recentLayoutSet.getUserId(), recentLayoutSet.getLayoutSetId()
			}, recentLayoutSet);

		recentLayoutSet.resetOriginalValues();
	}

	/**
	 * Caches the recent layout sets in the entity cache if it is enabled.
	 *
	 * @param recentLayoutSets the recent layout sets
	 */
	@Override
	public void cacheResult(List<RecentLayoutSet> recentLayoutSets) {
		for (RecentLayoutSet recentLayoutSet : recentLayoutSets) {
			if (entityCache.getResult(
						RecentLayoutSetModelImpl.ENTITY_CACHE_ENABLED,
						RecentLayoutSetImpl.class,
						recentLayoutSet.getPrimaryKey()) == null) {
				cacheResult(recentLayoutSet);
			}
			else {
				recentLayoutSet.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all recent layout sets.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(RecentLayoutSetImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the recent layout set.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(RecentLayoutSet recentLayoutSet) {
		entityCache.removeResult(RecentLayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			RecentLayoutSetImpl.class, recentLayoutSet.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((RecentLayoutSetModelImpl)recentLayoutSet);
	}

	@Override
	public void clearCache(List<RecentLayoutSet> recentLayoutSets) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (RecentLayoutSet recentLayoutSet : recentLayoutSets) {
			entityCache.removeResult(RecentLayoutSetModelImpl.ENTITY_CACHE_ENABLED,
				RecentLayoutSetImpl.class, recentLayoutSet.getPrimaryKey());

			clearUniqueFindersCache((RecentLayoutSetModelImpl)recentLayoutSet);
		}
	}

	protected void cacheUniqueFindersCache(
		RecentLayoutSetModelImpl recentLayoutSetModelImpl, boolean isNew) {
		if (isNew) {
			Object[] args = new Object[] {
					recentLayoutSetModelImpl.getUserId(),
					recentLayoutSetModelImpl.getLayoutSetId()
				};

			finderCache.putResult(FINDER_PATH_COUNT_BY_U_L, args,
				Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_U_L, args,
				recentLayoutSetModelImpl);
		}
		else {
			if ((recentLayoutSetModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_U_L.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						recentLayoutSetModelImpl.getUserId(),
						recentLayoutSetModelImpl.getLayoutSetId()
					};

				finderCache.putResult(FINDER_PATH_COUNT_BY_U_L, args,
					Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_U_L, args,
					recentLayoutSetModelImpl);
			}
		}
	}

	protected void clearUniqueFindersCache(
		RecentLayoutSetModelImpl recentLayoutSetModelImpl) {
		Object[] args = new Object[] {
				recentLayoutSetModelImpl.getUserId(),
				recentLayoutSetModelImpl.getLayoutSetId()
			};

		finderCache.removeResult(FINDER_PATH_COUNT_BY_U_L, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_U_L, args);

		if ((recentLayoutSetModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_U_L.getColumnBitmask()) != 0) {
			args = new Object[] {
					recentLayoutSetModelImpl.getOriginalUserId(),
					recentLayoutSetModelImpl.getOriginalLayoutSetId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_U_L, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_U_L, args);
		}
	}

	/**
	 * Creates a new recent layout set with the primary key. Does not add the recent layout set to the database.
	 *
	 * @param recentLayoutSetId the primary key for the new recent layout set
	 * @return the new recent layout set
	 */
	@Override
	public RecentLayoutSet create(long recentLayoutSetId) {
		RecentLayoutSet recentLayoutSet = new RecentLayoutSetImpl();

		recentLayoutSet.setNew(true);
		recentLayoutSet.setPrimaryKey(recentLayoutSetId);

		return recentLayoutSet;
	}

	/**
	 * Removes the recent layout set with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param recentLayoutSetId the primary key of the recent layout set
	 * @return the recent layout set that was removed
	 * @throws NoSuchRecentLayoutSetException if a recent layout set with the primary key could not be found
	 */
	@Override
	public RecentLayoutSet remove(long recentLayoutSetId)
		throws NoSuchRecentLayoutSetException {
		return remove((Serializable)recentLayoutSetId);
	}

	/**
	 * Removes the recent layout set with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the recent layout set
	 * @return the recent layout set that was removed
	 * @throws NoSuchRecentLayoutSetException if a recent layout set with the primary key could not be found
	 */
	@Override
	public RecentLayoutSet remove(Serializable primaryKey)
		throws NoSuchRecentLayoutSetException {
		Session session = null;

		try {
			session = openSession();

			RecentLayoutSet recentLayoutSet = (RecentLayoutSet)session.get(RecentLayoutSetImpl.class,
					primaryKey);

			if (recentLayoutSet == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchRecentLayoutSetException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(recentLayoutSet);
		}
		catch (NoSuchRecentLayoutSetException nsee) {
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
	protected RecentLayoutSet removeImpl(RecentLayoutSet recentLayoutSet) {
		recentLayoutSet = toUnwrappedModel(recentLayoutSet);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(recentLayoutSet)) {
				recentLayoutSet = (RecentLayoutSet)session.get(RecentLayoutSetImpl.class,
						recentLayoutSet.getPrimaryKeyObj());
			}

			if (recentLayoutSet != null) {
				session.delete(recentLayoutSet);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (recentLayoutSet != null) {
			clearCache(recentLayoutSet);
		}

		return recentLayoutSet;
	}

	@Override
	public RecentLayoutSet updateImpl(RecentLayoutSet recentLayoutSet) {
		recentLayoutSet = toUnwrappedModel(recentLayoutSet);

		boolean isNew = recentLayoutSet.isNew();

		RecentLayoutSetModelImpl recentLayoutSetModelImpl = (RecentLayoutSetModelImpl)recentLayoutSet;

		Session session = null;

		try {
			session = openSession();

			if (recentLayoutSet.isNew()) {
				session.save(recentLayoutSet);

				recentLayoutSet.setNew(false);
			}
			else {
				recentLayoutSet = (RecentLayoutSet)session.merge(recentLayoutSet);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !RecentLayoutSetModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((recentLayoutSetModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						recentLayoutSetModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { recentLayoutSetModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((recentLayoutSetModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						recentLayoutSetModelImpl.getOriginalUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] { recentLayoutSetModelImpl.getUserId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}

			if ((recentLayoutSetModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LAYOUTSETBRANCHID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						recentLayoutSetModelImpl.getOriginalLayoutSetBranchId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LAYOUTSETBRANCHID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LAYOUTSETBRANCHID,
					args);

				args = new Object[] {
						recentLayoutSetModelImpl.getLayoutSetBranchId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LAYOUTSETBRANCHID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LAYOUTSETBRANCHID,
					args);
			}
		}

		entityCache.putResult(RecentLayoutSetModelImpl.ENTITY_CACHE_ENABLED,
			RecentLayoutSetImpl.class, recentLayoutSet.getPrimaryKey(),
			recentLayoutSet, false);

		clearUniqueFindersCache(recentLayoutSetModelImpl);
		cacheUniqueFindersCache(recentLayoutSetModelImpl, isNew);

		recentLayoutSet.resetOriginalValues();

		return recentLayoutSet;
	}

	protected RecentLayoutSet toUnwrappedModel(RecentLayoutSet recentLayoutSet) {
		if (recentLayoutSet instanceof RecentLayoutSetImpl) {
			return recentLayoutSet;
		}

		RecentLayoutSetImpl recentLayoutSetImpl = new RecentLayoutSetImpl();

		recentLayoutSetImpl.setNew(recentLayoutSet.isNew());
		recentLayoutSetImpl.setPrimaryKey(recentLayoutSet.getPrimaryKey());

		recentLayoutSetImpl.setMvccVersion(recentLayoutSet.getMvccVersion());
		recentLayoutSetImpl.setRecentLayoutSetId(recentLayoutSet.getRecentLayoutSetId());
		recentLayoutSetImpl.setGroupId(recentLayoutSet.getGroupId());
		recentLayoutSetImpl.setCompanyId(recentLayoutSet.getCompanyId());
		recentLayoutSetImpl.setUserId(recentLayoutSet.getUserId());
		recentLayoutSetImpl.setLayoutSetId(recentLayoutSet.getLayoutSetId());
		recentLayoutSetImpl.setLayoutSetBranchId(recentLayoutSet.getLayoutSetBranchId());

		return recentLayoutSetImpl;
	}

	/**
	 * Returns the recent layout set with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the recent layout set
	 * @return the recent layout set
	 * @throws NoSuchRecentLayoutSetException if a recent layout set with the primary key could not be found
	 */
	@Override
	public RecentLayoutSet findByPrimaryKey(Serializable primaryKey)
		throws NoSuchRecentLayoutSetException {
		RecentLayoutSet recentLayoutSet = fetchByPrimaryKey(primaryKey);

		if (recentLayoutSet == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchRecentLayoutSetException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return recentLayoutSet;
	}

	/**
	 * Returns the recent layout set with the primary key or throws a {@link NoSuchRecentLayoutSetException} if it could not be found.
	 *
	 * @param recentLayoutSetId the primary key of the recent layout set
	 * @return the recent layout set
	 * @throws NoSuchRecentLayoutSetException if a recent layout set with the primary key could not be found
	 */
	@Override
	public RecentLayoutSet findByPrimaryKey(long recentLayoutSetId)
		throws NoSuchRecentLayoutSetException {
		return findByPrimaryKey((Serializable)recentLayoutSetId);
	}

	/**
	 * Returns the recent layout set with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the recent layout set
	 * @return the recent layout set, or <code>null</code> if a recent layout set with the primary key could not be found
	 */
	@Override
	public RecentLayoutSet fetchByPrimaryKey(Serializable primaryKey) {
		RecentLayoutSet recentLayoutSet = (RecentLayoutSet)entityCache.getResult(RecentLayoutSetModelImpl.ENTITY_CACHE_ENABLED,
				RecentLayoutSetImpl.class, primaryKey);

		if (recentLayoutSet == _nullRecentLayoutSet) {
			return null;
		}

		if (recentLayoutSet == null) {
			Session session = null;

			try {
				session = openSession();

				recentLayoutSet = (RecentLayoutSet)session.get(RecentLayoutSetImpl.class,
						primaryKey);

				if (recentLayoutSet != null) {
					cacheResult(recentLayoutSet);
				}
				else {
					entityCache.putResult(RecentLayoutSetModelImpl.ENTITY_CACHE_ENABLED,
						RecentLayoutSetImpl.class, primaryKey,
						_nullRecentLayoutSet);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(RecentLayoutSetModelImpl.ENTITY_CACHE_ENABLED,
					RecentLayoutSetImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return recentLayoutSet;
	}

	/**
	 * Returns the recent layout set with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param recentLayoutSetId the primary key of the recent layout set
	 * @return the recent layout set, or <code>null</code> if a recent layout set with the primary key could not be found
	 */
	@Override
	public RecentLayoutSet fetchByPrimaryKey(long recentLayoutSetId) {
		return fetchByPrimaryKey((Serializable)recentLayoutSetId);
	}

	@Override
	public Map<Serializable, RecentLayoutSet> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, RecentLayoutSet> map = new HashMap<Serializable, RecentLayoutSet>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			RecentLayoutSet recentLayoutSet = fetchByPrimaryKey(primaryKey);

			if (recentLayoutSet != null) {
				map.put(primaryKey, recentLayoutSet);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			RecentLayoutSet recentLayoutSet = (RecentLayoutSet)entityCache.getResult(RecentLayoutSetModelImpl.ENTITY_CACHE_ENABLED,
					RecentLayoutSetImpl.class, primaryKey);

			if (recentLayoutSet == null) {
				if (uncachedPrimaryKeys == null) {
					uncachedPrimaryKeys = new HashSet<Serializable>();
				}

				uncachedPrimaryKeys.add(primaryKey);
			}
			else {
				map.put(primaryKey, recentLayoutSet);
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_RECENTLAYOUTSET_WHERE_PKS_IN);

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

			for (RecentLayoutSet recentLayoutSet : (List<RecentLayoutSet>)q.list()) {
				map.put(recentLayoutSet.getPrimaryKeyObj(), recentLayoutSet);

				cacheResult(recentLayoutSet);

				uncachedPrimaryKeys.remove(recentLayoutSet.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(RecentLayoutSetModelImpl.ENTITY_CACHE_ENABLED,
					RecentLayoutSetImpl.class, primaryKey, _nullRecentLayoutSet);
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
	 * Returns all the recent layout sets.
	 *
	 * @return the recent layout sets
	 */
	@Override
	public List<RecentLayoutSet> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the recent layout sets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RecentLayoutSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of recent layout sets
	 * @param end the upper bound of the range of recent layout sets (not inclusive)
	 * @return the range of recent layout sets
	 */
	@Override
	public List<RecentLayoutSet> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the recent layout sets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RecentLayoutSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of recent layout sets
	 * @param end the upper bound of the range of recent layout sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of recent layout sets
	 */
	@Override
	public List<RecentLayoutSet> findAll(int start, int end,
		OrderByComparator<RecentLayoutSet> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the recent layout sets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RecentLayoutSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of recent layout sets
	 * @param end the upper bound of the range of recent layout sets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of recent layout sets
	 */
	@Override
	public List<RecentLayoutSet> findAll(int start, int end,
		OrderByComparator<RecentLayoutSet> orderByComparator,
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

		List<RecentLayoutSet> list = null;

		if (retrieveFromCache) {
			list = (List<RecentLayoutSet>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_RECENTLAYOUTSET);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_RECENTLAYOUTSET;

				if (pagination) {
					sql = sql.concat(RecentLayoutSetModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<RecentLayoutSet>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<RecentLayoutSet>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Removes all the recent layout sets from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (RecentLayoutSet recentLayoutSet : findAll()) {
			remove(recentLayoutSet);
		}
	}

	/**
	 * Returns the number of recent layout sets.
	 *
	 * @return the number of recent layout sets
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_RECENTLAYOUTSET);

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
		return RecentLayoutSetModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the recent layout set persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(RecentLayoutSetImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_RECENTLAYOUTSET = "SELECT recentLayoutSet FROM RecentLayoutSet recentLayoutSet";
	private static final String _SQL_SELECT_RECENTLAYOUTSET_WHERE_PKS_IN = "SELECT recentLayoutSet FROM RecentLayoutSet recentLayoutSet WHERE recentLayoutSetId IN (";
	private static final String _SQL_SELECT_RECENTLAYOUTSET_WHERE = "SELECT recentLayoutSet FROM RecentLayoutSet recentLayoutSet WHERE ";
	private static final String _SQL_COUNT_RECENTLAYOUTSET = "SELECT COUNT(recentLayoutSet) FROM RecentLayoutSet recentLayoutSet";
	private static final String _SQL_COUNT_RECENTLAYOUTSET_WHERE = "SELECT COUNT(recentLayoutSet) FROM RecentLayoutSet recentLayoutSet WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "recentLayoutSet.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No RecentLayoutSet exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No RecentLayoutSet exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(RecentLayoutSetPersistenceImpl.class);
	private static final RecentLayoutSet _nullRecentLayoutSet = new RecentLayoutSetImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<RecentLayoutSet> toCacheModel() {
				return _nullRecentLayoutSetCacheModel;
			}
		};

	private static final CacheModel<RecentLayoutSet> _nullRecentLayoutSetCacheModel =
		new NullCacheModel();

	private static class NullCacheModel implements CacheModel<RecentLayoutSet>,
		MVCCModel {
		@Override
		public long getMvccVersion() {
			return -1;
		}

		@Override
		public void setMvccVersion(long mvccVersion) {
		}

		@Override
		public RecentLayoutSet toEntityModel() {
			return _nullRecentLayoutSet;
		}
	}
}