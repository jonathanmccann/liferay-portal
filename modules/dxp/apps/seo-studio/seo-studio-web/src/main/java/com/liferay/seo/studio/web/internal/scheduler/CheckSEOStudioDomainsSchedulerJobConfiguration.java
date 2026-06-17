/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.seo.studio.web.internal.scheduler;

import com.liferay.object.constants.ObjectEntryFolderConstants;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.object.service.ObjectFieldLocalService;
import com.liferay.petra.function.UnsafeRunnable;
import com.liferay.petra.sql.dsl.Column;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.feature.flag.FeatureFlagManagerUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.scheduler.SchedulerJobConfiguration;
import com.liferay.portal.kernel.scheduler.TimeUnit;
import com.liferay.portal.kernel.scheduler.TriggerConfiguration;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.seo.studio.web.internal.scan.SEOStudioScanCreator;
import com.liferay.seo.studio.web.internal.util.SEOStudioScanScheduleUtil;

import java.io.Serializable;

import java.time.Instant;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jonathan McCann
 */
@Component(service = SchedulerJobConfiguration.class)
public class CheckSEOStudioDomainsSchedulerJobConfiguration
	implements SchedulerJobConfiguration {

	@Override
	public UnsafeRunnable<Exception> getJobExecutorUnsafeRunnable() {
		return () -> _companyLocalService.forEachCompanyId(
			this::_checkSEOStudioDomains);
	}

	@Override
	public TriggerConfiguration getTriggerConfiguration() {
		return TriggerConfiguration.createTriggerConfiguration(
			5, TimeUnit.MINUTE);
	}

	private void _checkSEOStudioDomains(long companyId) throws Exception {
		if (!FeatureFlagManagerUtil.isEnabled(companyId, "LPD-44511")) {
			return;
		}

		ObjectDefinition objectDefinition =
			_objectDefinitionLocalService.
				fetchObjectDefinitionByExternalReferenceCode(
					"L_SEO_STUDIO_DOMAIN", companyId);

		if (objectDefinition == null) {
			return;
		}

		Column<?, Boolean> autoScanEnabledColumn =
			(Column<?, Boolean>)_objectFieldLocalService.getColumn(
				objectDefinition.getObjectDefinitionId(), "autoScanEnabled");
		Column<?, Date> nextScanDateColumn =
			(Column<?, Date>)_objectFieldLocalService.getColumn(
				objectDefinition.getObjectDefinitionId(), "nextScanDate");

		List<Long> seoStudioDomainIds = _objectEntryLocalService.getPrimaryKeys(
			new Long[] {0L}, companyId, 0,
			objectDefinition.getObjectDefinitionId(),
			autoScanEnabledColumn.eq(
				true
			).and(
				nextScanDateColumn.lte(new Date())
			),
			false, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		for (long seoStudioDomainId : seoStudioDomainIds) {
			try {
				_createSEOStudioScans(seoStudioDomainId);
			}
			catch (Exception exception) {
				_log.error(
					"Unable to create scans for SEO Studio domain " +
						seoStudioDomainId,
					exception);
			}
		}
	}

	private void _createSEOStudioScans(long seoStudioDomainId)
		throws Exception {

		ObjectEntry objectEntry = _objectEntryLocalService.getObjectEntry(
			seoStudioDomainId);

		Map<String, Serializable> values = objectEntry.getValues();

		long userId = objectEntry.getUserId();

		_seoStudioScanCreator.createScans(
			(Date)values.get("nextScanDate"), seoStudioDomainId, "scheduled",
			userId);

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCompanyId(objectEntry.getCompanyId());
		serviceContext.setUserId(userId);

		_objectEntryLocalService.partialUpdateObjectEntry(
			userId, seoStudioDomainId,
			ObjectEntryFolderConstants.PARENT_OBJECT_ENTRY_FOLDER_ID_DEFAULT,
			HashMapBuilder.<String, Serializable>put(
				"nextScanDate",
				SEOStudioScanScheduleUtil.getNextScanDate(
					Instant.now(), MapUtil.getInteger(values, "scanDayOfMonth"),
					MapUtil.getString(values, "scanDayOfWeek"),
					MapUtil.getString(values, "scanFrequency"),
					MapUtil.getString(values, "scanTime"),
					MapUtil.getString(values, "scanTimeZone"))
			).build(),
			serviceContext);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CheckSEOStudioDomainsSchedulerJobConfiguration.class);

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference
	private ObjectDefinitionLocalService _objectDefinitionLocalService;

	@Reference
	private ObjectEntryLocalService _objectEntryLocalService;

	@Reference
	private ObjectFieldLocalService _objectFieldLocalService;

	@Reference
	private SEOStudioScanCreator _seoStudioScanCreator;

}