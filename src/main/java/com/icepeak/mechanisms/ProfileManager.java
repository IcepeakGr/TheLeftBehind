package com.icepeak.mechanisms;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import com.icepeak.MainClass;
import com.icepeak.config.YamlManager;

public class ProfileManager {

    private final ConcurrentHashMap<UUID, SurvivorProfile> activeProfiles = new ConcurrentHashMap<>();
    private final YamlManager yamlManager;

    public ProfileManager(MainClass plugin) {
        this.yamlManager = new YamlManager(plugin);
    }

    public SurvivorProfile getProfile(UUID uuid) {
        return activeProfiles.get(uuid);
    }

    public void addProfile(UUID uuid, SurvivorProfile profile) {
        activeProfiles.put(uuid, profile);
    }

    public void removeProfile(UUID uuid) {
        activeProfiles.remove(uuid);
    }

    public void saveAndUnloadProfile(UUID uuid) {
        SurvivorProfile profile = activeProfiles.get(uuid);
        if (profile != null) {
            yamlManager.saveToFile(profile);
        }
    }

    public void loadProfile(UUID uuid) {
        SurvivorProfile profile = yamlManager.loadFromFile(uuid);
        if (profile != null) {
            activeProfiles.put(uuid, profile);
        }
    }

    public void savePlayerProfile(UUID uuid, SurvivorProfile profile) {
        // Save the profile to a database or file
        // This is just a placeholder implementation
        System.out.println("Saving profile for player: " + uuid);
    }

}
