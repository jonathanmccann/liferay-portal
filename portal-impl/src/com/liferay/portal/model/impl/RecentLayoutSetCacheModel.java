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

package com.liferay.portal.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.MVCCModel;
import com.liferay.portal.model.RecentLayoutSet;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing RecentLayoutSet in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see RecentLayoutSet
 * @generated
 */
@ProviderType
public class RecentLayoutSetCacheModel implements CacheModel<RecentLayoutSet>,
	Externalizable, MVCCModel {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof RecentLayoutSetCacheModel)) {
			return false;
		}

		RecentLayoutSetCacheModel recentLayoutSetCacheModel = (RecentLayoutSetCacheModel)obj;

		if ((recentLayoutSetId == recentLayoutSetCacheModel.recentLayoutSetId) &&
				(mvccVersion == recentLayoutSetCacheModel.mvccVersion)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, recentLayoutSetId);

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
		StringBundler sb = new StringBundler(15);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", recentLayoutSetId=");
		sb.append(recentLayoutSetId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", layoutSetId=");
		sb.append(layoutSetId);
		sb.append(", layoutSetBranchId=");
		sb.append(layoutSetBranchId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public RecentLayoutSet toEntityModel() {
		RecentLayoutSetImpl recentLayoutSetImpl = new RecentLayoutSetImpl();

		recentLayoutSetImpl.setMvccVersion(mvccVersion);
		recentLayoutSetImpl.setRecentLayoutSetId(recentLayoutSetId);
		recentLayoutSetImpl.setGroupId(groupId);
		recentLayoutSetImpl.setCompanyId(companyId);
		recentLayoutSetImpl.setUserId(userId);
		recentLayoutSetImpl.setLayoutSetId(layoutSetId);
		recentLayoutSetImpl.setLayoutSetBranchId(layoutSetBranchId);

		recentLayoutSetImpl.resetOriginalValues();

		return recentLayoutSetImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();
		recentLayoutSetId = objectInput.readLong();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		layoutSetId = objectInput.readLong();
		layoutSetBranchId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(mvccVersion);
		objectOutput.writeLong(recentLayoutSetId);
		objectOutput.writeLong(groupId);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(userId);
		objectOutput.writeLong(layoutSetId);
		objectOutput.writeLong(layoutSetBranchId);
	}

	public long mvccVersion;
	public long recentLayoutSetId;
	public long groupId;
	public long companyId;
	public long userId;
	public long layoutSetId;
	public long layoutSetBranchId;
}