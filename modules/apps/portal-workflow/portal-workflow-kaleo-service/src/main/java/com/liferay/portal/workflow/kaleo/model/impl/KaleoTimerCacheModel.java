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

package com.liferay.portal.workflow.kaleo.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.workflow.kaleo.model.KaleoTimer;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing KaleoTimer in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class KaleoTimerCacheModel
	implements CacheModel<KaleoTimer>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof KaleoTimerCacheModel)) {
			return false;
		}

		KaleoTimerCacheModel kaleoTimerCacheModel = (KaleoTimerCacheModel)obj;

		if ((kaleoTimerId == kaleoTimerCacheModel.kaleoTimerId) &&
			(mvccVersion == kaleoTimerCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, kaleoTimerId);

		return HashUtil.hash(hashCode, mvccVersion);
	}

	@Override
	public long getMvccVersion() {
		return mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		this.mvccVersion = mvccVersion;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(37);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", kaleoTimerId=");
		sb.append(kaleoTimerId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", kaleoClassName=");
		sb.append(kaleoClassName);
		sb.append(", kaleoClassPK=");
		sb.append(kaleoClassPK);
		sb.append(", kaleoDefinitionVersionId=");
		sb.append(kaleoDefinitionVersionId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", blocking=");
		sb.append(blocking);
		sb.append(", description=");
		sb.append(description);
		sb.append(", duration=");
		sb.append(duration);
		sb.append(", scale=");
		sb.append(scale);
		sb.append(", recurrenceDuration=");
		sb.append(recurrenceDuration);
		sb.append(", recurrenceScale=");
		sb.append(recurrenceScale);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public KaleoTimer toEntityModel() {
		KaleoTimerImpl kaleoTimerImpl = new KaleoTimerImpl();

		kaleoTimerImpl.setMvccVersion(mvccVersion);
		kaleoTimerImpl.setKaleoTimerId(kaleoTimerId);
		kaleoTimerImpl.setGroupId(groupId);
		kaleoTimerImpl.setCompanyId(companyId);
		kaleoTimerImpl.setUserId(userId);

		if (userName == null) {
			kaleoTimerImpl.setUserName("");
		}
		else {
			kaleoTimerImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			kaleoTimerImpl.setCreateDate(null);
		}
		else {
			kaleoTimerImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			kaleoTimerImpl.setModifiedDate(null);
		}
		else {
			kaleoTimerImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (kaleoClassName == null) {
			kaleoTimerImpl.setKaleoClassName("");
		}
		else {
			kaleoTimerImpl.setKaleoClassName(kaleoClassName);
		}

		kaleoTimerImpl.setKaleoClassPK(kaleoClassPK);
		kaleoTimerImpl.setKaleoDefinitionVersionId(kaleoDefinitionVersionId);

		if (name == null) {
			kaleoTimerImpl.setName("");
		}
		else {
			kaleoTimerImpl.setName(name);
		}

		kaleoTimerImpl.setBlocking(blocking);

		if (description == null) {
			kaleoTimerImpl.setDescription("");
		}
		else {
			kaleoTimerImpl.setDescription(description);
		}

		kaleoTimerImpl.setDuration(duration);

		if (scale == null) {
			kaleoTimerImpl.setScale("");
		}
		else {
			kaleoTimerImpl.setScale(scale);
		}

		kaleoTimerImpl.setRecurrenceDuration(recurrenceDuration);

		if (recurrenceScale == null) {
			kaleoTimerImpl.setRecurrenceScale("");
		}
		else {
			kaleoTimerImpl.setRecurrenceScale(recurrenceScale);
		}

		kaleoTimerImpl.resetOriginalValues();

		return kaleoTimerImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput)
		throws ClassNotFoundException, IOException {

		mvccVersion = objectInput.readLong();

		kaleoTimerId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = (String)objectInput.readObject();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		kaleoClassName = (String)objectInput.readObject();

		kaleoClassPK = objectInput.readLong();

		kaleoDefinitionVersionId = objectInput.readLong();
		name = (String)objectInput.readObject();

		blocking = objectInput.readBoolean();
		description = (String)objectInput.readObject();

		duration = objectInput.readDouble();
		scale = (String)objectInput.readObject();

		recurrenceDuration = objectInput.readDouble();
		recurrenceScale = (String)objectInput.readObject();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(kaleoTimerId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (kaleoClassName == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(kaleoClassName);
		}

		objectOutput.writeLong(kaleoClassPK);

		objectOutput.writeLong(kaleoDefinitionVersionId);

		if (name == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(name);
		}

		objectOutput.writeBoolean(blocking);

		if (description == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(description);
		}

		objectOutput.writeDouble(duration);

		if (scale == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(scale);
		}

		objectOutput.writeDouble(recurrenceDuration);

		if (recurrenceScale == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(recurrenceScale);
		}
	}

	public long mvccVersion;
	public long kaleoTimerId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String kaleoClassName;
	public long kaleoClassPK;
	public long kaleoDefinitionVersionId;
	public String name;
	public boolean blocking;
	public String description;
	public double duration;
	public String scale;
	public double recurrenceDuration;
	public String recurrenceScale;

}