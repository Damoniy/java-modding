package com.damoniy.common.blocks.enums;

public enum EnumLog {
	DARKWOOD(0, "dark"),
	GOLDENWOOD(1, "golden"),
	GREENWOOD(2, "cyan");

	private String name;
	private int id;

	EnumLog(int id, String name) {
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return this.name;
	}
	
	public int getId() {
		return this.id;
	}
}
