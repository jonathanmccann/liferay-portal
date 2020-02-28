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
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.kernel.model.User;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing User in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class UserCacheModel
	implements CacheModel<User>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof UserCacheModel)) {
			return false;
		}

		UserCacheModel userCacheModel = (UserCacheModel)obj;

		if ((userId == userCacheModel.userId) &&
			(mvccVersion == userCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, userId);

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
		StringBundler sb = new StringBundler(87);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", uuid=");
		sb.append(uuid);
		sb.append(", externalReferenceCode=");
		sb.append(externalReferenceCode);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", defaultUser=");
		sb.append(defaultUser);
		sb.append(", contactId=");
		sb.append(contactId);
		sb.append(", password=");
		sb.append(password);
		sb.append(", passwordEncrypted=");
		sb.append(passwordEncrypted);
		sb.append(", passwordReset=");
		sb.append(passwordReset);
		sb.append(", passwordModifiedDate=");
		sb.append(passwordModifiedDate);
		sb.append(", digest=");
		sb.append(digest);
		sb.append(", reminderQueryQuestion=");
		sb.append(reminderQueryQuestion);
		sb.append(", reminderQueryAnswer=");
		sb.append(reminderQueryAnswer);
		sb.append(", graceLoginCount=");
		sb.append(graceLoginCount);
		sb.append(", screenName=");
		sb.append(screenName);
		sb.append(", emailAddress=");
		sb.append(emailAddress);
		sb.append(", facebookId=");
		sb.append(facebookId);
		sb.append(", googleUserId=");
		sb.append(googleUserId);
		sb.append(", ldapServerId=");
		sb.append(ldapServerId);
		sb.append(", openId=");
		sb.append(openId);
		sb.append(", portraitId=");
		sb.append(portraitId);
		sb.append(", languageId=");
		sb.append(languageId);
		sb.append(", timeZoneId=");
		sb.append(timeZoneId);
		sb.append(", greeting=");
		sb.append(greeting);
		sb.append(", comments=");
		sb.append(comments);
		sb.append(", firstName=");
		sb.append(firstName);
		sb.append(", middleName=");
		sb.append(middleName);
		sb.append(", lastName=");
		sb.append(lastName);
		sb.append(", jobTitle=");
		sb.append(jobTitle);
		sb.append(", loginDate=");
		sb.append(loginDate);
		sb.append(", loginIP=");
		sb.append(loginIP);
		sb.append(", lastLoginDate=");
		sb.append(lastLoginDate);
		sb.append(", lastLoginIP=");
		sb.append(lastLoginIP);
		sb.append(", lastFailedLoginDate=");
		sb.append(lastFailedLoginDate);
		sb.append(", failedLoginAttempts=");
		sb.append(failedLoginAttempts);
		sb.append(", lockout=");
		sb.append(lockout);
		sb.append(", lockoutDate=");
		sb.append(lockoutDate);
		sb.append(", agreedToTermsOfUse=");
		sb.append(agreedToTermsOfUse);
		sb.append(", emailAddressVerified=");
		sb.append(emailAddressVerified);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public User toEntityModel() {
		UserImpl userImpl = new UserImpl();

		userImpl.setMvccVersion(mvccVersion);

		if (uuid == null) {
			userImpl.setUuid("");
		}
		else {
			userImpl.setUuid(uuid);
		}

		if (externalReferenceCode == null) {
			userImpl.setExternalReferenceCode("");
		}
		else {
			userImpl.setExternalReferenceCode(externalReferenceCode);
		}

		userImpl.setUserId(userId);
		userImpl.setCompanyId(companyId);

		if (createDate == Long.MIN_VALUE) {
			userImpl.setCreateDate(null);
		}
		else {
			userImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			userImpl.setModifiedDate(null);
		}
		else {
			userImpl.setModifiedDate(new Date(modifiedDate));
		}

		userImpl.setDefaultUser(defaultUser);
		userImpl.setContactId(contactId);

		if (password == null) {
			userImpl.setPassword("");
		}
		else {
			userImpl.setPassword(password);
		}

		userImpl.setPasswordEncrypted(passwordEncrypted);
		userImpl.setPasswordReset(passwordReset);

		if (passwordModifiedDate == Long.MIN_VALUE) {
			userImpl.setPasswordModifiedDate(null);
		}
		else {
			userImpl.setPasswordModifiedDate(new Date(passwordModifiedDate));
		}

		if (digest == null) {
			userImpl.setDigest("");
		}
		else {
			userImpl.setDigest(digest);
		}

		if (reminderQueryQuestion == null) {
			userImpl.setReminderQueryQuestion("");
		}
		else {
			userImpl.setReminderQueryQuestion(reminderQueryQuestion);
		}

		if (reminderQueryAnswer == null) {
			userImpl.setReminderQueryAnswer("");
		}
		else {
			userImpl.setReminderQueryAnswer(reminderQueryAnswer);
		}

		userImpl.setGraceLoginCount(graceLoginCount);

		if (screenName == null) {
			userImpl.setScreenName("");
		}
		else {
			userImpl.setScreenName(screenName);
		}

		if (emailAddress == null) {
			userImpl.setEmailAddress("");
		}
		else {
			userImpl.setEmailAddress(emailAddress);
		}

		userImpl.setFacebookId(facebookId);

		if (googleUserId == null) {
			userImpl.setGoogleUserId("");
		}
		else {
			userImpl.setGoogleUserId(googleUserId);
		}

		userImpl.setLdapServerId(ldapServerId);

		if (openId == null) {
			userImpl.setOpenId("");
		}
		else {
			userImpl.setOpenId(openId);
		}

		userImpl.setPortraitId(portraitId);

		if (languageId == null) {
			userImpl.setLanguageId("");
		}
		else {
			userImpl.setLanguageId(languageId);
		}

		if (timeZoneId == null) {
			userImpl.setTimeZoneId("");
		}
		else {
			userImpl.setTimeZoneId(timeZoneId);
		}

		if (greeting == null) {
			userImpl.setGreeting("");
		}
		else {
			userImpl.setGreeting(greeting);
		}

		if (comments == null) {
			userImpl.setComments("");
		}
		else {
			userImpl.setComments(comments);
		}

		if (firstName == null) {
			userImpl.setFirstName("");
		}
		else {
			userImpl.setFirstName(firstName);
		}

		if (middleName == null) {
			userImpl.setMiddleName("");
		}
		else {
			userImpl.setMiddleName(middleName);
		}

		if (lastName == null) {
			userImpl.setLastName("");
		}
		else {
			userImpl.setLastName(lastName);
		}

		if (jobTitle == null) {
			userImpl.setJobTitle("");
		}
		else {
			userImpl.setJobTitle(jobTitle);
		}

		if (loginDate == Long.MIN_VALUE) {
			userImpl.setLoginDate(null);
		}
		else {
			userImpl.setLoginDate(new Date(loginDate));
		}

		if (loginIP == null) {
			userImpl.setLoginIP("");
		}
		else {
			userImpl.setLoginIP(loginIP);
		}

		if (lastLoginDate == Long.MIN_VALUE) {
			userImpl.setLastLoginDate(null);
		}
		else {
			userImpl.setLastLoginDate(new Date(lastLoginDate));
		}

		if (lastLoginIP == null) {
			userImpl.setLastLoginIP("");
		}
		else {
			userImpl.setLastLoginIP(lastLoginIP);
		}

		if (lastFailedLoginDate == Long.MIN_VALUE) {
			userImpl.setLastFailedLoginDate(null);
		}
		else {
			userImpl.setLastFailedLoginDate(new Date(lastFailedLoginDate));
		}

		userImpl.setFailedLoginAttempts(failedLoginAttempts);
		userImpl.setLockout(lockout);

		if (lockoutDate == Long.MIN_VALUE) {
			userImpl.setLockoutDate(null);
		}
		else {
			userImpl.setLockoutDate(new Date(lockoutDate));
		}

		userImpl.setAgreedToTermsOfUse(agreedToTermsOfUse);
		userImpl.setEmailAddressVerified(emailAddressVerified);
		userImpl.setStatus(status);

		userImpl.resetOriginalValues();

		return userImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput)
		throws ClassNotFoundException, IOException {

		mvccVersion = objectInput.readLong();
		uuid = (String)objectInput.readObject();
		externalReferenceCode = (String)objectInput.readObject();

		userId = objectInput.readLong();

		companyId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		defaultUser = objectInput.readBoolean();

		contactId = objectInput.readLong();
		password = (String)objectInput.readObject();

		passwordEncrypted = objectInput.readBoolean();

		passwordReset = objectInput.readBoolean();
		passwordModifiedDate = objectInput.readLong();
		digest = (String)objectInput.readObject();
		reminderQueryQuestion = (String)objectInput.readObject();
		reminderQueryAnswer = (String)objectInput.readObject();

		graceLoginCount = objectInput.readInt();
		screenName = (String)objectInput.readObject();
		emailAddress = (String)objectInput.readObject();

		facebookId = objectInput.readLong();
		googleUserId = (String)objectInput.readObject();

		ldapServerId = objectInput.readLong();
		openId = (String)objectInput.readObject();

		portraitId = objectInput.readLong();
		languageId = (String)objectInput.readObject();
		timeZoneId = (String)objectInput.readObject();
		greeting = (String)objectInput.readObject();
		comments = (String)objectInput.readObject();
		firstName = (String)objectInput.readObject();
		middleName = (String)objectInput.readObject();
		lastName = (String)objectInput.readObject();
		jobTitle = (String)objectInput.readObject();
		loginDate = objectInput.readLong();
		loginIP = (String)objectInput.readObject();
		lastLoginDate = objectInput.readLong();
		lastLoginIP = (String)objectInput.readObject();
		lastFailedLoginDate = objectInput.readLong();

		failedLoginAttempts = objectInput.readInt();

		lockout = objectInput.readBoolean();
		lockoutDate = objectInput.readLong();

		agreedToTermsOfUse = objectInput.readBoolean();

		emailAddressVerified = objectInput.readBoolean();

		status = objectInput.readInt();
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

		if (externalReferenceCode == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(externalReferenceCode);
		}

		objectOutput.writeLong(userId);

		objectOutput.writeLong(companyId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeBoolean(defaultUser);

		objectOutput.writeLong(contactId);

		if (password == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(password);
		}

		objectOutput.writeBoolean(passwordEncrypted);

		objectOutput.writeBoolean(passwordReset);
		objectOutput.writeLong(passwordModifiedDate);

		if (digest == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(digest);
		}

		if (reminderQueryQuestion == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(reminderQueryQuestion);
		}

		if (reminderQueryAnswer == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(reminderQueryAnswer);
		}

		objectOutput.writeInt(graceLoginCount);

		if (screenName == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(screenName);
		}

		if (emailAddress == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(emailAddress);
		}

		objectOutput.writeLong(facebookId);

		if (googleUserId == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(googleUserId);
		}

		objectOutput.writeLong(ldapServerId);

		if (openId == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(openId);
		}

		objectOutput.writeLong(portraitId);

		if (languageId == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(languageId);
		}

		if (timeZoneId == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(timeZoneId);
		}

		if (greeting == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(greeting);
		}

		if (comments == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(comments);
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

		if (jobTitle == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(jobTitle);
		}

		objectOutput.writeLong(loginDate);

		if (loginIP == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(loginIP);
		}

		objectOutput.writeLong(lastLoginDate);

		if (lastLoginIP == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(lastLoginIP);
		}

		objectOutput.writeLong(lastFailedLoginDate);

		objectOutput.writeInt(failedLoginAttempts);

		objectOutput.writeBoolean(lockout);
		objectOutput.writeLong(lockoutDate);

		objectOutput.writeBoolean(agreedToTermsOfUse);

		objectOutput.writeBoolean(emailAddressVerified);

		objectOutput.writeInt(status);
	}

	public long mvccVersion;
	public String uuid;
	public String externalReferenceCode;
	public long userId;
	public long companyId;
	public long createDate;
	public long modifiedDate;
	public boolean defaultUser;
	public long contactId;
	public String password;
	public boolean passwordEncrypted;
	public boolean passwordReset;
	public long passwordModifiedDate;
	public String digest;
	public String reminderQueryQuestion;
	public String reminderQueryAnswer;
	public int graceLoginCount;
	public String screenName;
	public String emailAddress;
	public long facebookId;
	public String googleUserId;
	public long ldapServerId;
	public String openId;
	public long portraitId;
	public String languageId;
	public String timeZoneId;
	public String greeting;
	public String comments;
	public String firstName;
	public String middleName;
	public String lastName;
	public String jobTitle;
	public long loginDate;
	public String loginIP;
	public long lastLoginDate;
	public String lastLoginIP;
	public long lastFailedLoginDate;
	public int failedLoginAttempts;
	public boolean lockout;
	public long lockoutDate;
	public boolean agreedToTermsOfUse;
	public boolean emailAddressVerified;
	public int status;

}