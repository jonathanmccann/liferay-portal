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

package com.liferay.gsportal.client.portlet;


import java.io.IOException;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.gsportal.core.model.Client;
import com.liferay.gsportal.core.model.Project;
import com.liferay.gsportal.core.service.ClientLocalService;
import com.liferay.gsportal.core.service.ProjectLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.AddressLocalServiceUtil;
import com.liferay.portal.kernel.util.ParamUtil;

/**
 * @author Kayleen Lim
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.client",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Client Company Summary Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/client-company-summary/view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class ClientCompanySummaryPortlet extends MVCPortlet {

	@Override
	public void doView(RenderRequest request, RenderResponse response)
		throws IOException, PortletException {

		try {
			//TODO: get project Id from friendly url
			long projectId = ParamUtil.getLong(request, "projectId");
			//TODO: get client Id from friendly url
			long clientId = ParamUtil.getLong(request, "clientId");

			if (projectId > 0) {
				Project project = _projectLocalService.getProject(projectId);
				request.setAttribute("projectId", projectId);

				if (project.getClientId() > 0) {
					long parentClientId = project.getClientId();

					Client client = _clientLocalService.getClient(parentClientId);
					int clientProjectsCount = _projectLocalService.getProjectsCountByClientId(parentClientId);

					Address address = null;
					if (client.getAddressId() > 0){
						address = AddressLocalServiceUtil.getAddress(client.getAddressId());
					}

					request.setAttribute("address", address);
					request.setAttribute("clientId", parentClientId);
					request.setAttribute("client", client);
					request.setAttribute("clientProjectsCount", clientProjectsCount);
				}
			}
			else if (clientId > 0) {
				Client client = _clientLocalService.getClient(clientId);

				// TODO implement count method in service to offer a "view all link"
				// in maximized view
				List<Client> subclients = _clientLocalService.getSubclientsByClientId(clientId, 0, 20);

				Address address = null;
				if (client.getAddressId() > 0){
					address = AddressLocalServiceUtil.getAddress(client.getAddressId());
				}

				request.setAttribute("address", address);
				request.setAttribute("clientId", clientId);
				request.setAttribute("client", client);
				request.setAttribute("subclients", subclients);
			}

		}
		catch (Exception e) {
			_log.error("Cannot fetch client information: " + e.getMessage(), e);

			throw new PortletException(
				"Cannot fetch client information: " + e.getMessage(), e);
		}

		super.doView(request, response);
	}

	@Reference(unbind = "-")
	protected void setClientLocalService(ClientLocalService clientLocalService) {
	    _clientLocalService = clientLocalService;
	}
	@Reference(unbind = "-")
	protected void setProjectLocalService(ProjectLocalService projectLocalService) {
		_projectLocalService = projectLocalService;
	}
	private ClientLocalService _clientLocalService;
	private ProjectLocalService _projectLocalService;


	private static Log _log = LogFactoryUtil.getLog(
		ClientCompanySummaryPortlet.class);

}