package com.liferay.gsportal.engagement.util;

public enum Difficulty {
	NONE(1, "NONE"), EASY(2, "EASY"),
	MEDIUM(3, "MEDIUM"), DIFFICULT(4, "DIFFICULT");

	Difficulty(int value, String key) {
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
