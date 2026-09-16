package fr.frifri.survieevolution.adaptation;

import fr.frifri.survieevolution.data.SurvivalProfile;
import fr.frifri.survieevolution.data.SurvivalProfileManager;
import net.minecraft.server.level.ServerPlayer;

public final class AdaptationManager {

    private AdaptationManager() {
    }

    public static void update(ServerPlayer player) {
        SurvivalProfile profile = SurvivalProfileManager.getProfile(player);

        switch (profile.getDominantStyle()) {
            case MINER -> updateMinerAdaptation(player, profile);
            case EXPLORER -> updateExplorerAdaptation(player, profile);
            case COMBATANT -> updateCombatAdaptation(player, profile);
            case FARMER -> updateFarmerAdaptation(player, profile);
            case BUILDER -> updateBuilderAdaptation(player, profile);
            case NOMAD -> updateNomadAdaptation(player, profile);
        }
    }

    private static void updateMinerAdaptation(
        ServerPlayer player,
        SurvivalProfile profile
    ) {
        if (profile.getAdaptationLevel().getValue() >= 3) {
            player.addEffect(
                    new net.minecraft.world.effect.MobEffectInstance(
                            net.minecraft.world.effect.MobEffects.NIGHT_VISION,
                            220,
                            0,
                            false,
                            false
                    )
            );
        }
    }

    private static void updateExplorerAdaptation(
            ServerPlayer player,
            SurvivalProfile profile
    ) {
        // Adaptation exploration à implémenter
    }

    private static void updateCombatAdaptation(
            ServerPlayer player,
            SurvivalProfile profile
    ) {
        // Adaptation combat à implémenter
    }

    private static void updateFarmerAdaptation(
            ServerPlayer player,
            SurvivalProfile profile
    ) {
        // Adaptation farming à implémenter
    }

    private static void updateBuilderAdaptation(
            ServerPlayer player,
            SurvivalProfile profile
    ) {
        // Adaptation construction à implémenter
    }

    private static void updateNomadAdaptation(
            ServerPlayer player,
            SurvivalProfile profile
    ) {
        // Adaptation nomade à implémenter
    }
}