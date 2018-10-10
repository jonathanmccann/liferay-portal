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

package com.liferay.apio.architect.sample.internal.resource;

import com.liferay.apio.architect.identifier.Identifier;
import com.liferay.apio.architect.representor.Representor;
import com.liferay.apio.architect.resource.NestedCollectionResource;
import com.liferay.apio.architect.routes.ItemRoutes;
import com.liferay.apio.architect.routes.NestedCollectionRoutes;
import com.liferay.apio.architect.sample.internal.resource.PersonCollectionResource.PersonIdentifier;
import com.liferay.apio.architect.sample.internal.router.ContactPointActionRouter;
import com.liferay.apio.architect.sample.internal.type.ContactPoint;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Víctor Galán
 */
@Component(service = NestedCollectionResource.class)
public class ContactPointNestedCollectionResource
	implements NestedCollectionResource
		<ContactPoint, Long,
		 ContactPointNestedCollectionResource.ContactPointIdentifier, Long,
		 PersonIdentifier> {

	@Override
	public NestedCollectionRoutes<ContactPoint, Long, Long> collectionRoutes(
		NestedCollectionRoutes.Builder<ContactPoint, Long, Long> builder) {

		return builder.addGetter(
			(pagination, id) ->
				_contactPointActionRouter.retrieve(id, pagination)
		).build();
	}

	@Override
	public String getName() {
		return "contact-point";
	}

	@Override
	public ItemRoutes<ContactPoint, Long> itemRoutes(
		ItemRoutes.Builder<ContactPoint, Long> builder) {

		return builder.addGetter(
			_contactPointActionRouter::retrieve
		).build();
	}

	@Override
	public Representor<ContactPoint> representor(
		Representor.Builder<ContactPoint, Long> builder) {

		return builder.types(
			"ContactPoint"
		).identifier(
			ContactPoint::getId
		).addBidirectionalModel(
			"person", "contactPoint", PersonIdentifier.class,
			ContactPoint::getPersonId
		).addString(
			"contactOption", ContactPoint::getContactOption
		).addString(
			"email", ContactPoint::getEmail
		).addString(
			"getFaxNumber", ContactPoint::getFaxNumber
		).addString(
			"telephone", ContactPoint::getTelephone
		).build();
	}

	public interface ContactPointIdentifier extends Identifier<Long> {
	}

	@Reference
	private ContactPointActionRouter _contactPointActionRouter;

}