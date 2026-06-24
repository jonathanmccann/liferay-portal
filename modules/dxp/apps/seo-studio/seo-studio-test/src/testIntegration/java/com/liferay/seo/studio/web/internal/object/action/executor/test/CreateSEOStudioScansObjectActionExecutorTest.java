/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.seo.studio.web.internal.object.action.executor.test;

import com.liferay.account.model.AccountEntry;
import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.object.action.engine.ObjectActionEngine;
import com.liferay.object.constants.ObjectActionTriggerConstants;
import com.liferay.object.constants.ObjectEntryFolderConstants;
import com.liferay.object.model.ObjectEntry;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.test.rule.FeatureFlag;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;
import com.liferay.seo.studio.web.internal.test.BaseSEOStudioTestCase;

import java.io.Serializable;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Jonathan McCann
 */
@FeatureFlag("LPD-44511")
@RunWith(Arquillian.class)
public class CreateSEOStudioScansObjectActionExecutorTest
	extends BaseSEOStudioTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE);

	@Test
	public void testExecute() throws Exception {
		AccountEntry accountEntry = addAccountEntry();

		seoStudioInstanceObjectEntry = addSEOStudioInstanceObjectEntry(
			accountEntry);

		String includedPaths = RandomTestUtil.randomString();
		int maxPagesPerScan = RandomTestUtil.randomInt();
		String scope = RandomTestUtil.randomString();

		seoStudioDomainObjectEntry = _addSEOStudioDomainObjectEntry(
			accountEntry,
			JSONUtil.put(
				"engines",
				JSONUtil.put(
					"aiGenerated", JSONUtil.put("enabled", true)
				).put(
					"crawler", JSONUtil.put("enabled", true)
				).put(
					"gsc", JSONUtil.put("enabled", false)
				).put(
					"pageSpeed",
					JSONUtil.put(
						"enabled", true
					).put(
						"includedPaths", includedPaths
					).put(
						"maxPagesPerScan", maxPagesPerScan
					).put(
						"scope", scope
					)
				)
			).toString(),
			seoStudioInstanceObjectEntry);

		_executeCreateScans(seoStudioDomainObjectEntry);

		List<ObjectEntry> seoStudioScanObjectEntries =
			getSEOStudioScanObjectEntries(seoStudioDomainObjectEntry);

		Assert.assertEquals(
			seoStudioScanObjectEntries.toString(), 3,
			seoStudioScanObjectEntries.size());

		Map<String, ObjectEntry> seoStudioScanObjectEntryMap =
			_getSEOStudioScanObjectEntryMap(seoStudioScanObjectEntries);

		Assert.assertNotNull(seoStudioScanObjectEntryMap.get("aiGenerated"));
		Assert.assertNotNull(seoStudioScanObjectEntryMap.get("crawler"));
		Assert.assertNull(seoStudioScanObjectEntryMap.get("gsc"));

		ObjectEntry seoStudioScanObjectEntry = seoStudioScanObjectEntryMap.get(
			"pageSpeed");

		Map<String, Serializable> values = objectEntryLocalService.getValues(
			seoStudioScanObjectEntry.getObjectEntryId());

		Assert.assertEquals(
			accountEntry.getAccountEntryId(),
			MapUtil.getLong(
				values, "r_accountToSEOStudioScans_accountEntryId"));
		Assert.assertEquals("queued", MapUtil.getString(values, "state"));
		Assert.assertEquals("manual", MapUtil.getString(values, "triggeredBy"));

		JSONObject scopeConfigJSONObject = JSONFactoryUtil.createJSONObject(
			MapUtil.getString(values, "scopeConfig"));

		Assert.assertFalse(
			scopeConfigJSONObject.toString(),
			scopeConfigJSONObject.has("enabled"));
		Assert.assertEquals(
			includedPaths, scopeConfigJSONObject.getString("includedPaths"));
		Assert.assertEquals(
			maxPagesPerScan, scopeConfigJSONObject.getInt("maxPagesPerScan"));
		Assert.assertEquals(scope, scopeConfigJSONObject.getString("scope"));
	}

	private ObjectEntry _addSEOStudioDomainObjectEntry(
			AccountEntry accountEntry, String scanConfigJSON,
			ObjectEntry seoStudioInstanceObjectEntry)
		throws Exception {

		return objectEntryLocalService.addObjectEntry(
			0, TestPropsValues.getUserId(),
			seoStudioDomainObjectDefinition.getObjectDefinitionId(),
			ObjectEntryFolderConstants.PARENT_OBJECT_ENTRY_FOLDER_ID_DEFAULT,
			null,
			HashMapBuilder.<String, Serializable>put(
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
				"scanConfig", scanConfigJSON
			).build(),
			ServiceContextTestUtil.getServiceContext(
				group.getGroupId(), TestPropsValues.getUserId()));
	}

	private void _executeCreateScans(ObjectEntry seoStudioDomainObjectEntry)
		throws Exception {

		_objectActionEngine.executeObjectAction(
			"createScans", ObjectActionTriggerConstants.KEY_STANDALONE,
			seoStudioDomainObjectDefinition.getObjectDefinitionId(),
			JSONUtil.put(
				"classPK", seoStudioDomainObjectEntry.getObjectEntryId()
			).put(
				"objectEntry",
				HashMapBuilder.<String, Object>putAll(
					seoStudioDomainObjectEntry.getModelAttributes()
				).put(
					"values", seoStudioDomainObjectEntry.getValues()
				).build()
			),
			TestPropsValues.getUserId());
	}

	private Map<String, ObjectEntry> _getSEOStudioScanObjectEntryMap(
			List<ObjectEntry> seoStudioScanObjectEntries)
		throws Exception {

		Map<String, ObjectEntry> seoStudioScanObjectEntryMap =
			new LinkedHashMap<>();

		for (ObjectEntry seoStudioScanObjectEntry :
				seoStudioScanObjectEntries) {

			seoStudioScanObjectEntryMap.put(
				MapUtil.getString(
					objectEntryLocalService.getValues(
						seoStudioScanObjectEntry.getObjectEntryId()),
					"scanType"),
				seoStudioScanObjectEntry);
		}

		return seoStudioScanObjectEntryMap;
	}

	@Inject
	private ObjectActionEngine _objectActionEngine;

}