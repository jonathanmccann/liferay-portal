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
 * This class is a wrapper for {@link Client}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Client
 * @generated
 */
@ProviderType
public class ClientWrapper implements Client, ModelWrapper<Client> {
	public ClientWrapper(Client client) {
		_client = client;
	}

	@Override
	public Class<?> getModelClass() {
		return Client.class;
	}

	@Override
	public String getModelClassName() {
		return Client.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("clientId", getClientId());
		attributes.put("companyId", getCompanyId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("parentClientId", getParentClientId());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("dashboardUrl", getDashboardUrl());
		attributes.put("logoId", getLogoId());
		attributes.put("logoUrl", getLogoUrl());
		attributes.put("websiteUrl", getWebsiteUrl());
		attributes.put("addressId", getAddressId());
		attributes.put("active", getActive());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long clientId = (Long)attributes.get("clientId");

		if (clientId != null) {
			setClientId(clientId);
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

		Long parentClientId = (Long)attributes.get("parentClientId");

		if (parentClientId != null) {
			setParentClientId(parentClientId);
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

		Long logoId = (Long)attributes.get("logoId");

		if (logoId != null) {
			setLogoId(logoId);
		}

		String logoUrl = (String)attributes.get("logoUrl");

		if (logoUrl != null) {
			setLogoUrl(logoUrl);
		}

		String websiteUrl = (String)attributes.get("websiteUrl");

		if (websiteUrl != null) {
			setWebsiteUrl(websiteUrl);
		}

		Long addressId = (Long)attributes.get("addressId");

		if (addressId != null) {
			setAddressId(addressId);
		}

		Boolean active = (Boolean)attributes.get("active");

		if (active != null) {
			setActive(active);
		}
	}

	@Override
	public Client toEscapedModel() {
		return new ClientWrapper(_client.toEscapedModel());
	}

	@Override
	public Client toUnescapedModel() {
		return new ClientWrapper(_client.toUnescapedModel());
	}

	/**
	* Returns the active of this client.
	*
	* @return the active of this client
	*/
	@Override
	public boolean getActive() {
		return _client.getActive();
	}

	/**
	* Returns <code>true</code> if this client is active.
	*
	* @return <code>true</code> if this client is active; <code>false</code> otherwise
	*/
	@Override
	public boolean isActive() {
		return _client.isActive();
	}

	@Override
	public boolean isCachedModel() {
		return _client.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _client.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _client.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _client.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Client> toCacheModel() {
		return _client.toCacheModel();
	}

	@Override
	public int compareTo(Client client) {
		return _client.compareTo(client);
	}

	@Override
	public int hashCode() {
		return _client.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _client.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new ClientWrapper((Client)_client.clone());
	}

	/**
	* Returns the dashboard url of this client.
	*
	* @return the dashboard url of this client
	*/
	@Override
	public java.lang.String getDashboardUrl() {
		return _client.getDashboardUrl();
	}

	/**
	* Returns the description of this client.
	*
	* @return the description of this client
	*/
	@Override
	public java.lang.String getDescription() {
		return _client.getDescription();
	}

	/**
	* Returns the logo url of this client.
	*
	* @return the logo url of this client
	*/
	@Override
	public java.lang.String getLogoUrl() {
		return _client.getLogoUrl();
	}

	/**
	* Returns the name of this client.
	*
	* @return the name of this client
	*/
	@Override
	public java.lang.String getName() {
		return _client.getName();
	}

	/**
	* Returns the website url of this client.
	*
	* @return the website url of this client
	*/
	@Override
	public java.lang.String getWebsiteUrl() {
		return _client.getWebsiteUrl();
	}

	@Override
	public java.lang.String toString() {
		return _client.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _client.toXmlString();
	}

	/**
	* Returns the create date of this client.
	*
	* @return the create date of this client
	*/
	@Override
	public Date getCreateDate() {
		return _client.getCreateDate();
	}

	/**
	* Returns the modified date of this client.
	*
	* @return the modified date of this client
	*/
	@Override
	public Date getModifiedDate() {
		return _client.getModifiedDate();
	}

	/**
	* Returns the address ID of this client.
	*
	* @return the address ID of this client
	*/
	@Override
	public long getAddressId() {
		return _client.getAddressId();
	}

	/**
	* Returns the client ID of this client.
	*
	* @return the client ID of this client
	*/
	@Override
	public long getClientId() {
		return _client.getClientId();
	}

	/**
	* Returns the company ID of this client.
	*
	* @return the company ID of this client
	*/
	@Override
	public long getCompanyId() {
		return _client.getCompanyId();
	}

	/**
	* Returns the logo ID of this client.
	*
	* @return the logo ID of this client
	*/
	@Override
	public long getLogoId() {
		return _client.getLogoId();
	}

	/**
	* Returns the parent client ID of this client.
	*
	* @return the parent client ID of this client
	*/
	@Override
	public long getParentClientId() {
		return _client.getParentClientId();
	}

	/**
	* Returns the primary key of this client.
	*
	* @return the primary key of this client
	*/
	@Override
	public long getPrimaryKey() {
		return _client.getPrimaryKey();
	}

	@Override
	public void persist() {
		_client.persist();
	}

	/**
	* Sets whether this client is active.
	*
	* @param active the active of this client
	*/
	@Override
	public void setActive(boolean active) {
		_client.setActive(active);
	}

	/**
	* Sets the address ID of this client.
	*
	* @param addressId the address ID of this client
	*/
	@Override
	public void setAddressId(long addressId) {
		_client.setAddressId(addressId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_client.setCachedModel(cachedModel);
	}

	/**
	* Sets the client ID of this client.
	*
	* @param clientId the client ID of this client
	*/
	@Override
	public void setClientId(long clientId) {
		_client.setClientId(clientId);
	}

	/**
	* Sets the company ID of this client.
	*
	* @param companyId the company ID of this client
	*/
	@Override
	public void setCompanyId(long companyId) {
		_client.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this client.
	*
	* @param createDate the create date of this client
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_client.setCreateDate(createDate);
	}

	/**
	* Sets the dashboard url of this client.
	*
	* @param dashboardUrl the dashboard url of this client
	*/
	@Override
	public void setDashboardUrl(java.lang.String dashboardUrl) {
		_client.setDashboardUrl(dashboardUrl);
	}

	/**
	* Sets the description of this client.
	*
	* @param description the description of this client
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_client.setDescription(description);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_client.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_client.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_client.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the logo ID of this client.
	*
	* @param logoId the logo ID of this client
	*/
	@Override
	public void setLogoId(long logoId) {
		_client.setLogoId(logoId);
	}

	/**
	* Sets the logo url of this client.
	*
	* @param logoUrl the logo url of this client
	*/
	@Override
	public void setLogoUrl(java.lang.String logoUrl) {
		_client.setLogoUrl(logoUrl);
	}

	/**
	* Sets the modified date of this client.
	*
	* @param modifiedDate the modified date of this client
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_client.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this client.
	*
	* @param name the name of this client
	*/
	@Override
	public void setName(java.lang.String name) {
		_client.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_client.setNew(n);
	}

	/**
	* Sets the parent client ID of this client.
	*
	* @param parentClientId the parent client ID of this client
	*/
	@Override
	public void setParentClientId(long parentClientId) {
		_client.setParentClientId(parentClientId);
	}

	/**
	* Sets the primary key of this client.
	*
	* @param primaryKey the primary key of this client
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_client.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_client.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the website url of this client.
	*
	* @param websiteUrl the website url of this client
	*/
	@Override
	public void setWebsiteUrl(java.lang.String websiteUrl) {
		_client.setWebsiteUrl(websiteUrl);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ClientWrapper)) {
			return false;
		}

		ClientWrapper clientWrapper = (ClientWrapper)obj;

		if (Objects.equals(_client, clientWrapper._client)) {
			return true;
		}

		return false;
	}

	@Override
	public Client getWrappedModel() {
		return _client;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _client.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _client.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_client.resetOriginalValues();
	}

	private final Client _client;
}