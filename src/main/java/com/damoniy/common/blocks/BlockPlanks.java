package com.damoniy.common.blocks;

import com.damoniy.common.blocks.enums.EnumLog;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.ItemGroup;

public class BlockPlanks extends BlockBase {

	public BlockPlanks(String name) {
		super(Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD),
				name, ItemGroup.BUILDING_BLOCKS);
	}
	
	public BlockPlanks() {
	}
	
	public BlockPlanks registerVariants() {
		for (EnumLog type : EnumLog.values()) {
			new BlockPlanks("block_planks_" + type.getName());
		}
		return this;
	}
}
