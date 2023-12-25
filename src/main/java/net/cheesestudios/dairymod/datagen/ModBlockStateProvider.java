package net.cheesestudios.dairymod.datagen;

import net.cheesestudios.dairymod.DairyMod;
import net.cheesestudios.dairymod.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {

        super(output, DairyMod.MOD_ID, exFileHelper);

    }

    @Override
    protected void registerStatesAndModels() {

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
}
