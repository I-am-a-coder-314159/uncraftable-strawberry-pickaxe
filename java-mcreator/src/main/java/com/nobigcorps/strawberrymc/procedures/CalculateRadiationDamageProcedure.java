package com.nobigcorps.strawberrymc.procedures;

import java.lang.Math;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

public class CalculateRadiationDamageProcedure {
	public static void execute() {
		execute(null, 0, 0, 0, null);
	}

	private final static Map<String, Integer> radioactiveBlocks = createRadioactiveBlocks();

	private static Map<String, Integer> createRadioactiveBlocks() {
		Map<String, Integer> blocks = new HashMap<>();
		blocks.put("strawberrymc:radonite_ore", 2);
		blocks.put("strawberrymc:radonite_block", 5);
		return blocks;
	}

	private static Holder<DamageType> getRadiationDamageType(Entity entity) {
		try {
			// Look up the custom radiation damage type from the registry
			var damageTypes = entity.level().registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE);
			Identifier radiationId = Identifier.parse("strawberrymc:radiation");
			ResourceKey<DamageType> radiationKey = ResourceKey.create(Registries.DAMAGE_TYPE, radiationId);
			return damageTypes.getOrThrow(radiationKey);
		} catch (Exception e) {
			return null;
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z) {
		execute(world, x, y, z, null);
	}

	@SuppressWarnings("deprecation")
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (world == null) {
			return;
		}

		int centerX = (int) Math.floor(x);
		int centerY = (int) Math.floor(y);
		int centerZ = (int) Math.floor(z);
		int radius = 5;
		int totalDmg = 0;

		for (int dx = -radius; dx <= radius; dx++) {
			for (int dy = -radius; dy <= radius; dy++) {
				for (int dz = -radius; dz <= radius; dz++) {
					BlockPos pos = new BlockPos(centerX + dx, centerY + dy, centerZ + dz);
					BlockState blockState = world.getBlockState(pos);
					String blockId = BuiltInRegistries.BLOCK.getKey(blockState.getBlock()).toString();
					Integer damageValue = radioactiveBlocks.get(blockId);
					if (damageValue != null) {
						totalDmg += damageValue;
					}
				}
			}
		}

		if (totalDmg > 0 && entity instanceof LivingEntity livingEntity) {
			Holder<DamageType> radiationDamage = getRadiationDamageType(entity);
			if (radiationDamage != null) {
				livingEntity.hurt(new DamageSource(radiationDamage), totalDmg);
			} else {
				// Fallback if custom damage type cannot be loaded
				livingEntity.hurt(entity.level().damageSources().generic(), totalDmg);
			}
		}
	}
}