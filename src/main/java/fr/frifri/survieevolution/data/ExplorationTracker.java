package fr.frifri.survieevolution.data;

import net.minecraft.world.entity.player.Player;

import java.util.HashSet;
import java.util.Set;

public final class ExplorationTracker {

    private static final int REGION_SIZE = 32;

    private ExplorationTracker() {
    }

    public static void track(Player player) {
        int regionX = player.blockPosition().getX() >> 5;
        int regionZ = player.blockPosition().getZ() >> 5;

        Set<String> discoveredRegions = getDiscoveredRegions(player);

        String key = regionX + ":" + regionZ;

        if (discoveredRegions.add(key)) {
            SurvivalProfileManager.getProfile(player).addExploration(1);
        }
    }

    private static Set<String> getDiscoveredRegions(Player player) {
        return player.getAttachedOrCreate(
                SurvivalAttachments.EXPLORATION_REGIONS
        );
    }
}