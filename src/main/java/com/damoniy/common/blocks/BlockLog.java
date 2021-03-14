package com.damoniy.common.blocks;

import com.damoniy.common.blocks.enums.EnumLog;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;

public class BlockLog extends BlockPillar {

	public BlockLog(String name) {
		super(Material.WOOD, 2.0F, SoundType.WOOD, ItemGroup.BUILDING_BLOCKS, name);
	}

	public BlockLog() {
	}

	public BlockLog registerVariants() {
		for (EnumLog type : EnumLog.values()) {
			new BlockLog("block_log_" + type.getName());
		}
		return this;
	}
}
