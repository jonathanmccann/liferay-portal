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

test('Ensure that a redirect can be added without updating references for redirect chain', async ({
	apiHelpers,
	page,
	redirectPage,
	site,
}) => {
	await apiHelpers.jsonWebServicesLayout.addLayout({
		groupId: site.id,
		title: 'Test Page 1',
	});

	await apiHelpers.jsonWebServicesLayout.addLayout({
		groupId: site.id,
		title: 'Test Page 2',
	});

	await apiHelpers.jsonWebServicesLayout.addLayout({
		groupId: site.id,
		title: 'Test Page 3',
	});

	await redirectPage.goto(site.friendlyUrlPath);

	await redirectPage.addRedirect(
		'test-page-1',
		`http://localhost:8080/web/${site.name}/test-page-2`,
		false
	);

	await page.getByRole('link', {name: 'Add'}).click();

	await redirectPage.fillRedirectDetails(
		'test-page-2',
		`http://localhost:8080/web/${site.name}/test-page-3`,
		false
	);

	await page.getByRole('button', {name: 'Create'}).click();

	await redirectPage.updateReferences(false);

	await expect(
		page.locator('.lfr-destination-url-column', {hasText: 'test-page-2'})
	).toBeVisible();

	await page.goto(`/web/${site.name}/test-page-1`);

	await expect(page.url()).toContain('test-page-3');

	await page.goto(`/web/${site.name}/test-page-2`);

	await expect(page.url()).toContain('test-page-3');
});

test('Ensure that a redirect can be added with updating references for redirect chain', async ({
	apiHelpers,
	page,
	redirectPage,
	site,
}) => {
	await apiHelpers.jsonWebServicesLayout.addLayout({
		groupId: site.id,
		title: 'Test Page 1',
	});

	await apiHelpers.jsonWebServicesLayout.addLayout({
		groupId: site.id,
		title: 'Test Page 2',
	});

	await apiHelpers.jsonWebServicesLayout.addLayout({
		groupId: site.id,
		title: 'Test Page 3',
	});

	await redirectPage.goto(site.friendlyUrlPath);

	await redirectPage.addRedirect(
		'test-page-1',
		`http://localhost:8080/web/${site.name}/test-page-2`,
		false
	);

	await page.getByRole('link', {name: 'Add'}).click();

	await redirectPage.fillRedirectDetails(
		'test-page-2',
		`http://localhost:8080/web/${site.name}/test-page-3`,
		false
	);

	await page.getByRole('button', {name: 'Create'}).click();

	await redirectPage.updateReferences();

	await expect(
		page.locator('.lfr-destination-url-column', {hasText: 'test-page-2'})
	).not.toBeVisible();

	await page.goto(`/web/${site.name}/test-page-1`);

	await expect(page.url()).toContain('test-page-3');

	await page.goto(`/web/${site.name}/test-page-2`);

	await expect(page.url()).toContain('test-page-3');
});
