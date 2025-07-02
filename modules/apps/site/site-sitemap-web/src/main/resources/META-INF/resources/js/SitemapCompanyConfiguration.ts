/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {openSelectionModal} from 'frontend-js-components-web';
import {delegate, sub} from 'frontend-js-web';

interface Props {
	groupSelectorURL: string;
	namespace: string;
	objectDefinitionSelectorURL: string;
	selectEventName: string;
}

type SelectedItem = {
	groupdescriptivename: string;
	groupid: string;
	hasvirtualhost: string;
};

export default function ({
	groupSelectorURL,
	namespace,
	objectDefinitionSelectorURL,
	selectEventName,
}: Props) {
	const groupIdsInput = document.getElementById(
		`${namespace}groupsSearchContainerPrimaryKeys`
	) as HTMLInputElement;

	const objectDefinitionExternalReferenceCodesInput = document.getElementById(
		`${namespace}objectDefinitionsSearchContainerPrimaryKeys`
	) as HTMLInputElement;

	const selectObjectDefinitionButton = document.getElementById(
		`${namespace}selectObjectDefinitionLink`
	) as HTMLButtonElement;

	const selectSiteButton = document.getElementById(
		`${namespace}selectSiteLink`
	) as HTMLButtonElement;

	// @ts-ignore

	const groupsSearchContainer = Liferay.SearchContainer.get(
		`${namespace}groupsSearchContainer`
	);

	const groupsSearchContainerContentBox =
		groupsSearchContainer.get('contentBox');

	const objectDefinitionsSearchContainer = Liferay.SearchContainer.get(
		`${namespace}objectDefinitionsSearchContainer`
	);

	const objectDefinitionsSearchContainerContentBox =
		objectDefinitionsSearchContainer.get('contentBox');

	const getSearchContainerData = (searchContainer: any) => {
		const searchContainerData = searchContainer.getData();

		return !searchContainerData.length
			? []
			: searchContainerData.split(',');
	};

	const onSelectObjectDefinitionClick = () => {
		const objectDefinitionExternalReferenceCodes = getSearchContainerData(
			objectDefinitionsSearchContainer
		);

		openSelectionModal({
			onSelect: (selectedItem: SelectedItem) => {
				if (selectedItem) {
					const values = JSON.parse(selectedItem.value);

					const rowColumns = [];

					const title = sub(
						Liferay.Language.get('remove-x'),
						values.label
					);

					const removeIcon =
						Liferay.Util.getLexiconIconTpl('times-circle');

					const removeButton = `<button
						aria-label="${title}"
						class="btn btn-monospaced btn-outline-borderless btn-outline-secondary
							btn-sm lfr-portal-tooltip remove-button text-secondary" 
						data-rowid="${values.externalReferenceCode}" 
						type="button" 
						title="${title}"
					>
						<span class="inline-item">${removeIcon}</span>
					</button>`;

					rowColumns.push(
						`<span class="text-truncate">${values.label}</span>`
					);
					rowColumns.push(removeButton);

					objectDefinitionsSearchContainer.addRow(
						rowColumns,
						values.externalReferenceCode
					);
					objectDefinitionsSearchContainer.updateDataStore();

					objectDefinitionExternalReferenceCodes.push(
						values.externalReferenceCode
					);
					objectDefinitionExternalReferenceCodesInput!.value =
						objectDefinitionExternalReferenceCodes.join(',');

					console.error("Testing depot stuff");
				}
			},
			selectEventName,
			selectedData: objectDefinitionsSearchContainer.getData(true),
			title: sub(
				Liferay.Language.get('select-x'),
				Liferay.Language.get('object-definition')
			),
			url: objectDefinitionSelectorURL,
		});
	};

	const onSelectSiteClick = () => {
		const groupIds = getSearchContainerData(groupsSearchContainer);

		openSelectionModal({
			onSelect: (selectedItem: SelectedItem) => {
				if (selectedItem) {
					console.error(selectedItem);
					const {
						groupdescriptivename: entityName,
						groupid: entityId,
						hasvirtualhost: hasVirtualHost,
					} = selectedItem;
					const rowColumns = [];

					const title = sub(
						Liferay.Language.get('remove-x'),
						entityName
					);

					const sitesIcon = Liferay.Util.getLexiconIconTpl(
						'sites',
						'c-ml-2 text-secondary text-4'
					);

					const removeIcon =
						Liferay.Util.getLexiconIconTpl('times-circle');

					let siteName;

					if (hasVirtualHost === 'true') {
						const warningIcon = Liferay.Util.getLexiconIconTpl(
							'warning-full',
							'text-warning'
						);

						const warningTitle = Liferay.Language.get(
							'this-site-is-not-included-in-the-companys-xml-sitemap-because-it-already-has-a-virtual-host'
						);

						siteName = `<span class="text-truncate">
							${entityName}
							<span
								class="c-ml-2 d-inline lfr-portal-tooltip"
								title="${warningTitle}"
							>
								${warningIcon}
							</span>
						</span>`;
					}
					else {
						siteName = `<span class="text-truncate">${entityName}</span>`;
					}

					const removeButton = `<button
						aria-label="${title}"
						class="btn btn-monospaced btn-outline-borderless btn-outline-secondary
							btn-sm lfr-portal-tooltip remove-button text-secondary" 
						data-rowid="${entityId}" 
						type="button" 
						title="${title}"
					>
						<span class="inline-item">${removeIcon}</span>
					</button>`;

					rowColumns.push(sitesIcon);
					rowColumns.push(siteName);
					rowColumns.push(removeButton);

					groupsSearchContainer.addRow(rowColumns, entityId);
					groupsSearchContainer.updateDataStore();

					groupIds.push(entityId);
					groupIdsInput!.value = groupIds.join(',');
				}
			},
			selectEventName,
			selectedData: [groupIds],
			title: sub(
				Liferay.Language.get('select-x'),
				Liferay.Language.get('site')
			),
			url: groupSelectorURL,
		});
	};

	const onRemoveObjectDefinition =
		objectDefinitionsSearchContainerContentBox.delegate(
			'click',
			({currentTarget: removeButton}: {currentTarget: any}) => {
				objectDefinitionsSearchContainer.deleteRow(
					removeButton.ancestor('tr'),
					removeButton.attr('data-rowid')
				);

				const objectDefinitionExternalReferenceCodes =
					getSearchContainerData(objectDefinitionsSearchContainer);

				objectDefinitionExternalReferenceCodesInput.value =
					objectDefinitionExternalReferenceCodes.join(',');
			},
			'.remove-button'
		);

	const onRemoveSite = groupsSearchContainerContentBox.delegate(
		'click',
		({currentTarget: removeButton}: {currentTarget: any}) => {
			groupsSearchContainer.deleteRow(
				removeButton.ancestor('tr'),
				removeButton.attr('data-rowid')
			);

			const groupIds = getSearchContainerData(groupsSearchContainer);

			groupIdsInput.value = groupIds.join(',');
		},
		'.remove-button'
	);

	const selectObjectDefinitionDelegate = delegate(
		selectObjectDefinitionButton,
		'click',
		'.btn',
		onSelectObjectDefinitionClick
	);

	const selectSiteDelegate = delegate(
		selectSiteButton,
		'click',
		'.btn',
		onSelectSiteClick
	);

	return {
		dispose() {
			onRemoveObjectDefinition.detach();
			onRemoveSite.detach();
			selectObjectDefinitionDelegate.dispose();
			selectSiteDelegate.dispose();
		},
	};
}
