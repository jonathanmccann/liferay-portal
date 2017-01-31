/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.gsportal.staffmember.util;

import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * List of new configuration values which are read from portal-ext.properties,
 * together with their default values (used when key is not found in any
 * portal-ext* that Liferay uses in runtime).
 *
 * @author Josef Sustacek
 * @see com.liferay.gsportal.LgspPropsKeys
 */
public class LgspPropsValues {

	public static final boolean ENABLE_OUTGOING_PORTAL_EMAILS =
		GetterUtil.getBoolean(
			PropsUtil.get(LgspPropsKeys.ENABLE_OUTGOING_PORTAL_EMAILS),
			true);

	public static final String INTERNAL_USERS_DOMAIN_NAME =
		GetterUtil.getString(
				PropsUtil.get(LgspPropsKeys.INTERNAL_USERS_DOMAIN_NAME),
				"@liferay.com");

	public static final String[] GLOBAL_VOCABULARY_KEYS = StringUtil.split(
		GetterUtil.getString(
			PropsUtil.get(LgspPropsKeys.GLOBAL_VOCABULARY_KEYS),
			"engagement-type"), StringPool.COMMA);

	public static final String GS_CLIENTS_SITE_FRIENDLY_URL =
		GetterUtil.getString(
			PropsUtil.get(LgspPropsKeys.GS_CLIENTS_SITE_FRIENDLY_URL),
			"/web/property-not-set-in-portal-ext.properties/"
				+ LgspPropsKeys.GS_CLIENTS_SITE_FRIENDLY_URL);

	public static final String GS_CLIENTS_SITE_NAME = GetterUtil.getString(
		PropsUtil.get(LgspPropsKeys.GS_CLIENTS_SITE_NAME), "GS Clients");

	public static final String LESA_PROJECT_LINK_PATTERN =
		GetterUtil.getString(PropsUtil.get(LgspPropsKeys.LESA_PROJECT_LINK_PATTERN),
			"http://property-not-set-in-portal-ext.properties/"
				+ LgspPropsKeys.LESA_PROJECT_LINK_PATTERN);

	public static final String ENGAGEMENTS_CALENDAR_NAME =
		GetterUtil.getString(PropsUtil.get(LgspPropsKeys.ENGAGEMENTS_CALENDAR_NAME),
			"Engagements");

	public static final String ENGAGEMENTS_TYPE_ASSET_VOCABULARY_NAME =
		GetterUtil.getString(PropsUtil.get(
				LgspPropsKeys.ENGAGEMENTS_TYPE_ASSET_VOCABULARY_NAME),
				"GS Portal - Engagement Type");

	public static final String[] ENGAGEMENT_ROLE_SUGGESTIONS =
		StringUtil.split(GetterUtil.getString(
			PropsUtil.get(LgspPropsKeys.ENGAGEMENTS_ROLE_SUGGESTIONS),
			"Consultant"), StringPool.COMMA);

	public static final String ENGAGEMENTS_FRIENDLY_URL = GetterUtil.getString(
		PropsUtil.get(LgspPropsKeys.ENGAGEMENTS_FRIENDLY_URL),
		"http://property-not-set-in-portal-ext.properties/"
				+ LgspPropsKeys.ENGAGEMENTS_FRIENDLY_URL);

	public static final String REPORT_BUG_URL = GetterUtil.getString(
		PropsUtil.get(LgspPropsKeys.REPORT_BUG_URL),
		"https://issues.liferay.com/browse/LGSP");

	public static final String SYSTEM_ADMINISTRATOR_ROLE_NAME =
		GetterUtil.getString(
			PropsUtil.get(LgspPropsKeys.SYSTEM_ADMINISTRATOR_ROLE_NAME),
			"property-not-set-in-portal-ext.properties");

	public static final String TECHNICAL_ATTRIBUTES_ASSET_VOCABULARY_NAME = GetterUtil
			.getString(PropsUtil.get(LgspPropsKeys.GLOBAL_VOCABULARY_TITLE,
					new Filter("project-technical-attributes")),
					"GS Portal - Project Technical Attributes");
}