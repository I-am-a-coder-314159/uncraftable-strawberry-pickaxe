package com.nobigcorps.strawberrymc.item;

import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Item;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;

public class ChorineShovelItem extends ShovelItem {
	private static final ToolMaterial TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 54, 3f, 0, 5, TagKey.create(Registries.ITEM, Identifier.parse("strawberrymc:chorine_shovel_repair_items")));

	public ChorineShovelItem(Item.Properties properties) {
		super(TOOL_MATERIAL, 0f, -4f, properties);
	}
}