package fr.frifri.survieevolution.event;

import fr.frifri.survieevolution.data.ExplorationTracker;
import fr.frifri.survieevolution.data.SurvivalProfileManager;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.InteractionResult;

import net.minecraft.tags.BlockTags;

public final class SurvivalEventHandler {

    private SurvivalEventHandler() {
    }

    public static void initialize() {
        PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) -> {
            if (state.is(BlockTags.CROPS)) {
                SurvivalProfileManager.getProfile(player).addFarming(1);
            } else {
                SurvivalProfileManager.getProfile(player).addMining(1);
            }
        });

        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (var player : server.getPlayerList().getPlayers()) {
                ExplorationTracker.track(player);
            }
        });

        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            SurvivalProfileManager.getProfile(player).addCombat(1);

            return net.minecraft.world.InteractionResult.PASS;
        });

        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            if (world.isClientSide()) {
                return InteractionResult.PASS;
            }

            if (!(player.getItemInHand(hand).getItem() instanceof BlockItem)) {
                return InteractionResult.PASS;
            }

            var targetPos = hitResult.getBlockPos().relative(hitResult.getDirection());
            var beforeState = world.getBlockState(targetPos);
            var server = player.getServer();

            if (server != null) {
                server.execute(() -> {
                    var afterState = world.getBlockState(targetPos);

                    if (!afterState.equals(beforeState)) {
                        SurvivalProfileManager.getProfile(player).addBuilding(1);
                    }
                });
            }

            return InteractionResult.PASS;
        });
    }
}