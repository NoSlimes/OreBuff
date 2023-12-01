package com.noslimes.orebuff.util;


import com.noslimes.orebuff.OreBuff;
import com.noslimes.orebuff.config.ModConfigManager;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.*;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.item.EnchantmentPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.util.Identifier;

public class ModOreLootTableModifiers {
    private static final Identifier COAL_ORE_ID = Blocks.COAL_ORE.getLootTableId();
    private static final Identifier DEEPSLATE_COAL_ORE_ID = Blocks.DEEPSLATE_COAL_ORE.getLootTableId();
    private static final Identifier IRON_ORE_ID = Blocks.IRON_ORE.getLootTableId();
    private static final Identifier DEEPSLATE_IRON_ORE_ID = Blocks.DEEPSLATE_IRON_ORE.getLootTableId();
    private static final Identifier COPPER_ORE_ID = Blocks.COPPER_ORE.getLootTableId();
    private static final Identifier DEEPSLATE_COPPER_ORE_ID = Blocks.DEEPSLATE_COPPER_ORE.getLootTableId();
    private static final Identifier GOLD_ORE_ID = Blocks.GOLD_ORE.getLootTableId();
    private static final Identifier DEEPSLATE_GOLD_ORE_ID = Blocks.DEEPSLATE_GOLD_ORE.getLootTableId();
    private static final Identifier LAPIS_ORE_ID = Blocks.LAPIS_ORE.getLootTableId();
    private static final Identifier DEEPSLATE_LAPIS_ORE_ID = Blocks.DEEPSLATE_LAPIS_ORE.getLootTableId();
    private static final Identifier REDSTONE_ORE_ID = Blocks.REDSTONE_ORE.getLootTableId();
    private static final Identifier DEEPSLATE_REDSTONE_ORE_ID = Blocks.DEEPSLATE_REDSTONE_ORE.getLootTableId();
    private static final Identifier DIAMOND_ORE_ID = Blocks.DIAMOND_ORE.getLootTableId();
    private static final Identifier DEEPSLATE_DIAMOND_ORE_ID = Blocks.DEEPSLATE_DIAMOND_ORE.getLootTableId();
    private static final Identifier EMERALD_ORE_ID = Blocks.EMERALD_ORE.getLootTableId();
    private static final Identifier DEEPSLATE_EMERALD_ORE_ID = Blocks.DEEPSLATE_EMERALD_ORE.getLootTableId();
    private static final Identifier NETHER_QUARTZ_ORE_ID = Blocks.NETHER_QUARTZ_ORE.getLootTableId();
    private static final Identifier ANCIENT_DEBRIS_ID = Blocks.ANCIENT_DEBRIS.getLootTableId();

