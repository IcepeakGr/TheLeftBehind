package com.icepeak.items.health;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;

import com.icepeak.items.BaseItem;

public class BottledWater extends BaseItem {

    @Override
    public String getDisplayName() {
        return "§bBottled Water";
    }

    @Override
    public Material getBaseMaterial() {
        return Material.POTION;
    }

    @Override
    public int getCustomModelDataID() {
        return 1002;
    }

    @Override
    public List<String> getLore() {
        return Arrays.asList("§7A bottle of clean water.", "§7Use it to quench your thirst.");
    }

}
