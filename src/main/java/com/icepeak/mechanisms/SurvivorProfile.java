package com.icepeak.mechanisms;

import java.util.UUID;

public class SurvivorProfile {

    private final UUID uuid;
    private double thirstLevel;
    private double infectionLevel;

    public SurvivorProfile(UUID uuid) {
        this.uuid = uuid;
        this.thirstLevel = 100.0;
        this.infectionLevel = 0.0;
    }

    public UUID getPlayerUUID() {
        return uuid;
    }

    public double getThirstLevel() {
        return thirstLevel;
    }

    public double getInfectionLevel() {
        return infectionLevel;
    }


    public void setThirstLevel(double thirstLevel) {
        this.thirstLevel = Math.max(0, Math.min(thirstLevel, 100));
    }

    public void setInfectionLevel(double infectionLevel) {
        this.infectionLevel = Math.max(0, Math.min(infectionLevel, 100));
    }

}
