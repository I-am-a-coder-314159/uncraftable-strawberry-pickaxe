package com.nobigcorps.strawberrymc.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;

import java.util.Comparator;

import com.nobigcorps.strawberrymc.init.StrawberrymcModBlocks;

public class SensegrassTickProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double targetDist = 0;
		targetDist = 10;
		if (!world.getEntitiesOfClass(Player.class, new AABB(Vec3.ZERO, Vec3.ZERO).move(new Vec3(x, y, z)).inflate(6 / 2d), e -> true).isEmpty()) {
			targetDist = (new Vec3(x, y, z))
					.distanceTo((new Vec3(((findEntityInWorldRange(world, Player.class, x, y, z, 6)).getX()), ((findEntityInWorldRange(world, Player.class, x, y, z, 6)).getY()), ((findEntityInWorldRange(world, Player.class, x, y, z, 6)).getZ()))));
		}
		if (targetDist <= 1.5) {
			world.setBlock(BlockPos.containing(x, y, z), StrawberrymcModBlocks.SENSEGRASS_RETRACT.get().defaultBlockState(), 3);
		} else {
			if (targetDist <= 3.5) {
				world.setBlock(BlockPos.containing(x, y, z), StrawberrymcModBlocks.SENSEGRASS_MID.get().defaultBlockState(), 3);
			} else if (targetDist > 3.5) {
				world.setBlock(BlockPos.containing(x, y, z), StrawberrymcModBlocks.SENSEGRASS_EXT.get().defaultBlockState(), 3);
			}
		}
		world.scheduleTick(BlockPos.containing(x, y, z), world.getBlockState(BlockPos.containing(x, y, z)).getBlock(), 3);
	}

	private static Entity findEntityInWorldRange(LevelAccessor world, Class<? extends Entity> clazz, double x, double y, double z, double range) {
		return (Entity) world.getEntitiesOfClass(clazz, AABB.ofSize(new Vec3(x, y, z), range, range, range), e -> true).stream().sorted(Comparator.comparingDouble(e -> e.distanceToSqr(x, y, z))).findFirst().orElse(null);
	}
}