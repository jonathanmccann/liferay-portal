/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

// The insight type catalogue is seeded by the site initializer and is shared by
// every scan, so tests reference the seeded entries instead of creating their
// own. The label, description, and suggestion are resolved from language keys
// derived from the external reference code.

export const MISSING_OR_EMPTY_META_DESCRIPTION_TAG = {
	descriptionText: 'Google autogenerates a snippet from body text',
	externalReferenceCode: 'MISSING_OR_EMPTY_META_DESCRIPTION_TAG',
	fixHintText:
		'Add a unique meta description of roughly 150 to 160 characters',
	label: 'Missing or Empty Meta Description Tag',
};

export const MISSING_OR_EMPTY_TITLE_TAG = {
	descriptionText:
		'The title is the first thing search engines and users read',
	externalReferenceCode: 'MISSING_OR_EMPTY_TITLE_TAG',
	fixHintText: 'Add a concise, unique',
	label: 'Missing or Empty Title Tag',
};
