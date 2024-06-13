package com.leclowndu93150.invertedbed;

import com.leclowndu93150.invertedbed.block.InvertedBedRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.leclowndu93150.invertedbed.Main.*;

@Mod.EventBusSubscriber(modid = Main.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class IBClientEvents {

    @SubscribeEvent
    public static void registerBERenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(INVERTED_BED.get() , InvertedBedRenderer::new);
    }

    @SubscribeEvent
    public static void registerBS(EntityRenderersEvent.RegisterLayerDefinitions layerDefinitions) {
        layerDefinitions.registerLayerDefinition(InvertedBedRenderer.BED_FOOT, InvertedBedRenderer::createFootLayer);
        layerDefinitions.registerLayerDefinition(InvertedBedRenderer.BED_HEAD, InvertedBedRenderer::createHeadLayer);
    }
}
