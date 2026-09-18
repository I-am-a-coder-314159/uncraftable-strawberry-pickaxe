package com.nobigcorps.strawberrymc.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;

import com.nobigcorps.strawberrymc.init.StrawberrymcModItems;

public class ElectricParticleProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == StrawberrymcModItems.SMITE_VIAL.get()) {
			if (world instanceof ServerLevel _level)
				_level.sendParticles(ParticleTypes.END_ROD, x, y, z, 5, 1, 1, 1, 1);
			if (world instanceof ServerLevel _level)
				_level.sendParticles(ParticleTypes.ELECTRIC_SPARK, x, y, z, 5, 1, 1, 1, 1);
			if (world instanceof ServerLevel _level)
				_level.sendParticles(ParticleTypes.ANGRY_VILLAGER, x, y, z, 5, 1, 1, 1, 1);
		}
	}
}