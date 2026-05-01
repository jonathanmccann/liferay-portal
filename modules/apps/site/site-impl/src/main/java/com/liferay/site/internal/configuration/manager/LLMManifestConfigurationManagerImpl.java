/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.internal.configuration.manager;

import com.liferay.portal.configuration.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.site.configuration.manager.LLMManifestConfigurationManager;
import com.liferay.site.internal.configuration.LLMManifestGroupConfiguration;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jonathan McCann
 */
@Component(service = LLMManifestConfigurationManager.class)
public class LLMManifestConfigurationManagerImpl
	implements LLMManifestConfigurationManager {

	@Override
	public String getMarkdown(long companyId, long groupId)
		throws ConfigurationException {

		LLMManifestGroupConfiguration llmManifestGroupConfiguration =
			_configurationProvider.getGroupConfiguration(
				LLMManifestGroupConfiguration.class, companyId, groupId);

		return llmManifestGroupConfiguration.markdown();
	}

	@Override
	public boolean isEnabled(long companyId, long groupId)
		throws ConfigurationException {

		LLMManifestGroupConfiguration llmManifestGroupConfiguration =
			_configurationProvider.getGroupConfiguration(
				LLMManifestGroupConfiguration.class, companyId, groupId);

		return llmManifestGroupConfiguration.enabled();
	}

	@Reference
	private ConfigurationProvider _configurationProvider;

}