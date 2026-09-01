package com.nobigcorps.strawberrymc.block;

import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.SoundType;

import com.nobigcorps.strawberrymc.init.StrawberrymcModWoodTypes;
import com.nobigcorps.strawberrymc.init.StrawberrymcModBlocks;

public class EchowoodWallHangingSignBlock extends WallHangingSignBlock {
	public EchowoodWallHangingSignBlock(BlockBehaviour.Properties properties) {
		super(StrawberrymcModWoodTypes.ECHOWOOD_HANGING_SIGN_WOOD_TYPE, properties.sound(SoundType.HANGING_SIGN).strength(1.2f).noCollision().ignitedByLava().instrument(NoteBlockInstrument.BASS).forceSolidOn()
				.overrideLootTable(StrawberrymcModBlocks.ECHOWOOD_HANGING_SIGN.get().getLootTable()).overrideDescription(StrawberrymcModBlocks.ECHOWOOD_HANGING_SIGN.get().getDescriptionId()));
	}
}