package com.icepeak;

import org.bukkit.plugin.java.JavaPlugin;

public class MainClass extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("TheLeftBehind has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("TheLeftBehind has been disabled!");
    }

}
