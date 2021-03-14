package com.damoniy.api.block;

import java.util.ArrayList;

import com.damoniy.common.blocks.BlockBase;
import com.damoniy.common.blocks.BlockGrass;
import com.damoniy.common.blocks.BlockLog;
import com.damoniy.common.blocks.BlockPlanks;
import com.damoniy.common.blocks.BlockPortal;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;

public class BlockHandler {
	
	public static ArrayList<Block> blocks = new ArrayList<Block>();

	public static final BlockGrass grass = new BlockGrass().registerVariants();
	public static final BlockLog log = new BlockLog().registerVariants();
	public static final BlockPlanks planks = new BlockPlanks().registerVariants();

	public static final BlockBase dirt = new BlockBase(Material.EARTH, 0.5F, SoundType.GROUND, ItemGroup.BUILDING_BLOCKS, "block_dirt", 0);
	public static final BlockPortal portal = new BlockPortal();

}
