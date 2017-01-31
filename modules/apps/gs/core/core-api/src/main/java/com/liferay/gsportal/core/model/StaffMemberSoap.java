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
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class StaffMemberSoap implements Serializable {
	public static StaffMemberSoap toSoapModel(StaffMember model) {
		StaffMemberSoap soapModel = new StaffMemberSoap();

		soapModel.setUserId(model.getUserId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setEmployeeType(model.getEmployeeType());
		soapModel.setEmployerName(model.getEmployerName());

		return soapModel;
	}

	public static StaffMemberSoap[] toSoapModels(StaffMember[] models) {
		StaffMemberSoap[] soapModels = new StaffMemberSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static StaffMemberSoap[][] toSoapModels(StaffMember[][] models) {
		StaffMemberSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new StaffMemberSoap[models.length][models[0].length];
		}
		else {
			soapModels = new StaffMemberSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static StaffMemberSoap[] toSoapModels(List<StaffMember> models) {
		List<StaffMemberSoap> soapModels = new ArrayList<StaffMemberSoap>(models.size());

		for (StaffMember model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new StaffMemberSoap[soapModels.size()]);
	}

	public StaffMemberSoap() {
	}

	public long getPrimaryKey() {
		return _userId;
	}

	public void setPrimaryKey(long pk) {
		setUserId(pk);
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public int getEmployeeType() {
		return _employeeType;
	}

	public void setEmployeeType(int employeeType) {
		_employeeType = employeeType;
	}

	public String getEmployerName() {
		return _employerName;
	}

	public void setEmployerName(String employerName) {
		_employerName = employerName;
	}

	private long _userId;
	private long _companyId;
	private int _employeeType;
	private String _employerName;
}