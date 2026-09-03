package com.nobigcorps.strawberrymc.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;

import com.nobigcorps.strawberrymc.init.StrawberrymcModItems;
import com.nobigcorps.strawberrymc.init.StrawberrymcModBlocks;

public class CalcGemcutterOutputProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (ItemStack.EMPTY.getItem() == StrawberrymcModItems.COCONUT_OIL.get()) {
			if (ItemStack.EMPTY.getItem() == StrawberrymcModItems.RAW_DIAMOND.get()) {
			} else {
				if (ItemStack.EMPTY.getItem() == Blocks.AMETHYST_BLOCK.asItem()) {
				} else {
					if (ItemStack.EMPTY.getItem() == Items.LAPIS_LAZULI) {
					} else {
						if (ItemStack.EMPTY.getItem() == StrawberrymcModItems.GREEN_BERYL.get()) {
						} else {
							if (ItemStack.EMPTY.getItem() == StrawberrymcModBlocks.CHORINE_BLOCK.get().asItem()) {
							}
						}
					}
				}
			}
		}
	}
}