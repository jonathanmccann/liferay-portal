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

package com.liferay.apio.architect.internal.related;

import com.liferay.apio.architect.identifier.Identifier;
import com.liferay.apio.architect.related.RelatedCollection;

import java.util.function.Function;

/**
 * Represents the relation between a resource and a collection. The type of the
 * resource's identifier must be a subclass of {@code Identifier}.
 *
 * @author Alejandro Hernández
 * @param  <S> the type of the resource's identifier
 */
public class RelatedCollectionImpl<T, S extends Identifier>
	implements RelatedCollection<T, S> {

	public RelatedCollectionImpl(String key, Class<S> identifierClass) {
		this(key, identifierClass, null);
	}

	public RelatedCollectionImpl(
		String key, Class<S> identifierClass,
		Function<T, ?> modelToIdentifierFunction) {

		_key = key;
		_identifierClass = identifierClass;
		_modelToIdentifierFunction = modelToIdentifierFunction;
	}

	@Override
	public Class<S> getIdentifierClass() {
		return _identifierClass;
	}

	@Override
	public String getKey() {
		return _key;
	}

	@Override
	public Function<T, ?> getModelToIdentifierFunction() {
		return _modelToIdentifierFunction;
	}

	private final Class<S> _identifierClass;
	private final String _key;
	private Function<T, ?> _modelToIdentifierFunction;

}