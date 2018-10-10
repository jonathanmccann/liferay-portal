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

import static com.liferay.apio.architect.internal.annotation.representor.RepresentorTransformerUtil.addCommonFields;
import static com.liferay.apio.architect.internal.annotation.representor.RepresentorTransformerUtil.getMethodFunction;

import static org.apache.commons.lang3.reflect.MethodUtils.getMethodsListWithAnnotation;

import com.liferay.apio.architect.annotation.Vocabulary.Field;
import com.liferay.apio.architect.annotation.Vocabulary.RelatedCollection;
import com.liferay.apio.architect.annotation.Vocabulary.Type;
import com.liferay.apio.architect.representor.NestedRepresentor;

import java.lang.reflect.Method;

import java.util.List;

/**
 * Provides utility function to transform a class annotated with {@link Type}
 * into a nested representor
 *
 * @author Víctor Galán
 * @review
 */
public class NestedRepresentorTransformer {

	/**
	 * Transform a class annotated with {@link Type} into a nested representor,
	 * using the builder provided
	 *
	 * @param  typeClass the class annotated with {@link Type}
	 * @param  builder the nested representor builder
	 * @return the instance of representor created
	 * @review
	 */
	public static NestedRepresentor<?> toRepresentor(
		Class<?> typeClass, NestedRepresentor.Builder<?> builder) {

		Type type = typeClass.getAnnotation(Type.class);

		NestedRepresentor.FirstStep<?> firstStep = builder.types(
			type.value()
		);

		List<Method> methods = getMethodsListWithAnnotation(
			typeClass, Field.class);

		methods.forEach(method -> _processMethod(firstStep, method));

		return firstStep.build();
	}

	private static void _processMethod(
		NestedRepresentor.FirstStep<?> firstStep, Method method) {

		Class<?> returnType = method.getReturnType();

		Field field = method.getAnnotation(Field.class);

		String key = field.value();

		RelatedCollection relatedCollection = method.getAnnotation(
			RelatedCollection.class);

		if (relatedCollection != null) {
			firstStep.addRelatedCollection(
				key, relatedCollection.value(), getMethodFunction(method));
		}

		addCommonFields(firstStep, method, returnType, key);
	}

}