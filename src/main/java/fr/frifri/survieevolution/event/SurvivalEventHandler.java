package fr.frifri.survieevolution.event;

import fr.frifri.survieevolution.data.ExplorationTracker;
import fr.frifri.survieevolution.data.SurvivalProfileManager;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

public final class SurvivalEventHandler {

    private SurvivalEventHandler() {
    }

    public static void initialize() {
        PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) -> {
            SurvivalProfileManager.getProfile(player).addMining(1);
        });

        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (var player : server.getPlayerList().getPlayers()) {
                ExplorationTracker.track(player);
            }
        });
    }
}