    public static void modifyLootTables(){


        //Coal
        registerLootTableModification(COAL_ORE_ID, ModConfigManager.getConfig().enableCoal, ModConfigManager.getConfig().coalChance, Items.COAL, ModConfigManager.getConfig().coalMinCount, ModConfigManager.getConfig().coalMaxCount);
        registerLootTableModification(DEEPSLATE_COAL_ORE_ID, ModConfigManager.getConfig().enableDeepslateCoal, ModConfigManager.getConfig().coalChance, Items.COAL, ModConfigManager.getConfig().coalMinCount, ModConfigManager.getConfig().coalMaxCount);

        //Iron
        registerLootTableModification(IRON_ORE_ID, ModConfigManager.getConfig().enableIron, ModConfigManager.getConfig().ironChance, Items.RAW_IRON, ModConfigManager.getConfig().ironMinCount, ModConfigManager.getConfig().ironMaxCount);
        registerLootTableModification(DEEPSLATE_IRON_ORE_ID, ModConfigManager.getConfig().enableDeepslateIron, ModConfigManager.getConfig().ironChance, Items.RAW_IRON, ModConfigManager.getConfig().ironMinCount, ModConfigManager.getConfig().ironMaxCount);

        //Copper
        registerLootTableModification(COPPER_ORE_ID, ModConfigManager.getConfig().enableCopper, ModConfigManager.getConfig().copperChance, Items.RAW_COPPER, ModConfigManager.getConfig().copperMinCount, ModConfigManager.getConfig().copperMaxCount);
        registerLootTableModification(DEEPSLATE_COPPER_ORE_ID, ModConfigManager.getConfig().enableDeepslateCopper, ModConfigManager.getConfig().copperChance, Items.RAW_COPPER, ModConfigManager.getConfig().copperMinCount, ModConfigManager.getConfig().copperMaxCount);

        //Gold
        registerLootTableModification(GOLD_ORE_ID, ModConfigManager.getConfig().enableGold, ModConfigManager.getConfig().goldChance, Items.RAW_GOLD, ModConfigManager.getConfig().goldMinCount, ModConfigManager.getConfig().goldMaxCount);
        registerLootTableModification(DEEPSLATE_GOLD_ORE_ID, ModConfigManager.getConfig().enableDeepslateGold, ModConfigManager.getConfig().goldChance, Items.RAW_GOLD, ModConfigManager.getConfig().goldMinCount, ModConfigManager.getConfig().goldMaxCount);

        //Lapis
        registerLootTableModification(LAPIS_ORE_ID, ModConfigManager.getConfig().enableLapis, ModConfigManager.getConfig().lapisChance, Items.LAPIS_LAZULI, ModConfigManager.getConfig().lapisMinCount, ModConfigManager.getConfig().lapisMaxCount);
        registerLootTableModification(DEEPSLATE_LAPIS_ORE_ID, ModConfigManager.getConfig().enableDeepslateLapis, ModConfigManager.getConfig().lapisChance, Items.LAPIS_LAZULI, ModConfigManager.getConfig().lapisMinCount, ModConfigManager.getConfig().lapisMaxCount);

        //Redstone
        registerLootTableModification(REDSTONE_ORE_ID, ModConfigManager.getConfig().enableRedstone, ModConfigManager.getConfig().redstoneChance, Items.REDSTONE, ModConfigManager.getConfig().redstoneMinCount, ModConfigManager.getConfig().redstoneMaxCount);
        registerLootTableModification(DEEPSLATE_REDSTONE_ORE_ID, ModConfigManager.getConfig().enableDeepslateRedstone, ModConfigManager.getConfig().redstoneChance, Items.REDSTONE, ModConfigManager.getConfig().redstoneMinCount, ModConfigManager.getConfig().redstoneMaxCount);

        //Emerald
        registerLootTableModification(EMERALD_ORE_ID, ModConfigManager.getConfig().enableEmerald, ModConfigManager.getConfig().emeraldChance, Items.EMERALD, ModConfigManager.getConfig().emeraldMinCount, ModConfigManager.getConfig().emeraldMaxCount);
        registerLootTableModification(DEEPSLATE_EMERALD_ORE_ID, ModConfigManager.getConfig().enableDeepslateEmerald, ModConfigManager.getConfig().emeraldChance, Items.EMERALD, ModConfigManager.getConfig().emeraldMinCount, ModConfigManager.getConfig().emeraldMaxCount);

        //Diamond
        registerLootTableModification(DIAMOND_ORE_ID, ModConfigManager.getConfig().enableDiamond, ModConfigManager.getConfig().diamondChance, Items.DIAMOND, ModConfigManager.getConfig().diamondMinCount, ModConfigManager.getConfig().diamondMaxCount);
        registerLootTableModification(DEEPSLATE_DIAMOND_ORE_ID, ModConfigManager.getConfig().enableDeepslateDiamond, ModConfigManager.getConfig().diamondChance, Items.DIAMOND, ModConfigManager.getConfig().diamondMinCount, ModConfigManager.getConfig().diamondMaxCount);

        //Quartz
        registerLootTableModification(NETHER_QUARTZ_ORE_ID, ModConfigManager.getConfig().enableQuartz, ModConfigManager.getConfig().quartzChance, Items.QUARTZ, ModConfigManager.getConfig().quartzMinCount, ModConfigManager.getConfig().quartzMaxCount);

        //Ancient debris
        registerLootTableModification(ANCIENT_DEBRIS_ID, ModConfigManager.getConfig().enableAncientDebris, ModConfigManager.getConfig().ancientDebrisChance, Items.NETHERITE_SCRAP, ModConfigManager.getConfig().ancientDebrisMinCount, ModConfigManager.getConfig().ancientDebrisMaxCount);

        OreBuff.LOGGER.info("Ore loot tables modified!");
    }

    private static void registerLootTableModification(Identifier lootTableId, boolean enableModification, float dropChance, Item dropItem, int dropMinCount, int dropMaxCount) {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (source.isBuiltin() && lootTableId.equals(id) && enableModification) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1f))
                        .conditionally(RandomChanceWithLootingLootCondition.builder(dropChance, 1.5f).build())
                        .conditionally(InvertedLootCondition.builder(MatchToolLootCondition.builder(ItemPredicate.Builder.create().enchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, NumberRange.IntRange.ANY)))))
                        .with(ItemEntry.builder(dropItem))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(dropMinCount, dropMaxCount)).build());

                tableBuilder.pool(poolBuilder.build());
            }

        });
    }
}