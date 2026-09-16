package fr.frifri.survieevolution.command;

import com.mojang.brigadier.Command;
import fr.frifri.survieevolution.data.SurvivalProfile;
import fr.frifri.survieevolution.data.SurvivalProfileManager;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public final class SurvivalCommands {

    private SurvivalCommands() {
    }

    public static void initialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(
                    Commands.literal("survival")
                            .then(Commands.literal("debug")
                                    .executes(context -> showDebug(context.getSource().getPlayerOrException())))
            );
        });
    }

    private static int showDebug(ServerPlayer player) {
        SurvivalProfile profile = SurvivalProfileManager.getProfile(player);

        player.sendSystemMessage(
                Component.literal("Style: " + profile.getDominantStyle())
        );

        player.sendSystemMessage(
                Component.literal("=== Survival Evolution ===")
        );

        player.sendSystemMessage(
                Component.literal("Exploration: " + profile.getExploration())
        );

        player.sendSystemMessage(
                Component.literal("Combat: " + profile.getCombat())
        );

        player.sendSystemMessage(
                Component.literal("Farming: " + profile.getFarming())
        );

        player.sendSystemMessage(
                Component.literal("Mining: " + profile.getMining())
        );

        player.sendSystemMessage(
                Component.literal("Building: " + profile.getBuilding())
        );

        player.sendSystemMessage(
                Component.literal("Travel: " + profile.getTravel())
        );

        return Command.SINGLE_SUCCESS;
    }
}