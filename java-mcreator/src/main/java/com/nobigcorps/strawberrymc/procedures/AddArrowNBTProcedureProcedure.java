package com.nobigcorps.strawberrymc.procedures;

import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.component.DataComponents;

public class AddArrowNBTProcedureProcedure {
	public static void execute(Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		if (ItemStack.EMPTY.getItem() == Items.FLINT) {
			{
				final String _tagName = "tagName";
				final String _tagValue = "flint";
				CustomData.update(DataComponents.CUSTOM_DATA, itemstack, tag -> tag.putString(_tagName, _tagValue));
			}
		} else if (ItemStack.EMPTY.getItem() == Items.IRON_NUGGET) {
			{
				final String _tagName = "tagName";
				final String _tagValue = "iron_nugget";
				CustomData.update(DataComponents.CUSTOM_DATA, itemstack, tag -> tag.putString(_tagName, _tagValue));
			}
		} else if (ItemStack.EMPTY.getItem() == Items.COPPER_NUGGET) {
			{
				final String _tagName = "tagName";
				final String _tagValue = "iron_nugget";
				CustomData.update(DataComponents.CUSTOM_DATA, itemstack, tag -> tag.putString(_tagName, _tagValue));
			}
		} else if (ItemStack.EMPTY.getItem() == Items.IRON_NUGGET) {
			{
				final String _tagName = "tagName";
				final String _tagValue = "iron_nugget";
				CustomData.update(DataComponents.CUSTOM_DATA, itemstack, tag -> tag.putString(_tagName, _tagValue));
			}
		} else if (ItemStack.EMPTY.getItem() == Items.IRON_NUGGET) {
			{
				final String _tagName = "tagName";
				final String _tagValue = "iron_nugget";
				CustomData.update(DataComponents.CUSTOM_DATA, itemstack, tag -> tag.putString(_tagName, _tagValue));
			}
		} else if (ItemStack.EMPTY.getItem() == Items.IRON_NUGGET) {
			{
				final String _tagName = "tagName";
				final String _tagValue = "iron_nugget";
				CustomData.update(DataComponents.CUSTOM_DATA, itemstack, tag -> tag.putString(_tagName, _tagValue));
			}
		}
	}
}