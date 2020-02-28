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

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.MVCCModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Contact in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ContactCacheModel
	implements CacheModel<Contact>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ContactCacheModel)) {
			return false;
		}

		ContactCacheModel contactCacheModel = (ContactCacheModel)obj;

		if ((contactId == contactCacheModel.contactId) &&
			(mvccVersion == contactCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, contactId);

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
		StringBundler sb = new StringBundler(59);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", contactId=");
		sb.append(contactId);
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
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", accountId=");
		sb.append(accountId);
		sb.append(", parentContactId=");
		sb.append(parentContactId);
		sb.append(", emailAddress=");
		sb.append(emailAddress);
		sb.append(", firstName=");
		sb.append(firstName);
		sb.append(", middleName=");
		sb.append(middleName);
		sb.append(", lastName=");
		sb.append(lastName);
		sb.append(", prefixId=");
		sb.append(prefixId);
		sb.append(", suffixId=");
		sb.append(suffixId);
		sb.append(", male=");
		sb.append(male);
		sb.append(", birthday=");
		sb.append(birthday);
		sb.append(", smsSn=");
		sb.append(smsSn);
		sb.append(", facebookSn=");
		sb.append(facebookSn);
		sb.append(", jabberSn=");
		sb.append(jabberSn);
		sb.append(", skypeSn=");
		sb.append(skypeSn);
		sb.append(", twitterSn=");
		sb.append(twitterSn);
		sb.append(", employeeStatusId=");
		sb.append(employeeStatusId);
		sb.append(", employeeNumber=");
		sb.append(employeeNumber);
		sb.append(", jobTitle=");
		sb.append(jobTitle);
		sb.append(", jobClass=");
		sb.append(jobClass);
		sb.append(", hoursOfOperation=");
		sb.append(hoursOfOperation);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Contact toEntityModel() {
		ContactImpl contactImpl = new ContactImpl();

		contactImpl.setMvccVersion(mvccVersion);
		contactImpl.setContactId(contactId);
		contactImpl.setCompanyId(companyId);
		contactImpl.setUserId(userId);

		if (userName == null) {
			contactImpl.setUserName("");
		}
		else {
			contactImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			contactImpl.setCreateDate(null);
		}
		else {
			contactImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			contactImpl.setModifiedDate(null);
		}
		else {
			contactImpl.setModifiedDate(new Date(modifiedDate));
		}

		contactImpl.setClassNameId(classNameId);
		contactImpl.setClassPK(classPK);
		contactImpl.setAccountId(accountId);
		contactImpl.setParentContactId(parentContactId);

		if (emailAddress == null) {
			contactImpl.setEmailAddress("");
		}
		else {
			contactImpl.setEmailAddress(emailAddress);
		}

		if (firstName == null) {
			contactImpl.setFirstName("");
		}
		else {
			contactImpl.setFirstName(firstName);
		}

		if (middleName == null) {
			contactImpl.setMiddleName("");
		}
		else {
			contactImpl.setMiddleName(middleName);
		}

		if (lastName == null) {
			contactImpl.setLastName("");
		}
		else {
			contactImpl.setLastName(lastName);
		}

		contactImpl.setPrefixId(prefixId);
		contactImpl.setSuffixId(suffixId);
		contactImpl.setMale(male);

		if (birthday == Long.MIN_VALUE) {
			contactImpl.setBirthday(null);
		}
		else {
			contactImpl.setBirthday(new Date(birthday));
		}

		if (smsSn == null) {
			contactImpl.setSmsSn("");
		}
		else {
			contactImpl.setSmsSn(smsSn);
		}

		if (facebookSn == null) {
			contactImpl.setFacebookSn("");
		}
		else {
			contactImpl.setFacebookSn(facebookSn);
		}

		if (jabberSn == null) {
			contactImpl.setJabberSn("");
		}
		else {
			contactImpl.setJabberSn(jabberSn);
		}

		if (skypeSn == null) {
			contactImpl.setSkypeSn("");
		}
		else {
			contactImpl.setSkypeSn(skypeSn);
		}

		if (twitterSn == null) {
			contactImpl.setTwitterSn("");
		}
		else {
			contactImpl.setTwitterSn(twitterSn);
		}

		if (employeeStatusId == null) {
			contactImpl.setEmployeeStatusId("");
		}
		else {
			contactImpl.setEmployeeStatusId(employeeStatusId);
		}

		if (employeeNumber == null) {
			contactImpl.setEmployeeNumber("");
		}
		else {
			contactImpl.setEmployeeNumber(employeeNumber);
		}

		if (jobTitle == null) {
			contactImpl.setJobTitle("");
		}
		else {
			contactImpl.setJobTitle(jobTitle);
		}

		if (jobClass == null) {
			contactImpl.setJobClass("");
		}
		else {
			contactImpl.setJobClass(jobClass);
		}

		if (hoursOfOperation == null) {
			contactImpl.setHoursOfOperation("");
		}
		else {
			contactImpl.setHoursOfOperation(hoursOfOperation);
		}

		contactImpl.resetOriginalValues();

		return contactImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput)
		throws ClassNotFoundException, IOException {

		mvccVersion = objectInput.readLong();

		contactId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = (String)objectInput.readObject();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();

		accountId = objectInput.readLong();

		parentContactId = objectInput.readLong();
		emailAddress = (String)objectInput.readObject();
		firstName = (String)objectInput.readObject();
		middleName = (String)objectInput.readObject();
		lastName = (String)objectInput.readObject();

		prefixId = objectInput.readLong();

		suffixId = objectInput.readLong();

		male = objectInput.readBoolean();
		birthday = objectInput.readLong();
		smsSn = (String)objectInput.readObject();
		facebookSn = (String)objectInput.readObject();
		jabberSn = (String)objectInput.readObject();
		skypeSn = (String)objectInput.readObject();
		twitterSn = (String)objectInput.readObject();
		employeeStatusId = (String)objectInput.readObject();
		employeeNumber = (String)objectInput.readObject();
		jobTitle = (String)objectInput.readObject();
		jobClass = (String)objectInput.readObject();
		hoursOfOperation = (String)objectInput.readObject();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(contactId);

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

		objectOutput.writeLong(classNameId);

		objectOutput.writeLong(classPK);

		objectOutput.writeLong(accountId);

		objectOutput.writeLong(parentContactId);

		if (emailAddress == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(emailAddress);
		}

		if (firstName == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(firstName);
		}

		if (middleName == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(middleName);
		}

		if (lastName == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(lastName);
		}

		objectOutput.writeLong(prefixId);

		objectOutput.writeLong(suffixId);

		objectOutput.writeBoolean(male);
		objectOutput.writeLong(birthday);

		if (smsSn == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(smsSn);
		}

		if (facebookSn == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(facebookSn);
		}

		if (jabberSn == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(jabberSn);
		}

		if (skypeSn == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(skypeSn);
		}

		if (twitterSn == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(twitterSn);
		}

		if (employeeStatusId == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(employeeStatusId);
		}

		if (employeeNumber == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(employeeNumber);
		}

		if (jobTitle == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(jobTitle);
		}

		if (jobClass == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(jobClass);
		}

		if (hoursOfOperation == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(hoursOfOperation);
		}
	}

	public long mvccVersion;
	public long contactId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long classNameId;
	public long classPK;
	public long accountId;
	public long parentContactId;
	public String emailAddress;
	public String firstName;
	public String middleName;
	public String lastName;
	public long prefixId;
	public long suffixId;
	public boolean male;
	public long birthday;
	public String smsSn;
	public String facebookSn;
	public String jabberSn;
	public String skypeSn;
	public String twitterSn;
	public String employeeStatusId;
	public String employeeNumber;
	public String jobTitle;
	public String jobClass;
	public String hoursOfOperation;

}