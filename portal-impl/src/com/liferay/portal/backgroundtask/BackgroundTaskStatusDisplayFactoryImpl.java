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

package com.liferay.portal.backgroundtask;

import com.liferay.portal.kernel.backgroundtask.BackgroundTaskExecutor;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskStatusDisplay;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskStatusDisplayFactory;
import com.liferay.portal.model.BackgroundTask;
import com.liferay.portal.service.BackgroundTaskLocalServiceUtil;

import java.util.Locale;

/**
 * @author Andrew Betts
 */
public class BackgroundTaskStatusDisplayFactoryImpl
	implements BackgroundTaskStatusDisplayFactory {

	public BackgroundTaskStatusDisplay getBackgroundTaskStatusDisplay(
		long backgroundTaskId, Locale locale) {

		BackgroundTask backgroundTask =
			BackgroundTaskLocalServiceUtil.fetchBackgroundTask(
				backgroundTaskId);

		if (backgroundTask == null) {
			return null;
		}

		BackgroundTaskExecutor backgroundTaskExecutor =
			backgroundTask.getBackgroundTaskExecutor();

		BackgroundTaskStatusDisplay backgroundTaskStatusDisplay =
			backgroundTaskExecutor.getBackgroundTaskStatusDisplay(
				backgroundTask, locale);

		return backgroundTaskStatusDisplay;
	}

}