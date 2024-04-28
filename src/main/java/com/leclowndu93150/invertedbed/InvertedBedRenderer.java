package com.leclowndu93150.invertedbed;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.world.level.block.entity.BlockEntity;

public class InvertedBedRenderer implements BlockEntityRenderer<InvertedBedBlockEntity> {
    public InvertedBedRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(InvertedBedBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {

    }
}
