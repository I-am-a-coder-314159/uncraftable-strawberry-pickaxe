package com.nobigcorps.strawberrymc.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class ChorineUpgradeTemplateItem extends Item {
	public ChorineUpgradeTemplateItem(Item.Properties properties) {
		super(properties.rarity(Rarity.RARE));
	}
}