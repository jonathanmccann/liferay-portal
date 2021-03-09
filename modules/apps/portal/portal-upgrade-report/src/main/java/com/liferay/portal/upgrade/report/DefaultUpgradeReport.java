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

import com.liferay.exportimport.kernel.staging.LayoutStaging;
import com.liferay.portal.kernel.upgrade.UpgradeReport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Jonathan McCann
 */
@Component(immediate = true, service = UpgradeReport.class)
public class DefaultUpgradeReport implements UpgradeReport {

	public void generateReport() {
		for (UpgradeInformation upgradeInformation : _upgradeInformations) {

		}

		Collections.sort(_upgradeInformations, _comparator.reversed());

		System.out.println("_upgradeInformations.get(0).getClassName() = " +  _upgradeInformations.get(0).getClassName());
		System.out.println("_upgradeInformations.get(0).getDuration() = " +  _upgradeInformations.get(0).getDuration());
		System.out.println();
		System.out.println("_upgradeInformations.get(1).getClassName() = " +  _upgradeInformations.get(1).getClassName());
		System.out.println("_upgradeInformations.get(1).getDuration() = " +  _upgradeInformations.get(1).getDuration());
		System.out.println();
		System.out.println("_upgradeInformations.get(2).getClassName() = " +  _upgradeInformations.get(2).getClassName());
		System.out.println("_upgradeInformations.get(2).getDuration() = " +  _upgradeInformations.get(2).getDuration());
		System.out.println();
		System.out.println("_upgradeInformations.get(3).getClassName() = " +  _upgradeInformations.get(3).getClassName());
		System.out.println("_upgradeInformations.get(3).getDuration() = " +  _upgradeInformations.get(3).getDuration());
		System.out.println();
		System.out.println("_upgradeInformations.get(4).getClassName() = " +  _upgradeInformations.get(4).getClassName());
		System.out.println("_upgradeInformations.get(4).getDuration() = " +  _upgradeInformations.get(4).getDuration());
		System.out.println();
	}

	public void addUpgradeInformation(
		String className, long duration, String exception) {

		UpgradeInformation upgradeInformation = new UpgradeInformation(
			className, duration, exception);

		_upgradeInformations.add(upgradeInformation);
	}

	private static List<UpgradeInformation> _upgradeInformations =
		new ArrayList<>();

	private final Comparator<UpgradeInformation> _comparator =
		Comparator.comparingLong(UpgradeInformation::getDuration);

}