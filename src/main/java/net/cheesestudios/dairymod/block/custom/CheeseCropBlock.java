package net.cheesestudios.dairymod.block.custom;

import net.cheesestudios.dairymod.item.ModItems;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class CheeseCropBlock extends CropBlock {

    public static final int MAX_AGE = 3;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3; // IntegerProperty is specific to the blockstate

    public CheeseCropBlock(Properties pProperties) {

        super(pProperties);

    }

    @Override
    protected ItemLike getBaseSeedId() {

        return ModItems.CHEESE_SEEDS.get();

    }

    @Override
    public IntegerProperty getAgeProperty() {

        return AGE;

    }

    @Override
    public int getMaxAge() {

        return MAX_AGE;

    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {

        pBuilder.add(AGE);

    }
}
