package com.nobigcorps.strawberrymc.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;

public class ChiseledCauldronsToWaypointsProcedureProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "isregistered") instanceof BooleanProperty _getbp1 && (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getbp1)) {
			if (world instanceof ServerLevel _level) {
				
			}
		}
	}

	private static Property<?> getPropertyByName(BlockState state, String name) {
		for (Property<?> property : state.getProperties()) {
			if (property.getName().equals(name)) {
				return property;
			}
		}
		return null;
	}
}