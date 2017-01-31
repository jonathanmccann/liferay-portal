package com.liferay.gsportal.engagement.util;

public enum ProgressStatus {
	TENTATIVE(1, "TENTATIVE"), IN_PROGRESS(2, "IN_PROGRESS"),
	COMPLETED(3, "COMPLETED"), ARCHIVED(4, "ARCHIVED"),
	CANCELLED(5, "CANCELLED");

	ProgressStatus(int value, String key) {
		_value = value;
		_key = key;
	}

	public String getLangKey() {
		return _key;
	}

	public int getValue() {
		return _value;
	}

	private String _key;
	private int _value;
}
