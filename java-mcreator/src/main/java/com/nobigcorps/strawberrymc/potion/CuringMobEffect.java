package com.nobigcorps.strawberrymc.potion;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.ParticleTypes;

public class CuringMobEffect extends MobEffect {
	public CuringMobEffect() {
		super(MobEffectCategory.NEUTRAL, -16711715, mobEffectInstance -> ParticleTypes.END_ROD);
		this.withSoundOnAdded(BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("entity.shulker.teleport")));
	}
}