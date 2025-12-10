package com.noslimes.orebuff.util;

import com.noslimes.orebuff.OreBuff;
import com.noslimes.orebuff.config.ModConfigManager;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.InvertedLootCondition;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.condition.RandomChanceWithLootingLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.item.EnchantmentPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.Registries;
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

            if(entry.getValue().replace) {
                registerLootTableReplacement(blockID, item, enable, dropChance, dropMinCount, dropMaxCount);
            }
            else {
                registerLootTableModification(blockID, item, enable, dropChance, dropMinCount, dropMaxCount);
            }
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
            // Loot table ids are usually "namespace:blocks/<block_name>".
            // Convert to a block id if necessary so we can lookup the block in the BLOCK registry.
            String path = blockID.getPath();
            Identifier blockIdentifier = blockID;
            final String blocksPrefix = "blocks/";
            if (path.startsWith(blocksPrefix)) {
                blockIdentifier = new Identifier(blockID.getNamespace(), path.substring(blocksPrefix.length()));
            }
            return Registries.BLOCK.get(blockIdentifier);
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
                        .conditionally(InvertedLootCondition.builder(
                                MatchToolLootCondition.builder(
                                        ItemPredicate.Builder.create()
                                                .enchantment(new EnchantmentPredicate(net.minecraft.enchantment.Enchantments.SILK_TOUCH, NumberRange.IntRange.atLeast(1)))
                                                .build()
                                ).build()
                        ).build())
                        .with(ItemEntry.builder(dropItem))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(dropMinCount, dropMaxCount)).build());

                tableBuilder.pool(poolBuilder.build());
            }

        });
    }

    // REPLACE is not required to function for the default behavior; keep safe no-op return to avoid runtime errors.
    public static void registerLootTableReplacement(Identifier lootTableId, Item dropItem, boolean enableModification, float dropChance, int dropMinCount, int dropMaxCount) {
        LootTableEvents.REPLACE.register((resourceManager, lootManager, id, lootTable, source) -> {
            if (source.isBuiltin() && lootTableId.equals(id) && enableModification) {
                OreBuff.LOGGER.warn("REPLACE handler requested for {} but REPLACE is not implemented; skipping replacement and returning original loot table.", lootTableId);
                // If you want to fully replace tables, build and return a new LootTable here.
            }
            return lootTable;
        });
    }

}