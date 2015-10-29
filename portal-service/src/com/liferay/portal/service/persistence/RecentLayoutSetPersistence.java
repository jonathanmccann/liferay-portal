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

import com.liferay.portal.model.RecentLayoutSet;

/**
 * The persistence interface for the recent layout set service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.service.persistence.impl.RecentLayoutSetPersistenceImpl
 * @see RecentLayoutSetUtil
 * @generated
 */
@ProviderType
public interface RecentLayoutSetPersistence extends BasePersistence<RecentLayoutSet> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link RecentLayoutSetUtil} to access the recent layout set persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the recent layout sets where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching recent layout sets
	*/
	public java.util.List<RecentLayoutSet> findByGroupId(long groupId);

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
	public java.util.List<RecentLayoutSet> findByGroupId(long groupId,
		int start, int end);

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
	public java.util.List<RecentLayoutSet> findByGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RecentLayoutSet> orderByComparator);

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
	public java.util.List<RecentLayoutSet> findByGroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RecentLayoutSet> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first recent layout set in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching recent layout set
	* @throws NoSuchRecentLayoutSetException if a matching recent layout set could not be found
	*/
	public RecentLayoutSet findByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<RecentLayoutSet> orderByComparator)
		throws com.liferay.portal.NoSuchRecentLayoutSetException;

	/**
	* Returns the first recent layout set in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching recent layout set, or <code>null</code> if a matching recent layout set could not be found
	*/
	public RecentLayoutSet fetchByGroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<RecentLayoutSet> orderByComparator);

	/**
	* Returns the last recent layout set in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching recent layout set
	* @throws NoSuchRecentLayoutSetException if a matching recent layout set could not be found
	*/
	public RecentLayoutSet findByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<RecentLayoutSet> orderByComparator)
		throws com.liferay.portal.NoSuchRecentLayoutSetException;

	/**
	* Returns the last recent layout set in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching recent layout set, or <code>null</code> if a matching recent layout set could not be found
	*/
	public RecentLayoutSet fetchByGroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<RecentLayoutSet> orderByComparator);

	/**
	* Returns the recent layout sets before and after the current recent layout set in the ordered set where groupId = &#63;.
	*
	* @param recentLayoutSetId the primary key of the current recent layout set
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next recent layout set
	* @throws NoSuchRecentLayoutSetException if a recent layout set with the primary key could not be found
	*/
	public RecentLayoutSet[] findByGroupId_PrevAndNext(long recentLayoutSetId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<RecentLayoutSet> orderByComparator)
		throws com.liferay.portal.NoSuchRecentLayoutSetException;

	/**
	* Removes all the recent layout sets where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByGroupId(long groupId);

	/**
	* Returns the number of recent layout sets where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching recent layout sets
	*/
	public int countByGroupId(long groupId);

	/**
	* Returns all the recent layout sets where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching recent layout sets
	*/
	public java.util.List<RecentLayoutSet> findByUserId(long userId);

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
	public java.util.List<RecentLayoutSet> findByUserId(long userId, int start,
		int end);

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
	public java.util.List<RecentLayoutSet> findByUserId(long userId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<RecentLayoutSet> orderByComparator);

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
	public java.util.List<RecentLayoutSet> findByUserId(long userId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<RecentLayoutSet> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first recent layout set in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching recent layout set
	* @throws NoSuchRecentLayoutSetException if a matching recent layout set could not be found
	*/
	public RecentLayoutSet findByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<RecentLayoutSet> orderByComparator)
		throws com.liferay.portal.NoSuchRecentLayoutSetException;

	/**
	* Returns the first recent layout set in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching recent layout set, or <code>null</code> if a matching recent layout set could not be found
	*/
	public RecentLayoutSet fetchByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<RecentLayoutSet> orderByComparator);

	/**
	* Returns the last recent layout set in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching recent layout set
	* @throws NoSuchRecentLayoutSetException if a matching recent layout set could not be found
	*/
	public RecentLayoutSet findByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<RecentLayoutSet> orderByComparator)
		throws com.liferay.portal.NoSuchRecentLayoutSetException;

	/**
	* Returns the last recent layout set in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching recent layout set, or <code>null</code> if a matching recent layout set could not be found
	*/
	public RecentLayoutSet fetchByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<RecentLayoutSet> orderByComparator);

	/**
	* Returns the recent layout sets before and after the current recent layout set in the ordered set where userId = &#63;.
	*
	* @param recentLayoutSetId the primary key of the current recent layout set
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next recent layout set
	* @throws NoSuchRecentLayoutSetException if a recent layout set with the primary key could not be found
	*/
	public RecentLayoutSet[] findByUserId_PrevAndNext(long recentLayoutSetId,
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<RecentLayoutSet> orderByComparator)
		throws com.liferay.portal.NoSuchRecentLayoutSetException;

	/**
	* Removes all the recent layout sets where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public void removeByUserId(long userId);

	/**
	* Returns the number of recent layout sets where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching recent layout sets
	*/
	public int countByUserId(long userId);

	/**
	* Returns all the recent layout sets where layoutSetBranchId = &#63;.
	*
	* @param layoutSetBranchId the layout set branch ID
	* @return the matching recent layout sets
	*/
	public java.util.List<RecentLayoutSet> findByLayoutSetBranchId(
		long layoutSetBranchId);

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
	public java.util.List<RecentLayoutSet> findByLayoutSetBranchId(
		long layoutSetBranchId, int start, int end);

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
	public java.util.List<RecentLayoutSet> findByLayoutSetBranchId(
		long layoutSetBranchId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RecentLayoutSet> orderByComparator);

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
	public java.util.List<RecentLayoutSet> findByLayoutSetBranchId(
		long layoutSetBranchId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RecentLayoutSet> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first recent layout set in the ordered set where layoutSetBranchId = &#63;.
	*
	* @param layoutSetBranchId the layout set branch ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching recent layout set
	* @throws NoSuchRecentLayoutSetException if a matching recent layout set could not be found
	*/
	public RecentLayoutSet findByLayoutSetBranchId_First(
		long layoutSetBranchId,
		com.liferay.portal.kernel.util.OrderByComparator<RecentLayoutSet> orderByComparator)
		throws com.liferay.portal.NoSuchRecentLayoutSetException;

	/**
	* Returns the first recent layout set in the ordered set where layoutSetBranchId = &#63;.
	*
	* @param layoutSetBranchId the layout set branch ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching recent layout set, or <code>null</code> if a matching recent layout set could not be found
	*/
	public RecentLayoutSet fetchByLayoutSetBranchId_First(
		long layoutSetBranchId,
		com.liferay.portal.kernel.util.OrderByComparator<RecentLayoutSet> orderByComparator);

	/**
	* Returns the last recent layout set in the ordered set where layoutSetBranchId = &#63;.
	*
	* @param layoutSetBranchId the layout set branch ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching recent layout set
	* @throws NoSuchRecentLayoutSetException if a matching recent layout set could not be found
	*/
	public RecentLayoutSet findByLayoutSetBranchId_Last(
		long layoutSetBranchId,
		com.liferay.portal.kernel.util.OrderByComparator<RecentLayoutSet> orderByComparator)
		throws com.liferay.portal.NoSuchRecentLayoutSetException;

	/**
	* Returns the last recent layout set in the ordered set where layoutSetBranchId = &#63;.
	*
	* @param layoutSetBranchId the layout set branch ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching recent layout set, or <code>null</code> if a matching recent layout set could not be found
	*/
	public RecentLayoutSet fetchByLayoutSetBranchId_Last(
		long layoutSetBranchId,
		com.liferay.portal.kernel.util.OrderByComparator<RecentLayoutSet> orderByComparator);

	/**
	* Returns the recent layout sets before and after the current recent layout set in the ordered set where layoutSetBranchId = &#63;.
	*
	* @param recentLayoutSetId the primary key of the current recent layout set
	* @param layoutSetBranchId the layout set branch ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next recent layout set
	* @throws NoSuchRecentLayoutSetException if a recent layout set with the primary key could not be found
	*/
	public RecentLayoutSet[] findByLayoutSetBranchId_PrevAndNext(
		long recentLayoutSetId, long layoutSetBranchId,
		com.liferay.portal.kernel.util.OrderByComparator<RecentLayoutSet> orderByComparator)
		throws com.liferay.portal.NoSuchRecentLayoutSetException;

	/**
	* Removes all the recent layout sets where layoutSetBranchId = &#63; from the database.
	*
	* @param layoutSetBranchId the layout set branch ID
	*/
	public void removeByLayoutSetBranchId(long layoutSetBranchId);

	/**
	* Returns the number of recent layout sets where layoutSetBranchId = &#63;.
	*
	* @param layoutSetBranchId the layout set branch ID
	* @return the number of matching recent layout sets
	*/
	public int countByLayoutSetBranchId(long layoutSetBranchId);

	/**
	* Returns the recent layout set where userId = &#63; and layoutSetId = &#63; or throws a {@link NoSuchRecentLayoutSetException} if it could not be found.
	*
	* @param userId the user ID
	* @param layoutSetId the layout set ID
	* @return the matching recent layout set
	* @throws NoSuchRecentLayoutSetException if a matching recent layout set could not be found
	*/
	public RecentLayoutSet findByU_L(long userId, long layoutSetId)
		throws com.liferay.portal.NoSuchRecentLayoutSetException;

	/**
	* Returns the recent layout set where userId = &#63; and layoutSetId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param layoutSetId the layout set ID
	* @return the matching recent layout set, or <code>null</code> if a matching recent layout set could not be found
	*/
	public RecentLayoutSet fetchByU_L(long userId, long layoutSetId);

	/**
	* Returns the recent layout set where userId = &#63; and layoutSetId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param layoutSetId the layout set ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching recent layout set, or <code>null</code> if a matching recent layout set could not be found
	*/
	public RecentLayoutSet fetchByU_L(long userId, long layoutSetId,
		boolean retrieveFromCache);

	/**
	* Removes the recent layout set where userId = &#63; and layoutSetId = &#63; from the database.
	*
	* @param userId the user ID
	* @param layoutSetId the layout set ID
	* @return the recent layout set that was removed
	*/
	public RecentLayoutSet removeByU_L(long userId, long layoutSetId)
		throws com.liferay.portal.NoSuchRecentLayoutSetException;

	/**
	* Returns the number of recent layout sets where userId = &#63; and layoutSetId = &#63;.
	*
	* @param userId the user ID
	* @param layoutSetId the layout set ID
	* @return the number of matching recent layout sets
	*/
	public int countByU_L(long userId, long layoutSetId);

	/**
	* Caches the recent layout set in the entity cache if it is enabled.
	*
	* @param recentLayoutSet the recent layout set
	*/
	public void cacheResult(RecentLayoutSet recentLayoutSet);

	/**
	* Caches the recent layout sets in the entity cache if it is enabled.
	*
	* @param recentLayoutSets the recent layout sets
	*/
	public void cacheResult(java.util.List<RecentLayoutSet> recentLayoutSets);

	/**
	* Creates a new recent layout set with the primary key. Does not add the recent layout set to the database.
	*
	* @param recentLayoutSetId the primary key for the new recent layout set
	* @return the new recent layout set
	*/
	public RecentLayoutSet create(long recentLayoutSetId);

	/**
	* Removes the recent layout set with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param recentLayoutSetId the primary key of the recent layout set
	* @return the recent layout set that was removed
	* @throws NoSuchRecentLayoutSetException if a recent layout set with the primary key could not be found
	*/
	public RecentLayoutSet remove(long recentLayoutSetId)
		throws com.liferay.portal.NoSuchRecentLayoutSetException;

	public RecentLayoutSet updateImpl(RecentLayoutSet recentLayoutSet);

	/**
	* Returns the recent layout set with the primary key or throws a {@link NoSuchRecentLayoutSetException} if it could not be found.
	*
	* @param recentLayoutSetId the primary key of the recent layout set
	* @return the recent layout set
	* @throws NoSuchRecentLayoutSetException if a recent layout set with the primary key could not be found
	*/
	public RecentLayoutSet findByPrimaryKey(long recentLayoutSetId)
		throws com.liferay.portal.NoSuchRecentLayoutSetException;

	/**
	* Returns the recent layout set with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param recentLayoutSetId the primary key of the recent layout set
	* @return the recent layout set, or <code>null</code> if a recent layout set with the primary key could not be found
	*/
	public RecentLayoutSet fetchByPrimaryKey(long recentLayoutSetId);

	@Override
	public java.util.Map<java.io.Serializable, RecentLayoutSet> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the recent layout sets.
	*
	* @return the recent layout sets
	*/
	public java.util.List<RecentLayoutSet> findAll();

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
	public java.util.List<RecentLayoutSet> findAll(int start, int end);

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
	public java.util.List<RecentLayoutSet> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RecentLayoutSet> orderByComparator);

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
	public java.util.List<RecentLayoutSet> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<RecentLayoutSet> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the recent layout sets from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of recent layout sets.
	*
	* @return the number of recent layout sets
	*/
	public int countAll();
}