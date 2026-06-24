/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.seo.studio.web.internal.test;

import com.liferay.account.constants.AccountConstants;
import com.liferay.account.model.AccountEntry;
import com.liferay.account.service.AccountEntryLocalService;
import com.liferay.object.constants.ObjectEntryFolderConstants;
import com.liferay.object.model.ObjectAction;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.model.ObjectRelationship;
import com.liferay.object.service.ObjectActionLocalService;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.object.service.ObjectRelationshipLocalService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.test.rule.Inject;
import com.liferay.site.initializer.SiteInitializer;
import com.liferay.site.initializer.SiteInitializerRegistry;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 * @author Jonathan McCann
 */
public abstract class BaseSEOStudioTestCase {

	@BeforeClass
	public static void setUpClass() throws Exception {
		_originalName = PrincipalThreadLocal.getName();

		PrincipalThreadLocal.setName(TestPropsValues.getUserId());

		_originalPermissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		PermissionThreadLocal.setPermissionChecker(
			PermissionCheckerFactoryUtil.create(TestPropsValues.getUser()));

		group = GroupTestUtil.addGroup();

		ServiceContextThreadLocal.pushServiceContext(
			ServiceContextTestUtil.getServiceContext(
				group.getGroupId(), TestPropsValues.getUserId()));

		SiteInitializer siteInitializer =
			siteInitializerRegistry.getSiteInitializer(
				"com.liferay.seo.studio.site.initializer");

		siteInitializer.initialize(group.getGroupId());

		seoStudioDomainObjectDefinition =
			objectDefinitionLocalService.
				getObjectDefinitionByExternalReferenceCode(
					"L_SEO_STUDIO_DOMAIN", TestPropsValues.getCompanyId());
		seoStudioInstanceObjectDefinition =
			objectDefinitionLocalService.
				getObjectDefinitionByExternalReferenceCode(
					"L_SEO_STUDIO_INSTANCE", TestPropsValues.getCompanyId());

		ObjectDefinition seoStudioScanObjectDefinition =
			objectDefinitionLocalService.
				getObjectDefinitionByExternalReferenceCode(
					"L_SEO_STUDIO_SCAN", TestPropsValues.getCompanyId());

		for (ObjectAction objectAction :
				objectActionLocalService.getObjectActions(
					seoStudioScanObjectDefinition.getObjectDefinitionId())) {

			objectActionLocalService.deleteObjectAction(objectAction);
		}
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		ServiceContextThreadLocal.popServiceContext();

		PermissionThreadLocal.setPermissionChecker(_originalPermissionChecker);

		PrincipalThreadLocal.setName(_originalName);
	}

	@After
	public void tearDown() throws Exception {
		if (seoStudioDomainObjectEntry != null) {
			for (ObjectEntry seoStudioScanObjectEntry :
					getSEOStudioScanObjectEntries(seoStudioDomainObjectEntry)) {

				objectEntryLocalService.deleteObjectEntry(
					seoStudioScanObjectEntry.getObjectEntryId());
			}

			objectEntryLocalService.deleteObjectEntry(
				seoStudioDomainObjectEntry.getObjectEntryId());

			seoStudioDomainObjectEntry = null;
		}

		if (seoStudioInstanceObjectEntry != null) {
			objectEntryLocalService.deleteObjectEntry(
				seoStudioInstanceObjectEntry.getObjectEntryId());

			seoStudioInstanceObjectEntry = null;
		}
	}

	protected AccountEntry addAccountEntry() throws Exception {
		return accountEntryLocalService.addAccountEntry(
			RandomTestUtil.randomString(), TestPropsValues.getUserId(),
			AccountConstants.PARENT_ACCOUNT_ENTRY_ID_DEFAULT,
			RandomTestUtil.randomString(), null, null,
			RandomTestUtil.randomString() + "@liferay.com", null, null,
			AccountConstants.ACCOUNT_ENTRY_TYPE_BUSINESS,
			WorkflowConstants.STATUS_APPROVED,
			ServiceContextTestUtil.getServiceContext());
	}

