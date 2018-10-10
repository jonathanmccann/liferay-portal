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

package com.liferay.apio.architect.internal.annotation.representor;

/**
 * Provides utility functions for transforming {@code String}.
 *
 * <p>
 * This class shouldn't be instantiated.
 * </p>
 *
 * @author Alejandro Hernández
 * @review
 */
public class StringUtil {

	/**
	 * Transforms a string into its "slug" version. For example: transforms
	 * {@code "BlogPosting"} into {@code "blog-posting"}.
	 *
	 * @review
	 */
	public static String toLowercaseSlug(String string) {
		String sluggedString = String.join("-", string.split("(?=[A-Z])"));

		return sluggedString.toLowerCase();
	}

}