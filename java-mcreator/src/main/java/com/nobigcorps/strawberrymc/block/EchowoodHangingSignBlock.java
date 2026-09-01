package com.nobigcorps.strawberrymc.block;

import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.CeilingHangingSignBlock;

import com.nobigcorps.strawberrymc.init.StrawberrymcModWoodTypes;

public class EchowoodHangingSignBlock extends CeilingHangingSignBlock {
	public EchowoodHangingSignBlock(BlockBehaviour.Properties properties) {
		super(StrawberrymcModWoodTypes.ECHOWOOD_HANGING_SIGN_WOOD_TYPE, properties.sound(SoundType.HANGING_SIGN).strength(1.2f).noCollision().ignitedByLava().instrument(NoteBlockInstrument.BASS).forceSolidOn());
	}
}