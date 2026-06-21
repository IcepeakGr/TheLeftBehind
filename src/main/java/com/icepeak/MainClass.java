package com.icepeak;

import org.bukkit.plugin.java.JavaPlugin;

import com.icepeak.mechanisms.HudEngine;
import com.icepeak.mechanisms.ProfileManager;

public class MainClass extends JavaPlugin {

    private ProfileManager profileManager;

    @Override
    public void onEnable() {

        this.profileManager = new ProfileManager();

        getLogger().info("===================================");
        getLogger().info("TheLeftBehind has been enabled!");
        getLogger().info("===================================");


        getServer().getScheduler().runTaskTimer(this, new HudEngine(profileManager), 20L, 20L);
    }

    @Override
    public void onDisable() {
        getLogger().info("===================================");
        getLogger().info("TheLeftBehind has been disabled!");
        getLogger().info("===================================");
    }

}
