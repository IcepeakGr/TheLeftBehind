package com.icepeak.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.icepeak.mechanisms.ProfileManager;

public class PlayerQuitListener implements Listener {

    private ProfileManager profileManager;

    public PlayerQuitListener(ProfileManager profileManager) {
        this.profileManager = profileManager;
    }

    // Player Quit Event
    // When a player quits the server, save their profile
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {

        if(profileManager.getProfile(event.getPlayer().getUniqueId()) != null) {
            profileManager.savePlayerProfile(event.getPlayer().getUniqueId(), profileManager.getProfile(event.getPlayer().getUniqueId()));
        }
    }

}
