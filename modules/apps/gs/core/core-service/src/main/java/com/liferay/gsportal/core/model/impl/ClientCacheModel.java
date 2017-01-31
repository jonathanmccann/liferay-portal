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

import com.liferay.gsportal.core.model.Client;

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
 * The cache model class for representing Client in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Client
 * @generated
 */
@ProviderType
public class ClientCacheModel implements CacheModel<Client>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ClientCacheModel)) {
			return false;
		}

		ClientCacheModel clientCacheModel = (ClientCacheModel)obj;

		if (clientId == clientCacheModel.clientId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, clientId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{clientId=");
		sb.append(clientId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", parentClientId=");
		sb.append(parentClientId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", dashboardUrl=");
		sb.append(dashboardUrl);
		sb.append(", logoId=");
		sb.append(logoId);
		sb.append(", logoUrl=");
		sb.append(logoUrl);
		sb.append(", websiteUrl=");
		sb.append(websiteUrl);
		sb.append(", addressId=");
		sb.append(addressId);
		sb.append(", active=");
		sb.append(active);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Client toEntityModel() {
		ClientImpl clientImpl = new ClientImpl();

		clientImpl.setClientId(clientId);
		clientImpl.setCompanyId(companyId);

		if (createDate == Long.MIN_VALUE) {
			clientImpl.setCreateDate(null);
		}
		else {
			clientImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			clientImpl.setModifiedDate(null);
		}
		else {
			clientImpl.setModifiedDate(new Date(modifiedDate));
		}

		clientImpl.setParentClientId(parentClientId);

		if (name == null) {
			clientImpl.setName(StringPool.BLANK);
		}
		else {
			clientImpl.setName(name);
		}

		if (description == null) {
			clientImpl.setDescription(StringPool.BLANK);
		}
		else {
			clientImpl.setDescription(description);
		}

		if (dashboardUrl == null) {
			clientImpl.setDashboardUrl(StringPool.BLANK);
		}
		else {
			clientImpl.setDashboardUrl(dashboardUrl);
		}

		clientImpl.setLogoId(logoId);

		if (logoUrl == null) {
			clientImpl.setLogoUrl(StringPool.BLANK);
		}
		else {
			clientImpl.setLogoUrl(logoUrl);
		}

		if (websiteUrl == null) {
			clientImpl.setWebsiteUrl(StringPool.BLANK);
		}
		else {
			clientImpl.setWebsiteUrl(websiteUrl);
		}

		clientImpl.setAddressId(addressId);
		clientImpl.setActive(active);

		clientImpl.resetOriginalValues();

		return clientImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		clientId = objectInput.readLong();

		companyId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		parentClientId = objectInput.readLong();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
		dashboardUrl = objectInput.readUTF();

		logoId = objectInput.readLong();
		logoUrl = objectInput.readUTF();
		websiteUrl = objectInput.readUTF();

		addressId = objectInput.readLong();

		active = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(clientId);

		objectOutput.writeLong(companyId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(parentClientId);

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

		objectOutput.writeLong(logoId);

		if (logoUrl == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(logoUrl);
		}

		if (websiteUrl == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(websiteUrl);
		}

		objectOutput.writeLong(addressId);

		objectOutput.writeBoolean(active);
	}

	public long clientId;
	public long companyId;
	public long createDate;
	public long modifiedDate;
	public long parentClientId;
	public String name;
	public String description;
	public String dashboardUrl;
	public long logoId;
	public String logoUrl;
	public String websiteUrl;
	public long addressId;
	public boolean active;
}