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

package com.liferay.apio.architect.sample.internal.type;

import com.liferay.apio.architect.annotation.Id;
import com.liferay.apio.architect.annotation.Vocabulary.BidirectionalModel;
import com.liferay.apio.architect.annotation.Vocabulary.Field;
import com.liferay.apio.architect.annotation.Vocabulary.Type;
import com.liferay.apio.architect.identifier.Identifier;

/**
 * Instances of this interface represent a contact point exposed through the
 * API.
 *
 * @author Alejandro Hernández
 * @see    <a href="https://schema.org/ContactPoint">ContactPoint</a>
 * @review
 */
@Type("ContactPointAnnotated")
public interface ContactPoint extends Identifier<Long> {

	/**
	 * Returns the contact point's contact option.
	 *
	 * @return the contact point's contact option
	 * @see    <a href="https://schema.org/contactOption">creator</a>
	 * @review
	 */
	@Field("contactOption")
	public String getContactOption();

	/**
	 * Returns the contact point's email.
	 *
	 * @return the contact point's email
	 * @see    <a href="https://schema.org/email">creator</a>
	 * @review
	 */
	@Field("email")
	public String getEmail();

	/**
	 * Returns the contact point's fax number.
	 *
	 * @return the contact point's fax number
	 * @see    <a href="https://schema.org/faxNumber">creator</a>
	 * @review
	 */
	@Field("getFaxNumber")
	public String getFaxNumber();

	/**
	 * Returns the contact point's ID.
	 *
	 * @return the contact point's ID
	 * @review
	 */
	@Id
	public Long getId();

	/**
	 * Returns the contact point's person ID.
	 *
	 * @return the contact point's person ID
	 * @review
	 */
	@BidirectionalModel(
		field = @Field("contactPoint"), modelClass = Person.class
	)
	@Field("person")
	public Long getPersonId();

	/**
	 * Returns the contact point's telephone.
	 *
	 * @return the contact point's telephone
	 * @see    <a href="https://schema.org/telephone">creator</a>
	 * @review
	 */
	@Field("telephone")
	public String getTelephone();

}