	protected void addSEOStudioDomainObjectEntry(boolean autoScanEnabled)
		throws Exception {

		AccountEntry accountEntry = addAccountEntry();

		seoStudioInstanceObjectEntry = addSEOStudioInstanceObjectEntry(
			accountEntry);

		seoStudioDomainObjectEntry = objectEntryLocalService.addObjectEntry(
			0, TestPropsValues.getUserId(),
			seoStudioDomainObjectDefinition.getObjectDefinitionId(),
			ObjectEntryFolderConstants.PARENT_OBJECT_ENTRY_FOLDER_ID_DEFAULT,
			null,
			HashMapBuilder.<String, Serializable>put(
				"autoScanEnabled", autoScanEnabled
			).put(
				"hostname", RandomTestUtil.randomString()
			).put(
				"name", RandomTestUtil.randomString()
			).put(
				"r_accountToSEOStudioDomains_accountEntryId",
				accountEntry.getAccountEntryId()
			).put(
				"r_seoStudioInstanceToSEOStudioDomains_seoStudioInstanceId",
				seoStudioInstanceObjectEntry.getObjectEntryId()
			).put(
				"scanConfig",
				JSONUtil.put(
					"engines",
					JSONUtil.put(
						"crawler", JSONUtil.put("enabled", true)
					).put(
						"pageSpeed", JSONUtil.put("enabled", true)
					)
				).toString()
			).put(
				"scanFrequency", "daily"
			).put(
				"scanTime", "09:00"
			).build(),
			ServiceContextTestUtil.getServiceContext(
				group.getGroupId(), TestPropsValues.getUserId()));
	}

	protected ObjectEntry addSEOStudioInstanceObjectEntry(
			AccountEntry accountEntry)
		throws Exception {

		return objectEntryLocalService.addObjectEntry(
			0, TestPropsValues.getUserId(),
			seoStudioInstanceObjectDefinition.getObjectDefinitionId(),
			ObjectEntryFolderConstants.PARENT_OBJECT_ENTRY_FOLDER_ID_DEFAULT,
			null,
			HashMapBuilder.<String, Serializable>put(
				"hostname", RandomTestUtil.randomString()
			).put(
				"name", RandomTestUtil.randomString()
			).put(
				"r_accountToSEOStudioInstances_accountEntryId",
				accountEntry.getAccountEntryId()
			).build(),
			ServiceContextTestUtil.getServiceContext(
				group.getGroupId(), TestPropsValues.getUserId()));
	}

	protected List<ObjectEntry> getSEOStudioScanObjectEntries(
			ObjectEntry seoStudioDomainObjectEntry)
		throws Exception {

		ObjectRelationship objectRelationship =
			objectRelationshipLocalService.fetchObjectRelationship(
				seoStudioDomainObjectDefinition.getObjectDefinitionId(),
				"seoStudioDomainToSEOStudioScans");

		return objectEntryLocalService.getOneToManyObjectEntries(
			seoStudioDomainObjectEntry.getGroupId(),
			objectRelationship.getObjectRelationshipId(), null, true,
			seoStudioDomainObjectEntry.getObjectEntryId(), true, null,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	protected Map<String, Serializable> getValues(ObjectEntry objectEntry)
		throws Exception {

		return objectEntryLocalService.getValues(
			objectEntry.getObjectEntryId());
	}

	protected static Group group;

	@Inject
	protected static ObjectActionLocalService objectActionLocalService;

	@Inject
	protected static ObjectDefinitionLocalService objectDefinitionLocalService;

	protected static ObjectDefinition seoStudioDomainObjectDefinition;
	protected static ObjectDefinition seoStudioInstanceObjectDefinition;

	@Inject
	protected static SiteInitializerRegistry siteInitializerRegistry;

	@Inject
	protected AccountEntryLocalService accountEntryLocalService;

	@Inject
	protected ObjectEntryLocalService objectEntryLocalService;

	@Inject
	protected ObjectRelationshipLocalService objectRelationshipLocalService;

	protected ObjectEntry seoStudioDomainObjectEntry;
	protected ObjectEntry seoStudioInstanceObjectEntry;

	private static String _originalName;
	private static PermissionChecker _originalPermissionChecker;

}