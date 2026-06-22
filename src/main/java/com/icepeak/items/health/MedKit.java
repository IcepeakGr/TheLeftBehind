package com.icepeak.items.health;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;

import com.icepeak.items.BaseItem;

public class MedKit extends BaseItem {

    @Override
    public String getDisplayName() {
        return "§cMedKit";
    }

    @Override
    public Material getBaseMaterial() {
        return Material.RED_DYE;
    }

    @Override
    public int getCustomModelDataID() {
        return 1001;
    }

    @Override
    public List<String> getLore() {
        return Arrays.asList("§7A kit containing medical supplies.", "§7Use it to heal yourself.");
    }

}
