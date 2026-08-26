/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {expect, mergeTests} from '@playwright/test';

import {dataApiHelpersTest} from '../../../fixtures/dataApiHelpersTest';
import {loginTest} from '../../../fixtures/loginTest';
import {
	InsightInput,
	PageData,
	Scan,
} from '../../../helpers/SEOStudioApiHelper';
import getRandomString from '../../../utils/getRandomString';
import {MISSING_OR_EMPTY_META_DESCRIPTION_TAG} from './constants/insightTypes';
import {SEO_STUDIO_FRIENDLY_URL} from './constants/site';
import {seoStudioPagesTest} from './fixtures/seoStudioPagesTest';

const test = mergeTests(loginTest(), dataApiHelpersTest, seoStudioPagesTest);

let insightInput: InsightInput & {pageURLs: PageData[]};
let scan: Scan;

test.beforeEach(async ({apiHelpers, onPagePage}) => {
	insightInput = {
		externalReferenceCode:
			MISSING_OR_EMPTY_META_DESCRIPTION_TAG.externalReferenceCode,
		pageURLs: [
			{
				author: 'Alice',
				pageURL: 'https://example.com/alpha',
				title: 'Alpha',
				type: 'Web Content',
			},
			{
				author: 'Bob',
				pageURL: 'https://example.com/beta',
				title: 'Beta',
				type: 'Document',
			},
		],
	};

	scan = await apiHelpers.seoStudio.createScan('crawler');

	await apiHelpers.seoStudio.createInsights(scan, [insightInput]);

	await onPagePage.goto(SEO_STUDIO_FRIENDLY_URL);
});

test.afterEach(async () => {
	await scan.teardown();
});

test(
	'Renders the breadcrumb, title, and content sections on the insight detail screen',
	{tag: '@LPD-91182'},
	async ({insightDetailPage, onPagePage, page}) => {
		await onPagePage.selectInsight(
			MISSING_OR_EMPTY_META_DESCRIPTION_TAG.label
		);

		await expect(insightDetailPage.onPageBreadcrumbLink).toBeVisible();

		await expect(
			page.getByRole('heading', {
				level: 2,
				name: `${MISSING_OR_EMPTY_META_DESCRIPTION_TAG.label} from ${insightInput.pageURLs.length} pages`,
			})
		).toBeVisible();

		await expect(insightDetailPage.descriptionSectionTitle).toBeVisible();
		await expect(
			page.getByText(
				MISSING_OR_EMPTY_META_DESCRIPTION_TAG.descriptionText
			)
		).toBeVisible();

		await expect(insightDetailPage.suggestionSectionTitle).toBeVisible();
		await expect(
			page.getByText(MISSING_OR_EMPTY_META_DESCRIPTION_TAG.fixHintText)
		).toBeVisible();
	}
);

test(
	'Renders the affected pages table with one row per scan insight',
	{tag: '@LPD-91182'},
	async ({insightDetailPage, onPagePage}) => {
		await onPagePage.selectInsight(
			MISSING_OR_EMPTY_META_DESCRIPTION_TAG.label
		);

		await expect(insightDetailPage.affectedPagesHeading).toContainText(
			`(${insightInput.pageURLs.length})`
		);

		for (const pageInput of insightInput.pageURLs) {
			const row = insightDetailPage.getAffectedPageRow(pageInput.title);

			await expect(row).toBeVisible();
			await expect(row).toContainText(pageInput.author);
			await expect(row).toContainText(pageInput.type);
			await expect(row).toContainText(pageInput.pageURL);
		}

		await expect(insightDetailPage.getTitleHeader()).toBeVisible();
		await expect(insightDetailPage.getTypeHeader()).toBeVisible();
	}
);

test(
	'Navigates back to the On-Page screen from the breadcrumb',
	{tag: '@LPD-91182'},
	async ({insightDetailPage, onPagePage}) => {
		await onPagePage.selectInsight(
			MISSING_OR_EMPTY_META_DESCRIPTION_TAG.label
		);

		await expect(insightDetailPage.onPageBreadcrumbLink).toBeVisible();

		await insightDetailPage.onPageBreadcrumbLink.click();

		await expect(onPagePage.onPageHeading).toBeVisible();
	}
);

test(
	'Lists only pending pages and excludes fixed pages from the affected pages table and count',
	{tag: '@LPD-95129'},
	async ({apiHelpers, insightDetailPage, onPagePage, page}) => {
		const fixedPages: PageData[] = [
			{
				author: getRandomString(),
				pageURL: `https://example.com/${getRandomString()}`,
				state: 0,
				title: 'FixedOne',
				type: getRandomString(),
			},
		];

		await apiHelpers.seoStudio.addPages(
			scan,
			insightInput.externalReferenceCode,
			fixedPages
		);

		await onPagePage.goto(SEO_STUDIO_FRIENDLY_URL);

		await onPagePage.selectInsight(
			MISSING_OR_EMPTY_META_DESCRIPTION_TAG.label
		);

		await expect(insightDetailPage.affectedPagesHeading).toContainText(
			`(${insightInput.pageURLs.length})`
		);

		await expect(
			page.getByRole('heading', {
				level: 2,
				name: `${MISSING_OR_EMPTY_META_DESCRIPTION_TAG.label} from ${insightInput.pageURLs.length} pages`,
			})
		).toBeVisible();

		for (const pageInput of insightInput.pageURLs) {
			await expect(
				insightDetailPage.getAffectedPageRow(pageInput.title)
			).toBeVisible();
		}

		for (const pageInput of fixedPages) {
			await expect(
				insightDetailPage.getAffectedPageRow(pageInput.title)
			).not.toBeVisible();
		}
	}
);
