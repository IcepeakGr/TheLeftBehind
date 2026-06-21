package com.icepeak.mechanisms;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class HudEngine implements Runnable {

    private final ProfileManager profileManager;

    public HudEngine(ProfileManager profileManager) {
        this.profileManager = profileManager;
    }

    @Override
    public void run() {
        // Update HUD elements based on the survivor profiles

        for(Player player: Bukkit.getServer().getOnlinePlayers()) {

            if(player == null) { continue; }

                SurvivorProfile profile = profileManager.getProfile(player.getUniqueId());
                if(profile != null) {

                    double thirst = player.isSprinting() ? profile.getThirstLevel() - 0.01 : profile.getThirstLevel();
                    profile.setThirstLevel(thirst);

                    // If the player is infected - build up over time
                    if(profile.getInfectionLevel() > 0) {
                        profile.setInfectionLevel(profile.getInfectionLevel() + 0.05);
                    }


                    // Update the player's HUD with the new profile information
                    String actionBarMessage = String.format("Thirst: %.2f | Infection: %.2f", profile.getThirstLevel(), profile.getInfectionLevel());

                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(actionBarMessage));

                    
                    if(profile.getThirstLevel() <= 0) {
                        player.damage(0.02); // Damage the player if thirst is zero

                    }

                    if(profile.getInfectionLevel() >= 100) {
                        player.damage(0.05); // Damage the player if infection is at max
                    }
            }
        }
    }
}
