<%--
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */
--%>

<%@ include file="/new-clients/init.jsp"%>

<liferay-portlet:actionURL portletConfiguration="true" var="saveConfigurationURL"></liferay-portlet:actionURL>
<liferay-portlet:renderURL portletConfiguration="true" var="renderConfigurationURL"></liferay-portlet:renderURL>

<%
	int maxClients = GetterUtil.getInteger(
			portletPreferences.getValue(NewClientsPortlet.MAX_CLIENTS, StringPool.BLANK),
			NewClientsPortlet.MAX_CLIENTS_DEFAULT);
%>

<c:set value="<%=NewClientsPortlet.MAX_CLIENTS%>" var="maxClientsPrefKey" />
<c:set value="<%=maxClients%>" var="maxClients" />
<c:set value="<%=NewClientsPortlet.MAX_CLIENTS_OPTIONS%>" var="maxClientsOptions" />

<aui:form action="${saveConfigurationURL}" method="post" name="fm">
	<aui:input name="<%=Constants.CMD%>" type="hidden" value="<%=Constants.UPDATE%>" />
	<aui:input name="redirect" type="hidden" value="${renderConfigurationURL}" />
	
	<aui:fieldset>
		<aui:select label="lgsp-core-portlet.new-clients.maxClients" name="preferences--${maxClientsPrefKey}--">
			<c:forEach items="${maxClientsOptions}" var="maxClientsOption">
				<aui:option label="${maxClientsOption}" selected="${maxClientsOption == maxClients}" value="${maxClientsOption}" />
			</c:forEach>
		</aui:select>
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" />
	</aui:button-row>
</aui:form>