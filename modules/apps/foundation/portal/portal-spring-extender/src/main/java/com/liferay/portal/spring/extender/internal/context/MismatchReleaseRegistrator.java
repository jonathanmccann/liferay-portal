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

package com.liferay.portal.spring.extender.internal.context;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Release;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * @author Fortunato Maldonado
 */
public class MismatchReleaseRegistrator {

	public MismatchReleaseRegistrator(Bundle bundle) {
		_bundle = bundle;
	}

	public void added() throws Exception {
		_check();
	}

	public void removed() throws Exception {
		_check();
	}

	public void start() throws Exception {
		_check();
	}

	private void _check() throws Exception {
		Dictionary<String, String> headers = _bundle.getHeaders();

		String requireSchemaVersion = headers.get(
			"Liferay-Require-SchemaVersion");

		if (Validator.isNull(requireSchemaVersion)) {
			return;
		}

		BundleContext bundleContext = _bundle.getBundleContext();

		String bundleSymbolicName = _bundle.getSymbolicName();

		String filterString = StringBundler.concat(
			"(release.bundle.symbolic.name=", bundleSymbolicName, ")");

		ServiceReference[] serviceReferences =
			bundleContext.getServiceReferences(
				Release.class.getName(), filterString);

		List<String> publishSchemaVersions = new ArrayList<>();

		if (serviceReferences != null) {
			for (ServiceReference serviceReference : serviceReferences) {
				String publishSchemaVersion =
					(String)serviceReference.getProperty(
						"release.schema.version");

				if (publishSchemaVersion.equals(requireSchemaVersion)) {
					return;
				}

				publishSchemaVersions.add(publishSchemaVersion);
			}
		}

		if (!publishSchemaVersions.isEmpty()) {
			_log.error(
				StringBundler.concat(
					bundleSymbolicName, " requires schema version '",
					requireSchemaVersion,
					"', but the current available version is '",
					StringUtil.merge(publishSchemaVersions),
					"'. Waiting for an up-to-date version of ",
					bundleSymbolicName));
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		MismatchReleaseRegistrator.class);

	private final Bundle _bundle;

}