package com.nobigcorps.strawberrymc.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;

import java.util.Comparator;

public class CalculateRadiationDamageProcedureProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double dirX = 0;
		double dirY = 0;
		double dirZ = 0;
		double currentLength = 0;
		String blockInterceptList = "";
		{
			final Vec3 _center = new Vec3(x, y, z);
			for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(10 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList()) {
			}
		}
	}
}