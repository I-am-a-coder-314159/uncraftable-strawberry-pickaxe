package com.nobigcorps.strawberrymc.entity;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.vehicle.boat.ChestBoat;
import net.minecraft.world.entity.EntityType;

import com.nobigcorps.strawberrymc.init.StrawberrymcModItems;

public class EchowoodChestBoatEntity extends ChestBoat {
	public EchowoodChestBoatEntity(EntityType<EchowoodChestBoatEntity> type, Level world) {
		super(type, world, StrawberrymcModItems.ECHOWOOD_CHEST_BOAT);
	}
}