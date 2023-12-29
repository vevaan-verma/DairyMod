package net.cheesestudios.dairymod;

import com.mojang.logging.LogUtils;
import net.cheesestudios.dairymod.block.ModBlocks;
import net.cheesestudios.dairymod.block.entity.ModBlockEntities;
import net.cheesestudios.dairymod.item.ModCreativeModeTabs;
import net.cheesestudios.dairymod.item.ModItems;
import net.cheesestudios.dairymod.loot.ModLootModifiers;
import net.cheesestudios.dairymod.recipe.ModRecipes;
import net.cheesestudios.dairymod.screen.DairyCondenserScreen;
import net.cheesestudios.dairymod.screen.ModMenuTypes;
import net.cheesestudios.dairymod.sound.ModSounds;
import net.cheesestudios.dairymod.villager.ModVillagers;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(DairyMod.MOD_ID)
public class DairyMod {

    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "dairymod";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public DairyMod() {

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus); // register mod items
        ModBlocks.register(modEventBus); // register mod blocks
        ModBlockEntities.register(modEventBus); // register mod block entities
        ModRecipes.register(modEventBus); // register mod recipes
        ModMenuTypes.register(modEventBus); // register mod menu types
        ModSounds.register(modEventBus); // register mod sounds
        ModLootModifiers.register(modEventBus); // register mod loot modifiers
        ModVillagers.register(modEventBus); // register mod villagers
        ModCreativeModeTabs.register(modEventBus); // register creative mode tabs

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);

    }

    private void commonSetup(final FMLCommonSetupEvent event) {

        event.enqueueWork(() -> {

            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.CHEESE_FLOWER.getId(), ModBlocks.POTTED_CHEESE_FLOWER);

        });
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {

        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {

            event.accept(ModItems.CHEESE);
            event.accept(ModItems.ICE_CREAM);
            event.accept(ModItems.NACHOS);
            event.accept(ModItems.CONCENTRATED_CHEESE);
            event.accept(ModItems.MILK_SOLIDS);
            event.accept(ModItems.SALT);

        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

            MenuScreens.register(ModMenuTypes.DAIRY_CONDENSER_MENU.get(), DairyCondenserScreen::new);

        }
    }
}
