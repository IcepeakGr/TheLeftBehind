package com.icepeak.items.weapons;

import org.bukkit.Material;
import org.bukkit.Sound;

public class Pistol extends BaseGun {

    @Override
    public double getDamage() {
        return 10.0;
    }

    @Override
    public int getMaxAmmo() {
        return 15;
    }

    @Override
    public double getRange() {
        return 50.0;
    }

    @Override
    public Sound getFireSound() {
        return Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR;
    }

    @Override
    public String getDisplayName() {
        return "§fPistol";
    }

    @Override
    public Material getBaseMaterial() {
        return Material.IRON_SWORD;
    }

    @Override
    public int getCustomModelDataID() {
        return 1002;
    }



}
