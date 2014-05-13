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

package com.liferay.portal.verify;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portlet.mobiledevicerules.model.MDRRuleGroup;
import com.liferay.portlet.mobiledevicerules.service.MDRRuleGroupLocalServiceUtil;

import java.util.List;

/**
 * @author Jonathan McCann
 */
public class VerifyMDR extends VerifyProcess {

	@Override
	protected void doVerify() throws Exception {
		updateMDRXml();
	}

	protected void updateMDRXml() throws SystemException {
		List<MDRRuleGroup> MDRRuleGroups =
			MDRRuleGroupLocalServiceUtil.getMDRRuleGroups(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (MDRRuleGroup MDRRuleGroup : MDRRuleGroups) {
			String name = MDRRuleGroup.getName();

			if (!name.equals(HtmlUtil.escapeXml(name))) {
				MDRRuleGroupLocalServiceUtil.updateMDRRuleGroup(MDRRuleGroup);
			}
		}
	}

}