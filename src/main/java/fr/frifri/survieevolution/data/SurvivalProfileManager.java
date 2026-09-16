package fr.frifri.survieevolution.data;

import net.minecraft.world.entity.player.Player;

public final class SurvivalProfileManager {

    private SurvivalProfileManager() {
    }

    public static SurvivalProfile getProfile(Player player) {
        return player.getAttachedOrCreate(
                SurvivalAttachments.SURVIVAL_PROFILE
        );
    }
}