package com.nobigcorps.strawberrymc.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import com.nobigcorps.strawberrymc.init.StrawberrymcModBlocks;

public class CoconutPlantProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		world.setBlock(BlockPos.containing(x, y, z), StrawberrymcModBlocks.COCONUT_SPROUT.get().defaultBlockState(), 3);
	}
}