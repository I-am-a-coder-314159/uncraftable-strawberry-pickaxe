package com.nobigcorps.strawberrymc.potion;

import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.item.consume_effects.RemoveStatusEffectsConsumeEffect;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.Items;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.resources.Identifier;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.component.DataComponents;

import java.util.List;
import java.util.ArrayList;

import com.nobigcorps.strawberrymc.init.StrawberrymcModMobEffects;

@EventBusSubscriber
public class UncraftableMobEffect extends MobEffect {
	public UncraftableMobEffect() {
		super(MobEffectCategory.NEUTRAL, -14221057, mobEffectInstance -> ParticleTypes.PORTAL);
		this.withSoundOnAdded(BuiltInRegistries.SOUND_EVENT.getValue(Identifier.parse("block.end_portal_frame.fill")));
	}

	@SubscribeEvent
	public static void modifyItemComponents(ModifyDefaultComponentsEvent event) {
		Consumable original = Consumables.HONEY_BOTTLE;
		List<ConsumeEffect> onConsumeEffects = new ArrayList<>(original.onConsumeEffects());
		onConsumeEffects.add(new RemoveStatusEffectsConsumeEffect(StrawberrymcModMobEffects.UNCRAFTABLE));
		Consumable replacementConsumable = new Consumable(original.consumeSeconds(), original.animation(), original.sound(), original.hasConsumeParticles(), onConsumeEffects);
		event.modify(Items.HONEY_BOTTLE, (builder, _, _) -> builder.set(DataComponents.CONSUMABLE, replacementConsumable));
	}
}