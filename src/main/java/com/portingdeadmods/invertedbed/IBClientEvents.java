package com.portingdeadmods.invertedbed;

import com.portingdeadmods.invertedbed.block.InvertedBedRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

import static com.portingdeadmods.invertedbed.Main.*;

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
