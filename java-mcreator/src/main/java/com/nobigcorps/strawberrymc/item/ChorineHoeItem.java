package com.nobigcorps.strawberrymc.item;

import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.HoeItem;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;

public class ChorineHoeItem extends HoeItem {
	private static final ToolMaterial TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 54, 3f, 0, 5, TagKey.create(Registries.ITEM, Identifier.parse("strawberrymc:chorine_hoe_repair_items")));

	public ChorineHoeItem(Item.Properties properties) {
		super(TOOL_MATERIAL, -1f, -3f, properties);
	}
}