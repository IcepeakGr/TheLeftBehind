package com.icepeak;

import org.bukkit.plugin.java.JavaPlugin;

import com.icepeak.listeners.PlayerJoinListener;
import com.icepeak.listeners.PlayerQuitListener;
import com.icepeak.mechanisms.HudEngine;
import com.icepeak.mechanisms.ProfileManager;

public class MainClass extends JavaPlugin {

    private ProfileManager profileManager;
    private MainClass instance;

    {
        this.instance = this;
    }

    @Override
    public void onEnable() {

        this.profileManager = new ProfileManager(this);

        getLogger().info("===================================");
        getLogger().info("TheLeftBehind has been enabled!");
        getLogger().info("===================================");


        loadListeners();

        getServer().getScheduler().runTaskTimer(this, new HudEngine(profileManager), 20L, 20L);
    }

    @Override
    public void onDisable() {
        getLogger().info("===================================");
        getLogger().info("TheLeftBehind has been disabled!");
        getLogger().info("===================================");
    }


    public MainClass getInstance() {
        return instance;
    }


    public void loadListeners() {
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(profileManager), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(profileManager), this);
    }

}
