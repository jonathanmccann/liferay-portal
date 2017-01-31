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

import com.liferay.gsportal.core.model.StaffMember;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing StaffMember in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see StaffMember
 * @generated
 */
@ProviderType
public class StaffMemberCacheModel implements CacheModel<StaffMember>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StaffMemberCacheModel)) {
			return false;
		}

		StaffMemberCacheModel staffMemberCacheModel = (StaffMemberCacheModel)obj;

		if (userId == staffMemberCacheModel.userId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, userId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{userId=");
		sb.append(userId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", employeeType=");
		sb.append(employeeType);
		sb.append(", employerName=");
		sb.append(employerName);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public StaffMember toEntityModel() {
		StaffMemberImpl staffMemberImpl = new StaffMemberImpl();

		staffMemberImpl.setUserId(userId);
		staffMemberImpl.setCompanyId(companyId);
		staffMemberImpl.setEmployeeType(employeeType);

		if (employerName == null) {
			staffMemberImpl.setEmployerName(StringPool.BLANK);
		}
		else {
			staffMemberImpl.setEmployerName(employerName);
		}

		staffMemberImpl.resetOriginalValues();

		return staffMemberImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		userId = objectInput.readLong();

		companyId = objectInput.readLong();

		employeeType = objectInput.readInt();
		employerName = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(userId);

		objectOutput.writeLong(companyId);

		objectOutput.writeInt(employeeType);

		if (employerName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(employerName);
		}
	}

	public long userId;
	public long companyId;
	public int employeeType;
	public String employerName;
}