package com.nobigcorps.strawberrymc.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.Identifier;
import net.minecraft.core.BlockPos;

import java.util.Comparator;

public class CalculateRadiationDamageProcedureProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double dirX = 0;
		double dirY = 0;
		double dirZ = 0;
		double currentLength = 0;
		double thrY = 0;
		double thrX = 0;
		double thrZ = 0;
		double distance = 0;
		double totalDmg = 0;
		String blockInterceptList = "";
		Vec3 dir = Vec3.ZERO;
		Vec3 step = Vec3.ZERO;
		Vec3 pos = Vec3.ZERO;
		BlockState thisBlock = Blocks.AIR.defaultBlockState();
		{
			final Vec3 _center = new Vec3(x, y, z);
			for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(10 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList()) {
				dir = new Vec3((x - entityiterator.getX()), (y - entityiterator.getY()), (z - entityiterator.getZ()));
				pos = new Vec3(x, y, z);
				currentLength = dir.x() * dir.x() + dir.y() * dir.y() + dir.z() * dir.z();
				totalDmg = 0.05;
				if (currentLength > 10) {
					break;
				}
				for (int _i1 = 0; _i1 < 20; _i1++) {
					pos = pos.add((step.scale(0.05)));
					thisBlock = (world.getBlockState(BlockPos.containing(pos.x(), dir.y(), dir.z())));
					if (thisBlock.is(BlockTags.create(Identifier.parse("strawberrymc:radiation_shielding_99")))) {
						totalDmg = totalDmg * 0.01;
					} else if (thisBlock.is(BlockTags.create(Identifier.parse("strawberrymc:radiation_shielding_95")))) {
						totalDmg = totalDmg * 0.05;
					} else if (thisBlock.is(BlockTags.create(Identifier.parse("strawberrymc:radiation_shielding_88")))) {
						totalDmg = totalDmg * 0.12;
					} else if (thisBlock.is(BlockTags.create(Identifier.parse("strawberrymc:radiation_shielding_85")))) {
						totalDmg = totalDmg * 0.15;
					} else if (thisBlock.is(BlockTags.create(Identifier.parse("strawberrymc:radiation_shielding_75")))) {
						totalDmg = totalDmg * 0.25;
					} else if (thisBlock.is(BlockTags.create(Identifier.parse("strawberrymc:radiation_shielding_55")))) {
						totalDmg = totalDmg * 0.45;
					} else if (thisBlock.is(BlockTags.create(Identifier.parse("strawberrymc:radiation_shielding_50")))) {
						totalDmg = totalDmg * 0.5;
					} else if (thisBlock.is(BlockTags.create(Identifier.parse("strawberrymc:radiation_shielding_48")))) {
						totalDmg = totalDmg * 0.52;
					} else if (thisBlock.is(BlockTags.create(Identifier.parse("strawberrymc:radiation_shielding_45")))) {
						totalDmg = totalDmg * 0.55;
					} else if (thisBlock.is(BlockTags.create(Identifier.parse("strawberrymc:radiation_shielding_42")))) {
						totalDmg = totalDmg * 0.58;
					} else if (thisBlock.is(BlockTags.create(Identifier.parse("strawberrymc:radiation_shielding_38")))) {
						totalDmg = totalDmg * 0.62;
					} else if (thisBlock.is(BlockTags.create(Identifier.parse("strawberrymc:radiation_shielding_35")))) {
						totalDmg = totalDmg * 0.65;
					} else if (thisBlock.is(BlockTags.create(Identifier.parse("strawberrymc:radiation_shielding_30")))) {
						totalDmg = totalDmg * 0.70;
					} else if (thisBlock.is(BlockTags.create(Identifier.parse("strawberrymc:radiation_shielding_25")))) {
						totalDmg = totalDmg * 0.75;
					}
				}
			}
		}
	}
}