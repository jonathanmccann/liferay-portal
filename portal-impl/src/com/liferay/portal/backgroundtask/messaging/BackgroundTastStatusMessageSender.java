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

package com.liferay.portal.backgroundtask.messaging;

import com.liferay.portal.kernel.backgroundtask.BackgroundTaskThreadLocal;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.sender.SingleDestinationMessageSender;

/**
 * @author Andrew Betts
 */
public abstract class BackgroundTastStatusMessageSender {

	public void sendStatusMessage(Object... params) {
		if (!BackgroundTaskThreadLocal.hasBackgroundTask()) {
			return;
		}

		Message message = createMessage(params);

		_singleDestinationMessageSender.send(message);
	}

	public void setSingleDestinationMessageSender(
		SingleDestinationMessageSender singleDestinationMessageSender) {

		_singleDestinationMessageSender = singleDestinationMessageSender;
	}

	protected abstract Message createMessage(Object... params);

	private SingleDestinationMessageSender _singleDestinationMessageSender;

}