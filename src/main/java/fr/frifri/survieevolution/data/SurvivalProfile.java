package fr.frifri.survieevolution.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public class SurvivalProfile {

    public static final Codec<SurvivalProfile> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.INT.fieldOf("exploration").forGetter(SurvivalProfile::getExploration),
                    Codec.INT.fieldOf("combat").forGetter(SurvivalProfile::getCombat),
                    Codec.INT.fieldOf("farming").forGetter(SurvivalProfile::getFarming),
                    Codec.INT.fieldOf("mining").forGetter(SurvivalProfile::getMining),
                    Codec.INT.fieldOf("building").forGetter(SurvivalProfile::getBuilding),
                    Codec.INT.fieldOf("travel").forGetter(SurvivalProfile::getTravel),
                    Codec.BOOL.optionalFieldOf("miner_adaptation_notified", false)
                        .forGetter(profile -> profile.minerAdaptationNotified)
            ).apply(instance, SurvivalProfile::new)
    );

    private int exploration;
    private int combat;
    private int farming;
    private int mining;
    private int building;
    private int travel;

    private boolean minerAdaptationNotified;

    public SurvivalProfile() {
        this(0, 0, 0, 0, 0, 0, false);
    }

    public SurvivalProfile(
        int exploration,
        int combat,
        int farming,
        int mining,
        int building,
        int travel,
        boolean minerAdaptationNotified
    ) {
        this.exploration = exploration;
        this.combat = combat;
        this.farming = farming;
        this.mining = mining;
        this.building = building;
        this.travel = travel;
        this.minerAdaptationNotified = minerAdaptationNotified;
    }

    public int getExploration() {
        return exploration;
    }

    public int getCombat() {
        return combat;
    }

    public int getFarming() {
        return farming;
    }

    public int getMining() {
        return mining;
    }

    public int getBuilding() {
        return building;
    }

    public int getTravel() {
        return travel;
    }

    public void addExploration(int amount) {
        exploration += amount;
    }

    public void addCombat(int amount) {
        combat += amount;
    }

    public void addFarming(int amount) {
        farming += amount;
    }

    public void addMining(int amount) {
        mining += amount;
    }

    public void addBuilding(int amount) {
        building += amount;
    }

    public void addTravel(int amount) {
        travel += amount;
    }

    public SurvivalStyle getDominantStyle() {
        int max = Math.max(
                exploration,
                Math.max(
                        combat,
                        Math.max(
                                farming,
                                Math.max(
                                        mining,
                                        Math.max(building, travel)
                                )
                        )
                )
        );

        if (max == mining) {
            return SurvivalStyle.MINER;
        }

        if (max == exploration) {
            return SurvivalStyle.EXPLORER;
        }

        if (max == combat) {
            return SurvivalStyle.COMBATANT;
        }

        if (max == farming) {
            return SurvivalStyle.FARMER;
        }

        if (max == building) {
            return SurvivalStyle.BUILDER;
        }

        return SurvivalStyle.NOMAD;
    }

    public AdaptationLevel getAdaptationLevel() {
        int max = Math.max(
                exploration,
                Math.max(
                        combat,
                        Math.max(
                                farming,
                                Math.max(
                                        mining,
                                        Math.max(building, travel)
                                )
                        )
                )
        );

        if (max <= 0) {
            return AdaptationLevel.NONE;
        }

        if (max < 10) {
            return AdaptationLevel.DETECTED;
        }

        if (max < 25) {
            return AdaptationLevel.LIGHT;
        }

        if (max < 50) {
            return AdaptationLevel.ADVANCED;
        }

        if (max < 100) {
            return AdaptationLevel.STRONG;
        }

        return AdaptationLevel.EXTREME;
    }

    public boolean isMinerAdaptationNotified() {
        return minerAdaptationNotified;
    }

    public void setMinerAdaptationNotified(boolean minerAdaptationNotified) {
        this.minerAdaptationNotified = minerAdaptationNotified;
    }
}