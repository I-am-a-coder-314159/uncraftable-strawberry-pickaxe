package com.nobigcorps.strawberrymc.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;

public class ChorineItem extends Item {
	public ChorineItem(Item.Properties properties) {
		super(properties);
	}

	@Override
	public boolean isFoil(ItemStack itemstack) {
		return true;
	}
}