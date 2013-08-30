/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.portlet.messageboards.util.comparator;

import com.liferay.portlet.messageboards.model.MBThread;

import java.util.Comparator;
import java.util.Date;

/**
 * @author Jonathan McCann
 */
public class ThreadLastBumpDateComparator implements Comparator<MBThread> {

	@Override
	public int compare(MBThread thread1, MBThread thread2) {
		Date lastBumpDate1 = thread1.getLastBumpDate();
		Date lastBumpDate2 = thread2.getLastBumpDate();

		Date lastPostDate1 = thread1.getLastPostDate();
		Date lastPostDate2 = thread2.getLastPostDate();

		if ((lastBumpDate1 == null) || lastPostDate1.after(lastBumpDate1)) {
			lastBumpDate1 = lastPostDate1;
		}

		if ((lastBumpDate2 == null) || lastPostDate2.after(lastBumpDate2)) {
			lastBumpDate2 = lastPostDate2;
		}

		int value = 1;

		if (lastBumpDate1.after(lastBumpDate2)) {
			value = -1;
		}

		return value;
	}

}