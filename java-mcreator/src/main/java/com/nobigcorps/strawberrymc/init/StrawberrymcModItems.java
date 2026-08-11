/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.nobigcorps.strawberrymc.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

import java.util.function.Function;

import com.nobigcorps.strawberrymc.item.*;
import com.nobigcorps.strawberrymc.StrawberrymcMod;

public class StrawberrymcModItems {
	public static final DeferredRegister.Items REGISTRY = DeferredRegister.createItems(StrawberrymcMod.MODID);
	public static final DeferredItem<Item> RADONITE_INGOT;
	public static final DeferredItem<Item> RADONITE_ORE;
	public static final DeferredItem<Item> RADONITE_BLOCK;
	public static final DeferredItem<Item> RADONITE_PICKAXE;
	public static final DeferredItem<Item> RADONITE_AXE;
	public static final DeferredItem<Item> RADONITE_SWORD;
	public static final DeferredItem<Item> RADONITE_SHOVEL;
	public static final DeferredItem<Item> RADONITE_HOE;
	public static final DeferredItem<Item> RADONITE_ARMOR_HELMET;
	public static final DeferredItem<Item> RADONITE_ARMOR_CHESTPLATE;
	public static final DeferredItem<Item> RADONITE_ARMOR_LEGGINGS;
	public static final DeferredItem<Item> RADONITE_ARMOR_BOOTS;
	static {
		RADONITE_INGOT = register("radonite_ingot", RadoniteIngotItem::new);
		RADONITE_ORE = block(StrawberrymcModBlocks.RADONITE_ORE);
		RADONITE_BLOCK = block(StrawberrymcModBlocks.RADONITE_BLOCK);
		RADONITE_PICKAXE = register("radonite_pickaxe", RadonitePickaxeItem::new);
		RADONITE_AXE = register("radonite_axe", RadoniteAxeItem::new);
		RADONITE_SWORD = register("radonite_sword", RadoniteSwordItem::new);
		RADONITE_SHOVEL = register("radonite_shovel", RadoniteShovelItem::new);
		RADONITE_HOE = register("radonite_hoe", RadoniteHoeItem::new);
		RADONITE_ARMOR_HELMET = register("radonite_armor_helmet", RadoniteArmorItem.Helmet::new);
		RADONITE_ARMOR_CHESTPLATE = register("radonite_armor_chestplate", RadoniteArmorItem.Chestplate::new);
		RADONITE_ARMOR_LEGGINGS = register("radonite_armor_leggings", RadoniteArmorItem.Leggings::new);
		RADONITE_ARMOR_BOOTS = register("radonite_armor_boots", RadoniteArmorItem.Boots::new);
	}

	// Start of user code block custom items
	// End of user code block custom items
	private static <I extends Item> DeferredItem<I> register(String name, Function<Item.Properties, ? extends I> supplier) {
		return REGISTRY.registerItem(name, supplier, Item.Properties::new);
	}

	private static DeferredItem<Item> block(DeferredHolder<Block, Block> block) {
		return block(block, new Item.Properties());
	}

	private static DeferredItem<Item> block(DeferredHolder<Block, Block> block, Item.Properties properties) {
		return REGISTRY.registerItem(block.getId().getPath(), prop -> new BlockItem(block.get(), prop), () -> properties);
	}
}