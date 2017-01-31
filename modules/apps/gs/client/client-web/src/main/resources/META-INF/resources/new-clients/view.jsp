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
 */--%>

<%@ include file="/new-clients/init.jsp" %>

<liferay-ui:error key="error.cannot-fetch-new-clients">
	<liferay-ui:message arguments="${errorException}" key="error.cannot-fetch-new-clients" />
</liferay-ui:error>

<c:choose>
	<c:when test="${not empty newClients}">
		<div class="new-clients">
			<c:set value="${newClients}" var="searchResults" />
			<%@ include file="/new-clients/logo_search_results.jsp" %>
		</div>

		<div>
			<c:if test="${newClients.size() < totalClients}">
				<portlet:renderURL var="allClientsURL" windowState="<%= WindowState.MAXIMIZED.toString() %>" />

				<liferay-ui:message arguments="${newClients.size() }" key="displaying-x-newest-clients" />

				<aui:a href="${allClientsURL}">
					<liferay-ui:message arguments="${totalClients}" key="show-all-x-clients" />
				</aui:a>
			</c:if>
		</div>
	</c:when> <%-- ${not empty newClients.results} --%>
	<c:otherwise>
		<i>
			<liferay-ui:message key="there-are-no-clients-yet" />
		</i>
	</c:otherwise>
</c:choose>