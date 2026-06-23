package com.icepeak.items.weapons;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import com.icepeak.MainClass;
import com.icepeak.items.BaseItem;

public abstract class BaseGun extends BaseItem {


    // Core gun specifications to implement per weapon type
    public abstract double getDamage();
    public abstract int getMaxAmmo();
    public abstract double getRange();
    public abstract Sound getFireSound();
    public abstract String getDisplayName();
    public abstract Material getBaseMaterial();
    public abstract int getCustomModelDataID();

    @Override
    public List<String> getLore() {
        return java.util.Arrays.asList(
            "§7Type: §f" + getDisplayName(),
            "§7Damage: §c" + getDamage() + " §4❤",
            "§7Capacity: §e" + getMaxAmmo() + " rounds",
            "",
            "§6[Right-Click] §eTo fire round.",
            "§6[Left-Click] §eTo reload weapon."
        );
    }


    public int getCurrentAmmo(ItemStack item) {
        if (item == null || !item.hasItemMeta()) return 0;

        NamespacedKey ammoKey = new NamespacedKey(MainClass.getInstance(), "current_ammo");
        return item.getItemMeta().getPersistentDataContainer().getOrDefault(ammoKey, PersistentDataType.INTEGER, this.getMaxAmmo());
    }

}
