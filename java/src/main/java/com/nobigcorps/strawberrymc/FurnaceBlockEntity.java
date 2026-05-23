/*package com.nobigcorps.strawberrymc;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.FurnaceMenu;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.crafting.RecipeType;

public class StrawberryFurnaceBlockEntity extends AbstractFurnaceBlockEntity {

    public StrawberryFurnaceBlockEntity(BlockPos pos, BlockState state) {
        // Sets up your custom furnace type using the standard SMELTING recipe engine
        super(Registration.STRAWBERRY_FURNACE_BE.get(), pos, state, RecipeType.SMELTING);
    }

    @Override
    protected Component getDefaultName() {
        return Component.literal("Yummy Strawberry Furnace");
    }

    // CRUCIAL: Override container size. Default furnace is 3, we expand it to 4 slots!
    @Override
    public int getContainerSize() {
        return 4;
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory player) {
        // Creates the link to our custom menu container handler
        return new StrawberryFurnaceMenu(id, player, this, this.dataAccess);
    }
}
*/