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

package com.liferay.portal.service;

import aQute.bnd.annotation.ProviderType;

/**
 * Provides a wrapper for {@link RecentLayoutSetLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see RecentLayoutSetLocalService
 * @generated
 */
@ProviderType
public class RecentLayoutSetLocalServiceWrapper
	implements RecentLayoutSetLocalService,
		ServiceWrapper<RecentLayoutSetLocalService> {
	public RecentLayoutSetLocalServiceWrapper(
		RecentLayoutSetLocalService recentLayoutSetLocalService) {
		_recentLayoutSetLocalService = recentLayoutSetLocalService;
	}

	/**
	* Adds the recent layout set to the database. Also notifies the appropriate model listeners.
	*
	* @param recentLayoutSet the recent layout set
	* @return the recent layout set that was added
	*/
	@Override
	public com.liferay.portal.model.RecentLayoutSet addRecentLayoutSet(
		com.liferay.portal.model.RecentLayoutSet recentLayoutSet) {
		return _recentLayoutSetLocalService.addRecentLayoutSet(recentLayoutSet);
	}

	/**
	* Creates a new recent layout set with the primary key. Does not add the recent layout set to the database.
	*
	* @param recentLayoutSetId the primary key for the new recent layout set
	* @return the new recent layout set
	*/
	@Override
	public com.liferay.portal.model.RecentLayoutSet createRecentLayoutSet(
		long recentLayoutSetId) {
		return _recentLayoutSetLocalService.createRecentLayoutSet(recentLayoutSetId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.model.PersistedModel deletePersistedModel(
		com.liferay.portal.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _recentLayoutSetLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the recent layout set from the database. Also notifies the appropriate model listeners.
	*
	* @param recentLayoutSet the recent layout set
	* @return the recent layout set that was removed
	*/
	@Override
	public com.liferay.portal.model.RecentLayoutSet deleteRecentLayoutSet(
		com.liferay.portal.model.RecentLayoutSet recentLayoutSet) {
		return _recentLayoutSetLocalService.deleteRecentLayoutSet(recentLayoutSet);
	}

	/**
	* Deletes the recent layout set with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param recentLayoutSetId the primary key of the recent layout set
	* @return the recent layout set that was removed
	* @throws PortalException if a recent layout set with the primary key could not be found
	*/
	@Override
	public com.liferay.portal.model.RecentLayoutSet deleteRecentLayoutSet(
		long recentLayoutSetId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _recentLayoutSetLocalService.deleteRecentLayoutSet(recentLayoutSetId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _recentLayoutSetLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _recentLayoutSetLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.RecentLayoutSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _recentLayoutSetLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.RecentLayoutSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _recentLayoutSetLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _recentLayoutSetLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _recentLayoutSetLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public com.liferay.portal.model.RecentLayoutSet fetchRecentLayoutSet(
		long recentLayoutSetId) {
		return _recentLayoutSetLocalService.fetchRecentLayoutSet(recentLayoutSetId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _recentLayoutSetLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _recentLayoutSetLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _recentLayoutSetLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the recent layout set with the primary key.
	*
	* @param recentLayoutSetId the primary key of the recent layout set
	* @return the recent layout set
	* @throws PortalException if a recent layout set with the primary key could not be found
	*/
	@Override
	public com.liferay.portal.model.RecentLayoutSet getRecentLayoutSet(
		long recentLayoutSetId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _recentLayoutSetLocalService.getRecentLayoutSet(recentLayoutSetId);
	}

	/**
	* Returns a range of all the recent layout sets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.portal.model.impl.RecentLayoutSetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of recent layout sets
	* @param end the upper bound of the range of recent layout sets (not inclusive)
	* @return the range of recent layout sets
	*/
	@Override
	public java.util.List<com.liferay.portal.model.RecentLayoutSet> getRecentLayoutSets(
		int start, int end) {
		return _recentLayoutSetLocalService.getRecentLayoutSets(start, end);
	}

	/**
	* Returns the number of recent layout sets.
	*
	* @return the number of recent layout sets
	*/
	@Override
	public int getRecentLayoutSetsCount() {
		return _recentLayoutSetLocalService.getRecentLayoutSetsCount();
	}

	/**
	* Updates the recent layout set in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param recentLayoutSet the recent layout set
	* @return the recent layout set that was updated
	*/
	@Override
	public com.liferay.portal.model.RecentLayoutSet updateRecentLayoutSet(
		com.liferay.portal.model.RecentLayoutSet recentLayoutSet) {
		return _recentLayoutSetLocalService.updateRecentLayoutSet(recentLayoutSet);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	@Deprecated
	public RecentLayoutSetLocalService getWrappedRecentLayoutSetLocalService() {
		return _recentLayoutSetLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	@Deprecated
	public void setWrappedRecentLayoutSetLocalService(
		RecentLayoutSetLocalService recentLayoutSetLocalService) {
		_recentLayoutSetLocalService = recentLayoutSetLocalService;
	}

	@Override
	public RecentLayoutSetLocalService getWrappedService() {
		return _recentLayoutSetLocalService;
	}

	@Override
	public void setWrappedService(
		RecentLayoutSetLocalService recentLayoutSetLocalService) {
		_recentLayoutSetLocalService = recentLayoutSetLocalService;
	}

	private RecentLayoutSetLocalService _recentLayoutSetLocalService;
}