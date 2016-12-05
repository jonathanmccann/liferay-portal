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

<%@ include file="/html/taglib/init.jsp" %>

<%
Layout selLayout = (Layout)request.getAttribute("liferay-theme:layout-icon:layout");
long iconImageId = selLayout.getIconImageId();
String layoutIconImageURL = themeDisplay.getPathImage() + "/organization_logo?img_id=0";

if (iconImageId > 0) {
	layoutIconImageURL = DLUtil.getPreviewURL(iconImageId, themeDisplay);
}
else {
	Group group = GroupLocalServiceUtil.getGroup(selLayout.getGroupId());

	if (group.isOrganization()) {
		layoutIconImageURL = group.getLogoURL(themeDisplay, true);
	}
}
%>

<c:if test="<%= (selLayout != null) && selLayout.isIconImage() %>">
	<img alt="<liferay-ui:message escapeAttribute="<%= true %>" key="page-icon" />" class="layout-logo-<%= selLayout.getPlid() %>" src="<%= layoutIconImageURL %>" />
</c:if>