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

package com.liferay.portal.kernel.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Andrew Betts
 */
public class TreePathUtilTest {

	@Test
	public void testGetPrimaryKeys() {
		String treePath = "";

		Assert.assertEquals(
			null , TreePathUtil.getPrimaryKeys(treePath));

		treePath = "/";

		Assert.assertEquals(
			new long[0], TreePathUtil.getPrimaryKeys(treePath));

		treePath = "123/456/789";

		Assert.assertEquals(
			new long[] {123, 456, 789}, TreePathUtil.getPrimaryKeys(treePath));

		treePath = "/123/456/789/";

		Assert.assertEquals(
			new long[] {123, 456, 789}, TreePathUtil.getPrimaryKeys(treePath));
	}

}