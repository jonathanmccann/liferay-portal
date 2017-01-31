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

import com.liferay.gsportal.core.model.MemberOf;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing MemberOf in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see MemberOf
 * @generated
 */
@ProviderType
public class MemberOfCacheModel implements CacheModel<MemberOf>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MemberOfCacheModel)) {
			return false;
		}

		MemberOfCacheModel memberOfCacheModel = (MemberOfCacheModel)obj;

		if (memberOfId == memberOfCacheModel.memberOfId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, memberOfId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{memberOfId=");
		sb.append(memberOfId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", clientId=");
		sb.append(clientId);
		sb.append(", projectId=");
		sb.append(projectId);
		sb.append(", engagementId=");
		sb.append(engagementId);
		sb.append(", memberRoleId=");
		sb.append(memberRoleId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public MemberOf toEntityModel() {
		MemberOfImpl memberOfImpl = new MemberOfImpl();

		memberOfImpl.setMemberOfId(memberOfId);
		memberOfImpl.setCompanyId(companyId);

		if (createDate == Long.MIN_VALUE) {
			memberOfImpl.setCreateDate(null);
		}
		else {
			memberOfImpl.setCreateDate(new Date(createDate));
		}

		memberOfImpl.setUserId(userId);
		memberOfImpl.setClientId(clientId);
		memberOfImpl.setProjectId(projectId);
		memberOfImpl.setEngagementId(engagementId);
		memberOfImpl.setMemberRoleId(memberRoleId);

		memberOfImpl.resetOriginalValues();

		return memberOfImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		memberOfId = objectInput.readLong();

		companyId = objectInput.readLong();
		createDate = objectInput.readLong();

		userId = objectInput.readLong();

		clientId = objectInput.readLong();

		projectId = objectInput.readLong();

		engagementId = objectInput.readLong();

		memberRoleId = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(memberOfId);

		objectOutput.writeLong(companyId);
		objectOutput.writeLong(createDate);

		objectOutput.writeLong(userId);

		objectOutput.writeLong(clientId);

		objectOutput.writeLong(projectId);

		objectOutput.writeLong(engagementId);

		objectOutput.writeInt(memberRoleId);
	}

	public long memberOfId;
	public long companyId;
	public long createDate;
	public long userId;
	public long clientId;
	public long projectId;
	public long engagementId;
	public int memberRoleId;
}