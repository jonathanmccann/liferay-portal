package com.liferay.dynamic.data.mapping.internal.upgrade.v1_0_3;

import com.liferay.dynamic.data.mapping.internal.upgrade.v1_0_3.util.DDMStructureTable;
import com.liferay.dynamic.data.mapping.internal.upgrade.v1_0_3.util.DDMStructureVersionTable;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

public class UpgradeDDMStructureName extends UpgradeProcess {

	protected void doUpgrade() throws Exception {
		alter(
			DDMStructureTable.class,
			new UpgradeProcess.AlterColumnType("name", "TEXT null"));
		alter(
			DDMStructureVersionTable.class,
			new UpgradeProcess.AlterColumnType("name", "TEXT null"));
	}
}