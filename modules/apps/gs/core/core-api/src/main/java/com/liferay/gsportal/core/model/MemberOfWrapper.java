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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link MemberOf}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MemberOf
 * @generated
 */
@ProviderType
public class MemberOfWrapper implements MemberOf, ModelWrapper<MemberOf> {
	public MemberOfWrapper(MemberOf memberOf) {
		_memberOf = memberOf;
	}

	@Override
	public Class<?> getModelClass() {
		return MemberOf.class;
	}

	@Override
	public String getModelClassName() {
		return MemberOf.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("memberOfId", getMemberOfId());
		attributes.put("companyId", getCompanyId());
		attributes.put("createDate", getCreateDate());
		attributes.put("userId", getUserId());
		attributes.put("clientId", getClientId());
		attributes.put("projectId", getProjectId());
		attributes.put("engagementId", getEngagementId());
		attributes.put("memberRoleId", getMemberRoleId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long memberOfId = (Long)attributes.get("memberOfId");

		if (memberOfId != null) {
			setMemberOfId(memberOfId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long clientId = (Long)attributes.get("clientId");

		if (clientId != null) {
			setClientId(clientId);
		}

		Long projectId = (Long)attributes.get("projectId");

		if (projectId != null) {
			setProjectId(projectId);
		}

		Long engagementId = (Long)attributes.get("engagementId");

		if (engagementId != null) {
			setEngagementId(engagementId);
		}

		Integer memberRoleId = (Integer)attributes.get("memberRoleId");

		if (memberRoleId != null) {
			setMemberRoleId(memberRoleId);
		}
	}

	@Override
	public MemberOf toEscapedModel() {
		return new MemberOfWrapper(_memberOf.toEscapedModel());
	}

	@Override
	public MemberOf toUnescapedModel() {
		return new MemberOfWrapper(_memberOf.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _memberOf.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _memberOf.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _memberOf.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _memberOf.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<MemberOf> toCacheModel() {
		return _memberOf.toCacheModel();
	}

	@Override
	public int compareTo(MemberOf memberOf) {
		return _memberOf.compareTo(memberOf);
	}

	/**
	* Returns the member role ID of this member of.
	*
	* @return the member role ID of this member of
	*/
	@Override
	public int getMemberRoleId() {
		return _memberOf.getMemberRoleId();
	}

	@Override
	public int hashCode() {
		return _memberOf.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _memberOf.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new MemberOfWrapper((MemberOf)_memberOf.clone());
	}

	/**
	* Returns the user uuid of this member of.
	*
	* @return the user uuid of this member of
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _memberOf.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _memberOf.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _memberOf.toXmlString();
	}

	/**
	* Returns the create date of this member of.
	*
	* @return the create date of this member of
	*/
	@Override
	public Date getCreateDate() {
		return _memberOf.getCreateDate();
	}

	/**
	* Returns the client ID of this member of.
	*
	* @return the client ID of this member of
	*/
	@Override
	public long getClientId() {
		return _memberOf.getClientId();
	}

	/**
	* Returns the company ID of this member of.
	*
	* @return the company ID of this member of
	*/
	@Override
	public long getCompanyId() {
		return _memberOf.getCompanyId();
	}

	/**
	* Returns the engagement ID of this member of.
	*
	* @return the engagement ID of this member of
	*/
	@Override
	public long getEngagementId() {
		return _memberOf.getEngagementId();
	}

	/**
	* Returns the member of ID of this member of.
	*
	* @return the member of ID of this member of
	*/
	@Override
	public long getMemberOfId() {
		return _memberOf.getMemberOfId();
	}

	/**
	* Returns the primary key of this member of.
	*
	* @return the primary key of this member of
	*/
	@Override
	public long getPrimaryKey() {
		return _memberOf.getPrimaryKey();
	}

	/**
	* Returns the project ID of this member of.
	*
	* @return the project ID of this member of
	*/
	@Override
	public long getProjectId() {
		return _memberOf.getProjectId();
	}

	/**
	* Returns the user ID of this member of.
	*
	* @return the user ID of this member of
	*/
	@Override
	public long getUserId() {
		return _memberOf.getUserId();
	}

	@Override
	public void persist() {
		_memberOf.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_memberOf.setCachedModel(cachedModel);
	}

	/**
	* Sets the client ID of this member of.
	*
	* @param clientId the client ID of this member of
	*/
	@Override
	public void setClientId(long clientId) {
		_memberOf.setClientId(clientId);
	}

	/**
	* Sets the company ID of this member of.
	*
	* @param companyId the company ID of this member of
	*/
	@Override
	public void setCompanyId(long companyId) {
		_memberOf.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this member of.
	*
	* @param createDate the create date of this member of
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_memberOf.setCreateDate(createDate);
	}

	/**
	* Sets the engagement ID of this member of.
	*
	* @param engagementId the engagement ID of this member of
	*/
	@Override
	public void setEngagementId(long engagementId) {
		_memberOf.setEngagementId(engagementId);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_memberOf.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_memberOf.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_memberOf.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the member of ID of this member of.
	*
	* @param memberOfId the member of ID of this member of
	*/
	@Override
	public void setMemberOfId(long memberOfId) {
		_memberOf.setMemberOfId(memberOfId);
	}

	/**
	* Sets the member role ID of this member of.
	*
	* @param memberRoleId the member role ID of this member of
	*/
	@Override
	public void setMemberRoleId(int memberRoleId) {
		_memberOf.setMemberRoleId(memberRoleId);
	}

	@Override
	public void setNew(boolean n) {
		_memberOf.setNew(n);
	}

	/**
	* Sets the primary key of this member of.
	*
	* @param primaryKey the primary key of this member of
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_memberOf.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_memberOf.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the project ID of this member of.
	*
	* @param projectId the project ID of this member of
	*/
	@Override
	public void setProjectId(long projectId) {
		_memberOf.setProjectId(projectId);
	}

	/**
	* Sets the user ID of this member of.
	*
	* @param userId the user ID of this member of
	*/
	@Override
	public void setUserId(long userId) {
		_memberOf.setUserId(userId);
	}

	/**
	* Sets the user uuid of this member of.
	*
	* @param userUuid the user uuid of this member of
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_memberOf.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MemberOfWrapper)) {
			return false;
		}

		MemberOfWrapper memberOfWrapper = (MemberOfWrapper)obj;

		if (Objects.equals(_memberOf, memberOfWrapper._memberOf)) {
			return true;
		}

		return false;
	}

	@Override
	public MemberOf getWrappedModel() {
		return _memberOf;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _memberOf.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _memberOf.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_memberOf.resetOriginalValues();
	}

	private final MemberOf _memberOf;
}