package com.icepeak.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.icepeak.mechanisms.ProfileManager;
import com.icepeak.mechanisms.SurvivorProfile;

public class PlayerJoinListener implements Listener {

    private ProfileManager profileManager;

    public PlayerJoinListener(ProfileManager profileManager) {
        this.profileManager = profileManager;
    }


    // Player Join Event
    // When a player joins the server and their profile doesn't exist, create a new one
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        if(profileManager.getProfile(event.getPlayer().getUniqueId()) == null) {
            profileManager.addProfile(event.getPlayer().getUniqueId(), new SurvivorProfile(event.getPlayer().getUniqueId()));
        }
    }

}
