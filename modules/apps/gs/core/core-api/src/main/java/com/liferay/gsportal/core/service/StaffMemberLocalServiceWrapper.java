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
 * Provides a wrapper for {@link StaffMemberLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see StaffMemberLocalService
 * @generated
 */
@ProviderType
public class StaffMemberLocalServiceWrapper implements StaffMemberLocalService,
	ServiceWrapper<StaffMemberLocalService> {
	public StaffMemberLocalServiceWrapper(
		StaffMemberLocalService staffMemberLocalService) {
		_staffMemberLocalService = staffMemberLocalService;
	}

	/**
	* Adds the staff member to the database. Also notifies the appropriate model listeners.
	*
	* @param staffMember the staff member
	* @return the staff member that was added
	*/
	@Override
	public com.liferay.gsportal.core.model.StaffMember addStaffMember(
		com.liferay.gsportal.core.model.StaffMember staffMember) {
		return _staffMemberLocalService.addStaffMember(staffMember);
	}

	/**
	* Creates a new staff member with the primary key. Does not add the staff member to the database.
	*
	* @param userId the primary key for the new staff member
	* @return the new staff member
	*/
	@Override
	public com.liferay.gsportal.core.model.StaffMember createStaffMember(
		long userId) {
		return _staffMemberLocalService.createStaffMember(userId);
	}

	/**
	* Deletes the staff member from the database. Also notifies the appropriate model listeners.
	*
	* @param staffMember the staff member
	* @return the staff member that was removed
	*/
	@Override
	public com.liferay.gsportal.core.model.StaffMember deleteStaffMember(
		com.liferay.gsportal.core.model.StaffMember staffMember) {
		return _staffMemberLocalService.deleteStaffMember(staffMember);
	}

	/**
	* Deletes the staff member with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userId the primary key of the staff member
	* @return the staff member that was removed
	* @throws PortalException if a staff member with the primary key could not be found
	* @throws SystemException
	*/
	@Override
	public com.liferay.gsportal.core.model.StaffMember deleteStaffMember(
		long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _staffMemberLocalService.deleteStaffMember(userId);
	}

	@Override
	public com.liferay.gsportal.core.model.StaffMember fetchStaffMember(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return _staffMemberLocalService.fetchStaffMember(userId);
	}

	/**
	* Returns the staff member with the primary key.
	*
	* @param userId the primary key of the staff member
	* @return the staff member
	* @throws PortalException if a staff member with the primary key could not be found
	* @throws SystemException
	*/
	@Override
	public com.liferay.gsportal.core.model.StaffMember getStaffMember(
		long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _staffMemberLocalService.getStaffMember(userId);
	}

	/**
	* Updates the staff member in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param staffMember the staff member
	* @return the staff member that was updated
	*/
	@Override
	public com.liferay.gsportal.core.model.StaffMember updateStaffMember(
		com.liferay.gsportal.core.model.StaffMember staffMember) {
		return _staffMemberLocalService.updateStaffMember(staffMember);
	}

	/**
	* Create or update a Staff Member and associated User
	*
	* For given userId:
	* (1) if staff member found and user not found, throw exception because
	* all staff member should have user
	* (2) if staff member and user found, update staff member and user according
	* to rules: external staff members can be updated in all fields, internal staff
	* members can only have technical skills updated
	* (3) if user not found, create new user
	* (4) if staff member not found, create new staff member
	*/
	@Override
	public com.liferay.gsportal.core.model.StaffMember updateStaffMember(
		long userId, java.lang.String firstName, java.lang.String lastName,
		java.lang.String employerName, java.lang.String jobTitle,
		java.lang.String emailAddress, int employeeType,
		long[] technicalSkillIds,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _staffMemberLocalService.updateStaffMember(userId, firstName,
			lastName, employerName, jobTitle, emailAddress, employeeType,
			technicalSkillIds, serviceContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _staffMemberLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _staffMemberLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _staffMemberLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _staffMemberLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _staffMemberLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.search.Hits search(long companyId,
		java.lang.String searchKeywords,
		java.lang.String engagementTypeFilterWords,
		java.lang.String employerNameFilterWords,
		java.lang.String techAttributeFilterWords)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _staffMemberLocalService.search(companyId, searchKeywords,
			engagementTypeFilterWords, employerNameFilterWords,
			techAttributeFilterWords);
	}

	@Override
	public com.liferay.portal.kernel.search.Hits search(long companyId,
		java.lang.String searchKeywords,
		java.lang.String engagementTypeFilterWords,
		java.lang.String employerNameFilterWords,
		java.lang.String techAttributeFilterWords, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _staffMemberLocalService.search(companyId, searchKeywords,
			engagementTypeFilterWords, employerNameFilterWords,
			techAttributeFilterWords, start, end);
	}

	@Override
	public int getStaffMemberCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _staffMemberLocalService.getStaffMemberCount();
	}

	/**
	* Returns the number of staff members.
	*
	* @return the number of staff members
	*/
	@Override
	public int getStaffMembersCount() {
		return _staffMemberLocalService.getStaffMembersCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _staffMemberLocalService.getOSGiServiceIdentifier();
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
		return _staffMemberLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.gsportal.core.model.impl.StaffMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _staffMemberLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.gsportal.core.model.impl.StaffMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _staffMemberLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.gsportal.core.model.StaffMember> getStaffMembers()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _staffMemberLocalService.getStaffMembers();
	}

	/**
	* Returns a range of all the staff members.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.gsportal.core.model.impl.StaffMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of staff members
	* @param end the upper bound of the range of staff members (not inclusive)
	* @return the range of staff members
	*/
	@Override
	public java.util.List<com.liferay.gsportal.core.model.StaffMember> getStaffMembers(
		int start, int end) {
		return _staffMemberLocalService.getStaffMembers(start, end);
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
		return _staffMemberLocalService.dynamicQueryCount(dynamicQuery);
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
		return _staffMemberLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public StaffMemberLocalService getWrappedService() {
		return _staffMemberLocalService;
	}

	@Override
	public void setWrappedService(
		StaffMemberLocalService staffMemberLocalService) {
		_staffMemberLocalService = staffMemberLocalService;
	}

	private StaffMemberLocalService _staffMemberLocalService;
}