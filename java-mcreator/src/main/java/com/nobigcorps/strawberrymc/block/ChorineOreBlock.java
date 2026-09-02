package com.nobigcorps.strawberrymc.block;

import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class ChorineOreBlock extends Block {
	public ChorineOreBlock(BlockBehaviour.Properties properties) {
		super(properties.strength(0.999999f, 1.245729943f).requiresCorrectToolForDrops().instrument(NoteBlockInstrument.BASEDRUM));
	}
}