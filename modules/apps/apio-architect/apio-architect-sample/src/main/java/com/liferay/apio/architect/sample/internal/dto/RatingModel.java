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

package com.liferay.apio.architect.sample.internal.dto;

/**
 * Represents a rating. This is a mock class for sample purposes only.
 *
 * @author Alejandro Hernández
 */
public class RatingModel {

	public RatingModel(Long creatorId, Long value) {
		_creatorId = creatorId;
		_value = value;
	}

	/**
	 * Returns the ID of the rating's creator.
	 *
	 * @return the ID of the rating's creator
	 */
	public Long getCreatorId() {
		return _creatorId;
	}

	/**
	 * Returns the rating's value.
	 *
	 * @return the rating's value
	 */
	public Long getValue() {
		return _value;
	}

	private final Long _creatorId;
	private final Long _value;

}