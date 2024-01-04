package net.cheesestudios.dairymod.worldgen.biome;

import net.cheesestudios.dairymod.DairyMod;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;

public class ModTerrablender {

    public static void registerBiomes() {

        Regions.register(new ModOverworldRegion(new ResourceLocation(DairyMod.MOD_ID, "overworld"), 5)); // weight determines how often the custom biome is going to be used

    }
}
