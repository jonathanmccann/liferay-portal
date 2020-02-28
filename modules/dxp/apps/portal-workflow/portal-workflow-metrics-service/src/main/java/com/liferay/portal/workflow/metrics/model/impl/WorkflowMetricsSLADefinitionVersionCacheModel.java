/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.portal.workflow.metrics.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;
import com.liferay.portal.workflow.metrics.model.WorkflowMetricsSLADefinitionVersion;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing WorkflowMetricsSLADefinitionVersion in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class WorkflowMetricsSLADefinitionVersionCacheModel
	implements CacheModel<WorkflowMetricsSLADefinitionVersion>, Externalizable,
			   MVCCModel {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WorkflowMetricsSLADefinitionVersionCacheModel)) {
			return false;
		}

		WorkflowMetricsSLADefinitionVersionCacheModel
			workflowMetricsSLADefinitionVersionCacheModel =
				(WorkflowMetricsSLADefinitionVersionCacheModel)obj;

		if ((workflowMetricsSLADefinitionVersionId ==
				workflowMetricsSLADefinitionVersionCacheModel.
					workflowMetricsSLADefinitionVersionId) &&
			(mvccVersion ==
				workflowMetricsSLADefinitionVersionCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, workflowMetricsSLADefinitionVersionId);

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
		StringBundler sb = new StringBundler(51);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", uuid=");
		sb.append(uuid);
		sb.append(", workflowMetricsSLADefinitionVersionId=");
		sb.append(workflowMetricsSLADefinitionVersionId);
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
		sb.append(", active=");
		sb.append(active);
		sb.append(", calendarKey=");
		sb.append(calendarKey);
		sb.append(", description=");
		sb.append(description);
		sb.append(", duration=");
		sb.append(duration);
		sb.append(", name=");
		sb.append(name);
		sb.append(", pauseNodeKeys=");
		sb.append(pauseNodeKeys);
		sb.append(", processId=");
		sb.append(processId);
		sb.append(", processVersion=");
		sb.append(processVersion);
		sb.append(", startNodeKeys=");
		sb.append(startNodeKeys);
		sb.append(", stopNodeKeys=");
		sb.append(stopNodeKeys);
		sb.append(", version=");
		sb.append(version);
		sb.append(", workflowMetricsSLADefinitionId=");
		sb.append(workflowMetricsSLADefinitionId);
		sb.append(", status=");
		sb.append(status);
		sb.append(", statusByUserId=");
		sb.append(statusByUserId);
		sb.append(", statusByUserName=");
		sb.append(statusByUserName);
		sb.append(", statusDate=");
		sb.append(statusDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public WorkflowMetricsSLADefinitionVersion toEntityModel() {
		WorkflowMetricsSLADefinitionVersionImpl
			workflowMetricsSLADefinitionVersionImpl =
				new WorkflowMetricsSLADefinitionVersionImpl();

		workflowMetricsSLADefinitionVersionImpl.setMvccVersion(mvccVersion);

		if (uuid == null) {
			workflowMetricsSLADefinitionVersionImpl.setUuid("");
		}
		else {
			workflowMetricsSLADefinitionVersionImpl.setUuid(uuid);
		}

		workflowMetricsSLADefinitionVersionImpl.
			setWorkflowMetricsSLADefinitionVersionId(
				workflowMetricsSLADefinitionVersionId);
		workflowMetricsSLADefinitionVersionImpl.setGroupId(groupId);
		workflowMetricsSLADefinitionVersionImpl.setCompanyId(companyId);
		workflowMetricsSLADefinitionVersionImpl.setUserId(userId);

		if (userName == null) {
			workflowMetricsSLADefinitionVersionImpl.setUserName("");
		}
		else {
			workflowMetricsSLADefinitionVersionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			workflowMetricsSLADefinitionVersionImpl.setCreateDate(null);
		}
		else {
			workflowMetricsSLADefinitionVersionImpl.setCreateDate(
				new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			workflowMetricsSLADefinitionVersionImpl.setModifiedDate(null);
		}
		else {
			workflowMetricsSLADefinitionVersionImpl.setModifiedDate(
				new Date(modifiedDate));
		}

		workflowMetricsSLADefinitionVersionImpl.setActive(active);

		if (calendarKey == null) {
			workflowMetricsSLADefinitionVersionImpl.setCalendarKey("");
		}
		else {
			workflowMetricsSLADefinitionVersionImpl.setCalendarKey(calendarKey);
		}

		if (description == null) {
			workflowMetricsSLADefinitionVersionImpl.setDescription("");
		}
		else {
			workflowMetricsSLADefinitionVersionImpl.setDescription(description);
		}

		workflowMetricsSLADefinitionVersionImpl.setDuration(duration);

		if (name == null) {
			workflowMetricsSLADefinitionVersionImpl.setName("");
		}
		else {
			workflowMetricsSLADefinitionVersionImpl.setName(name);
		}

		if (pauseNodeKeys == null) {
			workflowMetricsSLADefinitionVersionImpl.setPauseNodeKeys("");
		}
		else {
			workflowMetricsSLADefinitionVersionImpl.setPauseNodeKeys(
				pauseNodeKeys);
		}

		workflowMetricsSLADefinitionVersionImpl.setProcessId(processId);

		if (processVersion == null) {
			workflowMetricsSLADefinitionVersionImpl.setProcessVersion("");
		}
		else {
			workflowMetricsSLADefinitionVersionImpl.setProcessVersion(
				processVersion);
		}

		if (startNodeKeys == null) {
			workflowMetricsSLADefinitionVersionImpl.setStartNodeKeys("");
		}
		else {
			workflowMetricsSLADefinitionVersionImpl.setStartNodeKeys(
				startNodeKeys);
		}

		if (stopNodeKeys == null) {
			workflowMetricsSLADefinitionVersionImpl.setStopNodeKeys("");
		}
		else {
			workflowMetricsSLADefinitionVersionImpl.setStopNodeKeys(
				stopNodeKeys);
		}

		if (version == null) {
			workflowMetricsSLADefinitionVersionImpl.setVersion("");
		}
		else {
			workflowMetricsSLADefinitionVersionImpl.setVersion(version);
		}

		workflowMetricsSLADefinitionVersionImpl.
			setWorkflowMetricsSLADefinitionId(workflowMetricsSLADefinitionId);
		workflowMetricsSLADefinitionVersionImpl.setStatus(status);
		workflowMetricsSLADefinitionVersionImpl.setStatusByUserId(
			statusByUserId);

		if (statusByUserName == null) {
			workflowMetricsSLADefinitionVersionImpl.setStatusByUserName("");
		}
		else {
			workflowMetricsSLADefinitionVersionImpl.setStatusByUserName(
				statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			workflowMetricsSLADefinitionVersionImpl.setStatusDate(null);
		}
		else {
			workflowMetricsSLADefinitionVersionImpl.setStatusDate(
				new Date(statusDate));
		}

		workflowMetricsSLADefinitionVersionImpl.resetOriginalValues();

		return workflowMetricsSLADefinitionVersionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput)
		throws ClassNotFoundException, IOException {

		mvccVersion = objectInput.readLong();
		uuid = (String)objectInput.readObject();

		workflowMetricsSLADefinitionVersionId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = (String)objectInput.readObject();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		active = objectInput.readBoolean();
		calendarKey = (String)objectInput.readObject();
		description = (String)objectInput.readObject();

		duration = objectInput.readLong();
		name = (String)objectInput.readObject();
		pauseNodeKeys = (String)objectInput.readObject();

		processId = objectInput.readLong();
		processVersion = (String)objectInput.readObject();
		startNodeKeys = (String)objectInput.readObject();
		stopNodeKeys = (String)objectInput.readObject();
		version = (String)objectInput.readObject();

		workflowMetricsSLADefinitionId = objectInput.readLong();

		status = objectInput.readInt();

		statusByUserId = objectInput.readLong();
		statusByUserName = (String)objectInput.readObject();
		statusDate = objectInput.readLong();
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

		objectOutput.writeLong(workflowMetricsSLADefinitionVersionId);

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

		objectOutput.writeBoolean(active);

		if (calendarKey == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(calendarKey);
		}

		if (description == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(description);
		}

		objectOutput.writeLong(duration);

		if (name == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(name);
		}

		if (pauseNodeKeys == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(pauseNodeKeys);
		}

		objectOutput.writeLong(processId);

		if (processVersion == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(processVersion);
		}

		if (startNodeKeys == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(startNodeKeys);
		}

		if (stopNodeKeys == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(stopNodeKeys);
		}

		if (version == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(version);
		}

		objectOutput.writeLong(workflowMetricsSLADefinitionId);

		objectOutput.writeInt(status);

		objectOutput.writeLong(statusByUserId);

		if (statusByUserName == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(statusByUserName);
		}

		objectOutput.writeLong(statusDate);
	}

	public long mvccVersion;
	public String uuid;
	public long workflowMetricsSLADefinitionVersionId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public boolean active;
	public String calendarKey;
	public String description;
	public long duration;
	public String name;
	public String pauseNodeKeys;
	public long processId;
	public String processVersion;
	public String startNodeKeys;
	public String stopNodeKeys;
	public String version;
	public long workflowMetricsSLADefinitionId;
	public int status;
	public long statusByUserId;
	public String statusByUserName;
	public long statusDate;

}