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

<%@ include file="init.jsp" %>

<portlet:actionURL name="updateClient" var="updateClientURL" />

<aui:form action="${updateClientURL}" enctype="multipart/form-data" method="POST">

    <aui:fieldset label="Client Information">
        <aui:model-context bean="${client}" model="<%= Client.class %>" />

        <aui:input name="clientId" type="hidden" />

        <aui:input name="parentClientId" type="hidden" />

        <aui:input name="name" required="true" />

        <aui:input name="description" type="textarea" value="${client.description}" />

        <aui:input name="websiteUrl" label="Website URL"/>

        <aui:input name="dashboardUrl" label="Dashboard URL"/>
    </aui:fieldset>

    <!-- address -->

    <aui:fieldset label="address">
        <aui:row>
            <aui:col width="<%= 50 %>">
                <aui:model-context bean="${address}" model="<%= Address.class %>" />

                <liferay-ui:error exception="<%= AddressCityException.class %>" message="please-enter-a-valid-city" />

                <aui:input name="city" required="true"/>

                <liferay-ui:error exception="<%= AddressZipException.class %>" message="please-enter-a-valid-postal-code" />

                <aui:input name="zip" required="true"/>

                <liferay-ui:error exception="<%= NoSuchCountryException.class %>" message="please-select-a-country" />

                <aui:select label="country" name="countryId" required="true"/>

                <liferay-ui:error exception="<%= NoSuchRegionException.class %>" message="please-select-a-region" />

                <aui:select label="region" name="regionId" required="true"/>
            </aui:col>

            <aui:col width="<%= 50 %>">
                <liferay-ui:error exception="<%= AddressStreetException.class %>" message="please-enter-a-valid-street" />

                <aui:input name="street1" required="true"/>

                <aui:input name="street2" />

                <aui:input name="street3" />
            </aui:col>
        </aui:row>
    </aui:fieldset>

	<aui:button type="submit" />
</aui:form>


<aui:script use="liferay-dynamic-select,liferay-address">
new Liferay.DynamicSelect(
    [
        {
            select: '<portlet:namespace />countryId',
            selectData: Liferay.Address.getCountries,
            selectDesc: 'nameCurrentValue',
            selectSort: '${true}',
            selectId: 'countryId',
            selectVal: '${countryId}'
        },
        {
            select: '<portlet:namespace />regionId',
            selectData: Liferay.Address.getRegions,
            selectDesc: 'name',
            selectId: 'regionId',
            selectVal: '${regionId}'
        }
    ]
);
</aui:script>