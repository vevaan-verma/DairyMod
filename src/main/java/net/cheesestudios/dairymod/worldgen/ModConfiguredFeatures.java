package net.cheesestudios.dairymod.worldgen;

import net.cheesestudios.dairymod.DairyMod;
import net.cheesestudios.dairymod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {

    // ores
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_SALT_ORE_KEY = registerKey("salt_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_SALT_ORE_KEY = registerKey("nether_salt_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> END_SALT_ORE_KEY = registerKey("end_salt_ore");

    // trees
    public static final ResourceKey<ConfiguredFeature<?, ?>> CHEESE_TREE_KEY = registerKey("cheese_tree");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {

        // ores
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherrackReplaceables = new BlockMatchTest(Blocks.NETHERRACK);
        RuleTest endReplaceables = new BlockMatchTest(Blocks.END_STONE);

        List<OreConfiguration.TargetBlockState> overworldSaltOres = List.of(OreConfiguration.target(stoneReplaceables,
                        ModBlocks.SALT_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_SALT_ORE.get().defaultBlockState())); // both deepslate and stone ores can spawn in overworld

        register(context, OVERWORLD_SALT_ORE_KEY, Feature.ORE, new OreConfiguration(overworldSaltOres, 9)); // size is the vein size
        register(context, NETHER_SALT_ORE_KEY, Feature.ORE, new OreConfiguration(netherrackReplaceables, ModBlocks.NETHERRACK_SALT_ORE.get().defaultBlockState(), 9));
        register(context, END_SALT_ORE_KEY, Feature.ORE, new OreConfiguration(endReplaceables, ModBlocks.END_STONE_SALT_ORE.get().defaultBlockState(), 9));

        // trees
        register(context, CHEESE_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(

                // RECOMMENDED TO LOOK AT EXISTING EXAMPLES
                BlockStateProvider.simple(ModBlocks.CHEESE_LOG.get()), // wood to be placed
                new StraightTrunkPlacer(3, 1, 2), // placed in straight trunk pattern | parameters: (base height, random height addon 1, random height addon 2)

                BlockStateProvider.simple(ModBlocks.CHEESE_LEAVES.get()), // leaves to be placed
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3), // placed in blob foliage pattern | parameters: (radius, offset from trunk, height)

                new TwoLayersFeatureSize(1, 0, 2)).build() // checks space around the tree being placed

        );

    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {

        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(DairyMod.MOD_ID, name));

    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {

        context.register(key, new ConfiguredFeature<>(feature, configuration));

    }
}
