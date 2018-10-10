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

package com.liferay.apio.architect.internal.representor;

import com.liferay.apio.architect.identifier.Identifier;
import com.liferay.apio.architect.internal.related.RelatedCollectionImpl;
import com.liferay.apio.architect.related.RelatedCollection;
import com.liferay.apio.architect.representor.NestedRepresentor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @author Alejandro Hernández
 */
public class NestedRepresentorImpl<T>
	extends BaseRepresentorImpl<T> implements NestedRepresentor<T> {

	@Override
	public Stream<RelatedCollection<T, ? extends Identifier>>
		getRelatedCollections() {

		return Stream.of(
			_relatedCollections, supplier.get()
		).filter(
			Objects::nonNull
		).flatMap(
			Collection::stream
		);
	}

	@Override
	public boolean isNested() {
		return true;
	}

	/**
	 * Creates generic representations of your domain models that Apio
	 * hypermedia writers can understand.
	 *
	 * @param <T> the model's type
	 */
	public static class BuilderImpl<T>
		extends BaseBuilderImpl<T, NestedRepresentorImpl<T>>
		implements Builder<T> {

		public BuilderImpl(
			Function<Class<? extends Identifier<?>>, String> nameFunction,
			Supplier<List<RelatedCollection<T, ?>>> supplier) {

			super(new NestedRepresentorImpl<>(nameFunction, supplier));
		}

		@Override
		public FirstStep<T> types(String type, String... types) {
			baseRepresentor.addTypes(type, types);

			return new FirstStepImpl();
		}

		public class FirstStepImpl
			extends BaseFirstStepImpl<NestedRepresentor<T>, FirstStep<T>>
			implements FirstStep<T> {

			@Override
			public <V, S extends Identifier<?>> NestedRepresentor.FirstStep<T>
				addRelatedCollection(
					String key, Class<S> itemIdentifierClass,
					Function<T, V> modelToIdentifierFunction) {

				baseRepresentor._addRelatedCollection(
					key, itemIdentifierClass, modelToIdentifierFunction);

				return this;
			}

			@Override
			public FirstStepImpl getThis() {
				return this;
			}

		}

	}

	private NestedRepresentorImpl(
		Function<Class<? extends Identifier<?>>, String> nameFunction,
		Supplier<List<RelatedCollection<T, ?>>> supplier) {

		super(nameFunction, supplier);

		_relatedCollections = new ArrayList<>();
	}

	private <S extends Identifier<?>> void _addRelatedCollection(
		String key, Class<S> itemIdentifierClass,
		Function<T, ?> modelToIdentifierFunction) {

		RelatedCollection<T, ?> relatedCollection = new RelatedCollectionImpl(
			key, itemIdentifierClass, modelToIdentifierFunction);

		_relatedCollections.add(relatedCollection);
	}

	private final List<RelatedCollection<T, ?>> _relatedCollections;

}