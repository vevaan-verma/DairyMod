package net.cheesestudios.dairymod.datagen;

import net.cheesestudios.dairymod.DairyMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = DairyMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {

        DataGenerator generator = event.getGenerator();
        PackOutput pOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> pProvider = event.getLookupProvider();

        generator.addProvider(event.includeServer(), new ModRecipeProvider(pOutput));
        generator.addProvider(event.includeServer(), ModLootTableProvider.create(pOutput));

        generator.addProvider(event.includeClient(), new ModBlockStateProvider(pOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new ModItemModelProvider(pOutput, existingFileHelper));

        ModBlockTagGenerator blockTagGenerator = generator.addProvider(event.includeServer(),
                new ModBlockTagGenerator(pOutput, pProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new ModItemTagGenerator(pOutput, pProvider, blockTagGenerator.contentsGetter(), existingFileHelper));

        generator.addProvider(event.includeServer(), new ModGlobalLootModifiersProvider(pOutput));
        generator.addProvider(event.includeServer(), new ModPoiTypeTagsProvider(pOutput, pProvider, existingFileHelper));

        generator.addProvider(event.includeServer(), new ModWorldGenProvider(pOutput, pProvider));
        
    }
}
