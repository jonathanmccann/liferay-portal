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

package com.liferay.portal.upgrade.v6_1_1;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.ResourcePermission;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionCacheUtil;
import com.liferay.portal.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.service.impl.ResourcePermissionLocalServiceImpl;

import java.util.List;

/**
 * @author Preston Crary
 */
public class UpgradePermission extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		upgradeOrganizationRolePermissions();
	}

	protected void upgradeOrganizationRolePermissions() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			ResourcePermission.class);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("name", Organization.class.getName()));

		List<ResourcePermission> resourcePermissions =
			ResourcePermissionLocalServiceUtil.dynamicQuery(dynamicQuery);

		for (ResourcePermission resourcePermission : resourcePermissions) {
			ResourcePermission groupResourcePermission = null;

			try {
				groupResourcePermission =
					ResourcePermissionLocalServiceUtil.getResourcePermission(
						resourcePermission.getCompanyId(),
						Group.class.getName(), resourcePermission.getScope(),
						resourcePermission.getPrimKey(),
						resourcePermission.getRoleId());
			}
			catch (Exception e) {
				ResourcePermissionLocalServiceUtil.setResourcePermissions(
					resourcePermission.getCompanyId(), Group.class.getName(),
					resourcePermission.getScope(),
					resourcePermission.getPrimKey(),
					resourcePermission.getRoleId(),
					ResourcePermissionLocalServiceImpl.EMPTY_ACTION_IDS);

				groupResourcePermission =
					ResourcePermissionLocalServiceUtil.getResourcePermission(
						resourcePermission.getCompanyId(),
						Group.class.getName(), resourcePermission.getScope(),
						resourcePermission.getPrimKey(),
						resourcePermission.getRoleId());
			}

			long organizationActions = resourcePermission.getActionIds();
			long groupActions = groupResourcePermission.getActionIds();

			for (Object[] actionIdToMask : _ORGANIZATION_ACTION_IDS_TO_MASKS) {
				long organizationActionMask = (Long)actionIdToMask[1];
				long groupActionMask = (Long)actionIdToMask[2];

				if ((organizationActions & organizationActionMask) ==
						organizationActionMask) {

					organizationActions =
						organizationActions & (~organizationActionMask);
					groupActions = groupActions | groupActionMask;
				}
			}

			try {
				resourcePermission.resetOriginalValues();

				resourcePermission.setActionIds(organizationActions);

				ResourcePermissionLocalServiceUtil.updateResourcePermission(
					resourcePermission);

				groupResourcePermission.resetOriginalValues();
				groupResourcePermission.setActionIds(groupActions);

				ResourcePermissionLocalServiceUtil.updateResourcePermission(
					groupResourcePermission);
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		PermissionCacheUtil.clearCache();
	}

	private static final Object[][] _ORGANIZATION_ACTION_IDS_TO_MASKS =
		new Object[][] {
			new Object[] {"APPROVE_PROPOSAL", 2L, 0L},
			new Object[] {ActionKeys.ASSIGN_MEMBERS, 4L, 4L},
			new Object[] {"ASSIGN_REVIEWER", 8L, 0L},
			new Object[] {ActionKeys.MANAGE_ARCHIVED_SETUPS, 128L, 128L},
			new Object[] {ActionKeys.MANAGE_LAYOUTS, 256L, 256L},
			new Object[] {ActionKeys.MANAGE_STAGING, 512L, 512L},
			new Object[] {ActionKeys.MANAGE_TEAMS, 2048L, 1024L},
			new Object[] {ActionKeys.PUBLISH_STAGING, 16384L, 4096L}
		};

	private static final Log _log = LogFactoryUtil.getLog(
		UpgradePermission.class);

}