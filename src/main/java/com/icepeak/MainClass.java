package com.icepeak;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.icepeak.commands.GetMedKitCommand;
import com.icepeak.commands.GetPistolCommand;
import com.icepeak.listeners.CancelHandSwapListener;
import com.icepeak.listeners.PistolFireListener;
import com.icepeak.listeners.PlayerJoinListener;
import com.icepeak.listeners.PlayerQuitListener;
import com.icepeak.mechanisms.HudEngine;
import com.icepeak.mechanisms.ProfileManager;

public class MainClass extends JavaPlugin {

    private ProfileManager profileManager;
    private static MainClass instance;

    

    @Override
    public void onEnable() {

        instance = this;

        this.profileManager = new ProfileManager(this);

        getLogger().info("===================================");
        getLogger().info("TheLeftBehind has been enabled!");
        getLogger().info("===================================");


        loadListeners();
        loadMedKitCommands();
        loadPistolCommands();

        getServer().getScheduler().runTaskTimer(this, new HudEngine(profileManager), 20L, 20L);
    }

    @Override
    public void onDisable() {
        if (this.profileManager != null) {
        getLogger().info("Server shutting down! Safely flushing active survivor profiles to disk...");
        
        // Loop through any player currently online right now and force write their files
        for (Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()) {
            this.profileManager.saveAndUnloadProfile(onlinePlayer.getUniqueId());
            }
        }
        getLogger().info("The Left Behind data is successfully secured. Goodbye!");
    }


    public static MainClass getInstance() {
        return instance;
    }


    public void loadListeners() {
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(profileManager), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(profileManager), this);
        getServer().getPluginManager().registerEvents(new PistolFireListener(), this);
        getServer().getPluginManager().registerEvents(new CancelHandSwapListener(), this);
    }

    public void loadMedKitCommands() {
        GetMedKitCommand getMedkit = new GetMedKitCommand();
        this.getCommand("getmedkit").setExecutor(getMedkit);
        this.getCommand("getmedkit").setTabCompleter(getMedkit);
    }

    public void loadPistolCommands() {
        GetPistolCommand getPistol = new GetPistolCommand();
        this.getCommand("getpistol").setExecutor(getPistol);
        this.getCommand("getpistol").setTabCompleter(getPistol);
    }

}
