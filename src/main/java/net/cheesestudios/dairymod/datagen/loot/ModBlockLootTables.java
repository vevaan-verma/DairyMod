package net.cheesestudios.dairymod.datagen.loot;

import net.cheesestudios.dairymod.block.ModBlocks;
import net.cheesestudios.dairymod.block.custom.CheeseCropBlock;
import net.cheesestudios.dairymod.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {

    public ModBlockLootTables() {

        super(Set.of(), FeatureFlags.REGISTRY.allFlags());

    }

    @Override
    protected void generate() {

        // crops
        LootItemCondition.Builder lootitemcondition$builder = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(ModBlocks.CHEESE_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(CheeseCropBlock.AGE, 3));
        this.add(ModBlocks.CHEESE_CROP.get(), createCropDrops(ModBlocks.CHEESE_CROP.get(), ModItems.CHEESE.get(),
                ModItems.CHEESE_SEEDS.get(), lootitemcondition$builder));

        // flowers
        this.dropSelf(ModBlocks.CHEESE_FLOWER.get());
        this.add(ModBlocks.POTTED_CHEESE_FLOWER.get(), createPotFlowerItemTable(ModBlocks.CHEESE_FLOWER.get()));

        // blocks
        this.dropSelf(ModBlocks.CHEESE_BLOCK.get());
        this.dropSelf(ModBlocks.SALT_BLOCK.get());

        // advanced blocks
        this.dropSelf(ModBlocks.SOUND_BLOCK.get());
        this.add(ModBlocks.SOUND_BLOCK.get(), block -> createCopperLikeOreDrops(ModBlocks.SOUND_BLOCK.get(), ModItems.CHEESE.get()));

        // ores
        this.add(ModBlocks.SALT_ORE.get(), block -> createCopperLikeOreDrops(ModBlocks.SALT_ORE.get(), ModItems.SALT.get()));

        // stairs
        this.dropSelf(ModBlocks.CHEESE_STAIRS.get());

        // slabs
        this.add(ModBlocks.CHEESE_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.CHEESE_SLAB.get()));

        // buttons & pressure plates
        this.dropSelf(ModBlocks.CHEESE_BUTTON.get());
        this.dropSelf(ModBlocks.CHEESE_PRESSURE_PLATE.get());

        // fences & walls
        this.dropSelf(ModBlocks.CHEESE_FENCE.get());
        this.dropSelf(ModBlocks.CHEESE_FENCE_GATE.get());
        this.dropSelf(ModBlocks.CHEESE_WALL.get());

        // doors
        this.add(ModBlocks.CHEESE_DOOR.get(),
                block -> createDoorTable(ModBlocks.CHEESE_DOOR.get()));
        this.dropSelf(ModBlocks.CHEESE_TRAPDOOR.get());

        // block entities
        this.dropSelf(ModBlocks.DAIRY_CONDENSER.get());

        // custom wood
        this.dropSelf(ModBlocks.CHEESE_LOG.get());
        this.dropSelf(ModBlocks.CHEESE_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_CHEESE_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_CHEESE_WOOD.get());
        this.dropSelf(ModBlocks.CHEESE_PLANKS.get());

        // custom leaves
        this.add(ModBlocks.CHEESE_LEAVES.get(), block ->
                createLeavesDrops(block, ModBlocks.CHEESE_BLOCK.get(), NORMAL_LEAVES_SAPLING_CHANCES)); // TODO: change to drop sapling

    }

    @Override
    protected Iterable<Block> getKnownBlocks() {

        // all known blocks with loot tables
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;

    }

    protected LootTable.Builder createCopperLikeOreDrops(Block pBlock, Item item) {

        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));

    }

    protected LootTable.Builder createLapisLikeOreDrops(Block pBlock, Item item) {

        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 9.0F)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));

    }

    protected LootTable.Builder createRedstoneLikeOreDrops(Block pBlock, Item item) {

        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 5.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE))));

    }
}
