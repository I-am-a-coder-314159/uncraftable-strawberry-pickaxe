package com.nobigcorps.strawberrymc;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

public class Registration {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(StrawberryMinecraft.MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(StrawberryMinecraft.MODID);

    public static final DeferredBlock<Block> PALM_LOG = BLOCKS.registerSimpleBlock(
            "palm_log",
            () -> BlockBehaviour.Properties.of().destroyTime(1.5f).explosionResistance(6.0f)
    );

    public static final DeferredItem<BlockItem> PALM_LOG_ITEM = ITEMS.registerSimpleBlockItem(
            PALM_LOG
    );

    public static final DeferredBlock<Block> PALM_WOOD = BLOCKS.registerSimpleBlock(
            "palm_wood",
            () -> BlockBehaviour.Properties.of().destroyTime(1.5f).explosionResistance(6.0f)
    );

    public static final DeferredItem<BlockItem> PALM_WOOD_ITEM = ITEMS.registerSimpleBlockItem(
            PALM_WOOD
    );
    public static final DeferredBlock<Block> PALM_PLANKS = BLOCKS.registerSimpleBlock(
            "palm_planks",
            () -> BlockBehaviour.Properties.of().destroyTime(1.5f).explosionResistance(6.0f)
    );

    public static final DeferredItem<BlockItem> PALM_PLANKS_ITEM = ITEMS.registerSimpleBlockItem(
            PALM_PLANKS
    );
    public static final DeferredItem<Item> COCONUT = ITEMS.registerSimpleItem(
            "coconut",
            () -> new Item.Properties()
    );

    public static void init(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
    }
}