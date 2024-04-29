package com.leclowndu93150.invertedbed;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import static com.leclowndu93150.invertedbed.Main.INVERTED_BED_BE;

public class InvertedBedBlockEntity extends BlockEntity {
    private DyeColor color;

    public InvertedBedBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(INVERTED_BED_BE.get(), pPos, pBlockState);
        this.color = ((BedBlock)pBlockState.getBlock()).getColor();
    }

    public InvertedBedBlockEntity(BlockPos pPos, BlockState pBlockState, DyeColor pColor) {
        super(INVERTED_BED_BE.get(), pPos, pBlockState);
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
