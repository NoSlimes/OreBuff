package com.noslimes.orebuff.config;

import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class ModConfig {

    private final Map<Identifier, OreConfig> oreConfigs = new HashMap<>();

    public ModConfig() {
        // Set default values
        setOreConfig(Blocks.COAL_ORE.getLootTableId(), Items.COAL.toString(), true, 0.5f, 1, 2);
        setOreConfig(Blocks.DEEPSLATE_COAL_ORE.getLootTableId(), Items.COAL.toString(), true, 0.5f, 1, 2);

        setOreConfig(Blocks.IRON_ORE.getLootTableId(), Items.RAW_IRON.toString(), true, 0.35f, 1, 1);
        setOreConfig(Blocks.DEEPSLATE_IRON_ORE.getLootTableId(), Items.RAW_IRON.toString(), true, 0.35f, 1, 1);

        setOreConfig(Blocks.COPPER_ORE.getLootTableId(), Items.RAW_COPPER.toString(), false, 0.35f, 1, 2);
        setOreConfig(Blocks.DEEPSLATE_COPPER_ORE.getLootTableId(), Items.RAW_COPPER.toString(), false, 0.35f, 1, 2);

        setOreConfig(Blocks.GOLD_ORE.getLootTableId(), Items.RAW_GOLD.toString(), true, 0.35f, 1, 1);
        setOreConfig(Blocks.DEEPSLATE_GOLD_ORE.getLootTableId(), Items.RAW_GOLD.toString(), true, 0.35f, 1, 1);

        setOreConfig(Blocks.LAPIS_ORE.getLootTableId(), Items.LAPIS_LAZULI.toString(), false, 0.35f, 1, 2);
        setOreConfig(Blocks.DEEPSLATE_LAPIS_ORE.getLootTableId(), Items.LAPIS_LAZULI.toString(), false, 0.35f, 1, 2);

        setOreConfig(Blocks.REDSTONE_ORE.getLootTableId(), Items.REDSTONE.toString(), false, 0.35f, 1, 3);
        setOreConfig(Blocks.DEEPSLATE_REDSTONE_ORE.getLootTableId(), Items.REDSTONE.toString(), false, 0.35f, 1, 3);

        setOreConfig(Blocks.EMERALD_ORE.getLootTableId(), Items.EMERALD.toString(), false, 0.15f, 1, 1);
        setOreConfig(Blocks.DEEPSLATE_EMERALD_ORE.getLootTableId(), Items.EMERALD.toString(), false, 0.15f, 1, 1);

        setOreConfig(Blocks.DIAMOND_ORE.getLootTableId(), Items.DIAMOND.toString(), false, 0.15f, 1, 1);
        setOreConfig(Blocks.DEEPSLATE_DIAMOND_ORE.getLootTableId(), Items.DIAMOND.toString(), false, 0.15f, 1, 1);

        setOreConfig(Blocks.NETHER_QUARTZ_ORE.getLootTableId(), Items.QUARTZ.toString(), true,0.35f, 1, 2);

        setOreConfig(Blocks.ANCIENT_DEBRIS.getLootTableId(), Items.NETHERITE_SCRAP.toString(), false, 0.05f, 1, 1);
    }

    public Map<Identifier, OreConfig> getOreConfigs(){
        return oreConfigs;
    }

    private void setOreConfig(Identifier oreName, String itemName, boolean enable, float chance, int minCount, int maxCount) {
        oreConfigs.put(oreName, new OreConfig(itemName, enable, chance, minCount, maxCount));
    }

    public static class OreConfig {
        public String itemName;
        public boolean enable;
        public float chance;
        public int minDrop, maxDrop;

        // Add a default constructor
        public OreConfig() {
            // Default constructor
        }

        public OreConfig(String itemName, boolean enable, float chance, int minDrop, int maxDrop) {
            this.itemName = itemName;
            this.enable = enable;
            this.chance = chance;
            this.minDrop = minDrop;
            this.maxDrop = maxDrop;
        }
    }

}
