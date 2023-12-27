package net.cheesestudios.dairymod.datagen;

import net.cheesestudios.dairymod.DairyMod;
import net.cheesestudios.dairymod.item.ModItems;
import net.cheesestudios.dairymod.loot.AddItemModifier;
import net.cheesestudios.dairymod.loot.AddSuspiciousBlockItemModifier;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithLootingCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {

    public ModGlobalLootModifiersProvider(PackOutput output) {

        super(output, DairyMod.MOD_ID);

    }

    @Override
    protected void start() {

        // from block
        add("pinecone_from_oak_leaf", new AddItemModifier(new LootItemCondition[]{

                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.OAK_LEAVES).build(),
                LootItemRandomChanceCondition.randomChance(0.2f).build()

        }, ModItems.PINECONE.get()));

        // from entity
        add("snakc_from_creeper", new AddItemModifier(new LootItemCondition[]{

                new LootTableIdCondition.Builder(new ResourceLocation("entities/creeper")).build(),
                LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.05f, 2f).build()

        }, ModItems.CREEPER_SNAKC.get()));

        add("snakc_from_enderman", new AddItemModifier(new LootItemCondition[]{

                new LootTableIdCondition.Builder(new ResourceLocation("entities/enderman")).build(),
                LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.05f, 2f).build()

        }, ModItems.ENDERMAN_SNAKC.get()));

        add("snakc_from_piglin", new AddItemModifier(new LootItemCondition[]{

                new LootTableIdCondition.Builder(new ResourceLocation("entities/piglin")).build(),
                LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.05f, 2f).build()

        }, ModItems.PIGLIN_SNAKC.get()));

        add("snakc_from_skeleton", new AddItemModifier(new LootItemCondition[]{

                new LootTableIdCondition.Builder(new ResourceLocation("entities/skeleton")).build(),
                LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.05f, 2f).build()

        }, ModItems.SKELETON_SNAKC.get()));

        add("snakc_from_wither_skeleton", new AddItemModifier(new LootItemCondition[]{

                new LootTableIdCondition.Builder(new ResourceLocation("entities/wither_skeleton")).build(),
                LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.05f, 2f).build()

        }, ModItems.WITHER_SKELETON_SNAKC.get()));

        // from chest
        add("pinecone_from_jungle_temple_chest", new AddItemModifier(new LootItemCondition[]{

                new LootTableIdCondition.Builder(new ResourceLocation("chests/jungle_temple")).build(),
                LootItemRandomChanceCondition.randomChance(0.25f).build()

        }, ModItems.PINECONE.get()));

        add("nachos_from_pillager_outpost_chest", new AddItemModifier(new LootItemCondition[]{

                new LootTableIdCondition.Builder(new ResourceLocation("chests/pillager_outpost")).build(),
                LootItemRandomChanceCondition.randomChance(0.3f).build()

        }, ModItems.NACHOS.get()));

        // from suspicious sand
        add("cheese_staff_from_suspicious_block", new AddSuspiciousBlockItemModifier(new LootItemCondition[]{

                new LootTableIdCondition.Builder(new ResourceLocation("archaeology/desert_pyramid")).build()

        }, ModItems.CHEESE_STAFF.get()));
    }
}
