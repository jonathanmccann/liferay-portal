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

package com.liferay.apio.architect.internal.annotation.representor;

import static com.liferay.apio.architect.internal.representor.RepresentorTestUtil.testRelatedModel;

import static java.util.Arrays.asList;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;

import static org.junit.Assert.assertThat;

import com.liferay.apio.architect.alias.representor.FieldFunction;
import com.liferay.apio.architect.internal.annotation.representor.types.Dummy;
import com.liferay.apio.architect.internal.annotation.representor.types.Dummy.DummpyImpl;
import com.liferay.apio.architect.internal.annotation.representor.types.Dummy.IntegerIdentifier;
import com.liferay.apio.architect.internal.annotation.representor.types.Dummy.StringIdentifier;
import com.liferay.apio.architect.internal.representor.RepresentorTestUtil;
import com.liferay.apio.architect.related.RelatedCollection;
import com.liferay.apio.architect.related.RelatedModel;
import com.liferay.apio.architect.representor.Representor;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hamcrest.MatcherAssert;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Víctor Galán
 */
public class RepresentorTransformerTest {

	@Before
	public void setUp() {
		_relatedCollections = new HashMap<>();

		_representor = RepresentorTransformer.toRepresentor(
			Dummy.class, null, _relatedCollections);
	}

	@Test
	public void testApplicationRelativeUrlFields() {
		_testFields(
			_representor.getApplicationRelativeURLFunctions(),
			asList("applicationRelativeUrl"), asList("/application"));
	}

	@Test
	public void testBooleanFields() {
		_testFields(
			_representor.getBooleanFunctions(),
			asList("booleanField1", "booleanField2"), asList(true, false));
	}

	@Test
	public void testIdentifier() {
		assertThat(_representor.getIdentifier(_dummy), is(1L));
	}

	@Test
	public void testLinkedModel() {
		List<RelatedModel<Dummy, ?>> relatedModels =
			_representor.getRelatedModels();

		relatedModels.sort(Comparator.comparing(RelatedModel::getKey));

		MatcherAssert.assertThat(relatedModels, hasSize(4));

		testRelatedModel(
			relatedModels.get(0), _dummy, "bidirectional1",
			IntegerIdentifier.class, 3);

		testRelatedModel(
			relatedModels.get(1), _dummy, "bidirectional2",
			StringIdentifier.class, "2d1d");

		testRelatedModel(
			relatedModels.get(2), _dummy, "linkedModel1",
			IntegerIdentifier.class, 1);

		testRelatedModel(
			relatedModels.get(3), _dummy, "linkedModel2",
			StringIdentifier.class, "2d1d");
	}

	@Test
	public void testListFields() {
		_testFields(
			_representor.getStringListFunctions(),
			asList("stringListField1", "stringListField2"),
			asList(
				asList("one", "two", "three"), asList("four", "five", "six")));

		_testFields(
			_representor.getNumberListFunctions(),
			asList("numberListField1", "numberListField2"),
			asList(asList(1, 2, 3), asList(4, 5, 6)));

		_testFields(
			_representor.getBooleanListFunctions(),
			asList("booleanListField1", "booleanListField2"),
			asList(asList(true, true, false), asList(false, true, true)));
	}

	@Test
	public void testNumberFields() {
		_testFields(
			_representor.getNumberFunctions(),
			asList("numberField1", "numberField2"), asList(10, 20L));
	}

	@Test
	public void testRelatedCollection() {
		Stream<RelatedCollection<Dummy, ?>> relatedCollections =
			_representor.getRelatedCollections();

		List<?> list = relatedCollections.filter(
			relatedCollection ->
				(relatedCollection.getIdentifierClass() ==
					IntegerIdentifier.class) ||
				(relatedCollection.getIdentifierClass() ==
					StringIdentifier.class)
		).filter(
			relatedCollection ->
				relatedCollection.getKey().equals("relatedCollection1") ||
				relatedCollection.getKey().equals("relatedCollection2")
		).collect(
			Collectors.toList()
		);

		MatcherAssert.assertThat(list, hasSize(2));
	}

	@Test
	public void testRelativeUrlFields() {
		_testFields(
			_representor.getRelativeURLFunctions(),
			asList("relativeUrl1", "relativeUrl2"),
			asList("/first", "/second"));
	}

	@Test
	public void testStringFields() {
		_testFields(
			_representor.getStringFunctions(),
			asList("dateField1", "dateField2", "stringField1", "stringField2"),
			asList(
				"1970-01-01T00:00Z", "1970-01-01T00:03Z", "string1",
				"string2"));
	}

	@Test
	public void testTypeName() {
		assertThat(_representor.getPrimaryType(), is("Dummy"));
	}

	private <T> void _testFields(
		List<FieldFunction<Dummy, T>> list, List<String> keys, List<T> values) {

		list.sort(Comparator.comparing(FieldFunction::getKey));

		RepresentorTestUtil.testFields(_dummy, list, keys, values);
	}

	private final Dummy _dummy = new DummpyImpl();
	private HashMap<String, List<RelatedCollection<?, ?>>> _relatedCollections;
	private Representor<Dummy> _representor;

}