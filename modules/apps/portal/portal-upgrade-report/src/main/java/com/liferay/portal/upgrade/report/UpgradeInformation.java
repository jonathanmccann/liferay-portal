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

package com.liferay.portal.upgrade.report;


/**
 * @author Jonathan McCann
 */
public class UpgradeInformation {
	public UpgradeInformation(
		String className, long duration, String exception) {

		_className = className;
		_duration = duration;
		_exception = exception;
	}

	public String getClassName() {
		return _className;
	}

	public long getDuration() {
		return _duration;
	}

	public String getException() {
		return _exception;
	}

	private String _className;
	private long _duration;
	private String _exception;
}