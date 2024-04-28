package com.leclowndu93150.invertedbed;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

import static com.leclowndu93150.invertedbed.InvertedBed.*;

@Mod.EventBusSubscriber(modid = InvertedBed.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BedRendering {

    @SubscribeEvent
    public static void registerBERenderer(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(INVERTED_BED_E.get() ,BedRendering::new);
    }

}
