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
 * This class is a wrapper for {@link Project}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Project
 * @generated
 */
@ProviderType
public class ProjectWrapper implements Project, ModelWrapper<Project> {
	public ProjectWrapper(Project project) {
		_project = project;
	}

	@Override
	public Class<?> getModelClass() {
		return Project.class;
	}

	@Override
	public String getModelClassName() {
		return Project.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("projectId", getProjectId());
		attributes.put("companyId", getCompanyId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("clientId", getClientId());
		attributes.put("calendarId", getCalendarId());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("dashboardUrl", getDashboardUrl());
		attributes.put("lesaProjectKey", getLesaProjectKey());
		attributes.put("logoId", getLogoId());
		attributes.put("logoUrl", getLogoUrl());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long projectId = (Long)attributes.get("projectId");

		if (projectId != null) {
			setProjectId(projectId);
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

		Long clientId = (Long)attributes.get("clientId");

		if (clientId != null) {
			setClientId(clientId);
		}

		Long calendarId = (Long)attributes.get("calendarId");

		if (calendarId != null) {
			setCalendarId(calendarId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String dashboardUrl = (String)attributes.get("dashboardUrl");

		if (dashboardUrl != null) {
			setDashboardUrl(dashboardUrl);
		}

		String lesaProjectKey = (String)attributes.get("lesaProjectKey");

		if (lesaProjectKey != null) {
			setLesaProjectKey(lesaProjectKey);
		}

		Long logoId = (Long)attributes.get("logoId");

		if (logoId != null) {
			setLogoId(logoId);
		}

		String logoUrl = (String)attributes.get("logoUrl");

		if (logoUrl != null) {
			setLogoUrl(logoUrl);
		}
	}

	@Override
	public Project toEscapedModel() {
		return new ProjectWrapper(_project.toEscapedModel());
	}

	@Override
	public Project toUnescapedModel() {
		return new ProjectWrapper(_project.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _project.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _project.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _project.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _project.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Project> toCacheModel() {
		return _project.toCacheModel();
	}

	@Override
	public int compareTo(Project project) {
		return _project.compareTo(project);
	}

	@Override
	public int hashCode() {
		return _project.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _project.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new ProjectWrapper((Project)_project.clone());
	}

	/**
	* Returns the dashboard url of this project.
	*
	* @return the dashboard url of this project
	*/
	@Override
	public java.lang.String getDashboardUrl() {
		return _project.getDashboardUrl();
	}

	/**
	* Returns the description of this project.
	*
	* @return the description of this project
	*/
	@Override
	public java.lang.String getDescription() {
		return _project.getDescription();
	}

	/**
	* Returns the lesa project key of this project.
	*
	* @return the lesa project key of this project
	*/
	@Override
	public java.lang.String getLesaProjectKey() {
		return _project.getLesaProjectKey();
	}

	/**
	* Returns the logo url of this project.
	*
	* @return the logo url of this project
	*/
	@Override
	public java.lang.String getLogoUrl() {
		return _project.getLogoUrl();
	}

	/**
	* Returns the name of this project.
	*
	* @return the name of this project
	*/
	@Override
	public java.lang.String getName() {
		return _project.getName();
	}

	@Override
	public java.lang.String toString() {
		return _project.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _project.toXmlString();
	}

	/**
	* Returns the create date of this project.
	*
	* @return the create date of this project
	*/
	@Override
	public Date getCreateDate() {
		return _project.getCreateDate();
	}

	/**
	* Returns the modified date of this project.
	*
	* @return the modified date of this project
	*/
	@Override
	public Date getModifiedDate() {
		return _project.getModifiedDate();
	}

	/**
	* Returns the calendar ID of this project.
	*
	* @return the calendar ID of this project
	*/
	@Override
	public long getCalendarId() {
		return _project.getCalendarId();
	}

	/**
	* Returns the client ID of this project.
	*
	* @return the client ID of this project
	*/
	@Override
	public long getClientId() {
		return _project.getClientId();
	}

	/**
	* Returns the company ID of this project.
	*
	* @return the company ID of this project
	*/
	@Override
	public long getCompanyId() {
		return _project.getCompanyId();
	}

	/**
	* Returns the logo ID of this project.
	*
	* @return the logo ID of this project
	*/
	@Override
	public long getLogoId() {
		return _project.getLogoId();
	}

	/**
	* Returns the primary key of this project.
	*
	* @return the primary key of this project
	*/
	@Override
	public long getPrimaryKey() {
		return _project.getPrimaryKey();
	}

	/**
	* Returns the project ID of this project.
	*
	* @return the project ID of this project
	*/
	@Override
	public long getProjectId() {
		return _project.getProjectId();
	}

	@Override
	public void persist() {
		_project.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_project.setCachedModel(cachedModel);
	}

	/**
	* Sets the calendar ID of this project.
	*
	* @param calendarId the calendar ID of this project
	*/
	@Override
	public void setCalendarId(long calendarId) {
		_project.setCalendarId(calendarId);
	}

	/**
	* Sets the client ID of this project.
	*
	* @param clientId the client ID of this project
	*/
	@Override
	public void setClientId(long clientId) {
		_project.setClientId(clientId);
	}

	/**
	* Sets the company ID of this project.
	*
	* @param companyId the company ID of this project
	*/
	@Override
	public void setCompanyId(long companyId) {
		_project.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this project.
	*
	* @param createDate the create date of this project
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_project.setCreateDate(createDate);
	}

	/**
	* Sets the dashboard url of this project.
	*
	* @param dashboardUrl the dashboard url of this project
	*/
	@Override
	public void setDashboardUrl(java.lang.String dashboardUrl) {
		_project.setDashboardUrl(dashboardUrl);
	}

	/**
	* Sets the description of this project.
	*
	* @param description the description of this project
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_project.setDescription(description);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_project.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_project.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_project.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the lesa project key of this project.
	*
	* @param lesaProjectKey the lesa project key of this project
	*/
	@Override
	public void setLesaProjectKey(java.lang.String lesaProjectKey) {
		_project.setLesaProjectKey(lesaProjectKey);
	}

	/**
	* Sets the logo ID of this project.
	*
	* @param logoId the logo ID of this project
	*/
	@Override
	public void setLogoId(long logoId) {
		_project.setLogoId(logoId);
	}

	/**
	* Sets the logo url of this project.
	*
	* @param logoUrl the logo url of this project
	*/
	@Override
	public void setLogoUrl(java.lang.String logoUrl) {
		_project.setLogoUrl(logoUrl);
	}

	/**
	* Sets the modified date of this project.
	*
	* @param modifiedDate the modified date of this project
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_project.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this project.
	*
	* @param name the name of this project
	*/
	@Override
	public void setName(java.lang.String name) {
		_project.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_project.setNew(n);
	}

	/**
	* Sets the primary key of this project.
	*
	* @param primaryKey the primary key of this project
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_project.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_project.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the project ID of this project.
	*
	* @param projectId the project ID of this project
	*/
	@Override
	public void setProjectId(long projectId) {
		_project.setProjectId(projectId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ProjectWrapper)) {
			return false;
		}

		ProjectWrapper projectWrapper = (ProjectWrapper)obj;

		if (Objects.equals(_project, projectWrapper._project)) {
			return true;
		}

		return false;
	}

	@Override
	public Project getWrappedModel() {
		return _project;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _project.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _project.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_project.resetOriginalValues();
	}

	private final Project _project;
}