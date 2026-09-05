/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.nobigcorps.strawberrymc.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.core.registries.Registries;

import com.nobigcorps.strawberrymc.StrawberrymcMod;

public class StrawberrymcModPotions {
	public static final DeferredRegister<Potion> REGISTRY = DeferredRegister.create(Registries.POTION, StrawberrymcMod.MODID);
	public static final DeferredHolder<Potion, Potion> UNCRAFTING_POTION = REGISTRY.register("uncrafting_potion", () -> new Potion("uncrafting_potion", new MobEffectInstance(StrawberrymcModMobEffects.UNCRAFTABLE, 2000, 0, false, true)));
	public static final DeferredHolder<Potion, Potion> ENDILLARY_NECTAR = REGISTRY.register("endillary_nectar", () -> new Potion("endillary_nectar", new MobEffectInstance(StrawberrymcModMobEffects.NECTAR_INVULNERABILITY, 3600, 0, false, true)));
}