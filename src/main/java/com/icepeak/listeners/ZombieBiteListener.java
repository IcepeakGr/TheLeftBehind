package com.icepeak.listeners;

import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import com.icepeak.mechanisms.ProfileManager;
import com.icepeak.mechanisms.SurvivorProfile;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class ZombieBiteListener implements Listener {

    private ProfileManager profileManager;

    public ZombieBiteListener(ProfileManager profileManager) {
        this.profileManager = profileManager;
    }

    @EventHandler
    public void onZombieBite(EntityDamageEvent event) {
        
        if (!(event.getEntity() instanceof Player)) return;
        if (!(event.getDamageSource() instanceof Zombie)) return;

        Player player = (Player) event.getEntity();
        //Zombie zombie = (Zombie) event.getDamageSource();

        SurvivorProfile profile = profileManager.getProfile(player.getUniqueId());
        if (profile == null) return;

        Location hitLoc = player.getLocation();

        // Core audio feedback for the physical bite impact
        player.getWorld().playSound(hitLoc, Sound.ENTITY_ZOMBIE_INFECT, 1.0f, 1.0f);

        // Roll a 50% chance using ThreadLocalRandom
        boolean isInfected = ThreadLocalRandom.current().nextBoolean();

        if(isInfected) {
            double currentInfection = profile.getInfectionLevel();
            double newInfectionLevel = currentInfection + 10.0;
            profile.setInfectionLevel(newInfectionLevel);

            // Title alerting the survivor they are sick with red color
            player.sendTitle("Infection Alert", "You have been infected!", 10, 70, 20);

            // Action bar for 1 second alerting the survivor about the infection
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                new TextComponent("§cYou have been infected!"));

            

        }else {
            player.sendTitle("Infection Alert", "You have not been infected.", 10, 70, 20);
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                new TextComponent("§aYou are not infected."));
        }

        // Inflict damage on player
        event.setDamage(event.getDamage() + 2.0); // Increase damage by 2.0


        

    }

}
