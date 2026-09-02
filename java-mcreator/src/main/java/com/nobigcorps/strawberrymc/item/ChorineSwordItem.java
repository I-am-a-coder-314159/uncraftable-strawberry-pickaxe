package com.nobigcorps.strawberrymc.item;

import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;

public class ChorineSwordItem extends Item {
	private static final ToolMaterial TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 54, 3f, 0, 5, TagKey.create(Registries.ITEM, Identifier.parse("strawberrymc:chorine_sword_repair_items")));

	public ChorineSwordItem(Item.Properties properties) {
		super(properties.sword(TOOL_MATERIAL, 1f, -3f));
	}
}