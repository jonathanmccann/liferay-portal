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

import com.liferay.gsportal.core.model.StaffMember;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the staff member service. This utility wraps {@link com.liferay.gsportal.core.service.persistence.impl.StaffMemberPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see StaffMemberPersistence
 * @see com.liferay.gsportal.core.service.persistence.impl.StaffMemberPersistenceImpl
 * @generated
 */
@ProviderType
public class StaffMemberUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(StaffMember staffMember) {
		getPersistence().clearCache(staffMember);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<StaffMember> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<StaffMember> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<StaffMember> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<StaffMember> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static StaffMember update(StaffMember staffMember) {
		return getPersistence().update(staffMember);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static StaffMember update(StaffMember staffMember,
		ServiceContext serviceContext) {
		return getPersistence().update(staffMember, serviceContext);
	}

	/**
	* Caches the staff member in the entity cache if it is enabled.
	*
	* @param staffMember the staff member
	*/
	public static void cacheResult(StaffMember staffMember) {
		getPersistence().cacheResult(staffMember);
	}

	/**
	* Caches the staff members in the entity cache if it is enabled.
	*
	* @param staffMembers the staff members
	*/
	public static void cacheResult(List<StaffMember> staffMembers) {
		getPersistence().cacheResult(staffMembers);
	}

	/**
	* Creates a new staff member with the primary key. Does not add the staff member to the database.
	*
	* @param userId the primary key for the new staff member
	* @return the new staff member
	*/
	public static StaffMember create(long userId) {
		return getPersistence().create(userId);
	}

	/**
	* Removes the staff member with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userId the primary key of the staff member
	* @return the staff member that was removed
	* @throws NoSuchStaffMemberException if a staff member with the primary key could not be found
	*/
	public static StaffMember remove(long userId)
		throws com.liferay.gsportal.core.exception.NoSuchStaffMemberException {
		return getPersistence().remove(userId);
	}

	public static StaffMember updateImpl(StaffMember staffMember) {
		return getPersistence().updateImpl(staffMember);
	}

	/**
	* Returns the staff member with the primary key or throws a {@link NoSuchStaffMemberException} if it could not be found.
	*
	* @param userId the primary key of the staff member
	* @return the staff member
	* @throws NoSuchStaffMemberException if a staff member with the primary key could not be found
	*/
	public static StaffMember findByPrimaryKey(long userId)
		throws com.liferay.gsportal.core.exception.NoSuchStaffMemberException {
		return getPersistence().findByPrimaryKey(userId);
	}

	/**
	* Returns the staff member with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param userId the primary key of the staff member
	* @return the staff member, or <code>null</code> if a staff member with the primary key could not be found
	*/
	public static StaffMember fetchByPrimaryKey(long userId) {
		return getPersistence().fetchByPrimaryKey(userId);
	}

	public static java.util.Map<java.io.Serializable, StaffMember> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the staff members.
	*
	* @return the staff members
	*/
	public static List<StaffMember> findAll() {
		return getPersistence().findAll();
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
	public static List<StaffMember> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
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
	public static List<StaffMember> findAll(int start, int end,
		OrderByComparator<StaffMember> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
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
	public static List<StaffMember> findAll(int start, int end,
		OrderByComparator<StaffMember> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the staff members from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of staff members.
	*
	* @return the number of staff members
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static StaffMemberPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<StaffMemberPersistence, StaffMemberPersistence> _serviceTracker =
		ServiceTrackerFactory.open(StaffMemberPersistence.class);
}