<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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

<%-- Required template variables (EL variables):
		* 'searchResults'
			* type ~ List<com.liferay.gsportal.core.model.Project>
			* or type ~ List<com.liferay.gsportal.core.model.Client>
			* list of projects or clients to render

	This JSP template is currently used from:
		* /new-clients/view.jsp
			* New Clients portlet
		* /new-projects/view.jsp
			* New Projects portlet
		* /client-projects/view.jsp
			* Client Projects portlet
--%>

<div class="clients-projects-logo-list">
	<c:forEach items="${searchResults}" var="searchResult" varStatus="loop">
		<c:set value="${loop.index + 1}" var="oneBasedIndex" />

		<c:set value="${oneBasedIndex % 4 == 1}" var="startFluidRow" />

		<c:set value="${(oneBasedIndex % 4 == 0) || (oneBasedIndex == fn:length(searchResults))}" var="endFluidRow" />

		<c:if test="${startFluidRow}">

		<div class="row-fluid equal-height-column-row">

		</c:if>
			<div class="card client-project-info span3">

				<%-- Tag <aui:a /> checks whether its "href" is not empty and renders <a href="..." /> only if it isn't,
				 	otherwise just renders the body. So we do not need to do the '${not empty searchResult.getDashboardUrl()}'
					test ourselves --%>

				<aui:a href="${searchResult.getDashboardUrl()}">
					<div class="card-section">

						<%-- ${searchResult.getLogoUrl()} will point to company logo when custom one was not selected for
							given Project / Client, no need to check for custom logo if we are fine with displaying
							the company one instead. --%>

						<img alt="${fn:escapeXml(searchResult.getName())} Logo"
							 class="round-image img-rounded client-project-logo"
							 src="${fn:escapeXml(searchResult.getLogoUrl())}"
							 title="${StringUtils.abbreviate(searchResult.getDescription(),100)}" />
					</div>

					<div class="card-footer">
						<span class="client-project-name"><c:out value="${searchResult.getName()}" /></span>
					</div>
				</aui:a>
			</div>

		<c:if test="${endFluidRow}">
		</div>
		</c:if>
	</c:forEach>
</div>