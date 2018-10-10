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

package com.liferay.apio.architect.sample.internal.converter;

import com.liferay.apio.architect.sample.internal.dto.ContactPointModel;
import com.liferay.apio.architect.sample.internal.type.ContactPoint;

/**
 * Provides methods for creating {@link ContactPoint} instances out of other
 * DTOs.
 *
 * @author Víctor Galán
 * @review
 */
public class ContactPointConverter {

	/**
	 * Converts a {@link ContactPointModel} to a {@link ContactPoint}.
	 *
	 * @param  contactPointModel the internal contact point model
	 * @return a {@link ContactPoint}
	 * @review
	 */
	public static ContactPoint toContactPoint(
		ContactPointModel contactPointModel) {

		return new ContactPoint() {

			@Override
			public String getContactOption() {
				return contactPointModel.getContactPointType();
			}

			@Override
			public String getEmail() {
				return contactPointModel.getEmail();
			}

			@Override
			public String getFaxNumber() {
				return contactPointModel.getFax();
			}

			@Override
			public Long getId() {
				return contactPointModel.getId();
			}

			@Override
			public Long getPersonId() {
				return contactPointModel.getPersonId();
			}

			@Override
			public String getTelephone() {
				return contactPointModel.getPhoneNumber();
			}

		};
	}

}