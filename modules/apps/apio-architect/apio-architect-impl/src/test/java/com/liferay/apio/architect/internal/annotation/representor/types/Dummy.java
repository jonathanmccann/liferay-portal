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

package com.liferay.apio.architect.internal.annotation.representor.types;

import static java.util.Arrays.asList;

import com.liferay.apio.architect.annotation.Id;
import com.liferay.apio.architect.annotation.Vocabulary.BidirectionalModel;
import com.liferay.apio.architect.annotation.Vocabulary.Field;
import com.liferay.apio.architect.annotation.Vocabulary.LinkedModel;
import com.liferay.apio.architect.annotation.Vocabulary.RelatedCollection;
import com.liferay.apio.architect.annotation.Vocabulary.RelativeURL;
import com.liferay.apio.architect.annotation.Vocabulary.Type;
import com.liferay.apio.architect.identifier.Identifier;

import java.util.Date;
import java.util.List;

/**
 * @author Víctor Galán
 */
@Type("Dummy")
public interface Dummy extends Identifier<Long> {

	@Field("applicationRelativeUrl")
	@RelativeURL(fromApplication = true)
	public default String getApplicationRelativeUrl() {
		return "/application";
	}

	@BidirectionalModel(
		field = @Field("linked1"), modelClass = IntegerIdentifier.class
	)
	@Field("bidirectional1")
	public default Integer getBidirectional1() {
		return 3;
	}

	@BidirectionalModel(
		field = @Field("linked2"), modelClass = StringIdentifier.class
	)
	@Field("bidirectional2")
	public default String getBidirectional2() {
		return "2d1d";
	}

	@Field("booleanField1")
	public default Boolean getBooleanField1() {
		return true;
	}

	@Field("booleanField2")
	public default Boolean getBooleanField2() {
		return false;
	}

	@Field("booleanListField1")
	public default List<Boolean> getBooleanListField1() {
		return asList(true, true, false);
	}

	@Field("booleanListField2")
	public default List<Boolean> getBooleanListField2() {
		return asList(false, true, true);
	}

	@Field("dateField1")
	public default Date getDateField1() {
		return new Date(1L);
	}

	@Field("dateField2")
	public default Date getDateField2() {
		return new Date(200000L);
	}

	@Id
	public default Long getId() {
		return 1L;
	}

	@Field("linkedModel1")
	@LinkedModel(IntegerIdentifier.class)
	public default Integer getLinkedModel1() {
		return 1;
	}

	@Field("linkedModel2")
	@LinkedModel(StringIdentifier.class)
	public default String getLinkedModel2() {
		return "2d1d";
	}

	@Field("numberField1")
	public default Integer getNumberField1() {
		return 10;
	}

	@Field("numberField2")
	public default Long getNumberField2() {
		return 20L;
	}

	@Field("stringListField1")
	public default List<String> getNumberListField() {
		return asList("one", "two", "three");
	}

	@Field("stringListField2")
	public default List<String> getNumberListField2() {
		return asList("four", "five", "six");
	}

	@Field("relatedCollection1")
	@RelatedCollection(IntegerIdentifier.class)
	public default Integer getRelatedCollection1() {
		return 1;
	}

	@Field("relatedCollection2")
	@RelatedCollection(StringIdentifier.class)
	public default String getRelatedCollection2() {
		return "2d1d";
	}

	@Field("relativeUrl1")
	@RelativeURL
	public default String getRelativeUrl1() {
		return "/first";
	}

	@Field("relativeUrl2")
	@RelativeURL
	public default String getRelativeUrl2() {
		return "/second";
	}

	@Field("stringField1")
	public default String getStringField1() {
		return "string1";
	}

	@Field("stringField2")
	public default String getStringField2() {
		return "string2";
	}

	@Field("numberListField1")
	public default List<Integer> getStringListField1() {
		return asList(1, 2, 3);
	}

	@Field("numberListField2")
	public default List<Integer> getStringListField2() {
		return asList(4, 5, 6);
	}

	public class DummpyImpl implements Dummy {
	}

	public interface IntegerIdentifier extends Identifier<Integer> {
	}

	public interface StringIdentifier extends Identifier<String> {
	}

}