package net.cheesestudios.dairymod.worldgen.biome.surface;

import net.cheesestudios.dairymod.block.ModBlocks;
import net.cheesestudios.dairymod.worldgen.biome.ModBiomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;

public class ModSurfaceRules {

    private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    private static final SurfaceRules.RuleSource CHEESE_BLOCK = makeStateRule(ModBlocks.CHEESE_BLOCK.get());
    private static final SurfaceRules.RuleSource CHEESE_PLANKS = makeStateRule(ModBlocks.CHEESE_PLANKS.get());

    public static SurfaceRules.RuleSource makeRules() {

        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);
        SurfaceRules.RuleSource grassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(isAtOrAboveWaterLevel, GRASS_BLOCK), DIRT);

        return SurfaceRules.sequence(
                SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.CHEESE_BIOME),
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, CHEESE_BLOCK)), // floor blocks
                        SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, CHEESE_PLANKS)), // ceiling blocks

                // default to a grass and dirt surface
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, grassSurface)

        );
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {

        return SurfaceRules.state(block.defaultBlockState());

    }
}
