package com.icepeak.mechanisms;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ProfileManager {

    private final ConcurrentHashMap<UUID, SurvivorProfile> activeProfiles = new ConcurrentHashMap<>();

    public SurvivorProfile getProfile(UUID uuid) {
        return activeProfiles.get(uuid);
    }

    public void addProfile(SurvivorProfile profile) {
        activeProfiles.put(profile.getPlayerUUID(), profile);
    }

    public void removeProfile(UUID uuid) {
        activeProfiles.remove(uuid);
    }

}
