package com.noslimes.orebuff.util;


import com.noslimes.orebuff.OreBuff;
import com.noslimes.orebuff.config.ModConfigManager;
import io.netty.handler.ssl.IdentityCipherSuiteFilter;
import joptsimple.util.KeyValuePair;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.*;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.item.EnchantmentPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.util.Identifier;

public class ModOreLootTableModifiers {
    public static void modifyLootTables() {
        for (var entry : ModConfigManager.getConfig().getOreConfigs().entrySet()) {
            Identifier blockID = entry.getKey();
            if (blockID == null) {
                continue;
            }

            Identifier itemID = createIdentifierSafely(entry.getValue().itemName);
            if (itemID == null) {
                continue;
            }

            Block block = getBlockSafely(blockID);
            if (block == null) {
                continue;
            }

            Item item = getItemSafely(itemID);
            if (item == null) {
                continue;
            }

            boolean enable = entry.getValue().enable;
            float dropChance = entry.getValue().chance;
            int dropMinCount = entry.getValue().minDrop;
            int dropMaxCount = entry.getValue().maxDrop;

            registerLootTableModification(blockID, item, enable, dropChance, dropMinCount, dropMaxCount);
        }

        OreBuff.LOGGER.info("Ore loot tables modified!");
    }

    private static Identifier createIdentifierSafely(String identifierString) {
        try {
            return new Identifier(identifierString);
        } catch (Exception e) {
            OreBuff.LOGGER.warn("Error creating Identifier for '{}': {}", identifierString, e.getMessage());
            return null;
        }
    }

    private static Block getBlockSafely(Identifier blockID) {
        try {
            return Registries.BLOCK.get(blockID);
        } catch (Exception e) {
            OreBuff.LOGGER.warn("The corresponding block for entered identifier '{}' was not found!", blockID);
            return null;
        }
    }

    private static Item getItemSafely(Identifier itemID) {
        try {
            return Registries.ITEM.get(itemID);
        } catch (Exception e) {
            OreBuff.LOGGER.warn("The corresponding item for entered identifier '{}' was not found!", itemID);
            return null;
        }
    }


    public static void registerLootTableModification(Identifier lootTableId, Item dropItem, boolean enableModification, float dropChance, int dropMinCount, int dropMaxCount) {
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