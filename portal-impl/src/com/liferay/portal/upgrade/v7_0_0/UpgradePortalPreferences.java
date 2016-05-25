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

package com.liferay.portal.upgrade.v7_0_0;

import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.util.xml.XMLUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Joshua Gok
 */
public class UpgradePortalPreferences extends UpgradeProcess {

	protected void convertCalendarPreferenceNames(
		Element preferenceElement, String preferenceName) {

		String newPreferenceName = _newCalendarPreferences.get(preferenceName);

		if (newPreferenceName != null) {
			Element preferenceNameElement = preferenceElement.element("name");

			preferenceNameElement.setText(newPreferenceName);
		}
		else {
			Matcher colorMatcher = _oldCalendarPreferenceColorPattern.matcher(
				preferenceName);
			Matcher visibleMatcher =
				_oldCalendarPreferenceVisiblePattern.matcher(preferenceName);

			if (colorMatcher.matches() || visibleMatcher.matches()) {
				Matcher idMatcher = _idPattern.matcher(preferenceName);
				Matcher preferenceMatcher = _preferencePattern.matcher(
					preferenceName);

				idMatcher.find();
				preferenceMatcher.find();

				String id = idMatcher.group(0);
				String preference = preferenceMatcher.group(0);

				newPreferenceName = StringUtil.replace(
					_newCalendarPreferencesWithId.get(preference),
					"{calendarId}", id);

				Element preferenceNameElement = preferenceElement.element(
					"name");

				preferenceNameElement.setText(newPreferenceName);
			}
		}
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgradePortalPreferences();
	}

	protected String updatePreferences(String preferences) throws Exception {
		Document newDocument = SAXReaderUtil.createDocument();

		Element newRootElement = SAXReaderUtil.createElement(
			"portlet-preferences");

		newDocument.add(newRootElement);

		Document document = SAXReaderUtil.read(preferences);

		Element rootElement = document.getRootElement();

		Iterator<Element> iterator = rootElement.elementIterator();

		while (iterator.hasNext()) {
			Element preferenceElement = iterator.next();

			String preferenceName = preferenceElement.elementText("name");

			if (!preferenceName.contains(
					"com.liferay.portlet.kernel.staging.Staging")) {

				convertCalendarPreferenceNames(
					preferenceElement, preferenceName);

				newRootElement.add(preferenceElement.createCopy());
			}
		}

		return XMLUtil.formatXML(newDocument);
	}

	protected void upgradePortalPreferences() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer();
			PreparedStatement ps1 = connection.prepareStatement(
				"select portalPreferencesId, preferences from " +
					"PortalPreferences");
			ResultSet rs = ps1.executeQuery();
			PreparedStatement ps2 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection,
					"update PortalPreferences set preferences = ? " +
						"where portalPreferencesId = ?")) {

			while (rs.next()) {
				long portalPreferencesId = rs.getLong("portalPreferencesId");

				String preferences = rs.getString("preferences");

				preferences = updatePreferences(preferences);

				ps2.setString(1, preferences);
				ps2.setLong(2, portalPreferencesId);

				ps2.addBatch();
			}

			ps2.executeBatch();
		}
	}

	private static final String _SESSION_CLICKS =
		"com.liferay.portal.util.SessionClicks#";

	private static final Pattern _idPattern = Pattern.compile("[0-9]+");
	private static final Map<String, String> _newCalendarPreferences =
		new HashMap<>();
	private static final Map<String, String> _newCalendarPreferencesWithId =
		new HashMap<>();
	private static final Pattern _oldCalendarPreferenceColorPattern =
		Pattern.compile(
			_SESSION_CLICKS + "calendar-portlet-calendar-[0-9]+-color");
	private static final Pattern _oldCalendarPreferenceVisiblePattern =
		Pattern.compile(
			_SESSION_CLICKS + "calendar-portlet-calendar-[0-9]+-visible");
	static {
		_newCalendarPreferences.put(
			_SESSION_CLICKS + "calendar-portlet-default-view",
			_SESSION_CLICKS + "com.liferay.calendar.web_defaultView");
		_newCalendarPreferences.put(
			_SESSION_CLICKS + "calendar-portlet-other-calendars",
			_SESSION_CLICKS + "com.liferay.calendar.web_otherCalendars");
		_newCalendarPreferences.put(
			_SESSION_CLICKS + "calendar-portlet-column-options-visible",
			_SESSION_CLICKS + "com.liferay.calendar.web_columnOptionsVisible");
	}

	private static final Pattern _preferencePattern = Pattern.compile(
		"[a-z]+$");
	static {
		_newCalendarPreferencesWithId.put(
			"color",
			_SESSION_CLICKS +
				"com.liferay.calendar.web_calendar{calendarId}Color");
		_newCalendarPreferencesWithId.put(
			"visible",
			_SESSION_CLICKS +
				"com.liferay.calendar.web_calendar{calendarId}Visible");
	}
}