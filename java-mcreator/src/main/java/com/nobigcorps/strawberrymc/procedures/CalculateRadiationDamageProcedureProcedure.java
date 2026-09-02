package com.nobigcorps.strawberrymc.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.BlockPos;

import java.util.Comparator;

import com.nobigcorps.strawberrymc.init.StrawberrymcModAttributes;

public class CalculateRadiationDamageProcedureProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double currentLength = 0;
		double totalDmg = 0;
		Vec3 direction = Vec3.ZERO;
		Vec3 currentposition = Vec3.ZERO;
		Vec3 minisculizeddirection = Vec3.ZERO;
		BlockState currentBlock = Blocks.AIR.defaultBlockState();
		{
			final Vec3 _center = new Vec3(x, y, z);
			for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(10 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList()) {
				direction = new Vec3((x - entityiterator.getX()), (y - entityiterator.getY()), (z - entityiterator.getZ()));
				currentposition = new Vec3(x, y, z);
				currentLength = direction.x() * direction.x() + direction.y() * direction.y() + direction.z() * direction.z();
				totalDmg = 0.2;
				direction = new Vec3((direction.x() / 30), (direction.y() / 30), (direction.z() / 30));
				for (int _i1 = 0; _i1 < 30; _i1++) {
					currentposition = currentposition.add(minisculizeddirection);
					currentBlock = (world.getBlockState(BlockPos.containing(currentposition.x(), currentposition.y(), currentposition.z())));
					if (currentBlock.is(BlockTags.create(Identifier.parse("strawberrymc:radiation_shielding_99")))) {
						totalDmg = totalDmg * 0.01;
					} else if (currentBlock.is(BlockTags.create(Identifier.parse("strawberrymc:radiation_shielding_95")))) {
						totalDmg = totalDmg * 0.05;
					} else if (currentBlock.is(BlockTags.create(Identifier.parse("strawberrymc:radiation_shielding_85")))) {
						totalDmg = totalDmg * 0.15;
					} else if (currentBlock.is(BlockTags.create(Identifier.parse("strawberrymc:radiation_shielding_88")))) {
						totalDmg = totalDmg * 0.12;
					} else if (currentBlock.is(BlockTags.create(Identifier.parse("strawberrymc:radiation_shielding_75")))) {
						totalDmg = totalDmg * 0.25;
					} else if (currentBlock.is(BlockTags.create(Identifier.parse("strawberrymc:radiation_shielding_55")))) {
						totalDmg = totalDmg * 0.45;
					} else if (currentBlock.is(BlockTags.create(Identifier.parse("strawberrymc:radiation_shielding_50")))) {
						totalDmg = totalDmg * 0.5;
					} else if (currentBlock.is(BlockTags.create(Identifier.parse("strawberrymc:radiation_shielding_48")))) {
						totalDmg = totalDmg * 0.52;
					} else if (currentBlock.is(BlockTags.create(Identifier.parse("strawberrymc:radiation_shielding_45")))) {
						totalDmg = totalDmg * 0.55;
					} else if (currentBlock.is(BlockTags.create(Identifier.parse("strawberrymc:radiation_shielding_42")))) {
						totalDmg = totalDmg * 0.58;
					} else if (currentBlock.is(BlockTags.create(Identifier.parse("strawberrymc:radiation_shielding_38")))) {
						totalDmg = totalDmg * 0.62;
					} else if (currentBlock.is(BlockTags.create(Identifier.parse("strawberrymc:radiation_shielding_35")))) {
						totalDmg = totalDmg * 0.65;
					} else if (currentBlock.is(BlockTags.create(Identifier.parse("strawberrymc:radiation_shielding_32")))) {
						totalDmg = totalDmg * 0.68;
					} else if (currentBlock.is(BlockTags.create(Identifier.parse("strawberrymc:radiation_shielding_30")))) {
						totalDmg = totalDmg * 0.7;
					} else if (currentBlock.is(BlockTags.create(Identifier.parse("strawberrymc:radiation_shielding_25")))) {
						totalDmg = totalDmg * 0.75;
					}
				}
				totalDmg = totalDmg * (1 - (entityiterator instanceof LivingEntity _livingEntity35 && _livingEntity35.getAttributes().hasAttribute(StrawberrymcModAttributes.RADIATION_TOLERANCE)
						? _livingEntity35.getAttribute(StrawberrymcModAttributes.RADIATION_TOLERANCE).getBaseValue()
						: 0));
				if (entityiterator instanceof LivingEntity _livingEntity37 && _livingEntity37.getAttributes().hasAttribute(StrawberrymcModAttributes.RADIATION_TOLERANCE))
					_livingEntity37.getAttribute(StrawberrymcModAttributes.RADIATION_TOLERANCE)
							.setBaseValue(((entityiterator instanceof LivingEntity _livingEntity36 && _livingEntity36.getAttributes().hasAttribute(StrawberrymcModAttributes.RADIATION_TOLERANCE)
									? _livingEntity36.getAttribute(StrawberrymcModAttributes.RADIATION_TOLERANCE).getBaseValue()
									: 0) + 0.01 * totalDmg));
				{
					Entity _ent = entityiterator;
					if (_ent.level() instanceof ServerLevel _serverLevel) {
						_ent.hurtServer(_serverLevel, new DamageSource(world.holderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, Identifier.parse("strawberrymc:radiation")))), (float) totalDmg);
					}
				}
			}
		}
	}
}