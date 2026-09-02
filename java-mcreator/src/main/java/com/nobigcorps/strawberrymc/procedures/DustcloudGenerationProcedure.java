package com.nobigcorps.strawberrymc.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.SimpleParticleType;

import com.nobigcorps.strawberrymc.init.StrawberrymcModParticleTypes;

public class DustcloudGenerationProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (Mth.nextInt(RandomSource.create(), 1, 100) == 1) {
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (StrawberrymcModParticleTypes.DUSTCLOUD.get()), x, y, z, Mth.nextInt(RandomSource.create(), 5, 40), (Mth.nextInt(RandomSource.create(), 3, 8)), 3, (Mth.nextInt(RandomSource.create(), 3, 8)),
						1);
		}
	}
}