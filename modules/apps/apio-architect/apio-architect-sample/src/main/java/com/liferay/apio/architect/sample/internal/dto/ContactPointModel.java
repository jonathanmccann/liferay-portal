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

package com.liferay.apio.architect.sample.internal.dto;

/**
 * Represents a contact point. This is a mock class for sample purposes only.
 *
 * @author Víctor Galán
 * @review
 */
public class ContactPointModel {

	public ContactPointModel(
		Long personId, Long id, String email, String fax, String phoneNumber,
		String contactPointType) {

		_personId = personId;
		_id = id;
		_email = email;
		_fax = fax;
		_phoneNumber = phoneNumber;
		_contactPointType = contactPointType;
	}

	public String getContactPointType() {
		return _contactPointType;
	}

	public String getEmail() {
		return _email;
	}

	public String getFax() {
		return _fax;
	}

	public Long getId() {
		return _id;
	}

	public Long getPersonId() {
		return _personId;
	}

	public String getPhoneNumber() {
		return _phoneNumber;
	}

	private final String _contactPointType;
	private final String _email;
	private final String _fax;
	private final Long _id;
	private final Long _personId;
	private final String _phoneNumber;

}