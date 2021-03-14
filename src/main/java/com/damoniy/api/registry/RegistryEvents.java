package com.damoniy.api.registry;

import com.damoniy.api.block.BlockHandler;
import com.damoniy.api.item.ItemHandler;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistryEvents {

	@SubscribeEvent
	public static void onBlocksRegistry(final RegistryEvent.Register<Block> event) {
		for (Block block : BlockHandler.blocks) {
			event.getRegistry().register(block);
		}
	}

	@SubscribeEvent
	public static void onItemRegistry(final RegistryEvent.Register<Item> event) {
		for (Item item : ItemHandler.items) {
			event.getRegistry().register(item);
		}
	}
}
