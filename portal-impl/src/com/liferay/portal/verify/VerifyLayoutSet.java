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

package com.liferay.portal.verify;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.service.LayoutSetLocalServiceUtil;

import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Gergely Mathe
 * @author Kenneth Chang
 */
public class VerifyLayoutSet extends VerifyProcess {

	@Override
	protected void doVerify() throws Exception {
		verifyLayoutSet();
	}

	protected void verifyLayoutSet() throws Exception {
		List<LayoutSet> layoutSetList =
			LayoutSetLocalServiceUtil.getLayoutSetPrototypeUuids();

		if (layoutSetList.size() > 0) {
			for (LayoutSet layoutSet : layoutSetList) {
				Group layoutSetGroup = layoutSet.getGroup();

				if (!layoutSetGroup.isSite() &&
					layoutSetGroup.isOrganization()) {

					layoutSet.setLayoutSetPrototypeLinkEnabled(Boolean.FALSE);
					layoutSet.setLayoutSetPrototypeUuid(StringPool.BLANK);
					layoutSet.setModifiedDate(new Date());
					layoutSet.setPageCount(0);

					LayoutSetLocalServiceUtil.updateLayoutSet(layoutSet);
				}
			}
		}
	}

}