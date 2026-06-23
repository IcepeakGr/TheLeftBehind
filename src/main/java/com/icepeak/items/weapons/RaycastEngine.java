package com.icepeak.items.weapons;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import com.icepeak.MainClass;

public class RaycastEngine {

    public static void fireWeapon(Player player, BaseGun gun, ItemStack item) {

        ItemMeta meta = item.getItemMeta();
        if (meta == null) return;

        // Ammo logic
        int currentAmmo = gun.getCurrentAmmo(item);

        if (currentAmmo <= 0) {
            // Empty chamber dry-fire click feedback
            player.playSound(player.getLocation(), Sound.BLOCK_LEVER_CLICK, 1.0f, 1.5f);
            player.sendMessage("§c*Click* Out of ammo! Left-click to reload.");
            return;
        }

        currentAmmo--;

        NamespacedKey ammoKey = new NamespacedKey(MainClass.getInstance(), "current_ammo");
        meta.getPersistentDataContainer().set(ammoKey, PersistentDataType.INTEGER, currentAmmo);

        // Dynamically insert live ammo stats above baseline gun lore
        List<String> newLore = new ArrayList<>();
        newLore.add("§7Ammo: §e" + currentAmmo + "§7/§e" + gun.getMaxAmmo());
        newLore.addAll(gun.getLore());
        meta.setLore(newLore);
        item.setItemMeta(meta);


        // Raycasting

        Location eyeLocation = player.getEyeLocation();
        Vector direction = eyeLocation.getDirection();

        player.getWorld().playSound(eyeLocation, gun.getFireSound(), 1.0f, 1.0f);


        RayTraceResult result = player.getWorld().rayTrace(
                    eyeLocation,
                    direction,
                    gun.getRange(),
                    FluidCollisionMode.NEVER,
                    true, // Ignore block hitboxes for entities
                    0.2,  // Precision padding for tight hitboxes
                    entity -> entity instanceof LivingEntity && !entity.getUniqueId().equals(player.getUniqueId())
        );


        if (result != null && result.getHitEntity() instanceof LivingEntity) {

            LivingEntity target = (LivingEntity) result.getHitEntity();
            // Apply damage or effects to the target
            target.damage(gun.getDamage(), player);


            Location hitLocation = result.getHitPosition().toLocation(player.getWorld());
            //player.getWorld().playSound(hitLocation, Sound.ENTITY_ITEM_BREAK, 0.8f, 0.5f);
        }

    }

}
