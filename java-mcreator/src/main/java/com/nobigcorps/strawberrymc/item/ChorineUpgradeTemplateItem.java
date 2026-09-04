package com.nobigcorps.strawberrymc.item;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;

public class ChorineUpgradeTemplateItem extends Item {
	public ChorineUpgradeTemplateItem(Item.Properties properties) {
		super(properties.rarity(Rarity.EPIC).stacksTo(1));
	}

	@Override
	public float getDestroySpeed(ItemStack itemstack, BlockState state) {
		return 1.3f;
	}
}