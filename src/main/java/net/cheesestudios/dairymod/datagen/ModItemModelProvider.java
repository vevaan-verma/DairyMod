package net.cheesestudios.dairymod.datagen;

import net.cheesestudios.dairymod.DairyMod;
import net.cheesestudios.dairymod.block.ModBlocks;
import net.cheesestudios.dairymod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedHashMap;

public class ModItemModelProvider extends ItemModelProvider {

    private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();

    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1f);
        trimMaterials.put(TrimMaterials.IRON, 0.2f);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3f);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4f);
        trimMaterials.put(TrimMaterials.COPPER, 0.5f);
        trimMaterials.put(TrimMaterials.GOLD, 0.6f);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7f);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8f);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9f);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0f);
    }

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

        // music discs
        simpleItem(ModItems.AFTER_HOURS_MUSIC_DISC);
        simpleItem(ModItems.GREEDY_MUSIC_DISC);
        simpleItem(ModItems.HOUDINI_EXTENDED_MUSIC_DISC);
        simpleItem(ModItems.IN_THE_NIGHT_MUSIC_DISC);
        simpleItem(ModItems.POPULAR_MUSIC_DISC);

        // advanced items
        simpleItem(ModItems.CHEESE_DETECTOR);

        // handheld items
        handheldItem(ModItems.CHEESE_SWORD);
        handheldItem(ModItems.CHEESE_PICKAXE);
        handheldItem(ModItems.CHEESE_AXE);
        handheldItem(ModItems.CHEESE_SHOVEL);
        handheldItem(ModItems.CHEESE_HOE);

        // armor (trimmed)
        trimmedArmorItem(ModItems.CHEESE_HELMET);
        trimmedArmorItem(ModItems.CHEESE_CHESTPLATE);
        trimmedArmorItem(ModItems.CHEESE_LEGGINGS);
        trimmedArmorItem(ModItems.CHEESE_BOOTS);

        // foods
        simpleItem(ModItems.CHEESE);
        simpleItem(ModItems.ICE_CREAM);
        simpleItem(ModItems.NACHOS);

        // snakcs
        simpleItem(ModItems.CREEPER_SNAKC);
        simpleItem(ModItems.ENDERMAN_SNAKC);
        simpleItem(ModItems.PIGLIN_SNAKC);
        simpleItem(ModItems.SKELETON_SNAKC);
        simpleItem(ModItems.WITHER_SKELETON_SNAKC);

        // crop seeds
        simpleItem(ModItems.CHEESE_SEEDS);

        // signs
        simpleItem(ModItems.CHEESE_SIGN);
        simpleItem(ModItems.CHEESE_HANGING_SIGN);

        // flowers
        simpleBlockItemBlockTexture(ModBlocks.CHEESE_FLOWER);

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

    // for flowers
    private ItemModelBuilder simpleBlockItemBlockTexture(RegistryObject<Block> item) {

        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(DairyMod.MOD_ID, "block/" + item.getId().getPath()));

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

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {

        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(DairyMod.MOD_ID, "item/" + item.getId().getPath()));

    }

    // shoutout to El_Redstoniano for making this
    private void trimmedArmorItem(RegistryObject<Item> itemRegistryObject) {

        final String MOD_ID = DairyMod.MOD_ID;

        if (itemRegistryObject.get() instanceof ArmorItem armorItem) {

            trimMaterials.entrySet().forEach(entry -> {

                ResourceKey<TrimMaterial> trimMaterial = entry.getKey();
                float trimValue = entry.getValue();

                String armorType = switch (armorItem.getEquipmentSlot()) {

                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";

                };

                String armorItemPath = "item/" + armorItem;
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = new ResourceLocation(MOD_ID, armorItemPath);
                ResourceLocation trimResLoc = new ResourceLocation(trimPath); // minecraft namespace
                ResourceLocation trimNameResLoc = new ResourceLocation(MOD_ID, currentTrimName);

                // this is used for making the ExistingFileHelper acknowledge that this texture exist, so this will
                // avoid an IllegalArgumentException
                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                // trimmed armorItem files
                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc)
                        .texture("layer1", trimResLoc);

                // non-trimmed armorItem file (normal variant)
                this.withExistingParent(itemRegistryObject.getId().getPath(),
                                mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                new ResourceLocation(MOD_ID,
                                        "item/" + itemRegistryObject.getId().getPath()));

            });
        }
    }
}
