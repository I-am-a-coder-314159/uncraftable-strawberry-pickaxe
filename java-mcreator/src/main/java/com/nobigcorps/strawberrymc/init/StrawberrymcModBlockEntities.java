/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.nobigcorps.strawberrymc.init;

import net.neoforged.neoforge.transfer.item.WorldlyContainerWrapper;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.core.registries.BuiltInRegistries;

import com.nobigcorps.strawberrymc.block.entity.ChiseledCauldronBlockEntity;
import com.nobigcorps.strawberrymc.StrawberrymcMod;

@EventBusSubscriber
public class StrawberrymcModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, StrawberrymcMod.MODID);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ChiseledCauldronBlockEntity>> CHISELED_CAULDRON = register("chiseled_cauldron", StrawberrymcModBlocks.CHISELED_CAULDRON, ChiseledCauldronBlockEntity::new);

	// Start of user code block custom block entities
	// End of user code block custom block entities
	private static <T extends BlockEntity> DeferredHolder<BlockEntityType<?>, BlockEntityType<T>> register(String registryname, DeferredHolder<Block, Block> block, BlockEntityType.BlockEntitySupplier<T> supplier) {
		return REGISTRY.register(registryname, () -> new BlockEntityType(supplier, block.get()));
	}

	@SubscribeEvent
	public static void registerCapabilities(RegisterCapabilitiesEvent event) {
		event.registerBlockEntity(Capabilities.Item.BLOCK, CHISELED_CAULDRON.get(), WorldlyContainerWrapper::new);
		event.registerBlockEntity(Capabilities.Fluid.BLOCK, CHISELED_CAULDRON.get(), (blockEntity, side) -> blockEntity.getFluidTank());
	}
}