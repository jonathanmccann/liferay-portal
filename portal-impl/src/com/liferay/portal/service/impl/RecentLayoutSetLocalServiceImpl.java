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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.model.LayoutSetBranch;
import com.liferay.portal.model.RecentLayoutSet;
import com.liferay.portal.model.User;
import com.liferay.portal.service.base.RecentLayoutSetLocalServiceBaseImpl;

/**
 * @author Brian Wing Shun Chan
 * @author Preston Crary
 */
@ProviderType
public class RecentLayoutSetLocalServiceImpl
	extends RecentLayoutSetLocalServiceBaseImpl {

	@Override
	public RecentLayoutSet addRecentLayoutSet(
		long companyId, long groupId, long userId, long layoutSetId,
		long layoutSetBranchId) {

		long recentLayoutSetId = counterLocalService.increment();

		RecentLayoutSet recentLayoutSet = recentLayoutSetPersistence.create(
			recentLayoutSetId);

		recentLayoutSet.setCompanyId(companyId);
		recentLayoutSet.setGroupId(groupId);
		recentLayoutSet.setUserId(userId);
		recentLayoutSet.setLayoutSetId(layoutSetId);
		recentLayoutSet.setLayoutSetBranchId(layoutSetBranchId);

		return recentLayoutSetPersistence.update(recentLayoutSet);
	}

	@Override
	public void deleteRecentLayoutSets(LayoutSetBranch layoutSetBranch) {
		recentLayoutSetPersistence.removeByLayoutSetBranchId(
			layoutSetBranch.getLayoutSetBranchId());
	}

	@Override
	public void deleteRecentLayoutSets(User user) {
		recentLayoutSetPersistence.removeByUserId(user.getUserId());
	}

	@Override
	public RecentLayoutSet fetchRecentLayoutSet(long userId, long layoutSetId) {
		return recentLayoutSetPersistence.fetchByU_L(userId, layoutSetId);
	}

}