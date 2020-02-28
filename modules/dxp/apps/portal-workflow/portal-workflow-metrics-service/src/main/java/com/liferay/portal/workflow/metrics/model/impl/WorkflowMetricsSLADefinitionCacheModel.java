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
import com.liferay.portal.workflow.metrics.model.WorkflowMetricsSLADefinition;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing WorkflowMetricsSLADefinition in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class WorkflowMetricsSLADefinitionCacheModel
	implements CacheModel<WorkflowMetricsSLADefinition>, Externalizable,
			   MVCCModel {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WorkflowMetricsSLADefinitionCacheModel)) {
			return false;
		}

		WorkflowMetricsSLADefinitionCacheModel
			workflowMetricsSLADefinitionCacheModel =
				(WorkflowMetricsSLADefinitionCacheModel)obj;

		if ((workflowMetricsSLADefinitionId ==
				workflowMetricsSLADefinitionCacheModel.
					workflowMetricsSLADefinitionId) &&
			(mvccVersion ==
				workflowMetricsSLADefinitionCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, workflowMetricsSLADefinitionId);

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
		sb.append(", workflowMetricsSLADefinitionId=");
		sb.append(workflowMetricsSLADefinitionId);
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
	public WorkflowMetricsSLADefinition toEntityModel() {
		WorkflowMetricsSLADefinitionImpl workflowMetricsSLADefinitionImpl =
			new WorkflowMetricsSLADefinitionImpl();

		workflowMetricsSLADefinitionImpl.setMvccVersion(mvccVersion);

		if (uuid == null) {
			workflowMetricsSLADefinitionImpl.setUuid("");
		}
		else {
			workflowMetricsSLADefinitionImpl.setUuid(uuid);
		}

		workflowMetricsSLADefinitionImpl.setWorkflowMetricsSLADefinitionId(
			workflowMetricsSLADefinitionId);
		workflowMetricsSLADefinitionImpl.setGroupId(groupId);
		workflowMetricsSLADefinitionImpl.setCompanyId(companyId);
		workflowMetricsSLADefinitionImpl.setUserId(userId);

		if (userName == null) {
			workflowMetricsSLADefinitionImpl.setUserName("");
		}
		else {
			workflowMetricsSLADefinitionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			workflowMetricsSLADefinitionImpl.setCreateDate(null);
		}
		else {
			workflowMetricsSLADefinitionImpl.setCreateDate(
				new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			workflowMetricsSLADefinitionImpl.setModifiedDate(null);
		}
		else {
			workflowMetricsSLADefinitionImpl.setModifiedDate(
				new Date(modifiedDate));
		}

		workflowMetricsSLADefinitionImpl.setActive(active);

		if (calendarKey == null) {
			workflowMetricsSLADefinitionImpl.setCalendarKey("");
		}
		else {
			workflowMetricsSLADefinitionImpl.setCalendarKey(calendarKey);
		}

		if (description == null) {
			workflowMetricsSLADefinitionImpl.setDescription("");
		}
		else {
			workflowMetricsSLADefinitionImpl.setDescription(description);
		}

		workflowMetricsSLADefinitionImpl.setDuration(duration);

		if (name == null) {
			workflowMetricsSLADefinitionImpl.setName("");
		}
		else {
			workflowMetricsSLADefinitionImpl.setName(name);
		}

		if (pauseNodeKeys == null) {
			workflowMetricsSLADefinitionImpl.setPauseNodeKeys("");
		}
		else {
			workflowMetricsSLADefinitionImpl.setPauseNodeKeys(pauseNodeKeys);
		}

		workflowMetricsSLADefinitionImpl.setProcessId(processId);

		if (processVersion == null) {
			workflowMetricsSLADefinitionImpl.setProcessVersion("");
		}
		else {
			workflowMetricsSLADefinitionImpl.setProcessVersion(processVersion);
		}

		if (startNodeKeys == null) {
			workflowMetricsSLADefinitionImpl.setStartNodeKeys("");
		}
		else {
			workflowMetricsSLADefinitionImpl.setStartNodeKeys(startNodeKeys);
		}

		if (stopNodeKeys == null) {
			workflowMetricsSLADefinitionImpl.setStopNodeKeys("");
		}
		else {
			workflowMetricsSLADefinitionImpl.setStopNodeKeys(stopNodeKeys);
		}

		if (version == null) {
			workflowMetricsSLADefinitionImpl.setVersion("");
		}
		else {
			workflowMetricsSLADefinitionImpl.setVersion(version);
		}

		workflowMetricsSLADefinitionImpl.setStatus(status);
		workflowMetricsSLADefinitionImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			workflowMetricsSLADefinitionImpl.setStatusByUserName("");
		}
		else {
			workflowMetricsSLADefinitionImpl.setStatusByUserName(
				statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			workflowMetricsSLADefinitionImpl.setStatusDate(null);
		}
		else {
			workflowMetricsSLADefinitionImpl.setStatusDate(
				new Date(statusDate));
		}

		workflowMetricsSLADefinitionImpl.resetOriginalValues();

		return workflowMetricsSLADefinitionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput)
		throws ClassNotFoundException, IOException {

		mvccVersion = objectInput.readLong();
		uuid = (String)objectInput.readObject();

		workflowMetricsSLADefinitionId = objectInput.readLong();

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

		objectOutput.writeLong(workflowMetricsSLADefinitionId);

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
	public long workflowMetricsSLADefinitionId;
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
	public int status;
	public long statusByUserId;
	public String statusByUserName;
	public long statusDate;

}