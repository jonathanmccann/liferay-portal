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

package com.liferay.portal.lar.backgroundtask;

import com.liferay.portal.kernel.backgroundtask.BackgroundTaskStatus;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskStatusDisplay;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskStatusRegistryUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.BackgroundTask;
import com.liferay.portal.security.permission.ResourceActionsUtil;

import java.io.Serializable;

import java.util.Locale;
import java.util.Map;

/**
 * @author Andrew Betts
 */
public class StagingBackgroundTaskStatusDisplay
	implements BackgroundTaskStatusDisplay {

	public StagingBackgroundTaskStatusDisplay(
		BackgroundTask backgroundTask, Locale locale) {

		BackgroundTaskStatus backgroundTaskStatus =
			BackgroundTaskStatusRegistryUtil.getBackgroundTaskStatus(
				backgroundTask.getBackgroundTaskId());

		Map<String, Serializable> taskContextMap =
			backgroundTask.getTaskContextMap();

		_cmd = (String)taskContextMap.get(Constants.CMD);

		long allModelAdditionCountersTotal = GetterUtil.getLong(
			backgroundTaskStatus.getAttribute("allModelAdditionCountersTotal"));
		long allPortletAdditionCounter = GetterUtil.getLong(
			backgroundTaskStatus.getAttribute("allPortletAdditionCounter"));
		long currentModelAdditionCountersTotal = GetterUtil.getLong(
			backgroundTaskStatus.getAttribute(
				"currentModelAdditionCountersTotal"));
		long currentPortletAdditionCounter = GetterUtil.getLong(
			backgroundTaskStatus.getAttribute("currentPortletAdditionCounter"));
		String phase = GetterUtil.getString(
			backgroundTaskStatus.getAttribute("phase"));

		_allProgressBarCountersTotal =
			allModelAdditionCountersTotal + allPortletAdditionCounter;

		_percentage = calculatePercentage(
			_allProgressBarCountersTotal, currentModelAdditionCountersTotal,
			currentPortletAdditionCounter, phase);

		_stagedModelName = GetterUtil.getString(
			backgroundTaskStatus.getAttribute("stagedModelName"));
		_stagedModelType = GetterUtil.getString(
			backgroundTaskStatus.getAttribute("stagedModelType"));

		_message = processMessage(
			_cmd, _stagedModelName, _stagedModelType, _percentage, locale);
	}

	@Override
	public String getMessage() {
		return _message;
	}

	@Override
	public int getPercentage() {
		return _percentage;
	}

	@Override
	public boolean hasMessage() {
		if (hasRemoteMessage() || hasStagedModelMessage()) {
			return true;
		}

		return false;
	}

	@Override
	public boolean hasPercentage() {
		if ((_allProgressBarCountersTotal > 0) &&
			(!Validator.equals(_cmd, Constants.PUBLISH_TO_REMOTE) ||
			 (_percentage < 100))) {

			return true;
		}

		return false;
	}

	protected int calculatePercentage(
		long allProgressBarCountersTotal,
		long currentModelAdditionCountersTotal,
		long currentPortletAdditionCounter, String phase) {

		int percentage = 100;

		long currentProgressBarCountersTotal =
			currentModelAdditionCountersTotal + currentPortletAdditionCounter;

		if (allProgressBarCountersTotal > 0) {
			int base = 100;

			if (phase.equals(Constants.EXPORT) &&
				!Validator.equals(_cmd, Constants.PUBLISH_TO_REMOTE)) {

				base = 50;
			}

			percentage = Math.round(
				(float)currentProgressBarCountersTotal /
					allProgressBarCountersTotal * base);
		}

		return percentage;
	}

	protected boolean hasRemoteMessage() {
		if (Validator.equals(_cmd, Constants.PUBLISH_TO_REMOTE) &&
			(_percentage == 100)) {

			return true;
		}

		return false;
	}

	protected boolean hasStagedModelMessage() {
		if (Validator.isNotNull(_stagedModelName) &&
			Validator.isNotNull(_stagedModelType)) {

			return true;
		}

		return false;
	}

	protected String processMessage(
		String cmd, String stagedModelName, String stagedModelType,
		int percentage, Locale locale) {

		StringBundler sb = new StringBundler();

		String messageKey = "exporting";

		if (Validator.equals(cmd, Constants.IMPORT)) {
			messageKey = "importing";
		}
		else if (Validator.equals(cmd, Constants.PUBLISH_TO_LIVE) ||
				 Validator.equals(cmd, Constants.PUBLISH_TO_REMOTE)) {

			messageKey = "publishing";
		}

		if (hasRemoteMessage()) {
			return LanguageUtil.format(
				locale,
				"please-wait-as-the-publication-processes-on-the-remote-site",
				new Object[0]);
		}
		else if (hasStagedModelMessage()) {
			sb.append("<strong>");
			sb.append(LanguageUtil.format(locale, messageKey, new Object[0]));
			sb.append(StringPool.TRIPLE_PERIOD);
			sb.append("</strong>");
			sb.append(
				ResourceActionsUtil.getModelResource(locale, stagedModelType));
			sb.append("<em>");
			sb.append(HtmlUtil.escape(stagedModelName));
			sb.append("</em>");
		}

		return sb.toString();
	}

	private final long _allProgressBarCountersTotal;
	private final String _cmd;
	private final String _message;
	private int _percentage;
	private final String _stagedModelName;
	private final String _stagedModelType;

}