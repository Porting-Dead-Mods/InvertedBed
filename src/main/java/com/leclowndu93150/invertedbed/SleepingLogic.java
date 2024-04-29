package com.leclowndu93150.invertedbed;

import net.minecraft.server.level.ServerLevel;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.player.PlayerWakeUpEvent;
import net.neoforged.neoforge.event.entity.player.SleepingLocationCheckEvent;
import net.neoforged.neoforge.event.entity.player.SleepingTimeCheckEvent;

import static com.leclowndu93150.invertedbed.Main.INVERTED_BED;
import static com.leclowndu93150.invertedbed.Main.MODID;

@Mod.EventBusSubscriber(modid = MODID)
public class SleepingLogic {
    @SubscribeEvent
    public static void onTrySleep(PlayerWakeUpEvent event) {
        if (!event.getEntity().level().isClientSide){
            ((ServerLevel) event.getEntity().level()).setDayTime(13000);
        }
    }

    @SubscribeEvent
    public static void onCheckTime(SleepingTimeCheckEvent event) {
        if (event.getEntity().level().getBlockState(event.getSleepingLocation().get()).is(INVERTED_BED)) {
            event.setResult(event.getEntity().level().isDay() ? Event.Result.ALLOW : Event.Result.DENY);
        }
    }

    @SubscribeEvent
    public static void onBedCheck(SleepingLocationCheckEvent event) {
        if (event.getEntity().level().getBlockState(event.getSleepingLocation()).getBlock().equals(INVERTED_BED)) {
            event.setResult(Event.Result.ALLOW);
        }
    }

}
