/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.seo.studio.web.internal.scheduler.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.object.constants.ObjectEntryFolderConstants;
import com.liferay.object.model.ObjectEntry;
import com.liferay.petra.function.UnsafeRunnable;
import com.liferay.portal.kernel.scheduler.SchedulerJobConfiguration;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.FeatureFlag;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;
import com.liferay.seo.studio.web.internal.test.BaseSEOStudioTestCase;

import java.io.Serializable;

import java.util.Date;
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
public class CheckSEOStudioDomainsSchedulerJobConfigurationTest
	extends BaseSEOStudioTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE);

	@Test
	public void testCheckSEOStudioDomainsCreatesScans() throws Exception {
		addSEOStudioDomainObjectEntry(true);

		_setNextScanDate(
			new Date(System.currentTimeMillis() - Time.MINUTE),
			seoStudioDomainObjectEntry);

		_runJob();

		List<ObjectEntry> seoStudioScanObjectEntries =
			getSEOStudioScanObjectEntries(seoStudioDomainObjectEntry);

		Assert.assertEquals(
			seoStudioScanObjectEntries.toString(), 2,
			seoStudioScanObjectEntries.size());

		for (ObjectEntry seoStudioScanObjectEntry :
				seoStudioScanObjectEntries) {

			Map<String, Serializable> values =
				objectEntryLocalService.getValues(
					seoStudioScanObjectEntry.getObjectEntryId());

			Assert.assertEquals("queued", MapUtil.getString(values, "state"));
			Assert.assertEquals(
				"scheduled", MapUtil.getString(values, "triggeredBy"));
		}

		Map<String, Serializable> values = getValues(
			seoStudioDomainObjectEntry);

		Date nextScanDate = (Date)values.get("nextScanDate");

		Assert.assertTrue(nextScanDate.after(new Date()));
	}

	@Test
	public void testCheckSEOStudioDomainsDoesNotCreateDuplicateScans()
		throws Exception {

		addSEOStudioDomainObjectEntry(true);

		Date nextScanDate = new Date(System.currentTimeMillis() - Time.MINUTE);

		_setNextScanDate(nextScanDate, seoStudioDomainObjectEntry);

		_runJob();

		Assert.assertEquals(
			2, _getSEOStudioScanObjectEntriesCount(seoStudioDomainObjectEntry));

		_setNextScanDate(nextScanDate, seoStudioDomainObjectEntry);

		_runJob();

		Assert.assertEquals(
			2, _getSEOStudioScanObjectEntriesCount(seoStudioDomainObjectEntry));
	}

	@Test
	public void testCheckSEOStudioDomainsDoesNotCreateScansForDisabledDomain()
		throws Exception {

		addSEOStudioDomainObjectEntry(false);

		_setNextScanDate(
			new Date(System.currentTimeMillis() - Time.MINUTE),
			seoStudioDomainObjectEntry);

		_runJob();

		Assert.assertEquals(
			0, _getSEOStudioScanObjectEntriesCount(seoStudioDomainObjectEntry));
	}

	private int _getSEOStudioScanObjectEntriesCount(
			ObjectEntry seoStudioDomainObjectEntry)
		throws Exception {

		List<ObjectEntry> seoStudioScanObjectEntries =
			getSEOStudioScanObjectEntries(seoStudioDomainObjectEntry);

		return seoStudioScanObjectEntries.size();
	}

	private void _runJob() throws Exception {
		UnsafeRunnable<Exception> unsafeRunnable =
			_schedulerJobConfiguration.getJobExecutorUnsafeRunnable();

		unsafeRunnable.run();
	}

	private void _setNextScanDate(
			Date nextScanDate, ObjectEntry seoStudioDomainObjectEntry)
		throws Exception {

		objectEntryLocalService.partialUpdateObjectEntry(
			TestPropsValues.getUserId(),
			seoStudioDomainObjectEntry.getObjectEntryId(),
			ObjectEntryFolderConstants.PARENT_OBJECT_ENTRY_FOLDER_ID_DEFAULT,
			HashMapBuilder.<String, Serializable>put(
				"nextScanDate", nextScanDate
			).build(),
			ServiceContextTestUtil.getServiceContext(
				group.getGroupId(), TestPropsValues.getUserId()));
	}

	@Inject(
		filter = "component.name=com.liferay.seo.studio.web.internal.scheduler.CheckSEOStudioDomainsSchedulerJobConfiguration"
	)
	private SchedulerJobConfiguration _schedulerJobConfiguration;

}