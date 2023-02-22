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

package com.liferay.oauth2.provider.internal.upgrade.v3_0_1;

import com.liferay.oauth2.provider.configuration.OAuth2ProviderConfiguration;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.Time;

import java.sql.Date;
import java.sql.PreparedStatement;

/**
 * @author Jonathan McCann
 */
public class OAuth2AuthorizationUpgradeProcess extends UpgradeProcess {

	public OAuth2AuthorizationUpgradeProcess(
		ConfigurationProvider configurationProvider) {

		_configurationProvider = configurationProvider;
	}

	@Override
	protected void doUpgrade() throws Exception {
		OAuth2ProviderConfiguration oAuth2ProviderConfiguration =
			_configurationProvider.getSystemConfiguration(
				OAuth2ProviderConfiguration.class);

		int expiredAuthorizationsAfterlifeDuration = Math.max(
			oAuth2ProviderConfiguration.
				expiredAuthorizationsAfterlifeDuration(),
			0);

		long expiredAuthorizationsAfterlifeDurationMillis =
			expiredAuthorizationsAfterlifeDuration * Time.SECOND;

		Date purgeDate = new Date(System.currentTimeMillis());

		purgeDate.setTime(
			purgeDate.getTime() - expiredAuthorizationsAfterlifeDurationMillis);

		String sql = StringBundler.concat(
			"delete OAuth2Authorization, OA2Auths_OA2ScopeGrants from ",
			"OAuth2Authorization INNER JOIN OA2Auths_OA2ScopeGrants where ",
			"(OAuth2Authorization.accessTokenExpirationDate < ?) and ",
			"(((OAuth2Authorization.refreshTokenExpirationDate is not null) ",
			"and (OAuth2Authorization.refreshTokenExpirationDate < ?)) or ",
			"(OAuth2Authorization.refreshTokenExpirationDate is null)) and ",
			"OAuth2Authorization.oAuth2AuthorizationId = ",
			"OA2Auths_OA2ScopeGrants.oAuth2AuthorizationId");

		try (PreparedStatement preparedStatement = connection.prepareStatement(
				sql)) {

			preparedStatement.setDate(1, purgeDate);
			preparedStatement.setDate(2, purgeDate);

			preparedStatement.executeUpdate();
		}
	}

	private final ConfigurationProvider _configurationProvider;

}