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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class MemberOfSoap implements Serializable {
	public static MemberOfSoap toSoapModel(MemberOf model) {
		MemberOfSoap soapModel = new MemberOfSoap();

		soapModel.setMemberOfId(model.getMemberOfId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setUserId(model.getUserId());
		soapModel.setClientId(model.getClientId());
		soapModel.setProjectId(model.getProjectId());
		soapModel.setEngagementId(model.getEngagementId());
		soapModel.setMemberRoleId(model.getMemberRoleId());

		return soapModel;
	}

	public static MemberOfSoap[] toSoapModels(MemberOf[] models) {
		MemberOfSoap[] soapModels = new MemberOfSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static MemberOfSoap[][] toSoapModels(MemberOf[][] models) {
		MemberOfSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new MemberOfSoap[models.length][models[0].length];
		}
		else {
			soapModels = new MemberOfSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static MemberOfSoap[] toSoapModels(List<MemberOf> models) {
		List<MemberOfSoap> soapModels = new ArrayList<MemberOfSoap>(models.size());

		for (MemberOf model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new MemberOfSoap[soapModels.size()]);
	}

	public MemberOfSoap() {
	}

	public long getPrimaryKey() {
		return _memberOfId;
	}

	public void setPrimaryKey(long pk) {
		setMemberOfId(pk);
	}

	public long getMemberOfId() {
		return _memberOfId;
	}

	public void setMemberOfId(long memberOfId) {
		_memberOfId = memberOfId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public long getClientId() {
		return _clientId;
	}

	public void setClientId(long clientId) {
		_clientId = clientId;
	}

	public long getProjectId() {
		return _projectId;
	}

	public void setProjectId(long projectId) {
		_projectId = projectId;
	}

	public long getEngagementId() {
		return _engagementId;
	}

	public void setEngagementId(long engagementId) {
		_engagementId = engagementId;
	}

	public int getMemberRoleId() {
		return _memberRoleId;
	}

	public void setMemberRoleId(int memberRoleId) {
		_memberRoleId = memberRoleId;
	}

	private long _memberOfId;
	private long _companyId;
	private Date _createDate;
	private long _userId;
	private long _clientId;
	private long _projectId;
	private long _engagementId;
	private int _memberRoleId;
}