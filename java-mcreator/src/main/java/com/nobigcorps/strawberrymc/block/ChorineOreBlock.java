package com.nobigcorps.strawberrymc.block;

import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;

public class ChorineOreBlock extends Block {
	public ChorineOreBlock(BlockBehaviour.Properties properties) {
		super(properties.sound(SoundType.AMETHYST).strength(13.2f, 9.8148438807f).requiresCorrectToolForDrops().instrument(NoteBlockInstrument.BASEDRUM));
	}
}