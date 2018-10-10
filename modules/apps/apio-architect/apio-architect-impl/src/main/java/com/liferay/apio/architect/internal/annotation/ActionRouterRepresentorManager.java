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

package com.liferay.apio.architect.internal.annotation;

import static com.liferay.apio.architect.internal.annotation.representor.StringUtil.toLowercaseSlug;
import static com.liferay.apio.architect.internal.wiring.osgi.manager.cache.ManagerCache.INSTANCE;

import static org.osgi.service.component.annotations.ReferenceCardinality.MULTIPLE;
import static org.osgi.service.component.annotations.ReferencePolicyOption.GREEDY;

import static org.slf4j.LoggerFactory.getLogger;

import com.liferay.apio.architect.annotation.Vocabulary.Type;
import com.liferay.apio.architect.identifier.Identifier;
import com.liferay.apio.architect.internal.annotation.representor.ActionRouterTypeExtractor;
import com.liferay.apio.architect.internal.annotation.representor.RepresentorTransformer;
import com.liferay.apio.architect.related.RelatedCollection;
import com.liferay.apio.architect.representor.Representor;
import com.liferay.apio.architect.router.ActionRouter;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

import org.slf4j.Logger;

/**
 * Keep track of all {@link ActionRouter} registered and provides a method to
 * compute the representors from each action router.
 *
 * @author Víctor Galán
 * @review
 */
@Component(service = ActionRouterRepresentorManager.class)
public class ActionRouterRepresentorManager {

	@Activate
	public void activate() {
		INSTANCE.clear();
	}

	/**
	 * Create a representors for each {@link ActionRouter} registered, for this
	 * it uses its type.
	 *
	 * @param  nameFunction the function that gets a class's {@link
	 *         com.liferay.apio.architect.resource.CollectionResource} name
	 * @param  relatedCollections list of the related collections of all
	 *         representors
	 * @review
	 */
	public void computeRepresentors(
		Function<Class<? extends Identifier<?>>, String> nameFunction,
		Map<String, List<RelatedCollection<?, ?>>> relatedCollections) {

		if (_actionRouters == null) {
			return;
		}

		Stream<ActionRouter<?>> actionRouterStream = _actionRouters.stream();

		actionRouterStream.map(
			ActionRouterTypeExtractor::extractTypeClass
		).forEach(
			typeClassTry -> typeClassTry.voidFold(
				__ -> _logger.warn(
					"Unable to extract class from action router"),
				typeClass -> _computeRepresentor(
					typeClass, nameFunction, relatedCollections))
		);
	}

	@Deactivate
	public void deactivate() {
		INSTANCE.clear();
	}

	private <T extends Identifier<S>, S> void _computeRepresentor(
		Class<T> typeClass,
		Function<Class<? extends Identifier<?>>, String> nameFunction,
		Map<String, List<RelatedCollection<?, ?>>> relatedCollections) {

		Representor<T> representor = RepresentorTransformer.toRepresentor(
			typeClass, nameFunction, relatedCollections);

		Type type = typeClass.getAnnotation(Type.class);

		String name = toLowercaseSlug(type.value());

		INSTANCE.putName(typeClass.getName(), name);
		INSTANCE.putIdentifierClass(name, (Class)typeClass);
		INSTANCE.putRepresentor(name, representor);
	}

	@Reference(
		cardinality = MULTIPLE, policyOption = GREEDY,
		service = ActionRouter.class
	)
	private List<ActionRouter<?>> _actionRouters;

	private final Logger _logger = getLogger(getClass());

}