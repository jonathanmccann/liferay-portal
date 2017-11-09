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

package com.liferay.portal.upgrade.v7_0_0;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.model.ClassName;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.util.MathUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.LinkedList;

import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Michael Bowerman
 */
public class UpgradeAssetTest extends UpgradeAsset {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@BeforeClass
	public static void setUpClass() {
		ClassName className = ClassNameLocalServiceUtil.addClassName(
			"com.liferay.portlet.journal.model.JournalArticle");

		_journalArticleClassNameId = className.getClassNameId();
	}

	@After
	public void tearDown() throws Exception {
		ClassNameLocalServiceUtil.deleteClassName(_journalArticleClassNameId);

		while (!_assetEntryEntryIds.isEmpty()) {
			runSQL(
				"delete from AssetEntry where entryId = " +
					_assetEntryEntryIds.pop());
		}

		while (!_journalArticleIds.isEmpty()) {
			runSQL(
				"delete from JournalArticle where id_ = " +
					_journalArticleIds.pop());
		}
	}

	@Test
	public void testUpgradeAsset() throws Exception {
		_testUpgradeAsset(new boolean[] {false});
		_testUpgradeAsset(new boolean[] {true});
		_testUpgradeAsset(new boolean[] {false, true});
		_testUpgradeAsset(new boolean[] {true, false});
		_testUpgradeAsset(new boolean[] {false, false, true});
		_testUpgradeAsset(new boolean[] {false, true, false});
		_testUpgradeAsset(new boolean[] {true, false, true});
		_testUpgradeAsset(new boolean[] {true, true, false});
	}

	private long _addJournalArticleEntries(boolean[] indexableVersions)
		throws Exception {

		double version = MathUtil.format(1, 1, 1);

		long resourcePrimKey = CounterLocalServiceUtil.increment();

		for (boolean indexable : indexableVersions) {
			long id = CounterLocalServiceUtil.increment();

			_journalArticleIds.add(id);

			StringBundler sb = new StringBundler(12);

			sb.append("insert into JournalArticle (id_, resourcePrimKey, ");
			sb.append("version, indexable, status) values (");
			sb.append(id);
			sb.append(StringPool.COMMA_AND_SPACE);
			sb.append(resourcePrimKey);
			sb.append(StringPool.COMMA_AND_SPACE);
			sb.append(version);
			sb.append(StringPool.COMMA_AND_SPACE);
			sb.append(StringUtil.toUpperCase(String.valueOf(indexable)));
			sb.append(StringPool.COMMA_AND_SPACE);
			sb.append(WorkflowConstants.STATUS_APPROVED);
			sb.append(StringPool.CLOSE_PARENTHESIS);

			runSQL(sb.toString());

			version = MathUtil.format(version + 0.1, 1, 1);
		}

		StringBundler sb = new StringBundler();

		long entryId = CounterLocalServiceUtil.increment();

		_assetEntryEntryIds.add(entryId);

		sb.append("insert into AssetEntry (entryId, classNameId, classPK, ");
		sb.append("listable) values (");
		sb.append(entryId);
		sb.append(StringPool.COMMA_AND_SPACE);
		sb.append(_journalArticleClassNameId);
		sb.append(StringPool.COMMA_AND_SPACE);
		sb.append(resourcePrimKey);
		sb.append(StringPool.COMMA_AND_SPACE);
		sb.append("TRUE");
		sb.append(StringPool.CLOSE_PARENTHESIS);

		runSQL(sb.toString());

		return entryId;
	}

	private void _testUpgradeAsset(boolean[] indexableVersions)
		throws Exception {

		long entryId = _addJournalArticleEntries(indexableVersions);

		updateAssetEntries();

		try (Connection con = DataAccess.getUpgradeOptimizedConnection();
			PreparedStatement ps = con.prepareStatement(
				"select listable from AssetEntry where entryId = ?")) {

			ps.setLong(1, entryId);

			try (ResultSet rs = ps.executeQuery()) {
				Assert.assertTrue(rs.next());

				boolean listable = rs.getBoolean("listable");

				Assert.assertEquals(
					indexableVersions[indexableVersions.length - 1], listable);
			}
		}
	}

	private static long _journalArticleClassNameId;

	private final LinkedList<Long> _assetEntryEntryIds = new LinkedList<>();
	private final LinkedList<Long> _journalArticleIds = new LinkedList<>();

}