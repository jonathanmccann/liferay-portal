/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.seo.studio.web.internal.scan;

import com.liferay.object.constants.ObjectEntryFolderConstants;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.object.service.ObjectFieldLocalService;
import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.text.Format;

import java.util.Date;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jonathan McCann
 */
@Component(service = SEOStudioScanCreator.class)
public class SEOStudioScanCreator {

	public void createScans(
			Date scheduledScanDate, long seoStudioDomainId, String triggeredBy,
			long userId)
		throws Exception {

		ObjectEntry objectEntry = _objectEntryLocalService.getObjectEntry(
			seoStudioDomainId);

		Map<String, Serializable> values = objectEntry.getValues();

		String scanConfigJSON = GetterUtil.getString(values.get("scanConfig"));

		if (Validator.isNull(scanConfigJSON)) {
			return;
		}

		JSONObject scanConfigJSONObject = _jsonFactory.createJSONObject(
			scanConfigJSON);

		JSONObject enginesJSONObject = scanConfigJSONObject.getJSONObject(
			"engines");

		if (enginesJSONObject == null) {
			return;
		}

		long companyId = objectEntry.getCompanyId();

		ObjectDefinition objectDefinition =
			_objectDefinitionLocalService.
				getObjectDefinitionByExternalReferenceCode(
					"L_SEO_STUDIO_SCAN", companyId);

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCompanyId(companyId);
		serviceContext.setUserId(userId);

		for (String engineKey : enginesJSONObject.keySet()) {
			JSONObject engineJSONObject = enginesJSONObject.getJSONObject(
				engineKey);

			if ((engineJSONObject == null) ||
				!engineJSONObject.getBoolean("enabled")) {

				continue;
			}

			String scanKey = null;

			if (scheduledScanDate != null) {
				scanKey = _getScanKey(
					engineKey, scheduledScanDate, seoStudioDomainId);

				if (_hasScan(objectDefinition, scanKey)) {
					continue;
				}
			}

			_objectEntryLocalService.addObjectEntry(
				0, userId, objectDefinition.getObjectDefinitionId(),
				ObjectEntryFolderConstants.
					PARENT_OBJECT_ENTRY_FOLDER_ID_DEFAULT,
				null,
				HashMapBuilder.<String, Serializable>put(
					"name",
					engineKey + " - " +
						GetterUtil.getString(values.get("hostname"))
				).put(
					"r_accountToSEOStudioScans_accountEntryId",
					GetterUtil.getLong(
						values.get(
							"r_accountToSEOStudioDomains_accountEntryId"))
				).put(
					"r_seoStudioDomainToSEOStudioScans_seoStudioDomainId",
					seoStudioDomainId
				).put(
					"requestDate", new Date()
				).put(
					"scanKey", scanKey
				).put(
					"scanRange", "full"
				).put(
					"scanScope", "entireDomain"
				).put(
					"scanType", engineKey
				).put(
					"scopeConfig",
					() -> {
						JSONObject scopeConfigJSONObject =
							_jsonFactory.createJSONObject(
								engineJSONObject.toString());

						scopeConfigJSONObject.remove("enabled");

						return scopeConfigJSONObject.toString();
					}
				).put(
					"triggeredBy", triggeredBy
				).put(
					"triggeringUserId", userId
				).build(),
				serviceContext);
		}
	}

	public void createScans(
			long seoStudioDomainId, String triggeredBy, long userId)
		throws Exception {

		createScans(null, seoStudioDomainId, triggeredBy, userId);
	}

	private String _getScanKey(
		String engineKey, Date scheduledScanDate, long seoStudioDomainId) {

		Format format = FastDateFormatFactoryUtil.getSimpleDateFormat(
			"yyyyMMddHHmm", TimeZoneUtil.getTimeZone("UTC"));

		return StringBundler.concat(
			seoStudioDomainId, StringPool.UNDERLINE, engineKey,
			StringPool.UNDERLINE, format.format(scheduledScanDate));
	}

	private boolean _hasScan(ObjectDefinition objectDefinition, String scanKey)
		throws Exception {

		Column<?, String> column =
			(Column<?, String>)_objectFieldLocalService.getColumn(
				objectDefinition.getObjectDefinitionId(), "scanKey");

		long objectEntriesCount =
			_objectEntryLocalService.getObjectEntriesCount(
				0, null, objectDefinition, column.eq(scanKey));

		if (objectEntriesCount > 0) {
			return true;
		}

		return false;
	}

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private ObjectDefinitionLocalService _objectDefinitionLocalService;

	@Reference
	private ObjectEntryLocalService _objectEntryLocalService;

	@Reference
	private ObjectFieldLocalService _objectFieldLocalService;

}