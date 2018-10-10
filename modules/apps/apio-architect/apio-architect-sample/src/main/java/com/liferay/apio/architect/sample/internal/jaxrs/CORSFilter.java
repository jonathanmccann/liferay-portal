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

package com.liferay.apio.architect.sample.internal.jaxrs;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;

import org.osgi.service.component.annotations.Component;

/**
 * Enables Cross-Origin Resource Sharing (CORS) in the Apio Architect Sample.
 *
 * @author Alejandro Hernández
 * @see    <a href="https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS">Cross-Origin Resource Sharing (CORS)</a>
 * @review
 */
@Component(
	property = {
		"osgi.jaxrs.application.select=(liferay.apio.architect.application=true)",
		"osgi.jaxrs.extension=true"
	},
	service = ContainerResponseFilter.class
)
public class CORSFilter implements ContainerResponseFilter {

	@Override
	public void filter(
		ContainerRequestContext requestContext,
		ContainerResponseContext responseContext) {

		MultivaluedMap<String, Object> headers = responseContext.getHeaders();

		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Credentials", "true");
		headers.add(
			"Access-Control-Allow-Headers",
			"origin, content-type, accept, authorization");
		headers.add(
			"Access-Control-Allow-Methods",
			"GET, POST, PUT, DELETE, OPTIONS, HEAD");
	}

}