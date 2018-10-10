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

import com.liferay.apio.architect.annotation.Vocabulary.Type;
import com.liferay.apio.architect.functional.Try;
import com.liferay.apio.architect.identifier.Identifier;
import com.liferay.apio.architect.internal.unsafe.Unsafe;
import com.liferay.apio.architect.internal.wiring.osgi.util.GenericUtil;
import com.liferay.apio.architect.router.ActionRouter;

/**
 * Provides functions to extract the type class from an {@link ActionRouter}
 *
 * @author Alejandro Hernandez
 * @author Víctor Galán
 * @review
 */
public class ActionRouterTypeExtractor {

	/**
	 * Extract the type class from an {@link ActionRouter}
	 *
	 * @param  actionRouter the action router
	 * @return the type class
	 * @review
	 */
	public static Try<Class<? extends Identifier>> extractTypeClass(
		ActionRouter<?> actionRouter) {

		Try<Class<Object>> classTry = GenericUtil.getGenericTypeArgumentTry(
			actionRouter.getClass(), ActionRouter.class, 0);

		return classTry.filter(
			type -> type.isAnnotationPresent(Type.class)
		).map(
			Unsafe::unsafeCast
		);
	}

}