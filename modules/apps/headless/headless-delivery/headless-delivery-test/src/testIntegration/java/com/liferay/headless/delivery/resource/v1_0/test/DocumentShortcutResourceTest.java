/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.delivery.resource.v1_0.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppService;
import com.liferay.headless.delivery.client.dto.v1_0.DocumentShortcut;
import com.liferay.headless.delivery.client.pagination.Page;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileShortcut;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.constants.TestDataConstants;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;
import com.liferay.portal.vulcan.util.GroupUtil;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import java.util.List;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Javier Gamarra
 */
@RunWith(Arquillian.class)
public class DocumentShortcutResourceTest
	extends BaseDocumentShortcutResourceTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE);

	@Override
	@Test
	public void testGetAssetLibraryDocumentShortcutsPage() throws Exception {
		Long assetLibraryId =
			testGetAssetLibraryDocumentShortcutsPage_getAssetLibraryId();
		Long irrelevantAssetLibraryId =
			testGetAssetLibraryDocumentShortcutsPage_getIrrelevantAssetLibraryId();

		Page<DocumentShortcut> page =
			documentShortcutResource.getAssetLibraryDocumentShortcutsPage(
				assetLibraryId);

		long totalCount = page.getTotalCount();

		if (irrelevantAssetLibraryId != null) {
			DocumentShortcut irrelevantDocumentShortcut = _addDocumentShortcut(
				irrelevantGroup);

			page =
				documentShortcutResource.getAssetLibraryDocumentShortcutsPage(
					irrelevantAssetLibraryId);

			Assert.assertEquals(totalCount + 1, page.getTotalCount());

			assertContains(
				irrelevantDocumentShortcut,
				(List<DocumentShortcut>)page.getItems());
			assertValid(
				page,
				testGetAssetLibraryDocumentShortcutsPage_getExpectedActions(
					irrelevantAssetLibraryId));
		}

		DocumentShortcut documentShortcut1 = _addDocumentShortcut(
			testDepotEntry.getGroup());

		DocumentShortcut documentShortcut2 = _addDocumentShortcut(
			testDepotEntry.getGroup());

		page = documentShortcutResource.getAssetLibraryDocumentShortcutsPage(
			assetLibraryId);

		Assert.assertEquals(totalCount + 2, page.getTotalCount());

		assertContains(
			documentShortcut1, (List<DocumentShortcut>)page.getItems());
		assertContains(
			documentShortcut2, (List<DocumentShortcut>)page.getItems());
		assertValid(
			page,
			testGetAssetLibraryDocumentShortcutsPage_getExpectedActions(
				assetLibraryId));
	}

	@Override
	@Test
	public void testGetDocumentShortcut() throws Exception {
		DocumentShortcut postDocumentShortcut = _addDocumentShortcut();

		DocumentShortcut getDocumentShortcut =
			documentShortcutResource.getDocumentShortcut(
				postDocumentShortcut.getId());

		assertEquals(postDocumentShortcut, getDocumentShortcut);
		assertValid(getDocumentShortcut);
	}

	@Override
	@Test
	public void testGetSiteDocumentShortcutsPage() throws Exception {
		Long siteId = testGetSiteDocumentShortcutsPage_getSiteId();
		Long irrelevantSiteId =
			testGetSiteDocumentShortcutsPage_getIrrelevantSiteId();

		Page<DocumentShortcut> page =
			documentShortcutResource.getSiteDocumentShortcutsPage(siteId);

		long totalCount = page.getTotalCount();

		if (irrelevantSiteId != null) {
			DocumentShortcut irrelevantDocumentShortcut = _addDocumentShortcut(
				irrelevantGroup);

			page = documentShortcutResource.getSiteDocumentShortcutsPage(
				irrelevantSiteId);

			Assert.assertEquals(totalCount + 1, page.getTotalCount());

			assertContains(
				irrelevantDocumentShortcut,
				(List<DocumentShortcut>)page.getItems());
			assertValid(
				page,
				testGetSiteDocumentShortcutsPage_getExpectedActions(
					irrelevantSiteId));
		}

		DocumentShortcut documentShortcut1 = _addDocumentShortcut(testGroup);

		DocumentShortcut documentShortcut2 = _addDocumentShortcut(testGroup);

		page = documentShortcutResource.getSiteDocumentShortcutsPage(siteId);

		Assert.assertEquals(totalCount + 2, page.getTotalCount());

		assertContains(
			documentShortcut1, (List<DocumentShortcut>)page.getItems());
		assertContains(
			documentShortcut2, (List<DocumentShortcut>)page.getItems());
		assertValid(
			page, testGetSiteDocumentShortcutsPage_getExpectedActions(siteId));
	}

	@Override
	protected DocumentShortcut testGetDocumentShortcut_addDocumentShortcut()
		throws Exception {

		return _addDocumentShortcut();
	}

	@Override
	protected DocumentShortcut testGraphQLDocumentShortcut_addDocumentShortcut()
		throws Exception {

		return _addDocumentShortcut();
	}

	private DocumentShortcut _addDocumentShortcut() throws Exception {
		return _addDocumentShortcut(testGroup);
	}

	private DocumentShortcut _addDocumentShortcut(Group group)
		throws Exception {

		byte[] bytes = TestDataConstants.TEST_BYTE_ARRAY;

		InputStream inputStream = new ByteArrayInputStream(bytes);

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				testGroup.getGroupId(), TestPropsValues.getUserId());

		FileEntry fileEntry = _dlAppService.addFileEntry(
			null, group.getGroupId(),
			DLFolderConstants.DEFAULT_PARENT_FOLDER_ID,
			RandomTestUtil.randomString(),
			ContentTypes.APPLICATION_OCTET_STREAM,
			RandomTestUtil.randomString(), RandomTestUtil.randomString(),
			StringPool.BLANK, StringPool.BLANK, inputStream, bytes.length, null,
			null, null, serviceContext);

		FileShortcut fileShortcut = _dlAppService.addFileShortcut(
			fileEntry.getRepositoryId(), fileEntry.getFolderId(),
			fileEntry.getFileEntryId(), serviceContext);

		return new DocumentShortcut() {
			{
				assetLibraryKey = GroupUtil.getAssetLibraryKey(group);
				dateCreated = fileShortcut.getCreateDate();
				dateModified = fileShortcut.getModifiedDate();
				folderId = fileShortcut.getFolderId();
				id = fileShortcut.getFileShortcutId();
				siteId = fileShortcut.getGroupId();
				targetDocumentId = fileShortcut.getToFileEntryId();
				title = fileShortcut.getToTitle();
			}
		};
	}

	@Inject
	private static DLAppService _dlAppService;

}