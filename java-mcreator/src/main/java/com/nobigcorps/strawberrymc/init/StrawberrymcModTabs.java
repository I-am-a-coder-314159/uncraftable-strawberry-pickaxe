/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.nobigcorps.strawberrymc.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.core.registries.Registries;

import com.nobigcorps.strawberrymc.StrawberrymcMod;

@EventBusSubscriber
public class StrawberrymcModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, StrawberrymcMod.MODID);

	@SubscribeEvent
	public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {
		if (tabData.getTabKey() == CreativeModeTabs.INGREDIENTS) {
			tabData.accept(StrawberrymcModItems.RADONITE_INGOT.get());
		} else if (tabData.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
			tabData.accept(StrawberrymcModBlocks.RADONITE_ORE.get().asItem());
			tabData.accept(StrawberrymcModBlocks.RADONITE_BLOCK.get().asItem());
		} else if (tabData.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
			tabData.accept(StrawberrymcModItems.RADONITE_PICKAXE.get());
			tabData.accept(StrawberrymcModItems.RADONITE_AXE.get());
			tabData.accept(StrawberrymcModItems.RADONITE_SHOVEL.get());
			tabData.accept(StrawberrymcModItems.RADONITE_HOE.get());
		} else if (tabData.getTabKey() == CreativeModeTabs.COMBAT) {
			tabData.accept(StrawberrymcModItems.RADONITE_SWORD.get());
			tabData.accept(StrawberrymcModItems.RADONITE_ARMOR_HELMET.get());
			tabData.accept(StrawberrymcModItems.RADONITE_ARMOR_CHESTPLATE.get());
			tabData.accept(StrawberrymcModItems.RADONITE_ARMOR_LEGGINGS.get());
			tabData.accept(StrawberrymcModItems.RADONITE_ARMOR_BOOTS.get());
		}
	}
}