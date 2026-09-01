/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.nobigcorps.strawberrymc.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.event.BlockEntityTypeAddBlocksEvent;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.Block;
import net.minecraft.client.renderer.Sheets;

import java.util.function.Function;

import com.nobigcorps.strawberrymc.block.*;
import com.nobigcorps.strawberrymc.StrawberrymcMod;

@EventBusSubscriber
public class StrawberrymcModBlocks {
	public static final DeferredRegister.Blocks REGISTRY = DeferredRegister.createBlocks(StrawberrymcMod.MODID);
	public static final DeferredBlock<Block> RADONITE_ORE;
	public static final DeferredBlock<Block> RADONITE_BLOCK;
	public static final DeferredBlock<Block> ECHOWOOD_LOG;
	public static final DeferredBlock<Block> ECHOWOOD_WOOD;
	public static final DeferredBlock<Block> STRIPPED_ECHOWOOD_LOG;
	public static final DeferredBlock<Block> STRIPPED_ECHOWOOD_WOOD;
	public static final DeferredBlock<Block> ECHOWOOD_PLANKS;
	public static final DeferredBlock<Block> ECHOWOOD_LEAVES;
	public static final DeferredBlock<Block> ECHOWOOD_STAIRS;
	public static final DeferredBlock<Block> ECHOWOOD_SLAB;
	public static final DeferredBlock<Block> ECHOWOOD_FENCE;
	public static final DeferredBlock<Block> ECHOWOOD_FENCE_GATE;
	public static final DeferredBlock<Block> ECHOWOOD_DOOR;
	public static final DeferredBlock<Block> ECHOWOOD_TRAPDOOR;
	public static final DeferredBlock<Block> ECHOWOOD_PRESSURE_PLATE;
	public static final DeferredBlock<Block> ECHOWOOD_BUTTON;
	public static final DeferredBlock<Block> ECHOWOOD_SIGN;
	public static final DeferredBlock<Block> ECHOWOOD_WALL_SIGN;
	public static final DeferredBlock<Block> ECHOWOOD_HANGING_SIGN;
	public static final DeferredBlock<Block> ECHOWOOD_WALL_HANGING_SIGN;
	static {
		RADONITE_ORE = register("radonite_ore", RadoniteOreBlock::new);
		RADONITE_BLOCK = register("radonite_block", RadoniteBlockBlock::new);
		ECHOWOOD_LOG = register("echowood_log", EchowoodLogBlock::new);
		ECHOWOOD_WOOD = register("echowood_wood", EchowoodWoodBlock::new);
		STRIPPED_ECHOWOOD_LOG = register("stripped_echowood_log", StrippedEchowoodLogBlock::new);
		STRIPPED_ECHOWOOD_WOOD = register("stripped_echowood_wood", StrippedEchowoodWoodBlock::new);
		ECHOWOOD_PLANKS = register("echowood_planks", EchowoodPlanksBlock::new);
		ECHOWOOD_LEAVES = register("echowood_leaves", EchowoodLeavesBlock::new);
		ECHOWOOD_STAIRS = register("echowood_stairs", EchowoodStairsBlock::new);
		ECHOWOOD_SLAB = register("echowood_slab", EchowoodSlabBlock::new);
		ECHOWOOD_FENCE = register("echowood_fence", EchowoodFenceBlock::new);
		ECHOWOOD_FENCE_GATE = register("echowood_fence_gate", EchowoodFenceGateBlock::new);
		ECHOWOOD_DOOR = register("echowood_door", EchowoodDoorBlock::new);
		ECHOWOOD_TRAPDOOR = register("echowood_trapdoor", EchowoodTrapdoorBlock::new);
		ECHOWOOD_PRESSURE_PLATE = register("echowood_pressure_plate", EchowoodPressurePlateBlock::new);
		ECHOWOOD_BUTTON = register("echowood_button", EchowoodButtonBlock::new);
		ECHOWOOD_SIGN = register("echowood_sign", EchowoodSignBlock::new);
		ECHOWOOD_WALL_SIGN = register("echowood_wall_sign", EchowoodWallSignBlock::new);
		ECHOWOOD_HANGING_SIGN = register("echowood_hanging_sign", EchowoodHangingSignBlock::new);
		ECHOWOOD_WALL_HANGING_SIGN = register("echowood_wall_hanging_sign", EchowoodWallHangingSignBlock::new);
	}

	// Start of user code block custom blocks
	// End of user code block custom blocks
	private static <B extends Block> DeferredBlock<B> register(String name, Function<BlockBehaviour.Properties, ? extends B> supplier) {
		return REGISTRY.registerBlock(name, supplier);
	}

	@EventBusSubscriber(Dist.CLIENT)
	public static class BlocksClientSideHandler {
		@SubscribeEvent
		public static void clientSetup(FMLClientSetupEvent event) {
			Sheets.addWoodType(StrawberrymcModWoodTypes.ECHOWOOD_SIGN_WOOD_TYPE);
			Sheets.addWoodType(StrawberrymcModWoodTypes.ECHOWOOD_HANGING_SIGN_WOOD_TYPE);
		}
	}

	@SubscribeEvent
	public static void registerSigns(BlockEntityTypeAddBlocksEvent event) {
		event.modify(BlockEntityType.SIGN, ECHOWOOD_SIGN.get(), ECHOWOOD_WALL_SIGN.get());
		event.modify(BlockEntityType.HANGING_SIGN, ECHOWOOD_HANGING_SIGN.get(), ECHOWOOD_WALL_HANGING_SIGN.get());
	}
}