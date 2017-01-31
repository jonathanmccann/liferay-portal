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
 * This class is a wrapper for {@link Engagement}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Engagement
 * @generated
 */
@ProviderType
public class EngagementWrapper implements Engagement, ModelWrapper<Engagement> {
	public EngagementWrapper(Engagement engagement) {
		_engagement = engagement;
	}

	@Override
	public Class<?> getModelClass() {
		return Engagement.class;
	}

	@Override
	public String getModelClassName() {
		return Engagement.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("engagementId", getEngagementId());
		attributes.put("companyId", getCompanyId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("calendarBookingId", getCalendarBookingId());
		attributes.put("clientId", getClientId());
		attributes.put("projectId", getProjectId());
		attributes.put("title", getTitle());
		attributes.put("description", getDescription());
		attributes.put("leadUserId", getLeadUserId());
		attributes.put("leadName", getLeadName());
		attributes.put("typeCategoryId", getTypeCategoryId());
		attributes.put("difficultyId", getDifficultyId());
		attributes.put("progressStatusId", getProgressStatusId());
		attributes.put("approvalStatusId", getApprovalStatusId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long engagementId = (Long)attributes.get("engagementId");

		if (engagementId != null) {
			setEngagementId(engagementId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long calendarBookingId = (Long)attributes.get("calendarBookingId");

		if (calendarBookingId != null) {
			setCalendarBookingId(calendarBookingId);
		}

		Long clientId = (Long)attributes.get("clientId");

		if (clientId != null) {
			setClientId(clientId);
		}

		Long projectId = (Long)attributes.get("projectId");

		if (projectId != null) {
			setProjectId(projectId);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Long leadUserId = (Long)attributes.get("leadUserId");

		if (leadUserId != null) {
			setLeadUserId(leadUserId);
		}

		String leadName = (String)attributes.get("leadName");

		if (leadName != null) {
			setLeadName(leadName);
		}

		Long typeCategoryId = (Long)attributes.get("typeCategoryId");

		if (typeCategoryId != null) {
			setTypeCategoryId(typeCategoryId);
		}

		Integer difficultyId = (Integer)attributes.get("difficultyId");

		if (difficultyId != null) {
			setDifficultyId(difficultyId);
		}

		Integer progressStatusId = (Integer)attributes.get("progressStatusId");

		if (progressStatusId != null) {
			setProgressStatusId(progressStatusId);
		}

		Integer approvalStatusId = (Integer)attributes.get("approvalStatusId");

		if (approvalStatusId != null) {
			setApprovalStatusId(approvalStatusId);
		}
	}

	@Override
	public Engagement toEscapedModel() {
		return new EngagementWrapper(_engagement.toEscapedModel());
	}

	@Override
	public Engagement toUnescapedModel() {
		return new EngagementWrapper(_engagement.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _engagement.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _engagement.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _engagement.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _engagement.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Engagement> toCacheModel() {
		return _engagement.toCacheModel();
	}

	@Override
	public int compareTo(Engagement engagement) {
		return _engagement.compareTo(engagement);
	}

	/**
	* Returns the approval status ID of this engagement.
	*
	* @return the approval status ID of this engagement
	*/
	@Override
	public int getApprovalStatusId() {
		return _engagement.getApprovalStatusId();
	}

	/**
	* Returns the difficulty ID of this engagement.
	*
	* @return the difficulty ID of this engagement
	*/
	@Override
	public int getDifficultyId() {
		return _engagement.getDifficultyId();
	}

	/**
	* Returns the progress status ID of this engagement.
	*
	* @return the progress status ID of this engagement
	*/
	@Override
	public int getProgressStatusId() {
		return _engagement.getProgressStatusId();
	}

	@Override
	public int hashCode() {
		return _engagement.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _engagement.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new EngagementWrapper((Engagement)_engagement.clone());
	}

	/**
	* Returns the description of this engagement.
	*
	* @return the description of this engagement
	*/
	@Override
	public java.lang.String getDescription() {
		return _engagement.getDescription();
	}

	/**
	* Returns the lead name of this engagement.
	*
	* @return the lead name of this engagement
	*/
	@Override
	public java.lang.String getLeadName() {
		return _engagement.getLeadName();
	}

	/**
	* Returns the lead user uuid of this engagement.
	*
	* @return the lead user uuid of this engagement
	*/
	@Override
	public java.lang.String getLeadUserUuid() {
		return _engagement.getLeadUserUuid();
	}

	/**
	* Returns the title of this engagement.
	*
	* @return the title of this engagement
	*/
	@Override
	public java.lang.String getTitle() {
		return _engagement.getTitle();
	}

	@Override
	public java.lang.String toString() {
		return _engagement.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _engagement.toXmlString();
	}

	/**
	* Returns the create date of this engagement.
	*
	* @return the create date of this engagement
	*/
	@Override
	public Date getCreateDate() {
		return _engagement.getCreateDate();
	}

	/**
	* Returns the modified date of this engagement.
	*
	* @return the modified date of this engagement
	*/
	@Override
	public Date getModifiedDate() {
		return _engagement.getModifiedDate();
	}

	/**
	* Returns the calendar booking ID of this engagement.
	*
	* @return the calendar booking ID of this engagement
	*/
	@Override
	public long getCalendarBookingId() {
		return _engagement.getCalendarBookingId();
	}

	/**
	* Returns the client ID of this engagement.
	*
	* @return the client ID of this engagement
	*/
	@Override
	public long getClientId() {
		return _engagement.getClientId();
	}

	/**
	* Returns the company ID of this engagement.
	*
	* @return the company ID of this engagement
	*/
	@Override
	public long getCompanyId() {
		return _engagement.getCompanyId();
	}

	/**
	* Returns the engagement ID of this engagement.
	*
	* @return the engagement ID of this engagement
	*/
	@Override
	public long getEngagementId() {
		return _engagement.getEngagementId();
	}

	/**
	* Returns the lead user ID of this engagement.
	*
	* @return the lead user ID of this engagement
	*/
	@Override
	public long getLeadUserId() {
		return _engagement.getLeadUserId();
	}

	/**
	* Returns the primary key of this engagement.
	*
	* @return the primary key of this engagement
	*/
	@Override
	public long getPrimaryKey() {
		return _engagement.getPrimaryKey();
	}

	/**
	* Returns the project ID of this engagement.
	*
	* @return the project ID of this engagement
	*/
	@Override
	public long getProjectId() {
		return _engagement.getProjectId();
	}

	/**
	* Returns the type category ID of this engagement.
	*
	* @return the type category ID of this engagement
	*/
	@Override
	public long getTypeCategoryId() {
		return _engagement.getTypeCategoryId();
	}

	@Override
	public void persist() {
		_engagement.persist();
	}

	/**
	* Sets the approval status ID of this engagement.
	*
	* @param approvalStatusId the approval status ID of this engagement
	*/
	@Override
	public void setApprovalStatusId(int approvalStatusId) {
		_engagement.setApprovalStatusId(approvalStatusId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_engagement.setCachedModel(cachedModel);
	}

	/**
	* Sets the calendar booking ID of this engagement.
	*
	* @param calendarBookingId the calendar booking ID of this engagement
	*/
	@Override
	public void setCalendarBookingId(long calendarBookingId) {
		_engagement.setCalendarBookingId(calendarBookingId);
	}

	/**
	* Sets the client ID of this engagement.
	*
	* @param clientId the client ID of this engagement
	*/
	@Override
	public void setClientId(long clientId) {
		_engagement.setClientId(clientId);
	}

	/**
	* Sets the company ID of this engagement.
	*
	* @param companyId the company ID of this engagement
	*/
	@Override
	public void setCompanyId(long companyId) {
		_engagement.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this engagement.
	*
	* @param createDate the create date of this engagement
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_engagement.setCreateDate(createDate);
	}

	/**
	* Sets the description of this engagement.
	*
	* @param description the description of this engagement
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_engagement.setDescription(description);
	}

	/**
	* Sets the difficulty ID of this engagement.
	*
	* @param difficultyId the difficulty ID of this engagement
	*/
	@Override
	public void setDifficultyId(int difficultyId) {
		_engagement.setDifficultyId(difficultyId);
	}

	/**
	* Sets the engagement ID of this engagement.
	*
	* @param engagementId the engagement ID of this engagement
	*/
	@Override
	public void setEngagementId(long engagementId) {
		_engagement.setEngagementId(engagementId);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_engagement.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_engagement.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_engagement.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the lead name of this engagement.
	*
	* @param leadName the lead name of this engagement
	*/
	@Override
	public void setLeadName(java.lang.String leadName) {
		_engagement.setLeadName(leadName);
	}

	/**
	* Sets the lead user ID of this engagement.
	*
	* @param leadUserId the lead user ID of this engagement
	*/
	@Override
	public void setLeadUserId(long leadUserId) {
		_engagement.setLeadUserId(leadUserId);
	}

	/**
	* Sets the lead user uuid of this engagement.
	*
	* @param leadUserUuid the lead user uuid of this engagement
	*/
	@Override
	public void setLeadUserUuid(java.lang.String leadUserUuid) {
		_engagement.setLeadUserUuid(leadUserUuid);
	}

	/**
	* Sets the modified date of this engagement.
	*
	* @param modifiedDate the modified date of this engagement
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_engagement.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_engagement.setNew(n);
	}

	/**
	* Sets the primary key of this engagement.
	*
	* @param primaryKey the primary key of this engagement
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_engagement.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_engagement.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the progress status ID of this engagement.
	*
	* @param progressStatusId the progress status ID of this engagement
	*/
	@Override
	public void setProgressStatusId(int progressStatusId) {
		_engagement.setProgressStatusId(progressStatusId);
	}

	/**
	* Sets the project ID of this engagement.
	*
	* @param projectId the project ID of this engagement
	*/
	@Override
	public void setProjectId(long projectId) {
		_engagement.setProjectId(projectId);
	}

	/**
	* Sets the title of this engagement.
	*
	* @param title the title of this engagement
	*/
	@Override
	public void setTitle(java.lang.String title) {
		_engagement.setTitle(title);
	}

	/**
	* Sets the type category ID of this engagement.
	*
	* @param typeCategoryId the type category ID of this engagement
	*/
	@Override
	public void setTypeCategoryId(long typeCategoryId) {
		_engagement.setTypeCategoryId(typeCategoryId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EngagementWrapper)) {
			return false;
		}

		EngagementWrapper engagementWrapper = (EngagementWrapper)obj;

		if (Objects.equals(_engagement, engagementWrapper._engagement)) {
			return true;
		}

		return false;
	}

	@Override
	public Engagement getWrappedModel() {
		return _engagement;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _engagement.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _engagement.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_engagement.resetOriginalValues();
	}

	private final Engagement _engagement;
}