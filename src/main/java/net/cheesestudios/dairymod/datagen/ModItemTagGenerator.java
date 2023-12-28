package net.cheesestudios.dairymod.datagen;

import net.cheesestudios.dairymod.DairyMod;
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
    protected void addTags(HolderLookup.Provider provider) {

        this.tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.CHEESE_HELMET.get(),
                        ModItems.CHEESE_CHESTPLATE.get(),
                        ModItems.CHEESE_LEGGINGS.get(),
                        ModItems.CHEESE_BOOTS.get());
        
    }
}
