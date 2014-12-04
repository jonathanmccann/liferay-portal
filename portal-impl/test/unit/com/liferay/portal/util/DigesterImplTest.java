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

package com.liferay.portal.util;

import com.liferay.portal.kernel.util.Digester;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Kayleen Lim
 * @author Jonathan McCann
 */
public class DigesterImplTest {

	@Test
	public void testGetTempFolderNameWithLongPortletId() throws Exception {
		Assert.assertEquals(
			"cabc7889bb678adaf651cbe68824ae7d4bfe0e859f00696c84549987207e5155",
			_digestImpl.digestHex(
				Digester.SHA_256, "com.liferay.portal.kernel.lar." +
					"ExportImportHelpertestajaxportlet_WAR_ajaxtestportlet_" +
					"INSTANCE_ovCXcIQL242L"));
	}

	@Test
	public void testGetTempFolderNameWithNoPortletId() throws Exception {
		Assert.assertEquals(
			"1db25597ffc920e0a2e046daa0eb47f628da8797c0807e4027b8b5cb494f0d63",
			_digestImpl.digestHex(
				Digester.SHA_256,
				"com.liferay.portal.kernel.lar.ExportImportHelper"));
	}

	@Test
	public void testGetTempFolderNameWithShortPortletId() throws Exception {
		Assert.assertEquals(
			"c04f8cdb685f7b905abc9e7e323c800ff8d08c2936c6b5ca153edfdd90850a0f",
			_digestImpl.digestHex(
				Digester.SHA_256,
				"com.liferay.portal.kernel.lar.ExportImportHelper86"));
	}

	private final DigesterImpl _digestImpl = new DigesterImpl();

}