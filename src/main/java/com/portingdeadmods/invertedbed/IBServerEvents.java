package com.portingdeadmods.invertedbed;

import com.portingdeadmods.invertedbed.block.InvertedBedBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.CanContinueSleepingEvent;
import net.neoforged.neoforge.event.entity.player.CanPlayerSleepEvent;
import net.neoforged.neoforge.event.entity.player.PlayerWakeUpEvent;

import java.util.Optional;

@EventBusSubscriber(modid = Main.MODID)
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
    public static void canStartSleep(CanPlayerSleepEvent event) {
        BlockState blockState = event.getEntity().level().getBlockState(event.getPos());
        if (blockState.getBlock() instanceof InvertedBedBlock) {
            event.setProblem(event.getEntity().level().isDay()
                    && (event.getVanillaProblem() == null
                    || event.getVanillaProblem() == Player.BedSleepingProblem.NOT_POSSIBLE_NOW)
                    ? null
                    : Player.BedSleepingProblem.NOT_POSSIBLE_NOW);
        }
    }

    @SubscribeEvent
    public static void canContinueSleep(CanContinueSleepingEvent event) {
        Level level = event.getEntity().level();
        Optional<BlockPos> optionalBlockPos = event.getEntity().getSleepingPos();
        if (optionalBlockPos.isPresent()) {
            BlockState blockState = level.getBlockState(optionalBlockPos.get());
            if (blockState.getBlock() instanceof InvertedBedBlock)
                event.setContinueSleeping(level.isDay());
        }
    }

}
