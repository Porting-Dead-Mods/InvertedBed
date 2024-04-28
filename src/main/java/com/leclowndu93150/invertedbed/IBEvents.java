package com.leclowndu93150.invertedbed;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

import static com.leclowndu93150.invertedbed.Main.*;

@Mod.EventBusSubscriber(modid = Main.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class IBEvents {

    @SubscribeEvent
    public static void registerBERenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(INVERTED_BED_E.get() , InvertedBedRenderer::new);
    }

}
