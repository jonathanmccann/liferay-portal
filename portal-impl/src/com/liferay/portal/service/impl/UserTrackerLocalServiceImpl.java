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

package com.liferay.portal.service.impl;

import com.liferay.portal.kernel.cluster.ClusterExecutorUtil;
import com.liferay.portal.kernel.cluster.ClusterNodeResponse;
import com.liferay.portal.kernel.cluster.ClusterNodeResponses;
import com.liferay.portal.kernel.cluster.ClusterRequest;
import com.liferay.portal.kernel.cluster.FutureClusterResponses;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.UserTracker;
import com.liferay.portal.kernel.model.UserTrackerPath;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.liveusers.LiveUsers;
import com.liferay.portal.service.base.UserTrackerLocalServiceBaseImpl;
import com.liferay.portal.util.PropsValues;

import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Brian Wing Shun Chan
 */
public class UserTrackerLocalServiceImpl
	extends UserTrackerLocalServiceBaseImpl {

	@Override
	public UserTracker addUserTracker(
		long companyId, long userId, Date modifiedDate, String sessionId,
		String remoteAddr, String remoteHost, String userAgent,
		List<UserTrackerPath> userTrackerPaths) {

		if (PropsValues.SESSION_TRACKER_PERSISTENCE_ENABLED) {
			long userTrackerId = counterLocalService.increment(
				UserTracker.class.getName());

			UserTracker userTracker = userTrackerPersistence.create(
				userTrackerId);

			userTracker.setCompanyId(companyId);
			userTracker.setUserId(userId);
			userTracker.setModifiedDate(modifiedDate);
			userTracker.setSessionId(sessionId);
			userTracker.setRemoteAddr(remoteAddr);
			userTracker.setRemoteHost(remoteHost);
			userTracker.setUserAgent(userAgent);

			userTrackerPersistence.update(userTracker);

			for (UserTrackerPath userTrackerPath : userTrackerPaths) {
				long pathId = counterLocalService.increment(
					UserTrackerPath.class.getName());

				userTrackerPath.setUserTrackerPathId(pathId);
				userTrackerPath.setUserTrackerId(userTrackerId);

				userTrackerPathPersistence.update(userTrackerPath);
			}

			return userTracker;
		}
		else {
			return null;
		}
	}

	@Override
	public UserTracker deleteUserTracker(long userTrackerId)
		throws PortalException {

		UserTracker userTracker = userTrackerPersistence.findByPrimaryKey(
			userTrackerId);

		return deleteUserTracker(userTracker);
	}

	@Override
	public UserTracker deleteUserTracker(UserTracker userTracker) {

		// Paths

		userTrackerPathPersistence.removeByUserTrackerId(
			userTracker.getUserTrackerId());

		// User tracker

		return userTrackerPersistence.remove(userTracker);
	}

	public UserTracker getActiveUserTracker(UserTracker userTracker) {
		if ((userTracker != null) && (userTracker.getHits() != 0)) {
			return userTracker;
		}

		try {
			ClusterRequest clusterRequest =
				ClusterRequest.createMulticastRequest(
					new MethodHandler(
						_getUserTrackerMethodKey, userTracker.getCompanyId(),
						userTracker.getSessionId()),
					true);

			FutureClusterResponses futureClusterResponses =
				ClusterExecutorUtil.execute(clusterRequest);

			if (futureClusterResponses != null) {
				ClusterNodeResponses clusterNodeResponses =
					futureClusterResponses.get(20, TimeUnit.SECONDS);

				BlockingQueue<ClusterNodeResponse> clusterNodeResponseQueue =
					clusterNodeResponses.getClusterResponses();

				for (ClusterNodeResponse clusterNodeResponse :
						clusterNodeResponseQueue) {

					UserTracker nodeUserTracker =
						(UserTracker)clusterNodeResponse.getResult();

					if ((nodeUserTracker != null) &&
						(nodeUserTracker.getHits() != 0)) {

						return nodeUserTracker;
					}
				}
			}
		}
		catch (Exception e) {
			_log.error(e);
		}

		return userTracker;
	}

	@Override
	public List<UserTracker> getUserTrackers(
		long companyId, int start, int end) {

		return userTrackerPersistence.findByCompanyId(companyId, start, end);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UserTrackerLocalServiceImpl.class);

	private static final MethodKey _getUserTrackerMethodKey = new MethodKey(
		LiveUsers.class, "getUserTracker", long.class, String.class);

}