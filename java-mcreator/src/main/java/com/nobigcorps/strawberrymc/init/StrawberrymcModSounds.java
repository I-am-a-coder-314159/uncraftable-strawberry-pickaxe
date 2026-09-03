/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.nobigcorps.strawberrymc.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.Registries;

import com.nobigcorps.strawberrymc.StrawberrymcMod;

public class StrawberrymcModSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(Registries.SOUND_EVENT, StrawberrymcMod.MODID);
	public static final DeferredHolder<SoundEvent, SoundEvent> DESERT_TEMPLE_BOSS = REGISTRY.register("desert_temple_boss", () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("strawberrymc", "desert_temple_boss")));
}