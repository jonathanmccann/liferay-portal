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

import com.liferay.gsportal.core.model.Engagement;

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
 * The cache model class for representing Engagement in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Engagement
 * @generated
 */
@ProviderType
public class EngagementCacheModel implements CacheModel<Engagement>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EngagementCacheModel)) {
			return false;
		}

		EngagementCacheModel engagementCacheModel = (EngagementCacheModel)obj;

		if (engagementId == engagementCacheModel.engagementId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, engagementId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{engagementId=");
		sb.append(engagementId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", calendarBookingId=");
		sb.append(calendarBookingId);
		sb.append(", clientId=");
		sb.append(clientId);
		sb.append(", projectId=");
		sb.append(projectId);
		sb.append(", title=");
		sb.append(title);
		sb.append(", description=");
		sb.append(description);
		sb.append(", leadUserId=");
		sb.append(leadUserId);
		sb.append(", leadName=");
		sb.append(leadName);
		sb.append(", typeCategoryId=");
		sb.append(typeCategoryId);
		sb.append(", difficultyId=");
		sb.append(difficultyId);
		sb.append(", progressStatusId=");
		sb.append(progressStatusId);
		sb.append(", approvalStatusId=");
		sb.append(approvalStatusId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Engagement toEntityModel() {
		EngagementImpl engagementImpl = new EngagementImpl();

		engagementImpl.setEngagementId(engagementId);
		engagementImpl.setCompanyId(companyId);

		if (createDate == Long.MIN_VALUE) {
			engagementImpl.setCreateDate(null);
		}
		else {
			engagementImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			engagementImpl.setModifiedDate(null);
		}
		else {
			engagementImpl.setModifiedDate(new Date(modifiedDate));
		}

		engagementImpl.setCalendarBookingId(calendarBookingId);
		engagementImpl.setClientId(clientId);
		engagementImpl.setProjectId(projectId);

		if (title == null) {
			engagementImpl.setTitle(StringPool.BLANK);
		}
		else {
			engagementImpl.setTitle(title);
		}

		if (description == null) {
			engagementImpl.setDescription(StringPool.BLANK);
		}
		else {
			engagementImpl.setDescription(description);
		}

		engagementImpl.setLeadUserId(leadUserId);

		if (leadName == null) {
			engagementImpl.setLeadName(StringPool.BLANK);
		}
		else {
			engagementImpl.setLeadName(leadName);
		}

		engagementImpl.setTypeCategoryId(typeCategoryId);
		engagementImpl.setDifficultyId(difficultyId);
		engagementImpl.setProgressStatusId(progressStatusId);
		engagementImpl.setApprovalStatusId(approvalStatusId);

		engagementImpl.resetOriginalValues();

		return engagementImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		engagementId = objectInput.readLong();

		companyId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		calendarBookingId = objectInput.readLong();

		clientId = objectInput.readLong();

		projectId = objectInput.readLong();
		title = objectInput.readUTF();
		description = objectInput.readUTF();

		leadUserId = objectInput.readLong();
		leadName = objectInput.readUTF();

		typeCategoryId = objectInput.readLong();

		difficultyId = objectInput.readInt();

		progressStatusId = objectInput.readInt();

		approvalStatusId = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(engagementId);

		objectOutput.writeLong(companyId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(calendarBookingId);

		objectOutput.writeLong(clientId);

		objectOutput.writeLong(projectId);

		if (title == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(title);
		}

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}

		objectOutput.writeLong(leadUserId);

		if (leadName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(leadName);
		}

		objectOutput.writeLong(typeCategoryId);

		objectOutput.writeInt(difficultyId);

		objectOutput.writeInt(progressStatusId);

		objectOutput.writeInt(approvalStatusId);
	}

	public long engagementId;
	public long companyId;
	public long createDate;
	public long modifiedDate;
	public long calendarBookingId;
	public long clientId;
	public long projectId;
	public String title;
	public String description;
	public long leadUserId;
	public String leadName;
	public long typeCategoryId;
	public int difficultyId;
	public int progressStatusId;
	public int approvalStatusId;
}