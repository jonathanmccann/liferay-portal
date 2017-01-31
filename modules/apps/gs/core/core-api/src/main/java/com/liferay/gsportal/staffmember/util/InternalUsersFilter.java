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

package com.liferay.gsportal.staffmember.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

/**
 * Provides filtering functionality based on email domain of passed users or
 * email addresses.
 *
 * @author Josef Sustacek
 */
public class InternalUsersFilter {

	public InternalUsersFilter(String internalUsersMailDomain) {

		if (Validator.isNull(internalUsersMailDomain)) {
			throw new IllegalArgumentException(
				"internalUsersMailDomain cannot be empty");
		}

		_internalUsersMailDomain = internalUsersMailDomain;
	}

	/**
	 * Checks if given email address represents an internal user (= Liferay
	 * employee) or not.
	 *
	 * @param emailAddress the address to check
	 * @return <code>true</code> if user having given <code>emailAddress</code>
	 *      is an internal Lifeary employee, <code>false</code> otherwise
	 */
	public boolean isInternal(String emailAddress) {
		return StringUtil.endsWith(emailAddress, _internalUsersMailDomain);
	}

	/**
	 * Checks if given user object represents an internal user (= Liferay
	 * employee) or not.
	 *
	 * @param user the user to check; checks 'user.getEmailAddress()'
	 * @return <code>true</code> if user is an internal Lifeary employee,
	 *      <code>false</code> otherwise
	 */
	public boolean isInternal(User user)
		throws PortalException {
		if (user == null) {
			throw new PortalException("user cannot be null");
		}

		String emailAddress = user.getEmailAddress();

		return isInternal(emailAddress);
	}

	private final String _internalUsersMailDomain;

}