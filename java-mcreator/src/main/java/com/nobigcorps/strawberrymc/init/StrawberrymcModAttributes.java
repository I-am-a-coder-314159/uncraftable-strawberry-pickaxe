/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.nobigcorps.strawberrymc.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.core.registries.BuiltInRegistries;

import com.nobigcorps.strawberrymc.StrawberrymcMod;

@EventBusSubscriber
public class StrawberrymcModAttributes {
	public static final DeferredRegister<Attribute> REGISTRY = DeferredRegister.create(BuiltInRegistries.ATTRIBUTE, StrawberrymcMod.MODID);
	public static final DeferredHolder<Attribute, Attribute> RADIATION_TOLERANCE = REGISTRY.register("radiation_tolerance", () -> new RangedAttribute("attribute.strawberrymc.radiation_tolerance", 0d, 0d, 1d).setSyncable(true));

	@SubscribeEvent
	public static void addAttributes(EntityAttributeModificationEvent event) {
		event.getTypes().forEach(entity -> event.add(entity, RADIATION_TOLERANCE));
	}
}