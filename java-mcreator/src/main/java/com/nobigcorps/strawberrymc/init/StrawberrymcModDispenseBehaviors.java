/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package com.nobigcorps.strawberrymc.init;

import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.core.dispenser.BoatDispenseItemBehavior;

@EventBusSubscriber
public class StrawberrymcModDispenseBehaviors {
	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			DispenserBlock.registerBehavior(StrawberrymcModItems.ECHOWOOD_BOAT.get(), new BoatDispenseItemBehavior(StrawberrymcModEntities.ECHOWOOD_BOAT.get()));
			DispenserBlock.registerBehavior(StrawberrymcModItems.ECHOWOOD_CHEST_BOAT.get(), new BoatDispenseItemBehavior(StrawberrymcModEntities.ECHOWOOD_CHEST_BOAT.get()));
		});
	}
}