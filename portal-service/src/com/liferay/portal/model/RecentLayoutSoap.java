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
public class RecentLayoutSoap implements Serializable {
	public static RecentLayoutSoap toSoapModel(RecentLayout model) {
		RecentLayoutSoap soapModel = new RecentLayoutSoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setRecentLayoutId(model.getRecentLayoutId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setLayoutSetBranchId(model.getLayoutSetBranchId());
		soapModel.setPlid(model.getPlid());
		soapModel.setLayoutBranchId(model.getLayoutBranchId());
		soapModel.setLayoutRevisionId(model.getLayoutRevisionId());

		return soapModel;
	}

	public static RecentLayoutSoap[] toSoapModels(RecentLayout[] models) {
		RecentLayoutSoap[] soapModels = new RecentLayoutSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static RecentLayoutSoap[][] toSoapModels(RecentLayout[][] models) {
		RecentLayoutSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new RecentLayoutSoap[models.length][models[0].length];
		}
		else {
			soapModels = new RecentLayoutSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static RecentLayoutSoap[] toSoapModels(List<RecentLayout> models) {
		List<RecentLayoutSoap> soapModels = new ArrayList<RecentLayoutSoap>(models.size());

		for (RecentLayout model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new RecentLayoutSoap[soapModels.size()]);
	}

	public RecentLayoutSoap() {
	}

	public long getPrimaryKey() {
		return _recentLayoutId;
	}

	public void setPrimaryKey(long pk) {
		setRecentLayoutId(pk);
	}

	public long getMvccVersion() {
		return _mvccVersion;
	}

	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	public long getRecentLayoutId() {
		return _recentLayoutId;
	}

	public void setRecentLayoutId(long recentLayoutId) {
		_recentLayoutId = recentLayoutId;
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

	public long getLayoutSetBranchId() {
		return _layoutSetBranchId;
	}

	public void setLayoutSetBranchId(long layoutSetBranchId) {
		_layoutSetBranchId = layoutSetBranchId;
	}

	public long getPlid() {
		return _plid;
	}

	public void setPlid(long plid) {
		_plid = plid;
	}

	public long getLayoutBranchId() {
		return _layoutBranchId;
	}

	public void setLayoutBranchId(long layoutBranchId) {
		_layoutBranchId = layoutBranchId;
	}

	public long getLayoutRevisionId() {
		return _layoutRevisionId;
	}

	public void setLayoutRevisionId(long layoutRevisionId) {
		_layoutRevisionId = layoutRevisionId;
	}

	private long _mvccVersion;
	private long _recentLayoutId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private long _layoutSetBranchId;
	private long _plid;
	private long _layoutBranchId;
	private long _layoutRevisionId;
}