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

package com.liferay.portal.dao.sql.transformer;

import com.liferay.portal.kernel.dao.db.DB;

import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Manuel de la Peña
 */
public class SQLServerSQLTransformerLogic extends BaseSQLTransformerLogic {

	public SQLServerSQLTransformerLogic(DB db) {
		super(db);

		setFunctions(
			getBitwiseCheckFunction(), getBooleanFunction(),
			getCastClobTextFunction(), getCastLongFunction(),
			getCastTextFunction(), getInstrFunction(),
			getIntegerDivisionFunction(), getModFunction(),
			getNullDateFunction(), getSubstrFunction(), _getConcatFunction(),
			_getLengthFunction());
	}

	@Override
	protected String replaceCastText(Matcher matcher) {
		return matcher.replaceAll("CAST($1 AS NVARCHAR(MAX))");
	}

	private Function<String, String> _getConcatFunction() {
		return (String sql) -> {
			Matcher matcher = _concatPattern.matcher(sql);

			while (matcher.find()) {
				matcher.reset();

				sql = matcher.replaceAll("($1 || $2)");

				matcher = _concatPattern.matcher(sql);
			}

			return sql;
		};
	}

	private Function<String, String> _getLengthFunction() {
		return (String sql) -> {
			Matcher matcher = _lengthPattern.matcher(sql);

			return matcher.replaceAll("LEN(");
		};
	}

	private static final Pattern _concatPattern = Pattern.compile(
		"CONCAT\\(((?:'[^']*'|\"[^\"]*\"|[^,]*)*), " +
			"((?:'[^']*'|\"[^\"]*\"|[^,]*)*)\\)",
		Pattern.CASE_INSENSITIVE);
	private static final Pattern _lengthPattern = Pattern.compile(
		"LENGTH\\(", Pattern.CASE_INSENSITIVE);

}