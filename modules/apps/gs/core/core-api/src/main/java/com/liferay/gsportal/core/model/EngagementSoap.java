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
public class EngagementSoap implements Serializable {
	public static EngagementSoap toSoapModel(Engagement model) {
		EngagementSoap soapModel = new EngagementSoap();

		soapModel.setEngagementId(model.getEngagementId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setCalendarBookingId(model.getCalendarBookingId());
		soapModel.setClientId(model.getClientId());
		soapModel.setProjectId(model.getProjectId());
		soapModel.setTitle(model.getTitle());
		soapModel.setDescription(model.getDescription());
		soapModel.setLeadUserId(model.getLeadUserId());
		soapModel.setLeadName(model.getLeadName());
		soapModel.setTypeCategoryId(model.getTypeCategoryId());
		soapModel.setDifficultyId(model.getDifficultyId());
		soapModel.setProgressStatusId(model.getProgressStatusId());
		soapModel.setApprovalStatusId(model.getApprovalStatusId());

		return soapModel;
	}

	public static EngagementSoap[] toSoapModels(Engagement[] models) {
		EngagementSoap[] soapModels = new EngagementSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static EngagementSoap[][] toSoapModels(Engagement[][] models) {
		EngagementSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new EngagementSoap[models.length][models[0].length];
		}
		else {
			soapModels = new EngagementSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static EngagementSoap[] toSoapModels(List<Engagement> models) {
		List<EngagementSoap> soapModels = new ArrayList<EngagementSoap>(models.size());

		for (Engagement model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new EngagementSoap[soapModels.size()]);
	}

	public EngagementSoap() {
	}

	public long getPrimaryKey() {
		return _engagementId;
	}

	public void setPrimaryKey(long pk) {
		setEngagementId(pk);
	}

	public long getEngagementId() {
		return _engagementId;
	}

	public void setEngagementId(long engagementId) {
		_engagementId = engagementId;
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

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getCalendarBookingId() {
		return _calendarBookingId;
	}

	public void setCalendarBookingId(long calendarBookingId) {
		_calendarBookingId = calendarBookingId;
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

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public long getLeadUserId() {
		return _leadUserId;
	}

	public void setLeadUserId(long leadUserId) {
		_leadUserId = leadUserId;
	}

	public String getLeadName() {
		return _leadName;
	}

	public void setLeadName(String leadName) {
		_leadName = leadName;
	}

	public long getTypeCategoryId() {
		return _typeCategoryId;
	}

	public void setTypeCategoryId(long typeCategoryId) {
		_typeCategoryId = typeCategoryId;
	}

	public int getDifficultyId() {
		return _difficultyId;
	}

	public void setDifficultyId(int difficultyId) {
		_difficultyId = difficultyId;
	}

	public int getProgressStatusId() {
		return _progressStatusId;
	}

	public void setProgressStatusId(int progressStatusId) {
		_progressStatusId = progressStatusId;
	}

	public int getApprovalStatusId() {
		return _approvalStatusId;
	}

	public void setApprovalStatusId(int approvalStatusId) {
		_approvalStatusId = approvalStatusId;
	}

	private long _engagementId;
	private long _companyId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _calendarBookingId;
	private long _clientId;
	private long _projectId;
	private String _title;
	private String _description;
	private long _leadUserId;
	private String _leadName;
	private long _typeCategoryId;
	private int _difficultyId;
	private int _progressStatusId;
	private int _approvalStatusId;
}