package com.icepeak;

import org.bukkit.plugin.java.JavaPlugin;

public class MainClass extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("===================================");
        getLogger().info("TheLeftBehind has been enabled!");
        getLogger().info("===================================");
    }

    @Override
    public void onDisable() {
        getLogger().info("===================================");
        getLogger().info("TheLeftBehind has been disabled!");
        getLogger().info("===================================");
    }

}
