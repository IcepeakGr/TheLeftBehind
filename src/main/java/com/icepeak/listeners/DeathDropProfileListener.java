package com.icepeak.listeners;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.EntityEquipment;

import com.icepeak.mechanisms.ProfileManager;
import com.icepeak.mechanisms.SurvivorProfile;

public class DeathDropProfileListener implements Listener {

    private ProfileManager profileManager;

    public DeathDropProfileListener(ProfileManager profileManager) {
        this.profileManager = profileManager;
    }


    @SuppressWarnings("null")
    @EventHandler
    public void onSurvivorDeath(PlayerDeathEvent event) {

        Player player = (Player) event.getEntity();
        Location deathLoc = player.getLocation();

        SurvivorProfile profile = profileManager.getProfile(player.getUniqueId());

        if(profile == null) { return; }

        profile.setInfectionLevel(0);
        profile.setThirstLevel(100);


        Zombie clone = (Zombie) player.getWorld().spawnEntity(deathLoc, EntityType.ZOMBIE);

        clone.setCustomName("§cInfected " + player.getName());
        clone.setCustomNameVisible(true);
        clone.setCanPickupItems(true);

        EntityEquipment playerEquip = player.getEquipment();
        EntityEquipment zombieEquip = clone.getEquipment();

        if( playerEquip == null && zombieEquip == null) { return; }

        zombieEquip.setHelmet(playerEquip.getHelmet());
        zombieEquip.setChestplate(playerEquip.getChestplate());
        zombieEquip.setLeggings(playerEquip.getLeggings());
        zombieEquip.setBoots(playerEquip.getBoots());

        zombieEquip.setItemInMainHand(playerEquip.getItemInMainHand());
        zombieEquip.setItemInOffHand(playerEquip.getItemInOffHand());

        // Force 100% (1.0f) drop rates so items are guaranteed to drop back when slain
        zombieEquip.setHelmetDropChance(1.0f);
        zombieEquip.setChestplateDropChance(1.0f);
        zombieEquip.setLeggingsDropChance(1.0f);
        zombieEquip.setBootsDropChance(1.0f);
        zombieEquip.setItemInMainHandDropChance(1.0f);
        zombieEquip.setItemInOffHandDropChance(1.0f);

        // Wipe normal floor drop arrays to prevent duplication exploits
        // The remaining inventory items (backpack loot) can still drop on the ground normally
        event.getDrops().remove(playerEquip.getHelmet());
        event.getDrops().remove(playerEquip.getChestplate());
        event.getDrops().remove(playerEquip.getLeggings());
        event.getDrops().remove(playerEquip.getBoots());
        event.getDrops().remove(playerEquip.getItemInMainHand());
        event.getDrops().remove(playerEquip.getItemInOffHand());


        event.setDeathMessage("§4☠ §c" + player.getName() + " succumbed to the hazards and turned into a threat.");

        
        
    }


}
