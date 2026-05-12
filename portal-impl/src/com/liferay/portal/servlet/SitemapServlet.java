/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.servlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author Jorge Ferrer
 */
public class SitemapServlet extends HttpServlet {

	@Override
	public void service(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException, ServletException {

		try {
			String redirect = Portal.PATH_MAIN + "/portal/sitemap";

			String assetTypeSlug = _getAssetTypeSlug(
				httpServletRequest.getRequestURI());

			if (Validator.isNotNull(assetTypeSlug)) {
				String queryString = httpServletRequest.getQueryString();

				redirect = redirect + "?assetTypeSlug=" + assetTypeSlug;

				if (Validator.isNotNull(queryString)) {
					redirect = redirect + "&" + queryString;
				}
			}

			ServletContext servletContext = getServletContext();

			RequestDispatcher requestDispatcher =
				servletContext.getRequestDispatcher(redirect);

			requestDispatcher.forward(httpServletRequest, httpServletResponse);
		}
		catch (Exception exception) {
			_log.error(exception);

			PortalUtil.sendError(
				HttpServletResponse.SC_INTERNAL_SERVER_ERROR, exception,
				httpServletRequest, httpServletResponse);
		}
	}

	private String _getAssetTypeSlug(String requestURI) {
		if (requestURI == null) {
			return null;
		}

		int slashIndex = requestURI.lastIndexOf('/');

		String fileName = requestURI.substring(slashIndex + 1);

		if (!fileName.startsWith("sitemap-") || !fileName.endsWith(".xml")) {
			return null;
		}

		String assetTypeSlug = fileName.substring(
			"sitemap-".length(), fileName.length() - ".xml".length());

		if (assetTypeSlug.isEmpty()) {
			return null;
		}

		for (int i = 0; i < assetTypeSlug.length(); i++) {
			char c = assetTypeSlug.charAt(i);

			if (!Character.isLetterOrDigit(c) && (c != '-')) {
				return null;
			}
		}

		return assetTypeSlug;
	}

	private static final Log _log = LogFactoryUtil.getLog(SitemapServlet.class);

}