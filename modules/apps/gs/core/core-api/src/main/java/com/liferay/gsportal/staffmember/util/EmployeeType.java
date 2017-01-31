package com.liferay.gsportal.staffmember.util;

public enum EmployeeType {
	LIFERAY(1, "liferay"), PARTNER(2, "partner-contractor"), CUSTOMER(3, "customer");
	public String getLangKey() {
		return _key;
	}

	public int getValue() {
		return _value;
	}

	private EmployeeType(int value, String key) {
		_value = value;
		_key = key;
	}

	private String _key;
	private int _value;

}