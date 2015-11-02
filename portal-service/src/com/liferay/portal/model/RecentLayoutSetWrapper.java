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
 * This class is a wrapper for {@link RecentLayoutSet}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RecentLayoutSet
 * @generated
 */
@ProviderType
public class RecentLayoutSetWrapper implements RecentLayoutSet,
	ModelWrapper<RecentLayoutSet> {
	public RecentLayoutSetWrapper(RecentLayoutSet recentLayoutSet) {
		_recentLayoutSet = recentLayoutSet;
	}

	@Override
	public Class<?> getModelClass() {
		return RecentLayoutSet.class;
	}

	@Override
	public String getModelClassName() {
		return RecentLayoutSet.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("recentLayoutSetId", getRecentLayoutSetId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("layoutSetId", getLayoutSetId());
		attributes.put("layoutSetBranchId", getLayoutSetBranchId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		Long recentLayoutSetId = (Long)attributes.get("recentLayoutSetId");

		if (recentLayoutSetId != null) {
			setRecentLayoutSetId(recentLayoutSetId);
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

		Long layoutSetId = (Long)attributes.get("layoutSetId");

		if (layoutSetId != null) {
			setLayoutSetId(layoutSetId);
		}

		Long layoutSetBranchId = (Long)attributes.get("layoutSetBranchId");

		if (layoutSetBranchId != null) {
			setLayoutSetBranchId(layoutSetBranchId);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new RecentLayoutSetWrapper((RecentLayoutSet)_recentLayoutSet.clone());
	}

	@Override
	public int compareTo(
		com.liferay.portal.model.RecentLayoutSet recentLayoutSet) {
		return _recentLayoutSet.compareTo(recentLayoutSet);
	}

	/**
	* Returns the company ID of this recent layout set.
	*
	* @return the company ID of this recent layout set
	*/
	@Override
	public long getCompanyId() {
		return _recentLayoutSet.getCompanyId();
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _recentLayoutSet.getExpandoBridge();
	}

	/**
	* Returns the group ID of this recent layout set.
	*
	* @return the group ID of this recent layout set
	*/
	@Override
	public long getGroupId() {
		return _recentLayoutSet.getGroupId();
	}

	/**
	* Returns the layout set branch ID of this recent layout set.
	*
	* @return the layout set branch ID of this recent layout set
	*/
	@Override
	public long getLayoutSetBranchId() {
		return _recentLayoutSet.getLayoutSetBranchId();
	}

	/**
	* Returns the layout set ID of this recent layout set.
	*
	* @return the layout set ID of this recent layout set
	*/
	@Override
	public long getLayoutSetId() {
		return _recentLayoutSet.getLayoutSetId();
	}

	/**
	* Returns the mvcc version of this recent layout set.
	*
	* @return the mvcc version of this recent layout set
	*/
	@Override
	public long getMvccVersion() {
		return _recentLayoutSet.getMvccVersion();
	}

	/**
	* Returns the primary key of this recent layout set.
	*
	* @return the primary key of this recent layout set
	*/
	@Override
	public long getPrimaryKey() {
		return _recentLayoutSet.getPrimaryKey();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _recentLayoutSet.getPrimaryKeyObj();
	}

	/**
	* Returns the recent layout set ID of this recent layout set.
	*
	* @return the recent layout set ID of this recent layout set
	*/
	@Override
	public long getRecentLayoutSetId() {
		return _recentLayoutSet.getRecentLayoutSetId();
	}

	/**
	* Returns the user ID of this recent layout set.
	*
	* @return the user ID of this recent layout set
	*/
	@Override
	public long getUserId() {
		return _recentLayoutSet.getUserId();
	}

	/**
	* Returns the user uuid of this recent layout set.
	*
	* @return the user uuid of this recent layout set
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _recentLayoutSet.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _recentLayoutSet.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _recentLayoutSet.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _recentLayoutSet.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _recentLayoutSet.isNew();
	}

	@Override
	public void persist() {
		_recentLayoutSet.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_recentLayoutSet.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this recent layout set.
	*
	* @param companyId the company ID of this recent layout set
	*/
	@Override
	public void setCompanyId(long companyId) {
		_recentLayoutSet.setCompanyId(companyId);
	}

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel) {
		_recentLayoutSet.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_recentLayoutSet.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_recentLayoutSet.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this recent layout set.
	*
	* @param groupId the group ID of this recent layout set
	*/
	@Override
	public void setGroupId(long groupId) {
		_recentLayoutSet.setGroupId(groupId);
	}

	/**
	* Sets the layout set branch ID of this recent layout set.
	*
	* @param layoutSetBranchId the layout set branch ID of this recent layout set
	*/
	@Override
	public void setLayoutSetBranchId(long layoutSetBranchId) {
		_recentLayoutSet.setLayoutSetBranchId(layoutSetBranchId);
	}

	/**
	* Sets the layout set ID of this recent layout set.
	*
	* @param layoutSetId the layout set ID of this recent layout set
	*/
	@Override
	public void setLayoutSetId(long layoutSetId) {
		_recentLayoutSet.setLayoutSetId(layoutSetId);
	}

	/**
	* Sets the mvcc version of this recent layout set.
	*
	* @param mvccVersion the mvcc version of this recent layout set
	*/
	@Override
	public void setMvccVersion(long mvccVersion) {
		_recentLayoutSet.setMvccVersion(mvccVersion);
	}

	@Override
	public void setNew(boolean n) {
		_recentLayoutSet.setNew(n);
	}

	/**
	* Sets the primary key of this recent layout set.
	*
	* @param primaryKey the primary key of this recent layout set
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_recentLayoutSet.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_recentLayoutSet.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the recent layout set ID of this recent layout set.
	*
	* @param recentLayoutSetId the recent layout set ID of this recent layout set
	*/
	@Override
	public void setRecentLayoutSetId(long recentLayoutSetId) {
		_recentLayoutSet.setRecentLayoutSetId(recentLayoutSetId);
	}

	/**
	* Sets the user ID of this recent layout set.
	*
	* @param userId the user ID of this recent layout set
	*/
	@Override
	public void setUserId(long userId) {
		_recentLayoutSet.setUserId(userId);
	}

	/**
	* Sets the user uuid of this recent layout set.
	*
	* @param userUuid the user uuid of this recent layout set
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_recentLayoutSet.setUserUuid(userUuid);
	}

	@Override
	public CacheModel<com.liferay.portal.model.RecentLayoutSet> toCacheModel() {
		return _recentLayoutSet.toCacheModel();
	}

	@Override
	public com.liferay.portal.model.RecentLayoutSet toEscapedModel() {
		return new RecentLayoutSetWrapper(_recentLayoutSet.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _recentLayoutSet.toString();
	}

	@Override
	public com.liferay.portal.model.RecentLayoutSet toUnescapedModel() {
		return new RecentLayoutSetWrapper(_recentLayoutSet.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _recentLayoutSet.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof RecentLayoutSetWrapper)) {
			return false;
		}

		RecentLayoutSetWrapper recentLayoutSetWrapper = (RecentLayoutSetWrapper)obj;

		if (Validator.equals(_recentLayoutSet,
					recentLayoutSetWrapper._recentLayoutSet)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	@Deprecated
	public RecentLayoutSet getWrappedRecentLayoutSet() {
		return _recentLayoutSet;
	}

	@Override
	public RecentLayoutSet getWrappedModel() {
		return _recentLayoutSet;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _recentLayoutSet.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _recentLayoutSet.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_recentLayoutSet.resetOriginalValues();
	}

	private final RecentLayoutSet _recentLayoutSet;
}