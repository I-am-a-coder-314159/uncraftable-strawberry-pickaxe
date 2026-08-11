package com.nobigcorps.strawberrymc.block;

import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;

public class RadoniteOreBlock extends Block {
	public RadoniteOreBlock(BlockBehaviour.Properties properties) {
		super(properties.strength(3.03f, 3.0239760955f).requiresCorrectToolForDrops().instrument(NoteBlockInstrument.BASEDRUM));
	}
}