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

import static com.liferay.apio.architect.internal.unsafe.Unsafe.unsafeCast;

import com.liferay.apio.architect.annotation.Vocabulary.LinkedModel;
import com.liferay.apio.architect.annotation.Vocabulary.RelativeURL;
import com.liferay.apio.architect.annotation.Vocabulary.Type;
import com.liferay.apio.architect.functional.Try;
import com.liferay.apio.architect.language.AcceptLanguage;
import com.liferay.apio.architect.representor.BaseRepresentor;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Provides utility functions to fill a representor builder using an instance of
 * an annotated method
 *
 * @author Víctor Galán
 * @review
 */
public class RepresentorTransformerUtil {

	/**
	 * Fills the builder using the method provided
	 *
	 * @param  firstStep the firstStep of the builder
	 * @param  method the method to be parsed, annotated with {@link
	 *         com.liferay.apio.architect.annotation.Vocabulary.Field}
	 * @param  returnType the return type of the method
	 * @param  key the key of the field
	 * @review
	 */
	public static void addCommonFields(
		BaseRepresentor.BaseFirstStep<?, ?, ?> firstStep, Method method,
		Class<?> returnType, String key) {

		LinkedModel linkedModel = method.getAnnotation(LinkedModel.class);

		if (linkedModel != null) {
			firstStep.addLinkedModel(
				key, unsafeCast(linkedModel.value()),
				getMethodFunction(method));
		}
		else if (returnType == String.class) {
			_addStringFields(firstStep, method, key);
		}
		else if (returnType == Date.class) {
			firstStep.addDate(key, getMethodFunction(method));
		}
		else if (returnType == Boolean.class) {
			firstStep.addBoolean(key, getMethodFunction(method));
		}
		else if (Number.class.isAssignableFrom(returnType)) {
			firstStep.addNumber(key, getMethodFunction(method));
		}
		else if (returnType == List.class) {
			_addListFields(firstStep, method, key);
		}
		else {
			firstStep.addNested(
				key, getMethodFunction(method),
				builder -> unsafeCast(
					NestedRepresentorTransformer.toRepresentor(
						returnType, builder)));
		}
	}

	public static <A, T, S> BiFunction<T, A, S> getMethodBiFunction(
		Method method) {

		return (t, a) -> Try.fromFallible(
			() -> (S)method.invoke(t, a)
		).orElse(
			null
		);
	}

	public static <T, S> Function<T, S> getMethodFunction(Method method) {
		return t -> Try.fromFallible(
			() -> (S)method.invoke(t)
		).orElse(
			null
		);
	}

	private static void _addListFields(
		BaseRepresentor.BaseFirstStep<?, ?, ?> firstStep, Method method,
		String key) {

		ParameterizedType parameterizedType =
			(ParameterizedType)method.getGenericReturnType();

		Class<?> listClass =
			(Class<?>)parameterizedType.getActualTypeArguments()[0];

		if (listClass == String.class) {
			firstStep.addStringList(key, getMethodFunction(method));
		}
		else if (listClass == Boolean.class) {
			firstStep.addBooleanList(key, getMethodFunction(method));
		}
		else if (Number.class.isAssignableFrom(listClass)) {
			firstStep.addNumberList(key, getMethodFunction(method));
		}
		else {
			Type field = listClass.getAnnotation(Type.class);

			if (field != null) {
				firstStep.addNestedList(
					key, getMethodFunction(method),
					builder -> unsafeCast(
						NestedRepresentorTransformer.toRepresentor(
							listClass, builder)));
			}
		}
	}

	private static void _addStringFields(
		BaseRepresentor.BaseFirstStep<?, ?, ?> firstStep, Method method,
		String key) {

		Class<?>[] parameters = method.getParameterTypes();

		if (parameters.length > 0) {
			Class<?> firstParameter = parameters[0];

			if (firstParameter == Locale.class) {
				firstStep.addLocalizedStringByLocale(
					key, getMethodBiFunction(method));
			}
			else if (firstParameter == AcceptLanguage.class) {
				firstStep.addLocalizedStringByLanguage(
					key, getMethodBiFunction(method));
			}
		}
		else {
			RelativeURL relativeURL = method.getAnnotation(RelativeURL.class);

			if (relativeURL != null) {
				if (relativeURL.fromApplication()) {
					firstStep.addApplicationRelativeURL(
						key, getMethodFunction(method));
				}
				else {
					firstStep.addRelativeURL(key, getMethodFunction(method));
				}
			}
			else {
				firstStep.addString(key, getMethodFunction(method));
			}
		}
	}

}