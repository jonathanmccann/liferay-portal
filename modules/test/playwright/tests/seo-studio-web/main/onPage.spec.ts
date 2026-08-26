/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {expect, mergeTests} from '@playwright/test';

import {dataApiHelpersTest} from '../../../fixtures/dataApiHelpersTest';
import {loginTest} from '../../../fixtures/loginTest';
import {Scan} from '../../../helpers/SEOStudioApiHelper';
import {
	MISSING_OR_EMPTY_META_DESCRIPTION_TAG,
	MISSING_OR_EMPTY_TITLE_TAG,
} from './constants/insightTypes';
import {SEO_STUDIO_FRIENDLY_URL} from './constants/site';
import {seoStudioPagesTest} from './fixtures/seoStudioPagesTest';

const test = mergeTests(loginTest(), dataApiHelpersTest, seoStudioPagesTest);

test(
	'Renders the "no scans yet" empty state on the On-Page screen',
	{tag: '@LPD-91406'},
	async ({onPagePage}) => {
		await onPagePage.goto(SEO_STUDIO_FRIENDLY_URL);

		await expect(onPagePage.onPageHeading).toBeVisible();

		await expect(onPagePage.lastScanLabel).toContainText(
			'Last Scan: Never'
		);

		await expect(onPagePage.runScanNowButton).toBeVisible();

		await onPagePage.emptyStateIsVisible('no-scans');
	}
);

test(
	'Renders the "no insights found" empty state when the latest scan has no insights',
	{tag: '@LPD-91406'},
	async ({apiHelpers, onPagePage}) => {
		const scan = await apiHelpers.seoStudio.createScan('crawler');

		try {
			await onPagePage.goto(SEO_STUDIO_FRIENDLY_URL);

			await onPagePage.emptyStateIsVisible('no-insights');
		}
		finally {
			await scan.teardown();
		}
	}
);

test(
	'Renders the Insights table rows when the latest scan has insights',
	{tag: '@LPD-91406'},
	async ({apiHelpers, onPagePage}) => {
		const insightInputs = [
			{
				insightType: MISSING_OR_EMPTY_TITLE_TAG,
				pageURLs: [
					'https://example.com/a',
					'https://example.com/b',
					'https://example.com/c',
				],
			},
			{
				insightType: MISSING_OR_EMPTY_META_DESCRIPTION_TAG,
				pageURLs: ['https://example.com/d', 'https://example.com/e'],
			},
		];

		const scan = await apiHelpers.seoStudio.createScan('crawler');

		try {
			await apiHelpers.seoStudio.createInsights(
				scan,
				insightInputs.map(({insightType, pageURLs}) => ({
					externalReferenceCode: insightType.externalReferenceCode,
					pageURLs,
				}))
			);

			await onPagePage.goto(SEO_STUDIO_FRIENDLY_URL);

			for (const {insightType, pageURLs} of insightInputs) {
				const row = onPagePage.getInsightRow(insightType.label);

				await expect(row).toBeVisible();

				await expect(row).toContainText(String(pageURLs.length));
			}
		}
		finally {
			await scan.teardown();
		}
	}
);

test.describe('Filter and Sort Insights tests', () => {
	let scan: Scan;

	test.beforeEach(async ({apiHelpers, onPagePage}) => {
		scan = await apiHelpers.seoStudio.createScan('crawler');

		await apiHelpers.seoStudio.createInsights(scan, [
			{
				externalReferenceCode:
					MISSING_OR_EMPTY_TITLE_TAG.externalReferenceCode,
				pageURLs: ['https://example.com/a'],
			},
			{
				externalReferenceCode:
					MISSING_OR_EMPTY_META_DESCRIPTION_TAG.externalReferenceCode,
				pageURLs: ['https://example.com/b'],
			},
		]);

		await onPagePage.goto(SEO_STUDIO_FRIENDLY_URL);
	});

	test.afterEach(async () => {
		await scan.teardown();
	});

	test(
		'Sorts the insights by impact from high to low by default',
		{tag: '@LPD-91408'},
		async ({onPagePage}) => {
			await expect(
				onPagePage.getInsightRow(MISSING_OR_EMPTY_TITLE_TAG.label)
			).toBeVisible();

			await expect(async () => {
				expect(await onPagePage.getInsightNamesInOrder()).toEqual([
					MISSING_OR_EMPTY_TITLE_TAG.label,
					MISSING_OR_EMPTY_META_DESCRIPTION_TAG.label,
				]);
			}).toPass({timeout: 10000});
		}
	);

	test(
		'Sorts the insights by a column header and reflects the sort in the URL',
		{tag: '@LPD-91408'},
		async ({onPagePage, page}) => {
			await expect(
				onPagePage.getInsightRow(MISSING_OR_EMPTY_TITLE_TAG.label)
			).toBeVisible();

			await onPagePage.sortByColumn('Impact');

			await expect(page).toHaveURL(/fdsConfig/);

			await expect(async () => {
				expect(await onPagePage.getInsightNamesInOrder()).toEqual([
					MISSING_OR_EMPTY_META_DESCRIPTION_TAG.label,
					MISSING_OR_EMPTY_TITLE_TAG.label,
				]);
			}).toPass({timeout: 10000});

			await onPagePage.sortByColumn('Impact');

			await expect(async () => {
				expect(await onPagePage.getInsightNamesInOrder()).toEqual([
					MISSING_OR_EMPTY_TITLE_TAG.label,
					MISSING_OR_EMPTY_META_DESCRIPTION_TAG.label,
				]);
			}).toPass({timeout: 10000});
		}
	);

	test(
		'Filters the insights by impact and reflects the filter in the URL',
		{tag: '@LPD-91408'},
		async ({onPagePage, page}) => {
			await onPagePage.applyFilter('Impact', ['High']);

			await expect(
				onPagePage.activeFilterChip('Impact: High')
			).toBeVisible();

			await expect(
				onPagePage.getInsightRow(MISSING_OR_EMPTY_TITLE_TAG.label)
			).toBeVisible();
			await expect(
				onPagePage.getInsightRow(
					MISSING_OR_EMPTY_META_DESCRIPTION_TAG.label
				)
			).not.toBeVisible();

			await expect(page).toHaveURL(/fdsConfig/);
		}
	);

	test(
		'Shows the filtered empty state when no insights match the filters',
		{tag: '@LPD-91408'},
		async ({onPagePage}) => {
			await onPagePage.applyFilter('Category', ['AEO Readiness']);

			await expect(onPagePage.filteredEmptyStateTitle).toBeVisible();
		}
	);

	test(
		'Preserves the active filter when returning via the insight detail breadcrumb',
		{tag: '@LPD-91408'},
		async ({insightDetailPage, onPagePage, page}) => {
			await onPagePage.applyFilter('Impact', ['High']);

			await expect(
				onPagePage.activeFilterChip('Impact: High')
			).toBeVisible();
			await expect(
				onPagePage.getInsightRow(
					MISSING_OR_EMPTY_META_DESCRIPTION_TAG.label
				)
			).not.toBeVisible();

			await onPagePage.selectInsight(MISSING_OR_EMPTY_TITLE_TAG.label);

			await expect(page).toHaveURL(/objectEntryExternalReferenceCode=/);

			await insightDetailPage.onPageBreadcrumbLink.click();

			await expect(onPagePage.onPageHeading).toBeVisible();

			await expect(
				onPagePage.activeFilterChip('Impact: High')
			).toBeVisible();
			await expect(
				onPagePage.getInsightRow(
					MISSING_OR_EMPTY_META_DESCRIPTION_TAG.label
				)
			).not.toBeVisible();
		}
	);
});
