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

package com.liferay.portal.security.permission;

import com.liferay.portal.kernel.concurrent.ThreadPoolExecutor;
import com.liferay.portal.kernel.executor.PortalExecutorManagerUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.transaction.TransactionCommitCallbackUtil;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author Lianne Louie
 */
public class OrganizationModelListener extends BaseModelListener<Organization> {

	@Override
	public void onAfterUpdate(Organization organization) {
		updateOrganizationUsers(organization);
	}

	@Override
	public void onBeforeUpdate(Organization organization) {
		_organizationId = organization.getOrganizationId();

		try {
			Organization oldOrg = OrganizationLocalServiceUtil.getOrganization(
				_organizationId);

			_oldParentId = oldOrg.getParentOrganizationId();
		}
		catch (Exception e) {
			_log.error("Unable to fetch organization: " + _organizationId);

			_oldParentId = organization.getParentOrganizationId();
		}
	}

	protected void updateOrganizationUsers(Organization organization) {
		long currentParentId = organization.getParentOrganizationId();

		Callable<Void> callable = new Callable<Void>() {

			@Override
			public Void call() throws Exception {
				long currentParentId = organization.getParentOrganizationId();

				if (_oldParentId == currentParentId) {
					return null;
				}

				Indexer<User> userIndexer =
					IndexerRegistryUtil.nullSafeGetIndexer(User.class);

				long userCount = UserLocalServiceUtil.getOrganizationUsersCount(
					_organizationId);

				for (int i = 0; i < userCount; i += 10000) {
					int start = i;
					int end = i + 10000;

					List<User> users =
						UserLocalServiceUtil.getOrganizationUsers(
							_organizationId, start, end);

					for (User user : users) {
						userIndexer.reindex(user);

						PermissionCacheUtil.clearCache(user.getUserId());
					}
				}

				return null;
			}

		};

		ThreadPoolExecutor threadPoolExecutor =
			PortalExecutorManagerUtil.getPortalExecutor(
				OrganizationModelListener.class.getName());

		if (_oldParentId == currentParentId) {
			TransactionCommitCallbackUtil.registerCallback(
				new Callable<Void>() {

					@Override
					public Void call() throws Exception {
						threadPoolExecutor.submit(callable);

						return null;
					}

				});
		}
		else {
			threadPoolExecutor.submit(callable);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		OrganizationModelListener.class);

	private long _oldParentId;
	private long _organizationId;

}