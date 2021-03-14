package com.damoniy.api.registry;

import com.damoniy.common.world.dimension.EdolasModDimension;

import io.netty.buffer.Unpooled;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder("damoniy")
public class DimensionRegistry 
{
	public static final EdolasModDimension LAYER_ONE = new EdolasModDimension("damoniy:edolas_world");
	
	@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents
	{
		@SubscribeEvent
		public static void registerModDimensions(final RegistryEvent.Register<ModDimension> event) 
		{
			event.getRegistry().registerAll
			(
				LAYER_ONE
			);
			
		}
	}
	
	public static void registerDimensions()
	{
		DimensionManager.registerDimension(new ResourceLocation("damoniy", "edolas_world"), LAYER_ONE, new PacketBuffer(Unpooled.buffer(16)), true);
	}
}