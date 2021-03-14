package com.damoniy.common.blocks.enums;

public enum EnumGrass {
	EDOLAS(0, "edolas"),
	LIME(1, "lime");

	private String name;
	private int id;

	EnumGrass(int id, String name) {
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
