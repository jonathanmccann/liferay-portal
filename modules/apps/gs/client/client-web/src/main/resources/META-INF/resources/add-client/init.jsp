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

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

<%@ page import="com.liferay.gsportal.core.model.Client" %>
<%@ page import="com.liferay.portal.kernel.model.Address" %>

<%@ page import="com.liferay.portal.kernel.exception.AddressCityException" %>
<%@ page import="com.liferay.portal.kernel.exception.AddressStreetException" %>
<%@ page import="com.liferay.portal.kernel.exception.AddressZipException" %>
<%@ page import="com.liferay.portal.kernel.exception.NoSuchCountryException" %>
<%@ page import="com.liferay.portal.kernel.exception.NoSuchRegionException" %>

<portlet:defineObjects />
<liferay-theme:defineObjects />