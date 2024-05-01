package com.leclowndu93150.invertedbed;

import com.leclowndu93150.invertedbed.block.InvertedBedBlock;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.player.PlayerWakeUpEvent;
import net.neoforged.neoforge.event.entity.player.SleepingLocationCheckEvent;
import net.neoforged.neoforge.event.entity.player.SleepingTimeCheckEvent;

import static com.leclowndu93150.invertedbed.Main.MODID;

@Mod.EventBusSubscriber(modid = MODID)
public class IBServerEvents {
    @SubscribeEvent
    public static void onTrySleep(PlayerWakeUpEvent event) {
        if (!event.getEntity().level().isClientSide()) {
            ServerLevel level = ((ServerLevel) event.getEntity().level());
            BlockState blockState = event.getEntity().level().getBlockState(event.getEntity().getSleepingPos().get());
            if (blockState.getBlock() instanceof InvertedBedBlock && event.getEntity().getSleepTimer() >= 99)
                level.setDayTime(13000);
        }
    }

    @SubscribeEvent
    public static void onCheckTime(SleepingTimeCheckEvent event) {
        BlockState blockState = event.getEntity().level().getBlockState(event.getSleepingLocation().get());
        if (blockState.getBlock() instanceof InvertedBedBlock)
            event.setResult(event.getEntity().level().isDay() ? Event.Result.ALLOW : Event.Result.DENY);
    }

    @SubscribeEvent
    public static void onBedCheck(SleepingLocationCheckEvent event) {
        BlockState blockState = event.getEntity().level().getBlockState(event.getSleepingLocation());
        if (blockState.getBlock() instanceof InvertedBedBlock)
            event.setResult(Event.Result.ALLOW);
    }

}
