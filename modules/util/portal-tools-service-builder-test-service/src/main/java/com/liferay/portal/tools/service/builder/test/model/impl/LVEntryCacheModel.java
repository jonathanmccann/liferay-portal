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

package com.liferay.portal.tools.service.builder.test.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.tools.service.builder.test.model.LVEntry;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing LVEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class LVEntryCacheModel
	implements CacheModel<LVEntry>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LVEntryCacheModel)) {
			return false;
		}

		LVEntryCacheModel lvEntryCacheModel = (LVEntryCacheModel)obj;

		if ((lvEntryId == lvEntryCacheModel.lvEntryId) &&
			(mvccVersion == lvEntryCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, lvEntryId);

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
		StringBundler sb = new StringBundler(17);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", uuid=");
		sb.append(uuid);
		sb.append(", headId=");
		sb.append(headId);
		sb.append(", defaultLanguageId=");
		sb.append(defaultLanguageId);
		sb.append(", lvEntryId=");
		sb.append(lvEntryId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", uniqueGroupKey=");
		sb.append(uniqueGroupKey);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public LVEntry toEntityModel() {
		LVEntryImpl lvEntryImpl = new LVEntryImpl();

		lvEntryImpl.setMvccVersion(mvccVersion);

		if (uuid == null) {
			lvEntryImpl.setUuid("");
		}
		else {
			lvEntryImpl.setUuid(uuid);
		}

		lvEntryImpl.setHeadId(headId);
		lvEntryImpl.setHead(head);

		if (defaultLanguageId == null) {
			lvEntryImpl.setDefaultLanguageId("");
		}
		else {
			lvEntryImpl.setDefaultLanguageId(defaultLanguageId);
		}

		lvEntryImpl.setLvEntryId(lvEntryId);
		lvEntryImpl.setCompanyId(companyId);
		lvEntryImpl.setGroupId(groupId);

		if (uniqueGroupKey == null) {
			lvEntryImpl.setUniqueGroupKey("");
		}
		else {
			lvEntryImpl.setUniqueGroupKey(uniqueGroupKey);
		}

		lvEntryImpl.resetOriginalValues();

		return lvEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput)
		throws ClassNotFoundException, IOException {

		mvccVersion = objectInput.readLong();
		uuid = (String)objectInput.readObject();

		headId = objectInput.readLong();

		head = objectInput.readBoolean();
		defaultLanguageId = (String)objectInput.readObject();

		lvEntryId = objectInput.readLong();

		companyId = objectInput.readLong();

		groupId = objectInput.readLong();
		uniqueGroupKey = (String)objectInput.readObject();
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

		objectOutput.writeLong(headId);

		objectOutput.writeBoolean(head);

		if (defaultLanguageId == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(defaultLanguageId);
		}

		objectOutput.writeLong(lvEntryId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(groupId);

		if (uniqueGroupKey == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(uniqueGroupKey);
		}
	}

	public long mvccVersion;
	public String uuid;
	public long headId;
	public boolean head;
	public String defaultLanguageId;
	public long lvEntryId;
	public long companyId;
	public long groupId;
	public String uniqueGroupKey;

}