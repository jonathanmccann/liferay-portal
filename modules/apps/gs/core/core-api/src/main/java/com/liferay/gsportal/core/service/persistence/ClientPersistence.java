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

package com.liferay.gsportal.core.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.gsportal.core.exception.NoSuchClientException;
import com.liferay.gsportal.core.model.Client;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the client service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.gsportal.core.service.persistence.impl.ClientPersistenceImpl
 * @see ClientUtil
 * @generated
 */
@ProviderType
public interface ClientPersistence extends BasePersistence<Client> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ClientUtil} to access the client persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the clients where parentClientId = &#63;.
	*
	* @param parentClientId the parent client ID
	* @return the matching clients
	*/
	public java.util.List<Client> findByParentClientId(long parentClientId);

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
	public java.util.List<Client> findByParentClientId(long parentClientId,
		int start, int end);

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
	public java.util.List<Client> findByParentClientId(long parentClientId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Client> orderByComparator);

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
	public java.util.List<Client> findByParentClientId(long parentClientId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Client> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first client in the ordered set where parentClientId = &#63;.
	*
	* @param parentClientId the parent client ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching client
	* @throws NoSuchClientException if a matching client could not be found
	*/
	public Client findByParentClientId_First(long parentClientId,
		com.liferay.portal.kernel.util.OrderByComparator<Client> orderByComparator)
		throws NoSuchClientException;

	/**
	* Returns the first client in the ordered set where parentClientId = &#63;.
	*
	* @param parentClientId the parent client ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching client, or <code>null</code> if a matching client could not be found
	*/
	public Client fetchByParentClientId_First(long parentClientId,
		com.liferay.portal.kernel.util.OrderByComparator<Client> orderByComparator);

	/**
	* Returns the last client in the ordered set where parentClientId = &#63;.
	*
	* @param parentClientId the parent client ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching client
	* @throws NoSuchClientException if a matching client could not be found
	*/
	public Client findByParentClientId_Last(long parentClientId,
		com.liferay.portal.kernel.util.OrderByComparator<Client> orderByComparator)
		throws NoSuchClientException;

	/**
	* Returns the last client in the ordered set where parentClientId = &#63;.
	*
	* @param parentClientId the parent client ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching client, or <code>null</code> if a matching client could not be found
	*/
	public Client fetchByParentClientId_Last(long parentClientId,
		com.liferay.portal.kernel.util.OrderByComparator<Client> orderByComparator);

	/**
	* Returns the clients before and after the current client in the ordered set where parentClientId = &#63;.
	*
	* @param clientId the primary key of the current client
	* @param parentClientId the parent client ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next client
	* @throws NoSuchClientException if a client with the primary key could not be found
	*/
	public Client[] findByParentClientId_PrevAndNext(long clientId,
		long parentClientId,
		com.liferay.portal.kernel.util.OrderByComparator<Client> orderByComparator)
		throws NoSuchClientException;

	/**
	* Removes all the clients where parentClientId = &#63; from the database.
	*
	* @param parentClientId the parent client ID
	*/
	public void removeByParentClientId(long parentClientId);

	/**
	* Returns the number of clients where parentClientId = &#63;.
	*
	* @param parentClientId the parent client ID
	* @return the number of matching clients
	*/
	public int countByParentClientId(long parentClientId);

	/**
	* Caches the client in the entity cache if it is enabled.
	*
	* @param client the client
	*/
	public void cacheResult(Client client);

	/**
	* Caches the clients in the entity cache if it is enabled.
	*
	* @param clients the clients
	*/
	public void cacheResult(java.util.List<Client> clients);

	/**
	* Creates a new client with the primary key. Does not add the client to the database.
	*
	* @param clientId the primary key for the new client
	* @return the new client
	*/
	public Client create(long clientId);

	/**
	* Removes the client with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param clientId the primary key of the client
	* @return the client that was removed
	* @throws NoSuchClientException if a client with the primary key could not be found
	*/
	public Client remove(long clientId) throws NoSuchClientException;

	public Client updateImpl(Client client);

	/**
	* Returns the client with the primary key or throws a {@link NoSuchClientException} if it could not be found.
	*
	* @param clientId the primary key of the client
	* @return the client
	* @throws NoSuchClientException if a client with the primary key could not be found
	*/
	public Client findByPrimaryKey(long clientId) throws NoSuchClientException;

	/**
	* Returns the client with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param clientId the primary key of the client
	* @return the client, or <code>null</code> if a client with the primary key could not be found
	*/
	public Client fetchByPrimaryKey(long clientId);

	@Override
	public java.util.Map<java.io.Serializable, Client> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the clients.
	*
	* @return the clients
	*/
	public java.util.List<Client> findAll();

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
	public java.util.List<Client> findAll(int start, int end);

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
	public java.util.List<Client> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Client> orderByComparator);

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
	public java.util.List<Client> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Client> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the clients from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of clients.
	*
	* @return the number of clients
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}