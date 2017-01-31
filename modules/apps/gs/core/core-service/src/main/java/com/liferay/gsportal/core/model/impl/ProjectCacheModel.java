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

package com.liferay.gsportal.core.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.gsportal.core.model.Project;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Project in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Project
 * @generated
 */
@ProviderType
public class ProjectCacheModel implements CacheModel<Project>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ProjectCacheModel)) {
			return false;
		}

		ProjectCacheModel projectCacheModel = (ProjectCacheModel)obj;

		if (projectId == projectCacheModel.projectId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, projectId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{projectId=");
		sb.append(projectId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", clientId=");
		sb.append(clientId);
		sb.append(", calendarId=");
		sb.append(calendarId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", dashboardUrl=");
		sb.append(dashboardUrl);
		sb.append(", lesaProjectKey=");
		sb.append(lesaProjectKey);
		sb.append(", logoId=");
		sb.append(logoId);
		sb.append(", logoUrl=");
		sb.append(logoUrl);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Project toEntityModel() {
		ProjectImpl projectImpl = new ProjectImpl();

		projectImpl.setProjectId(projectId);
		projectImpl.setCompanyId(companyId);

		if (createDate == Long.MIN_VALUE) {
			projectImpl.setCreateDate(null);
		}
		else {
			projectImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			projectImpl.setModifiedDate(null);
		}
		else {
			projectImpl.setModifiedDate(new Date(modifiedDate));
		}

		projectImpl.setClientId(clientId);
		projectImpl.setCalendarId(calendarId);

		if (name == null) {
			projectImpl.setName(StringPool.BLANK);
		}
		else {
			projectImpl.setName(name);
		}

		if (description == null) {
			projectImpl.setDescription(StringPool.BLANK);
		}
		else {
			projectImpl.setDescription(description);
		}

		if (dashboardUrl == null) {
			projectImpl.setDashboardUrl(StringPool.BLANK);
		}
		else {
			projectImpl.setDashboardUrl(dashboardUrl);
		}

		if (lesaProjectKey == null) {
			projectImpl.setLesaProjectKey(StringPool.BLANK);
		}
		else {
			projectImpl.setLesaProjectKey(lesaProjectKey);
		}

		projectImpl.setLogoId(logoId);

		if (logoUrl == null) {
			projectImpl.setLogoUrl(StringPool.BLANK);
		}
		else {
			projectImpl.setLogoUrl(logoUrl);
		}

		projectImpl.resetOriginalValues();

		return projectImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		projectId = objectInput.readLong();

		companyId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		clientId = objectInput.readLong();

		calendarId = objectInput.readLong();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
		dashboardUrl = objectInput.readUTF();
		lesaProjectKey = objectInput.readUTF();

		logoId = objectInput.readLong();
		logoUrl = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(projectId);

		objectOutput.writeLong(companyId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(clientId);

		objectOutput.writeLong(calendarId);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (dashboardUrl == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(dashboardUrl);
		}

		if (lesaProjectKey == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(lesaProjectKey);
		}

		objectOutput.writeLong(logoId);

		if (logoUrl == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(logoUrl);
		}
	}

	public long projectId;
	public long companyId;
	public long createDate;
	public long modifiedDate;
	public long clientId;
	public long calendarId;
	public String name;
	public String description;
	public String dashboardUrl;
	public String lesaProjectKey;
	public long logoId;
	public String logoUrl;
}