/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {expect, mergeTests} from '@playwright/test';

import {apiHelpersTest} from '../../fixtures/apiHelpersTest';
import {isolatedSiteTest} from '../../fixtures/isolatedSiteTest';
import {loginTest} from '../../fixtures/loginTest';
import {redirectPagesTest} from '../../fixtures/redirectPagesTest';

export const test = mergeTests(
	apiHelpersTest,
	isolatedSiteTest,
	loginTest(),
	redirectPagesTest
);

test('Ensure that the user will be redirected to the cached target URL of a permanent redirection after it is updated', async ({
	apiHelpers,
	page,
	redirectPage,
	site,
}) => {
	const destinationPage = await apiHelpers.jsonWebServicesLayout.addLayout({
		groupId: site.id,
		title: 'Destination Page',
	});

	const newDestinationPage = await apiHelpers.jsonWebServicesLayout.addLayout(
		{
			groupId: site.id,
			title: 'New Destination Page',
		}
	);

	await redirectPage.goto(site.friendlyUrlPath);

	await redirectPage.addRedirect(
		'test/source/url',
		`http://localhost:8080/web/${site.name}${destinationPage.friendlyURL}`,
		true
	);

	await page.goto(`/web/${site.name}/test/source/url`);

	await expect(page.url()).toContain(destinationPage.friendlyURL);

	await redirectPage.goto(site.friendlyUrlPath);

	await redirectPage.editRedirect(
		'/test/source/url',
		'test/source/url',
		`http://localhost:8080/web/${site.name}${newDestinationPage.friendlyURL}`,
		true
	);

	await page.goto(`/web/${site.name}/test/source/url`);

	await expect(page.url()).toContain(destinationPage.friendlyURL);
});

test('Ensure that the user will be redirected to the latest target URL of a temporary redirection after it is updated', async ({
	apiHelpers,
	page,
	redirectPage,
	site,
}) => {
	const destinationPage = await apiHelpers.jsonWebServicesLayout.addLayout({
		groupId: site.id,
		title: 'Destination Page',
	});

	const newDestinationPage = await apiHelpers.jsonWebServicesLayout.addLayout(
		{
			groupId: site.id,
			title: 'New Destination Page',
		}
	);

	await redirectPage.goto(site.friendlyUrlPath);

	await redirectPage.addRedirect(
		'test/source/url',
		`http://localhost:8080/web/${site.name}${destinationPage.friendlyURL}`,
		false
	);

	await page.goto(`/web/${site.name}/test/source/url`);

	await expect(page.url()).toContain(destinationPage.friendlyURL);

	await redirectPage.goto(site.friendlyUrlPath);

	await redirectPage.editRedirect(
		'/test/source/url',
		'test/source/url',
		`http://localhost:8080/web/${site.name}${newDestinationPage.friendlyURL}`,
		false
	);

	await page.goto(`/web/${site.name}/test/source/url`);

	await expect(page.url()).toContain(newDestinationPage.friendlyURL);
});
