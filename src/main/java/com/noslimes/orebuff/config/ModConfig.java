package com.noslimes.orebuff.config;

public class ModConfig {
    //public OreConfig[] oreConfigs = new OreConfig[10];

    public boolean enableCoal, enableDeepslateCoal;
    public float coalChance;
    public int coalMinCount, coalMaxCount;

    public boolean enableIron, enableDeepslateIron;
    public float ironChance;
    public int ironMinCount, ironMaxCount;

    public boolean enableCopper, enableDeepslateCopper;
    public float copperChance;
    public int copperMinCount, copperMaxCount;

    public boolean enableGold, enableDeepslateGold;
    public float goldChance;
    public int goldMinCount, goldMaxCount;

    public boolean enableLapis, enableDeepslateLapis;
    public float lapisChance;
    public int lapisMinCount, lapisMaxCount;

    public boolean enableRedstone, enableDeepslateRedstone;
    public float redstoneChance;
    public int redstoneMinCount, redstoneMaxCount;

    public boolean enableEmerald, enableDeepslateEmerald;
    public float emeraldChance;
    public int emeraldMinCount, emeraldMaxCount;

    public boolean enableDiamond, enableDeepslateDiamond;
    public float diamondChance;
    public int diamondMinCount, diamondMaxCount;

    public  boolean enableQuartz;
    public float quartzChance;
    public int quartzMinCount, quartzMaxCount;

    public boolean enableAncientDebris;
    public float ancientDebrisChance;
    public int ancientDebrisMinCount, ancientDebrisMaxCount;

    public ModConfig() {
        // Set default values

        enableCoal = true;
        enableDeepslateCoal = true;
        coalChance = 0.5f;
        coalMinCount = 1;
        coalMaxCount = 2;

        enableIron = true;
        enableDeepslateIron = true;
        ironChance = 0.35f;
        ironMinCount = 1;
        ironMaxCount = 1;

        enableCopper = false;
        enableDeepslateCopper = false;
        copperChance = 0.35f;
        copperMinCount = 1;
        copperMaxCount = 2;

        enableGold = true;
        enableDeepslateGold = true;
        goldChance = 0.35f;
        goldMinCount = 1;
        goldMaxCount = 1;

        enableLapis = false;
        enableDeepslateLapis = false;
        lapisChance = 0.35f;
        lapisMinCount = 1;
        lapisMaxCount = 2;

        enableRedstone = false;
        enableDeepslateRedstone = false;
        redstoneChance = 0.35f;
        redstoneMinCount = 1;
        redstoneMaxCount = 3;

        enableEmerald = false;
        enableDeepslateEmerald = false;
        emeraldChance = 0.15f;
        emeraldMinCount = 1;
        emeraldMaxCount = 1;

        enableDiamond = false;
        enableDeepslateDiamond = false;
        diamondChance = 0.15f;
        diamondMinCount = 1;
        diamondMaxCount = 1;

        enableQuartz = true;
        quartzChance = 0.35f;
        quartzMinCount = 1;
        quartzMaxCount = 2;

        enableAncientDebris = false;
        ancientDebrisChance = 0.05f;
        ancientDebrisMinCount = 1;
        ancientDebrisMaxCount = 1;
    }
}


