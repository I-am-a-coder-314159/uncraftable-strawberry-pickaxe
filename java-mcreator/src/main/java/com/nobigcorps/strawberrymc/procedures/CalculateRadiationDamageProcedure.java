package com.nobigcorps.strawberrymc.procedures;

import java.lang.Math;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

public class CalculateRadiationDamageProcedure {
	public static void execute() {
		execute(null, 0, 0, 0);
	}

	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (world == null) {
			return;
		}

		int centerX = (int) Math.floor(x);
		int centerY = (int) Math.floor(y);
		int centerZ = (int) Math.floor(z);
		int radius = 5;
		int nonAirBlocks = 0;

		for (int dx = -radius; dx <= radius; dx++) {
			for (int dy = -radius; dy <= radius; dy++) {
				for (int dz = -radius; dz <= radius; dz++) {
					BlockPos pos = new BlockPos(centerX + dx, centerY + dy, centerZ + dz);
					BlockState blockState = world.getBlockState(pos);
					if (!blockState.isAir()) {
						nonAirBlocks++;
					}
				}
			}
		}

		if (nonAirBlocks > 0) {
			// Use nonAirBlocks here for your radiation logic.
		}
	}
}