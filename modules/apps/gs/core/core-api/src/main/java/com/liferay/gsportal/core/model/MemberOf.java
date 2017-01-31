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
 * The extended model interface for the MemberOf service. Represents a row in the &quot;Core_MemberOf&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see MemberOfModel
 * @see com.liferay.gsportal.core.model.impl.MemberOfImpl
 * @see com.liferay.gsportal.core.model.impl.MemberOfModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.gsportal.core.model.impl.MemberOfImpl")
@ProviderType
public interface MemberOf extends MemberOfModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.gsportal.core.model.impl.MemberOfImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<MemberOf, Long> MEMBER_OF_ID_ACCESSOR = new Accessor<MemberOf, Long>() {
			@Override
			public Long get(MemberOf memberOf) {
				return memberOf.getMemberOfId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<MemberOf> getTypeClass() {
				return MemberOf.class;
			}
		};
}