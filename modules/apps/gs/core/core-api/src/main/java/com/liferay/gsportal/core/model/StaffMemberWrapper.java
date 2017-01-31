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

package com.liferay.gsportal.core.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link StaffMember}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see StaffMember
 * @generated
 */
@ProviderType
public class StaffMemberWrapper implements StaffMember,
	ModelWrapper<StaffMember> {
	public StaffMemberWrapper(StaffMember staffMember) {
		_staffMember = staffMember;
	}

	@Override
	public Class<?> getModelClass() {
		return StaffMember.class;
	}

	@Override
	public String getModelClassName() {
		return StaffMember.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("userId", getUserId());
		attributes.put("companyId", getCompanyId());
		attributes.put("employeeType", getEmployeeType());
		attributes.put("employerName", getEmployerName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Integer employeeType = (Integer)attributes.get("employeeType");

		if (employeeType != null) {
			setEmployeeType(employeeType);
		}

		String employerName = (String)attributes.get("employerName");

		if (employerName != null) {
			setEmployerName(employerName);
		}
	}

	@Override
	public StaffMember toEscapedModel() {
		return new StaffMemberWrapper(_staffMember.toEscapedModel());
	}

	@Override
	public StaffMember toUnescapedModel() {
		return new StaffMemberWrapper(_staffMember.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _staffMember.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _staffMember.isEscapedModel();
	}

	@Override
	public boolean isInternal() {
		return _staffMember.isInternal();
	}

	@Override
	public boolean isNew() {
		return _staffMember.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _staffMember.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<StaffMember> toCacheModel() {
		return _staffMember.toCacheModel();
	}

	@Override
	public com.liferay.portal.kernel.model.User getUser()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _staffMember.getUser();
	}

	@Override
	public int compareTo(StaffMember staffMember) {
		return _staffMember.compareTo(staffMember);
	}

	/**
	* Returns the employee type of this staff member.
	*
	* @return the employee type of this staff member
	*/
	@Override
	public int getEmployeeType() {
		return _staffMember.getEmployeeType();
	}

	@Override
	public int hashCode() {
		return _staffMember.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _staffMember.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new StaffMemberWrapper((StaffMember)_staffMember.clone());
	}

	@Override
	public java.lang.String getEmployeeTypeName() {
		return _staffMember.getEmployeeTypeName();
	}

	/**
	* Returns the employer name of this staff member.
	*
	* @return the employer name of this staff member
	*/
	@Override
	public java.lang.String getEmployerName() {
		return _staffMember.getEmployerName();
	}

	/**
	* Returns the user uuid of this staff member.
	*
	* @return the user uuid of this staff member
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _staffMember.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _staffMember.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _staffMember.toXmlString();
	}

	/**
	* Returns the company ID of this staff member.
	*
	* @return the company ID of this staff member
	*/
	@Override
	public long getCompanyId() {
		return _staffMember.getCompanyId();
	}

	/**
	* Returns the primary key of this staff member.
	*
	* @return the primary key of this staff member
	*/
	@Override
	public long getPrimaryKey() {
		return _staffMember.getPrimaryKey();
	}

	/**
	* Returns the user ID of this staff member.
	*
	* @return the user ID of this staff member
	*/
	@Override
	public long getUserId() {
		return _staffMember.getUserId();
	}

	@Override
	public void persist() {
		_staffMember.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_staffMember.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this staff member.
	*
	* @param companyId the company ID of this staff member
	*/
	@Override
	public void setCompanyId(long companyId) {
		_staffMember.setCompanyId(companyId);
	}

	/**
	* Sets the employee type of this staff member.
	*
	* @param employeeType the employee type of this staff member
	*/
	@Override
	public void setEmployeeType(int employeeType) {
		_staffMember.setEmployeeType(employeeType);
	}

	/**
	* Sets the employer name of this staff member.
	*
	* @param employerName the employer name of this staff member
	*/
	@Override
	public void setEmployerName(java.lang.String employerName) {
		_staffMember.setEmployerName(employerName);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_staffMember.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_staffMember.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_staffMember.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_staffMember.setNew(n);
	}

	/**
	* Sets the primary key of this staff member.
	*
	* @param primaryKey the primary key of this staff member
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_staffMember.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_staffMember.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this staff member.
	*
	* @param userId the user ID of this staff member
	*/
	@Override
	public void setUserId(long userId) {
		_staffMember.setUserId(userId);
	}

	/**
	* Sets the user uuid of this staff member.
	*
	* @param userUuid the user uuid of this staff member
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_staffMember.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StaffMemberWrapper)) {
			return false;
		}

		StaffMemberWrapper staffMemberWrapper = (StaffMemberWrapper)obj;

		if (Objects.equals(_staffMember, staffMemberWrapper._staffMember)) {
			return true;
		}

		return false;
	}

	@Override
	public StaffMember getWrappedModel() {
		return _staffMember;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _staffMember.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _staffMember.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_staffMember.resetOriginalValues();
	}

	private final StaffMember _staffMember;
}