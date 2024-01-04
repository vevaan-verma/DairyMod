package net.cheesestudios.dairymod.worldgen.tree.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.cheesestudios.dairymod.worldgen.tree.ModFoliagePlacers;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class CheeseFoliagePlacer extends FoliagePlacer {

    public static final Codec<CheeseFoliagePlacer> CODEC = RecordCodecBuilder.create(cheeseFoliagePlacerInstance ->
            foliagePlacerParts(cheeseFoliagePlacerInstance).and(Codec.intRange(0, 16).fieldOf("height")
                    .forGetter(fp -> fp.height)).apply(cheeseFoliagePlacerInstance, CheeseFoliagePlacer::new));

    private final int height;

    public CheeseFoliagePlacer(IntProvider pRadius, IntProvider pOffset, int height) {

        super(pRadius, pOffset);
        this.height = height;

    }

    @Override
    protected FoliagePlacerType<?> type() {

        return ModFoliagePlacers.CHEESE_FOLIAGE_PLACER.get();

    }

    // foliage version of placeTrunk()
    @Override
    protected void createFoliage(LevelSimulatedReader pLevel, FoliageSetter pBlockSetter, RandomSource pRandom, TreeConfiguration pConfig,
                                 int pMaxTreeHeight, FoliageAttachment pAttachment, int pFoliageHeight, int pFoliageRadius, int pOffset) {

        // pAttachment.pos(): first position above last log that was placed
        /*
        GUIDE:
            - pRange: radius from center point (radius 1 = 3x3, radius 2 = 5x5, etc.)
            - pLocalY: change in y position locally
         */

        /*
        simple leaves code:
        this.placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().above(0), 2, 2, pAttachment.doubleTrunk());
        this.placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().above(1), 2, 2, pAttachment.doubleTrunk());
        this.placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos().above(2), 2, 2, pAttachment.doubleTrunk());
        */

        for (int i = pOffset; i >= pOffset - pFoliageHeight; i--)
            this.placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, pAttachment.pos(), Math.max(pFoliageRadius + pAttachment.radiusOffset() - 1 - i / 2, 0), i, pAttachment.doubleTrunk());

    }

    @Override
    public int foliageHeight(RandomSource pRandom, int pHeight, TreeConfiguration pConfig) {

        return this.height;

    }

    @Override
    protected boolean shouldSkipLocation(RandomSource pRandom, int pLocalX, int pLocalY, int pLocalZ, int pRange, boolean pLarge) {

        return false;

    }
}
