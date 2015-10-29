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

import com.liferay.portal.NoSuchRecentLayoutException;
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
import com.liferay.portal.model.RecentLayout;
import com.liferay.portal.model.impl.RecentLayoutImpl;
import com.liferay.portal.model.impl.RecentLayoutModelImpl;
import com.liferay.portal.service.persistence.RecentLayoutPersistence;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the recent layout service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RecentLayoutPersistence
 * @see com.liferay.portal.service.persistence.RecentLayoutUtil
 * @generated
 */
@ProviderType
public class RecentLayoutPersistenceImpl extends BasePersistenceImpl<RecentLayout>
	implements RecentLayoutPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link RecentLayoutUtil} to access the recent layout persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = RecentLayoutImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(RecentLayoutModelImpl.ENTITY_CACHE_ENABLED,
			RecentLayoutModelImpl.FINDER_CACHE_ENABLED, RecentLayoutImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(RecentLayoutModelImpl.ENTITY_CACHE_ENABLED,
			RecentLayoutModelImpl.FINDER_CACHE_ENABLED, RecentLayoutImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(RecentLayoutModelImpl.ENTITY_CACHE_ENABLED,
			RecentLayoutModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(RecentLayoutModelImpl.ENTITY_CACHE_ENABLED,
			RecentLayoutModelImpl.FINDER_CACHE_ENABLED, RecentLayoutImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
		new FinderPath(RecentLayoutModelImpl.ENTITY_CACHE_ENABLED,
			RecentLayoutModelImpl.FINDER_CACHE_ENABLED, RecentLayoutImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] { Long.class.getName() },
			RecentLayoutModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(RecentLayoutModelImpl.ENTITY_CACHE_ENABLED,
			RecentLayoutModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the recent layouts where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching recent layouts
	 */
	@Override
	public List<RecentLayout> findByGroupId(long groupId) {
		return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the recent layouts where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RecentLayoutModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of recent layouts
	 * @param end the upper bound of the range of recent layouts (not inclusive)
	 * @return the range of matching recent layouts
	 */
	@Override
	public List<RecentLayout> findByGroupId(long groupId, int start, int end) {
		return findByGroupId(groupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the recent layouts where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RecentLayoutModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of recent layouts
	 * @param end the upper bound of the range of recent layouts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching recent layouts
	 */
	@Override
	public List<RecentLayout> findByGroupId(long groupId, int start, int end,
		OrderByComparator<RecentLayout> orderByComparator) {
		return findByGroupId(groupId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the recent layouts where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RecentLayoutModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of recent layouts
	 * @param end the upper bound of the range of recent layouts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching recent layouts
	 */
	@Override
	public List<RecentLayout> findByGroupId(long groupId, int start, int end,
		OrderByComparator<RecentLayout> orderByComparator,
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

		List<RecentLayout> list = null;

		if (retrieveFromCache) {
			list = (List<RecentLayout>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (RecentLayout recentLayout : list) {
					if ((groupId != recentLayout.getGroupId())) {
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

			query.append(_SQL_SELECT_RECENTLAYOUT_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(RecentLayoutModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				if (!pagination) {
					list = (List<RecentLayout>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<RecentLayout>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first recent layout in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching recent layout
	 * @throws NoSuchRecentLayoutException if a matching recent layout could not be found
	 */
	@Override
	public RecentLayout findByGroupId_First(long groupId,
		OrderByComparator<RecentLayout> orderByComparator)
		throws NoSuchRecentLayoutException {
		RecentLayout recentLayout = fetchByGroupId_First(groupId,
				orderByComparator);

		if (recentLayout != null) {
			return recentLayout;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRecentLayoutException(msg.toString());
	}

	/**
	 * Returns the first recent layout in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching recent layout, or <code>null</code> if a matching recent layout could not be found
	 */
	@Override
	public RecentLayout fetchByGroupId_First(long groupId,
		OrderByComparator<RecentLayout> orderByComparator) {
		List<RecentLayout> list = findByGroupId(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last recent layout in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching recent layout
	 * @throws NoSuchRecentLayoutException if a matching recent layout could not be found
	 */
	@Override
	public RecentLayout findByGroupId_Last(long groupId,
		OrderByComparator<RecentLayout> orderByComparator)
		throws NoSuchRecentLayoutException {
		RecentLayout recentLayout = fetchByGroupId_Last(groupId,
				orderByComparator);

		if (recentLayout != null) {
			return recentLayout;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRecentLayoutException(msg.toString());
	}

	/**
	 * Returns the last recent layout in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching recent layout, or <code>null</code> if a matching recent layout could not be found
	 */
	@Override
	public RecentLayout fetchByGroupId_Last(long groupId,
		OrderByComparator<RecentLayout> orderByComparator) {
		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<RecentLayout> list = findByGroupId(groupId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the recent layouts before and after the current recent layout in the ordered set where groupId = &#63;.
	 *
	 * @param recentLayoutId the primary key of the current recent layout
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next recent layout
	 * @throws NoSuchRecentLayoutException if a recent layout with the primary key could not be found
	 */
	@Override
	public RecentLayout[] findByGroupId_PrevAndNext(long recentLayoutId,
		long groupId, OrderByComparator<RecentLayout> orderByComparator)
		throws NoSuchRecentLayoutException {
		RecentLayout recentLayout = findByPrimaryKey(recentLayoutId);

		Session session = null;

		try {
			session = openSession();

			RecentLayout[] array = new RecentLayoutImpl[3];

			array[0] = getByGroupId_PrevAndNext(session, recentLayout, groupId,
					orderByComparator, true);

			array[1] = recentLayout;

			array[2] = getByGroupId_PrevAndNext(session, recentLayout, groupId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected RecentLayout getByGroupId_PrevAndNext(Session session,
		RecentLayout recentLayout, long groupId,
		OrderByComparator<RecentLayout> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_RECENTLAYOUT_WHERE);

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
			query.append(RecentLayoutModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(recentLayout);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<RecentLayout> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the recent layouts where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (RecentLayout recentLayout : findByGroupId(groupId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(recentLayout);
		}
	}

	/**
	 * Returns the number of recent layouts where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching recent layouts
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

		Object[] finderArgs = new Object[] { groupId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_RECENTLAYOUT_WHERE);

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

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "recentLayout.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(RecentLayoutModelImpl.ENTITY_CACHE_ENABLED,
			RecentLayoutModelImpl.FINDER_CACHE_ENABLED, RecentLayoutImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(RecentLayoutModelImpl.ENTITY_CACHE_ENABLED,
			RecentLayoutModelImpl.FINDER_CACHE_ENABLED, RecentLayoutImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] { Long.class.getName() },
			RecentLayoutModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(RecentLayoutModelImpl.ENTITY_CACHE_ENABLED,
			RecentLayoutModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the recent layouts where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching recent layouts
	 */
	@Override
	public List<RecentLayout> findByUserId(long userId) {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the recent layouts where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RecentLayoutModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of recent layouts
	 * @param end the upper bound of the range of recent layouts (not inclusive)
	 * @return the range of matching recent layouts
	 */
	@Override
	public List<RecentLayout> findByUserId(long userId, int start, int end) {
		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the recent layouts where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RecentLayoutModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of recent layouts
	 * @param end the upper bound of the range of recent layouts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching recent layouts
	 */
	@Override
	public List<RecentLayout> findByUserId(long userId, int start, int end,
		OrderByComparator<RecentLayout> orderByComparator) {
		return findByUserId(userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the recent layouts where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RecentLayoutModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of recent layouts
	 * @param end the upper bound of the range of recent layouts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching recent layouts
	 */
	@Override
	public List<RecentLayout> findByUserId(long userId, int start, int end,
		OrderByComparator<RecentLayout> orderByComparator,
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

		List<RecentLayout> list = null;

		if (retrieveFromCache) {
			list = (List<RecentLayout>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (RecentLayout recentLayout : list) {
					if ((userId != recentLayout.getUserId())) {
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

			query.append(_SQL_SELECT_RECENTLAYOUT_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(RecentLayoutModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (!pagination) {
					list = (List<RecentLayout>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<RecentLayout>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first recent layout in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching recent layout
	 * @throws NoSuchRecentLayoutException if a matching recent layout could not be found
	 */
	@Override
	public RecentLayout findByUserId_First(long userId,
		OrderByComparator<RecentLayout> orderByComparator)
		throws NoSuchRecentLayoutException {
		RecentLayout recentLayout = fetchByUserId_First(userId,
				orderByComparator);

		if (recentLayout != null) {
			return recentLayout;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRecentLayoutException(msg.toString());
	}

	/**
	 * Returns the first recent layout in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching recent layout, or <code>null</code> if a matching recent layout could not be found
	 */
	@Override
	public RecentLayout fetchByUserId_First(long userId,
		OrderByComparator<RecentLayout> orderByComparator) {
		List<RecentLayout> list = findByUserId(userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last recent layout in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching recent layout
	 * @throws NoSuchRecentLayoutException if a matching recent layout could not be found
	 */
	@Override
	public RecentLayout findByUserId_Last(long userId,
		OrderByComparator<RecentLayout> orderByComparator)
		throws NoSuchRecentLayoutException {
		RecentLayout recentLayout = fetchByUserId_Last(userId, orderByComparator);

		if (recentLayout != null) {
			return recentLayout;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRecentLayoutException(msg.toString());
	}

	/**
	 * Returns the last recent layout in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching recent layout, or <code>null</code> if a matching recent layout could not be found
	 */
	@Override
	public RecentLayout fetchByUserId_Last(long userId,
		OrderByComparator<RecentLayout> orderByComparator) {
		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<RecentLayout> list = findByUserId(userId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the recent layouts before and after the current recent layout in the ordered set where userId = &#63;.
	 *
	 * @param recentLayoutId the primary key of the current recent layout
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next recent layout
	 * @throws NoSuchRecentLayoutException if a recent layout with the primary key could not be found
	 */
	@Override
	public RecentLayout[] findByUserId_PrevAndNext(long recentLayoutId,
		long userId, OrderByComparator<RecentLayout> orderByComparator)
		throws NoSuchRecentLayoutException {
		RecentLayout recentLayout = findByPrimaryKey(recentLayoutId);

		Session session = null;

		try {
			session = openSession();

			RecentLayout[] array = new RecentLayoutImpl[3];

			array[0] = getByUserId_PrevAndNext(session, recentLayout, userId,
					orderByComparator, true);

			array[1] = recentLayout;

			array[2] = getByUserId_PrevAndNext(session, recentLayout, userId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected RecentLayout getByUserId_PrevAndNext(Session session,
		RecentLayout recentLayout, long userId,
		OrderByComparator<RecentLayout> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_RECENTLAYOUT_WHERE);

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
			query.append(RecentLayoutModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(recentLayout);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<RecentLayout> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the recent layouts where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByUserId(long userId) {
		for (RecentLayout recentLayout : findByUserId(userId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(recentLayout);
		}
	}

	/**
	 * Returns the number of recent layouts where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching recent layouts
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_USERID;

		Object[] finderArgs = new Object[] { userId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_RECENTLAYOUT_WHERE);

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

	private static final String _FINDER_COLUMN_USERID_USERID_2 = "recentLayout.userId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LAYOUTBRANCHID =
		new FinderPath(RecentLayoutModelImpl.ENTITY_CACHE_ENABLED,
			RecentLayoutModelImpl.FINDER_CACHE_ENABLED, RecentLayoutImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByLayoutBranchId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LAYOUTBRANCHID =
		new FinderPath(RecentLayoutModelImpl.ENTITY_CACHE_ENABLED,
			RecentLayoutModelImpl.FINDER_CACHE_ENABLED, RecentLayoutImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByLayoutBranchId",
			new String[] { Long.class.getName() },
			RecentLayoutModelImpl.LAYOUTBRANCHID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_LAYOUTBRANCHID = new FinderPath(RecentLayoutModelImpl.ENTITY_CACHE_ENABLED,
			RecentLayoutModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByLayoutBranchId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the recent layouts where layoutBranchId = &#63;.
	 *
	 * @param layoutBranchId the layout branch ID
	 * @return the matching recent layouts
	 */
	@Override
	public List<RecentLayout> findByLayoutBranchId(long layoutBranchId) {
		return findByLayoutBranchId(layoutBranchId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the recent layouts where layoutBranchId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RecentLayoutModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param layoutBranchId the layout branch ID
	 * @param start the lower bound of the range of recent layouts
	 * @param end the upper bound of the range of recent layouts (not inclusive)
	 * @return the range of matching recent layouts
	 */
	@Override
	public List<RecentLayout> findByLayoutBranchId(long layoutBranchId,
		int start, int end) {
		return findByLayoutBranchId(layoutBranchId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the recent layouts where layoutBranchId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RecentLayoutModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param layoutBranchId the layout branch ID
	 * @param start the lower bound of the range of recent layouts
	 * @param end the upper bound of the range of recent layouts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching recent layouts
	 */
	@Override
	public List<RecentLayout> findByLayoutBranchId(long layoutBranchId,
		int start, int end, OrderByComparator<RecentLayout> orderByComparator) {
		return findByLayoutBranchId(layoutBranchId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the recent layouts where layoutBranchId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RecentLayoutModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param layoutBranchId the layout branch ID
	 * @param start the lower bound of the range of recent layouts
	 * @param end the upper bound of the range of recent layouts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching recent layouts
	 */
	@Override
	public List<RecentLayout> findByLayoutBranchId(long layoutBranchId,
		int start, int end, OrderByComparator<RecentLayout> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LAYOUTBRANCHID;
			finderArgs = new Object[] { layoutBranchId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_LAYOUTBRANCHID;
			finderArgs = new Object[] {
					layoutBranchId,
					
					start, end, orderByComparator
				};
		}

		List<RecentLayout> list = null;

		if (retrieveFromCache) {
			list = (List<RecentLayout>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (RecentLayout recentLayout : list) {
					if ((layoutBranchId != recentLayout.getLayoutBranchId())) {
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

			query.append(_SQL_SELECT_RECENTLAYOUT_WHERE);

			query.append(_FINDER_COLUMN_LAYOUTBRANCHID_LAYOUTBRANCHID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(RecentLayoutModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(layoutBranchId);

				if (!pagination) {
					list = (List<RecentLayout>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<RecentLayout>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first recent layout in the ordered set where layoutBranchId = &#63;.
	 *
	 * @param layoutBranchId the layout branch ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching recent layout
	 * @throws NoSuchRecentLayoutException if a matching recent layout could not be found
	 */
	@Override
	public RecentLayout findByLayoutBranchId_First(long layoutBranchId,
		OrderByComparator<RecentLayout> orderByComparator)
		throws NoSuchRecentLayoutException {
		RecentLayout recentLayout = fetchByLayoutBranchId_First(layoutBranchId,
				orderByComparator);

		if (recentLayout != null) {
			return recentLayout;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("layoutBranchId=");
		msg.append(layoutBranchId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRecentLayoutException(msg.toString());
	}

	/**
	 * Returns the first recent layout in the ordered set where layoutBranchId = &#63;.
	 *
	 * @param layoutBranchId the layout branch ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching recent layout, or <code>null</code> if a matching recent layout could not be found
	 */
	@Override
	public RecentLayout fetchByLayoutBranchId_First(long layoutBranchId,
		OrderByComparator<RecentLayout> orderByComparator) {
		List<RecentLayout> list = findByLayoutBranchId(layoutBranchId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last recent layout in the ordered set where layoutBranchId = &#63;.
	 *
	 * @param layoutBranchId the layout branch ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching recent layout
	 * @throws NoSuchRecentLayoutException if a matching recent layout could not be found
	 */
	@Override
	public RecentLayout findByLayoutBranchId_Last(long layoutBranchId,
		OrderByComparator<RecentLayout> orderByComparator)
		throws NoSuchRecentLayoutException {
		RecentLayout recentLayout = fetchByLayoutBranchId_Last(layoutBranchId,
				orderByComparator);

		if (recentLayout != null) {
			return recentLayout;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("layoutBranchId=");
		msg.append(layoutBranchId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchRecentLayoutException(msg.toString());
	}

	/**
	 * Returns the last recent layout in the ordered set where layoutBranchId = &#63;.
	 *
	 * @param layoutBranchId the layout branch ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching recent layout, or <code>null</code> if a matching recent layout could not be found
	 */
	@Override
	public RecentLayout fetchByLayoutBranchId_Last(long layoutBranchId,
		OrderByComparator<RecentLayout> orderByComparator) {
		int count = countByLayoutBranchId(layoutBranchId);

		if (count == 0) {
			return null;
		}

		List<RecentLayout> list = findByLayoutBranchId(layoutBranchId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the recent layouts before and after the current recent layout in the ordered set where layoutBranchId = &#63;.
	 *
	 * @param recentLayoutId the primary key of the current recent layout
	 * @param layoutBranchId the layout branch ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next recent layout
	 * @throws NoSuchRecentLayoutException if a recent layout with the primary key could not be found
	 */
	@Override
	public RecentLayout[] findByLayoutBranchId_PrevAndNext(
		long recentLayoutId, long layoutBranchId,
		OrderByComparator<RecentLayout> orderByComparator)
		throws NoSuchRecentLayoutException {
		RecentLayout recentLayout = findByPrimaryKey(recentLayoutId);

		Session session = null;

		try {
			session = openSession();

			RecentLayout[] array = new RecentLayoutImpl[3];

			array[0] = getByLayoutBranchId_PrevAndNext(session, recentLayout,
					layoutBranchId, orderByComparator, true);

			array[1] = recentLayout;

			array[2] = getByLayoutBranchId_PrevAndNext(session, recentLayout,
					layoutBranchId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected RecentLayout getByLayoutBranchId_PrevAndNext(Session session,
		RecentLayout recentLayout, long layoutBranchId,
		OrderByComparator<RecentLayout> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_RECENTLAYOUT_WHERE);

		query.append(_FINDER_COLUMN_LAYOUTBRANCHID_LAYOUTBRANCHID_2);

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
			query.append(RecentLayoutModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(layoutBranchId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(recentLayout);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<RecentLayout> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the recent layouts where layoutBranchId = &#63; from the database.
	 *
	 * @param layoutBranchId the layout branch ID
	 */
	@Override
	public void removeByLayoutBranchId(long layoutBranchId) {
		for (RecentLayout recentLayout : findByLayoutBranchId(layoutBranchId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(recentLayout);
		}
	}

	/**
	 * Returns the number of recent layouts where layoutBranchId = &#63;.
	 *
	 * @param layoutBranchId the layout branch ID
	 * @return the number of matching recent layouts
	 */
	@Override
	public int countByLayoutBranchId(long layoutBranchId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_LAYOUTBRANCHID;

		Object[] finderArgs = new Object[] { layoutBranchId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_RECENTLAYOUT_WHERE);

			query.append(_FINDER_COLUMN_LAYOUTBRANCHID_LAYOUTBRANCHID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(layoutBranchId);

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

	private static final String _FINDER_COLUMN_LAYOUTBRANCHID_LAYOUTBRANCHID_2 = "recentLayout.layoutBranchId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_U_L_P = new FinderPath(RecentLayoutModelImpl.ENTITY_CACHE_ENABLED,
			RecentLayoutModelImpl.FINDER_CACHE_ENABLED, RecentLayoutImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByU_L_P",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			RecentLayoutModelImpl.USERID_COLUMN_BITMASK |
			RecentLayoutModelImpl.LAYOUTSETBRANCHID_COLUMN_BITMASK |
			RecentLayoutModelImpl.PLID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_L_P = new FinderPath(RecentLayoutModelImpl.ENTITY_CACHE_ENABLED,
			RecentLayoutModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_L_P",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});

	/**
	 * Returns the recent layout where userId = &#63; and layoutSetBranchId = &#63; and plid = &#63; or throws a {@link NoSuchRecentLayoutException} if it could not be found.
	 *
	 * @param userId the user ID
	 * @param layoutSetBranchId the layout set branch ID
	 * @param plid the plid
	 * @return the matching recent layout
	 * @throws NoSuchRecentLayoutException if a matching recent layout could not be found
	 */
	@Override
	public RecentLayout findByU_L_P(long userId, long layoutSetBranchId,
		long plid) throws NoSuchRecentLayoutException {
		RecentLayout recentLayout = fetchByU_L_P(userId, layoutSetBranchId, plid);

		if (recentLayout == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(", layoutSetBranchId=");
			msg.append(layoutSetBranchId);

			msg.append(", plid=");
			msg.append(plid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchRecentLayoutException(msg.toString());
		}

		return recentLayout;
	}

	/**
	 * Returns the recent layout where userId = &#63; and layoutSetBranchId = &#63; and plid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @param layoutSetBranchId the layout set branch ID
	 * @param plid the plid
	 * @return the matching recent layout, or <code>null</code> if a matching recent layout could not be found
	 */
	@Override
	public RecentLayout fetchByU_L_P(long userId, long layoutSetBranchId,
		long plid) {
		return fetchByU_L_P(userId, layoutSetBranchId, plid, true);
	}

	/**
	 * Returns the recent layout where userId = &#63; and layoutSetBranchId = &#63; and plid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param layoutSetBranchId the layout set branch ID
	 * @param plid the plid
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching recent layout, or <code>null</code> if a matching recent layout could not be found
	 */
	@Override
	public RecentLayout fetchByU_L_P(long userId, long layoutSetBranchId,
		long plid, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { userId, layoutSetBranchId, plid };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_U_L_P,
					finderArgs, this);
		}

		if (result instanceof RecentLayout) {
			RecentLayout recentLayout = (RecentLayout)result;

			if ((userId != recentLayout.getUserId()) ||
					(layoutSetBranchId != recentLayout.getLayoutSetBranchId()) ||
					(plid != recentLayout.getPlid())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_RECENTLAYOUT_WHERE);

			query.append(_FINDER_COLUMN_U_L_P_USERID_2);

			query.append(_FINDER_COLUMN_U_L_P_LAYOUTSETBRANCHID_2);

			query.append(_FINDER_COLUMN_U_L_P_PLID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(layoutSetBranchId);

				qPos.add(plid);

				List<RecentLayout> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_U_L_P,
						finderArgs, list);
				}
				else {
					RecentLayout recentLayout = list.get(0);

					result = recentLayout;

					cacheResult(recentLayout);

					if ((recentLayout.getUserId() != userId) ||
							(recentLayout.getLayoutSetBranchId() != layoutSetBranchId) ||
							(recentLayout.getPlid() != plid)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_U_L_P,
							finderArgs, recentLayout);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_U_L_P, finderArgs);

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
			return (RecentLayout)result;
		}
	}

	/**
	 * Removes the recent layout where userId = &#63; and layoutSetBranchId = &#63; and plid = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param layoutSetBranchId the layout set branch ID
	 * @param plid the plid
	 * @return the recent layout that was removed
	 */
	@Override
	public RecentLayout removeByU_L_P(long userId, long layoutSetBranchId,
		long plid) throws NoSuchRecentLayoutException {
		RecentLayout recentLayout = findByU_L_P(userId, layoutSetBranchId, plid);

		return remove(recentLayout);
	}

	/**
	 * Returns the number of recent layouts where userId = &#63; and layoutSetBranchId = &#63; and plid = &#63;.
	 *
	 * @param userId the user ID
	 * @param layoutSetBranchId the layout set branch ID
	 * @param plid the plid
	 * @return the number of matching recent layouts
	 */
	@Override
	public int countByU_L_P(long userId, long layoutSetBranchId, long plid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_U_L_P;

		Object[] finderArgs = new Object[] { userId, layoutSetBranchId, plid };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_RECENTLAYOUT_WHERE);

			query.append(_FINDER_COLUMN_U_L_P_USERID_2);

			query.append(_FINDER_COLUMN_U_L_P_LAYOUTSETBRANCHID_2);

			query.append(_FINDER_COLUMN_U_L_P_PLID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(layoutSetBranchId);

				qPos.add(plid);

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

	private static final String _FINDER_COLUMN_U_L_P_USERID_2 = "recentLayout.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_L_P_LAYOUTSETBRANCHID_2 = "recentLayout.layoutSetBranchId = ? AND ";
	private static final String _FINDER_COLUMN_U_L_P_PLID_2 = "recentLayout.plid = ?";

	public RecentLayoutPersistenceImpl() {
		setModelClass(RecentLayout.class);
	}

	/**
	 * Caches the recent layout in the entity cache if it is enabled.
	 *
	 * @param recentLayout the recent layout
	 */
	@Override
	public void cacheResult(RecentLayout recentLayout) {
		entityCache.putResult(RecentLayoutModelImpl.ENTITY_CACHE_ENABLED,
			RecentLayoutImpl.class, recentLayout.getPrimaryKey(), recentLayout);

		finderCache.putResult(FINDER_PATH_FETCH_BY_U_L_P,
			new Object[] {
				recentLayout.getUserId(), recentLayout.getLayoutSetBranchId(),
				recentLayout.getPlid()
			}, recentLayout);

		recentLayout.resetOriginalValues();
	}

	/**
	 * Caches the recent layouts in the entity cache if it is enabled.
	 *
	 * @param recentLayouts the recent layouts
	 */
	@Override
	public void cacheResult(List<RecentLayout> recentLayouts) {
		for (RecentLayout recentLayout : recentLayouts) {
			if (entityCache.getResult(
						RecentLayoutModelImpl.ENTITY_CACHE_ENABLED,
						RecentLayoutImpl.class, recentLayout.getPrimaryKey()) == null) {
				cacheResult(recentLayout);
			}
			else {
				recentLayout.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all recent layouts.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(RecentLayoutImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the recent layout.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(RecentLayout recentLayout) {
		entityCache.removeResult(RecentLayoutModelImpl.ENTITY_CACHE_ENABLED,
			RecentLayoutImpl.class, recentLayout.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((RecentLayoutModelImpl)recentLayout);
	}

	@Override
	public void clearCache(List<RecentLayout> recentLayouts) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (RecentLayout recentLayout : recentLayouts) {
			entityCache.removeResult(RecentLayoutModelImpl.ENTITY_CACHE_ENABLED,
				RecentLayoutImpl.class, recentLayout.getPrimaryKey());

			clearUniqueFindersCache((RecentLayoutModelImpl)recentLayout);
		}
	}

	protected void cacheUniqueFindersCache(
		RecentLayoutModelImpl recentLayoutModelImpl, boolean isNew) {
		if (isNew) {
			Object[] args = new Object[] {
					recentLayoutModelImpl.getUserId(),
					recentLayoutModelImpl.getLayoutSetBranchId(),
					recentLayoutModelImpl.getPlid()
				};

			finderCache.putResult(FINDER_PATH_COUNT_BY_U_L_P, args,
				Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_U_L_P, args,
				recentLayoutModelImpl);
		}
		else {
			if ((recentLayoutModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_U_L_P.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						recentLayoutModelImpl.getUserId(),
						recentLayoutModelImpl.getLayoutSetBranchId(),
						recentLayoutModelImpl.getPlid()
					};

				finderCache.putResult(FINDER_PATH_COUNT_BY_U_L_P, args,
					Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_U_L_P, args,
					recentLayoutModelImpl);
			}
		}
	}

	protected void clearUniqueFindersCache(
		RecentLayoutModelImpl recentLayoutModelImpl) {
		Object[] args = new Object[] {
				recentLayoutModelImpl.getUserId(),
				recentLayoutModelImpl.getLayoutSetBranchId(),
				recentLayoutModelImpl.getPlid()
			};

		finderCache.removeResult(FINDER_PATH_COUNT_BY_U_L_P, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_U_L_P, args);

		if ((recentLayoutModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_U_L_P.getColumnBitmask()) != 0) {
			args = new Object[] {
					recentLayoutModelImpl.getOriginalUserId(),
					recentLayoutModelImpl.getOriginalLayoutSetBranchId(),
					recentLayoutModelImpl.getOriginalPlid()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_U_L_P, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_U_L_P, args);
		}
	}

	/**
	 * Creates a new recent layout with the primary key. Does not add the recent layout to the database.
	 *
	 * @param recentLayoutId the primary key for the new recent layout
	 * @return the new recent layout
	 */
	@Override
	public RecentLayout create(long recentLayoutId) {
		RecentLayout recentLayout = new RecentLayoutImpl();

		recentLayout.setNew(true);
		recentLayout.setPrimaryKey(recentLayoutId);

		return recentLayout;
	}

	/**
	 * Removes the recent layout with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param recentLayoutId the primary key of the recent layout
	 * @return the recent layout that was removed
	 * @throws NoSuchRecentLayoutException if a recent layout with the primary key could not be found
	 */
	@Override
	public RecentLayout remove(long recentLayoutId)
		throws NoSuchRecentLayoutException {
		return remove((Serializable)recentLayoutId);
	}

	/**
	 * Removes the recent layout with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the recent layout
	 * @return the recent layout that was removed
	 * @throws NoSuchRecentLayoutException if a recent layout with the primary key could not be found
	 */
	@Override
	public RecentLayout remove(Serializable primaryKey)
		throws NoSuchRecentLayoutException {
		Session session = null;

		try {
			session = openSession();

			RecentLayout recentLayout = (RecentLayout)session.get(RecentLayoutImpl.class,
					primaryKey);

			if (recentLayout == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchRecentLayoutException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(recentLayout);
		}
		catch (NoSuchRecentLayoutException nsee) {
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
	protected RecentLayout removeImpl(RecentLayout recentLayout) {
		recentLayout = toUnwrappedModel(recentLayout);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(recentLayout)) {
				recentLayout = (RecentLayout)session.get(RecentLayoutImpl.class,
						recentLayout.getPrimaryKeyObj());
			}

			if (recentLayout != null) {
				session.delete(recentLayout);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (recentLayout != null) {
			clearCache(recentLayout);
		}

		return recentLayout;
	}

	@Override
	public RecentLayout updateImpl(RecentLayout recentLayout) {
		recentLayout = toUnwrappedModel(recentLayout);

		boolean isNew = recentLayout.isNew();

		RecentLayoutModelImpl recentLayoutModelImpl = (RecentLayoutModelImpl)recentLayout;

		Session session = null;

		try {
			session = openSession();

			if (recentLayout.isNew()) {
				session.save(recentLayout);

				recentLayout.setNew(false);
			}
			else {
				recentLayout = (RecentLayout)session.merge(recentLayout);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !RecentLayoutModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((recentLayoutModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						recentLayoutModelImpl.getOriginalGroupId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);

				args = new Object[] { recentLayoutModelImpl.getGroupId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
					args);
			}

			if ((recentLayoutModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						recentLayoutModelImpl.getOriginalUserId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] { recentLayoutModelImpl.getUserId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}

			if ((recentLayoutModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LAYOUTBRANCHID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						recentLayoutModelImpl.getOriginalLayoutBranchId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LAYOUTBRANCHID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LAYOUTBRANCHID,
					args);

				args = new Object[] { recentLayoutModelImpl.getLayoutBranchId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_LAYOUTBRANCHID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LAYOUTBRANCHID,
					args);
			}
		}

		entityCache.putResult(RecentLayoutModelImpl.ENTITY_CACHE_ENABLED,
			RecentLayoutImpl.class, recentLayout.getPrimaryKey(), recentLayout,
			false);

		clearUniqueFindersCache(recentLayoutModelImpl);
		cacheUniqueFindersCache(recentLayoutModelImpl, isNew);

		recentLayout.resetOriginalValues();

		return recentLayout;
	}

	protected RecentLayout toUnwrappedModel(RecentLayout recentLayout) {
		if (recentLayout instanceof RecentLayoutImpl) {
			return recentLayout;
		}

		RecentLayoutImpl recentLayoutImpl = new RecentLayoutImpl();

		recentLayoutImpl.setNew(recentLayout.isNew());
		recentLayoutImpl.setPrimaryKey(recentLayout.getPrimaryKey());

		recentLayoutImpl.setMvccVersion(recentLayout.getMvccVersion());
		recentLayoutImpl.setRecentLayoutId(recentLayout.getRecentLayoutId());
		recentLayoutImpl.setGroupId(recentLayout.getGroupId());
		recentLayoutImpl.setCompanyId(recentLayout.getCompanyId());
		recentLayoutImpl.setUserId(recentLayout.getUserId());
		recentLayoutImpl.setLayoutSetBranchId(recentLayout.getLayoutSetBranchId());
		recentLayoutImpl.setPlid(recentLayout.getPlid());
		recentLayoutImpl.setLayoutBranchId(recentLayout.getLayoutBranchId());
		recentLayoutImpl.setLayoutRevisionId(recentLayout.getLayoutRevisionId());

		return recentLayoutImpl;
	}

	/**
	 * Returns the recent layout with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the recent layout
	 * @return the recent layout
	 * @throws NoSuchRecentLayoutException if a recent layout with the primary key could not be found
	 */
	@Override
	public RecentLayout findByPrimaryKey(Serializable primaryKey)
		throws NoSuchRecentLayoutException {
		RecentLayout recentLayout = fetchByPrimaryKey(primaryKey);

		if (recentLayout == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchRecentLayoutException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return recentLayout;
	}

	/**
	 * Returns the recent layout with the primary key or throws a {@link NoSuchRecentLayoutException} if it could not be found.
	 *
	 * @param recentLayoutId the primary key of the recent layout
	 * @return the recent layout
	 * @throws NoSuchRecentLayoutException if a recent layout with the primary key could not be found
	 */
	@Override
	public RecentLayout findByPrimaryKey(long recentLayoutId)
		throws NoSuchRecentLayoutException {
		return findByPrimaryKey((Serializable)recentLayoutId);
	}

	/**
	 * Returns the recent layout with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the recent layout
	 * @return the recent layout, or <code>null</code> if a recent layout with the primary key could not be found
	 */
	@Override
	public RecentLayout fetchByPrimaryKey(Serializable primaryKey) {
		RecentLayout recentLayout = (RecentLayout)entityCache.getResult(RecentLayoutModelImpl.ENTITY_CACHE_ENABLED,
				RecentLayoutImpl.class, primaryKey);

		if (recentLayout == _nullRecentLayout) {
			return null;
		}

		if (recentLayout == null) {
			Session session = null;

			try {
				session = openSession();

				recentLayout = (RecentLayout)session.get(RecentLayoutImpl.class,
						primaryKey);

				if (recentLayout != null) {
					cacheResult(recentLayout);
				}
				else {
					entityCache.putResult(RecentLayoutModelImpl.ENTITY_CACHE_ENABLED,
						RecentLayoutImpl.class, primaryKey, _nullRecentLayout);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(RecentLayoutModelImpl.ENTITY_CACHE_ENABLED,
					RecentLayoutImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return recentLayout;
	}

	/**
	 * Returns the recent layout with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param recentLayoutId the primary key of the recent layout
	 * @return the recent layout, or <code>null</code> if a recent layout with the primary key could not be found
	 */
	@Override
	public RecentLayout fetchByPrimaryKey(long recentLayoutId) {
		return fetchByPrimaryKey((Serializable)recentLayoutId);
	}

	@Override
	public Map<Serializable, RecentLayout> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, RecentLayout> map = new HashMap<Serializable, RecentLayout>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			RecentLayout recentLayout = fetchByPrimaryKey(primaryKey);

			if (recentLayout != null) {
				map.put(primaryKey, recentLayout);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			RecentLayout recentLayout = (RecentLayout)entityCache.getResult(RecentLayoutModelImpl.ENTITY_CACHE_ENABLED,
					RecentLayoutImpl.class, primaryKey);

			if (recentLayout == null) {
				if (uncachedPrimaryKeys == null) {
					uncachedPrimaryKeys = new HashSet<Serializable>();
				}

				uncachedPrimaryKeys.add(primaryKey);
			}
			else {
				map.put(primaryKey, recentLayout);
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_RECENTLAYOUT_WHERE_PKS_IN);

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

			for (RecentLayout recentLayout : (List<RecentLayout>)q.list()) {
				map.put(recentLayout.getPrimaryKeyObj(), recentLayout);

				cacheResult(recentLayout);

				uncachedPrimaryKeys.remove(recentLayout.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(RecentLayoutModelImpl.ENTITY_CACHE_ENABLED,
					RecentLayoutImpl.class, primaryKey, _nullRecentLayout);
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
	 * Returns all the recent layouts.
	 *
	 * @return the recent layouts
	 */
	@Override
	public List<RecentLayout> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the recent layouts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RecentLayoutModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of recent layouts
	 * @param end the upper bound of the range of recent layouts (not inclusive)
	 * @return the range of recent layouts
	 */
	@Override
	public List<RecentLayout> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the recent layouts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RecentLayoutModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of recent layouts
	 * @param end the upper bound of the range of recent layouts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of recent layouts
	 */
	@Override
	public List<RecentLayout> findAll(int start, int end,
		OrderByComparator<RecentLayout> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the recent layouts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RecentLayoutModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of recent layouts
	 * @param end the upper bound of the range of recent layouts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of recent layouts
	 */
	@Override
	public List<RecentLayout> findAll(int start, int end,
		OrderByComparator<RecentLayout> orderByComparator,
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

		List<RecentLayout> list = null;

		if (retrieveFromCache) {
			list = (List<RecentLayout>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_RECENTLAYOUT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_RECENTLAYOUT;

				if (pagination) {
					sql = sql.concat(RecentLayoutModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<RecentLayout>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<RecentLayout>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the recent layouts from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (RecentLayout recentLayout : findAll()) {
			remove(recentLayout);
		}
	}

	/**
	 * Returns the number of recent layouts.
	 *
	 * @return the number of recent layouts
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_RECENTLAYOUT);

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
		return RecentLayoutModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the recent layout persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(RecentLayoutImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_RECENTLAYOUT = "SELECT recentLayout FROM RecentLayout recentLayout";
	private static final String _SQL_SELECT_RECENTLAYOUT_WHERE_PKS_IN = "SELECT recentLayout FROM RecentLayout recentLayout WHERE recentLayoutId IN (";
	private static final String _SQL_SELECT_RECENTLAYOUT_WHERE = "SELECT recentLayout FROM RecentLayout recentLayout WHERE ";
	private static final String _SQL_COUNT_RECENTLAYOUT = "SELECT COUNT(recentLayout) FROM RecentLayout recentLayout";
	private static final String _SQL_COUNT_RECENTLAYOUT_WHERE = "SELECT COUNT(recentLayout) FROM RecentLayout recentLayout WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "recentLayout.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No RecentLayout exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No RecentLayout exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(RecentLayoutPersistenceImpl.class);
	private static final RecentLayout _nullRecentLayout = new RecentLayoutImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<RecentLayout> toCacheModel() {
				return _nullRecentLayoutCacheModel;
			}
		};

	private static final CacheModel<RecentLayout> _nullRecentLayoutCacheModel = new NullCacheModel();

	private static class NullCacheModel implements CacheModel<RecentLayout>,
		MVCCModel {
		@Override
		public long getMvccVersion() {
			return -1;
		}

		@Override
		public void setMvccVersion(long mvccVersion) {
		}

		@Override
		public RecentLayout toEntityModel() {
			return _nullRecentLayout;
		}
	}
}