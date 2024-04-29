package com.leclowndu93150.invertedbed;

import net.minecraft.server.level.ServerLevel;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.player.PlayerWakeUpEvent;
import net.neoforged.neoforge.event.entity.player.SleepingLocationCheckEvent;
import net.neoforged.neoforge.event.entity.player.SleepingTimeCheckEvent;

import static com.leclowndu93150.invertedbed.Main.INVERTED_BED;

@Mod.EventBusSubscriber(modid = "inverted_bed")
public class SleepingLogic {
    @SubscribeEvent
    public static void onTrySleep(PlayerWakeUpEvent event) {
        if (event.getEntity().getSleepTimer() >= 99 && event.getEntity().level().getBlockState(event.getEntity().getSleepingPos().get()).getBlock().equals(INVERTED_BED)) {
            if (event.getEntity().level() instanceof ServerLevel serverLevel) {
                serverLevel.setDayTime(13000);
            }
        }
    }

    @SubscribeEvent
    public static void onCheckTime(SleepingTimeCheckEvent event) {
        if (event.getEntity().level().getBlockState(event.getSleepingLocation().get()).getBlock().equals(INVERTED_BED)) {
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
