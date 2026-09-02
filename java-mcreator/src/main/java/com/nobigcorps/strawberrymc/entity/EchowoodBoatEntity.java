package com.nobigcorps.strawberrymc.entity;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.entity.EntityType;

import com.nobigcorps.strawberrymc.init.StrawberrymcModItems;

public class EchowoodBoatEntity extends Boat {
	public EchowoodBoatEntity(EntityType<EchowoodBoatEntity> type, Level world) {
		super(type, world, StrawberrymcModItems.ECHOWOOD_BOAT);
	}
}