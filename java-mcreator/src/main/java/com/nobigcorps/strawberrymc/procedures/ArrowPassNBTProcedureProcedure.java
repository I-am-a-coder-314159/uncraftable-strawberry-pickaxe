package com.nobigcorps.strawberrymc.procedures;

import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;

@EventBusSubscriber
public class ArrowPassNBTProcedureProcedure {
	@SubscribeEvent
	public static void onEntitySpawned(EntityJoinLevelEvent event) {
		execute(event);
	}

	public static void execute() {
		execute(null);
	}

	private static void execute(@Nullable Event event) {
		if (!(event instanceof EntityJoinLevelEvent joinEvent))
			return;
		if (!(joinEvent.getEntity() instanceof AbstractArrow arrow))
			return;
		if (!(arrow.getOwner() instanceof LivingEntity shooter))
			return;
		ItemStack arrowStack = arrow.getPickupItemStackOrigin();
		AddArrowNBTProcedureProcedure.execute(shooter, shooter.getMainHandItem(), arrowStack);
		AddArrowNBTProcedureProcedure.execute(shooter, shooter.getOffhandItem(), arrowStack);
	}
}