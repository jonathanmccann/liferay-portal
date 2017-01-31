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

import com.liferay.gsportal.core.exception.NoSuchMemberOfException;
import com.liferay.gsportal.core.model.MemberOf;
import com.liferay.gsportal.core.model.impl.MemberOfImpl;
import com.liferay.gsportal.core.model.impl.MemberOfModelImpl;
import com.liferay.gsportal.core.service.persistence.MemberOfPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
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
import com.liferay.portal.kernel.util.StringUtil;
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
 * The persistence implementation for the member of service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MemberOfPersistence
 * @see com.liferay.gsportal.core.service.persistence.MemberOfUtil
 * @generated
 */
@ProviderType
public class MemberOfPersistenceImpl extends BasePersistenceImpl<MemberOf>
	implements MemberOfPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link MemberOfUtil} to access the member of persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = MemberOfImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MemberOfModelImpl.ENTITY_CACHE_ENABLED,
			MemberOfModelImpl.FINDER_CACHE_ENABLED, MemberOfImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MemberOfModelImpl.ENTITY_CACHE_ENABLED,
			MemberOfModelImpl.FINDER_CACHE_ENABLED, MemberOfImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MemberOfModelImpl.ENTITY_CACHE_ENABLED,
			MemberOfModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_P_E = new FinderPath(MemberOfModelImpl.ENTITY_CACHE_ENABLED,
			MemberOfModelImpl.FINDER_CACHE_ENABLED, MemberOfImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_P_E",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_P_E = new FinderPath(MemberOfModelImpl.ENTITY_CACHE_ENABLED,
			MemberOfModelImpl.FINDER_CACHE_ENABLED, MemberOfImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByC_P_E",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			MemberOfModelImpl.CLIENTID_COLUMN_BITMASK |
			MemberOfModelImpl.PROJECTID_COLUMN_BITMASK |
			MemberOfModelImpl.ENGAGEMENTID_COLUMN_BITMASK |
			MemberOfModelImpl.CREATEDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_P_E = new FinderPath(MemberOfModelImpl.ENTITY_CACHE_ENABLED,
			MemberOfModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_P_E",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});

	/**
	 * Returns all the member ofs where clientId = &#63; and projectId = &#63; and engagementId = &#63;.
	 *
	 * @param clientId the client ID
	 * @param projectId the project ID
	 * @param engagementId the engagement ID
	 * @return the matching member ofs
	 */
	@Override
	public List<MemberOf> findByC_P_E(long clientId, long projectId,
		long engagementId) {
		return findByC_P_E(clientId, projectId, engagementId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the member ofs where clientId = &#63; and projectId = &#63; and engagementId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MemberOfModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param clientId the client ID
	 * @param projectId the project ID
	 * @param engagementId the engagement ID
	 * @param start the lower bound of the range of member ofs
	 * @param end the upper bound of the range of member ofs (not inclusive)
	 * @return the range of matching member ofs
	 */
	@Override
	public List<MemberOf> findByC_P_E(long clientId, long projectId,
		long engagementId, int start, int end) {
		return findByC_P_E(clientId, projectId, engagementId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the member ofs where clientId = &#63; and projectId = &#63; and engagementId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MemberOfModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param clientId the client ID
	 * @param projectId the project ID
	 * @param engagementId the engagement ID
	 * @param start the lower bound of the range of member ofs
	 * @param end the upper bound of the range of member ofs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching member ofs
	 */
	@Override
	public List<MemberOf> findByC_P_E(long clientId, long projectId,
		long engagementId, int start, int end,
		OrderByComparator<MemberOf> orderByComparator) {
		return findByC_P_E(clientId, projectId, engagementId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the member ofs where clientId = &#63; and projectId = &#63; and engagementId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MemberOfModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param clientId the client ID
	 * @param projectId the project ID
	 * @param engagementId the engagement ID
	 * @param start the lower bound of the range of member ofs
	 * @param end the upper bound of the range of member ofs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching member ofs
	 */
	@Override
	public List<MemberOf> findByC_P_E(long clientId, long projectId,
		long engagementId, int start, int end,
		OrderByComparator<MemberOf> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_P_E;
			finderArgs = new Object[] { clientId, projectId, engagementId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_P_E;
			finderArgs = new Object[] {
					clientId, projectId, engagementId,
					
					start, end, orderByComparator
				};
		}

		List<MemberOf> list = null;

		if (retrieveFromCache) {
			list = (List<MemberOf>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (MemberOf memberOf : list) {
					if ((clientId != memberOf.getClientId()) ||
							(projectId != memberOf.getProjectId()) ||
							(engagementId != memberOf.getEngagementId())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_MEMBEROF_WHERE);

			query.append(_FINDER_COLUMN_C_P_E_CLIENTID_2);

			query.append(_FINDER_COLUMN_C_P_E_PROJECTID_2);

			query.append(_FINDER_COLUMN_C_P_E_ENGAGEMENTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(MemberOfModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(clientId);

				qPos.add(projectId);

				qPos.add(engagementId);

				if (!pagination) {
					list = (List<MemberOf>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MemberOf>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first member of in the ordered set where clientId = &#63; and projectId = &#63; and engagementId = &#63;.
	 *
	 * @param clientId the client ID
	 * @param projectId the project ID
	 * @param engagementId the engagement ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching member of
	 * @throws NoSuchMemberOfException if a matching member of could not be found
	 */
	@Override
	public MemberOf findByC_P_E_First(long clientId, long projectId,
		long engagementId, OrderByComparator<MemberOf> orderByComparator)
		throws NoSuchMemberOfException {
		MemberOf memberOf = fetchByC_P_E_First(clientId, projectId,
				engagementId, orderByComparator);

		if (memberOf != null) {
			return memberOf;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("clientId=");
		msg.append(clientId);

		msg.append(", projectId=");
		msg.append(projectId);

		msg.append(", engagementId=");
		msg.append(engagementId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMemberOfException(msg.toString());
	}

	/**
	 * Returns the first member of in the ordered set where clientId = &#63; and projectId = &#63; and engagementId = &#63;.
	 *
	 * @param clientId the client ID
	 * @param projectId the project ID
	 * @param engagementId the engagement ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching member of, or <code>null</code> if a matching member of could not be found
	 */
	@Override
	public MemberOf fetchByC_P_E_First(long clientId, long projectId,
		long engagementId, OrderByComparator<MemberOf> orderByComparator) {
		List<MemberOf> list = findByC_P_E(clientId, projectId, engagementId, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last member of in the ordered set where clientId = &#63; and projectId = &#63; and engagementId = &#63;.
	 *
	 * @param clientId the client ID
	 * @param projectId the project ID
	 * @param engagementId the engagement ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching member of
	 * @throws NoSuchMemberOfException if a matching member of could not be found
	 */
	@Override
	public MemberOf findByC_P_E_Last(long clientId, long projectId,
		long engagementId, OrderByComparator<MemberOf> orderByComparator)
		throws NoSuchMemberOfException {
		MemberOf memberOf = fetchByC_P_E_Last(clientId, projectId,
				engagementId, orderByComparator);

		if (memberOf != null) {
			return memberOf;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("clientId=");
		msg.append(clientId);

		msg.append(", projectId=");
		msg.append(projectId);

		msg.append(", engagementId=");
		msg.append(engagementId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchMemberOfException(msg.toString());
	}

	/**
	 * Returns the last member of in the ordered set where clientId = &#63; and projectId = &#63; and engagementId = &#63;.
	 *
	 * @param clientId the client ID
	 * @param projectId the project ID
	 * @param engagementId the engagement ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching member of, or <code>null</code> if a matching member of could not be found
	 */
	@Override
	public MemberOf fetchByC_P_E_Last(long clientId, long projectId,
		long engagementId, OrderByComparator<MemberOf> orderByComparator) {
		int count = countByC_P_E(clientId, projectId, engagementId);

		if (count == 0) {
			return null;
		}

		List<MemberOf> list = findByC_P_E(clientId, projectId, engagementId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the member ofs before and after the current member of in the ordered set where clientId = &#63; and projectId = &#63; and engagementId = &#63;.
	 *
	 * @param memberOfId the primary key of the current member of
	 * @param clientId the client ID
	 * @param projectId the project ID
	 * @param engagementId the engagement ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next member of
	 * @throws NoSuchMemberOfException if a member of with the primary key could not be found
	 */
	@Override
	public MemberOf[] findByC_P_E_PrevAndNext(long memberOfId, long clientId,
		long projectId, long engagementId,
		OrderByComparator<MemberOf> orderByComparator)
		throws NoSuchMemberOfException {
		MemberOf memberOf = findByPrimaryKey(memberOfId);

		Session session = null;

		try {
			session = openSession();

			MemberOf[] array = new MemberOfImpl[3];

			array[0] = getByC_P_E_PrevAndNext(session, memberOf, clientId,
					projectId, engagementId, orderByComparator, true);

			array[1] = memberOf;

			array[2] = getByC_P_E_PrevAndNext(session, memberOf, clientId,
					projectId, engagementId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected MemberOf getByC_P_E_PrevAndNext(Session session,
		MemberOf memberOf, long clientId, long projectId, long engagementId,
		OrderByComparator<MemberOf> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(5);
		}

		query.append(_SQL_SELECT_MEMBEROF_WHERE);

		query.append(_FINDER_COLUMN_C_P_E_CLIENTID_2);

		query.append(_FINDER_COLUMN_C_P_E_PROJECTID_2);

		query.append(_FINDER_COLUMN_C_P_E_ENGAGEMENTID_2);

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
			query.append(MemberOfModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(clientId);

		qPos.add(projectId);

		qPos.add(engagementId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(memberOf);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<MemberOf> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the member ofs where clientId = &#63; and projectId = &#63; and engagementId = &#63; from the database.
	 *
	 * @param clientId the client ID
	 * @param projectId the project ID
	 * @param engagementId the engagement ID
	 */
	@Override
	public void removeByC_P_E(long clientId, long projectId, long engagementId) {
		for (MemberOf memberOf : findByC_P_E(clientId, projectId, engagementId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(memberOf);
		}
	}

	/**
	 * Returns the number of member ofs where clientId = &#63; and projectId = &#63; and engagementId = &#63;.
	 *
	 * @param clientId the client ID
	 * @param projectId the project ID
	 * @param engagementId the engagement ID
	 * @return the number of matching member ofs
	 */
	@Override
	public int countByC_P_E(long clientId, long projectId, long engagementId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_P_E;

		Object[] finderArgs = new Object[] { clientId, projectId, engagementId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_MEMBEROF_WHERE);

			query.append(_FINDER_COLUMN_C_P_E_CLIENTID_2);

			query.append(_FINDER_COLUMN_C_P_E_PROJECTID_2);

			query.append(_FINDER_COLUMN_C_P_E_ENGAGEMENTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(clientId);

				qPos.add(projectId);

				qPos.add(engagementId);

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

	private static final String _FINDER_COLUMN_C_P_E_CLIENTID_2 = "memberOf.clientId = ? AND ";
	private static final String _FINDER_COLUMN_C_P_E_PROJECTID_2 = "memberOf.projectId = ? AND ";
	private static final String _FINDER_COLUMN_C_P_E_ENGAGEMENTID_2 = "memberOf.engagementId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_U_C_P_E = new FinderPath(MemberOfModelImpl.ENTITY_CACHE_ENABLED,
			MemberOfModelImpl.FINDER_CACHE_ENABLED, MemberOfImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByU_C_P_E",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Long.class.getName()
			},
			MemberOfModelImpl.USERID_COLUMN_BITMASK |
			MemberOfModelImpl.CLIENTID_COLUMN_BITMASK |
			MemberOfModelImpl.PROJECTID_COLUMN_BITMASK |
			MemberOfModelImpl.ENGAGEMENTID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_C_P_E = new FinderPath(MemberOfModelImpl.ENTITY_CACHE_ENABLED,
			MemberOfModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_C_P_E",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				Long.class.getName()
			});

	/**
	 * Returns the member of where userId = &#63; and clientId = &#63; and projectId = &#63; and engagementId = &#63; or throws a {@link NoSuchMemberOfException} if it could not be found.
	 *
	 * @param userId the user ID
	 * @param clientId the client ID
	 * @param projectId the project ID
	 * @param engagementId the engagement ID
	 * @return the matching member of
	 * @throws NoSuchMemberOfException if a matching member of could not be found
	 */
	@Override
	public MemberOf findByU_C_P_E(long userId, long clientId, long projectId,
		long engagementId) throws NoSuchMemberOfException {
		MemberOf memberOf = fetchByU_C_P_E(userId, clientId, projectId,
				engagementId);

		if (memberOf == null) {
			StringBundler msg = new StringBundler(10);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(", clientId=");
			msg.append(clientId);

			msg.append(", projectId=");
			msg.append(projectId);

			msg.append(", engagementId=");
			msg.append(engagementId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchMemberOfException(msg.toString());
		}

		return memberOf;
	}

	/**
	 * Returns the member of where userId = &#63; and clientId = &#63; and projectId = &#63; and engagementId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @param clientId the client ID
	 * @param projectId the project ID
	 * @param engagementId the engagement ID
	 * @return the matching member of, or <code>null</code> if a matching member of could not be found
	 */
	@Override
	public MemberOf fetchByU_C_P_E(long userId, long clientId, long projectId,
		long engagementId) {
		return fetchByU_C_P_E(userId, clientId, projectId, engagementId, true);
	}

	/**
	 * Returns the member of where userId = &#63; and clientId = &#63; and projectId = &#63; and engagementId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param clientId the client ID
	 * @param projectId the project ID
	 * @param engagementId the engagement ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching member of, or <code>null</code> if a matching member of could not be found
	 */
	@Override
	public MemberOf fetchByU_C_P_E(long userId, long clientId, long projectId,
		long engagementId, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] {
				userId, clientId, projectId, engagementId
			};

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_U_C_P_E,
					finderArgs, this);
		}

		if (result instanceof MemberOf) {
			MemberOf memberOf = (MemberOf)result;

			if ((userId != memberOf.getUserId()) ||
					(clientId != memberOf.getClientId()) ||
					(projectId != memberOf.getProjectId()) ||
					(engagementId != memberOf.getEngagementId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_SELECT_MEMBEROF_WHERE);

			query.append(_FINDER_COLUMN_U_C_P_E_USERID_2);

			query.append(_FINDER_COLUMN_U_C_P_E_CLIENTID_2);

			query.append(_FINDER_COLUMN_U_C_P_E_PROJECTID_2);

			query.append(_FINDER_COLUMN_U_C_P_E_ENGAGEMENTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(clientId);

				qPos.add(projectId);

				qPos.add(engagementId);

				List<MemberOf> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_U_C_P_E,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"MemberOfPersistenceImpl.fetchByU_C_P_E(long, long, long, long, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					MemberOf memberOf = list.get(0);

					result = memberOf;

					cacheResult(memberOf);

					if ((memberOf.getUserId() != userId) ||
							(memberOf.getClientId() != clientId) ||
							(memberOf.getProjectId() != projectId) ||
							(memberOf.getEngagementId() != engagementId)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_U_C_P_E,
							finderArgs, memberOf);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_U_C_P_E,
					finderArgs);

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
			return (MemberOf)result;
		}
	}

	/**
	 * Removes the member of where userId = &#63; and clientId = &#63; and projectId = &#63; and engagementId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param clientId the client ID
	 * @param projectId the project ID
	 * @param engagementId the engagement ID
	 * @return the member of that was removed
	 */
	@Override
	public MemberOf removeByU_C_P_E(long userId, long clientId, long projectId,
		long engagementId) throws NoSuchMemberOfException {
		MemberOf memberOf = findByU_C_P_E(userId, clientId, projectId,
				engagementId);

		return remove(memberOf);
	}

	/**
	 * Returns the number of member ofs where userId = &#63; and clientId = &#63; and projectId = &#63; and engagementId = &#63;.
	 *
	 * @param userId the user ID
	 * @param clientId the client ID
	 * @param projectId the project ID
	 * @param engagementId the engagement ID
	 * @return the number of matching member ofs
	 */
	@Override
	public int countByU_C_P_E(long userId, long clientId, long projectId,
		long engagementId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_U_C_P_E;

		Object[] finderArgs = new Object[] {
				userId, clientId, projectId, engagementId
			};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_MEMBEROF_WHERE);

			query.append(_FINDER_COLUMN_U_C_P_E_USERID_2);

			query.append(_FINDER_COLUMN_U_C_P_E_CLIENTID_2);

			query.append(_FINDER_COLUMN_U_C_P_E_PROJECTID_2);

			query.append(_FINDER_COLUMN_U_C_P_E_ENGAGEMENTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				qPos.add(clientId);

				qPos.add(projectId);

				qPos.add(engagementId);

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

	private static final String _FINDER_COLUMN_U_C_P_E_USERID_2 = "memberOf.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_C_P_E_CLIENTID_2 = "memberOf.clientId = ? AND ";
	private static final String _FINDER_COLUMN_U_C_P_E_PROJECTID_2 = "memberOf.projectId = ? AND ";
	private static final String _FINDER_COLUMN_U_C_P_E_ENGAGEMENTID_2 = "memberOf.engagementId = ?";

	public MemberOfPersistenceImpl() {
		setModelClass(MemberOf.class);
	}

	/**
	 * Caches the member of in the entity cache if it is enabled.
	 *
	 * @param memberOf the member of
	 */
	@Override
	public void cacheResult(MemberOf memberOf) {
		entityCache.putResult(MemberOfModelImpl.ENTITY_CACHE_ENABLED,
			MemberOfImpl.class, memberOf.getPrimaryKey(), memberOf);

		finderCache.putResult(FINDER_PATH_FETCH_BY_U_C_P_E,
			new Object[] {
				memberOf.getUserId(), memberOf.getClientId(),
				memberOf.getProjectId(), memberOf.getEngagementId()
			}, memberOf);

		memberOf.resetOriginalValues();
	}

	/**
	 * Caches the member ofs in the entity cache if it is enabled.
	 *
	 * @param memberOfs the member ofs
	 */
	@Override
	public void cacheResult(List<MemberOf> memberOfs) {
		for (MemberOf memberOf : memberOfs) {
			if (entityCache.getResult(MemberOfModelImpl.ENTITY_CACHE_ENABLED,
						MemberOfImpl.class, memberOf.getPrimaryKey()) == null) {
				cacheResult(memberOf);
			}
			else {
				memberOf.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all member ofs.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(MemberOfImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the member of.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(MemberOf memberOf) {
		entityCache.removeResult(MemberOfModelImpl.ENTITY_CACHE_ENABLED,
			MemberOfImpl.class, memberOf.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((MemberOfModelImpl)memberOf);
	}

	@Override
	public void clearCache(List<MemberOf> memberOfs) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (MemberOf memberOf : memberOfs) {
			entityCache.removeResult(MemberOfModelImpl.ENTITY_CACHE_ENABLED,
				MemberOfImpl.class, memberOf.getPrimaryKey());

			clearUniqueFindersCache((MemberOfModelImpl)memberOf);
		}
	}

	protected void cacheUniqueFindersCache(
		MemberOfModelImpl memberOfModelImpl, boolean isNew) {
		if (isNew) {
			Object[] args = new Object[] {
					memberOfModelImpl.getUserId(),
					memberOfModelImpl.getClientId(),
					memberOfModelImpl.getProjectId(),
					memberOfModelImpl.getEngagementId()
				};

			finderCache.putResult(FINDER_PATH_COUNT_BY_U_C_P_E, args,
				Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_U_C_P_E, args,
				memberOfModelImpl);
		}
		else {
			if ((memberOfModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_U_C_P_E.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						memberOfModelImpl.getUserId(),
						memberOfModelImpl.getClientId(),
						memberOfModelImpl.getProjectId(),
						memberOfModelImpl.getEngagementId()
					};

				finderCache.putResult(FINDER_PATH_COUNT_BY_U_C_P_E, args,
					Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_U_C_P_E, args,
					memberOfModelImpl);
			}
		}
	}

	protected void clearUniqueFindersCache(MemberOfModelImpl memberOfModelImpl) {
		Object[] args = new Object[] {
				memberOfModelImpl.getUserId(), memberOfModelImpl.getClientId(),
				memberOfModelImpl.getProjectId(),
				memberOfModelImpl.getEngagementId()
			};

		finderCache.removeResult(FINDER_PATH_COUNT_BY_U_C_P_E, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_U_C_P_E, args);

		if ((memberOfModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_U_C_P_E.getColumnBitmask()) != 0) {
			args = new Object[] {
					memberOfModelImpl.getOriginalUserId(),
					memberOfModelImpl.getOriginalClientId(),
					memberOfModelImpl.getOriginalProjectId(),
					memberOfModelImpl.getOriginalEngagementId()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_U_C_P_E, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_U_C_P_E, args);
		}
	}

	/**
	 * Creates a new member of with the primary key. Does not add the member of to the database.
	 *
	 * @param memberOfId the primary key for the new member of
	 * @return the new member of
	 */
	@Override
	public MemberOf create(long memberOfId) {
		MemberOf memberOf = new MemberOfImpl();

		memberOf.setNew(true);
		memberOf.setPrimaryKey(memberOfId);

		memberOf.setCompanyId(companyProvider.getCompanyId());

		return memberOf;
	}

	/**
	 * Removes the member of with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param memberOfId the primary key of the member of
	 * @return the member of that was removed
	 * @throws NoSuchMemberOfException if a member of with the primary key could not be found
	 */
	@Override
	public MemberOf remove(long memberOfId) throws NoSuchMemberOfException {
		return remove((Serializable)memberOfId);
	}

	/**
	 * Removes the member of with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the member of
	 * @return the member of that was removed
	 * @throws NoSuchMemberOfException if a member of with the primary key could not be found
	 */
	@Override
	public MemberOf remove(Serializable primaryKey)
		throws NoSuchMemberOfException {
		Session session = null;

		try {
			session = openSession();

			MemberOf memberOf = (MemberOf)session.get(MemberOfImpl.class,
					primaryKey);

			if (memberOf == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchMemberOfException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(memberOf);
		}
		catch (NoSuchMemberOfException nsee) {
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
	protected MemberOf removeImpl(MemberOf memberOf) {
		memberOf = toUnwrappedModel(memberOf);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(memberOf)) {
				memberOf = (MemberOf)session.get(MemberOfImpl.class,
						memberOf.getPrimaryKeyObj());
			}

			if (memberOf != null) {
				session.delete(memberOf);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (memberOf != null) {
			clearCache(memberOf);
		}

		return memberOf;
	}

	@Override
	public MemberOf updateImpl(MemberOf memberOf) {
		memberOf = toUnwrappedModel(memberOf);

		boolean isNew = memberOf.isNew();

		MemberOfModelImpl memberOfModelImpl = (MemberOfModelImpl)memberOf;

		Session session = null;

		try {
			session = openSession();

			if (memberOf.isNew()) {
				session.save(memberOf);

				memberOf.setNew(false);
			}
			else {
				memberOf = (MemberOf)session.merge(memberOf);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !MemberOfModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((memberOfModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_P_E.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						memberOfModelImpl.getOriginalClientId(),
						memberOfModelImpl.getOriginalProjectId(),
						memberOfModelImpl.getOriginalEngagementId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_P_E, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_P_E,
					args);

				args = new Object[] {
						memberOfModelImpl.getClientId(),
						memberOfModelImpl.getProjectId(),
						memberOfModelImpl.getEngagementId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_C_P_E, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_C_P_E,
					args);
			}
		}

		entityCache.putResult(MemberOfModelImpl.ENTITY_CACHE_ENABLED,
			MemberOfImpl.class, memberOf.getPrimaryKey(), memberOf, false);

		clearUniqueFindersCache(memberOfModelImpl);
		cacheUniqueFindersCache(memberOfModelImpl, isNew);

		memberOf.resetOriginalValues();

		return memberOf;
	}

	protected MemberOf toUnwrappedModel(MemberOf memberOf) {
		if (memberOf instanceof MemberOfImpl) {
			return memberOf;
		}

		MemberOfImpl memberOfImpl = new MemberOfImpl();

		memberOfImpl.setNew(memberOf.isNew());
		memberOfImpl.setPrimaryKey(memberOf.getPrimaryKey());

		memberOfImpl.setMemberOfId(memberOf.getMemberOfId());
		memberOfImpl.setCompanyId(memberOf.getCompanyId());
		memberOfImpl.setCreateDate(memberOf.getCreateDate());
		memberOfImpl.setUserId(memberOf.getUserId());
		memberOfImpl.setClientId(memberOf.getClientId());
		memberOfImpl.setProjectId(memberOf.getProjectId());
		memberOfImpl.setEngagementId(memberOf.getEngagementId());
		memberOfImpl.setMemberRoleId(memberOf.getMemberRoleId());

		return memberOfImpl;
	}

	/**
	 * Returns the member of with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the member of
	 * @return the member of
	 * @throws NoSuchMemberOfException if a member of with the primary key could not be found
	 */
	@Override
	public MemberOf findByPrimaryKey(Serializable primaryKey)
		throws NoSuchMemberOfException {
		MemberOf memberOf = fetchByPrimaryKey(primaryKey);

		if (memberOf == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchMemberOfException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return memberOf;
	}

	/**
	 * Returns the member of with the primary key or throws a {@link NoSuchMemberOfException} if it could not be found.
	 *
	 * @param memberOfId the primary key of the member of
	 * @return the member of
	 * @throws NoSuchMemberOfException if a member of with the primary key could not be found
	 */
	@Override
	public MemberOf findByPrimaryKey(long memberOfId)
		throws NoSuchMemberOfException {
		return findByPrimaryKey((Serializable)memberOfId);
	}

	/**
	 * Returns the member of with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the member of
	 * @return the member of, or <code>null</code> if a member of with the primary key could not be found
	 */
	@Override
	public MemberOf fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(MemberOfModelImpl.ENTITY_CACHE_ENABLED,
				MemberOfImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		MemberOf memberOf = (MemberOf)serializable;

		if (memberOf == null) {
			Session session = null;

			try {
				session = openSession();

				memberOf = (MemberOf)session.get(MemberOfImpl.class, primaryKey);

				if (memberOf != null) {
					cacheResult(memberOf);
				}
				else {
					entityCache.putResult(MemberOfModelImpl.ENTITY_CACHE_ENABLED,
						MemberOfImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(MemberOfModelImpl.ENTITY_CACHE_ENABLED,
					MemberOfImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return memberOf;
	}

	/**
	 * Returns the member of with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param memberOfId the primary key of the member of
	 * @return the member of, or <code>null</code> if a member of with the primary key could not be found
	 */
	@Override
	public MemberOf fetchByPrimaryKey(long memberOfId) {
		return fetchByPrimaryKey((Serializable)memberOfId);
	}

	@Override
	public Map<Serializable, MemberOf> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, MemberOf> map = new HashMap<Serializable, MemberOf>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			MemberOf memberOf = fetchByPrimaryKey(primaryKey);

			if (memberOf != null) {
				map.put(primaryKey, memberOf);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(MemberOfModelImpl.ENTITY_CACHE_ENABLED,
					MemberOfImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (MemberOf)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_MEMBEROF_WHERE_PKS_IN);

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

			for (MemberOf memberOf : (List<MemberOf>)q.list()) {
				map.put(memberOf.getPrimaryKeyObj(), memberOf);

				cacheResult(memberOf);

				uncachedPrimaryKeys.remove(memberOf.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(MemberOfModelImpl.ENTITY_CACHE_ENABLED,
					MemberOfImpl.class, primaryKey, nullModel);
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
	 * Returns all the member ofs.
	 *
	 * @return the member ofs
	 */
	@Override
	public List<MemberOf> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the member ofs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MemberOfModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of member ofs
	 * @param end the upper bound of the range of member ofs (not inclusive)
	 * @return the range of member ofs
	 */
	@Override
	public List<MemberOf> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the member ofs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MemberOfModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of member ofs
	 * @param end the upper bound of the range of member ofs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of member ofs
	 */
	@Override
	public List<MemberOf> findAll(int start, int end,
		OrderByComparator<MemberOf> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the member ofs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link MemberOfModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of member ofs
	 * @param end the upper bound of the range of member ofs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of member ofs
	 */
	@Override
	public List<MemberOf> findAll(int start, int end,
		OrderByComparator<MemberOf> orderByComparator, boolean retrieveFromCache) {
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

		List<MemberOf> list = null;

		if (retrieveFromCache) {
			list = (List<MemberOf>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_MEMBEROF);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_MEMBEROF;

				if (pagination) {
					sql = sql.concat(MemberOfModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<MemberOf>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<MemberOf>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the member ofs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (MemberOf memberOf : findAll()) {
			remove(memberOf);
		}
	}

	/**
	 * Returns the number of member ofs.
	 *
	 * @return the number of member ofs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_MEMBEROF);

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
		return MemberOfModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the member of persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(MemberOfImpl.class.getName());
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
	private static final String _SQL_SELECT_MEMBEROF = "SELECT memberOf FROM MemberOf memberOf";
	private static final String _SQL_SELECT_MEMBEROF_WHERE_PKS_IN = "SELECT memberOf FROM MemberOf memberOf WHERE memberOfId IN (";
	private static final String _SQL_SELECT_MEMBEROF_WHERE = "SELECT memberOf FROM MemberOf memberOf WHERE ";
	private static final String _SQL_COUNT_MEMBEROF = "SELECT COUNT(memberOf) FROM MemberOf memberOf";
	private static final String _SQL_COUNT_MEMBEROF_WHERE = "SELECT COUNT(memberOf) FROM MemberOf memberOf WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "memberOf.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MemberOf exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No MemberOf exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(MemberOfPersistenceImpl.class);
}