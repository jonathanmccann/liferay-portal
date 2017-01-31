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

import com.liferay.gsportal.core.exception.NoSuchStaffMemberException;
import com.liferay.gsportal.core.model.StaffMember;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the staff member service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.gsportal.core.service.persistence.impl.StaffMemberPersistenceImpl
 * @see StaffMemberUtil
 * @generated
 */
@ProviderType
public interface StaffMemberPersistence extends BasePersistence<StaffMember> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link StaffMemberUtil} to access the staff member persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the staff member in the entity cache if it is enabled.
	*
	* @param staffMember the staff member
	*/
	public void cacheResult(StaffMember staffMember);

	/**
	* Caches the staff members in the entity cache if it is enabled.
	*
	* @param staffMembers the staff members
	*/
	public void cacheResult(java.util.List<StaffMember> staffMembers);

	/**
	* Creates a new staff member with the primary key. Does not add the staff member to the database.
	*
	* @param userId the primary key for the new staff member
	* @return the new staff member
	*/
	public StaffMember create(long userId);

	/**
	* Removes the staff member with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userId the primary key of the staff member
	* @return the staff member that was removed
	* @throws NoSuchStaffMemberException if a staff member with the primary key could not be found
	*/
	public StaffMember remove(long userId) throws NoSuchStaffMemberException;

	public StaffMember updateImpl(StaffMember staffMember);

	/**
	* Returns the staff member with the primary key or throws a {@link NoSuchStaffMemberException} if it could not be found.
	*
	* @param userId the primary key of the staff member
	* @return the staff member
	* @throws NoSuchStaffMemberException if a staff member with the primary key could not be found
	*/
	public StaffMember findByPrimaryKey(long userId)
		throws NoSuchStaffMemberException;

	/**
	* Returns the staff member with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param userId the primary key of the staff member
	* @return the staff member, or <code>null</code> if a staff member with the primary key could not be found
	*/
	public StaffMember fetchByPrimaryKey(long userId);

	@Override
	public java.util.Map<java.io.Serializable, StaffMember> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the staff members.
	*
	* @return the staff members
	*/
	public java.util.List<StaffMember> findAll();

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
	public java.util.List<StaffMember> findAll(int start, int end);

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
	public java.util.List<StaffMember> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StaffMember> orderByComparator);

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
	public java.util.List<StaffMember> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<StaffMember> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the staff members from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of staff members.
	*
	* @return the number of staff members
	*/
	public int countAll();
}