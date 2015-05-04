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

import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.staging.Staging;
import com.liferay.portal.kernel.staging.StagingConstants;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portlet.dynamicdatamapping.util.DDMXMLUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Joshua Gok
 */
public class UpgradePortalPreferences extends UpgradeProcess {

	protected String convertStagingPreferencesToJSON(String preferences)
		throws Exception {

		Document newDocument = SAXReaderUtil.createDocument();

		Element newRootElement = SAXReaderUtil.createElement(
			"portlet-preferences");

		newDocument.add(newRootElement);

		Document document = SAXReaderUtil.read(preferences);

		Element rootElement = document.getRootElement();

		Iterator<Element> itr = rootElement.elementIterator();

		Map<String, String> stagingPreferencesMap = new HashMap<>();

		while (itr.hasNext()) {
			Element preferenceElement = itr.next();

			Element nameElement = preferenceElement.element("name");

			String preferenceName = nameElement.getText();

			if (preferenceName.contains(Staging.class.getName())) {
				Element valueElement = preferenceElement.element("value");

				String value = valueElement.getText();

				int index = preferenceName.indexOf(StringPool.POUND);

				stagingPreferencesMap.put(
					preferenceName.substring(index + 1), value);
			}
			else {
				newRootElement.add(preferenceElement.createCopy());
			}
		}

		JSONArray stagingPreferencesJsonArray =
			JSONFactoryUtil.createJSONArray();

		for (String key : stagingPreferencesMap.keySet()) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put(key, stagingPreferencesMap.get(key));

			stagingPreferencesJsonArray.put(jsonObject);
		}

		if (stagingPreferencesJsonArray.length() > 0) {
			Element stagingPreferencesElement = SAXReaderUtil.createElement(
				"preference");

			Element stagingPreferencesNameElement = SAXReaderUtil.createElement(
				"name");

			String stagingPreferencesName =
				Staging.class.getName() + StringPool.POUND +
				StagingConstants.STAGING_RECENT_LAYOUT_IDS_MAP;

			stagingPreferencesNameElement.setText(stagingPreferencesName);

			Element stagingPreferencesValueElement =
				SAXReaderUtil.createElement("value");

			stagingPreferencesValueElement.setText(
				stagingPreferencesJsonArray.toString());

			stagingPreferencesElement.add(stagingPreferencesNameElement);
			stagingPreferencesElement.add(stagingPreferencesValueElement);

			newRootElement.add(stagingPreferencesElement);
		}

		return DDMXMLUtil.formatXML(newDocument);
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgradePortalPreferences();
	}

	protected void upgradePortalPreferences() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"select portalPreferencesId, preferences from " +
					"PortalPreferences");

			rs = ps.executeQuery();

			while (rs.next()) {
				long portalPreferencesId = rs.getLong("portalPreferencesId");

				String preferences = rs.getString("preferences");

				upgradeUserStagingPreferences(portalPreferencesId, preferences);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	protected void upgradeUserStagingPreferences(
			long portalPreferencesId, String preferences)
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			ps = con.prepareStatement(
				"update PortalPreferences set preferences = ? where " +
					"portalPreferencesId = ?");

			ps.setString(1, convertStagingPreferencesToJSON(preferences));
			ps.setLong(2, portalPreferencesId);
			ps.executeUpdate();
		}
		finally {
			DataAccess.cleanUp(con, ps);
		}
	}

}