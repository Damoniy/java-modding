package com.damoniy.api.item;

import java.util.ArrayList;

import com.damoniy.common.items.ItemBase;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ItemHandler {

	public static ArrayList<Item> items = new ArrayList<Item>();
	
	public static final ItemBase teleporter = new ItemBase(new Item.Properties().group(ItemGroup.BREWING));
}
