/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.internal.struts;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.model.VirtualHost;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.LayoutSetLocalService;
import com.liferay.portal.kernel.service.VirtualHostLocalService;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.struts.StrutsAction;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PropsValues;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.site.configuration.manager.LLMManifestConfigurationManager;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jonathan McCann
 */
@Component(property = "path=/portal/llm_manifest", service = StrutsAction.class)
public class LLMManifestStrutsAction implements StrutsAction {

	@Override
	public String execute(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {

		try {
			String host = GetterUtil.getString(
				_portal.getForwardedHost(httpServletRequest));

			Group group = null;

			VirtualHost virtualHost = _virtualHostLocalService.fetchVirtualHost(
				host);

			if ((virtualHost != null) && (virtualHost.getLayoutSetId() > 0)) {
				LayoutSet layoutSet = _layoutSetLocalService.fetchLayoutSet(
					virtualHost.getLayoutSetId());

				if (layoutSet != null) {
					group = _groupLocalService.fetchGroup(
						layoutSet.getGroupId());
				}
			}
			else {
				Company company = _portal.getCompany(httpServletRequest);

				if (host.equals(company.getVirtualHostname()) &&
					Validator.isNotNull(
						PropsValues.VIRTUAL_HOSTS_DEFAULT_SITE_NAME)) {

					group = _groupLocalService.getGroup(
						company.getCompanyId(),
						PropsValues.VIRTUAL_HOSTS_DEFAULT_SITE_NAME);
				}
			}

			if ((group == null) ||
				!_llmManifestConfigurationManager.isEnabled(
					group.getCompanyId(), group.getGroupId())) {

				httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);

				return null;
			}

			String markdown = _llmManifestConfigurationManager.getMarkdown(
				group.getCompanyId(), group.getGroupId());

			if (Validator.isNull(markdown)) {
				httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);

				return null;
			}

			ServletResponseUtil.sendFile(
				httpServletRequest, httpServletResponse, null,
				markdown.getBytes(StringPool.UTF8),
				ContentTypes.TEXT_PLAIN_UTF8);
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn(exception);
			}

			_portal.sendError(
				HttpServletResponse.SC_INTERNAL_SERVER_ERROR, exception,
				httpServletRequest, httpServletResponse);
		}

		return null;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LLMManifestStrutsAction.class);

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private LayoutSetLocalService _layoutSetLocalService;

	@Reference
	private LLMManifestConfigurationManager _llmManifestConfigurationManager;

	@Reference
	private Portal _portal;

	@Reference
	private VirtualHostLocalService _virtualHostLocalService;

}