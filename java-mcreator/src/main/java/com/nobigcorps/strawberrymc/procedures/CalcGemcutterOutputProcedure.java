package com.nobigcorps.strawberrymc.procedures;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import com.nobigcorps.strawberrymc.init.StrawberrymcModMenus;
import com.nobigcorps.strawberrymc.init.StrawberrymcModItems;

public class CalcGemcutterOutputProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof StrawberrymcModMenus.MenuAccessor _menu0 ? _menu0.getSlots().get(2).getItem() : ItemStack.EMPTY).getItem() == StrawberrymcModItems.COCONUT_OIL.get()) {
			if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof StrawberrymcModMenus.MenuAccessor _menu2 ? _menu2.getSlots().get(0).getItem() : ItemStack.EMPTY).getItem() == StrawberrymcModItems.RAW_DIAMOND.get()) {
				if (entity instanceof Player _player && _player.containerMenu instanceof StrawberrymcModMenus.MenuAccessor _menu) {
					ItemStack _setstack4 = new ItemStack(Items.DIAMOND).copy();
					_setstack4.setCount(1);
					_menu.getSlots().get(1).set(_setstack4);
					ItemStack _setstack5 = new ItemStack(Blocks.AIR).copy();
					_setstack5.setCount(1);
					_menu.getSlots().get(0).set(_setstack5);
					_player.containerMenu.broadcastChanges();
				}
			} else {
				if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof StrawberrymcModMenus.MenuAccessor _menu6 ? _menu6.getSlots().get(0).getItem() : ItemStack.EMPTY).getItem() == Blocks.AMETHYST_BLOCK.asItem()) {
					if (entity instanceof Player _player && _player.containerMenu instanceof StrawberrymcModMenus.MenuAccessor _menu) {
						ItemStack _setstack8 = new ItemStack(Items.AMETHYST_SHARD).copy();
						_setstack8.setCount(1);
						_menu.getSlots().get(1).set(_setstack8);
						ItemStack _setstack9 = new ItemStack(Blocks.AIR).copy();
						_setstack9.setCount(1);
						_menu.getSlots().get(0).set(_setstack9);
						_player.containerMenu.broadcastChanges();
					}
				} else {
					if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof StrawberrymcModMenus.MenuAccessor _menu10 ? _menu10.getSlots().get(0).getItem() : ItemStack.EMPTY).getItem() == Items.LAPIS_LAZULI) {
						if (entity instanceof Player _player && _player.containerMenu instanceof StrawberrymcModMenus.MenuAccessor _menu) {
							ItemStack _setstack12 = new ItemStack(Items.BLUE_DYE).copy();
							_setstack12.setCount(1);
							_menu.getSlots().get(1).set(_setstack12);
							ItemStack _setstack13 = new ItemStack(Blocks.AIR).copy();
							_setstack13.setCount(1);
							_menu.getSlots().get(0).set(_setstack13);
							_player.containerMenu.broadcastChanges();
						}
					} else {
						if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof StrawberrymcModMenus.MenuAccessor _menu14 ? _menu14.getSlots().get(0).getItem() : ItemStack.EMPTY)
								.getItem() == StrawberrymcModItems.GREEN_BERYL.get()) {
							if (entity instanceof Player _player && _player.containerMenu instanceof StrawberrymcModMenus.MenuAccessor _menu) {
								ItemStack _setstack16 = new ItemStack(Items.EMERALD).copy();
								_setstack16.setCount(1);
								_menu.getSlots().get(1).set(_setstack16);
								ItemStack _setstack17 = new ItemStack(Blocks.AIR).copy();
								_setstack17.setCount(1);
								_menu.getSlots().get(0).set(_setstack17);
								_player.containerMenu.broadcastChanges();
							}
						}
					}
				}
			}
		}
	}
}