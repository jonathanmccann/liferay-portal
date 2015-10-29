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

import com.liferay.portal.model.LayoutBranch;
import com.liferay.portal.model.RecentLayout;
import com.liferay.portal.model.User;
import com.liferay.portal.service.base.RecentLayoutLocalServiceBaseImpl;

/**
 * @author Brian Wing Shun Chan
 * @author Preston Crary
 */
@ProviderType
public class RecentLayoutLocalServiceImpl
	extends RecentLayoutLocalServiceBaseImpl {

	@Override
	public RecentLayout addRecentLayout(
		long companyId, long groupId, long userId, long layoutSetBranchId,
		long plid) {

		long recentLayoutId = counterLocalService.increment();

		RecentLayout recentLayout = recentLayoutPersistence.create(
			recentLayoutId);

		recentLayout.setCompanyId(companyId);
		recentLayout.setGroupId(groupId);
		recentLayout.setUserId(userId);
		recentLayout.setLayoutSetBranchId(layoutSetBranchId);
		recentLayout.setPlid(plid);

		return recentLayoutPersistence.update(recentLayout);
	}

	@Override
	public void deleteRecentLayouts(LayoutBranch layoutBranch) {
		recentLayoutPersistence.removeByLayoutBranchId(
			layoutBranch.getLayoutBranchId());
	}

	@Override
	public void deleteRecentLayouts(User user) {
		recentLayoutPersistence.removeByUserId(user.getUserId());
	}

	@Override
	public RecentLayout fetchRecentLayout(
		long userId, long layoutSetBranchId, long plid) {

		return recentLayoutPersistence.fetchByU_L_P(
			userId, layoutSetBranchId, plid);
	}

}