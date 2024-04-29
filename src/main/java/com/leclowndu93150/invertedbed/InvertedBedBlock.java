package com.leclowndu93150.invertedbed;


import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class InvertedBedBlock extends BedBlock {
    public InvertedBedBlock(DyeColor p_49454_, Properties p_49455_) {
        super(p_49454_, p_49455_);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new InvertedBedBlockEntity(pPos, pState);
    }
}
