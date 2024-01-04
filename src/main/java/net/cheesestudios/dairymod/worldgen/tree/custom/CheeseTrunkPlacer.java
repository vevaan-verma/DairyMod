package net.cheesestudios.dairymod.worldgen.tree.custom;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.cheesestudios.dairymod.worldgen.tree.ModTrunkPlacerTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;

public class CheeseTrunkPlacer extends TrunkPlacer {

    public static final Codec<CheeseTrunkPlacer> CODEC = RecordCodecBuilder.create(cheeseTrunkPlacerInstance ->
            trunkPlacerParts(cheeseTrunkPlacerInstance).apply(cheeseTrunkPlacerInstance, CheeseTrunkPlacer::new));

    public CheeseTrunkPlacer(int pBaseHeight, int pHeightRandA, int pHeightRandB) {

        super(pBaseHeight, pHeightRandA, pHeightRandB);

    }

    @Override
    protected TrunkPlacerType<?> type() {

        return ModTrunkPlacerTypes.CHEESE_TRUNK_PLACER.get();

    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader pLevel, BiConsumer<BlockPos, BlockState> pBlockSetter,
                                                            RandomSource pRandom, int pFreeTreeHeight, BlockPos pPos, TreeConfiguration pConfig) {

        setDirtAt(pLevel, pBlockSetter, pRandom, pPos.below(), pConfig); // to set block below sapling to dirt [REQUIRED]

        for (int i = 0; i < pFreeTreeHeight; i++) {

            this.placeLog(pLevel, pBlockSetter, pRandom, pPos.above(i), pConfig);

            // idea: have multiple hardcoded tree presets that you randomly pick from if you have advanced branches
            /*
            simple branches code:
            if (i % 2 == 0 && pRandom.nextBoolean())
                for (int j = 0; j < 4; j++)
                    pBlockSetter.accept(pPos.above(i).relative(Direction.NORTH, j),
                            (BlockState) Function.identity().apply(pConfig.trunkProvider.getState(pRandom, pPos)
                                    .setValue(RotatedPillarBlock.AXIS, Direction.Axis.Z)));

            if (i % 2 == 0 && pRandom.nextBoolean())
                for (int j = 0; j < 4; j++)
                    pBlockSetter.accept(pPos.above(i).relative(Direction.EAST, j),
                            (BlockState) Function.identity().apply(pConfig.trunkProvider.getState(pRandom, pPos)
                                    .setValue(RotatedPillarBlock.AXIS, Direction.Axis.X)));

            if (i % 2 == 0 && pRandom.nextBoolean())
                for (int j = 0; j < 4; j++)
                    pBlockSetter.accept(pPos.above(i).relative(Direction.SOUTH, j),
                            (BlockState) Function.identity().apply(pConfig.trunkProvider.getState(pRandom, pPos)
                                    .setValue(RotatedPillarBlock.AXIS, Direction.Axis.Z)));

            if (i % 2 == 0 && pRandom.nextBoolean())
                for (int j = 0; j < 4; j++)
                    pBlockSetter.accept(pPos.above(i).relative(Direction.WEST, j),
                            (BlockState) Function.identity().apply(pConfig.trunkProvider.getState(pRandom, pPos)
                                    .setValue(RotatedPillarBlock.AXIS, Direction.Axis.X)));
            */
        }

        return ImmutableList.of(new FoliagePlacer.FoliageAttachment(pPos.above(pFreeTreeHeight), 0, false)); // list of where foliage placers will place something

    }
}
