package net.cheesestudios.dairymod.block;

import net.cheesestudios.dairymod.DairyMod;
import net.cheesestudios.dairymod.block.custom.*;
import net.cheesestudios.dairymod.item.ModItems;
import net.cheesestudios.dairymod.sound.ModSounds;
import net.cheesestudios.dairymod.util.ModWoodTypes;
import net.cheesestudios.dairymod.worldgen.tree.CheeseTreeGrower;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, DairyMod.MOD_ID);

    // blocks
    public static final RegistryObject<Block> CHEESE_BLOCK = registerBlock("cheese_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL).sound(SoundType.HONEY_BLOCK)));
    public static final RegistryObject<Block> SALT_BLOCK = registerBlock("salt_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops().sound(SoundType.SAND)));

    // ores
    public static final RegistryObject<Block> SALT_ORE = registerBlock("salt_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(2f).requiresCorrectToolForDrops(),
                    UniformInt.of(2, 5)));
    public static final RegistryObject<Block> NETHERRACK_SALT_ORE = registerBlock("netherrack_salt_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.NETHERRACK).strength(1f).requiresCorrectToolForDrops(),
                    UniformInt.of(3, 5)));
    public static final RegistryObject<Block> DEEPSLATE_SALT_ORE = registerBlock("deepslate_salt_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).strength(3f).requiresCorrectToolForDrops(),
                    UniformInt.of(3, 7)));
    public static final RegistryObject<Block> END_STONE_SALT_ORE = registerBlock("end_stone_salt_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.END_STONE).strength(5f).requiresCorrectToolForDrops(),
                    UniformInt.of(3, 7)));

    // advanced blocks
    public static final RegistryObject<Block> SOUND_BLOCK = registerBlock("sound_block",
            () -> new SoundBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops().sound(ModSounds.SOUND_BLOCK_SOUNDS)));

    // SPECIAL BLOCKS
    // stairs & slabs
    public static final RegistryObject<Block> CHEESE_STAIRS = registerBlock("cheese_stairs",
            () -> new StairBlock(() -> ModBlocks.CHEESE_BLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL).sound(SoundType.HONEY_BLOCK)));
    public static final RegistryObject<Block> CHEESE_SLAB = registerBlock("cheese_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL).sound(SoundType.HONEY_BLOCK)));

    // buttons & pressure plates
    public static final RegistryObject<Block> CHEESE_BUTTON = registerBlock("cheese_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BUTTON).sound(SoundType.HONEY_BLOCK),
                    BlockSetType.IRON, 10, true));
    public static final RegistryObject<Block> CHEESE_PRESSURE_PLATE = registerBlock("cheese_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                    BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL).sound(SoundType.HONEY_BLOCK), BlockSetType.IRON));

    // fences & walls
    public static final RegistryObject<Block> CHEESE_FENCE = registerBlock("cheese_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL).sound(SoundType.HONEY_BLOCK)));
    public static final RegistryObject<Block> CHEESE_FENCE_GATE = registerBlock("cheese_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL).sound(SoundType.HONEY_BLOCK), SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE));
    public static final RegistryObject<Block> CHEESE_WALL = registerBlock("cheese_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL).sound(SoundType.HONEY_BLOCK)));

    // doors
    // IF YOU DON"T COPY A DOOR BLOCK USE .noOcclusion()
    public static final RegistryObject<Block> CHEESE_DOOR = registerBlock("cheese_door",
            () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL).sound(SoundType.HONEY_BLOCK).noOcclusion(), BlockSetType.OAK));
    public static final RegistryObject<Block> CHEESE_TRAPDOOR = registerBlock("cheese_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL).sound(SoundType.HONEY_BLOCK).noOcclusion(), BlockSetType.OAK));

    // crops
    // use BLOCKS.register() for crops because there is no associated block item (seeds are block item)
    public static final RegistryObject<Block> CHEESE_CROP = BLOCKS.register("cheese_crop",
            () -> new CheeseCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));

    // flowers
    public static final RegistryObject<Block> CHEESE_FLOWER = registerBlock("cheese_flower",
            () -> new FlowerBlock(MobEffects.SATURATION, 5,
                    BlockBehaviour.Properties.copy(Blocks.OXEYE_DAISY).noOcclusion().noCollission())); // duration is in seconds
    public static final RegistryObject<Block> POTTED_CHEESE_FLOWER = BLOCKS.register("potted_cheese_flower", // don't want an item to be generated with this, use Blocks.register()
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), ModBlocks.CHEESE_FLOWER,
                    BlockBehaviour.Properties.copy(Blocks.POTTED_OXEYE_DAISY).noOcclusion()));

    // block entities
    public static final RegistryObject<Block> DAIRY_CONDENSER = registerBlock("dairy_condenser",
            () -> new DairyCondenserBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()));

    // saplings
    public static final RegistryObject<Block> CHEESE_SAPLING = registerBlock("cheese_sapling",
            () -> new SaplingBlock(new CheeseTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

    // wood
    public static final RegistryObject<Block> CHEESE_LOG = registerBlock("cheese_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).strength(3f)));
    public static final RegistryObject<Block> CHEESE_WOOD = registerBlock("cheese_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).strength(3f)));
    public static final RegistryObject<Block> STRIPPED_CHEESE_LOG = registerBlock("stripped_cheese_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).strength(3f)));
    public static final RegistryObject<Block> STRIPPED_CHEESE_WOOD = registerBlock("stripped_cheese_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD).strength(3f)));
    public static final RegistryObject<Block> CHEESE_PLANKS = registerBlock("cheese_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {

                // anonymous class can be turned into new PlankBlock class
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {

                    return true;

                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {

                    return 20;

                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {

                    return 5;

                }

            });

    // custom leaves
    public static final RegistryObject<Block> CHEESE_LEAVES = registerBlock("cheese_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)) {

                // anonymous class can be turned into new LeavesBlock class
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {

                    return true;

                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {

                    return 60;

                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {

                    return 30;

                }

            });

    // signs
    // use BLOCKS.register() for signs because there is no associated block item
    public static final RegistryObject<Block> CHEESE_SIGN = BLOCKS.register("cheese_sign",
            () -> new ModStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), ModWoodTypes.CHEESE));
    public static final RegistryObject<Block> CHEESE_WALL_SIGN = BLOCKS.register("cheese_wall_sign",
            () -> new ModWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), ModWoodTypes.CHEESE));
    public static final RegistryObject<Block> CHEESE_HANGING_SIGN = BLOCKS.register("cheese_hanging_sign",
            () -> new ModHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_HANGING_SIGN), ModWoodTypes.CHEESE));
    public static final RegistryObject<Block> CHEESE_WALL_HANGING_SIGN = BLOCKS.register("cheese_wall_hanging_sign",
            () -> new ModWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_HANGING_SIGN), ModWoodTypes.CHEESE));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {

        // register block
        RegistryObject<T> returnBlock = BLOCKS.register(name, block);
        registerBlockItem(name, returnBlock);
        return returnBlock;

    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {

        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties())); // register actual item associated with block (not done automatically)

    }

    public static void register(IEventBus eventBus) {

        BLOCKS.register(eventBus);

    }
}
