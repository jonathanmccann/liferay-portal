package com.liferay.gsportal.client.portlet;

import com.liferay.gsportal.core.model.Client;
import com.liferay.gsportal.core.service.ClientLocalService;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;
import java.io.IOException;
import java.util.List;

@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.client",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=New Clients Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/new-clients/view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class NewClientsPortlet extends MVCPortlet {

	// Defining constants

	public static final String MAX_CLIENTS = "maxClients";

	public static final int MAX_CLIENTS_DEFAULT = 4;

	public static final int[] MAX_CLIENTS_OPTIONS = { 1, 2, 3, 4, 8, 12, 16, 20, 40 };

	@Override
	public void doView(RenderRequest request, RenderResponse response)
		throws IOException, PortletException {

		PortletPreferences prefs = request.getPreferences();

		try {
			int maxClients = GetterUtil.getInteger(
				prefs.getValue(MAX_CLIENTS, StringPool.BLANK), MAX_CLIENTS_DEFAULT);

			if (WindowState.MAXIMIZED.equals(request.getWindowState())) {
				maxClients = Integer.MAX_VALUE;
			}

			// Retrieving list of new Clients and Client count

			int totalClients = _clientLocalService.getClientsCount();
			List<Client> newClients = _clientLocalService.getClients(0, maxClients);

			request.setAttribute("totalClients", totalClients);
			request.setAttribute("newClients", newClients);

		}
		catch (SystemException e) {
			_log.error("Cannot fetch new clients: " + e.getMessage(), e);

			SessionErrors.add(request, "error.cannot-fetch-new-clients", e.toString());
		}

		super.doView(request, response);
	}

	@Reference(unbind = "-")
	protected void setClientLocalService(ClientLocalService clientLocalService) {
		_clientLocalService = clientLocalService;
	}

	private ClientLocalService _clientLocalService;

	private static Log _log = LogFactoryUtil.getLog(NewClientsPortlet.class);

}