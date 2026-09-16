package fr.frifri.survieevolution.data;

import net.minecraft.world.entity.player.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class TravelTracker {

    private static final double DISTANCE_THRESHOLD = 100.0;

    private static final Map<UUID, Position> LAST_POSITIONS = new HashMap<>();

    private TravelTracker() {
    }

    public static void track(Player player) {
        UUID playerId = player.getUUID();

        Position lastPosition = LAST_POSITIONS.get(playerId);

        if (lastPosition == null) {
            LAST_POSITIONS.put(
                    playerId,
                    new Position(player.getX(), player.getZ())
            );
            return;
        }

        double dx = player.getX() - lastPosition.x();
        double dz = player.getZ() - lastPosition.z();

        double distanceSquared = dx * dx + dz * dz;

        if (distanceSquared >= DISTANCE_THRESHOLD * DISTANCE_THRESHOLD) {
            SurvivalProfileManager.getProfile(player).addTravel(1);

            LAST_POSITIONS.put(
                    playerId,
                    new Position(player.getX(), player.getZ())
            );
        }
    }

    private record Position(double x, double z) {
    }
}