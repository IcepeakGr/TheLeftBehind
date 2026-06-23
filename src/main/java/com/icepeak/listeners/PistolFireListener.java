package com.icepeak.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.icepeak.items.weapons.Pistol;
import com.icepeak.items.weapons.RaycastEngine;

public class PistolFireListener implements Listener {

    // This listener is used to handle pistol firing events.
    // It listens for player interactions with the pistol and triggers the firing mechanism.

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onPistolFire(PlayerInteractEvent event) {
        
        // Right click to fire
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        ItemStack item = event.getItem();
        if (item == null || !item.hasItemMeta()) return;

        ItemMeta meta = item.getItemMeta();
        if (!meta.hasCustomModelData()) return;

        int customModelData = meta.getCustomModelData();
        if (customModelData != 1002) return;

        // Fire the pistol
        Pistol pistol = new Pistol();
        RaycastEngine.fireWeapon(event.getPlayer(), pistol, item);
    }
}
