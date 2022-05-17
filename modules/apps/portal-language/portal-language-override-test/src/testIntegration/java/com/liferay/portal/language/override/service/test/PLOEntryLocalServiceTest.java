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

package com.liferay.portal.language.override.service.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.ModelHintsUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.language.LanguageResources;
import com.liferay.portal.language.override.exception.PLOEntryKeyException;
import com.liferay.portal.language.override.exception.PLOEntryValueException;
import com.liferay.portal.language.override.model.PLOEntry;
import com.liferay.portal.language.override.service.PLOEntryLocalService;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.util.Locale;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Drew Brokke
 */
@RunWith(Arquillian.class)
public class PLOEntryLocalServiceTest {

	@ClassRule
	@Rule
	public static final LiferayIntegrationTestRule liferayIntegrationTestRule =
		new LiferayIntegrationTestRule();

	@Test
	public void testAddPLOEntry() throws Exception {
		Locale defaultLocale = LocaleUtil.getDefault();

		String languageId = LanguageUtil.getLanguageId(defaultLocale);

		long companyId = TestPropsValues.getCompanyId();
		long userId = TestPropsValues.getUserId();

		try {
			String key = StringPool.BLANK;

			_ploEntryLocalService.addOrUpdatePLOEntry(
				companyId, userId, key, languageId,
				RandomTestUtil.randomString());

			Assert.fail();
		}
		catch (PLOEntryKeyException.MustNotBeNull ploEntryKeyException) {
			Assert.assertEquals(
				"Key must not be null", ploEntryKeyException.getMessage());
		}

		int keyMaxLength = ModelHintsUtil.getMaxLength(
			PLOEntry.class.getName(), "key");

		try {
			_ploEntryLocalService.addOrUpdatePLOEntry(
				companyId, userId,
				RandomTestUtil.randomString(keyMaxLength + 1), languageId,
				RandomTestUtil.randomString());

			Assert.fail();
		}
		catch (PLOEntryKeyException.MustBeShorter ploEntryKeyException) {
			Assert.assertEquals(
				String.format(
					"Key must not have more than %s characters", keyMaxLength),
				ploEntryKeyException.getMessage());
		}

		String key = RandomTestUtil.randomString();

		try {
			_ploEntryLocalService.addOrUpdatePLOEntry(
				companyId, userId, key, languageId, StringPool.BLANK);

			Assert.fail();
		}
		catch (PLOEntryValueException.MustNotBeNull ploEntryValueException) {
			Assert.assertEquals(
				"Value must not be null", ploEntryValueException.getMessage());
		}

		PLOEntry ploEntry = _ploEntryLocalService.addOrUpdatePLOEntry(
			companyId, userId, key, languageId, RandomTestUtil.randomString());

		Assert.assertEquals(
			ploEntry.getValue(),
			LanguageResources.getMessage(defaultLocale, key));

		Assert.assertEquals(
			ploEntry.getValue(),
			ResourceBundleUtil.getString(
				LanguageResources.getResourceBundle(defaultLocale), key));

		PLOEntry updatedPLOEntry = _ploEntryLocalService.addOrUpdatePLOEntry(
			companyId, userId, key, languageId, RandomTestUtil.randomString());

		Assert.assertEquals(
			updatedPLOEntry.getValue(),
			LanguageResources.getMessage(defaultLocale, key));

		Assert.assertEquals(
			updatedPLOEntry.getValue(),
			ResourceBundleUtil.getString(
				LanguageResources.getResourceBundle(defaultLocale), key));
	}

	@Inject
	private PLOEntryLocalService _ploEntryLocalService;

}