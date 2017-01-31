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

import com.liferay.gsportal.core.exception.NoSuchMemberOfException;
import com.liferay.gsportal.core.model.MemberOf;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the member of service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.gsportal.core.service.persistence.impl.MemberOfPersistenceImpl
 * @see MemberOfUtil
 * @generated
 */
@ProviderType
public interface MemberOfPersistence extends BasePersistence<MemberOf> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MemberOfUtil} to access the member of persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the member ofs where clientId = &#63; and projectId = &#63; and engagementId = &#63;.
	*
	* @param clientId the client ID
	* @param projectId the project ID
	* @param engagementId the engagement ID
	* @return the matching member ofs
	*/
	public java.util.List<MemberOf> findByC_P_E(long clientId, long projectId,
		long engagementId);

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
	public java.util.List<MemberOf> findByC_P_E(long clientId, long projectId,
		long engagementId, int start, int end);

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
	public java.util.List<MemberOf> findByC_P_E(long clientId, long projectId,
		long engagementId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MemberOf> orderByComparator);

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
	public java.util.List<MemberOf> findByC_P_E(long clientId, long projectId,
		long engagementId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MemberOf> orderByComparator,
		boolean retrieveFromCache);

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
	public MemberOf findByC_P_E_First(long clientId, long projectId,
		long engagementId,
		com.liferay.portal.kernel.util.OrderByComparator<MemberOf> orderByComparator)
		throws NoSuchMemberOfException;

	/**
	* Returns the first member of in the ordered set where clientId = &#63; and projectId = &#63; and engagementId = &#63;.
	*
	* @param clientId the client ID
	* @param projectId the project ID
	* @param engagementId the engagement ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching member of, or <code>null</code> if a matching member of could not be found
	*/
	public MemberOf fetchByC_P_E_First(long clientId, long projectId,
		long engagementId,
		com.liferay.portal.kernel.util.OrderByComparator<MemberOf> orderByComparator);

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
	public MemberOf findByC_P_E_Last(long clientId, long projectId,
		long engagementId,
		com.liferay.portal.kernel.util.OrderByComparator<MemberOf> orderByComparator)
		throws NoSuchMemberOfException;

	/**
	* Returns the last member of in the ordered set where clientId = &#63; and projectId = &#63; and engagementId = &#63;.
	*
	* @param clientId the client ID
	* @param projectId the project ID
	* @param engagementId the engagement ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching member of, or <code>null</code> if a matching member of could not be found
	*/
	public MemberOf fetchByC_P_E_Last(long clientId, long projectId,
		long engagementId,
		com.liferay.portal.kernel.util.OrderByComparator<MemberOf> orderByComparator);

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
	public MemberOf[] findByC_P_E_PrevAndNext(long memberOfId, long clientId,
		long projectId, long engagementId,
		com.liferay.portal.kernel.util.OrderByComparator<MemberOf> orderByComparator)
		throws NoSuchMemberOfException;

	/**
	* Removes all the member ofs where clientId = &#63; and projectId = &#63; and engagementId = &#63; from the database.
	*
	* @param clientId the client ID
	* @param projectId the project ID
	* @param engagementId the engagement ID
	*/
	public void removeByC_P_E(long clientId, long projectId, long engagementId);

	/**
	* Returns the number of member ofs where clientId = &#63; and projectId = &#63; and engagementId = &#63;.
	*
	* @param clientId the client ID
	* @param projectId the project ID
	* @param engagementId the engagement ID
	* @return the number of matching member ofs
	*/
	public int countByC_P_E(long clientId, long projectId, long engagementId);

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
	public MemberOf findByU_C_P_E(long userId, long clientId, long projectId,
		long engagementId) throws NoSuchMemberOfException;

	/**
	* Returns the member of where userId = &#63; and clientId = &#63; and projectId = &#63; and engagementId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param clientId the client ID
	* @param projectId the project ID
	* @param engagementId the engagement ID
	* @return the matching member of, or <code>null</code> if a matching member of could not be found
	*/
	public MemberOf fetchByU_C_P_E(long userId, long clientId, long projectId,
		long engagementId);

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
	public MemberOf fetchByU_C_P_E(long userId, long clientId, long projectId,
		long engagementId, boolean retrieveFromCache);

	/**
	* Removes the member of where userId = &#63; and clientId = &#63; and projectId = &#63; and engagementId = &#63; from the database.
	*
	* @param userId the user ID
	* @param clientId the client ID
	* @param projectId the project ID
	* @param engagementId the engagement ID
	* @return the member of that was removed
	*/
	public MemberOf removeByU_C_P_E(long userId, long clientId, long projectId,
		long engagementId) throws NoSuchMemberOfException;

	/**
	* Returns the number of member ofs where userId = &#63; and clientId = &#63; and projectId = &#63; and engagementId = &#63;.
	*
	* @param userId the user ID
	* @param clientId the client ID
	* @param projectId the project ID
	* @param engagementId the engagement ID
	* @return the number of matching member ofs
	*/
	public int countByU_C_P_E(long userId, long clientId, long projectId,
		long engagementId);

	/**
	* Caches the member of in the entity cache if it is enabled.
	*
	* @param memberOf the member of
	*/
	public void cacheResult(MemberOf memberOf);

	/**
	* Caches the member ofs in the entity cache if it is enabled.
	*
	* @param memberOfs the member ofs
	*/
	public void cacheResult(java.util.List<MemberOf> memberOfs);

	/**
	* Creates a new member of with the primary key. Does not add the member of to the database.
	*
	* @param memberOfId the primary key for the new member of
	* @return the new member of
	*/
	public MemberOf create(long memberOfId);

	/**
	* Removes the member of with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param memberOfId the primary key of the member of
	* @return the member of that was removed
	* @throws NoSuchMemberOfException if a member of with the primary key could not be found
	*/
	public MemberOf remove(long memberOfId) throws NoSuchMemberOfException;

	public MemberOf updateImpl(MemberOf memberOf);

	/**
	* Returns the member of with the primary key or throws a {@link NoSuchMemberOfException} if it could not be found.
	*
	* @param memberOfId the primary key of the member of
	* @return the member of
	* @throws NoSuchMemberOfException if a member of with the primary key could not be found
	*/
	public MemberOf findByPrimaryKey(long memberOfId)
		throws NoSuchMemberOfException;

	/**
	* Returns the member of with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param memberOfId the primary key of the member of
	* @return the member of, or <code>null</code> if a member of with the primary key could not be found
	*/
	public MemberOf fetchByPrimaryKey(long memberOfId);

	@Override
	public java.util.Map<java.io.Serializable, MemberOf> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the member ofs.
	*
	* @return the member ofs
	*/
	public java.util.List<MemberOf> findAll();

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
	public java.util.List<MemberOf> findAll(int start, int end);

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
	public java.util.List<MemberOf> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MemberOf> orderByComparator);

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
	public java.util.List<MemberOf> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<MemberOf> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the member ofs from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of member ofs.
	*
	* @return the number of member ofs
	*/
	public int countAll();
}