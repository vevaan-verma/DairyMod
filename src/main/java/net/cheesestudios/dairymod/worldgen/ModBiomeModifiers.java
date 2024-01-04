package net.cheesestudios.dairymod.worldgen;

import net.cheesestudios.dairymod.DairyMod;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBiomeModifiers {

    // ores
    public static final ResourceKey<BiomeModifier> ADD_SALT_ORE = registerKey("add_salt_ore");
    public static final ResourceKey<BiomeModifier> ADD_NETHER_SALT_ORE = registerKey("add_nether_salt_ore");
    public static final ResourceKey<BiomeModifier> ADD_END_SALT_ORE = registerKey("add_end_salt_ore");

    // trees
    public static final ResourceKey<BiomeModifier> ADD_CHEESE_TREE = registerKey("add_cheese_tree");

    public static void bootstrap(BootstapContext<BiomeModifier> context) {

        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);

        // ores
        context.register(ADD_SALT_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(

                biomes.getOrThrow(BiomeTags.IS_OVERWORLD), // for specific biomes use Biomes.____
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.SALT_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES

        ));

        context.register(ADD_NETHER_SALT_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(

                biomes.getOrThrow(BiomeTags.IS_NETHER), // for specific biomes use Biomes.____
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.NETHER_SALT_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES

        ));

        context.register(ADD_END_SALT_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(

                biomes.getOrThrow(BiomeTags.IS_END), // for specific biomes use Biomes.____
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.END_SALT_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES

        ));

        // trees
        context.register(ADD_CHEESE_TREE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(

                biomes.getOrThrow(Tags.Biomes.IS_PLAINS), // biome
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.CHEESE_TREE_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION // important for trees

        ));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {

        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(DairyMod.MOD_ID, name));

    }
}
