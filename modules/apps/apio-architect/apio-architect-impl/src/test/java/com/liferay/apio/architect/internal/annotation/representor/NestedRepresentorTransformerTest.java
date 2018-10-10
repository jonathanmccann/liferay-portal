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
import static com.liferay.apio.architect.internal.unsafe.Unsafe.unsafeCast;

import static java.util.Arrays.asList;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;

import static org.junit.Assert.assertThat;

import com.liferay.apio.architect.alias.representor.NestedFieldFunction;
import com.liferay.apio.architect.alias.representor.NestedListFieldFunction;
import com.liferay.apio.architect.internal.annotation.representor.types.Dummy.IntegerIdentifier;
import com.liferay.apio.architect.internal.annotation.representor.types.DummyWithNested;
import com.liferay.apio.architect.internal.annotation.representor.types.DummyWithNested.NestedDummy;
import com.liferay.apio.architect.internal.annotation.representor.types.DummyWithNested.NestedDummyImpl;
import com.liferay.apio.architect.internal.representor.RepresentorTestUtil;
import com.liferay.apio.architect.related.RelatedCollection;
import com.liferay.apio.architect.related.RelatedModel;
import com.liferay.apio.architect.representor.NestedRepresentor;
import com.liferay.apio.architect.representor.Representor;

import java.util.Collections;
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
public class NestedRepresentorTransformerTest {

	@Before
	public void setUp() {
		_representor = RepresentorTransformer.toRepresentor(
			DummyWithNested.class, null, new HashMap<>());
	}

	@Test
	public void testNestedRepresentor() {
		List<NestedFieldFunction<DummyWithNested, ?>> nestedFieldFunctions =
			_representor.getNestedFieldFunctions();

		Stream<NestedFieldFunction<DummyWithNested, ?>> stream =
			nestedFieldFunctions.stream();

		List<NestedFieldFunction<DummyWithNested, ?>> nestedDummyList =
			stream.filter(
				nestedFieldFunction ->
					nestedFieldFunction.getKey().equals("nestedDummy")
			).collect(
				Collectors.toList()
			);

		assertThat(nestedDummyList.size(), is(1));

		NestedRepresentor<NestedDummy> nestedRepresentor = unsafeCast(
			nestedDummyList.get(0).getNestedRepresentor());

		_testRelatedModels(nestedRepresentor);

		_testRelatedCollections(nestedRepresentor);

		_testFields(nestedRepresentor);

		_testTypeName(nestedRepresentor);
	}

	@Test
	public void testNestedRepresentorList() {
		List<NestedListFieldFunction<DummyWithNested, ?>> nestedFieldFunctions =
			_representor.getNestedListFieldFunctions();

		Stream<NestedListFieldFunction<DummyWithNested, ?>> stream =
			nestedFieldFunctions.stream();

		List<NestedListFieldFunction<DummyWithNested, ?>> nestedDummyList =
			stream.filter(
				nestedFieldFunction ->
					nestedFieldFunction.getKey().equals("nestedDummyList")
			).collect(
				Collectors.toList()
			);

		assertThat(nestedDummyList.size(), is(1));

		NestedRepresentor<NestedDummy> nestedRepresentor = unsafeCast(
			nestedDummyList.get(0).getNestedRepresentor());

		_testRelatedModels(nestedRepresentor);

		_testRelatedCollections(nestedRepresentor);

		_testFields(nestedRepresentor);

		_testTypeName(nestedRepresentor);
	}

	private void _testFields(NestedRepresentor<NestedDummy> nestedRepresentor) {
		RepresentorTestUtil.testFields(
			_nestedDummy, nestedRepresentor.getStringListFunctions(),
			Collections.singletonList("stringListField"),
			Collections.singletonList(asList("one", "two")));

		RepresentorTestUtil.testFields(
			_nestedDummy, nestedRepresentor.getStringFunctions(),
			Collections.singletonList("stringField"),
			Collections.singletonList("string"));
	}

	private void _testRelatedCollections(
		NestedRepresentor<NestedDummy> nestedRepresentor) {

		Stream<RelatedCollection<NestedDummy, ?>> relatedCollections =
			nestedRepresentor.getRelatedCollections();

		List<?> list0 = relatedCollections.filter(
			relatedCollection ->
				relatedCollection.getIdentifierClass() ==
					IntegerIdentifier.class
		).filter(
			relatedCollection ->
				relatedCollection.getKey().equals("relatedCollection")
		).collect(
			Collectors.toList()
		);

		MatcherAssert.assertThat(list0, hasSize(1));
	}

	private void _testRelatedModels(
		NestedRepresentor<NestedDummy> nestedRepresentor) {

		List<RelatedModel<NestedDummy, ?>> relatedModels =
			nestedRepresentor.getRelatedModels();

		testRelatedModel(
			relatedModels.get(0), _nestedDummy, "linkedModel",
			IntegerIdentifier.class, 1);
	}

	private void _testTypeName(
		NestedRepresentor<NestedDummy> nestedRepresentor) {

		assertThat(nestedRepresentor.getPrimaryType(), is("NestedDummy"));
	}

	private final NestedDummy _nestedDummy = new NestedDummyImpl();
	private Representor<DummyWithNested> _representor;

}