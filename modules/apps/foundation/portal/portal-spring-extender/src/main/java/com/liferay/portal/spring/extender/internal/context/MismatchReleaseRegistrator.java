package com.liferay.portal.spring.extender.internal.context;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Release;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

public class MismatchReleaseRegistrator {

	public MismatchReleaseRegistrator(Bundle bundle) {
		_bundle = bundle;
	}

	public void start() throws Exception {
		check();
	}

	public void added() throws Exception {
		check();
	}

	public void removed() throws Exception {
		check();
	}

	private void check() throws Exception {
		Dictionary<String, String> headers = _bundle.getHeaders();

		String requireSchemaVersion = headers.get(
			"Liferay-Require-SchemaVersion");

		if (Validator.isNull(requireSchemaVersion)) {
			return;
		}

		BundleContext bundleContext = _bundle.getBundleContext();

		String bundleSymbolicName = _bundle.getSymbolicName();

		String filterString = StringBundler.concat(
			"(release.bundle.symbolic.name=",
			bundleSymbolicName, ")");

		ServiceReference[] serviceReferences =
			bundleContext.getServiceReferences(
				Release.class.getName(), filterString);

		List<String> publishSchemaVersions = new ArrayList<>();

		if (serviceReferences != null) {
			for (ServiceReference serviceReference : serviceReferences) {
				String publishSchemaVersion =
					(String) serviceReference.getProperty(
						"release.schema.version");

				if (publishSchemaVersion.equals(requireSchemaVersion)) {
					return;
				}

				publishSchemaVersions.add(publishSchemaVersion);
			}
		}

		if (!publishSchemaVersions.isEmpty()) {
			_log.error(StringBundler.concat(
				bundleSymbolicName, " requires schema version '",
				requireSchemaVersion, "', but the current available version is '",
				StringUtil.merge(publishSchemaVersions),
				"'. Waiting for an up-to-date version of ", bundleSymbolicName));
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		MismatchReleaseRegistrator.class);

	private final Bundle _bundle;

}
