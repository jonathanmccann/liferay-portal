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

package com.liferay.apio.architect.sample.internal.dao;

import com.github.javafaker.Company;
import com.github.javafaker.Faker;
import com.github.javafaker.Internet;
import com.github.javafaker.PhoneNumber;

import com.liferay.apio.architect.sample.internal.dto.ContactPointModel;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * Contains methods for retrieving contact points in an in-memory database with
 * fake data.
 *
 * @author Victor Galán
 * @review
 */
@Component(immediate = true, service = ContactPointModelService.class)
public class ContactPointModelService {

	/**
	 * Computes the fake data for this model class.
	 *
	 * @review
	 */
	@Activate
	public void activate() {
		int personCount = _personModelService.getCount();

		for (long index = 0; index < personCount; index++) {
			Map<Long, ContactPointModel> contactPointModels = new HashMap<>();
			Random random = new Random();

			for (int i = 0; i < random.nextInt(5); i++) {
				Faker faker = new Faker();

				Internet internet = faker.internet();
				PhoneNumber phoneNumber = faker.phoneNumber();
				Company company = faker.company();

				ContactPointModel contactPointModel = new ContactPointModel(
					index, _count.get(), internet.safeEmailAddress(),
					phoneNumber.cellPhone(), phoneNumber.cellPhone(),
					company.name());

				contactPointModels.put(
					_count.getAndIncrement(), contactPointModel);
			}

			_contactPointsModels.put(index, contactPointModels);
		}
	}

	/**
	 * Returns the contact point that matches the specified ID, if that contact
	 * point exists; returns {@code Optional#empty()} otherwise.
	 *
	 * @param  id the contact point's ID
	 * @return the contact point, if present; {@code Optional#empty()} otherwise
	 * @review
	 */
	public Optional<ContactPointModel> get(long id) {
		Collection<Map<Long, ContactPointModel>> contactPointModels =
			_contactPointsModels.values();

		Stream<Map<Long, ContactPointModel>> stream =
			contactPointModels.stream();

		return stream.map(
			Map::values
		).map(
			Collection::stream
		).flatMap(
			Function.identity()
		).filter(
			contactPointModel -> contactPointModel.getId() == id
		).findFirst();
	}

	/**
	 * Returns the total number of contact points for a person.
	 *
	 * @param  personId the person's ID
	 * @return the total number of comments
	 * @review
	 */
	public int getCount(long personId) {
		return Optional.of(
			personId
		).map(
			_contactPointsModels::get
		).map(
			Map::size
		).orElse(
			0
		);
	}

	/**
	 * Returns the page of contact points for a person as specified by the
	 * page's start and end positions.
	 *
	 * @param  personId the contact point's ID
	 * @param  start the page's start position
	 * @param  end the page's end position
	 * @return the page of contact points
	 * @review
	 */
	public List<ContactPointModel> getPage(long personId, int start, int end) {
		return Optional.of(
			personId
		).map(
			_contactPointsModels::get
		).map(
			Map::values
		).map(
			Collection::stream
		).orElseGet(
			Stream::empty
		).skip(
			start
		).limit(
			end
		).collect(
			Collectors.toList()
		);
	}

	private final Map<Long, Map<Long, ContactPointModel>> _contactPointsModels =
		new ConcurrentHashMap<>();
	private final AtomicLong _count = new AtomicLong(0);

	@Reference
	private PersonModelService _personModelService;

}