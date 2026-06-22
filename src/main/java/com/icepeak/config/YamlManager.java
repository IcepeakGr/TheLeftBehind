package com.icepeak.config;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.bukkit.configuration.file.YamlConfiguration;

import com.icepeak.MainClass;
import com.icepeak.mechanisms.ProfileManager;
import com.icepeak.mechanisms.SurvivorProfile;

public class YamlManager {

    private MainClass plugin;
    private File profilesFolder;

    public YamlManager(MainClass plugin) {
        this.plugin = plugin;
        this.profilesFolder = new File(plugin.getDataFolder(), "profiles");

        if (!profilesFolder.exists()) {
            profilesFolder.mkdirs();
        }
    }

    // Save the SurvivorProfile to a YAML file
    public void saveToFile(SurvivorProfile profile) {
        File playerFile = new File(profilesFolder, profile.getPlayerUUID().toString() + ".yml");
        
        YamlConfiguration config = new YamlConfiguration();
        config.set("thirst", profile.getThirstLevel());
        config.set("infection", profile.getInfectionLevel());

        try {
            config.save(playerFile);
        } catch (IOException e) {
            plugin.getLogger().severe("Could not save .yml data for UUID: " + profile.getPlayerUUID());
        }
    }


    // Load the SurvivorProfile from a YAML file
    public SurvivorProfile loadFromFile(UUID uuid) {
        File playerFile = new File(profilesFolder, uuid.toString() + ".yml");
        
        if (!playerFile.exists()) {
            plugin.getLogger().warning("Profile file not found for UUID: " + uuid);
            return null;

        }
        
        YamlConfiguration config = YamlConfiguration.loadConfiguration(playerFile);
        SurvivorProfile profile = new SurvivorProfile(uuid);

        profile.setThirstLevel(config.getInt("thirst", 100));
        profile.setInfectionLevel(config.getInt("infection", 0));

        try {
            config.save(playerFile);
        } catch (IOException e) {
            plugin.getLogger().severe("Could not load .yml data for UUID: " + uuid);
        }
        
        return profile;
    }

}
