/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.internal.upgrade.v4_0_37;

import com.liferay.portal.configuration.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapDictionaryBuilder;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.site.constants.LegacySitemapIndexPropsKeys;
import com.liferay.site.internal.configuration.SitemapCompanyConfiguration;

/**
 * @author Jonathan McCann
 */
public class XMLSitemapIndexEnabledConfigurationUpgradeProcess
	extends UpgradeProcess {

	public XMLSitemapIndexEnabledConfigurationUpgradeProcess(
		CompanyLocalService companyLocalService,
		ConfigurationProvider configurationProvider) {

		_companyLocalService = companyLocalService;
		_configurationProvider = configurationProvider;
	}

	@Override
	protected void doUpgrade() throws Exception {
		String xmlSitemapIndexEnabled = PropsUtil.get(
			LegacySitemapIndexPropsKeys.XML_SITEMAP_INDEX_ENABLED);

		if (xmlSitemapIndexEnabled == null) {
			return;
		}

		_companyLocalService.forEachCompanyId(
			companyId -> {
				SitemapCompanyConfiguration sitemapCompanyConfiguration =
					_configurationProvider.getCompanyConfiguration(
						SitemapCompanyConfiguration.class, companyId);

				_configurationProvider.saveCompanyConfiguration(
					SitemapCompanyConfiguration.class, companyId,
					HashMapDictionaryBuilder.<String, Object>put(
						"companySitemapGroupIds",
						sitemapCompanyConfiguration.companySitemapGroupIds()
					).put(
						"includeCategories",
						sitemapCompanyConfiguration.includeCategories()
					).put(
						"includePages",
						sitemapCompanyConfiguration.includePages()
					).put(
						"includeWebContent",
						sitemapCompanyConfiguration.includeWebContent()
					).put(
						"xmlSitemapIndexEnabled",
						GetterUtil.getBoolean(xmlSitemapIndexEnabled)
					).build());
			});
	}

	private final CompanyLocalService _companyLocalService;
	private final ConfigurationProvider _configurationProvider;

}