package com.icepeak.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class CancelHandSwapListener implements Listener {

    // This listener is used to cancel the hand swap event, which is triggered when a player presses the 'F' key.
    // This is done to prevent players from accidentally swapping their main hand and off hand items, which can be problematic in a survival game.

    @EventHandler
    public void onHandSwap(PlayerSwapHandItemsEvent event) {
        event.setCancelled(true);
    }


    
}
