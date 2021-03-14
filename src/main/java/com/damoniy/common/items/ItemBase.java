package com.damoniy.common.items;

import com.damoniy.api.item.ItemHandler;

import net.minecraft.item.Item;

public class ItemBase extends Item {

	public ItemBase(Properties properties) {
		super(properties);
		this.setRegistryName("damoniy:teleporter");
		ItemHandler.items.add(this);
	}
}
