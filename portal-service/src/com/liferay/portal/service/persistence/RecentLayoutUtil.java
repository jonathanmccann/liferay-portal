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
import com.liferay.portal.model.RecentLayout;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the recent layout service. This utility wraps {@link com.liferay.portal.service.persistence.impl.RecentLayoutPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RecentLayoutPersistence
 * @see com.liferay.portal.service.persistence.impl.RecentLayoutPersistenceImpl
 * @generated
 */
@ProviderType
public class RecentLayoutUtil {
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
	public static void clearCache(RecentLayout recentLayout) {
		getPersistence().clearCache(recentLayout);
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
	public static List<RecentLayout> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<RecentLayout> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<RecentLayout> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<RecentLayout> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static RecentLayout update(RecentLayout recentLayout) {
		return getPersistence().update(recentLayout);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static RecentLayout update(RecentLayout recentLayout,
		ServiceContext serviceContext) {
		return getPersistence().update(recentLayout, serviceContext);
	}

	/**
	* Returns all the recent layouts where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching recent layouts
	*/
	public static List<RecentLayout> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
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
	public static List<RecentLayout> findByGroupId(long groupId, int start,
		int end) {
		return getPersistence().findByGroupId(groupId, start, end);
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
	public static List<RecentLayout> findByGroupId(long groupId, int start,
		int end, OrderByComparator<RecentLayout> orderByComparator) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
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
	public static List<RecentLayout> findByGroupId(long groupId, int start,
		int end, OrderByComparator<RecentLayout> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first recent layout in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching recent layout
	* @throws NoSuchRecentLayoutException if a matching recent layout could not be found
	*/
	public static RecentLayout findByGroupId_First(long groupId,
		OrderByComparator<RecentLayout> orderByComparator)
		throws com.liferay.portal.NoSuchRecentLayoutException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the first recent layout in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching recent layout, or <code>null</code> if a matching recent layout could not be found
	*/
	public static RecentLayout fetchByGroupId_First(long groupId,
		OrderByComparator<RecentLayout> orderByComparator) {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last recent layout in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching recent layout
	* @throws NoSuchRecentLayoutException if a matching recent layout could not be found
	*/
	public static RecentLayout findByGroupId_Last(long groupId,
		OrderByComparator<RecentLayout> orderByComparator)
		throws com.liferay.portal.NoSuchRecentLayoutException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last recent layout in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching recent layout, or <code>null</code> if a matching recent layout could not be found
	*/
	public static RecentLayout fetchByGroupId_Last(long groupId,
		OrderByComparator<RecentLayout> orderByComparator) {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
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
	public static RecentLayout[] findByGroupId_PrevAndNext(
		long recentLayoutId, long groupId,
		OrderByComparator<RecentLayout> orderByComparator)
		throws com.liferay.portal.NoSuchRecentLayoutException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(recentLayoutId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the recent layouts where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Returns the number of recent layouts where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching recent layouts
	*/
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns all the recent layouts where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching recent layouts
	*/
	public static List<RecentLayout> findByUserId(long userId) {
		return getPersistence().findByUserId(userId);
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
	public static List<RecentLayout> findByUserId(long userId, int start,
		int end) {
		return getPersistence().findByUserId(userId, start, end);
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
	public static List<RecentLayout> findByUserId(long userId, int start,
		int end, OrderByComparator<RecentLayout> orderByComparator) {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator);
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
	public static List<RecentLayout> findByUserId(long userId, int start,
		int end, OrderByComparator<RecentLayout> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first recent layout in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching recent layout
	* @throws NoSuchRecentLayoutException if a matching recent layout could not be found
	*/
	public static RecentLayout findByUserId_First(long userId,
		OrderByComparator<RecentLayout> orderByComparator)
		throws com.liferay.portal.NoSuchRecentLayoutException {
		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the first recent layout in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching recent layout, or <code>null</code> if a matching recent layout could not be found
	*/
	public static RecentLayout fetchByUserId_First(long userId,
		OrderByComparator<RecentLayout> orderByComparator) {
		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the last recent layout in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching recent layout
	* @throws NoSuchRecentLayoutException if a matching recent layout could not be found
	*/
	public static RecentLayout findByUserId_Last(long userId,
		OrderByComparator<RecentLayout> orderByComparator)
		throws com.liferay.portal.NoSuchRecentLayoutException {
		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the last recent layout in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching recent layout, or <code>null</code> if a matching recent layout could not be found
	*/
	public static RecentLayout fetchByUserId_Last(long userId,
		OrderByComparator<RecentLayout> orderByComparator) {
		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
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
	public static RecentLayout[] findByUserId_PrevAndNext(long recentLayoutId,
		long userId, OrderByComparator<RecentLayout> orderByComparator)
		throws com.liferay.portal.NoSuchRecentLayoutException {
		return getPersistence()
				   .findByUserId_PrevAndNext(recentLayoutId, userId,
			orderByComparator);
	}

	/**
	* Removes all the recent layouts where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public static void removeByUserId(long userId) {
		getPersistence().removeByUserId(userId);
	}

	/**
	* Returns the number of recent layouts where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching recent layouts
	*/
	public static int countByUserId(long userId) {
		return getPersistence().countByUserId(userId);
	}

	/**
	* Returns all the recent layouts where layoutBranchId = &#63;.
	*
	* @param layoutBranchId the layout branch ID
	* @return the matching recent layouts
	*/
	public static List<RecentLayout> findByLayoutBranchId(long layoutBranchId) {
		return getPersistence().findByLayoutBranchId(layoutBranchId);
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
	public static List<RecentLayout> findByLayoutBranchId(long layoutBranchId,
		int start, int end) {
		return getPersistence().findByLayoutBranchId(layoutBranchId, start, end);
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
	public static List<RecentLayout> findByLayoutBranchId(long layoutBranchId,
		int start, int end, OrderByComparator<RecentLayout> orderByComparator) {
		return getPersistence()
				   .findByLayoutBranchId(layoutBranchId, start, end,
			orderByComparator);
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
	public static List<RecentLayout> findByLayoutBranchId(long layoutBranchId,
		int start, int end, OrderByComparator<RecentLayout> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByLayoutBranchId(layoutBranchId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first recent layout in the ordered set where layoutBranchId = &#63;.
	*
	* @param layoutBranchId the layout branch ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching recent layout
	* @throws NoSuchRecentLayoutException if a matching recent layout could not be found
	*/
	public static RecentLayout findByLayoutBranchId_First(long layoutBranchId,
		OrderByComparator<RecentLayout> orderByComparator)
		throws com.liferay.portal.NoSuchRecentLayoutException {
		return getPersistence()
				   .findByLayoutBranchId_First(layoutBranchId, orderByComparator);
	}

	/**
	* Returns the first recent layout in the ordered set where layoutBranchId = &#63;.
	*
	* @param layoutBranchId the layout branch ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching recent layout, or <code>null</code> if a matching recent layout could not be found
	*/
	public static RecentLayout fetchByLayoutBranchId_First(
		long layoutBranchId, OrderByComparator<RecentLayout> orderByComparator) {
		return getPersistence()
				   .fetchByLayoutBranchId_First(layoutBranchId,
			orderByComparator);
	}

	/**
	* Returns the last recent layout in the ordered set where layoutBranchId = &#63;.
	*
	* @param layoutBranchId the layout branch ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching recent layout
	* @throws NoSuchRecentLayoutException if a matching recent layout could not be found
	*/
	public static RecentLayout findByLayoutBranchId_Last(long layoutBranchId,
		OrderByComparator<RecentLayout> orderByComparator)
		throws com.liferay.portal.NoSuchRecentLayoutException {
		return getPersistence()
				   .findByLayoutBranchId_Last(layoutBranchId, orderByComparator);
	}

	/**
	* Returns the last recent layout in the ordered set where layoutBranchId = &#63;.
	*
	* @param layoutBranchId the layout branch ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching recent layout, or <code>null</code> if a matching recent layout could not be found
	*/
	public static RecentLayout fetchByLayoutBranchId_Last(long layoutBranchId,
		OrderByComparator<RecentLayout> orderByComparator) {
		return getPersistence()
				   .fetchByLayoutBranchId_Last(layoutBranchId, orderByComparator);
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
	public static RecentLayout[] findByLayoutBranchId_PrevAndNext(
		long recentLayoutId, long layoutBranchId,
		OrderByComparator<RecentLayout> orderByComparator)
		throws com.liferay.portal.NoSuchRecentLayoutException {
		return getPersistence()
				   .findByLayoutBranchId_PrevAndNext(recentLayoutId,
			layoutBranchId, orderByComparator);
	}

	/**
	* Removes all the recent layouts where layoutBranchId = &#63; from the database.
	*
	* @param layoutBranchId the layout branch ID
	*/
	public static void removeByLayoutBranchId(long layoutBranchId) {
		getPersistence().removeByLayoutBranchId(layoutBranchId);
	}

	/**
	* Returns the number of recent layouts where layoutBranchId = &#63;.
	*
	* @param layoutBranchId the layout branch ID
	* @return the number of matching recent layouts
	*/
	public static int countByLayoutBranchId(long layoutBranchId) {
		return getPersistence().countByLayoutBranchId(layoutBranchId);
	}

	/**
	* Returns the recent layout where userId = &#63; and layoutSetBranchId = &#63; and plid = &#63; or throws a {@link NoSuchRecentLayoutException} if it could not be found.
	*
	* @param userId the user ID
	* @param layoutSetBranchId the layout set branch ID
	* @param plid the plid
	* @return the matching recent layout
	* @throws NoSuchRecentLayoutException if a matching recent layout could not be found
	*/
	public static RecentLayout findByU_L_P(long userId, long layoutSetBranchId,
		long plid) throws com.liferay.portal.NoSuchRecentLayoutException {
		return getPersistence().findByU_L_P(userId, layoutSetBranchId, plid);
	}

	/**
	* Returns the recent layout where userId = &#63; and layoutSetBranchId = &#63; and plid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param layoutSetBranchId the layout set branch ID
	* @param plid the plid
	* @return the matching recent layout, or <code>null</code> if a matching recent layout could not be found
	*/
	public static RecentLayout fetchByU_L_P(long userId,
		long layoutSetBranchId, long plid) {
		return getPersistence().fetchByU_L_P(userId, layoutSetBranchId, plid);
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
	public static RecentLayout fetchByU_L_P(long userId,
		long layoutSetBranchId, long plid, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByU_L_P(userId, layoutSetBranchId, plid,
			retrieveFromCache);
	}

	/**
	* Removes the recent layout where userId = &#63; and layoutSetBranchId = &#63; and plid = &#63; from the database.
	*
	* @param userId the user ID
	* @param layoutSetBranchId the layout set branch ID
	* @param plid the plid
	* @return the recent layout that was removed
	*/
	public static RecentLayout removeByU_L_P(long userId,
		long layoutSetBranchId, long plid)
		throws com.liferay.portal.NoSuchRecentLayoutException {
		return getPersistence().removeByU_L_P(userId, layoutSetBranchId, plid);
	}

	/**
	* Returns the number of recent layouts where userId = &#63; and layoutSetBranchId = &#63; and plid = &#63;.
	*
	* @param userId the user ID
	* @param layoutSetBranchId the layout set branch ID
	* @param plid the plid
	* @return the number of matching recent layouts
	*/
	public static int countByU_L_P(long userId, long layoutSetBranchId,
		long plid) {
		return getPersistence().countByU_L_P(userId, layoutSetBranchId, plid);
	}

	/**
	* Caches the recent layout in the entity cache if it is enabled.
	*
	* @param recentLayout the recent layout
	*/
	public static void cacheResult(RecentLayout recentLayout) {
		getPersistence().cacheResult(recentLayout);
	}

	/**
	* Caches the recent layouts in the entity cache if it is enabled.
	*
	* @param recentLayouts the recent layouts
	*/
	public static void cacheResult(List<RecentLayout> recentLayouts) {
		getPersistence().cacheResult(recentLayouts);
	}

	/**
	* Creates a new recent layout with the primary key. Does not add the recent layout to the database.
	*
	* @param recentLayoutId the primary key for the new recent layout
	* @return the new recent layout
	*/
	public static RecentLayout create(long recentLayoutId) {
		return getPersistence().create(recentLayoutId);
	}

	/**
	* Removes the recent layout with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param recentLayoutId the primary key of the recent layout
	* @return the recent layout that was removed
	* @throws NoSuchRecentLayoutException if a recent layout with the primary key could not be found
	*/
	public static RecentLayout remove(long recentLayoutId)
		throws com.liferay.portal.NoSuchRecentLayoutException {
		return getPersistence().remove(recentLayoutId);
	}

	public static RecentLayout updateImpl(RecentLayout recentLayout) {
		return getPersistence().updateImpl(recentLayout);
	}

	/**
	* Returns the recent layout with the primary key or throws a {@link NoSuchRecentLayoutException} if it could not be found.
	*
	* @param recentLayoutId the primary key of the recent layout
	* @return the recent layout
	* @throws NoSuchRecentLayoutException if a recent layout with the primary key could not be found
	*/
	public static RecentLayout findByPrimaryKey(long recentLayoutId)
		throws com.liferay.portal.NoSuchRecentLayoutException {
		return getPersistence().findByPrimaryKey(recentLayoutId);
	}

	/**
	* Returns the recent layout with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param recentLayoutId the primary key of the recent layout
	* @return the recent layout, or <code>null</code> if a recent layout with the primary key could not be found
	*/
	public static RecentLayout fetchByPrimaryKey(long recentLayoutId) {
		return getPersistence().fetchByPrimaryKey(recentLayoutId);
	}

	public static java.util.Map<java.io.Serializable, RecentLayout> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the recent layouts.
	*
	* @return the recent layouts
	*/
	public static List<RecentLayout> findAll() {
		return getPersistence().findAll();
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
	public static List<RecentLayout> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
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
	public static List<RecentLayout> findAll(int start, int end,
		OrderByComparator<RecentLayout> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
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
	public static List<RecentLayout> findAll(int start, int end,
		OrderByComparator<RecentLayout> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the recent layouts from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of recent layouts.
	*
	* @return the number of recent layouts
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static RecentLayoutPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (RecentLayoutPersistence)PortalBeanLocatorUtil.locate(RecentLayoutPersistence.class.getName());

			ReferenceRegistry.registerReference(RecentLayoutUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setPersistence(RecentLayoutPersistence persistence) {
	}

	private static RecentLayoutPersistence _persistence;
}