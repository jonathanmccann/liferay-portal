/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.seo.studio.web.internal.object.action.executor.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.object.constants.ObjectEntryFolderConstants;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.test.rule.FeatureFlag;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;
import com.liferay.seo.studio.web.internal.test.BaseSEOStudioTestCase;

import java.io.Serializable;

import java.util.Date;
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
public class ComputeSEOStudioDomainNextScanDateObjectActionExecutorTest
	extends BaseSEOStudioTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE);

	@Test
	public void testExecute() throws Exception {
		addSEOStudioDomainObjectEntry(false);

		_updateAutoScanEnabled(true);

		Map<String, Serializable> values = getValues(
			seoStudioDomainObjectEntry);

		Date nextScanDate = (Date)values.get("nextScanDate");

		Assert.assertTrue(nextScanDate.after(new Date()));
	}

	@Test
	public void testExecuteWithAutoScanDisabled() throws Exception {
		addSEOStudioDomainObjectEntry(false);

		_updateAutoScanEnabled(false);

		Map<String, Serializable> values = getValues(
			seoStudioDomainObjectEntry);

		Assert.assertNull(values.get("nextScanDate"));
	}

	private void _updateAutoScanEnabled(boolean autoScanEnabled)
		throws Exception {

		objectEntryLocalService.partialUpdateObjectEntry(
			TestPropsValues.getUserId(),
			seoStudioDomainObjectEntry.getObjectEntryId(),
			ObjectEntryFolderConstants.PARENT_OBJECT_ENTRY_FOLDER_ID_DEFAULT,
			HashMapBuilder.<String, Serializable>put(
				"autoScanEnabled", autoScanEnabled
			).build(),
			ServiceContextTestUtil.getServiceContext(
				group.getGroupId(), TestPropsValues.getUserId()));
	}

}