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

package com.liferay.exportimport.internal.content.processor;

import com.liferay.document.library.kernel.exception.NoSuchFileEntryException;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.exportimport.configuration.ExportImportServiceConfiguration;
import com.liferay.exportimport.content.processor.ExportImportContentProcessor;
import com.liferay.exportimport.kernel.lar.ExportImportPathUtil;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.PortletDataHandlerKeys;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.NoSuchLayoutException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutFriendlyURL;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.model.StagedModel;
import com.liferay.portal.kernel.model.VirtualHost;
import com.liferay.portal.kernel.model.VirtualLayoutConstants;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.LayoutFriendlyURLLocalService;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.LayoutSetLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.VirtualHostLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.util.PropsValues;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Daniel Kocsis
 */
@Component(
	configurationPid = "com.liferay.exportimport.configuration.ExportImportServiceConfiguration",
	immediate = true, property = {"model.class.name=java.lang.String"},
	service = ExportImportContentProcessor.class
)
public class DefaultTextExportImportContentProcessor
	implements ExportImportContentProcessor<String> {

	public DefaultTextExportImportContentProcessor() {
	}

	@Override
	public String replaceExportContentReferences(
			PortletDataContext portletDataContext, StagedModel stagedModel,
			String content, boolean exportReferencedContent,
			boolean escapeContent)
		throws Exception {

		content = replaceExportDLReferences(
			portletDataContext, stagedModel, content, exportReferencedContent);

		content = replaceExportLayoutReferences(
			portletDataContext, stagedModel, content);

		content = replaceExportLinksToLayouts(
			portletDataContext, stagedModel, content);

		if (escapeContent) {
			content = StringUtil.replace(
				content, StringPool.AMPERSAND_ENCODED, StringPool.AMPERSAND);
		}

		return content;
	}

	@Override
	public String replaceImportContentReferences(
			PortletDataContext portletDataContext, StagedModel stagedModel,
			String content)
		throws Exception {

		content = replaceImportDLReferences(
			portletDataContext, stagedModel, content);

		content = replaceImportLayoutReferences(portletDataContext, content);
		content = replaceImportLinksToLayouts(portletDataContext, content);

		return content;
	}

	@Override
	public void validateContentReferences(long groupId, String content)
		throws PortalException {

		validateDLReferences(groupId, content);
		validateLayoutReferences(groupId, content);
		validateLinksToLayoutsReferences(content);
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_exportImportServiceConfiguration = ConfigurableUtil.createConfigurable(
			ExportImportServiceConfiguration.class, properties);
	}

	protected void deleteTimestampParameters(StringBuilder sb, int beginPos) {
		beginPos = sb.indexOf(StringPool.CLOSE_BRACKET, beginPos);

		if ((beginPos == -1) || (beginPos == (sb.length() - 1)) ||
			(sb.charAt(beginPos + 1) != CharPool.QUESTION)) {

			return;
		}

		int endPos = StringUtil.indexOfAny(
			sb.toString(), _DL_REFERENCE_LEGACY_STOP_CHARS, beginPos + 2);

		if (endPos == -1) {
			return;
		}

		String urlParams = sb.substring(beginPos + 1, endPos);

		urlParams = _http.removeParameter(urlParams, "t");

		sb.replace(beginPos + 1, endPos, urlParams);
	}

	protected Map<String, String[]> getDLReferenceParameters(
		long groupId, String content, int beginPos, int endPos) {

		boolean legacyURL = true;
		char[] stopChars = _DL_REFERENCE_LEGACY_STOP_CHARS;

		if (content.startsWith("/documents/", beginPos)) {
			legacyURL = false;
			stopChars = _DL_REFERENCE_STOP_CHARS;
		}

		endPos = StringUtil.indexOfAny(content, stopChars, beginPos, endPos);

		if (endPos == -1) {
			return null;
		}

		Map<String, String[]> map = new HashMap<>();

		String dlReference = content.substring(beginPos, endPos);

		while (dlReference.contains(StringPool.AMPERSAND_ENCODED)) {
			dlReference = dlReference.replace(
				StringPool.AMPERSAND_ENCODED, StringPool.AMPERSAND);
		}

		if (!legacyURL) {
			String[] pathArray = dlReference.split(StringPool.SLASH);

			if (pathArray.length < 3) {
				return map;
			}

			map.put("groupId", new String[] {pathArray[2]});

			if (pathArray.length == 4) {
				map.put("uuid", new String[] {pathArray[3]});
			}
			else if (pathArray.length == 5) {
				map.put("folderId", new String[] {pathArray[3]});
				map.put("title", new String[] {_http.decodeURL(pathArray[4])});
			}
			else if (pathArray.length > 5) {
				map.put("uuid", new String[] {pathArray[5]});
			}
		}
		else {
			dlReference = dlReference.substring(
				dlReference.indexOf(CharPool.QUESTION) + 1);

			map = _http.parameterMapFromString(dlReference);

			String[] imageIds = null;

			if (map.containsKey("img_id")) {
				imageIds = map.get("img_id");
			}
			else if (map.containsKey("i_id")) {
				imageIds = map.get("i_id");
			}

			imageIds = ArrayUtil.filter(imageIds, Validator::isNotNull);

			if (ArrayUtil.isNotEmpty(imageIds)) {
				map.put("image_id", imageIds);
			}
		}

		map.put("endPos", new String[] {String.valueOf(endPos)});

		String groupIdString = MapUtil.getString(map, "groupId");

		if (groupIdString.equals("@group_id@")) {
			groupIdString = String.valueOf(groupId);

			map.put("groupId", new String[] {groupIdString});
		}

		return map;
	}

	protected FileEntry getFileEntry(Map<String, String[]> map) {
		if (MapUtil.isEmpty(map)) {
			return null;
		}

		FileEntry fileEntry = null;

		try {
			String uuid = MapUtil.getString(map, "uuid");
			long groupId = MapUtil.getLong(map, "groupId");

			if (Validator.isNotNull(uuid)) {
				fileEntry = _dlAppLocalService.getFileEntryByUuidAndGroupId(
					uuid, groupId);
			}
			else {
				if (map.containsKey("folderId")) {
					long folderId = MapUtil.getLong(map, "folderId");
					String name = MapUtil.getString(map, "name");
					String title = MapUtil.getString(map, "title");

					if (Validator.isNotNull(title)) {
						fileEntry = _dlAppLocalService.getFileEntry(
							groupId, folderId, title);
					}
					else {
						DLFileEntry dlFileEntry =
							_dlFileEntryLocalService.fetchFileEntryByName(
								groupId, folderId, name);

						if (dlFileEntry != null) {
							fileEntry = _dlAppLocalService.getFileEntry(
								dlFileEntry.getFileEntryId());
						}
					}
				}
				else if (map.containsKey("image_id")) {
					DLFileEntry dlFileEntry =
						_dlFileEntryLocalService.fetchFileEntryByAnyImageId(
							MapUtil.getLong(map, "image_id"));

					if (dlFileEntry != null) {
						fileEntry = _dlAppLocalService.getFileEntry(
							dlFileEntry.getFileEntryId());
					}
				}
			}
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
			else if (_log.isWarnEnabled()) {
				_log.warn(e.getMessage());
			}
		}

		return fileEntry;
	}

	protected String replaceExportDLReferences(
			PortletDataContext portletDataContext, StagedModel stagedModel,
			String content, boolean exportReferencedContent)
		throws Exception {

		Group group = _groupLocalService.getGroup(
			portletDataContext.getGroupId());

		if (group.isStagingGroup()) {
			group = group.getLiveGroup();
		}

		if (group.isStaged() && !group.isStagedRemotely() &&
			!group.isStagedPortlet(PortletKeys.DOCUMENT_LIBRARY)) {

			return content;
		}

		StringBuilder sb = new StringBuilder(content);

		String contextPath = _portal.getPathContext();

		String[] patterns = {
			contextPath.concat("/c/document_library/get_file?"),
			contextPath.concat("/documents/"),
			contextPath.concat("/image/image_gallery?")
		};

		int beginPos = -1;
		int endPos = content.length();

		while (true) {
			beginPos = StringUtil.lastIndexOfAny(content, patterns, endPos);

			if (beginPos == -1) {
				break;
			}

			Map<String, String[]> dlReferenceParameters =
				getDLReferenceParameters(
					portletDataContext.getScopeGroupId(), content,
					beginPos + contextPath.length(), endPos);

			FileEntry fileEntry = getFileEntry(dlReferenceParameters);

			if (fileEntry == null) {
				endPos = beginPos - 1;

				continue;
			}

			endPos = MapUtil.getInteger(dlReferenceParameters, "endPos");

			try {
				if (exportReferencedContent) {
					StagedModelDataHandlerUtil.exportReferenceStagedModel(
						portletDataContext, stagedModel, fileEntry,
						PortletDataContext.REFERENCE_TYPE_DEPENDENCY);
				}
				else {
					Element entityElement =
						portletDataContext.getExportDataElement(stagedModel);

					portletDataContext.addReferenceElement(
						stagedModel, entityElement, fileEntry,
						PortletDataContext.REFERENCE_TYPE_DEPENDENCY, true);
				}

				String path = ExportImportPathUtil.getModelPath(fileEntry);

				sb.replace(beginPos, endPos, "[$dl-reference=" + path + "$]");

				deleteTimestampParameters(sb, beginPos);
			}
			catch (Exception e) {
				if (_log.isDebugEnabled()) {
					_log.debug(e, e);
				}
				else if (_log.isWarnEnabled()) {
					StringBundler exceptionSB = new StringBundler(6);

					exceptionSB.append("Unable to process file entry ");
					exceptionSB.append(fileEntry.getFileEntryId());
					exceptionSB.append(" for staged model ");
					exceptionSB.append(stagedModel.getModelClassName());
					exceptionSB.append(" with primary key ");
					exceptionSB.append(stagedModel.getPrimaryKeyObj());

					_log.warn(exceptionSB.toString());
				}
			}

			endPos = beginPos - 1;
		}

		return sb.toString();
	}

	protected String replaceExportHostname(
			long groupId, String url, StringBundler urlSB)
		throws PortalException {

		return _extractVirtualHostFromURL(url, urlSB).getValue();
	}

	protected String replaceExportLayoutReferences(
			PortletDataContext portletDataContext, StagedModel stagedModel,
			String content)
		throws Exception {

		Group group = _groupLocalService.getGroup(
			portletDataContext.getScopeGroupId());

		StringBuilder sb = new StringBuilder(content);

		String[] patterns = {"href=", "[["};

		int beginPos = -1;
		int endPos = content.length();
		int offset = 0;

		while (true) {
			if (beginPos > -1) {
				endPos = beginPos - 1;
			}

			beginPos = StringUtil.lastIndexOfAny(content, patterns, endPos);

			if (beginPos == -1) {
				break;
			}

			if (content.startsWith("href=", beginPos)) {
				offset = 5;

				char c = content.charAt(beginPos + offset);

				if ((c == CharPool.APOSTROPHE) || (c == CharPool.QUOTE)) {
					offset++;
				}
			}
			else if (content.charAt(beginPos) == CharPool.OPEN_BRACKET) {
				offset = 2;
			}

			endPos = StringUtil.indexOfAny(
				content, _LAYOUT_REFERENCE_STOP_CHARS, beginPos + offset,
				endPos);

			if (endPos == -1) {
				continue;
			}

			String url = content.substring(beginPos + offset, endPos);

			ObjectValuePair<Layout, String> ovp = null;

			try {
				ovp = _getLayoutFromURL(url, group);

				Layout layout = ovp.getKey();

				if (layout != null) {
					Element entityElement =
						portletDataContext.getExportDataElement(stagedModel);

					portletDataContext.addReferenceElement(
						stagedModel, entityElement, layout,
						PortletDataContext.REFERENCE_TYPE_DEPENDENCY, true);
				}
			}
			catch (Exception e) {
				if (e instanceof NoSuchLayoutException &&
					!_exportImportServiceConfiguration.
						validateLayoutReferences()) {

					continue;
				}

				if (_log.isDebugEnabled()) {
					_log.debug(e, e);
				}
				else if (_log.isWarnEnabled()) {
					StringBundler exceptionSB = new StringBundler(6);

					exceptionSB.append("Unable to process layout URL ");
					exceptionSB.append(url);
					exceptionSB.append(" for staged model ");
					exceptionSB.append(stagedModel.getModelClassName());
					exceptionSB.append(" with primary key ");
					exceptionSB.append(stagedModel.getPrimaryKeyObj());

					_log.warn(exceptionSB.toString());
				}
			}
			finally {
				if (ovp != null) {
					sb.replace(beginPos + offset, endPos, ovp.getValue());
				}
			}
		}

		return sb.toString();
	}

	protected String replaceExportLinksToLayouts(
			PortletDataContext portletDataContext, StagedModel stagedModel,
			String content)
		throws Exception {

		List<String> oldLinksToLayout = new ArrayList<>();
		List<String> newLinksToLayout = new ArrayList<>();

		Matcher matcher = _exportLinksToLayoutPattern.matcher(content);

		while (matcher.find()) {
			long layoutId = GetterUtil.getLong(matcher.group(1));

			String type = matcher.group(2);

			boolean privateLayout = type.startsWith("private");

			try {
				Layout layout = _layoutLocalService.getLayout(
					portletDataContext.getScopeGroupId(), privateLayout,
					layoutId);

				String oldLinkToLayout = matcher.group(0);

				StringBundler sb = new StringBundler(3);

				sb.append(type);
				sb.append(StringPool.AT);
				sb.append(layout.getPlid());

				String newLinkToLayout = StringUtil.replace(
					oldLinkToLayout, type, sb.toString());

				oldLinksToLayout.add(oldLinkToLayout);
				newLinksToLayout.add(newLinkToLayout);

				Element entityElement = portletDataContext.getExportDataElement(
					stagedModel);

				portletDataContext.addReferenceElement(
					stagedModel, entityElement, layout,
					PortletDataContext.REFERENCE_TYPE_DEPENDENCY, true);
			}
			catch (Exception e) {
				if (_log.isDebugEnabled() || _log.isWarnEnabled()) {
					String message =
						"Unable to get layout with ID " + layoutId +
							" in group " + portletDataContext.getScopeGroupId();

					if (_log.isDebugEnabled()) {
						_log.debug(message, e);
					}
					else {
						_log.warn(message);
					}
				}
			}
		}

		content = StringUtil.replace(
			content, ArrayUtil.toStringArray(oldLinksToLayout.toArray()),
			ArrayUtil.toStringArray(newLinksToLayout.toArray()));

		return content;
	}

	protected String replaceImportDLReferences(
			PortletDataContext portletDataContext, StagedModel stagedModel,
			String content)
		throws Exception {

		List<Element> referenceElements =
			portletDataContext.getReferenceElements(
				stagedModel, DLFileEntry.class);

		for (Element referenceElement : referenceElements) {
			Long classPK = GetterUtil.getLong(
				referenceElement.attributeValue("class-pk"));

			Element referenceDataElement =
				portletDataContext.getReferenceDataElement(
					stagedModel, DLFileEntry.class, classPK);

			String path = null;

			if (referenceDataElement != null) {
				path = referenceDataElement.attributeValue("path");
			}

			long groupId = GetterUtil.getLong(
				referenceElement.attributeValue("group-id"));

			if (Validator.isNull(path)) {
				String className = referenceElement.attributeValue(
					"class-name");

				path = ExportImportPathUtil.getModelPath(
					groupId, className, classPK);
			}

			if (!content.contains("[$dl-reference=" + path + "$]")) {
				continue;
			}

			try {
				StagedModelDataHandlerUtil.importReferenceStagedModel(
					portletDataContext, stagedModel, DLFileEntry.class,
					classPK);
			}
			catch (Exception e) {
				if (_log.isDebugEnabled()) {
					_log.debug(e, e);
				}
				else if (_log.isWarnEnabled()) {
					StringBundler sb = new StringBundler(6);

					sb.append("Unable to process file entry ");
					sb.append(classPK);
					sb.append(" for ");
					sb.append(stagedModel.getModelClassName());
					sb.append(" with primary key ");
					sb.append(stagedModel.getPrimaryKeyObj());

					_log.warn(sb.toString());
				}
			}

			Map<Long, Long> dlFileEntryIds =
				(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
					DLFileEntry.class);

			long fileEntryId = MapUtil.getLong(
				dlFileEntryIds, classPK, classPK);

			FileEntry importedFileEntry = null;

			try {
				importedFileEntry = _dlAppLocalService.getFileEntry(
					fileEntryId);
			}
			catch (PortalException pe) {
				if (_log.isDebugEnabled()) {
					_log.debug(pe, pe);
				}
				else if (_log.isWarnEnabled()) {
					_log.warn(pe.getMessage());
				}

				continue;
			}

			String url = DLUtil.getPreviewURL(
				importedFileEntry, importedFileEntry.getFileVersion(), null,
				StringPool.BLANK, false, false);

			if (url.contains(StringPool.QUESTION)) {
				content = StringUtil.replace(content, "$]?", "$]&");
			}

			content = StringUtil.replace(
				content, "[$dl-reference=" + path + "$]", url);
		}

		return content;
	}

	protected String replaceImportLayoutReferences(
			PortletDataContext portletDataContext, String content)
		throws Exception {

		String companyPortalURL = StringPool.BLANK;
		String privateLayoutSetPortalURL = StringPool.BLANK;
		String publicLayoutSetPortalURL = StringPool.BLANK;

		Group group = _groupLocalService.getGroup(
			portletDataContext.getScopeGroupId());

		Company company = _companyLocalService.getCompany(group.getCompanyId());

		LayoutSet privateLayoutSet = group.getPrivateLayoutSet();
		LayoutSet publicLayoutSet = group.getPublicLayoutSet();

		int serverPort = _portal.getPortalServerPort(false);

		if (serverPort != -1) {
			if (Validator.isNotNull(company.getVirtualHostname())) {
				companyPortalURL = _portal.getPortalURL(
					company.getVirtualHostname(), serverPort, false);
			}

			if (Validator.isNotNull(privateLayoutSet.getVirtualHostname())) {
				privateLayoutSetPortalURL = _portal.getPortalURL(
					privateLayoutSet.getVirtualHostname(), serverPort, false);
			}

			if (Validator.isNotNull(publicLayoutSet.getVirtualHostname())) {
				publicLayoutSetPortalURL = _portal.getPortalURL(
					publicLayoutSet.getVirtualHostname(), serverPort, false);
			}
		}

		int secureSecurePort = _portal.getPortalServerPort(true);

		String companySecurePortalURL = StringPool.BLANK;
		String privateLayoutSetSecurePortalURL = StringPool.BLANK;
		String publicLayoutSetSecurePortalURL = StringPool.BLANK;

		if (secureSecurePort != -1) {
			if (Validator.isNotNull(company.getVirtualHostname())) {
				companySecurePortalURL = _portal.getPortalURL(
					company.getVirtualHostname(), secureSecurePort, true);
			}

			if (Validator.isNotNull(privateLayoutSet.getVirtualHostname())) {
				privateLayoutSetSecurePortalURL = _portal.getPortalURL(
					privateLayoutSet.getVirtualHostname(), secureSecurePort,
					true);
			}

			if (Validator.isNotNull(publicLayoutSet.getVirtualHostname())) {
				publicLayoutSetSecurePortalURL = _portal.getPortalURL(
					publicLayoutSet.getVirtualHostname(), secureSecurePort,
					true);
			}
		}

		content = StringUtil.replace(
			content, _DATA_HANDLER_COMPANY_ADMIN_URL, _companyAdminURL);
		content = StringUtil.replace(
			content, _DATA_HANDLER_COMPANY_SECURE_URL, companySecurePortalURL);
		content = StringUtil.replace(
			content, _DATA_HANDLER_COMPANY_URL, companyPortalURL);
		content = StringUtil.replace(
			content, _DATA_HANDLER_GROUP_FRIENDLY_URL, group.getFriendlyURL());
		content = StringUtil.replace(
			content, _DATA_HANDLER_PATH_CONTEXT, _portal.getPathContext());
		content = StringUtil.replace(
			content, _DATA_HANDLER_PRIVATE_GROUP_SERVLET_MAPPING,
			PropsValues.LAYOUT_FRIENDLY_URL_PRIVATE_GROUP_SERVLET_MAPPING);
		content = StringUtil.replace(
			content, _DATA_HANDLER_PRIVATE_LAYOUT_SET_SECURE_URL,
			privateLayoutSetSecurePortalURL);
		content = StringUtil.replace(
			content, _DATA_HANDLER_PRIVATE_LAYOUT_SET_URL,
			privateLayoutSetPortalURL);
		content = StringUtil.replace(
			content, _DATA_HANDLER_PRIVATE_USER_SERVLET_MAPPING,
			PropsValues.LAYOUT_FRIENDLY_URL_PRIVATE_USER_SERVLET_MAPPING);
		content = StringUtil.replace(
			content, _DATA_HANDLER_PUBLIC_LAYOUT_SET_SECURE_URL,
			publicLayoutSetSecurePortalURL);
		content = StringUtil.replace(
			content, _DATA_HANDLER_PUBLIC_LAYOUT_SET_URL,
			publicLayoutSetPortalURL);
		content = StringUtil.replace(
			content, _DATA_HANDLER_PUBLIC_SERVLET_MAPPING,
			PropsValues.LAYOUT_FRIENDLY_URL_PUBLIC_SERVLET_MAPPING);
		content = StringUtil.replace(
			content, _DATA_HANDLER_SITE_ADMIN_URL, _siteAdminURL);

		return content;
	}

	protected String replaceImportLinksToLayouts(
			PortletDataContext portletDataContext, String content)
		throws Exception {

		List<String> oldLinksToLayout = new ArrayList<>();
		List<String> newLinksToLayout = new ArrayList<>();

		Matcher matcher = _importLinksToLayoutPattern.matcher(content);

		Map<Long, Long> layoutPlids =
			(Map<Long, Long>)portletDataContext.getNewPrimaryKeysMap(
				Layout.class);

		String layoutsImportMode = MapUtil.getString(
			portletDataContext.getParameterMap(),
			PortletDataHandlerKeys.LAYOUTS_IMPORT_MODE,
			PortletDataHandlerKeys.LAYOUTS_IMPORT_MODE_MERGE_BY_LAYOUT_UUID);

		while (matcher.find()) {
			long oldPlid = GetterUtil.getLong(matcher.group(4));

			Long newPlid = MapUtil.getLong(layoutPlids, oldPlid);

			long oldGroupId = GetterUtil.getLong(matcher.group(6));

			long newGroupId = oldGroupId;

			long oldLayoutId = GetterUtil.getLong(matcher.group(1));

			long newLayoutId = oldLayoutId;

			Layout layout = _layoutLocalService.fetchLayout(newPlid);

			if (layout != null) {
				newGroupId = layout.getGroupId();
				newLayoutId = layout.getLayoutId();
			}
			else if (_log.isWarnEnabled()) {
				_log.warn("Unable to get layout with plid " + oldPlid);
			}

			String oldLinkToLayout = matcher.group(0);

			String newLinkToLayout = StringUtil.replaceFirst(
				oldLinkToLayout,
				new String[] {
					StringPool.AT + oldPlid, String.valueOf(oldLayoutId)
				},
				new String[] {StringPool.BLANK, String.valueOf(newLayoutId)});

			if ((layout != null) && layout.isPublicLayout() &&
				layoutsImportMode.equals(
					PortletDataHandlerKeys.
						LAYOUTS_IMPORT_MODE_CREATED_FROM_PROTOTYPE)) {

				newLinkToLayout = StringUtil.replace(
					newLinkToLayout, "private-group", "public");
			}

			if ((oldGroupId != 0) && (oldGroupId != newGroupId)) {
				newLinkToLayout = StringUtil.replaceLast(
					newLinkToLayout, String.valueOf(oldGroupId),
					String.valueOf(newGroupId));
			}

			oldLinksToLayout.add(oldLinkToLayout);
			newLinksToLayout.add(newLinkToLayout);
		}

		content = StringUtil.replace(
			content, ArrayUtil.toStringArray(oldLinksToLayout.toArray()),
			ArrayUtil.toStringArray(newLinksToLayout.toArray()));

		return content;
	}

	protected void validateDLReferences(long groupId, String content)
		throws PortalException {

		String portalURL = _portal.getPathContext();

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		if ((serviceContext != null) &&
			(serviceContext.getThemeDisplay() != null)) {

			ThemeDisplay themeDisplay = serviceContext.getThemeDisplay();

			portalURL =
				_portal.getPortalURL(themeDisplay) + _portal.getPathContext();
		}

		String[] patterns = {
			portalURL.concat("/c/document_library/get_file?"),
			portalURL.concat("/documents/"),
			portalURL.concat("/image/image_gallery?")
		};

		String[] completePatterns = new String[patterns.length];

		long[] companyIds = _portal.getCompanyIds();

		for (long companyId : companyIds) {
			Company company = _companyLocalService.getCompany(companyId);

			String webId = company.getWebId();

			int i = 0;

			for (String pattern : patterns) {
				completePatterns[i] = webId.concat(pattern);

				i++;
			}

			int beginPos = -1;
			int endPos = content.length();

			while (true) {
				beginPos = StringUtil.lastIndexOfAny(
					content, completePatterns, endPos);

				if (beginPos == -1) {
					break;
				}

				Map<String, String[]> dlReferenceParameters =
					getDLReferenceParameters(
						groupId, content,
						beginPos + portalURL.length() + webId.length(), endPos);

				FileEntry fileEntry = getFileEntry(dlReferenceParameters);

				if (fileEntry == null) {
					StringBundler sb = new StringBundler(4);

					sb.append("Validation failed for a referenced file entry ");
					sb.append("because a file entry could not be found with ");
					sb.append("the following parameters: ");
					sb.append(dlReferenceParameters);

					throw new NoSuchFileEntryException(sb.toString());
				}

				endPos = beginPos - 1;
			}
		}
	}

	protected void validateLayoutReferences(long groupId, String content)
		throws PortalException {

		if (!_exportImportServiceConfiguration.validateLayoutReferences()) {
			return;
		}

		Group group = _groupLocalService.getGroup(groupId);

		String[] patterns = {"href=", "[["};

		int beginPos = -1;
		int endPos = content.length();
		int offset = 0;

		while (true) {
			if (beginPos > -1) {
				endPos = beginPos - 1;
			}

			beginPos = StringUtil.lastIndexOfAny(content, patterns, endPos);

			if (beginPos == -1) {
				break;
			}

			if (content.startsWith("href=", beginPos)) {
				offset = 5;

				char c = content.charAt(beginPos + offset);

				if ((c == CharPool.APOSTROPHE) || (c == CharPool.QUOTE)) {
					offset++;
				}
			}
			else if (content.charAt(beginPos) == CharPool.OPEN_BRACKET) {
				offset = 2;
			}

			endPos = StringUtil.indexOfAny(
				content, _LAYOUT_REFERENCE_STOP_CHARS, beginPos + offset,
				endPos);

			if (endPos == -1) {
				continue;
			}

			String url = content.substring(beginPos + offset, endPos);

			_getLayoutFromURL(url, group);
		}
	}

	protected void validateLinksToLayoutsReferences(String content)
		throws PortalException {

		Matcher matcher = _exportLinksToLayoutPattern.matcher(content);

		while (matcher.find()) {
			long groupId = GetterUtil.getLong(matcher.group(5));

			String type = matcher.group(2);

			boolean privateLayout = type.startsWith("private");

			long layoutId = GetterUtil.getLong(matcher.group(1));

			Layout layout = _layoutLocalService.fetchLayout(
				groupId, privateLayout, layoutId);

			if (layout == null) {
				StringBundler sb = new StringBundler(8);

				sb.append("Unable to validate referenced page because it ");
				sb.append("cannot be found with the following parameters: ");
				sb.append("groupId ");
				sb.append(groupId);
				sb.append(", layoutId ");
				sb.append(layoutId);
				sb.append(", privateLayout ");
				sb.append(privateLayout);

				throw new NoSuchLayoutException(sb.toString());
			}
		}
	}

	private ObjectValuePair<VirtualHost, String> _extractVirtualHostFromURL(
			String url, StringBundler urlSB)
		throws PortalException {

		if (!_http.hasProtocol(url)) {
			return new ObjectValuePair<>(null, url);
		}

		boolean secure = _http.isSecure(url);

		int serverPort = _portal.getPortalServerPort(secure);

		if (serverPort == -1) {
			return new ObjectValuePair<>(null, url);
		}

		String portString = _getPortString(serverPort, secure);

		int virtualHostnameBeginPos =
			url.indexOf(Http.PROTOCOL_DELIMITER) +
				Http.PROTOCOL_DELIMITER.length();

		int virtualHostnameEndPos = -1;

		if (Validator.isNotNull(portString)) {
			virtualHostnameEndPos = url.indexOf(
				portString, virtualHostnameBeginPos);
		}
		else {
			virtualHostnameEndPos = url.indexOf(
				StringPool.SLASH, virtualHostnameBeginPos);
		}

		if (virtualHostnameEndPos <= virtualHostnameBeginPos) {
			return new ObjectValuePair<>(null, url);
		}

		String virtualHostname = url.substring(
			virtualHostnameBeginPos, virtualHostnameEndPos);

		VirtualHost virtualHost = _virtualHostLocalService.fetchVirtualHost(
			virtualHostname);

		if (virtualHost == null) {
			if (virtualHostname.equals(PropsValues.WEB_SERVER_HOST) ||
				virtualHostname.equals("localhost")) {

				Company company = _companyLocalService.getCompanyByWebId(
					PropsValues.COMPANY_DEFAULT_WEB_ID);

				virtualHost = _virtualHostLocalService.getVirtualHost(
					company.getCompanyId(), 0);
			}
		}

		if (virtualHost == null) {
			return new ObjectValuePair<>(null, url);
		}

		if (virtualHost.getLayoutSetId() == 0) {
			if (secure) {
				urlSB.append(_DATA_HANDLER_COMPANY_SECURE_URL);
			}
			else {
				urlSB.append(_DATA_HANDLER_COMPANY_URL);
			}
		}
		else {
			LayoutSet layoutSet = _layoutSetLocalService.getLayoutSet(
				virtualHost.getLayoutSetId());

			if (layoutSet.isPrivateLayout()) {
				if (secure) {
					urlSB.append(_DATA_HANDLER_PRIVATE_LAYOUT_SET_SECURE_URL);
				}
				else {
					urlSB.append(_DATA_HANDLER_PRIVATE_LAYOUT_SET_URL);
				}
			}
			else {
				if (secure) {
					urlSB.append(_DATA_HANDLER_PUBLIC_LAYOUT_SET_SECURE_URL);
				}
				else {
					urlSB.append(_DATA_HANDLER_PUBLIC_LAYOUT_SET_URL);
				}
			}
		}

		url = url.substring(virtualHostnameEndPos + portString.length());

		return new ObjectValuePair<>(virtualHost, url);
	}

	private ObjectValuePair<Layout, String> _getLayoutFromURL(
			String originalURL, Group group)
		throws PortalException {

		String url = originalURL;

		String urlTail = StringPool.BLANK;

		int endPos = url.indexOf(Portal.FRIENDLY_URL_SEPARATOR);

		if (endPos != -1) {
			urlTail = url.substring(endPos);

			url = url.substring(0, endPos);
		}

		if (url.endsWith(StringPool.SLASH)) {
			urlTail = StringPool.SLASH + urlTail;

			url = url.substring(0, url.length() - 1);
		}

		StringBundler urlSB = new StringBundler(7);

		url = replaceExportHostname(group.getGroupId(), url, urlSB);

		String pathContext = _portal.getPathContext();

		if (pathContext.length() > 1) {
			if (!url.startsWith(pathContext)) {
				return _getObjectValuePair(urlSB, url + urlTail, originalURL);
			}

			urlSB.append(_DATA_HANDLER_PATH_CONTEXT);

			url = url.substring(pathContext.length());
		}

		if (!url.startsWith(StringPool.SLASH)) {
			return _getObjectValuePair(urlSB, url + urlTail, originalURL);
		}

		int pos = url.indexOf(StringPool.SLASH, 1);

		String localePath = StringPool.BLANK;

		Locale locale = null;

		if (pos != -1) {
			localePath = url.substring(0, pos);

			locale = LocaleUtil.fromLanguageId(
				localePath.substring(1), true, false);
		}

		if (locale != null) {
			String urlWithoutLocale = url.substring(localePath.length());

			if (urlWithoutLocale.startsWith(_privateGroupServletMapping) ||
				urlWithoutLocale.startsWith(_privateUserServletMapping) ||
				urlWithoutLocale.startsWith(_publicGroupServletMapping)) {

				urlSB.append(localePath);

				url = urlWithoutLocale;
			}
		}

		boolean privateLayout = false;

		if (url.startsWith(_privateGroupServletMapping)) {
			urlSB.append(_DATA_HANDLER_PRIVATE_GROUP_SERVLET_MAPPING);

			url = url.substring(_privateGroupServletMapping.length() - 1);

			if (url.equals(_companyAdminURL)) {
				urlSB.append(_DATA_HANDLER_COMPANY_ADMIN_URL);

				return new ObjectValuePair<>(null, urlSB.toString());
			}

			privateLayout = true;
		}
		else if (url.startsWith(_privateUserServletMapping)) {
			urlSB.append(_DATA_HANDLER_PRIVATE_USER_SERVLET_MAPPING);

			url = url.substring(_privateUserServletMapping.length() - 1);

			privateLayout = true;
		}
		else if (url.startsWith(_publicGroupServletMapping)) {
			urlSB.append(_DATA_HANDLER_PUBLIC_SERVLET_MAPPING);

			url = url.substring(_publicGroupServletMapping.length() - 1);
		}
		else {
			String urlSBString = urlSB.toString();

			LayoutSet layoutSet = null;

			if (urlSBString.contains(
					_DATA_HANDLER_PUBLIC_LAYOUT_SET_SECURE_URL) ||
				urlSBString.contains(_DATA_HANDLER_PUBLIC_LAYOUT_SET_URL)) {

				layoutSet = group.getPublicLayoutSet();
			}
			else if (urlSBString.contains(
						_DATA_HANDLER_PRIVATE_LAYOUT_SET_SECURE_URL) ||
					 urlSBString.contains(
						 _DATA_HANDLER_PRIVATE_LAYOUT_SET_URL)) {

				layoutSet = group.getPrivateLayoutSet();
			}
			else {
				Group virtualHostsDefaultGroup = _groupLocalService.fetchGroup(
					group.getCompanyId(),
					PropsValues.VIRTUAL_HOSTS_DEFAULT_SITE_NAME);

				if (virtualHostsDefaultGroup != null) {
					layoutSet = _layoutSetLocalService.fetchLayoutSet(
						virtualHostsDefaultGroup.getGroupId(), false);
				}
			}

			if (layoutSet == null) {
				return _getObjectValuePair(urlSB, url + urlTail, originalURL);
			}

			privateLayout = layoutSet.isPrivateLayout();

			Group urlGroup = layoutSet.getGroup();

			LayoutFriendlyURL layoutFriendlyUrl =
				_layoutFriendlyURLLocalService.fetchFirstLayoutFriendlyURL(
					urlGroup.getGroupId(), privateLayout, url);

			if (layoutFriendlyUrl == null) {
				return _getObjectValuePair(urlSB, url + urlTail, originalURL);
			}

			if (privateLayout) {
				if (urlGroup.isUser()) {
					urlSB.append(_DATA_HANDLER_PRIVATE_USER_SERVLET_MAPPING);
				}
				else {
					urlSB.append(_DATA_HANDLER_PRIVATE_GROUP_SERVLET_MAPPING);
				}
			}
			else {
				urlSB.append(_DATA_HANDLER_PUBLIC_SERVLET_MAPPING);
			}

			urlSB.append(_DATA_HANDLER_GROUP_FRIENDLY_URL);
			urlSB.append(url);
			urlSB.append(urlTail);

			return new ObjectValuePair<>(null, urlSB.toString());
		}

		pos = url.indexOf(StringPool.SLASH, 1);

		String groupFriendlyURL = url;

		if (pos != -1) {
			groupFriendlyURL = url.substring(0, pos);
		}

		Group urlGroup = _groupLocalService.fetchFriendlyURLGroup(
			group.getCompanyId(), groupFriendlyURL);

		if (urlGroup == null) {
			StringBundler sb = new StringBundler(7);

			sb.append("Unable to validate referenced page from URL \"");
			sb.append(originalURL);
			sb.append("\" because no group could be found with friendly ");
			sb.append("URL \"");
			sb.append(groupFriendlyURL);
			sb.append("\" in company ");
			sb.append(group.getCompanyId());

			throw new NoSuchLayoutException(sb.toString());
		}

		urlSB.append(_DATA_HANDLER_GROUP_FRIENDLY_URL);

		if (pos == -1) {
			return new ObjectValuePair<>(null, urlSB.toString());
		}

		url = url.substring(pos);

		if (url.equals(_siteAdminURL)) {
			urlSB.append(_DATA_HANDLER_SITE_ADMIN_URL);

			return new ObjectValuePair<>(null, urlSB.toString());
		}

		Layout layout = _layoutLocalService.fetchLayoutByFriendlyURL(
			urlGroup.getGroupId(), privateLayout, url);

		if (layout == null) {
			StringBundler sb = new StringBundler(8);

			sb.append("Unable to validate referenced page from URL \"");
			sb.append(originalURL);
			sb.append("\" because no ");

			if (privateLayout) {
				sb.append("private ");
			}
			else {
				sb.append("public ");
			}

			sb.append("layout could be found with friendly URL \"");
			sb.append(url);
			sb.append("\" in group ");
			sb.append(urlGroup.getGroupId());

			throw new NoSuchLayoutException(sb.toString());
		}

		urlSB.append(url);
		urlSB.append(urlTail);

		return new ObjectValuePair<>(layout, urlSB.toString());
	}

	private ObjectValuePair<Layout, String> _getObjectValuePair(
		StringBundler urlSB, String urlTail, String originalURL) {

		if (urlSB.length() > 0) {
			urlSB.append(urlTail);

			return new ObjectValuePair<>(null, urlSB.toString());
		}

		return new ObjectValuePair<>(null, originalURL);
	}

	private String _getPortString(int serverPort, boolean secure) {
		boolean https = false;

		if (secure ||
			StringUtil.equalsIgnoreCase(
				Http.HTTPS, PropsValues.WEB_SERVER_PROTOCOL)) {

			https = true;
		}

		if (!https) {
			if (PropsValues.WEB_SERVER_HTTP_PORT == -1) {
				if ((serverPort != Http.HTTP_PORT) &&
					(serverPort != Http.HTTPS_PORT)) {

					return StringPool.COLON + String.valueOf(serverPort);
				}
			}
			else if (PropsValues.WEB_SERVER_HTTP_PORT != Http.HTTP_PORT) {
				return StringPool.COLON +
					String.valueOf(PropsValues.WEB_SERVER_HTTP_PORT);
			}
		}
		else {
			if (PropsValues.WEB_SERVER_HTTPS_PORT == -1) {
				if ((serverPort != Http.HTTP_PORT) &&
					(serverPort != Http.HTTPS_PORT)) {

					return StringPool.COLON + String.valueOf(serverPort);
				}
			}
			else if (PropsValues.WEB_SERVER_HTTPS_PORT != Http.HTTPS_PORT) {
				return StringPool.COLON +
					String.valueOf(PropsValues.WEB_SERVER_HTTPS_PORT);
			}
		}

		return StringPool.BLANK;
	}

	private static final String _DATA_HANDLER_COMPANY_ADMIN_URL =
		"@data_handler_company_admin_url@";

	private static final String _DATA_HANDLER_COMPANY_SECURE_URL =
		"@data_handler_company_secure_url@";

	private static final String _DATA_HANDLER_COMPANY_URL =
		"@data_handler_company_url@";

	private static final String _DATA_HANDLER_GROUP_FRIENDLY_URL =
		"@data_handler_group_friendly_url@";

	private static final String _DATA_HANDLER_PATH_CONTEXT =
		"@data_handler_path_context@";

	private static final String _DATA_HANDLER_PRIVATE_GROUP_SERVLET_MAPPING =
		"@data_handler_private_group_servlet_mapping@";

	private static final String _DATA_HANDLER_PRIVATE_LAYOUT_SET_SECURE_URL =
		"@data_handler_private_layout_set_secure_url@";

	private static final String _DATA_HANDLER_PRIVATE_LAYOUT_SET_URL =
		"@data_handler_private_layout_set_url@";

	private static final String _DATA_HANDLER_PRIVATE_USER_SERVLET_MAPPING =
		"@data_handler_private_user_servlet_mapping@";

	private static final String _DATA_HANDLER_PUBLIC_LAYOUT_SET_SECURE_URL =
		"@data_handler_public_layout_set_secure_url@";

	private static final String _DATA_HANDLER_PUBLIC_LAYOUT_SET_URL =
		"@data_handler_public_layout_set_url@";

	private static final String _DATA_HANDLER_PUBLIC_SERVLET_MAPPING =
		"@data_handler_public_servlet_mapping@";

	private static final String _DATA_HANDLER_SITE_ADMIN_URL =
		"@data_handler_site_admin_url@";

	private static final char[] _DL_REFERENCE_LEGACY_STOP_CHARS = {
		CharPool.APOSTROPHE, CharPool.CLOSE_BRACKET, CharPool.CLOSE_CURLY_BRACE,
		CharPool.CLOSE_PARENTHESIS, CharPool.GREATER_THAN, CharPool.LESS_THAN,
		CharPool.PIPE, CharPool.QUOTE, CharPool.SPACE
	};

	private static final char[] _DL_REFERENCE_STOP_CHARS = {
		CharPool.APOSTROPHE, CharPool.CLOSE_BRACKET, CharPool.CLOSE_CURLY_BRACE,
		CharPool.CLOSE_PARENTHESIS, CharPool.GREATER_THAN, CharPool.LESS_THAN,
		CharPool.PIPE, CharPool.QUESTION, CharPool.QUOTE, CharPool.SPACE
	};

	private static final char[] _LAYOUT_REFERENCE_STOP_CHARS = {
		CharPool.APOSTROPHE, CharPool.CLOSE_BRACKET, CharPool.CLOSE_CURLY_BRACE,
		CharPool.CLOSE_PARENTHESIS, CharPool.GREATER_THAN, CharPool.LESS_THAN,
		CharPool.PIPE, CharPool.POUND, CharPool.QUESTION, CharPool.QUOTE,
		CharPool.SPACE
	};

	private static final Log _log = LogFactoryUtil.getLog(
		DefaultTextExportImportContentProcessor.class);

	private static String _companyAdminURL =
		GroupConstants.CONTROL_PANEL_FRIENDLY_URL +
			PropsValues.CONTROL_PANEL_LAYOUT_FRIENDLY_URL;
	private static final Pattern _exportLinksToLayoutPattern = Pattern.compile(
		"\\[([\\d]+)@(private(-group|-user)?|public)(@([\\d]+))?\\]");
	private static final Pattern _importLinksToLayoutPattern = Pattern.compile(
		"\\[([\\d]+)@(private(-group|-user)?|public)@([\\d]+)(@([\\d]+))?\\]");
	private static String _privateGroupServletMapping =
		PropsValues.LAYOUT_FRIENDLY_URL_PRIVATE_GROUP_SERVLET_MAPPING +
			StringPool.SLASH;
	private static String _privateUserServletMapping =
		PropsValues.LAYOUT_FRIENDLY_URL_PRIVATE_USER_SERVLET_MAPPING +
			StringPool.SLASH;
	private static String _publicGroupServletMapping =
		PropsValues.LAYOUT_FRIENDLY_URL_PUBLIC_SERVLET_MAPPING +
			StringPool.SLASH;
	private static String _siteAdminURL =
		VirtualLayoutConstants.CANONICAL_URL_SEPARATOR +
			GroupConstants.CONTROL_PANEL_FRIENDLY_URL +
				PropsValues.CONTROL_PANEL_LAYOUT_FRIENDLY_URL;

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference
	private DLAppLocalService _dlAppLocalService;

	@Reference
	private DLFileEntryLocalService _dlFileEntryLocalService;

	private ExportImportServiceConfiguration _exportImportServiceConfiguration;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private Http _http;

	@Reference
	private LayoutFriendlyURLLocalService _layoutFriendlyURLLocalService;

	@Reference
	private LayoutLocalService _layoutLocalService;

	@Reference
	private LayoutSetLocalService _layoutSetLocalService;

	@Reference
	private Portal _portal;

	@Reference
	private VirtualHostLocalService _virtualHostLocalService;

}