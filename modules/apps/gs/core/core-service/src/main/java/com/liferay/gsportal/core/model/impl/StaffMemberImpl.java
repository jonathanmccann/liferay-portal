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

package com.liferay.gsportal.core.model.impl;

import com.liferay.gsportal.staffmember.util.EmployeeType;
import com.liferay.gsportal.staffmember.util.StaffMemberConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

import aQute.bnd.annotation.ProviderType;

/**
 * The extended model implementation for the StaffMember service. Represents a row in the &quot;Core_StaffMember&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.gsportal.staffmember.model.StaffMember} interface.
 * </p>
 *
 * @author GS
 */
@ProviderType
public class StaffMemberImpl extends StaffMemberBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a staff member model instance should use the {@link com.liferay.gsportal.staffmember.model.StaffMember} interface instead.
	 */
	public StaffMemberImpl() {
	}

	@Override
	public User getUser() throws PortalException, SystemException {
		return UserLocalServiceUtil.getUserById(getUserId());
	}

	@Override
	public boolean isInternal() {
		if (getEmployeeType() == EmployeeType.LIFERAY.getValue())
			return true;
		else
			return false;
	}
	@Override
	public String getEmployeeTypeName() {

		int type = this.getEmployeeType();
		String typeName = "";
		switch (type) {
		case 1:
			typeName = StaffMemberConstants.LIFERAY;
			break;
		case 2:
			typeName = StaffMemberConstants.PARTNER;
			break;
		case 3:
			typeName = StaffMemberConstants.CUSTOMER;
			break;
		default:
			typeName = null;
			break;
		}
		return typeName;
	}

}