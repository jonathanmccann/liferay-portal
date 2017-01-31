package com.liferay.gsportal.client.portlet;

import com.liferay.gsportal.core.model.Client;
import com.liferay.gsportal.core.service.ClientLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.AddressLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;

@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.client",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Add Client Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/add-client/view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class AddClientPortlet extends MVCPortlet {

	public void updateClient(ActionRequest request, ActionResponse response) {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
				WebKeys.THEME_DISPLAY);

		String name = ParamUtil.getString(request, "name");
		String description = ParamUtil.getString(request, "description");

		long parentClientId = ParamUtil.getLong(request,
				"parentClientId", -1);
		String websiteUrl = ParamUtil.getString(request,
				"websiteURL");


		Address address = getAddress(request);

		try {
			ServiceContext serviceContext = ServiceContextFactory.getInstance(Client.class.getName(), request);

            // Add client

            _clientLocalService.addClient(
                    parentClientId, themeDisplay.getCompanyId(),
                    name, description, "", -1, "",
                    websiteUrl, address, serviceContext);
		}
		catch (Exception e) {
			SessionErrors.add(request, e.getClass());
			_log.error("Could not add/update Service Builder Client", e);
		}

		response.setRenderParameter("mvcPath", "/add-client/view.jsp");
	}

	protected Address getAddress(ActionRequest request) {

		String street1 = ParamUtil.getString(request, "street1");
		String street2 = ParamUtil.getString(request, "street2");
		String street3 = ParamUtil.getString(request, "street3");
		String city = ParamUtil.getString(request, "city");
		String zip = ParamUtil.getString(request, "zip");
		long regionId = ParamUtil.getLong(request, "regionId");
		long countryId = ParamUtil.getLong(request, "countryId");
		int addressTypeId = ParamUtil.getInteger(request, "addressTypeId");

		if (Validator.isNull(street1) && Validator.isNull(street2) &&
			Validator.isNull(street3) && Validator.isNull(city) &&
			Validator.isNull(zip) && (countryId == 0)) {

				return null;
		}

		long addressId = 0;

		Address address = _addressLocalService.createAddress(addressId);

		address.setStreet1(street1);
		address.setStreet2(street2);
		address.setStreet3(street3);
		address.setCity(city);
		address.setZip(zip);
		address.setRegionId(regionId);
		address.setCountryId(countryId);
		address.setTypeId(addressTypeId);
		address.setMailing(false);
		address.setPrimary(true);

		return address;
	}

	private AddressLocalService _addressLocalService;
	private ClientLocalService _clientLocalService;

	@Reference(unbind = "-")
	protected void setAddressLocalService(AddressLocalService addressLocalService){
		_addressLocalService = addressLocalService;
	}
	@Reference(unbind = "-")
	protected void setClientLocalService(ClientLocalService clientLocalService){
		_clientLocalService = clientLocalService;
	}

	private Log _log = LogFactoryUtil.getLog(AddClientPortlet.class);
}