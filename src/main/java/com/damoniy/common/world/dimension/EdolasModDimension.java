package com.damoniy.common.world.dimension;

import java.util.function.BiFunction;

import io.netty.buffer.Unpooled;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.biome.ColumnFuzzedBiomeMagnifier;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.ModDimension;

public class EdolasModDimension extends ModDimension {

	@SuppressWarnings("deprecation")
	public static final DimensionType OVERWORLD = register("damoniy:edolas_world_type",
			new DimensionType(3, "", "", EdolasDimension::new, true, ColumnFuzzedBiomeMagnifier.INSTANCE, new EdolasModDimension("as") ,new PacketBuffer(Unpooled.buffer(16))));

	public EdolasModDimension(final String registryName) {
		super();
		this.setRegistryName("damoniy:edolas_world_type");
	}

	public static DimensionType getDimensionType() {
		return OVERWORLD;
	}

	@Override
	public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
		return EdolasDimension::new;
	}

	@SuppressWarnings("deprecation")
	private static DimensionType register(String key, DimensionType type) {
		return Registry.register(Registry.DIMENSION_TYPE, type.getId(), key, type);
	}
}