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
import static com.liferay.apio.architect.internal.unsafe.Unsafe.unsafeCast;

import static org.apache.commons.lang3.reflect.MethodUtils.getMethodsListWithAnnotation;

import com.liferay.apio.architect.annotation.Id;
import com.liferay.apio.architect.annotation.Vocabulary;
import com.liferay.apio.architect.annotation.Vocabulary.BidirectionalModel;
import com.liferay.apio.architect.annotation.Vocabulary.Field;
import com.liferay.apio.architect.annotation.Vocabulary.Type;
import com.liferay.apio.architect.functional.Try;
import com.liferay.apio.architect.identifier.Identifier;
import com.liferay.apio.architect.internal.representor.RepresentorImpl;
import com.liferay.apio.architect.related.RelatedCollection;
import com.liferay.apio.architect.representor.Representor;
import com.liferay.apio.architect.representor.Representor.FirstStep;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Provides utility function to transform a class annotated with {@link Type}
 * into a representor
 *
 * @author Alejandro Hernandez
 * @author Víctor Galán
 * @review
 */
public class RepresentorTransformer {

	/**
	 * Transform a class annotated with {@link Type} into a representor
	 *
	 * @param  typeClass the class annotated with {@link Type}
	 * @param  nameFunction the function that gets a class's {@link
	 *         com.liferay.apio.architect.resource.CollectionResource} name
	 * @param  relatedCollections list of the related collections of all
	 *         representors
	 * @return the instance of representor created
	 * @review
	 */
	public static <T extends Identifier<S>, S> Representor<T> toRepresentor(
		Class<T> typeClass,
		Function<Class<? extends Identifier<?>>, String> nameFunction,
		Map<String, List<RelatedCollection<?, ?>>> relatedCollections) {

		Type type = typeClass.getAnnotation(Type.class);

		Representor.Builder<T, S> builder = _createBuilder(
			typeClass, nameFunction, unsafeCast(relatedCollections));

		FirstStep<T> firstStep = builder.types(
			type.value()
		).identifier(
			s -> Try.fromFallible(
				() -> getMethodsListWithAnnotation(typeClass, Id.class)
			).filter(
				methods -> !methods.isEmpty()
			).map(
				methods -> methods.get(0)
			).map(
				identifierMethod -> (S)identifierMethod.invoke(s)
			).orElse(
				null
			)
		);

		List<Method> methods = getMethodsListWithAnnotation(
			typeClass, Field.class);

		methods.forEach(method -> _processMethod(firstStep, method));

		return firstStep.build();
	}

	private static <T extends Identifier<S>, S> Representor.Builder<T, S>
		_createBuilder(
			Class<T> typeClass,
			Function<Class<? extends Identifier<?>>, String> nameFunction,
			Map<String, List<RelatedCollection<T, ?>>> relatedCollections) {

		Supplier<List<RelatedCollection<T, ?>>> relatedCollectionsSupplier =
			() -> relatedCollections.get(typeClass.getName());

		BiConsumer<Class<?>, RelatedCollection<T, ?>> biConsumer =
			(identifierClass, relatedCollection) -> {
				List<RelatedCollection<T, ?>> list =
					relatedCollections.computeIfAbsent(
						identifierClass.getName(), __ -> new ArrayList<>());

				list.add(relatedCollection);
			};

		return new RepresentorImpl.BuilderImpl<>(
			typeClass, nameFunction, biConsumer, relatedCollectionsSupplier);
	}

	private static <T extends Identifier<S>, S> void _processMethod(
		FirstStep<T> firstStep, Method method) {

		Class<?> returnType = method.getReturnType();

		Field field = method.getAnnotation(Field.class);

		String key = field.value();

		Vocabulary.RelatedCollection relatedCollection = method.getAnnotation(
			Vocabulary.RelatedCollection.class);

		BidirectionalModel bidirectionalModel = method.getAnnotation(
			BidirectionalModel.class);

		if (relatedCollection != null) {
			firstStep.addRelatedCollection(key, relatedCollection.value());
		}
		else if (bidirectionalModel != null) {
			Field bidirectionalField = bidirectionalModel.field();

			firstStep.addBidirectionalModel(
				key, bidirectionalField.value(),
				unsafeCast(bidirectionalModel.modelClass()),
				getMethodFunction(method));
		}
		else {
			addCommonFields(firstStep, method, returnType, key);
		}
	}

}