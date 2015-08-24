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

package com.liferay.portal.search.internal;

import com.liferay.portal.kernel.search.GroupIndexer;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.model.Group;

import java.util.List;

/**
 * @author Andrew Betts
 */
public class GroupIndexerImpl implements GroupIndexer {

	@Override
	public void delete(Group group) throws SearchException {
		indexGroup(group, false);
	}

	@Override
	public void reindex(Group group) throws SearchException {
		indexGroup(group, true);
	}

	protected void indexGroup(Group group, boolean reindex)
		throws SearchException {

		List<Indexer<?>> indexers = IndexerRegistryUtil.getIndexers();

		for (Indexer<?> indexer : indexers) {
			if (reindex) {
				indexer.reindexByGroup(group);
			}
			else {
				indexer.deleteByGroup(group);
			}
		}
	}

}