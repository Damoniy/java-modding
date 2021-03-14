package com.damoniy.api;

import com.damoniy.api.registry.DimensionRegistry;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("damoniy")
public class Damoniy
{
    public Damoniy() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::preInit);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::dispatchIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::receiveIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientInit);

        MinecraftForge.EVENT_BUS.register(this);
    }
    
    private void preInit(final FMLCommonSetupEvent event) {
        // some preInit code
    	DimensionRegistry.registerDimensions();
    }

    private void onClientInit(final FMLClientSetupEvent event) {
        // do something when the client starts
    }

    private void dispatchIMC(final InterModEnqueueEvent event) {
        // dispatch InterModComms
    }

    private void receiveIMC(final InterModProcessEvent event) {
        // receive InterModComms
    }
    
    @SubscribeEvent
    public void onServerInit(FMLServerStartingEvent event) {
        // do something when the server starts
    }
}
