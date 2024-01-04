package net.cheesestudios.dairymod.worldgen.biome;

import net.cheesestudios.dairymod.DairyMod;
import net.cheesestudios.dairymod.sound.ModSounds;
import net.cheesestudios.dairymod.worldgen.ModPlacedFeatures;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Musics;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;

public class ModBiomes {

    public static final ResourceKey<Biome> CHEESE_BIOME =
            ResourceKey.create(Registries.BIOME, new ResourceLocation(DairyMod.MOD_ID, "cheese_biome"));

    public static void bootstrap(BootstapContext<Biome> context) {

        context.register(CHEESE_BIOME, cheeseBiome(context));

    }

    private static Biome cheeseBiome(BootstapContext<Biome> context) {

        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        // spawns
        // spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.WOLF, 5, 4, 4));
        // spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModBlockEntities.RHINO.get(), 2, 3, 5)); TODO: add cheese mob spawn
        BiomeDefaultFeatures.farmAnimals(spawnBuilder);
        BiomeDefaultFeatures.commonSpawns(spawnBuilder);

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        // IMPORTANT: SAME ORDER AS VANILLA BIOMES MUST BE FOLLOWED FROM THE 'BiomeDefaultFeatures' CLASS
        globalOverworldGeneration(biomeBuilder);
        BiomeDefaultFeatures.addMossyStoneBlock(biomeBuilder);
        BiomeDefaultFeatures.addForestFlowers(biomeBuilder);
        BiomeDefaultFeatures.addFerns(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addExtraGold(biomeBuilder);

        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.TREES_PLAINS);

        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        BiomeDefaultFeatures.addDefaultExtraVegetation(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.CHEESE_TREE_PLACED_KEY);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.8f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0xe82e3b)
                        .waterFogColor(0xbf1b26)
                        .skyColor(0x30c918)
                        .grassColorOverride(0x7f03fc)
                        .foliageColorOverride(0xd203fc)
                        .fogColor(0x22a1e6)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.GREEDY.getHolder().get())).build()) // doesn't work for some reason
                .build();

    }

    public static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder) {

        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultSprings(builder);
        BiomeDefaultFeatures.addSurfaceFreezing(builder);

    }
}
