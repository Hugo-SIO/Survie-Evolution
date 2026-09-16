package fr.frifri.survieevolution.data;

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.resources.ResourceLocation;

import static fr.frifri.survieevolution.SurvieEvolution.MOD_ID;

public class SurvivalAttachments {

    public static final AttachmentType<SurvivalProfile> SURVIVAL_PROFILE =
            AttachmentRegistry.create(
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "survival_profile"),
                    builder -> builder
                            .initializer(SurvivalProfile::new)
                            .persistent(SurvivalProfile.CODEC)
                            .copyOnDeath()
            );

    public static void initialize() {
    }
}