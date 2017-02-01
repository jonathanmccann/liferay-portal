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

package com.liferay.document.library.internal.upgrade.v1_0_0;

import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.FullNameGenerator;
import com.liferay.portal.kernel.security.auth.FullNameGeneratorFactory;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LoggingTimer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Michael Bowerman
 */
public class UpgradeDLFolder extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer();
			PreparedStatement ps1 = connection.prepareStatement(
				"select distinct userId from DLFolder where userName IS NULL " +
					"or userName = ''");
			ResultSet rs = ps1.executeQuery();
			PreparedStatement ps2 = AutoBatchPreparedStatementUtil.autoBatch(
				connection.prepareStatement(
					"update DLFolder set userName = ? where userId = ? and " +
						"(userName IS NULL or userName = '')"))) {

			while (rs.next()) {
				long userId = rs.getLong("userId");

				String userName = getUserName(userId);

				if (userName != null) {
					ps2.setString(1, userName);

					ps2.setLong(2, userId);

					ps2.addBatch();
				}
				else {
					if (_log.isInfoEnabled()) {
						_log.info(
							"User " + userId + " does not exist in the " +
								"database. The userNames of DLFolders with " +
									"this userId will not be updated.");
					}
				}
			}

			ps2.executeBatch();
		}
	}

	protected String getUserName(long userId) throws Exception {
		try (PreparedStatement ps = connection.prepareStatement(
				"select firstName, middleName, lastName from User_ where " +
					"userId = ?")) {

			ps.setLong(1, userId);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					String firstName = rs.getString("firstName");
					String middleName = rs.getString("middleName");
					String lastName = rs.getString("lastName");

					FullNameGenerator fullNameGenerator =
						FullNameGeneratorFactory.getInstance();

					return fullNameGenerator.getFullName(
						firstName, middleName, lastName);
				}

				return null;
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UpgradeDLFolder.class);

}