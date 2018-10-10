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

import static org.hamcrest.core.Is.is;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;

import com.liferay.apio.architect.annotation.Vocabulary.Type;
import com.liferay.apio.architect.functional.Try;
import com.liferay.apio.architect.identifier.Identifier;
import com.liferay.apio.architect.router.ActionRouter;

import org.junit.Test;

/**
 * @author Víctor Galán
 */
public class ActionRouterTypeExtractorTest {

	@Test
	public void testExtractRouterWithCorrectTypeParameter() {
		@Type("Dummy")
		class Dummy implements Identifier<Long> {
		}

		class DummyRouter implements ActionRouter<Dummy> {
		}

		Try<Class<? extends Identifier>> classTry =
			ActionRouterTypeExtractor.extractTypeClass(new DummyRouter());

		assertSame(classTry.getUnchecked(), Dummy.class);
	}

	@Test
	public void testExtractRouterWithoutTypeParameter() {
		class DummyRouter implements ActionRouter {
		}

		Try<Class<? extends Identifier>> classTry =
			ActionRouterTypeExtractor.extractTypeClass(new DummyRouter());

		assertThat(classTry.isFailure(), is(true));
	}

	@Test
	public void testExtractRouterWithTypeParameterAnnotated() {
		class Dummy implements Identifier<Long> {
		}

		class DummyRouter implements ActionRouter<Dummy> {
		}

		Try<Class<? extends Identifier>> classTry =
			ActionRouterTypeExtractor.extractTypeClass(new DummyRouter());

		assertThat(classTry.isFailure(), is(true));
	}

}