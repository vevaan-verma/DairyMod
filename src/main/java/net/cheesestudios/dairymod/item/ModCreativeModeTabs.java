package net.cheesestudios.dairymod.item;

import net.cheesestudios.dairymod.DairyMod;
import net.cheesestudios.dairymod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DairyMod.MOD_ID);

    // mod creative tabs
    public static final RegistryObject<CreativeModeTab> DAIRY_TAB = CREATIVE_MODE_TABS.register("dairy_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.CHEESE.get()))
                    .title(Component.translatable("creativetab.dairy_tab"))
                    .displayItems((itemDisplayParameters, output) -> {

                        // items
                        output.accept(ModItems.CONCENTRATED_CHEESE.get());
                        output.accept(ModItems.MILK_SOLIDS.get());
                        output.accept(ModItems.SALT.get());

                        // advanced items
                        output.accept(ModItems.CHEESE_DETECTOR.get());
                        output.accept(ModItems.CHEESE_STAFF.get());

                        // tools
                        output.accept(ModItems.CHEESE_SWORD.get());
                        output.accept(ModItems.CHEESE_PICKAXE.get());
                        output.accept(ModItems.CHEESE_AXE.get());
                        output.accept(ModItems.CHEESE_SHOVEL.get());
                        output.accept(ModItems.CHEESE_HOE.get());

                        // armor
                        output.accept(ModItems.CHEESE_HELMET.get());
                        output.accept(ModItems.CHEESE_CHESTPLATE.get());
                        output.accept(ModItems.CHEESE_LEGGINGS.get());
                        output.accept(ModItems.CHEESE_BOOTS.get());

                        // food
                        output.accept(ModItems.CHEESE.get());
                        output.accept(ModItems.ICE_CREAM.get());
                        output.accept(ModItems.NACHOS.get());

                        // snakcs
                        output.accept(ModItems.CREEPER_SNAKC.get());
                        output.accept(ModItems.ENDERMAN_SNAKC.get());
                        output.accept(ModItems.PIGLIN_SNAKC.get());
                        output.accept(ModItems.SKELETON_SNAKC.get());
                        output.accept(ModItems.WITHER_SKELETON_SNAKC.get());

                        // crop seeds
                        output.accept(ModItems.CHEESE_SEEDS.get());

                        // flowers
                        output.accept(ModBlocks.CHEESE_FLOWER.get());

                        // fuel items
                        output.accept(ModItems.PINECONE.get());

                        // blocks
                        output.accept(ModBlocks.CHEESE_BLOCK.get());
                        output.accept(ModBlocks.SALT_BLOCK.get());

                        // ores
                        output.accept(ModBlocks.SALT_ORE.get());

                        // advanced blocks
                        output.accept(ModBlocks.SOUND_BLOCK.get());

                        // SPECIAL BLOCKS
                        // stairs & slabs
                        output.accept(ModBlocks.CHEESE_STAIRS.get());
                        output.accept(ModBlocks.CHEESE_SLAB.get());

                        // buttons & pressure plates
                        output.accept(ModBlocks.CHEESE_BUTTON.get());
                        output.accept(ModBlocks.CHEESE_PRESSURE_PLATE.get());

                        // fences & walls
                        output.accept(ModBlocks.CHEESE_FENCE.get());
                        output.accept(ModBlocks.CHEESE_FENCE_GATE.get());
                        output.accept(ModBlocks.CHEESE_WALL.get());

                        // doors
                        output.accept(ModBlocks.CHEESE_DOOR.get());
                        output.accept(ModBlocks.CHEESE_TRAPDOOR.get());

                    })
                    .build());

    public static void register(IEventBus eventBus) {

        CREATIVE_MODE_TABS.register(eventBus);

    }
}
