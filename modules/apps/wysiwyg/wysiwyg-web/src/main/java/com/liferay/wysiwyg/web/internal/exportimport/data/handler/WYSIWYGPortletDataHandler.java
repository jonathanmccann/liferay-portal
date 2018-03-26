/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.wysiwyg.web.internal.exportimport.data.handler;

import com.liferay.exportimport.kernel.lar.BasePortletDataHandler;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.PortletDataException;
import com.liferay.exportimport.kernel.lar.PortletDataHandler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.wysiwyg.web.internal.constants.WYSIWYGPortletKeys;

import javax.portlet.PortletPreferences;

import org.osgi.service.component.annotations.Component;

/**
 * @author Lianne Louie
 */
@Component(
	immediate = true,
	property = {"javax.portlet.name=" + WYSIWYGPortletKeys.WYSIWYG},
	service = PortletDataHandler.class
)
public class WYSIWYGPortletDataHandler extends BasePortletDataHandler {

	@Override
	public PortletPreferences processExportPortletPreferences(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws PortletDataException {

		try {
			return doProcessExportPortletPreferences(
				portletDataContext, portletId, portletPreferences);
		}
		catch (PortletDataException pde) {
			throw pde;
		}
		catch (Exception e) {
			throw new PortletDataException(e);
		}
	}

	@Override
	public PortletPreferences processImportPortletPreferences(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws PortletDataException {

		try {
			return doProcessImportPortletPreferences(
				portletDataContext, portletId, portletPreferences);
		}
		catch (PortletDataException pde) {
			throw pde;
		}
		catch (Exception e) {
			throw new PortletDataException(e);
		}
	}

	protected PortletPreferences doProcessExportPortletPreferences(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		String message = portletPreferences.getValue(
			"message", StringPool.BLANK);

		portletPreferences.setValue(
			"message",
			StringUtil.replace(
				message, "/documents/" + portletDataContext.getGroupId(),
				"/documents/[$groupId$]"));

		return portletPreferences;
	}

	protected PortletPreferences doProcessImportPortletPreferences(
			PortletDataContext portletDataContext, String portletId,
			PortletPreferences portletPreferences)
		throws Exception {

		String message = portletPreferences.getValue(
			"message", StringPool.BLANK);

		portletPreferences.setValue(
			"message",
			StringUtil.replace(
				message, "/documents/[$groupId$]",
				"/documents/" + portletDataContext.getGroupId()));

		return portletPreferences;
	}

}