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

package com.liferay.portal.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.model.RecentLayoutSet;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the recent layout set service. This utility wraps {@link com.liferay.portal.service.persistence.impl.RecentLayoutSetPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RecentLayoutSetPersistence
 * @see com.liferay.portal.service.persistence.impl.RecentLayoutSetPersistenceImpl
 * @generated
 */
@ProviderType
public class RecentLayoutSetUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(RecentLayoutSet recentLayoutSet) {
		getPersistence().clearCache(recentLayoutSet);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<RecentLayoutSet> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<RecentLayoutSet> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<RecentLayoutSet> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<RecentLayoutSet> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static RecentLayoutSet update(RecentLayoutSet recentLayoutSet) {
		return getPersistence().update(recentLayoutSet);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static RecentLayoutSet update(RecentLayoutSet recentLayoutSet,
		ServiceContext serviceContext) {
		return getPersistence().update(recentLayoutSet, serviceContext);
	}

	/**
	* Returns all the recent layout sets where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching recent layout sets
	*/
	public static List<RecentLayoutSet> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
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
	public static List<RecentLayoutSet> findByGroupId(long groupId, int start,
		int end) {
		return getPersistence().findByGroupId(groupId, start, end);
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
	public static List<RecentLayoutSet> findByGroupId(long groupId, int start,
		int end, OrderByComparator<RecentLayoutSet> orderByComparator) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
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
	public static List<RecentLayoutSet> findByGroupId(long groupId, int start,
		int end, OrderByComparator<RecentLayoutSet> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first recent layout set in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching recent layout set
	* @throws NoSuchRecentLayoutSetException if a matching recent layout set could not be found
	*/
	public static RecentLayoutSet findByGroupId_First(long groupId,
		OrderByComparator<RecentLayoutSet> orderByComparator)
		throws com.liferay.portal.NoSuchRecentLayoutSetException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first recent layout set in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching recent layout set, or <code>null</code> if a matching recent layout set could not be found
	*/
	public static RecentLayoutSet fetchByGroupId_First(long groupId,
		OrderByComparator<RecentLayoutSet> orderByComparator) {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last recent layout set in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching recent layout set
	* @throws NoSuchRecentLayoutSetException if a matching recent layout set could not be found
	*/
	public static RecentLayoutSet findByGroupId_Last(long groupId,
		OrderByComparator<RecentLayoutSet> orderByComparator)
		throws com.liferay.portal.NoSuchRecentLayoutSetException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last recent layout set in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching recent layout set, or <code>null</code> if a matching recent layout set could not be found
	*/
	public static RecentLayoutSet fetchByGroupId_Last(long groupId,
		OrderByComparator<RecentLayoutSet> orderByComparator) {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
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
	public static RecentLayoutSet[] findByGroupId_PrevAndNext(
		long recentLayoutSetId, long groupId,
		OrderByComparator<RecentLayoutSet> orderByComparator)
		throws com.liferay.portal.NoSuchRecentLayoutSetException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(recentLayoutSetId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the recent layout sets where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of recent layout sets where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching recent layout sets
	*/
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns all the recent layout sets where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching recent layout sets
	*/
	public static List<RecentLayoutSet> findByUserId(long userId) {
		return getPersistence().findByUserId(userId);
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
	public static List<RecentLayoutSet> findByUserId(long userId, int start,
		int end) {
		return getPersistence().findByUserId(userId, start, end);
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
	public static List<RecentLayoutSet> findByUserId(long userId, int start,
		int end, OrderByComparator<RecentLayoutSet> orderByComparator) {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator);
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
	public static List<RecentLayoutSet> findByUserId(long userId, int start,
		int end, OrderByComparator<RecentLayoutSet> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first recent layout set in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching recent layout set
	* @throws NoSuchRecentLayoutSetException if a matching recent layout set could not be found
	*/
	public static RecentLayoutSet findByUserId_First(long userId,
		OrderByComparator<RecentLayoutSet> orderByComparator)
		throws com.liferay.portal.NoSuchRecentLayoutSetException {
		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the first recent layout set in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching recent layout set, or <code>null</code> if a matching recent layout set could not be found
	*/
	public static RecentLayoutSet fetchByUserId_First(long userId,
		OrderByComparator<RecentLayoutSet> orderByComparator) {
		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the last recent layout set in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching recent layout set
	* @throws NoSuchRecentLayoutSetException if a matching recent layout set could not be found
	*/
	public static RecentLayoutSet findByUserId_Last(long userId,
		OrderByComparator<RecentLayoutSet> orderByComparator)
		throws com.liferay.portal.NoSuchRecentLayoutSetException {
		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the last recent layout set in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching recent layout set, or <code>null</code> if a matching recent layout set could not be found
	*/
	public static RecentLayoutSet fetchByUserId_Last(long userId,
		OrderByComparator<RecentLayoutSet> orderByComparator) {
		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
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
	public static RecentLayoutSet[] findByUserId_PrevAndNext(
		long recentLayoutSetId, long userId,
		OrderByComparator<RecentLayoutSet> orderByComparator)
		throws com.liferay.portal.NoSuchRecentLayoutSetException {
		return getPersistence()
				   .findByUserId_PrevAndNext(recentLayoutSetId, userId,
			orderByComparator);
	}

	/**
	* Removes all the recent layout sets where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public static void removeByUserId(long userId) {
		getPersistence().removeByUserId(userId);
	}

	/**
	* Returns the number of recent layout sets where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching recent layout sets
	*/
	public static int countByUserId(long userId) {
		return getPersistence().countByUserId(userId);
	}

	/**
	* Returns all the recent layout sets where layoutSetBranchId = &#63;.
	*
	* @param layoutSetBranchId the layout set branch ID
	* @return the matching recent layout sets
	*/
	public static List<RecentLayoutSet> findByLayoutSetBranchId(
		long layoutSetBranchId) {
		return getPersistence().findByLayoutSetBranchId(layoutSetBranchId);
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
	public static List<RecentLayoutSet> findByLayoutSetBranchId(
		long layoutSetBranchId, int start, int end) {
		return getPersistence()
				   .findByLayoutSetBranchId(layoutSetBranchId, start, end);
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
	public static List<RecentLayoutSet> findByLayoutSetBranchId(
		long layoutSetBranchId, int start, int end,
		OrderByComparator<RecentLayoutSet> orderByComparator) {
		return getPersistence()
				   .findByLayoutSetBranchId(layoutSetBranchId, start, end,
			orderByComparator);
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
	public static List<RecentLayoutSet> findByLayoutSetBranchId(
		long layoutSetBranchId, int start, int end,
		OrderByComparator<RecentLayoutSet> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByLayoutSetBranchId(layoutSetBranchId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first recent layout set in the ordered set where layoutSetBranchId = &#63;.
	*
	* @param layoutSetBranchId the layout set branch ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching recent layout set
	* @throws NoSuchRecentLayoutSetException if a matching recent layout set could not be found
	*/
	public static RecentLayoutSet findByLayoutSetBranchId_First(
		long layoutSetBranchId,
		OrderByComparator<RecentLayoutSet> orderByComparator)
		throws com.liferay.portal.NoSuchRecentLayoutSetException {
		return getPersistence()
				   .findByLayoutSetBranchId_First(layoutSetBranchId,
			orderByComparator);
	}

	/**
	* Returns the first recent layout set in the ordered set where layoutSetBranchId = &#63;.
	*
	* @param layoutSetBranchId the layout set branch ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching recent layout set, or <code>null</code> if a matching recent layout set could not be found
	*/
	public static RecentLayoutSet fetchByLayoutSetBranchId_First(
		long layoutSetBranchId,
		OrderByComparator<RecentLayoutSet> orderByComparator) {
		return getPersistence()
				   .fetchByLayoutSetBranchId_First(layoutSetBranchId,
			orderByComparator);
	}

	/**
	* Returns the last recent layout set in the ordered set where layoutSetBranchId = &#63;.
	*
	* @param layoutSetBranchId the layout set branch ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching recent layout set
	* @throws NoSuchRecentLayoutSetException if a matching recent layout set could not be found
	*/
	public static RecentLayoutSet findByLayoutSetBranchId_Last(
		long layoutSetBranchId,
		OrderByComparator<RecentLayoutSet> orderByComparator)
		throws com.liferay.portal.NoSuchRecentLayoutSetException {
		return getPersistence()
				   .findByLayoutSetBranchId_Last(layoutSetBranchId,
			orderByComparator);
	}

	/**
	* Returns the last recent layout set in the ordered set where layoutSetBranchId = &#63;.
	*
	* @param layoutSetBranchId the layout set branch ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching recent layout set, or <code>null</code> if a matching recent layout set could not be found
	*/
	public static RecentLayoutSet fetchByLayoutSetBranchId_Last(
		long layoutSetBranchId,
		OrderByComparator<RecentLayoutSet> orderByComparator) {
		return getPersistence()
				   .fetchByLayoutSetBranchId_Last(layoutSetBranchId,
			orderByComparator);
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
	public static RecentLayoutSet[] findByLayoutSetBranchId_PrevAndNext(
		long recentLayoutSetId, long layoutSetBranchId,
		OrderByComparator<RecentLayoutSet> orderByComparator)
		throws com.liferay.portal.NoSuchRecentLayoutSetException {
		return getPersistence()
				   .findByLayoutSetBranchId_PrevAndNext(recentLayoutSetId,
			layoutSetBranchId, orderByComparator);
	}

	/**
	* Removes all the recent layout sets where layoutSetBranchId = &#63; from the database.
	*
	* @param layoutSetBranchId the layout set branch ID
	*/
	public static void removeByLayoutSetBranchId(long layoutSetBranchId) {
		getPersistence().removeByLayoutSetBranchId(layoutSetBranchId);
	}

	/**
	* Returns the number of recent layout sets where layoutSetBranchId = &#63;.
	*
	* @param layoutSetBranchId the layout set branch ID
	* @return the number of matching recent layout sets
	*/
	public static int countByLayoutSetBranchId(long layoutSetBranchId) {
		return getPersistence().countByLayoutSetBranchId(layoutSetBranchId);
	}

	/**
	* Returns the recent layout set where userId = &#63; and layoutSetId = &#63; or throws a {@link NoSuchRecentLayoutSetException} if it could not be found.
	*
	* @param userId the user ID
	* @param layoutSetId the layout set ID
	* @return the matching recent layout set
	* @throws NoSuchRecentLayoutSetException if a matching recent layout set could not be found
	*/
	public static RecentLayoutSet findByU_L(long userId, long layoutSetId)
		throws com.liferay.portal.NoSuchRecentLayoutSetException {
		return getPersistence().findByU_L(userId, layoutSetId);
	}

	/**
	* Returns the recent layout set where userId = &#63; and layoutSetId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param layoutSetId the layout set ID
	* @return the matching recent layout set, or <code>null</code> if a matching recent layout set could not be found
	*/
	public static RecentLayoutSet fetchByU_L(long userId, long layoutSetId) {
		return getPersistence().fetchByU_L(userId, layoutSetId);
	}

	/**
	* Returns the recent layout set where userId = &#63; and layoutSetId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param layoutSetId the layout set ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching recent layout set, or <code>null</code> if a matching recent layout set could not be found
	*/
	public static RecentLayoutSet fetchByU_L(long userId, long layoutSetId,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByU_L(userId, layoutSetId, retrieveFromCache);
	}

	/**
	* Removes the recent layout set where userId = &#63; and layoutSetId = &#63; from the database.
	*
	* @param userId the user ID
	* @param layoutSetId the layout set ID
	* @return the recent layout set that was removed
	*/
	public static RecentLayoutSet removeByU_L(long userId, long layoutSetId)
		throws com.liferay.portal.NoSuchRecentLayoutSetException {
		return getPersistence().removeByU_L(userId, layoutSetId);
	}

	/**
	* Returns the number of recent layout sets where userId = &#63; and layoutSetId = &#63;.
	*
	* @param userId the user ID
	* @param layoutSetId the layout set ID
	* @return the number of matching recent layout sets
	*/
	public static int countByU_L(long userId, long layoutSetId) {
		return getPersistence().countByU_L(userId, layoutSetId);
	}

	/**
	* Caches the recent layout set in the entity cache if it is enabled.
	*
	* @param recentLayoutSet the recent layout set
	*/
	public static void cacheResult(RecentLayoutSet recentLayoutSet) {
		getPersistence().cacheResult(recentLayoutSet);
	}

	/**
	* Caches the recent layout sets in the entity cache if it is enabled.
	*
	* @param recentLayoutSets the recent layout sets
	*/
	public static void cacheResult(List<RecentLayoutSet> recentLayoutSets) {
		getPersistence().cacheResult(recentLayoutSets);
	}

	/**
	* Creates a new recent layout set with the primary key. Does not add the recent layout set to the database.
	*
	* @param recentLayoutSetId the primary key for the new recent layout set
	* @return the new recent layout set
	*/
	public static RecentLayoutSet create(long recentLayoutSetId) {
		return getPersistence().create(recentLayoutSetId);
	}

	/**
	* Removes the recent layout set with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param recentLayoutSetId the primary key of the recent layout set
	* @return the recent layout set that was removed
	* @throws NoSuchRecentLayoutSetException if a recent layout set with the primary key could not be found
	*/
	public static RecentLayoutSet remove(long recentLayoutSetId)
		throws com.liferay.portal.NoSuchRecentLayoutSetException {
		return getPersistence().remove(recentLayoutSetId);
	}

	public static RecentLayoutSet updateImpl(RecentLayoutSet recentLayoutSet) {
		return getPersistence().updateImpl(recentLayoutSet);
	}

	/**
	* Returns the recent layout set with the primary key or throws a {@link NoSuchRecentLayoutSetException} if it could not be found.
	*
	* @param recentLayoutSetId the primary key of the recent layout set
	* @return the recent layout set
	* @throws NoSuchRecentLayoutSetException if a recent layout set with the primary key could not be found
	*/
	public static RecentLayoutSet findByPrimaryKey(long recentLayoutSetId)
		throws com.liferay.portal.NoSuchRecentLayoutSetException {
		return getPersistence().findByPrimaryKey(recentLayoutSetId);
	}

	/**
	* Returns the recent layout set with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param recentLayoutSetId the primary key of the recent layout set
	* @return the recent layout set, or <code>null</code> if a recent layout set with the primary key could not be found
	*/
	public static RecentLayoutSet fetchByPrimaryKey(long recentLayoutSetId) {
		return getPersistence().fetchByPrimaryKey(recentLayoutSetId);
	}

	public static java.util.Map<java.io.Serializable, RecentLayoutSet> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the recent layout sets.
	*
	* @return the recent layout sets
	*/
	public static List<RecentLayoutSet> findAll() {
		return getPersistence().findAll();
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
	public static List<RecentLayoutSet> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
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
	public static List<RecentLayoutSet> findAll(int start, int end,
		OrderByComparator<RecentLayoutSet> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
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
	public static List<RecentLayoutSet> findAll(int start, int end,
		OrderByComparator<RecentLayoutSet> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the recent layout sets from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of recent layout sets.
	*
	* @return the number of recent layout sets
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static RecentLayoutSetPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (RecentLayoutSetPersistence)PortalBeanLocatorUtil.locate(RecentLayoutSetPersistence.class.getName());

			ReferenceRegistry.registerReference(RecentLayoutSetUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setPersistence(RecentLayoutSetPersistence persistence) {
	}

	private static RecentLayoutSetPersistence _persistence;
}