package com.nobigcorps.strawberrymc.item;

import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.Item;
import net.minecraft.world.InteractionResult;

import com.nobigcorps.strawberrymc.procedures.CoconutPlantProcedure;

public class RawCoconutItem extends Item {
	public RawCoconutItem(Item.Properties properties) {
		super(properties.stacksTo(16));
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		super.useOn(context);
		CoconutPlantProcedure.execute(context.getLevel(), context.getClickedPos().getX(), context.getClickedPos().getY(), context.getClickedPos().getZ());
		return InteractionResult.SUCCESS;
	}
}