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

import com.liferay.gsportal.core.exception.NoSuchEngagementException;
import com.liferay.gsportal.core.model.Engagement;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the engagement service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.gsportal.core.service.persistence.impl.EngagementPersistenceImpl
 * @see EngagementUtil
 * @generated
 */
@ProviderType
public interface EngagementPersistence extends BasePersistence<Engagement> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link EngagementUtil} to access the engagement persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the engagements where projectId = &#63;.
	*
	* @param projectId the project ID
	* @return the matching engagements
	*/
	public java.util.List<Engagement> findByProjectId(long projectId);

	/**
	* Returns a range of all the engagements where projectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EngagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param projectId the project ID
	* @param start the lower bound of the range of engagements
	* @param end the upper bound of the range of engagements (not inclusive)
	* @return the range of matching engagements
	*/
	public java.util.List<Engagement> findByProjectId(long projectId,
		int start, int end);

	/**
	* Returns an ordered range of all the engagements where projectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EngagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param projectId the project ID
	* @param start the lower bound of the range of engagements
	* @param end the upper bound of the range of engagements (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching engagements
	*/
	public java.util.List<Engagement> findByProjectId(long projectId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Engagement> orderByComparator);

	/**
	* Returns an ordered range of all the engagements where projectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EngagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param projectId the project ID
	* @param start the lower bound of the range of engagements
	* @param end the upper bound of the range of engagements (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching engagements
	*/
	public java.util.List<Engagement> findByProjectId(long projectId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Engagement> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first engagement in the ordered set where projectId = &#63;.
	*
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching engagement
	* @throws NoSuchEngagementException if a matching engagement could not be found
	*/
	public Engagement findByProjectId_First(long projectId,
		com.liferay.portal.kernel.util.OrderByComparator<Engagement> orderByComparator)
		throws NoSuchEngagementException;

	/**
	* Returns the first engagement in the ordered set where projectId = &#63;.
	*
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching engagement, or <code>null</code> if a matching engagement could not be found
	*/
	public Engagement fetchByProjectId_First(long projectId,
		com.liferay.portal.kernel.util.OrderByComparator<Engagement> orderByComparator);

	/**
	* Returns the last engagement in the ordered set where projectId = &#63;.
	*
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching engagement
	* @throws NoSuchEngagementException if a matching engagement could not be found
	*/
	public Engagement findByProjectId_Last(long projectId,
		com.liferay.portal.kernel.util.OrderByComparator<Engagement> orderByComparator)
		throws NoSuchEngagementException;

	/**
	* Returns the last engagement in the ordered set where projectId = &#63;.
	*
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching engagement, or <code>null</code> if a matching engagement could not be found
	*/
	public Engagement fetchByProjectId_Last(long projectId,
		com.liferay.portal.kernel.util.OrderByComparator<Engagement> orderByComparator);

	/**
	* Returns the engagements before and after the current engagement in the ordered set where projectId = &#63;.
	*
	* @param engagementId the primary key of the current engagement
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next engagement
	* @throws NoSuchEngagementException if a engagement with the primary key could not be found
	*/
	public Engagement[] findByProjectId_PrevAndNext(long engagementId,
		long projectId,
		com.liferay.portal.kernel.util.OrderByComparator<Engagement> orderByComparator)
		throws NoSuchEngagementException;

	/**
	* Removes all the engagements where projectId = &#63; from the database.
	*
	* @param projectId the project ID
	*/
	public void removeByProjectId(long projectId);

	/**
	* Returns the number of engagements where projectId = &#63;.
	*
	* @param projectId the project ID
	* @return the number of matching engagements
	*/
	public int countByProjectId(long projectId);

	/**
	* Caches the engagement in the entity cache if it is enabled.
	*
	* @param engagement the engagement
	*/
	public void cacheResult(Engagement engagement);

	/**
	* Caches the engagements in the entity cache if it is enabled.
	*
	* @param engagements the engagements
	*/
	public void cacheResult(java.util.List<Engagement> engagements);

	/**
	* Creates a new engagement with the primary key. Does not add the engagement to the database.
	*
	* @param engagementId the primary key for the new engagement
	* @return the new engagement
	*/
	public Engagement create(long engagementId);

	/**
	* Removes the engagement with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param engagementId the primary key of the engagement
	* @return the engagement that was removed
	* @throws NoSuchEngagementException if a engagement with the primary key could not be found
	*/
	public Engagement remove(long engagementId)
		throws NoSuchEngagementException;

	public Engagement updateImpl(Engagement engagement);

	/**
	* Returns the engagement with the primary key or throws a {@link NoSuchEngagementException} if it could not be found.
	*
	* @param engagementId the primary key of the engagement
	* @return the engagement
	* @throws NoSuchEngagementException if a engagement with the primary key could not be found
	*/
	public Engagement findByPrimaryKey(long engagementId)
		throws NoSuchEngagementException;

	/**
	* Returns the engagement with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param engagementId the primary key of the engagement
	* @return the engagement, or <code>null</code> if a engagement with the primary key could not be found
	*/
	public Engagement fetchByPrimaryKey(long engagementId);

	@Override
	public java.util.Map<java.io.Serializable, Engagement> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the engagements.
	*
	* @return the engagements
	*/
	public java.util.List<Engagement> findAll();

	/**
	* Returns a range of all the engagements.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EngagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of engagements
	* @param end the upper bound of the range of engagements (not inclusive)
	* @return the range of engagements
	*/
	public java.util.List<Engagement> findAll(int start, int end);

	/**
	* Returns an ordered range of all the engagements.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EngagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of engagements
	* @param end the upper bound of the range of engagements (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of engagements
	*/
	public java.util.List<Engagement> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Engagement> orderByComparator);

	/**
	* Returns an ordered range of all the engagements.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EngagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of engagements
	* @param end the upper bound of the range of engagements (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of engagements
	*/
	public java.util.List<Engagement> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Engagement> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the engagements from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of engagements.
	*
	* @return the number of engagements
	*/
	public int countAll();
}