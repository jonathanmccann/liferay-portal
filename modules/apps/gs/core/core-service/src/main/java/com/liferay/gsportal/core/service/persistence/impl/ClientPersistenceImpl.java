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

import com.liferay.gsportal.core.exception.NoSuchClientException;
import com.liferay.gsportal.core.model.Client;
import com.liferay.gsportal.core.model.impl.ClientImpl;
import com.liferay.gsportal.core.model.impl.ClientModelImpl;
import com.liferay.gsportal.core.service.persistence.ClientPersistence;

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
import com.liferay.portal.kernel.util.SetUtil;
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
 * The persistence implementation for the client service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ClientPersistence
 * @see com.liferay.gsportal.core.service.persistence.ClientUtil
 * @generated
 */
@ProviderType
public class ClientPersistenceImpl extends BasePersistenceImpl<Client>
	implements ClientPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ClientUtil} to access the client persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ClientImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ClientModelImpl.ENTITY_CACHE_ENABLED,
			ClientModelImpl.FINDER_CACHE_ENABLED, ClientImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ClientModelImpl.ENTITY_CACHE_ENABLED,
			ClientModelImpl.FINDER_CACHE_ENABLED, ClientImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ClientModelImpl.ENTITY_CACHE_ENABLED,
			ClientModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PARENTCLIENTID =
		new FinderPath(ClientModelImpl.ENTITY_CACHE_ENABLED,
			ClientModelImpl.FINDER_CACHE_ENABLED, ClientImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByParentClientId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTCLIENTID =
		new FinderPath(ClientModelImpl.ENTITY_CACHE_ENABLED,
			ClientModelImpl.FINDER_CACHE_ENABLED, ClientImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByParentClientId",
			new String[] { Long.class.getName() },
			ClientModelImpl.PARENTCLIENTID_COLUMN_BITMASK |
			ClientModelImpl.MODIFIEDDATE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PARENTCLIENTID = new FinderPath(ClientModelImpl.ENTITY_CACHE_ENABLED,
			ClientModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByParentClientId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the clients where parentClientId = &#63;.
	 *
	 * @param parentClientId the parent client ID
	 * @return the matching clients
	 */
	@Override
	public List<Client> findByParentClientId(long parentClientId) {
		return findByParentClientId(parentClientId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the clients where parentClientId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ClientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param parentClientId the parent client ID
	 * @param start the lower bound of the range of clients
	 * @param end the upper bound of the range of clients (not inclusive)
	 * @return the range of matching clients
	 */
	@Override
	public List<Client> findByParentClientId(long parentClientId, int start,
		int end) {
		return findByParentClientId(parentClientId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the clients where parentClientId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ClientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param parentClientId the parent client ID
	 * @param start the lower bound of the range of clients
	 * @param end the upper bound of the range of clients (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching clients
	 */
	@Override
	public List<Client> findByParentClientId(long parentClientId, int start,
		int end, OrderByComparator<Client> orderByComparator) {
		return findByParentClientId(parentClientId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the clients where parentClientId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ClientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param parentClientId the parent client ID
	 * @param start the lower bound of the range of clients
	 * @param end the upper bound of the range of clients (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching clients
	 */
	@Override
	public List<Client> findByParentClientId(long parentClientId, int start,
		int end, OrderByComparator<Client> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTCLIENTID;
			finderArgs = new Object[] { parentClientId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PARENTCLIENTID;
			finderArgs = new Object[] {
					parentClientId,
					
					start, end, orderByComparator
				};
		}

		List<Client> list = null;

		if (retrieveFromCache) {
			list = (List<Client>)finderCache.getResult(finderPath, finderArgs,
					this);

			if ((list != null) && !list.isEmpty()) {
				for (Client client : list) {
					if ((parentClientId != client.getParentClientId())) {
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

			query.append(_SQL_SELECT_CLIENT_WHERE);

			query.append(_FINDER_COLUMN_PARENTCLIENTID_PARENTCLIENTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ClientModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentClientId);

				if (!pagination) {
					list = (List<Client>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Client>)QueryUtil.list(q, getDialect(), start,
							end);
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
	 * Returns the first client in the ordered set where parentClientId = &#63;.
	 *
	 * @param parentClientId the parent client ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching client
	 * @throws NoSuchClientException if a matching client could not be found
	 */
	@Override
	public Client findByParentClientId_First(long parentClientId,
		OrderByComparator<Client> orderByComparator)
		throws NoSuchClientException {
		Client client = fetchByParentClientId_First(parentClientId,
				orderByComparator);

		if (client != null) {
			return client;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentClientId=");
		msg.append(parentClientId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchClientException(msg.toString());
	}

	/**
	 * Returns the first client in the ordered set where parentClientId = &#63;.
	 *
	 * @param parentClientId the parent client ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching client, or <code>null</code> if a matching client could not be found
	 */
	@Override
	public Client fetchByParentClientId_First(long parentClientId,
		OrderByComparator<Client> orderByComparator) {
		List<Client> list = findByParentClientId(parentClientId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last client in the ordered set where parentClientId = &#63;.
	 *
	 * @param parentClientId the parent client ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching client
	 * @throws NoSuchClientException if a matching client could not be found
	 */
	@Override
	public Client findByParentClientId_Last(long parentClientId,
		OrderByComparator<Client> orderByComparator)
		throws NoSuchClientException {
		Client client = fetchByParentClientId_Last(parentClientId,
				orderByComparator);

		if (client != null) {
			return client;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("parentClientId=");
		msg.append(parentClientId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchClientException(msg.toString());
	}

	/**
	 * Returns the last client in the ordered set where parentClientId = &#63;.
	 *
	 * @param parentClientId the parent client ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching client, or <code>null</code> if a matching client could not be found
	 */
	@Override
	public Client fetchByParentClientId_Last(long parentClientId,
		OrderByComparator<Client> orderByComparator) {
		int count = countByParentClientId(parentClientId);

		if (count == 0) {
			return null;
		}

		List<Client> list = findByParentClientId(parentClientId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the clients before and after the current client in the ordered set where parentClientId = &#63;.
	 *
	 * @param clientId the primary key of the current client
	 * @param parentClientId the parent client ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next client
	 * @throws NoSuchClientException if a client with the primary key could not be found
	 */
	@Override
	public Client[] findByParentClientId_PrevAndNext(long clientId,
		long parentClientId, OrderByComparator<Client> orderByComparator)
		throws NoSuchClientException {
		Client client = findByPrimaryKey(clientId);

		Session session = null;

		try {
			session = openSession();

			Client[] array = new ClientImpl[3];

			array[0] = getByParentClientId_PrevAndNext(session, client,
					parentClientId, orderByComparator, true);

			array[1] = client;

			array[2] = getByParentClientId_PrevAndNext(session, client,
					parentClientId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Client getByParentClientId_PrevAndNext(Session session,
		Client client, long parentClientId,
		OrderByComparator<Client> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(4 +
					(orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CLIENT_WHERE);

		query.append(_FINDER_COLUMN_PARENTCLIENTID_PARENTCLIENTID_2);

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
			query.append(ClientModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(parentClientId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(client);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Client> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the clients where parentClientId = &#63; from the database.
	 *
	 * @param parentClientId the parent client ID
	 */
	@Override
	public void removeByParentClientId(long parentClientId) {
		for (Client client : findByParentClientId(parentClientId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(client);
		}
	}

	/**
	 * Returns the number of clients where parentClientId = &#63;.
	 *
	 * @param parentClientId the parent client ID
	 * @return the number of matching clients
	 */
	@Override
	public int countByParentClientId(long parentClientId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PARENTCLIENTID;

		Object[] finderArgs = new Object[] { parentClientId };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CLIENT_WHERE);

			query.append(_FINDER_COLUMN_PARENTCLIENTID_PARENTCLIENTID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(parentClientId);

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

	private static final String _FINDER_COLUMN_PARENTCLIENTID_PARENTCLIENTID_2 = "client.parentClientId = ?";

	public ClientPersistenceImpl() {
		setModelClass(Client.class);
	}

	/**
	 * Caches the client in the entity cache if it is enabled.
	 *
	 * @param client the client
	 */
	@Override
	public void cacheResult(Client client) {
		entityCache.putResult(ClientModelImpl.ENTITY_CACHE_ENABLED,
			ClientImpl.class, client.getPrimaryKey(), client);

		client.resetOriginalValues();
	}

	/**
	 * Caches the clients in the entity cache if it is enabled.
	 *
	 * @param clients the clients
	 */
	@Override
	public void cacheResult(List<Client> clients) {
		for (Client client : clients) {
			if (entityCache.getResult(ClientModelImpl.ENTITY_CACHE_ENABLED,
						ClientImpl.class, client.getPrimaryKey()) == null) {
				cacheResult(client);
			}
			else {
				client.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all clients.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ClientImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the client.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Client client) {
		entityCache.removeResult(ClientModelImpl.ENTITY_CACHE_ENABLED,
			ClientImpl.class, client.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Client> clients) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Client client : clients) {
			entityCache.removeResult(ClientModelImpl.ENTITY_CACHE_ENABLED,
				ClientImpl.class, client.getPrimaryKey());
		}
	}

	/**
	 * Creates a new client with the primary key. Does not add the client to the database.
	 *
	 * @param clientId the primary key for the new client
	 * @return the new client
	 */
	@Override
	public Client create(long clientId) {
		Client client = new ClientImpl();

		client.setNew(true);
		client.setPrimaryKey(clientId);

		client.setCompanyId(companyProvider.getCompanyId());

		return client;
	}

	/**
	 * Removes the client with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param clientId the primary key of the client
	 * @return the client that was removed
	 * @throws NoSuchClientException if a client with the primary key could not be found
	 */
	@Override
	public Client remove(long clientId) throws NoSuchClientException {
		return remove((Serializable)clientId);
	}

	/**
	 * Removes the client with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the client
	 * @return the client that was removed
	 * @throws NoSuchClientException if a client with the primary key could not be found
	 */
	@Override
	public Client remove(Serializable primaryKey) throws NoSuchClientException {
		Session session = null;

		try {
			session = openSession();

			Client client = (Client)session.get(ClientImpl.class, primaryKey);

			if (client == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchClientException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(client);
		}
		catch (NoSuchClientException nsee) {
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
	protected Client removeImpl(Client client) {
		client = toUnwrappedModel(client);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(client)) {
				client = (Client)session.get(ClientImpl.class,
						client.getPrimaryKeyObj());
			}

			if (client != null) {
				session.delete(client);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (client != null) {
			clearCache(client);
		}

		return client;
	}

	@Override
	public Client updateImpl(Client client) {
		client = toUnwrappedModel(client);

		boolean isNew = client.isNew();

		ClientModelImpl clientModelImpl = (ClientModelImpl)client;

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (client.getCreateDate() == null)) {
			if (serviceContext == null) {
				client.setCreateDate(now);
			}
			else {
				client.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!clientModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				client.setModifiedDate(now);
			}
			else {
				client.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (client.isNew()) {
				session.save(client);

				client.setNew(false);
			}
			else {
				client = (Client)session.merge(client);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !ClientModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((clientModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTCLIENTID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						clientModelImpl.getOriginalParentClientId()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PARENTCLIENTID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTCLIENTID,
					args);

				args = new Object[] { clientModelImpl.getParentClientId() };

				finderCache.removeResult(FINDER_PATH_COUNT_BY_PARENTCLIENTID,
					args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTCLIENTID,
					args);
			}
		}

		entityCache.putResult(ClientModelImpl.ENTITY_CACHE_ENABLED,
			ClientImpl.class, client.getPrimaryKey(), client, false);

		client.resetOriginalValues();

		return client;
	}

	protected Client toUnwrappedModel(Client client) {
		if (client instanceof ClientImpl) {
			return client;
		}

		ClientImpl clientImpl = new ClientImpl();

		clientImpl.setNew(client.isNew());
		clientImpl.setPrimaryKey(client.getPrimaryKey());

		clientImpl.setClientId(client.getClientId());
		clientImpl.setCompanyId(client.getCompanyId());
		clientImpl.setCreateDate(client.getCreateDate());
		clientImpl.setModifiedDate(client.getModifiedDate());
		clientImpl.setParentClientId(client.getParentClientId());
		clientImpl.setName(client.getName());
		clientImpl.setDescription(client.getDescription());
		clientImpl.setDashboardUrl(client.getDashboardUrl());
		clientImpl.setLogoId(client.getLogoId());
		clientImpl.setLogoUrl(client.getLogoUrl());
		clientImpl.setWebsiteUrl(client.getWebsiteUrl());
		clientImpl.setAddressId(client.getAddressId());
		clientImpl.setActive(client.isActive());

		return clientImpl;
	}

	/**
	 * Returns the client with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the client
	 * @return the client
	 * @throws NoSuchClientException if a client with the primary key could not be found
	 */
	@Override
	public Client findByPrimaryKey(Serializable primaryKey)
		throws NoSuchClientException {
		Client client = fetchByPrimaryKey(primaryKey);

		if (client == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchClientException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return client;
	}

	/**
	 * Returns the client with the primary key or throws a {@link NoSuchClientException} if it could not be found.
	 *
	 * @param clientId the primary key of the client
	 * @return the client
	 * @throws NoSuchClientException if a client with the primary key could not be found
	 */
	@Override
	public Client findByPrimaryKey(long clientId) throws NoSuchClientException {
		return findByPrimaryKey((Serializable)clientId);
	}

	/**
	 * Returns the client with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the client
	 * @return the client, or <code>null</code> if a client with the primary key could not be found
	 */
	@Override
	public Client fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ClientModelImpl.ENTITY_CACHE_ENABLED,
				ClientImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Client client = (Client)serializable;

		if (client == null) {
			Session session = null;

			try {
				session = openSession();

				client = (Client)session.get(ClientImpl.class, primaryKey);

				if (client != null) {
					cacheResult(client);
				}
				else {
					entityCache.putResult(ClientModelImpl.ENTITY_CACHE_ENABLED,
						ClientImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ClientModelImpl.ENTITY_CACHE_ENABLED,
					ClientImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return client;
	}

	/**
	 * Returns the client with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param clientId the primary key of the client
	 * @return the client, or <code>null</code> if a client with the primary key could not be found
	 */
	@Override
	public Client fetchByPrimaryKey(long clientId) {
		return fetchByPrimaryKey((Serializable)clientId);
	}

	@Override
	public Map<Serializable, Client> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Client> map = new HashMap<Serializable, Client>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Client client = fetchByPrimaryKey(primaryKey);

			if (client != null) {
				map.put(primaryKey, client);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ClientModelImpl.ENTITY_CACHE_ENABLED,
					ClientImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Client)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_CLIENT_WHERE_PKS_IN);

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

			for (Client client : (List<Client>)q.list()) {
				map.put(client.getPrimaryKeyObj(), client);

				cacheResult(client);

				uncachedPrimaryKeys.remove(client.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ClientModelImpl.ENTITY_CACHE_ENABLED,
					ClientImpl.class, primaryKey, nullModel);
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
	 * Returns all the clients.
	 *
	 * @return the clients
	 */
	@Override
	public List<Client> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the clients.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ClientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of clients
	 * @param end the upper bound of the range of clients (not inclusive)
	 * @return the range of clients
	 */
	@Override
	public List<Client> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the clients.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ClientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of clients
	 * @param end the upper bound of the range of clients (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of clients
	 */
	@Override
	public List<Client> findAll(int start, int end,
		OrderByComparator<Client> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the clients.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ClientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of clients
	 * @param end the upper bound of the range of clients (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of clients
	 */
	@Override
	public List<Client> findAll(int start, int end,
		OrderByComparator<Client> orderByComparator, boolean retrieveFromCache) {
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

		List<Client> list = null;

		if (retrieveFromCache) {
			list = (List<Client>)finderCache.getResult(finderPath, finderArgs,
					this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CLIENT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CLIENT;

				if (pagination) {
					sql = sql.concat(ClientModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Client>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Client>)QueryUtil.list(q, getDialect(), start,
							end);
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
	 * Removes all the clients from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Client client : findAll()) {
			remove(client);
		}
	}

	/**
	 * Returns the number of clients.
	 *
	 * @return the number of clients
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CLIENT);

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
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return ClientModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the client persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ClientImpl.class.getName());
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
	private static final String _SQL_SELECT_CLIENT = "SELECT client FROM Client client";
	private static final String _SQL_SELECT_CLIENT_WHERE_PKS_IN = "SELECT client FROM Client client WHERE clientId IN (";
	private static final String _SQL_SELECT_CLIENT_WHERE = "SELECT client FROM Client client WHERE ";
	private static final String _SQL_COUNT_CLIENT = "SELECT COUNT(client) FROM Client client";
	private static final String _SQL_COUNT_CLIENT_WHERE = "SELECT COUNT(client) FROM Client client WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "client.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Client exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Client exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(ClientPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"active"
			});
}