<%--
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
--%>

<%@ include file="/init.jsp" %>

<%
Layout selLayout = layoutsAdminDisplayContext.getSelLayout();

UnicodeProperties layoutTypeSettings = selLayout.getTypeSettingsProperties();
%>

<liferay-ui:error-marker key="<%= WebKeys.ERROR_SECTION %>" value="advanced" />

<aui:model-context bean="<%= selLayout %>" model="<%= Layout.class %>" />

<liferay-ui:error exception="<%= ImageTypeException.class %>" message="please-enter-a-file-with-a-valid-file-type" />

<%
Group group = layoutsAdminDisplayContext.getGroup();
%>

<c:if test="<%= !group.isLayoutPrototype() %>">
	<div class="alert alert-warning layout-prototype-info-message <%= selLayout.isLayoutPrototypeLinkActive() ? StringPool.BLANK : "hide" %>">
		<liferay-ui:message arguments='<%= new String[] {LanguageUtil.get(request, "inherit-changes"), "General"} %>' key="some-page-settings-are-unavailable-because-x-is-enabled" translateArguments="<%= false %>" />
	</div>

	<%
	String queryString = GetterUtil.getString(layoutTypeSettings.getProperty("query-string"));
	%>

	<aui:input cssClass="propagatable-field" disabled="<%= selLayout.isLayoutPrototypeLinkActive() %>" helpMessage="query-string-help" label="query-string" name="TypeSettingsProperties--query-string--" size="30" type="text" value="<%= queryString %>" />
</c:if>

<%
String curTarget = GetterUtil.getString(layoutTypeSettings.getProperty("target"));
long logoId = selLayout.getIconImageId();
String logoURL = themeDisplay.getPathThemeImages() + "/spacer.png";

if (logoId > 0) {
	logoURL = DLUtil.getPreviewURL(logoId, themeDisplay);
}
else if (group.isOrganization()) {
	logoURL = group.getLogoURL(themeDisplay, true);
}
%>

<aui:input cssClass="propagatable-field" disabled="<%= selLayout.isLayoutPrototypeLinkActive() %>" label="target" name="TypeSettingsProperties--target--" size="15" type="text" value="<%= HtmlUtil.escapeAttribute(curTarget) %>" />

<aui:field-wrapper helpMessage="this-icon-will-be-shown-in-the-navigation-menu" label="icon" name="iconFileName">
	<liferay-ui:logo-selector
		currentLogoURL="<%= logoURL %>"
		editLogoFn='<%= liferayPortletResponse.getNamespace() + "editLayoutLogo" %>'
		logoDisplaySelector='<%= ".layout-logo-" + selLayout.getPlid() %>'
		tempImageFileName="<%= String.valueOf(selLayout.getPlid()) %>"
	/>
</aui:field-wrapper>

<aui:script>
	function <portlet:namespace />editLayoutLogo(logoURL, deleteLogo) {
		var $ = AUI.$;

		var layoutLogo = $('.layout-logo-<%= selLayout.getPlid() %>');

		if (!layoutLogo.length) {
			layoutLogo = $('<img alt="<liferay-ui:message escapeAttribute="<%= true %>" key="logo" />" class="layout-logo-<%= selLayout.getPlid() %>" src="' + logoURL + '" />');

			$('#layout_<%= selLayout.getLayoutId() %> span').prepend(layoutLogo);
		}

		layoutLogo.toggleClass('hide', deleteLogo);
	}
</aui:script>