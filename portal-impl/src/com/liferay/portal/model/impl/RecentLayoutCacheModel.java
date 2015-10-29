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
import com.liferay.portal.model.RecentLayout;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing RecentLayout in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see RecentLayout
 * @generated
 */
@ProviderType
public class RecentLayoutCacheModel implements CacheModel<RecentLayout>,
	Externalizable, MVCCModel {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof RecentLayoutCacheModel)) {
			return false;
		}

		RecentLayoutCacheModel recentLayoutCacheModel = (RecentLayoutCacheModel)obj;

		if ((recentLayoutId == recentLayoutCacheModel.recentLayoutId) &&
				(mvccVersion == recentLayoutCacheModel.mvccVersion)) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, recentLayoutId);

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
		StringBundler sb = new StringBundler(19);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", recentLayoutId=");
		sb.append(recentLayoutId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", layoutSetBranchId=");
		sb.append(layoutSetBranchId);
		sb.append(", plid=");
		sb.append(plid);
		sb.append(", layoutBranchId=");
		sb.append(layoutBranchId);
		sb.append(", layoutRevisionId=");
		sb.append(layoutRevisionId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public RecentLayout toEntityModel() {
		RecentLayoutImpl recentLayoutImpl = new RecentLayoutImpl();

		recentLayoutImpl.setMvccVersion(mvccVersion);
		recentLayoutImpl.setRecentLayoutId(recentLayoutId);
		recentLayoutImpl.setGroupId(groupId);
		recentLayoutImpl.setCompanyId(companyId);
		recentLayoutImpl.setUserId(userId);
		recentLayoutImpl.setLayoutSetBranchId(layoutSetBranchId);
		recentLayoutImpl.setPlid(plid);
		recentLayoutImpl.setLayoutBranchId(layoutBranchId);
		recentLayoutImpl.setLayoutRevisionId(layoutRevisionId);

		recentLayoutImpl.resetOriginalValues();

		return recentLayoutImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();
		recentLayoutId = objectInput.readLong();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		layoutSetBranchId = objectInput.readLong();
		plid = objectInput.readLong();
		layoutBranchId = objectInput.readLong();
		layoutRevisionId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(mvccVersion);
		objectOutput.writeLong(recentLayoutId);
		objectOutput.writeLong(groupId);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(userId);
		objectOutput.writeLong(layoutSetBranchId);
		objectOutput.writeLong(plid);
		objectOutput.writeLong(layoutBranchId);
		objectOutput.writeLong(layoutRevisionId);
	}

	public long mvccVersion;
	public long recentLayoutId;
	public long groupId;
	public long companyId;
	public long userId;
	public long layoutSetBranchId;
	public long plid;
	public long layoutBranchId;
	public long layoutRevisionId;
}