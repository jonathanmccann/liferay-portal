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

package com.liferay.gsportal.core.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link EngagementLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see EngagementLocalService
 * @generated
 */
@ProviderType
public class EngagementLocalServiceWrapper implements EngagementLocalService,
	ServiceWrapper<EngagementLocalService> {
	public EngagementLocalServiceWrapper(
		EngagementLocalService engagementLocalService) {
		_engagementLocalService = engagementLocalService;
	}

	/**
	* Adds the engagement to the database. Also notifies the appropriate model listeners.
	*
	* @param engagement the engagement
	* @return the engagement that was added
	*/
	@Override
	public com.liferay.gsportal.core.model.Engagement addEngagement(
		com.liferay.gsportal.core.model.Engagement engagement) {
		return _engagementLocalService.addEngagement(engagement);
	}

	/**
	* Creates a new engagement with the primary key. Does not add the engagement to the database.
	*
	* @param engagementId the primary key for the new engagement
	* @return the new engagement
	*/
	@Override
	public com.liferay.gsportal.core.model.Engagement createEngagement(
		long engagementId) {
		return _engagementLocalService.createEngagement(engagementId);
	}

	/**
	* Deletes the engagement from the database. Also notifies the appropriate model listeners.
	*
	* @param engagement the engagement
	* @return the engagement that was removed
	*/
	@Override
	public com.liferay.gsportal.core.model.Engagement deleteEngagement(
		com.liferay.gsportal.core.model.Engagement engagement) {
		return _engagementLocalService.deleteEngagement(engagement);
	}

	/**
	* Deletes the engagement with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param engagementId the primary key of the engagement
	* @return the engagement that was removed
	* @throws PortalException if a engagement with the primary key could not be found
	*/
	@Override
	public com.liferay.gsportal.core.model.Engagement deleteEngagement(
		long engagementId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _engagementLocalService.deleteEngagement(engagementId);
	}

	@Override
	public com.liferay.gsportal.core.model.Engagement fetchEngagement(
		long engagementId) {
		return _engagementLocalService.fetchEngagement(engagementId);
	}

	/**
	* Returns the engagement with the primary key.
	*
	* @param engagementId the primary key of the engagement
	* @return the engagement
	* @throws PortalException if a engagement with the primary key could not be found
	*/
	@Override
	public com.liferay.gsportal.core.model.Engagement getEngagement(
		long engagementId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _engagementLocalService.getEngagement(engagementId);
	}

	/**
	* Updates the engagement in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param engagement the engagement
	* @return the engagement that was updated
	*/
	@Override
	public com.liferay.gsportal.core.model.Engagement updateEngagement(
		com.liferay.gsportal.core.model.Engagement engagement) {
		return _engagementLocalService.updateEngagement(engagement);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _engagementLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _engagementLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _engagementLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _engagementLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _engagementLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of engagements.
	*
	* @return the number of engagements
	*/
	@Override
	public int getEngagementsCount() {
		return _engagementLocalService.getEngagementsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _engagementLocalService.getOSGiServiceIdentifier();
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
		return _engagementLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.gsportal.core.model.impl.EngagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _engagementLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.gsportal.core.model.impl.EngagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _engagementLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the engagements.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.gsportal.core.model.impl.EngagementModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of engagements
	* @param end the upper bound of the range of engagements (not inclusive)
	* @return the range of engagements
	*/
	@Override
	public java.util.List<com.liferay.gsportal.core.model.Engagement> getEngagements(
		int start, int end) {
		return _engagementLocalService.getEngagements(start, end);
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
		return _engagementLocalService.dynamicQueryCount(dynamicQuery);
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
		return _engagementLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public EngagementLocalService getWrappedService() {
		return _engagementLocalService;
	}

	@Override
	public void setWrappedService(EngagementLocalService engagementLocalService) {
		_engagementLocalService = engagementLocalService;
	}

	private EngagementLocalService _engagementLocalService;
}