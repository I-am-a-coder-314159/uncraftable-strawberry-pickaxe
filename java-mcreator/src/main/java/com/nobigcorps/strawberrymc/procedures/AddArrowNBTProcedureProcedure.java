package com.nobigcorps.strawberrymc.procedures;

import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.component.DataComponents;

import com.nobigcorps.strawberrymc.init.StrawberrymcModItems;

public class AddArrowNBTProcedureProcedure {
	public static void execute(Entity entity, ItemStack itemstack) {
		if (entity == null || itemstack == null || itemstack.isEmpty())
			return;
		ItemStack ingredient = entity instanceof Player player ? player.getInventory().getItem(2) : ItemStack.EMPTY;
		execute(entity, ingredient, itemstack);
	}

	public static void execute(Entity entity, ItemStack ingredient, ItemStack itemstack) {
		if (entity == null || ingredient == null || ingredient.isEmpty() || itemstack == null || itemstack.isEmpty())
			return;
		if (ingredient.is(Items.FLINT)) {
			{
				final String _tagName = "tip";
				final String _tagValue = "flint";
				CustomData.update(DataComponents.CUSTOM_DATA, itemstack, tag -> tag.putString(_tagName, _tagValue));
			}
		} else if (ingredient.is(Items.IRON_NUGGET)) {
			{
				final String _tagName = "tip";
				final String _tagValue = "iron_nugget";
				CustomData.update(DataComponents.CUSTOM_DATA, itemstack, tag -> tag.putString(_tagName, _tagValue));
			}
		} else if (ingredient.is(Items.COPPER_NUGGET)) {
			{
				final String _tagName = "tip";
				final String _tagValue = "copper_nugget";
				CustomData.update(DataComponents.CUSTOM_DATA, itemstack, tag -> tag.putString(_tagName, _tagValue));
			}
		} else if (ingredient.is(Items.GOLD_NUGGET)) {
			{
				final String _tagName = "tip";
				final String _tagValue = "gold_nugget";
				CustomData.update(DataComponents.CUSTOM_DATA, itemstack, tag -> tag.putString(_tagName, _tagValue));
			}
		} else if (ingredient.is(Items.PRISMARINE_SHARD)) {
			{
				final String _tagName = "tip";
				final String _tagValue = "prismarine_shard";
				CustomData.update(DataComponents.CUSTOM_DATA, itemstack, tag -> tag.putString(_tagName, _tagValue));
			}
		} else if (ingredient.is(Items.ECHO_SHARD)) {
			{
				final String _tagName = "tip";
				final String _tagValue = "echo_shard";
				CustomData.update(DataComponents.CUSTOM_DATA, itemstack, tag -> tag.putString(_tagName, _tagValue));
			}
		} else if (ingredient.is(Items.AMETHYST_SHARD)) {
			{
				final String _tagName = "tip";
				final String _tagValue = "amethyst_shard";
				CustomData.update(DataComponents.CUSTOM_DATA, itemstack, tag -> tag.putString(_tagName, _tagValue));
			}
		} else if (ingredient.is(Items.SHEARS)) {
			{
				final String _tagName = "tip";
				final String _tagValue = "shears";
				CustomData.update(DataComponents.CUSTOM_DATA, itemstack, tag -> tag.putString(_tagName, _tagValue));
			}
		}
		if (ingredient.is(Items.GUNPOWDER)) {
			{
				final String _tagName = "coating";
				final String _tagValue = "gunpowder";
				CustomData.update(DataComponents.CUSTOM_DATA, itemstack, tag -> tag.putString(_tagName, _tagValue));
			}
		} else if (ingredient.is(Items.GLOWSTONE_DUST)) {
			{
				final String _tagName = "coating";
				final String _tagValue = "glowstone_dust";
				CustomData.update(DataComponents.CUSTOM_DATA, itemstack, tag -> tag.putString(_tagName, _tagValue));
			}
		} else if (ingredient.is(Items.REDSTONE)) {
			{
				final String _tagName = "coating";
				final String _tagValue = "redstone";
				CustomData.update(DataComponents.CUSTOM_DATA, itemstack, tag -> tag.putString(_tagName, _tagValue));
			}
		} else if (ingredient.is(Items.SPONGE)) {
			{
				final String _tagName = "coating";
				final String _tagValue = "sponge";
				CustomData.update(DataComponents.CUSTOM_DATA, itemstack, tag -> tag.putString(_tagName, _tagValue));
			}
		} else if (ingredient.is(Items.ENDER_PEARL)) {
			{
				final String _tagName = "coating";
				final String _tagValue = "ender_pearl";
				CustomData.update(DataComponents.CUSTOM_DATA, itemstack, tag -> tag.putString(_tagName, _tagValue));
			}
		} else if (ingredient.is(StrawberrymcModItems.RECOVERY_PEARL.get())) {
			{
				final String _tagName = "coating";
				final String _tagValue = "recovery_pearl";
				CustomData.update(DataComponents.CUSTOM_DATA, itemstack, tag -> tag.putString(_tagName, _tagValue));
			}
		}
		if (ingredient.is(Items.WHEAT)) {
			{
				final String _tagName = "fletching";
				final String _tagValue = "wheat";
				CustomData.update(DataComponents.CUSTOM_DATA, itemstack, tag -> tag.putString(_tagName, _tagValue));
			}
		} else if (ingredient.is(Items.PHANTOM_MEMBRANE)) {
			{
				final String _tagName = "fletching";
				final String _tagValue = "phantom_membrane";
				CustomData.update(DataComponents.CUSTOM_DATA, itemstack, tag -> tag.putString(_tagName, _tagValue));
			}
		} else if (ingredient.is(Items.FEATHER)) {
			{
				final String _tagName = "fletching";
				final String _tagValue = "feather";
				CustomData.update(DataComponents.CUSTOM_DATA, itemstack, tag -> tag.putString(_tagName, _tagValue));
			}
		}
		if (ingredient.is(Items.BLAZE_ROD)) {
			{
				final String _tagName = "shaft";
				final String _tagValue = "blaze_rod";
				CustomData.update(DataComponents.CUSTOM_DATA, itemstack, tag -> tag.putString(_tagName, _tagValue));
			}
		} else if (ingredient.is(Items.BAMBOO)) {
			{
				final String _tagName = "shaft";
				final String _tagValue = "bamboo";
				CustomData.update(DataComponents.CUSTOM_DATA, itemstack, tag -> tag.putString(_tagName, _tagValue));
			}
		} else if (ingredient.is(Items.STICK)) {
			{
				final String _tagName = "shaft";
				final String _tagValue = "stick";
				CustomData.update(DataComponents.CUSTOM_DATA, itemstack, tag -> tag.putString(_tagName, _tagValue));
			}
		}
		if (ingredient.is(Items.POTION) || ingredient.is(Items.SPLASH_POTION) || ingredient.is(Items.LINGERING_POTION)) {
			CustomData.update(DataComponents.CUSTOM_DATA, itemstack, tag -> tag.putString("potion", "potion"));
		}
	}
}