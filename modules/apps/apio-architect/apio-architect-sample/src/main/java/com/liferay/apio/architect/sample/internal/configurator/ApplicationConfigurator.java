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

package com.liferay.apio.architect.sample.internal.configurator;

import java.io.IOException;

import java.util.Dictionary;
import java.util.Hashtable;

import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * This configurator updates the {@code ApioApplication} properties so its
 * served on the base path, instead of the default {@code "api"}.
 *
 * @author Alejandro Hernández
 * @review
 */
@Component(immediate = true, service = {})
public class ApplicationConfigurator {

	@Activate
	public void activate() throws IOException {
		_configuration = _configurationAdmin.getConfiguration(
			"com.liferay.apio.architect.internal.application.ApioApplication",
			"?");

		Dictionary<String, Object> properties = new Hashtable<>();

		properties.put("osgi.jaxrs.application.base", "/");

		_configuration.update(properties);
	}

	@Deactivate
	public void deactivate() throws IOException {
		if (_configuration != null) {
			_configuration.delete();
		}
	}

	private Configuration _configuration;

	@Reference
	private ConfigurationAdmin _configurationAdmin;

}