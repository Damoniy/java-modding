package com.damoniy.common.blocks;

import com.damoniy.api.block.BlockHandler;
import com.damoniy.api.item.ItemHandler;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class BlockBase extends Block {

	protected String unlocalizedName;
	
	public BlockBase(Material materialIn, float hardnessIn, float resistanceIn, SoundType soundTypeIn,
			ItemGroup itemGroup, String name, int lightValueIn) {
		super(Properties.create(materialIn).hardnessAndResistance(hardnessIn, resistanceIn).sound(soundTypeIn));
		BlockHandler.blocks.add(this.getBlock().setRegistryName(name));
		Item item = new BlockItem(this, new Item.Properties().group(itemGroup)).setRegistryName(name);
		ItemHandler.items.add(item);
		this.unlocalizedName = name;
	}

	public BlockBase(Material materialIn, float hardnessAndResistance, SoundType soundTypeIn, ItemGroup itemGroup,
			String name, int lightValueIn) {
		super(Properties.create(materialIn).hardnessAndResistance(hardnessAndResistance).sound(soundTypeIn));
		BlockHandler.blocks.add(this.getBlock().setRegistryName(name));
		Item item = new BlockItem(this, new Item.Properties().group(itemGroup)).setRegistryName(name);
		ItemHandler.items.add(item);
		this.unlocalizedName = name;
	}

	public BlockBase(Properties props, String name, ItemGroup itemGroup) {
		super(props);
		BlockHandler.blocks.add(this.getBlock().setRegistryName(name));
		Item item = new BlockItem(this, new Item.Properties().group(itemGroup)).setRegistryName(name);
		ItemHandler.items.add(item);
		this.unlocalizedName = name;
	}

	public BlockBase() {
		super(Block.Properties.create(Material.AIR));
	}

	public String getBlockName() {
		return this.unlocalizedName;
	}
}
