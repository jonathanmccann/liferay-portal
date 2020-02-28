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

package com.liferay.journal.model.impl;

import com.liferay.journal.model.JournalFeed;
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing JournalFeed in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class JournalFeedCacheModel
	implements CacheModel<JournalFeed>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof JournalFeedCacheModel)) {
			return false;
		}

		JournalFeedCacheModel journalFeedCacheModel =
			(JournalFeedCacheModel)obj;

		if ((id == journalFeedCacheModel.id) &&
			(mvccVersion == journalFeedCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, id);

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
		StringBundler sb = new StringBundler(49);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", uuid=");
		sb.append(uuid);
		sb.append(", id=");
		sb.append(id);
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
		sb.append(", feedId=");
		sb.append(feedId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", DDMStructureKey=");
		sb.append(DDMStructureKey);
		sb.append(", DDMTemplateKey=");
		sb.append(DDMTemplateKey);
		sb.append(", DDMRendererTemplateKey=");
		sb.append(DDMRendererTemplateKey);
		sb.append(", delta=");
		sb.append(delta);
		sb.append(", orderByCol=");
		sb.append(orderByCol);
		sb.append(", orderByType=");
		sb.append(orderByType);
		sb.append(", targetLayoutFriendlyUrl=");
		sb.append(targetLayoutFriendlyUrl);
		sb.append(", targetPortletId=");
		sb.append(targetPortletId);
		sb.append(", contentField=");
		sb.append(contentField);
		sb.append(", feedFormat=");
		sb.append(feedFormat);
		sb.append(", feedVersion=");
		sb.append(feedVersion);
		sb.append(", lastPublishDate=");
		sb.append(lastPublishDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public JournalFeed toEntityModel() {
		JournalFeedImpl journalFeedImpl = new JournalFeedImpl();

		journalFeedImpl.setMvccVersion(mvccVersion);

		if (uuid == null) {
			journalFeedImpl.setUuid("");
		}
		else {
			journalFeedImpl.setUuid(uuid);
		}

		journalFeedImpl.setId(id);
		journalFeedImpl.setGroupId(groupId);
		journalFeedImpl.setCompanyId(companyId);
		journalFeedImpl.setUserId(userId);

		if (userName == null) {
			journalFeedImpl.setUserName("");
		}
		else {
			journalFeedImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			journalFeedImpl.setCreateDate(null);
		}
		else {
			journalFeedImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			journalFeedImpl.setModifiedDate(null);
		}
		else {
			journalFeedImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (feedId == null) {
			journalFeedImpl.setFeedId("");
		}
		else {
			journalFeedImpl.setFeedId(feedId);
		}

		if (name == null) {
			journalFeedImpl.setName("");
		}
		else {
			journalFeedImpl.setName(name);
		}

		if (description == null) {
			journalFeedImpl.setDescription("");
		}
		else {
			journalFeedImpl.setDescription(description);
		}

		if (DDMStructureKey == null) {
			journalFeedImpl.setDDMStructureKey("");
		}
		else {
			journalFeedImpl.setDDMStructureKey(DDMStructureKey);
		}

		if (DDMTemplateKey == null) {
			journalFeedImpl.setDDMTemplateKey("");
		}
		else {
			journalFeedImpl.setDDMTemplateKey(DDMTemplateKey);
		}

		if (DDMRendererTemplateKey == null) {
			journalFeedImpl.setDDMRendererTemplateKey("");
		}
		else {
			journalFeedImpl.setDDMRendererTemplateKey(DDMRendererTemplateKey);
		}

		journalFeedImpl.setDelta(delta);

		if (orderByCol == null) {
			journalFeedImpl.setOrderByCol("");
		}
		else {
			journalFeedImpl.setOrderByCol(orderByCol);
		}

		if (orderByType == null) {
			journalFeedImpl.setOrderByType("");
		}
		else {
			journalFeedImpl.setOrderByType(orderByType);
		}

		if (targetLayoutFriendlyUrl == null) {
			journalFeedImpl.setTargetLayoutFriendlyUrl("");
		}
		else {
			journalFeedImpl.setTargetLayoutFriendlyUrl(targetLayoutFriendlyUrl);
		}

		if (targetPortletId == null) {
			journalFeedImpl.setTargetPortletId("");
		}
		else {
			journalFeedImpl.setTargetPortletId(targetPortletId);
		}

		if (contentField == null) {
			journalFeedImpl.setContentField("");
		}
		else {
			journalFeedImpl.setContentField(contentField);
		}

		if (feedFormat == null) {
			journalFeedImpl.setFeedFormat("");
		}
		else {
			journalFeedImpl.setFeedFormat(feedFormat);
		}

		journalFeedImpl.setFeedVersion(feedVersion);

		if (lastPublishDate == Long.MIN_VALUE) {
			journalFeedImpl.setLastPublishDate(null);
		}
		else {
			journalFeedImpl.setLastPublishDate(new Date(lastPublishDate));
		}

		journalFeedImpl.resetOriginalValues();

		return journalFeedImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput)
		throws ClassNotFoundException, IOException {

		mvccVersion = objectInput.readLong();
		uuid = (String)objectInput.readObject();

		id = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = (String)objectInput.readObject();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		feedId = (String)objectInput.readObject();
		name = (String)objectInput.readObject();
		description = (String)objectInput.readObject();
		DDMStructureKey = (String)objectInput.readObject();
		DDMTemplateKey = (String)objectInput.readObject();
		DDMRendererTemplateKey = (String)objectInput.readObject();

		delta = objectInput.readInt();
		orderByCol = (String)objectInput.readObject();
		orderByType = (String)objectInput.readObject();
		targetLayoutFriendlyUrl = (String)objectInput.readObject();
		targetPortletId = (String)objectInput.readObject();
		contentField = (String)objectInput.readObject();
		feedFormat = (String)objectInput.readObject();

		feedVersion = objectInput.readDouble();
		lastPublishDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		if (uuid == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(uuid);
		}

		objectOutput.writeLong(id);

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

		if (feedId == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(feedId);
		}

		if (name == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(name);
		}

		if (description == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(description);
		}

		if (DDMStructureKey == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(DDMStructureKey);
		}

		if (DDMTemplateKey == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(DDMTemplateKey);
		}

		if (DDMRendererTemplateKey == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(DDMRendererTemplateKey);
		}

		objectOutput.writeInt(delta);

		if (orderByCol == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(orderByCol);
		}

		if (orderByType == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(orderByType);
		}

		if (targetLayoutFriendlyUrl == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(targetLayoutFriendlyUrl);
		}

		if (targetPortletId == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(targetPortletId);
		}

		if (contentField == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(contentField);
		}

		if (feedFormat == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(feedFormat);
		}

		objectOutput.writeDouble(feedVersion);
		objectOutput.writeLong(lastPublishDate);
	}

	public long mvccVersion;
	public String uuid;
	public long id;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String feedId;
	public String name;
	public String description;
	public String DDMStructureKey;
	public String DDMTemplateKey;
	public String DDMRendererTemplateKey;
	public int delta;
	public String orderByCol;
	public String orderByType;
	public String targetLayoutFriendlyUrl;
	public String targetPortletId;
	public String contentField;
	public String feedFormat;
	public double feedVersion;
	public long lastPublishDate;

}