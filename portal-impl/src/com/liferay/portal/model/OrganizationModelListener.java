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

package com.liferay.portal.model;

import com.liferay.petra.concurrent.NoticeableExecutorService;
import com.liferay.petra.executor.PortalExecutorManager;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.transaction.TransactionCommitCallbackUtil;
import com.liferay.portal.kernel.util.ServiceProxyFactory;
import com.liferay.portal.model.impl.OrganizationModelImpl;
import com.liferay.portal.security.permission.PermissionCacheUtil;

/**
 * @author Lianne Louie
 * @author Jonathan McCann
 */
public class OrganizationModelListener extends BaseModelListener<Organization> {

	@Override
	public void onBeforeUpdate(Organization organization) {
		OrganizationModelImpl organizationModelImpl =
			(OrganizationModelImpl)organization;

		if (organizationModelImpl.getOriginalParentOrganizationId() ==
				organization.getParentOrganizationId()) {

			return;
		}

		long[] userIds = OrganizationLocalServiceUtil.getUserPrimaryKeys(
			organization.getOrganizationId());

		if (userIds.length > 0) {
			NoticeableExecutorService noticeableExecutorService =
				_portalExecutorManager.getPortalExecutor(
					OrganizationModelListener.class.getName());

			TransactionCommitCallbackUtil.registerCallback(
				() -> {
					noticeableExecutorService.submit(
						() -> {
							UserLocalServiceUtil.reindex(
								organization.getCompanyId(), userIds);

							PermissionCacheUtil.clearCache();

							return null;
						});

					return null;
				});
		}
	}

	private static volatile PortalExecutorManager _portalExecutorManager =
		ServiceProxyFactory.newServiceTrackedInstance(
			PortalExecutorManager.class, OrganizationModelListener.class,
			"_portalExecutorManager", true);

}