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
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
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

        // block entities
        simpleBlockWithItem(ModBlocks.DAIRY_CONDENSER.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/dairy_condenser"))); // use model file because blockbench gives json file not texture

        // custom wood
        logBlock((RotatedPillarBlock) ModBlocks.CHEESE_LOG.get());
        axisBlock(((RotatedPillarBlock) ModBlocks.CHEESE_WOOD.get()), blockTexture(ModBlocks.CHEESE_LOG.get()),
                blockTexture(ModBlocks.CHEESE_LOG.get())); // use cheese log texture for wood too
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_CHEESE_LOG.get()), blockTexture(ModBlocks.STRIPPED_CHEESE_LOG.get()),
                new ResourceLocation(DairyMod.MOD_ID, "block/stripped_cheese_log_top"));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_CHEESE_WOOD.get()), blockTexture(ModBlocks.STRIPPED_CHEESE_LOG.get()),
                blockTexture(ModBlocks.STRIPPED_CHEESE_LOG.get()));

        blockItem(ModBlocks.CHEESE_LOG);
        blockItem(ModBlocks.CHEESE_WOOD);
        blockItem(ModBlocks.STRIPPED_CHEESE_LOG);
        blockItem(ModBlocks.STRIPPED_CHEESE_WOOD);

        blockWithItem(ModBlocks.CHEESE_PLANKS);

        // custom leaves
        leavesBlock(ModBlocks.CHEESE_LEAVES);

        // signs
        signBlock(((StandingSignBlock) ModBlocks.CHEESE_SIGN.get()), ((WallSignBlock) ModBlocks.CHEESE_WALL_SIGN.get()),
                blockTexture(ModBlocks.CHEESE_PLANKS.get()));
        hangingSignBlock(ModBlocks.CHEESE_HANGING_SIGN.get(), ModBlocks.CHEESE_WALL_HANGING_SIGN.get(), blockTexture(ModBlocks.CHEESE_PLANKS.get()));

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

    private void blockItem(RegistryObject<Block> blockRegistryObject) {

        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(DairyMod.MOD_ID +
                ":block/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));

    }

    private void leavesBlock(RegistryObject<Block> blockRegistryObject) {

        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), new ResourceLocation("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));

    }

    public void hangingSignBlock(Block signBlock, Block wallSignBlock, ResourceLocation texture) {

        ModelFile sign = models().sign(name(signBlock), texture);
        hangingSignBlock(signBlock, wallSignBlock, sign);

    }

    public void hangingSignBlock(Block signBlock, Block wallSignBlock, ModelFile sign) {

        simpleBlock(signBlock, sign);
        simpleBlock(wallSignBlock, sign);

    }

    private String name(Block block) {

        return key(block).getPath();

    }

    private ResourceLocation key(Block block) {

        return ForgeRegistries.BLOCKS.getKey(block);

    }
}
