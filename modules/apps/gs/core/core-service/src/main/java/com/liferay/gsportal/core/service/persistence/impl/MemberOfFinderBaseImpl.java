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

package com.liferay.gsportal.core.service.persistence.impl;

import com.liferay.gsportal.core.model.MemberOf;
import com.liferay.gsportal.core.service.persistence.MemberOfPersistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class MemberOfFinderBaseImpl extends BasePersistenceImpl<MemberOf> {
	/**
	 * Returns the member of persistence.
	 *
	 * @return the member of persistence
	 */
	public MemberOfPersistence getMemberOfPersistence() {
		return memberOfPersistence;
	}

	/**
	 * Sets the member of persistence.
	 *
	 * @param memberOfPersistence the member of persistence
	 */
	public void setMemberOfPersistence(MemberOfPersistence memberOfPersistence) {
		this.memberOfPersistence = memberOfPersistence;
	}

	@BeanReference(type = MemberOfPersistence.class)
	protected MemberOfPersistence memberOfPersistence;
}