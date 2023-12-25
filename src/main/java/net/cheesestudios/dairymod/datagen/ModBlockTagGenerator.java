package net.cheesestudios.dairymod.datagen;

import net.cheesestudios.dairymod.DairyMod;
import net.cheesestudios.dairymod.block.ModBlocks;
import net.cheesestudios.dairymod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {

    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {

        super(output, lookupProvider, DairyMod.MOD_ID, existingFileHelper);

    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        // cheese detector detectables
        this.tag(ModTags.Blocks.CHEESE_DETECTOR_DETECTABLES)
                .add(ModBlocks.CHEESE_BLOCK.get());
        //.addTag(Tags.Blocks.ORES) <- for general ores;

        // salt ore tool requirements
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.SALT_ORE.get());

        // salt ore tool requirements
        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.SALT_ORE.get());

        // netherite tool
        // this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL).add();

        // fences & walls
        this.tag(BlockTags.FENCES)
                .add(ModBlocks.CHEESE_FENCE.get());
        this.tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.CHEESE_FENCE_GATE.get());
        this.tag(BlockTags.WALLS)
                .add(ModBlocks.CHEESE_WALL.get());

    }
}
