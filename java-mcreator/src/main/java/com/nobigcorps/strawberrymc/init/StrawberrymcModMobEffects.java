/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package com.nobigcorps.strawberrymc.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.core.registries.Registries;

import com.nobigcorps.strawberrymc.potion.UncraftableMobEffect;
import com.nobigcorps.strawberrymc.potion.CuringMobEffect;
import com.nobigcorps.strawberrymc.StrawberrymcMod;

public class StrawberrymcModMobEffects {
	public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(Registries.MOB_EFFECT, StrawberrymcMod.MODID);
	public static final DeferredHolder<MobEffect, MobEffect> UNCRAFTABLE = REGISTRY.register("uncraftable", UncraftableMobEffect::new);
	public static final DeferredHolder<MobEffect, MobEffect> NECTAR_INVULNERABILITY = REGISTRY.register("nectar_invulnerability", CuringMobEffect::new);
}