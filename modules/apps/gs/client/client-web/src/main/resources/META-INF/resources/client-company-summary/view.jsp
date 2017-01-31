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

<%@ page import="com.liferay.portal.kernel.util.StringPool" %>

<%@ include file="/client-company-summary/init.jsp" %>

<div class="card-horizontal">
	<c:choose>
		<c:when test="${projectId > 0 || clientId > 0}">
			<c:choose>
				<c:when test="${empty client}">
					<liferay-ui:message key="this-project-is-not-associated-with-any-client" />
				</c:when>
				<c:otherwise>
					<%-- "If company has more than one Liferay projects, name would be a link and clicking
							it will list all projects by company." (source: wireframes) --%>

					<div class="card-row">
						<div class="card-col-5">
							<img class="img-large img-rounded" src="${fn:escapeXml(fn:trim(client.logoUrl))}" />
						</div>

						<div class="card-col-7 card-col-gutters client-info">
							<c:choose>
								<c:when test="${clientProjectsCount > 1 && projectId > 0}">
									<h4 class="client-name"><aui:a href="${fn:trim(client.dashboardUrl)}" title="view-client-dashboard"><c:out value="${fn:trim(client.name)}" /></aui:a></h4>
								</c:when>

								<c:otherwise>
									<h4 class="client-name"><c:out value="${fn:trim(client.name)}" /></h4>
								</c:otherwise>
							</c:choose>

							<c:if test="${client.websiteUrl}">
								<aui:a href="${fn:trim(client.websiteUrl)}" target="_blank" title="${fn:escapeXml(fn:trim(client.websiteUrl))}"><c:out value="${fn:trim(client.websiteUrl)}" /></aui:a>

								<br>
							</c:if>

							<c:if test="${not empty address}">
								<span class="client-address">
									<c:out value="${fn:trim(address.street1)}" />

									<c:if test="${not empty address.street2}">
										<br>

										<c:out value="${fn:trim(address.street2)}" />
									</c:if>

									<c:if test="${not empty address.street3}">
										<br>

										<c:out value="${fn:trim(address.street3)}" />
									</c:if>

									<c:if test="${not empty address.city}">
										<br>

										 <c:out value="${fn:trim(address.city)}" /><%= StringPool.COMMA %>
									</c:if>

									<c:if test="${address.regionId > 0}">
										<c:set value="${address.regionId}" var="regionId" />

										<%-- TODO non-existing regionId will throw NoSuchRegionException. We need to catch it. --%>
										<c:set value='<%= RegionServiceUtil.getRegion((Long)pageContext.getAttribute("regionId")) %>' var="region" />

										<c:out value="${not empty region ? region.name : ''}" />
									</c:if>

									<c:out value="${fn:trim(address.zip)}" />

									<br>

									<c:if test="${address.countryId > 0}">
										<c:set value="${address.countryId}" var="countryId" />

										<%-- fetchXXX will return 'null' for non-existing countryId --%>
										<c:set value='<%= CountryServiceUtil.fetchCountry((Long)pageContext.getAttribute("countryId")) %>' var="country" />

										<c:out value="${not empty country ? country.nameCurrentValue : ''}" />
									</c:if>
								</span>
							</c:if>
						</div>
					</div>

					<c:if test="${clientId > 0 && fn:length(subclients) > 0}">
						<div class="card-row">
							<h5><liferay-ui:message key="clients-subclients" /></h5>
							<ul>
								<c:forEach var="subclient" items="${subclients}">
									<li>
										<aui:a href="${subclient.dashboardUrl}"><c:out value="${subclient.name}"/></aui:a>
									</li>
								</c:forEach>
							</ul>
						</div>
					</c:if>
				</c:otherwise>
			</c:choose>
		</c:when>
		<c:otherwise>
			<div class="portlet-msg-info">
				<liferay-ui:message key="neither-client-nor-project-can-be-determined-from-url" />
			</div>
		</c:otherwise>
	</c:choose>
</div>