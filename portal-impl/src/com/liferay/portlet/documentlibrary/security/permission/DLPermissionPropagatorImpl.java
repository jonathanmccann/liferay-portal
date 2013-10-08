/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.portlet.documentlibrary.security.permission;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryDefinition;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.security.permission.BasePermissionPropagator;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFileShortcut;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.documentlibrary.service.DLFolderLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.persistence.DLFileEntryActionableDynamicQuery;
import com.liferay.portlet.documentlibrary.service.persistence.DLFileShortcutActionableDynamicQuery;
import com.liferay.portlet.documentlibrary.service.persistence.DLFolderActionableDynamicQuery;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;

/**
 * @author Kenneth Chang
 */
public class DLPermissionPropagatorImpl extends BasePermissionPropagator {

	@Override
	public void propagateRolePermissions(
			ActionRequest actionRequest, String className, String primKey,
			long[] roleIds)
		throws PortalException, SystemException {

		if (className.equals(DLFolder.class.getName())) {
			propagateFolderRolePermissions(
				actionRequest, className, primKey, roleIds);
		}
		else if (className.equals("com.liferay.portlet.documentlibrary")) {
			propagateDLRolePermissions(
				actionRequest, className, primKey, roleIds);
		}
	}

	protected void propagateDLRolePermissions(
			final ActionRequest actionRequest, final String className,
			String primKey, final long[] roleIds)
		throws PortalException, SystemException {

		final long groupId = GetterUtil.getLong(primKey);

		ActionableDynamicQuery folderActionableDynamicQuery =
			new DLFolderActionableDynamicQuery() {

			@Override
			protected void performAction(Object object)
				throws PortalException, SystemException {

				DLFolder folder = (DLFolder)object;

				propagateFolderRolePermissions(
					actionRequest, className, groupId, folder.getFolderId(),
					roleIds);
			}

		};

		folderActionableDynamicQuery.setGroupId(groupId);

		folderActionableDynamicQuery.performActions();

		ActionableDynamicQuery fileEntryActionableDynamicQuery =
			new DLFileEntryActionableDynamicQuery() {

			@Override
			protected void performAction(Object object)
				throws PortalException, SystemException {

				DLFileEntry fileEntry = (DLFileEntry)object;

				propagateFileEntryRolePermissions(
					actionRequest, className, groupId,
					fileEntry.getFileEntryId(), roleIds);
			}

		};

		fileEntryActionableDynamicQuery.setGroupId(groupId);

		fileEntryActionableDynamicQuery.performActions();

		ActionableDynamicQuery fileShortcutActionableDynamicQuery =
			new DLFileShortcutActionableDynamicQuery() {

			@Override
			protected void performAction(Object object)
				throws PortalException, SystemException {

				DLFileShortcut fileShortcut = (DLFileShortcut)object;

				propagateFileShortcutRolePermissions(
					actionRequest, className, groupId,
					fileShortcut.getFileShortcutId(), roleIds);
			}

		};

		fileShortcutActionableDynamicQuery.setGroupId(groupId);

		fileShortcutActionableDynamicQuery.performActions();
	}

	protected void propagateFileEntryRolePermissions(
			ActionRequest actionRequest, String className, long primaryKey,
			long fileEntryId, long[] roleIds)
		throws PortalException, SystemException {

		for (long roleId : roleIds) {
			propagateRolePermissions(
				actionRequest, roleId, className, primaryKey,
				DLFileEntry.class.getName(), fileEntryId);
		}
	}

	protected void propagateFileShortcutRolePermissions(
			ActionRequest actionRequest, String className, long primaryKey,
			long fileShortcutId, long[] roleIds)
		throws PortalException, SystemException {

		for (long roleId : roleIds) {
			propagateRolePermissions(
				actionRequest, roleId, className, primaryKey,
				DLFileShortcut.class.getName(), fileShortcutId);
		}
	}

	protected void propagateFolderRolePermissions(
			ActionRequest actionRequest, String className, long primaryKey,
			long folderId, long[] roleIds)
		throws PortalException, SystemException {

		for (long roleId : roleIds) {
			propagateRolePermissions(
				actionRequest, roleId, className, primaryKey,
				DLFolder.class.getName(), folderId);
		}
	}

	protected void propagateFolderRolePermissions(
			final ActionRequest actionRequest, final String className,
			String primKey, final long[] roleIds)
		throws PortalException, SystemException {

		final long folderId = GetterUtil.getLong(primKey);

		DLFolder folder = DLFolderLocalServiceUtil.getFolder(folderId);

		long folderGroupId = folder.getGroupId();

		List<Object> foldersAndFileEntriesAndFileShortcuts =
			DLFolderLocalServiceUtil.getFoldersAndFileEntriesAndFileShortcuts(
				folderGroupId, folderId, null, false,
				new QueryDefinition(WorkflowConstants.STATUS_ANY));

		for (Object folderOrFile : foldersAndFileEntriesAndFileShortcuts) {
			if (folderOrFile instanceof DLFileEntry) {
				DLFileEntry fileEntry = (DLFileEntry)folderOrFile;

				propagateFileEntryRolePermissions(
						actionRequest, className, folderId,
						fileEntry.getFileEntryId(), roleIds);
			}
			else if (folderOrFile instanceof DLFileShortcut) {
				DLFileShortcut fileShortcut = (DLFileShortcut)folderOrFile;

				propagateFileShortcutRolePermissions(
					actionRequest, className, folderId,
					fileShortcut.getFileShortcutId(), roleIds);
			}
			else {
				folder = (DLFolder)folderOrFile;

				List<Long> folderIds = new ArrayList<Long>();

				folderIds.add(folder.getFolderId());

				DLFolderLocalServiceUtil.getSubfolderIds(
					folderIds, folderGroupId, folderIds.get(0));

				for (final long addFolderId : folderIds) {
					propagateFolderRolePermissions(
						actionRequest, className, folderId, addFolderId,
						roleIds);

					ActionableDynamicQuery fileEntryActionableDynamicQuery =
						new DLFileEntryActionableDynamicQuery() {

						@Override
						protected void addCriteria(DynamicQuery dynamicQuery) {
							Property folderIdProperty =
								PropertyFactoryUtil.forName("folderId");

							dynamicQuery.add(folderIdProperty.eq(addFolderId));
						}

						@Override
						protected void performAction(Object object)
							throws PortalException, SystemException {

							DLFileEntry fileEntry = (DLFileEntry)object;

							propagateFileEntryRolePermissions(
								actionRequest, className, folderId,
								fileEntry.getFileEntryId(), roleIds);
						}

					};

					fileEntryActionableDynamicQuery.setGroupId(folderGroupId);

					fileEntryActionableDynamicQuery.performActions();

					ActionableDynamicQuery fileShortcutActionableDynamicQuery =
						new DLFileShortcutActionableDynamicQuery() {

						@Override
						protected void addCriteria(DynamicQuery dynamicQuery) {
							Property folderIdProperty =
								PropertyFactoryUtil.forName("folderId");

							dynamicQuery.add(folderIdProperty.eq(addFolderId));
						}

						@Override
						protected void performAction(Object object)
							throws PortalException, SystemException {

							DLFileShortcut fileShortcut =
								(DLFileShortcut)object;

							propagateFileShortcutRolePermissions(
								actionRequest, className, folderId,
								fileShortcut.getFileShortcutId(), roleIds);
						}

					};

					fileShortcutActionableDynamicQuery.setGroupId(
						folderGroupId);

					fileShortcutActionableDynamicQuery.performActions();
				}
			}
		}
	}

}