package net.cheesestudios.dairymod.worldgen;

import net.cheesestudios.dairymod.DairyMod;
import net.cheesestudios.dairymod.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class ModPlacedFeatures {

    // ores
    public static final ResourceKey<PlacedFeature> SALT_ORE_PLACED_KEY = registerKey("salt_ore_placed");
    public static final ResourceKey<PlacedFeature> NETHER_SALT_ORE_PLACED_KEY = registerKey("nether_salt_ore_placed");
    public static final ResourceKey<PlacedFeature> END_SALT_ORE_PLACED_KEY = registerKey("end_salt_ore_placed");

    // trees
    public static final ResourceKey<PlacedFeature> CHEESE_TREE_PLACED_KEY = registerKey("cheese_tree_placed");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {

        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        // ores
        // VerticalAnchor.triangle() means the vein is most likely to spawn in the middle of the clamps, and least likely on the clamps
        register(context, SALT_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_SALT_ORE_KEY),
                ModOrePlacement.commonOrePlacement(12, // count is number of veins per chunk
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80)))); // range of y level spawns
        register(context, NETHER_SALT_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.NETHER_SALT_ORE_KEY),
                ModOrePlacement.commonOrePlacement(12, // count is number of veins per chunk
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80)))); // range of y level spawns
        register(context, END_SALT_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.END_SALT_ORE_KEY),
                ModOrePlacement.commonOrePlacement(12, // count is number of veins per chunk
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(80)))); // range of y level spawns

        // trees
        /*
        GUIDE:
            - p_195365_: amount of trees to place
            - p_195366_: chance of getting [p_195367_] extra trees [IMPORTANT: (1 / THIS PARAMETER) MUST BE AN INTEGER | Error: "Chance data cannot be represented as list weight"]
            - p_195367_: extra saplings
         */
        register(context, CHEESE_TREE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.CHEESE_TREE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ModBlocks.CHEESE_SAPLING.get())); // very important to pass sapling to prevent trees from spawning on top of each other (only spawn where sapling can grow)

    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {

        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(DairyMod.MOD_ID, name));

    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {

        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));

    }
}
