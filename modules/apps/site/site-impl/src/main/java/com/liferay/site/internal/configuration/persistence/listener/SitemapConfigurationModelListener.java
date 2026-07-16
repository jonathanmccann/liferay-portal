/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.internal.configuration.persistence.listener;

import com.liferay.portal.configuration.persistence.listener.ConfigurationModelListener;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.site.manager.SitemapManager;

import java.util.Dictionary;

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
			_sitemapManager.reconcileSitemapCompanyConfiguration(
				companyId,
				GetterUtil.getBoolean(
					properties.get("cachedGenerationEnabled")),
				GetterUtil.getBoolean(properties.get("xmlSitemapIndexEnabled")),
				GetterUtil.getString(properties.get("xmlSitemapIndexMode")));
		}
		catch (PortalException portalException) {
			_log.error(portalException);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SitemapConfigurationModelListener.class);

	@Reference
	private SitemapManager _sitemapManager;

}