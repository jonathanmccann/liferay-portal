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

package com.liferay.apio.architect.sample.internal.router;

import com.liferay.apio.architect.annotation.Actions.Retrieve;
import com.liferay.apio.architect.annotation.Id;
import com.liferay.apio.architect.annotation.ParentId;
import com.liferay.apio.architect.pagination.PageItems;
import com.liferay.apio.architect.pagination.Pagination;
import com.liferay.apio.architect.router.ActionRouter;
import com.liferay.apio.architect.sample.internal.converter.ContactPointConverter;
import com.liferay.apio.architect.sample.internal.dao.ContactPointModelService;
import com.liferay.apio.architect.sample.internal.dto.ContactPointModel;
import com.liferay.apio.architect.sample.internal.type.ContactPoint;
import com.liferay.apio.architect.sample.internal.type.Person;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ws.rs.NotFoundException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * Provides all the information necessary to expose <a
 * href="http://schema.org/ContactPoint">ContactPoint</a> resources through a
 * web API. The resources are mapped from the internal {@link ContactPointModel}
 * model.
 *
 * @author Víctor Galán
 * @review
 */
@Component(
	immediate = true,
	service = {ActionRouter.class, ContactPointActionRouter.class}
)
public class ContactPointActionRouter implements ActionRouter<ContactPoint> {

	@Retrieve
	public ContactPoint retrieve(@Id long id) {
		Optional<ContactPointModel> optional = _contactPointModelService.get(
			id);

		return optional.map(
			ContactPointConverter::toContactPoint
		).orElseThrow(
			() -> new NotFoundException("Unable to get contact point " + id)
		);
	}

	@Retrieve
	public PageItems<ContactPoint> retrieve(
		@ParentId(Person.class) long id, Pagination pagination) {

		List<ContactPointModel> contactPointModels =
			_contactPointModelService.getPage(
				id, pagination.getStartPosition(), pagination.getEndPosition());
		int count = _contactPointModelService.getCount(id);

		Stream<ContactPointModel> stream = contactPointModels.stream();

		List<ContactPoint> contactPoints = stream.map(
			ContactPointConverter::toContactPoint
		).collect(
			Collectors.toList()
		);

		return new PageItems<>(contactPoints, count);
	}

	@Reference
	private ContactPointModelService _contactPointModelService;

}