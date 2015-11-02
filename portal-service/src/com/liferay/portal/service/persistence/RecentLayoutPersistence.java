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

import com.liferay.portal.model.RecentLayout;

/**
 * The persistence interface for the recent layout service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.service.persistence.impl.RecentLayoutPersistenceImpl
 * @see RecentLayoutUtil
 * @generated
 */
@ProviderType
public interface RecentLayoutPersistence extends BasePersistence<RecentLayout> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link RecentLayoutUtil} to access the recent layout persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the recent layouts where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching recent layouts
	*/
	public java.util.List<RecentLayout> findByGroupId(long groupId);

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
	public java.util.List<RecentLayout> findByGroupId(long groupId, int start,
		int end);

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
	public java.util.List<RecentLayout> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<RecentLayout> orderByComparator);

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
	public java.util.List<RecentLayout> findByGroupId(long groupId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<RecentLayout> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first recent layout in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching recent layout
	* @throws NoSuchRecentLayoutException if a matching recent layout could not be found
	*/
	public RecentLayout findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<RecentLayout> orderByComparator)
		throws com.liferay.portal.NoSuchRecentLayoutException;

	/**
	* Returns the first recent layout in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching recent layout, or <code>null</code> if a matching recent layout could not be found
	*/
	public RecentLayout fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<RecentLayout> orderByComparator);

	/**
	* Returns the last recent layout in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching recent layout
	* @throws NoSuchRecentLayoutException if a matching recent layout could not be found
	*/
	public RecentLayout findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<RecentLayout> orderByComparator)
		throws com.liferay.portal.NoSuchRecentLayoutException;

	/**
	* Returns the last recent layout in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching recent layout, or <code>null</code> if a matching recent layout could not be found
	*/
	public RecentLayout fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<RecentLayout> orderByComparator);

	/**
	* Returns the recent layouts before and after the current recent layout in the ordered set where groupId = &#63;.
	*
	* @param recentLayoutId the primary key of the current recent layout
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next recent layout
	* @throws NoSuchRecentLayoutException if a recent layout with the primary key could not be found
	*/
	public RecentLayout[] findByGroupId_PrevAndNext(long recentLayoutId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<RecentLayout> orderByComparator)
		throws com.liferay.portal.NoSuchRecentLayoutException;

	/**
	* Removes all the recent layouts where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of recent layouts where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching recent layouts
	*/
	public int countByGroupId(long groupId);

	/**
	* Returns all the recent layouts where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching recent layouts
	*/
	public java.util.List<RecentLayout> findByUserId(long userId);

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
	public java.util.List<RecentLayout> findByUserId(long userId, int start,
		int end);

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
	public java.util.List<RecentLayout> findByUserId(long userId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<RecentLayout> orderByComparator);

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
	public java.util.List<RecentLayout> findByUserId(long userId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<RecentLayout> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first recent layout in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching recent layout
	* @throws NoSuchRecentLayoutException if a matching recent layout could not be found
	*/
	public RecentLayout findByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<RecentLayout> orderByComparator)
		throws com.liferay.portal.NoSuchRecentLayoutException;

	/**
	* Returns the first recent layout in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching recent layout, or <code>null</code> if a matching recent layout could not be found
	*/
	public RecentLayout fetchByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<RecentLayout> orderByComparator);

	/**
	* Returns the last recent layout in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching recent layout
	* @throws NoSuchRecentLayoutException if a matching recent layout could not be found
	*/
	public RecentLayout findByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<RecentLayout> orderByComparator)
		throws com.liferay.portal.NoSuchRecentLayoutException;

	/**
	* Returns the last recent layout in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching recent layout, or <code>null</code> if a matching recent layout could not be found
	*/
	public RecentLayout fetchByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<RecentLayout> orderByComparator);

	/**
	* Returns the recent layouts before and after the current recent layout in the ordered set where userId = &#63;.
	*
	* @param recentLayoutId the primary key of the current recent layout
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next recent layout
	* @throws NoSuchRecentLayoutException if a recent layout with the primary key could not be found
	*/
	public RecentLayout[] findByUserId_PrevAndNext(long recentLayoutId,
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<RecentLayout> orderByComparator)
		throws com.liferay.portal.NoSuchRecentLayoutException;

	/**
	* Removes all the recent layouts where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public void removeByUserId(long userId);

	/**
	* Returns the number of recent layouts where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching recent layouts
	*/
	public int countByUserId(long userId);

	/**
	* Returns all the recent layouts where layoutBranchId = &#63;.
	*
	* @param layoutBranchId the layout branch ID
	* @return the matching recent layouts
	*/
	public java.util.List<RecentLayout> findByLayoutBranchId(
		long layoutBranchId);

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
	public java.util.List<RecentLayout> findByLayoutBranchId(
		long layoutBranchId, int start, int end);

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
	public java.util.List<RecentLayout> findByLayoutBranchId(
		long layoutBranchId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RecentLayout> orderByComparator);

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
	public java.util.List<RecentLayout> findByLayoutBranchId(
		long layoutBranchId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RecentLayout> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first recent layout in the ordered set where layoutBranchId = &#63;.
	*
	* @param layoutBranchId the layout branch ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching recent layout
	* @throws NoSuchRecentLayoutException if a matching recent layout could not be found
	*/
	public RecentLayout findByLayoutBranchId_First(long layoutBranchId,
		com.liferay.portal.kernel.util.OrderByComparator<RecentLayout> orderByComparator)
		throws com.liferay.portal.NoSuchRecentLayoutException;

	/**
	* Returns the first recent layout in the ordered set where layoutBranchId = &#63;.
	*
	* @param layoutBranchId the layout branch ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching recent layout, or <code>null</code> if a matching recent layout could not be found
	*/
	public RecentLayout fetchByLayoutBranchId_First(long layoutBranchId,
		com.liferay.portal.kernel.util.OrderByComparator<RecentLayout> orderByComparator);

	/**
	* Returns the last recent layout in the ordered set where layoutBranchId = &#63;.
	*
	* @param layoutBranchId the layout branch ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching recent layout
	* @throws NoSuchRecentLayoutException if a matching recent layout could not be found
	*/
	public RecentLayout findByLayoutBranchId_Last(long layoutBranchId,
		com.liferay.portal.kernel.util.OrderByComparator<RecentLayout> orderByComparator)
		throws com.liferay.portal.NoSuchRecentLayoutException;

	/**
	* Returns the last recent layout in the ordered set where layoutBranchId = &#63;.
	*
	* @param layoutBranchId the layout branch ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching recent layout, or <code>null</code> if a matching recent layout could not be found
	*/
	public RecentLayout fetchByLayoutBranchId_Last(long layoutBranchId,
		com.liferay.portal.kernel.util.OrderByComparator<RecentLayout> orderByComparator);

	/**
	* Returns the recent layouts before and after the current recent layout in the ordered set where layoutBranchId = &#63;.
	*
	* @param recentLayoutId the primary key of the current recent layout
	* @param layoutBranchId the layout branch ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next recent layout
	* @throws NoSuchRecentLayoutException if a recent layout with the primary key could not be found
	*/
	public RecentLayout[] findByLayoutBranchId_PrevAndNext(
		long recentLayoutId, long layoutBranchId,
		com.liferay.portal.kernel.util.OrderByComparator<RecentLayout> orderByComparator)
		throws com.liferay.portal.NoSuchRecentLayoutException;

	/**
	* Removes all the recent layouts where layoutBranchId = &#63; from the database.
	*
	* @param layoutBranchId the layout branch ID
	*/
	public void removeByLayoutBranchId(long layoutBranchId);

	/**
	* Returns the number of recent layouts where layoutBranchId = &#63;.
	*
	* @param layoutBranchId the layout branch ID
	* @return the number of matching recent layouts
	*/
	public int countByLayoutBranchId(long layoutBranchId);

	/**
	* Returns the recent layout where userId = &#63; and layoutSetBranchId = &#63; and plid = &#63; or throws a {@link NoSuchRecentLayoutException} if it could not be found.
	*
	* @param userId the user ID
	* @param layoutSetBranchId the layout set branch ID
	* @param plid the plid
	* @return the matching recent layout
	* @throws NoSuchRecentLayoutException if a matching recent layout could not be found
	*/
	public RecentLayout findByU_L_P(long userId, long layoutSetBranchId,
		long plid) throws com.liferay.portal.NoSuchRecentLayoutException;

	/**
	* Returns the recent layout where userId = &#63; and layoutSetBranchId = &#63; and plid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param layoutSetBranchId the layout set branch ID
	* @param plid the plid
	* @return the matching recent layout, or <code>null</code> if a matching recent layout could not be found
	*/
	public RecentLayout fetchByU_L_P(long userId, long layoutSetBranchId,
		long plid);

	/**
	* Returns the recent layout where userId = &#63; and layoutSetBranchId = &#63; and plid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param layoutSetBranchId the layout set branch ID
	* @param plid the plid
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching recent layout, or <code>null</code> if a matching recent layout could not be found
	*/
	public RecentLayout fetchByU_L_P(long userId, long layoutSetBranchId,
		long plid, boolean retrieveFromCache);

	/**
	* Removes the recent layout where userId = &#63; and layoutSetBranchId = &#63; and plid = &#63; from the database.
	*
	* @param userId the user ID
	* @param layoutSetBranchId the layout set branch ID
	* @param plid the plid
	* @return the recent layout that was removed
	*/
	public RecentLayout removeByU_L_P(long userId, long layoutSetBranchId,
		long plid) throws com.liferay.portal.NoSuchRecentLayoutException;

	/**
	* Returns the number of recent layouts where userId = &#63; and layoutSetBranchId = &#63; and plid = &#63;.
	*
	* @param userId the user ID
	* @param layoutSetBranchId the layout set branch ID
	* @param plid the plid
	* @return the number of matching recent layouts
	*/
	public int countByU_L_P(long userId, long layoutSetBranchId, long plid);

	/**
	* Caches the recent layout in the entity cache if it is enabled.
	*
	* @param recentLayout the recent layout
	*/
	public void cacheResult(RecentLayout recentLayout);

	/**
	* Caches the recent layouts in the entity cache if it is enabled.
	*
	* @param recentLayouts the recent layouts
	*/
	public void cacheResult(java.util.List<RecentLayout> recentLayouts);

	/**
	* Creates a new recent layout with the primary key. Does not add the recent layout to the database.
	*
	* @param recentLayoutId the primary key for the new recent layout
	* @return the new recent layout
	*/
	public RecentLayout create(long recentLayoutId);

	/**
	* Removes the recent layout with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param recentLayoutId the primary key of the recent layout
	* @return the recent layout that was removed
	* @throws NoSuchRecentLayoutException if a recent layout with the primary key could not be found
	*/
	public RecentLayout remove(long recentLayoutId)
		throws com.liferay.portal.NoSuchRecentLayoutException;

	public RecentLayout updateImpl(RecentLayout recentLayout);

	/**
	* Returns the recent layout with the primary key or throws a {@link NoSuchRecentLayoutException} if it could not be found.
	*
	* @param recentLayoutId the primary key of the recent layout
	* @return the recent layout
	* @throws NoSuchRecentLayoutException if a recent layout with the primary key could not be found
	*/
	public RecentLayout findByPrimaryKey(long recentLayoutId)
		throws com.liferay.portal.NoSuchRecentLayoutException;

	/**
	* Returns the recent layout with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param recentLayoutId the primary key of the recent layout
	* @return the recent layout, or <code>null</code> if a recent layout with the primary key could not be found
	*/
	public RecentLayout fetchByPrimaryKey(long recentLayoutId);

	@Override
	public java.util.Map<java.io.Serializable, RecentLayout> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the recent layouts.
	*
	* @return the recent layouts
	*/
	public java.util.List<RecentLayout> findAll();

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
	public java.util.List<RecentLayout> findAll(int start, int end);

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
	public java.util.List<RecentLayout> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RecentLayout> orderByComparator);

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
	public java.util.List<RecentLayout> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RecentLayout> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the recent layouts from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of recent layouts.
	*
	* @return the number of recent layouts
	*/
	public int countAll();
}