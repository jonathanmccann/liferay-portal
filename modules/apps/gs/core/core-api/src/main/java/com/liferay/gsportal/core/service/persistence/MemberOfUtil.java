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

import com.liferay.gsportal.core.model.MemberOf;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the member of service. This utility wraps {@link com.liferay.gsportal.core.service.persistence.impl.MemberOfPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MemberOfPersistence
 * @see com.liferay.gsportal.core.service.persistence.impl.MemberOfPersistenceImpl
 * @generated
 */
@ProviderType
public class MemberOfUtil {
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
	public static void clearCache(MemberOf memberOf) {
		getPersistence().clearCache(memberOf);
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
	public static List<MemberOf> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<MemberOf> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<MemberOf> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<MemberOf> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static MemberOf update(MemberOf memberOf) {
		return getPersistence().update(memberOf);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static MemberOf update(MemberOf memberOf,
		ServiceContext serviceContext) {
		return getPersistence().update(memberOf, serviceContext);
	}

	/**
	* Returns all the member ofs where clientId = &#63; and projectId = &#63; and engagementId = &#63;.
	*
	* @param clientId the client ID
	* @param projectId the project ID
	* @param engagementId the engagement ID
	* @return the matching member ofs
	*/
	public static List<MemberOf> findByC_P_E(long clientId, long projectId,
		long engagementId) {
		return getPersistence().findByC_P_E(clientId, projectId, engagementId);
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
	public static List<MemberOf> findByC_P_E(long clientId, long projectId,
		long engagementId, int start, int end) {
		return getPersistence()
				   .findByC_P_E(clientId, projectId, engagementId, start, end);
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
	public static List<MemberOf> findByC_P_E(long clientId, long projectId,
		long engagementId, int start, int end,
		OrderByComparator<MemberOf> orderByComparator) {
		return getPersistence()
				   .findByC_P_E(clientId, projectId, engagementId, start, end,
			orderByComparator);
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
	public static List<MemberOf> findByC_P_E(long clientId, long projectId,
		long engagementId, int start, int end,
		OrderByComparator<MemberOf> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByC_P_E(clientId, projectId, engagementId, start, end,
			orderByComparator, retrieveFromCache);
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
	public static MemberOf findByC_P_E_First(long clientId, long projectId,
		long engagementId, OrderByComparator<MemberOf> orderByComparator)
		throws com.liferay.gsportal.core.exception.NoSuchMemberOfException {
		return getPersistence()
				   .findByC_P_E_First(clientId, projectId, engagementId,
			orderByComparator);
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
	public static MemberOf fetchByC_P_E_First(long clientId, long projectId,
		long engagementId, OrderByComparator<MemberOf> orderByComparator) {
		return getPersistence()
				   .fetchByC_P_E_First(clientId, projectId, engagementId,
			orderByComparator);
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
	public static MemberOf findByC_P_E_Last(long clientId, long projectId,
		long engagementId, OrderByComparator<MemberOf> orderByComparator)
		throws com.liferay.gsportal.core.exception.NoSuchMemberOfException {
		return getPersistence()
				   .findByC_P_E_Last(clientId, projectId, engagementId,
			orderByComparator);
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
	public static MemberOf fetchByC_P_E_Last(long clientId, long projectId,
		long engagementId, OrderByComparator<MemberOf> orderByComparator) {
		return getPersistence()
				   .fetchByC_P_E_Last(clientId, projectId, engagementId,
			orderByComparator);
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
	public static MemberOf[] findByC_P_E_PrevAndNext(long memberOfId,
		long clientId, long projectId, long engagementId,
		OrderByComparator<MemberOf> orderByComparator)
		throws com.liferay.gsportal.core.exception.NoSuchMemberOfException {
		return getPersistence()
				   .findByC_P_E_PrevAndNext(memberOfId, clientId, projectId,
			engagementId, orderByComparator);
	}

	/**
	* Removes all the member ofs where clientId = &#63; and projectId = &#63; and engagementId = &#63; from the database.
	*
	* @param clientId the client ID
	* @param projectId the project ID
	* @param engagementId the engagement ID
	*/
	public static void removeByC_P_E(long clientId, long projectId,
		long engagementId) {
		getPersistence().removeByC_P_E(clientId, projectId, engagementId);
	}

	/**
	* Returns the number of member ofs where clientId = &#63; and projectId = &#63; and engagementId = &#63;.
	*
	* @param clientId the client ID
	* @param projectId the project ID
	* @param engagementId the engagement ID
	* @return the number of matching member ofs
	*/
	public static int countByC_P_E(long clientId, long projectId,
		long engagementId) {
		return getPersistence().countByC_P_E(clientId, projectId, engagementId);
	}

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
	public static MemberOf findByU_C_P_E(long userId, long clientId,
		long projectId, long engagementId)
		throws com.liferay.gsportal.core.exception.NoSuchMemberOfException {
		return getPersistence()
				   .findByU_C_P_E(userId, clientId, projectId, engagementId);
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
	public static MemberOf fetchByU_C_P_E(long userId, long clientId,
		long projectId, long engagementId) {
		return getPersistence()
				   .fetchByU_C_P_E(userId, clientId, projectId, engagementId);
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
	public static MemberOf fetchByU_C_P_E(long userId, long clientId,
		long projectId, long engagementId, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByU_C_P_E(userId, clientId, projectId, engagementId,
			retrieveFromCache);
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
	public static MemberOf removeByU_C_P_E(long userId, long clientId,
		long projectId, long engagementId)
		throws com.liferay.gsportal.core.exception.NoSuchMemberOfException {
		return getPersistence()
				   .removeByU_C_P_E(userId, clientId, projectId, engagementId);
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
	public static int countByU_C_P_E(long userId, long clientId,
		long projectId, long engagementId) {
		return getPersistence()
				   .countByU_C_P_E(userId, clientId, projectId, engagementId);
	}

	/**
	* Caches the member of in the entity cache if it is enabled.
	*
	* @param memberOf the member of
	*/
	public static void cacheResult(MemberOf memberOf) {
		getPersistence().cacheResult(memberOf);
	}

	/**
	* Caches the member ofs in the entity cache if it is enabled.
	*
	* @param memberOfs the member ofs
	*/
	public static void cacheResult(List<MemberOf> memberOfs) {
		getPersistence().cacheResult(memberOfs);
	}

	/**
	* Creates a new member of with the primary key. Does not add the member of to the database.
	*
	* @param memberOfId the primary key for the new member of
	* @return the new member of
	*/
	public static MemberOf create(long memberOfId) {
		return getPersistence().create(memberOfId);
	}

	/**
	* Removes the member of with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param memberOfId the primary key of the member of
	* @return the member of that was removed
	* @throws NoSuchMemberOfException if a member of with the primary key could not be found
	*/
	public static MemberOf remove(long memberOfId)
		throws com.liferay.gsportal.core.exception.NoSuchMemberOfException {
		return getPersistence().remove(memberOfId);
	}

	public static MemberOf updateImpl(MemberOf memberOf) {
		return getPersistence().updateImpl(memberOf);
	}

	/**
	* Returns the member of with the primary key or throws a {@link NoSuchMemberOfException} if it could not be found.
	*
	* @param memberOfId the primary key of the member of
	* @return the member of
	* @throws NoSuchMemberOfException if a member of with the primary key could not be found
	*/
	public static MemberOf findByPrimaryKey(long memberOfId)
		throws com.liferay.gsportal.core.exception.NoSuchMemberOfException {
		return getPersistence().findByPrimaryKey(memberOfId);
	}

	/**
	* Returns the member of with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param memberOfId the primary key of the member of
	* @return the member of, or <code>null</code> if a member of with the primary key could not be found
	*/
	public static MemberOf fetchByPrimaryKey(long memberOfId) {
		return getPersistence().fetchByPrimaryKey(memberOfId);
	}

	public static java.util.Map<java.io.Serializable, MemberOf> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the member ofs.
	*
	* @return the member ofs
	*/
	public static List<MemberOf> findAll() {
		return getPersistence().findAll();
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
	public static List<MemberOf> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
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
	public static List<MemberOf> findAll(int start, int end,
		OrderByComparator<MemberOf> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
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
	public static List<MemberOf> findAll(int start, int end,
		OrderByComparator<MemberOf> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the member ofs from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of member ofs.
	*
	* @return the number of member ofs
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static MemberOfPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<MemberOfPersistence, MemberOfPersistence> _serviceTracker =
		ServiceTrackerFactory.open(MemberOfPersistence.class);
}