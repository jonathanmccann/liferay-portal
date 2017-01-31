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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for MemberOf. This utility wraps
 * {@link com.liferay.gsportal.core.service.impl.MemberOfLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see MemberOfLocalService
 * @see com.liferay.gsportal.core.service.base.MemberOfLocalServiceBaseImpl
 * @see com.liferay.gsportal.core.service.impl.MemberOfLocalServiceImpl
 * @generated
 */
@ProviderType
public class MemberOfLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.gsportal.core.service.impl.MemberOfLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the member of to the database. Also notifies the appropriate model listeners.
	*
	* @param memberOf the member of
	* @return the member of that was added
	*/
	public static com.liferay.gsportal.core.model.MemberOf addMemberOf(
		com.liferay.gsportal.core.model.MemberOf memberOf) {
		return getService().addMemberOf(memberOf);
	}

	/**
	* Creates a new member of with the primary key. Does not add the member of to the database.
	*
	* @param memberOfId the primary key for the new member of
	* @return the new member of
	*/
	public static com.liferay.gsportal.core.model.MemberOf createMemberOf(
		long memberOfId) {
		return getService().createMemberOf(memberOfId);
	}

	/**
	* Deletes the member of from the database. Also notifies the appropriate model listeners.
	*
	* @param memberOf the member of
	* @return the member of that was removed
	*/
	public static com.liferay.gsportal.core.model.MemberOf deleteMemberOf(
		com.liferay.gsportal.core.model.MemberOf memberOf) {
		return getService().deleteMemberOf(memberOf);
	}

	/**
	* Deletes the member of with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param memberOfId the primary key of the member of
	* @return the member of that was removed
	* @throws PortalException if a member of with the primary key could not be found
	*/
	public static com.liferay.gsportal.core.model.MemberOf deleteMemberOf(
		long memberOfId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteMemberOf(memberOfId);
	}

	public static com.liferay.gsportal.core.model.MemberOf fetchMemberOf(
		long memberOfId) {
		return getService().fetchMemberOf(memberOfId);
	}

	/**
	* Returns the member of with the primary key.
	*
	* @param memberOfId the primary key of the member of
	* @return the member of
	* @throws PortalException if a member of with the primary key could not be found
	*/
	public static com.liferay.gsportal.core.model.MemberOf getMemberOf(
		long memberOfId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getMemberOf(memberOfId);
	}

	/**
	* Updates the member of in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param memberOf the member of
	* @return the member of that was updated
	*/
	public static com.liferay.gsportal.core.model.MemberOf updateMemberOf(
		com.liferay.gsportal.core.model.MemberOf memberOf) {
		return getService().updateMemberOf(memberOf);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of member ofs.
	*
	* @return the number of member ofs
	*/
	public static int getMemberOfsCount() {
		return getService().getMemberOfsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.gsportal.core.model.impl.MemberOfModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.gsportal.core.model.impl.MemberOfModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns a range of all the member ofs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.gsportal.core.model.impl.MemberOfModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of member ofs
	* @param end the upper bound of the range of member ofs (not inclusive)
	* @return the range of member ofs
	*/
	public static java.util.List<com.liferay.gsportal.core.model.MemberOf> getMemberOfs(
		int start, int end) {
		return getService().getMemberOfs(start, end);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static void setMemberOfs(long clientId, long projectId,
		long engagementId, long[] userIds, int[] memberRoleIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.setMemberOfs(clientId, projectId, engagementId, userIds,
			memberRoleIds);
	}

	public static MemberOfLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<MemberOfLocalService, MemberOfLocalService> _serviceTracker =
		ServiceTrackerFactory.open(MemberOfLocalService.class);
}