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

import static com.liferay.apio.architect.sample.internal.converter.ContactPointConverter.toContactPoint;
import static com.liferay.apio.architect.sample.internal.converter.DemoDataUtil.CONTACT_POINT_MODEL;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.liferay.apio.architect.sample.internal.type.ContactPoint;

import org.junit.Test;

/**
 * @author Víctor Galán
 */
public class ContactPointConverterTest {

	@Test
	public void testToBlogPosting() {
		ContactPoint contactPoint = toContactPoint(CONTACT_POINT_MODEL);

		assertThat(contactPoint.getContactOption(), is("office"));
		assertThat(contactPoint.getEmail(), is("email@liferay.com"));
		assertThat(contactPoint.getFaxNumber(), is("123"));
		assertThat(contactPoint.getId(), is(2L));
		assertThat(contactPoint.getPersonId(), is(1L));
		assertThat(contactPoint.getTelephone(), is("456"));
	}

}