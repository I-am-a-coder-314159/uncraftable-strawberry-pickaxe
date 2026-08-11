/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.nobigcorps.strawberrymc.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredBlock;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

import java.util.function.Function;

import com.nobigcorps.strawberrymc.block.RadoniteOreBlock;
import com.nobigcorps.strawberrymc.block.RadoniteBlockBlock;
import com.nobigcorps.strawberrymc.StrawberrymcMod;

public class StrawberrymcModBlocks {
	public static final DeferredRegister.Blocks REGISTRY = DeferredRegister.createBlocks(StrawberrymcMod.MODID);
	public static final DeferredBlock<Block> RADONITE_ORE;
	public static final DeferredBlock<Block> RADONITE_BLOCK;
	static {
		RADONITE_ORE = register("radonite_ore", RadoniteOreBlock::new);
		RADONITE_BLOCK = register("radonite_block", RadoniteBlockBlock::new);
	}

	// Start of user code block custom blocks
	// End of user code block custom blocks
	private static <B extends Block> DeferredBlock<B> register(String name, Function<BlockBehaviour.Properties, ? extends B> supplier) {
		return REGISTRY.registerBlock(name, supplier);
	}
}