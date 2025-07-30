/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.navigation.internal.upgrade.v3_0_0;

import com.liferay.portal.kernel.dao.jdbc.AutoBatchPreparedStatementUtil;
import com.liferay.portal.kernel.model.ClassedModel;
import com.liferay.portal.kernel.model.ExternalReferenceCodeModel;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.UnicodePropertiesBuilder;
import com.liferay.site.navigation.type.SiteNavigationMenuItemType;
import com.liferay.site.navigation.type.SiteNavigationMenuItemTypeRegistry;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.Objects;

/**
 * @author Joao Victor Alves
 */
public class SiteNavigationMenuItemExternalReferenceCodeUpgradeProcess
	extends UpgradeProcess {

	public SiteNavigationMenuItemExternalReferenceCodeUpgradeProcess(
		SiteNavigationMenuItemTypeRegistry siteNavigationMenuItemTypeRegistry
//		, SiteNavigationMenuItemType siteNavigationMenuItemType
	) {

		_siteNavigationMenuItemTypeRegistry =
			siteNavigationMenuItemTypeRegistry;

//		_siteNavigationMenuItemType = siteNavigationMenuItemType;
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

				UnicodeProperties typeSettingsUnicodeProperties =
					UnicodePropertiesBuilder.fastLoad(
						resultSet.getString("typeSettings")
					).build();

				SiteNavigationMenuItemType siteNavigationMenuItemType =
					_siteNavigationMenuItemTypeRegistry.
						getSiteNavigationMenuItemType(navigationMenuItemType);

				ClassedModel classedModel = siteNavigationMenuItemType.getModel(
					typeSettingsUnicodeProperties);

				if (classedModel == null) {
					continue;
				}

				String externalReferenceCode = null;

				if (Objects.equals(
						classedModel.getModelClassName(),
						_CLASS_NAME_CPDEFINITION)) {

					try (PreparedStatement preparedStatement3 =
							connection.prepareStatement(
								"select externalReferenceCode from CProduct" +
									" where publishedCPDefinitionId = ?")) {

						preparedStatement3.setLong(
							1, (Long)classedModel.getPrimaryKeyObj());

						ResultSet resultSet3 =
							preparedStatement3.executeQuery();

						if (resultSet3.next()) {
							externalReferenceCode = resultSet3.getString(
								"externalReferenceCode");
						}
					}
				}

				if (classedModel instanceof ExternalReferenceCodeModel) {
					ExternalReferenceCodeModel externalReferenceCodeModel =
						(ExternalReferenceCodeModel)classedModel;

					externalReferenceCode =
						externalReferenceCodeModel.getExternalReferenceCode();
				}

				typeSettingsUnicodeProperties.setProperty(
					"externalReferenceCode", externalReferenceCode);

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

	private static final String _CLASS_NAME_CPDEFINITION =
		"com.liferay.commerce.product.model.CPDefinition";

	private final SiteNavigationMenuItemTypeRegistry
		_siteNavigationMenuItemTypeRegistry;
//	private final
//	SiteNavigationMenuItemType _siteNavigationMenuItemType;

}