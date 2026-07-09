/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.internal.configuration.persistence.listener;

import com.liferay.portal.configuration.persistence.listener.ConfigurationModelListener;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.scheduler.StorageType;
import com.liferay.portal.kernel.scheduler.messaging.SchedulerResponse;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.site.configuration.manager.SitemapConfigurationManager;
import com.liferay.site.internal.constants.SitemapDestinationNames;
import com.liferay.site.manager.SitemapManager;
import com.liferay.site.storage.helper.SitemapStorageHelper;

import java.util.Date;
import java.util.Dictionary;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Cheryl Tang
 */
@Component(
	property = "model.class.name=com.liferay.site.internal.configuration.SitemapCompanyConfiguration",
	service = ConfigurationModelListener.class
)
public class SitemapConfigurationModelListener
	implements ConfigurationModelListener {

	@Override
	public void onAfterSave(String pid, Dictionary<String, Object> properties) {
		long companyId = GetterUtil.getLong(
			properties.get("companyId"), CompanyConstants.SYSTEM);

		if (companyId == CompanyConstants.SYSTEM) {
			return;
		}

		try {
			_onAfterSave(companyId);
		}
		catch (PortalException portalException) {
			_log.error(portalException);
		}
	}

	private void _deleteScheduledJobs(long companyId) throws PortalException {
		for (SchedulerResponse schedulerResponse :
				_schedulerEngineHelper.getScheduledJobs(
					SitemapDestinationNames.SITEMAP_REGENERATION,
					StorageType.PERSISTED)) {

			Message message = schedulerResponse.getMessage();

			if ((message != null) &&
				(message.getLong("companyId") == companyId)) {

				_schedulerEngineHelper.delete(
					schedulerResponse.getJobName(),
					SitemapDestinationNames.SITEMAP_REGENERATION,
					StorageType.PERSISTED);
			}
		}
	}

	private void _onAfterSave(long companyId) throws PortalException {
		if (_sitemapConfigurationManager.cachedGenerationCompanyEnabled(
				companyId) &&
			_sitemapConfigurationManager.indexModeAssetTypeCompanyEnabled(
				companyId)) {

			if (_sitemapStorageHelper.hasSitemapFiles(companyId)) {
				return;
			}

			Map<Long, String> assetTypeKeys =
				_sitemapManager.getAssetTypeKeys();

			for (String assetTypeKey : assetTypeKeys.values()) {
				_sitemapManager.scheduleRegenerateSitemap(
					assetTypeKey, companyId, 0, new Date());
			}

			return;
		}

		_sitemapStorageHelper.deleteSitemaps(companyId);

		_deleteScheduledJobs(companyId);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SitemapConfigurationModelListener.class);

	@Reference
	private SchedulerEngineHelper _schedulerEngineHelper;

	@Reference
	private SitemapConfigurationManager _sitemapConfigurationManager;

	@Reference
	private SitemapManager _sitemapManager;

	@Reference
	private SitemapStorageHelper _sitemapStorageHelper;

}