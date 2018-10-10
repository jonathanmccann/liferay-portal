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

package com.liferay.apio.architect.sample.internal.identifier;

import com.liferay.apio.architect.functional.Try;
import com.liferay.apio.architect.uri.Path;
import com.liferay.apio.architect.uri.mapper.PathIdentifierMapper;

import javax.ws.rs.BadRequestException;

import org.osgi.service.component.annotations.Component;

/**
 * @author Javier Gamarra
 */
@Component(immediate = true, service = PathIdentifierMapper.class)
public class ModelNameModelIdIdentifierMapper
	implements PathIdentifierMapper<ModelNameModelIdIdentifier> {

	@Override
	public ModelNameModelIdIdentifier map(Path path) {
		String id = path.getId();

		String[] components = id.split(":");

		if (components.length != 2) {
			throw new BadRequestException(
				id + " should be a string with the form \"modelName:modelId\"");
		}

		String modelName = components[0];
		long modelId = _getAsLong(components[1]);

		return ModelNameModelIdIdentifier.create(modelName, modelId);
	}

	@Override
	public Path map(
		String name, ModelNameModelIdIdentifier modelNameModelIdIdentifier) {

		String modelName = modelNameModelIdIdentifier.getModelName();

		String id = modelName + ":" + modelNameModelIdIdentifier.getModelId();

		return new Path(name, id);
	}

	private long _getAsLong(String string) {
		return Try.fromFallible(
			() -> Long.valueOf(string)
		).orElseThrow(
			() -> new BadRequestException(
				"Unable to convert " + string + " to a long")
		);
	}

}