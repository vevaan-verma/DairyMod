package net.cheesestudios.dairymod.datagen;

import net.cheesestudios.dairymod.DairyMod;
import net.cheesestudios.dairymod.block.ModBlocks;
import net.cheesestudios.dairymod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {

    public ModItemTagGenerator(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pProvider, CompletableFuture<TagLookup<Block>> pBlock, @Nullable ExistingFileHelper existingFileHelper) {

        super(pOutput, pProvider, pBlock, DairyMod.MOD_ID, existingFileHelper);

    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {

        // music discs
        this.tag(ItemTags.MUSIC_DISCS)
                .add(ModItems.AFTER_HOURS_MUSIC_DISC.get(),
                        ModItems.GREEDY_MUSIC_DISC.get(),
                        ModItems.HOUDINI_EXTENDED_MUSIC_DISC.get(),
                        ModItems.IN_THE_NIGHT_MUSIC_DISC.get(),
                        ModItems.POPULAR_MUSIC_DISC.get());

        this.tag(ItemTags.CREEPER_DROP_MUSIC_DISCS) // for dropping when creeper is shot by skeleton
                .add(ModItems.AFTER_HOURS_MUSIC_DISC.get(),
                        ModItems.GREEDY_MUSIC_DISC.get(),
                        ModItems.HOUDINI_EXTENDED_MUSIC_DISC.get(),
                        ModItems.IN_THE_NIGHT_MUSIC_DISC.get(),
                        ModItems.POPULAR_MUSIC_DISC.get());

        // armor
        this.tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.CHEESE_HELMET.get(),
                        ModItems.CHEESE_CHESTPLATE.get(),
                        ModItems.CHEESE_LEGGINGS.get(),
                        ModItems.CHEESE_BOOTS.get());

        // custom wood
        this.tag(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.CHEESE_LOG.get().asItem(),
                        ModBlocks.CHEESE_WOOD.get().asItem(),
                        ModBlocks.STRIPPED_CHEESE_LOG.get().asItem(),
                        ModBlocks.STRIPPED_CHEESE_WOOD.get().asItem());

        this.tag(ItemTags.PLANKS)
                .add(ModBlocks.CHEESE_PLANKS.get().asItem());
        
    }
}
