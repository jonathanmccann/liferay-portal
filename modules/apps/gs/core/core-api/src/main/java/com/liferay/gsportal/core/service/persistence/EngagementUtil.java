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

import com.liferay.gsportal.core.model.Engagement;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the engagement service. This utility wraps {@link com.liferay.gsportal.core.service.persistence.impl.EngagementPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EngagementPersistence
 * @see com.liferay.gsportal.core.service.persistence.impl.EngagementPersistenceImpl
 * @generated
 */
@ProviderType
public class EngagementUtil {
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
	public static void clearCache(Engagement engagement) {
		getPersistence().clearCache(engagement);
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
	public static List<Engagement> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Engagement> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Engagement> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Engagement> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Engagement update(Engagement engagement) {
		return getPersistence().update(engagement);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Engagement update(Engagement engagement,
		ServiceContext serviceContext) {
		return getPersistence().update(engagement, serviceContext);
	}

	/**
	* Returns all the engagements where projectId = &#63;.
	*
	* @param projectId the project ID
	* @return the matching engagements
	*/
	public static List<Engagement> findByProjectId(long projectId) {
		return getPersistence().findByProjectId(projectId);
	}

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
	public static List<Engagement> findByProjectId(long projectId, int start,
		int end) {
		return getPersistence().findByProjectId(projectId, start, end);
	}

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
	public static List<Engagement> findByProjectId(long projectId, int start,
		int end, OrderByComparator<Engagement> orderByComparator) {
		return getPersistence()
				   .findByProjectId(projectId, start, end, orderByComparator);
	}

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
	public static List<Engagement> findByProjectId(long projectId, int start,
		int end, OrderByComparator<Engagement> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByProjectId(projectId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first engagement in the ordered set where projectId = &#63;.
	*
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching engagement
	* @throws NoSuchEngagementException if a matching engagement could not be found
	*/
	public static Engagement findByProjectId_First(long projectId,
		OrderByComparator<Engagement> orderByComparator)
		throws com.liferay.gsportal.core.exception.NoSuchEngagementException {
		return getPersistence()
				   .findByProjectId_First(projectId, orderByComparator);
	}

	/**
	* Returns the first engagement in the ordered set where projectId = &#63;.
	*
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching engagement, or <code>null</code> if a matching engagement could not be found
	*/
	public static Engagement fetchByProjectId_First(long projectId,
		OrderByComparator<Engagement> orderByComparator) {
		return getPersistence()
				   .fetchByProjectId_First(projectId, orderByComparator);
	}

	/**
	* Returns the last engagement in the ordered set where projectId = &#63;.
	*
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching engagement
	* @throws NoSuchEngagementException if a matching engagement could not be found
	*/
	public static Engagement findByProjectId_Last(long projectId,
		OrderByComparator<Engagement> orderByComparator)
		throws com.liferay.gsportal.core.exception.NoSuchEngagementException {
		return getPersistence()
				   .findByProjectId_Last(projectId, orderByComparator);
	}

	/**
	* Returns the last engagement in the ordered set where projectId = &#63;.
	*
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching engagement, or <code>null</code> if a matching engagement could not be found
	*/
	public static Engagement fetchByProjectId_Last(long projectId,
		OrderByComparator<Engagement> orderByComparator) {
		return getPersistence()
				   .fetchByProjectId_Last(projectId, orderByComparator);
	}

	/**
	* Returns the engagements before and after the current engagement in the ordered set where projectId = &#63;.
	*
	* @param engagementId the primary key of the current engagement
	* @param projectId the project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next engagement
	* @throws NoSuchEngagementException if a engagement with the primary key could not be found
	*/
	public static Engagement[] findByProjectId_PrevAndNext(long engagementId,
		long projectId, OrderByComparator<Engagement> orderByComparator)
		throws com.liferay.gsportal.core.exception.NoSuchEngagementException {
		return getPersistence()
				   .findByProjectId_PrevAndNext(engagementId, projectId,
			orderByComparator);
	}

	/**
	* Removes all the engagements where projectId = &#63; from the database.
	*
	* @param projectId the project ID
	*/
	public static void removeByProjectId(long projectId) {
		getPersistence().removeByProjectId(projectId);
	}

	/**
	* Returns the number of engagements where projectId = &#63;.
	*
	* @param projectId the project ID
	* @return the number of matching engagements
	*/
	public static int countByProjectId(long projectId) {
		return getPersistence().countByProjectId(projectId);
	}

	/**
	* Caches the engagement in the entity cache if it is enabled.
	*
	* @param engagement the engagement
	*/
	public static void cacheResult(Engagement engagement) {
		getPersistence().cacheResult(engagement);
	}

	/**
	* Caches the engagements in the entity cache if it is enabled.
	*
	* @param engagements the engagements
	*/
	public static void cacheResult(List<Engagement> engagements) {
		getPersistence().cacheResult(engagements);
	}

	/**
	* Creates a new engagement with the primary key. Does not add the engagement to the database.
	*
	* @param engagementId the primary key for the new engagement
	* @return the new engagement
	*/
	public static Engagement create(long engagementId) {
		return getPersistence().create(engagementId);
	}

	/**
	* Removes the engagement with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param engagementId the primary key of the engagement
	* @return the engagement that was removed
	* @throws NoSuchEngagementException if a engagement with the primary key could not be found
	*/
	public static Engagement remove(long engagementId)
		throws com.liferay.gsportal.core.exception.NoSuchEngagementException {
		return getPersistence().remove(engagementId);
	}

	public static Engagement updateImpl(Engagement engagement) {
		return getPersistence().updateImpl(engagement);
	}

	/**
	* Returns the engagement with the primary key or throws a {@link NoSuchEngagementException} if it could not be found.
	*
	* @param engagementId the primary key of the engagement
	* @return the engagement
	* @throws NoSuchEngagementException if a engagement with the primary key could not be found
	*/
	public static Engagement findByPrimaryKey(long engagementId)
		throws com.liferay.gsportal.core.exception.NoSuchEngagementException {
		return getPersistence().findByPrimaryKey(engagementId);
	}

	/**
	* Returns the engagement with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param engagementId the primary key of the engagement
	* @return the engagement, or <code>null</code> if a engagement with the primary key could not be found
	*/
	public static Engagement fetchByPrimaryKey(long engagementId) {
		return getPersistence().fetchByPrimaryKey(engagementId);
	}

	public static java.util.Map<java.io.Serializable, Engagement> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the engagements.
	*
	* @return the engagements
	*/
	public static List<Engagement> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<Engagement> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<Engagement> findAll(int start, int end,
		OrderByComparator<Engagement> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<Engagement> findAll(int start, int end,
		OrderByComparator<Engagement> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the engagements from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of engagements.
	*
	* @return the number of engagements
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static EngagementPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<EngagementPersistence, EngagementPersistence> _serviceTracker =
		ServiceTrackerFactory.open(EngagementPersistence.class);
}