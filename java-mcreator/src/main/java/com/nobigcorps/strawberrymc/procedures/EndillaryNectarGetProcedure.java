package com.nobigcorps.strawberrymc.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.GameType;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import com.nobigcorps.strawberrymc.init.StrawberrymcModPotions;
import com.nobigcorps.strawberrymc.init.StrawberrymcModBlocks;

public class EndillaryNectarGetProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == Items.GLASS_BOTTLE
				&& (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == StrawberrymcModBlocks.ENDILLARY_WITH_NECTAR.get()) {
			world.setBlock(BlockPos.containing(x, y, z), StrawberrymcModBlocks.ENDILLARY.get().defaultBlockState(), 3);
			if (!(entity instanceof Player _plr5 && _plr5.gameMode() == GameType.CREATIVE)) {
				(entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).shrink(1);
				if (entity instanceof Player _player) {
					ItemStack _setstack = (PotionContents.createItemStack(Items.POTION, StrawberrymcModPotions.ENDILLARY_NECTAR)).copy();
					_setstack.setCount(1);
					_player.getInventory().placeItemBackInInventory(_setstack);
				}
			}
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, BlockPos.containing(x, y, z), BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("item.bottle.fill")), SoundSource.NEUTRAL, 1, 1);
				} else {
					_level.playLocalSound(x, y, z, BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("item.bottle.fill")), SoundSource.NEUTRAL, 1, 1, false);
				}
			}
		}
	}
}