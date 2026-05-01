/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.configuration.manager;

import com.liferay.portal.kernel.module.configuration.ConfigurationException;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Jonathan McCann
 */
@ProviderType
public interface LLMManifestConfigurationManager {

	public String getMarkdown(long companyId, long groupId)
		throws ConfigurationException;

	public boolean isEnabled(long companyId, long groupId)
		throws ConfigurationException;

}