package com.damoniy.api.events;

import com.damoniy.common.world.dimension.EdolasModDimension;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.dimension.DimensionType;

public class TeleporterManager 
{
	public static void teleport(Entity entity)
	{
        if(!entity.world.isRemote)
        {
            DimensionType type = EdolasModDimension.getDimensionType();
            if(entity.getRidingEntity() == null && !entity.isBeingRidden())
            {
            	entity.setPortal(new BlockPos(entity.getPosX(), entity.getPosY(), entity.getPosZ()));
            	
                if (entity.timeUntilPortal > 0) 
                {
                    entity.timeUntilPortal = 10;
                }
                else if(entity.dimension != type)
                {
                    entity.timeUntilPortal = 10;
                    entity.changeDimension(type);
                }
                else if(entity.dimension == type)
                {
                    entity.timeUntilPortal = 10;
                    entity.changeDimension(DimensionType.OVERWORLD);
                }
            }
        }
    }
}