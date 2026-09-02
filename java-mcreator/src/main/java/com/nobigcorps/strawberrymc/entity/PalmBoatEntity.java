package com.nobigcorps.strawberrymc.entity;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.entity.EntityType;

import com.nobigcorps.strawberrymc.init.StrawberrymcModItems;

public class PalmBoatEntity extends Boat {
	public PalmBoatEntity(EntityType<PalmBoatEntity> type, Level world) {
		super(type, world, StrawberrymcModItems.PALM_BOAT);
	}
}