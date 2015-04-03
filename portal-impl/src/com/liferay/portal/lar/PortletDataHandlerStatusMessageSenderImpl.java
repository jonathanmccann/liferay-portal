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

package com.liferay.portal.lar;

import com.liferay.portal.backgroundtask.messaging.BackgroundTastStatusMessageSender;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskThreadLocal;
import com.liferay.portal.kernel.lar.ManifestSummary;
import com.liferay.portal.kernel.lar.PortletDataHandler;
import com.liferay.portal.kernel.lar.PortletDataHandlerStatusMessageSender;
import com.liferay.portal.kernel.lar.StagedModelDataHandler;
import com.liferay.portal.kernel.lar.StagedModelDataHandlerRegistryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.util.LongWrapper;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.StagedModel;
import com.liferay.portal.service.PortletLocalServiceUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public class PortletDataHandlerStatusMessageSenderImpl
	extends BackgroundTastStatusMessageSender
	implements PortletDataHandlerStatusMessageSender {

	/**
	 * @deprecated As of 7.0.0, replaced by {@link #sendStatusMessage(String,
	 *             String[], ManifestSummary)}
	 */
	@Deprecated
	@Override
	public void sendStatusMessage(
		String messageType, ManifestSummary manifestSummary) {

		sendStatusMessage(messageType, (String[])null, manifestSummary);
	}

	@Override
	public void sendStatusMessage(
		String messageType, String portletId, ManifestSummary manifestSummary) {

		super.sendStatusMessage(messageType, portletId, manifestSummary);
	}

	@Override
	public void sendStatusMessage(
		String messageType, String[] portletIds,
		ManifestSummary manifestSummary) {

		super.sendStatusMessage(messageType, portletIds, manifestSummary);
	}

	@Override
	public <T extends StagedModel> void sendStatusMessage(
		String messageType, T stagedModel, ManifestSummary manifestSummary) {

		StagedModelDataHandler<T> stagedModelDataHandler =
			(StagedModelDataHandler<T>)
				StagedModelDataHandlerRegistryUtil.getStagedModelDataHandler(
					stagedModel.getModelClassName());

		String stagedModelDisplayName = stagedModelDataHandler.getDisplayName(
			stagedModel);

		sendStatusMessage(
			messageType, stagedModel, stagedModelDisplayName, manifestSummary);
	}

	protected Message createLayoutMessage(
		String messageType, String[] portletIds,
		ManifestSummary manifestSummary) {

		Message message = doCreateMessage(messageType, manifestSummary);

		message.put("portletIds", portletIds);

		return message;
	}

	@Override
	protected Message createMessage(Object... params) {
		Message message = null;

		String messageType = (String)params[0];

		if (messageType.equals("layout")) {
			String[] portletIds = (String[])params[1];
			ManifestSummary manifestSummary = (ManifestSummary)params[2];

			message = createLayoutMessage(
				messageType, portletIds, manifestSummary);
		}
		else if (messageType.equals("portlet")) {
			String portletId = (String)params[1];
			ManifestSummary manifestSummary = (ManifestSummary)params[2];

			message = createPortletMessage(
				messageType, portletId, manifestSummary);
		}
		else if (messageType.equals("stagedModel")) {
			StagedModel stagedModel = (StagedModel)params[1];
			String stagedModelDisplayName = (String)params[2];
			ManifestSummary manifestSummary = (ManifestSummary)params[3];

			message = createStagedModelMessage(
				messageType, stagedModel, stagedModelDisplayName,
				manifestSummary);
		}

		return message;
	}

	protected Message createPortletMessage(
		String messageType, String portletId, ManifestSummary manifestSummary) {

		Message message = doCreateMessage(messageType, manifestSummary);

		message.put("portletId", portletId);

		Portlet portlet = PortletLocalServiceUtil.getPortletById(portletId);

		if (portlet != null) {
			PortletDataHandler portletDataHandler =
				portlet.getPortletDataHandlerInstance();

			long portletModelAdditionCountersTotal =
				portletDataHandler.getExportModelCount(manifestSummary);

			if (portletModelAdditionCountersTotal < 0) {
				portletModelAdditionCountersTotal = 0;
			}

			message.put(
				"portletModelAdditionCountersTotal",
				portletModelAdditionCountersTotal);
		}

		return message;
	}

	protected Message createStagedModelMessage(
		String messageType, StagedModel stagedModel,
		String stagedModelDisplayName, ManifestSummary manifestSummary) {

		Message message = doCreateMessage(messageType, manifestSummary);

		message.put("stagedModelName", stagedModelDisplayName);

		message.put(
			"stagedModelType",
			String.valueOf(stagedModel.getStagedModelType()));
		message.put("uuid", stagedModel.getUuid());

		return message;
	}

	protected Message doCreateMessage(
		String messageType, ManifestSummary manifestSummary) {

		Message message = new Message();

		message.put(
			"backgroundTaskId",
			BackgroundTaskThreadLocal.getBackgroundTaskId());
		message.put("messageType", messageType);

		Map<String, LongWrapper> modelAdditionCounters =
			manifestSummary.getModelAdditionCounters();

		message.put(
			"modelAdditionCounters",
			new HashMap<String, LongWrapper>(modelAdditionCounters));

		Map<String, LongWrapper> modelDeletionCounters =
			manifestSummary.getModelDeletionCounters();

		message.put(
			"modelDeletionCounters",
			new HashMap<String, LongWrapper>(modelDeletionCounters));

		return message;
	}

}