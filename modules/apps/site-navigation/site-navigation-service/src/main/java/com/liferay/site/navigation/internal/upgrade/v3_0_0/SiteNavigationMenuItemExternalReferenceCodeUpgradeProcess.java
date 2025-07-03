/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.navigation.internal.upgrade.v3_0_0;

import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.model.ExternalReferenceCodeModel;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.UnicodePropertiesBuilder;
import com.liferay.site.navigation.menu.item.util.SiteNavigationMenuItemUtil;
import com.liferay.site.navigation.type.SiteNavigationMenuItemType;
import com.liferay.site.navigation.type.SiteNavigationMenuItemTypeRegistry;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Joao Victor Alves
 */
public class SiteNavigationMenuItemExternalReferenceCodeUpgradeProcess
	extends UpgradeProcess {

	public SiteNavigationMenuItemExternalReferenceCodeUpgradeProcess(
		SiteNavigationMenuItemTypeRegistry siteNavigationMenuItemTypeRegistry) {

		_siteNavigationMenuItemTypeRegistry =
			siteNavigationMenuItemTypeRegistry;
	}

	@Override
	protected void doUpgrade() throws Exception {
		try (PreparedStatement preparedStatement1 = connection.prepareStatement(
				"select ctCollectionId, siteNavigationMenuItemId, type_, " +
					"typeSettings from SiteNavigationMenuItem");
			PreparedStatement preparedStatement2 =
				AutoBatchPreparedStatementUtil.concurrentAutoBatch(
					connection,
					"update SiteNavigationMenuItem set typeSettings = ? " +
						"where ctCollectionId = ? and " +
							"siteNavigationMenuItemId = ?");

			ResultSet resultSet = preparedStatement1.executeQuery()) {

			while (resultSet.next()) {
				String navigationMenuItemType = resultSet.getString("type_");

				if (!SiteNavigationMenuItemUtil.isExternalReferenceCodeType(
						navigationMenuItemType)) {

					continue;
				}

				UnicodeProperties typeSettingsUnicodeProperties =
					UnicodePropertiesBuilder.fastLoad(
						resultSet.getString("typeSettings")
					).build();

				SiteNavigationMenuItemType siteNavigationMenuItemType =
					_siteNavigationMenuItemTypeRegistry.
						getSiteNavigationMenuItemType(navigationMenuItemType);

				PersistedModel model = siteNavigationMenuItemType.getModel(
					typeSettingsUnicodeProperties);

				if (model instanceof ExternalReferenceCodeModel) {
					ExternalReferenceCodeModel externalReferenceCodeModel =
						(ExternalReferenceCodeModel)model;

					typeSettingsUnicodeProperties.setProperty(
						"externalReferenceCode",
						externalReferenceCodeModel.getExternalReferenceCode());
				}

				preparedStatement2.setString(
					1, typeSettingsUnicodeProperties.toString());

				preparedStatement2.setLong(
					2, resultSet.getLong("ctCollectionId"));

				preparedStatement2.setLong(
					3, resultSet.getLong("siteNavigationMenuItemId"));

				preparedStatement2.addBatch();
			}

			preparedStatement2.executeBatch();
		}
	}

	private final SiteNavigationMenuItemTypeRegistry
		_siteNavigationMenuItemTypeRegistry;

}