package net.cheesestudios.dairymod.datagen;

import net.cheesestudios.dairymod.DairyMod;
import net.cheesestudios.dairymod.block.ModBlocks;
import net.cheesestudios.dairymod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {

        super(output, DairyMod.MOD_ID, existingFileHelper);

    }

    @Override
    protected void registerModels() {

        // items
        simpleItem(ModItems.CONCENTRATED_CHEESE);
        simpleItem(ModItems.MILK_SOLIDS);
        simpleItem(ModItems.SALT);
        simpleItem(ModItems.PINECONE);

        // advanced items
        simpleItem(ModItems.CHEESE_DETECTOR);

        // foods
        simpleItem(ModItems.CHEESE);
        simpleItem(ModItems.ICE_CREAM);
        simpleItem(ModItems.NACHOS);

        // SPECIAL BLOCK ITEMS
        // stairs & slabs
        evenSimplerBlockItem(ModBlocks.CHEESE_STAIRS);
        evenSimplerBlockItem(ModBlocks.CHEESE_SLAB);

        // buttons & pressure plates
        buttonItem(ModBlocks.CHEESE_BUTTON, ModBlocks.CHEESE_BLOCK);
        evenSimplerBlockItem(ModBlocks.CHEESE_PRESSURE_PLATE);

        // fences & walls
        fenceItem(ModBlocks.CHEESE_FENCE, ModBlocks.CHEESE_BLOCK);
        evenSimplerBlockItem(ModBlocks.CHEESE_FENCE_GATE);
        wallItem(ModBlocks.CHEESE_WALL, ModBlocks.CHEESE_BLOCK);

        // doors
        simpleBlockItem(ModBlocks.CHEESE_DOOR);
        trapdoorItem(ModBlocks.CHEESE_TRAPDOOR);

    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {

        // item/generated -> normal item
        return withExistingParent(item.getId().getPath(), new ResourceLocation("item/generated")).texture("layer0", new ResourceLocation(DairyMod.MOD_ID, "item/" + item.getId().getPath()));

    }

    private ItemModelBuilder simpleBlockItem(RegistryObject<Block> item) {

        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(DairyMod.MOD_ID, "item/" + item.getId().getPath()));

    }

    // for blocks based on full block textures (stairs, slabs, pressure plates, fence gates)
    public void evenSimplerBlockItem(RegistryObject<Block> block) {

        this.withExistingParent(DairyMod.MOD_ID + ":" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));

    }

    public void fenceItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {

        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture", new ResourceLocation(DairyMod.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));

    }

    public void buttonItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {

        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture", new ResourceLocation(DairyMod.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));

    }

    public void wallItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {

        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/wall_inventory"))
                .texture("wall", new ResourceLocation(DairyMod.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));

    }

    public void trapdoorItem(RegistryObject<Block> block) {

        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath() + "_bottom"));

    }
}
