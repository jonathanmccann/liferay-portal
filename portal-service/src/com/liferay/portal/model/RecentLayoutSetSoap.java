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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class RecentLayoutSetSoap implements Serializable {
	public static RecentLayoutSetSoap toSoapModel(RecentLayoutSet model) {
		RecentLayoutSetSoap soapModel = new RecentLayoutSetSoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setRecentLayoutSetId(model.getRecentLayoutSetId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setLayoutSetId(model.getLayoutSetId());
		soapModel.setLayoutSetBranchId(model.getLayoutSetBranchId());

		return soapModel;
	}

	public static RecentLayoutSetSoap[] toSoapModels(RecentLayoutSet[] models) {
		RecentLayoutSetSoap[] soapModels = new RecentLayoutSetSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static RecentLayoutSetSoap[][] toSoapModels(
		RecentLayoutSet[][] models) {
		RecentLayoutSetSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new RecentLayoutSetSoap[models.length][models[0].length];
		}
		else {
			soapModels = new RecentLayoutSetSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static RecentLayoutSetSoap[] toSoapModels(
		List<RecentLayoutSet> models) {
		List<RecentLayoutSetSoap> soapModels = new ArrayList<RecentLayoutSetSoap>(models.size());

		for (RecentLayoutSet model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new RecentLayoutSetSoap[soapModels.size()]);
	}

	public RecentLayoutSetSoap() {
	}

	public long getPrimaryKey() {
		return _recentLayoutSetId;
	}

	public void setPrimaryKey(long pk) {
		setRecentLayoutSetId(pk);
	}

	public long getMvccVersion() {
		return _mvccVersion;
	}

	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	public long getRecentLayoutSetId() {
		return _recentLayoutSetId;
	}

	public void setRecentLayoutSetId(long recentLayoutSetId) {
		_recentLayoutSetId = recentLayoutSetId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public long getLayoutSetId() {
		return _layoutSetId;
	}

	public void setLayoutSetId(long layoutSetId) {
		_layoutSetId = layoutSetId;
	}

	public long getLayoutSetBranchId() {
		return _layoutSetBranchId;
	}

	public void setLayoutSetBranchId(long layoutSetBranchId) {
		_layoutSetBranchId = layoutSetBranchId;
	}

	private long _mvccVersion;
	private long _recentLayoutSetId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private long _layoutSetId;
	private long _layoutSetBranchId;
}