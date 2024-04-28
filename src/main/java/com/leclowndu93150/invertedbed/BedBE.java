package com.leclowndu93150.invertedbed;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import static com.leclowndu93150.invertedbed.InvertedBed.INVERTED_BED_E;

public class BedBE extends BlockEntity {
    public BedBE(BlockPos pos, BlockState state) {
        super(INVERTED_BED_E.get(), pos, state);
    }
}
