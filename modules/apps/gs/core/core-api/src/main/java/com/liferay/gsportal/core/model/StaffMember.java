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

package com.liferay.gsportal.core.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the StaffMember service. Represents a row in the &quot;Core_StaffMember&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see StaffMemberModel
 * @see com.liferay.gsportal.core.model.impl.StaffMemberImpl
 * @see com.liferay.gsportal.core.model.impl.StaffMemberModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.gsportal.core.model.impl.StaffMemberImpl")
@ProviderType
public interface StaffMember extends StaffMemberModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.gsportal.core.model.impl.StaffMemberImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<StaffMember, Long> USER_ID_ACCESSOR = new Accessor<StaffMember, Long>() {
			@Override
			public Long get(StaffMember staffMember) {
				return staffMember.getUserId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<StaffMember> getTypeClass() {
				return StaffMember.class;
			}
		};

	public com.liferay.portal.kernel.model.User getUser()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public boolean isInternal();

	public java.lang.String getEmployeeTypeName();
}