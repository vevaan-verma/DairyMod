package net.cheesestudios.dairymod.block.custom;

import net.cheesestudios.dairymod.block.entity.DairyCondenserBlockEntity;
import net.cheesestudios.dairymod.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public class DairyCondenserBlock extends BaseEntityBlock {

    // IMPORTANT: anything in Block class that is deprecated can be overridden, but not called

    public static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 14, 16); // define custom dimensions here

    public DairyCondenserBlock(Properties pProperties) {

        super(pProperties);

    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {

        return SHAPE;

    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {

        return RenderShape.MODEL; // block entity will be invisible if not overridden

    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {

        if (pState.getBlock() != pNewState.getBlock()) {

            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);

            if (blockEntity instanceof DairyCondenserBlockEntity)
                ((DairyCondenserBlockEntity) blockEntity).drops();

        }

        super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);

    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {

        if (!pLevel.isClientSide()) {

            BlockEntity entity = pLevel.getBlockEntity(pPos);

            if (entity instanceof DairyCondenserBlockEntity)
                NetworkHooks.openScreen(((ServerPlayer) pPlayer), (DairyCondenserBlockEntity) entity, pPos);
            else
                throw new IllegalStateException("Container provider is missing!");

        }

        return InteractionResult.sidedSuccess(pLevel.isClientSide());

    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {

        return new DairyCondenserBlockEntity(pPos, pState);

    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {

        if (pLevel.isClientSide())
            return null;

        return createTickerHelper(pBlockEntityType, ModBlockEntities.DAIRY_CONDENSER_BLOCK_ENTITY.get(),
                (pLevel1, pPos, pState1, pBlockEntity) -> pBlockEntity.tick(pLevel1, pPos, pState1));

    }
}
