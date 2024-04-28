package com.leclowndu93150.invertedbed;

import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.blockentity.BedRenderer;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;

import static com.leclowndu93150.invertedbed.Main.*;

@Mod.EventBusSubscriber(modid = Main.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class IBEvents {

    @SubscribeEvent
    public static void registerBERenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(INVERTED_BED_BE.get() , InvertedBedRenderer::new);
    }

    @SubscribeEvent
    public static void registerBS(EntityRenderersEvent.RegisterLayerDefinitions layerDefinitions) {
        layerDefinitions.registerLayerDefinition(InvertedBedRenderer.BED_FOOT, InvertedBedRenderer::createFootLayer);
        layerDefinitions.registerLayerDefinition(InvertedBedRenderer.BED_HEAD, InvertedBedRenderer::createHeadLayer);
    }
}
