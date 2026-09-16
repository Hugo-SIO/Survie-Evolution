package fr.frifri.survieevolution.event;

import fr.frifri.survieevolution.data.SurvivalProfileManager;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;

public final class SurvivalEventHandler {

    private SurvivalEventHandler() {
    }

    public static void initialize() {
        PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) -> {
            SurvivalProfileManager.getProfile(player).addMining(1);
        });
    }
}