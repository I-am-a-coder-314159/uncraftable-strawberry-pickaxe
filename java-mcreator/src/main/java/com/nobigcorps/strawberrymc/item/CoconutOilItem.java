package com.nobigcorps.strawberrymc.item;

import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;

public class CoconutOilItem extends Item {
	public CoconutOilItem(Item.Properties properties) {
		super(properties.food((new FoodProperties.Builder()).nutrition(1).saturationModifier(0.3f).alwaysEdible().build(), Consumables.defaultFood().animation(ItemUseAnimation.NONE).consumeSeconds(0F).build()).usingConvertsTo(Items.GLASS_BOTTLE));
	}
}