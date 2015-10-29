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

package com.liferay.portal.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link RecentLayout}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RecentLayout
 * @generated
 */
@ProviderType
public class RecentLayoutWrapper implements RecentLayout,
	ModelWrapper<RecentLayout> {
	public RecentLayoutWrapper(RecentLayout recentLayout) {
		_recentLayout = recentLayout;
	}

	@Override
	public Class<?> getModelClass() {
		return RecentLayout.class;
	}

	@Override
	public String getModelClassName() {
		return RecentLayout.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("recentLayoutId", getRecentLayoutId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("layoutSetBranchId", getLayoutSetBranchId());
		attributes.put("plid", getPlid());
		attributes.put("layoutBranchId", getLayoutBranchId());
		attributes.put("layoutRevisionId", getLayoutRevisionId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		Long recentLayoutId = (Long)attributes.get("recentLayoutId");

		if (recentLayoutId != null) {
			setRecentLayoutId(recentLayoutId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long layoutSetBranchId = (Long)attributes.get("layoutSetBranchId");

		if (layoutSetBranchId != null) {
			setLayoutSetBranchId(layoutSetBranchId);
		}

		Long plid = (Long)attributes.get("plid");

		if (plid != null) {
			setPlid(plid);
		}

		Long layoutBranchId = (Long)attributes.get("layoutBranchId");

		if (layoutBranchId != null) {
			setLayoutBranchId(layoutBranchId);
		}

		Long layoutRevisionId = (Long)attributes.get("layoutRevisionId");

		if (layoutRevisionId != null) {
			setLayoutRevisionId(layoutRevisionId);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new RecentLayoutWrapper((RecentLayout)_recentLayout.clone());
	}

	@Override
	public int compareTo(com.liferay.portal.model.RecentLayout recentLayout) {
		return _recentLayout.compareTo(recentLayout);
	}

	/**
	* Returns the company ID of this recent layout.
	*
	* @return the company ID of this recent layout
	*/
	@Override
	public long getCompanyId() {
		return _recentLayout.getCompanyId();
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _recentLayout.getExpandoBridge();
	}

	/**
	* Returns the group ID of this recent layout.
	*
	* @return the group ID of this recent layout
	*/
	@Override
	public long getGroupId() {
		return _recentLayout.getGroupId();
	}

	/**
	* Returns the layout branch ID of this recent layout.
	*
	* @return the layout branch ID of this recent layout
	*/
	@Override
	public long getLayoutBranchId() {
		return _recentLayout.getLayoutBranchId();
	}

	/**
	* Returns the layout revision ID of this recent layout.
	*
	* @return the layout revision ID of this recent layout
	*/
	@Override
	public long getLayoutRevisionId() {
		return _recentLayout.getLayoutRevisionId();
	}

	/**
	* Returns the layout set branch ID of this recent layout.
	*
	* @return the layout set branch ID of this recent layout
	*/
	@Override
	public long getLayoutSetBranchId() {
		return _recentLayout.getLayoutSetBranchId();
	}

	/**
	* Returns the mvcc version of this recent layout.
	*
	* @return the mvcc version of this recent layout
	*/
	@Override
	public long getMvccVersion() {
		return _recentLayout.getMvccVersion();
	}

	/**
	* Returns the plid of this recent layout.
	*
	* @return the plid of this recent layout
	*/
	@Override
	public long getPlid() {
		return _recentLayout.getPlid();
	}

	/**
	* Returns the primary key of this recent layout.
	*
	* @return the primary key of this recent layout
	*/
	@Override
	public long getPrimaryKey() {
		return _recentLayout.getPrimaryKey();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _recentLayout.getPrimaryKeyObj();
	}

	/**
	* Returns the recent layout ID of this recent layout.
	*
	* @return the recent layout ID of this recent layout
	*/
	@Override
	public long getRecentLayoutId() {
		return _recentLayout.getRecentLayoutId();
	}

	/**
	* Returns the user ID of this recent layout.
	*
	* @return the user ID of this recent layout
	*/
	@Override
	public long getUserId() {
		return _recentLayout.getUserId();
	}

	/**
	* Returns the user uuid of this recent layout.
	*
	* @return the user uuid of this recent layout
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _recentLayout.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _recentLayout.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _recentLayout.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _recentLayout.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _recentLayout.isNew();
	}

	@Override
	public void persist() {
		_recentLayout.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_recentLayout.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this recent layout.
	*
	* @param companyId the company ID of this recent layout
	*/
	@Override
	public void setCompanyId(long companyId) {
		_recentLayout.setCompanyId(companyId);
	}

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel) {
		_recentLayout.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_recentLayout.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_recentLayout.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this recent layout.
	*
	* @param groupId the group ID of this recent layout
	*/
	@Override
	public void setGroupId(long groupId) {
		_recentLayout.setGroupId(groupId);
	}

	/**
	* Sets the layout branch ID of this recent layout.
	*
	* @param layoutBranchId the layout branch ID of this recent layout
	*/
	@Override
	public void setLayoutBranchId(long layoutBranchId) {
		_recentLayout.setLayoutBranchId(layoutBranchId);
	}

	/**
	* Sets the layout revision ID of this recent layout.
	*
	* @param layoutRevisionId the layout revision ID of this recent layout
	*/
	@Override
	public void setLayoutRevisionId(long layoutRevisionId) {
		_recentLayout.setLayoutRevisionId(layoutRevisionId);
	}

	/**
	* Sets the layout set branch ID of this recent layout.
	*
	* @param layoutSetBranchId the layout set branch ID of this recent layout
	*/
	@Override
	public void setLayoutSetBranchId(long layoutSetBranchId) {
		_recentLayout.setLayoutSetBranchId(layoutSetBranchId);
	}

	/**
	* Sets the mvcc version of this recent layout.
	*
	* @param mvccVersion the mvcc version of this recent layout
	*/
	@Override
	public void setMvccVersion(long mvccVersion) {
		_recentLayout.setMvccVersion(mvccVersion);
	}

	@Override
	public void setNew(boolean n) {
		_recentLayout.setNew(n);
	}

	/**
	* Sets the plid of this recent layout.
	*
	* @param plid the plid of this recent layout
	*/
	@Override
	public void setPlid(long plid) {
		_recentLayout.setPlid(plid);
	}

	/**
	* Sets the primary key of this recent layout.
	*
	* @param primaryKey the primary key of this recent layout
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_recentLayout.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_recentLayout.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the recent layout ID of this recent layout.
	*
	* @param recentLayoutId the recent layout ID of this recent layout
	*/
	@Override
	public void setRecentLayoutId(long recentLayoutId) {
		_recentLayout.setRecentLayoutId(recentLayoutId);
	}

	/**
	* Sets the user ID of this recent layout.
	*
	* @param userId the user ID of this recent layout
	*/
	@Override
	public void setUserId(long userId) {
		_recentLayout.setUserId(userId);
	}

	/**
	* Sets the user uuid of this recent layout.
	*
	* @param userUuid the user uuid of this recent layout
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_recentLayout.setUserUuid(userUuid);
	}

	@Override
	public CacheModel<com.liferay.portal.model.RecentLayout> toCacheModel() {
		return _recentLayout.toCacheModel();
	}

	@Override
	public com.liferay.portal.model.RecentLayout toEscapedModel() {
		return new RecentLayoutWrapper(_recentLayout.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _recentLayout.toString();
	}

	@Override
	public com.liferay.portal.model.RecentLayout toUnescapedModel() {
		return new RecentLayoutWrapper(_recentLayout.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _recentLayout.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof RecentLayoutWrapper)) {
			return false;
		}

		RecentLayoutWrapper recentLayoutWrapper = (RecentLayoutWrapper)obj;

		if (Validator.equals(_recentLayout, recentLayoutWrapper._recentLayout)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	@Deprecated
	public RecentLayout getWrappedRecentLayout() {
		return _recentLayout;
	}

	@Override
	public RecentLayout getWrappedModel() {
		return _recentLayout;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _recentLayout.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _recentLayout.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_recentLayout.resetOriginalValues();
	}

	private final RecentLayout _recentLayout;
}