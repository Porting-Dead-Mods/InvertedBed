package com.leclowndu93150.invertedbed.block;

import com.leclowndu93150.invertedbed.Main;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class InvertedBedBlockEntity extends BlockEntity {
    private DyeColor color;

    public InvertedBedBlockEntity(BlockPos pPos, BlockState pBlockState) {
        this(pPos, pBlockState, ((BedBlock) pBlockState.getBlock()).getColor());
    }

    public InvertedBedBlockEntity(BlockPos pPos, BlockState pBlockState, DyeColor pColor) {
        super(Main.INVERTED_BED.get(), pPos, pBlockState);
        this.color = pColor;
    }

    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    public DyeColor getColor() {
        return this.color;
    }

    public void setColor(DyeColor pColor) {
        this.color = pColor;
    }
}
