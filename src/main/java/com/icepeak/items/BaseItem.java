package com.icepeak.items;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class BaseItem {

    // Properties that every apocalypse item must have
    public abstract String getDisplayName();
    public abstract Material getBaseMaterial();
    public abstract int getCustomModelDataID();
    public abstract List<String> getLore();

    @SuppressWarnings("deprecation")
    public ItemStack create(int amount) {
        ItemStack item = new ItemStack(getBaseMaterial(), amount);
        ItemMeta meta = item.getItemMeta();

        if(meta == null) {
            throw new IllegalStateException("ItemMeta is null for item: " + getDisplayName());
        }

        meta.setDisplayName(getDisplayName());
        meta.setLore(getLore());
        meta.setCustomModelData(getCustomModelDataID());
        item.setItemMeta(meta);
        
        return item;
    }

}
