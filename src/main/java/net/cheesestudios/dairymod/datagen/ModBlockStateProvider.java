package net.cheesestudios.dairymod.datagen;

import net.cheesestudios.dairymod.DairyMod;
import net.cheesestudios.dairymod.block.ModBlocks;
import net.cheesestudios.dairymod.block.custom.CheeseCropBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {

        super(output, DairyMod.MOD_ID, exFileHelper);

    }

    @Override
    protected void registerStatesAndModels() {

        // crops
        makeCheeseCrop((CropBlock) ModBlocks.CHEESE_CROP.get(), "cheese_stage", "cheese_stage");

        // flowers
        simpleBlockWithItem(ModBlocks.CHEESE_FLOWER.get(), models().cross(blockTexture(ModBlocks.CHEESE_FLOWER.get()).getPath(),
                blockTexture(ModBlocks.CHEESE_FLOWER.get())).renderType("cutout"));
        simpleBlockWithItem(ModBlocks.POTTED_CHEESE_FLOWER.get(), models().singleTexture("potted_cheese_flower",
                new ResourceLocation("flower_pot_cross"), "plant",
                blockTexture(ModBlocks.CHEESE_FLOWER.get())).renderType("cutout"));

        // blocks
        blockWithItem(ModBlocks.CHEESE_BLOCK);
        blockWithItem(ModBlocks.SALT_BLOCK);

        // ores
        blockWithItem(ModBlocks.SALT_ORE);

        // advanced blocks
        blockWithItem(ModBlocks.SOUND_BLOCK);

        // stairs
        stairsBlock((StairBlock) ModBlocks.CHEESE_STAIRS.get(), blockTexture(ModBlocks.CHEESE_BLOCK.get()));

        // slabs
        slabBlock((SlabBlock) ModBlocks.CHEESE_SLAB.get(), blockTexture(ModBlocks.CHEESE_BLOCK.get()), blockTexture(ModBlocks.CHEESE_BLOCK.get()));

        // buttons & pressure plates
        buttonBlock((ButtonBlock) ModBlocks.CHEESE_BUTTON.get(), blockTexture(ModBlocks.CHEESE_BLOCK.get()));
        pressurePlateBlock((PressurePlateBlock) ModBlocks.CHEESE_PRESSURE_PLATE.get(), blockTexture(ModBlocks.CHEESE_BLOCK.get()));

        // fences & walls
        fenceBlock((FenceBlock) ModBlocks.CHEESE_FENCE.get(), blockTexture(ModBlocks.CHEESE_BLOCK.get()));
        fenceGateBlock((FenceGateBlock) ModBlocks.CHEESE_FENCE_GATE.get(), blockTexture(ModBlocks.CHEESE_BLOCK.get()));
        wallBlock((WallBlock) ModBlocks.CHEESE_WALL.get(), blockTexture(ModBlocks.CHEESE_BLOCK.get()));

        // doors
        doorBlockWithRenderType((DoorBlock) ModBlocks.CHEESE_DOOR.get(), modLoc("block/cheese_door_bottom"), modLoc("block/cheese_door_top"), "cutout");
        trapdoorBlockWithRenderType((TrapDoorBlock) ModBlocks.CHEESE_TRAPDOOR.get(), modLoc("block/cheese_trapdoor"), true, "cutout");

    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {

        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));

    }

    public void makeCheeseCrop(CropBlock block, String modelName, String textureName) {

        Function<BlockState, ConfiguredModel[]> function = state -> cheeseCropStates(state, block, modelName, textureName);
        getVariantBuilder(block).forAllStates(function);

    }

    private ConfiguredModel[] cheeseCropStates(BlockState state, CropBlock block, String modelName, String textureName) {

        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((CheeseCropBlock) block).getAgeProperty()),
                new ResourceLocation(DairyMod.MOD_ID, "block/" + textureName + state.getValue(((CheeseCropBlock) block).getAgeProperty()))).renderType("cutout"));

        return models;

    }
}
