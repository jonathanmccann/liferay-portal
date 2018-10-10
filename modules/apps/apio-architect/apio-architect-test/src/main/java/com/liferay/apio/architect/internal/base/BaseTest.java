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

package com.liferay.apio.architect.internal.base;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Predicates.instanceOf;
import static io.vavr.Predicates.is;

import io.vavr.collection.List;
import io.vavr.collection.Traversable;
import io.vavr.control.Try;

import java.net.URI;

import java.util.function.Function;

import javax.ws.rs.client.ClientBuilder;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceObjects;
import org.osgi.framework.ServiceReference;

/**
 * Base class for integration tests. Provides utility methods for basic
 * whiteboard/Apio operations/checks.
 *
 * @author Alejandro Hernández
 * @review
 */
public class BaseTest {

	/**
	 * Returns a new {@code ClientBuilder} instance using the bundle context to
	 * retrieve the registered client builder implementation.
	 *
	 * @review
	 */
	protected static ClientBuilder getClientBuilder() {
		return _clientBuilders.getService();
	}

	protected static final URI WHITEBOARD_URI;

	@SuppressWarnings({"Convert2MethodRef", "unchecked"})
	private static final Function<Object, List<String>> _TO_LIST = v -> Match(
		v
	).of(
		Case($(is(null)), List.empty()),
		Case($(instanceOf(String[].class)), array -> List.of(array)),
		Case($(instanceOf(Iterable.class)), iterable -> List.ofAll(iterable)),
		Case($(), value -> List.of(value.toString()))
	);

	private static final BundleContext _bundleContext;
	private static final ServiceObjects<ClientBuilder> _clientBuilders;

	static {
		Bundle bundle = FrameworkUtil.getBundle(BaseTest.class);

		_bundleContext = bundle.getBundleContext();

		ServiceReference<ClientBuilder> clientBuilderServiceReference =
			_bundleContext.getServiceReference(ClientBuilder.class);

		_clientBuilders = _bundleContext.getServiceObjects(
			clientBuilderServiceReference);

		WHITEBOARD_URI = Try.of(
			() -> _bundleContext.getServiceReference(
				"org.osgi.service.jaxrs.runtime.JaxrsServiceRuntime")
		).map(
			serviceReference -> serviceReference.getProperty(
				"osgi.jaxrs.endpoint")
		).map(
			_TO_LIST
		).filter(
			endpoints -> endpoints.size() != 0,
			() -> new IllegalStateException(
				"Unable to find endpoints in \"osgi.jaxrs.endpoint\" property")
		).map(
			Traversable::head
		).mapTry(
			URI::new
		).get();
	}

}