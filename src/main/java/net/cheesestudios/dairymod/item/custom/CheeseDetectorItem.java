package net.cheesestudios.dairymod.item.custom;

import net.cheesestudios.dairymod.block.ModBlocks;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class CheeseDetectorItem extends Item {

    public CheeseDetectorItem(Properties pProperties) {

        super(pProperties);

    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {

        if (!pContext.getLevel().isClientSide()) {

            BlockPos clickedPos = pContext.getClickedPos();
            Player player = pContext.getPlayer();

            boolean blockFound = false;

            for (int i = 0; i <= clickedPos.getY() + 64; i++) { // add 64 because world goes to -64 y

                BlockState state = pContext.getLevel().getBlockState(clickedPos.below(i));

                if (isCheeseBlock(state)) {

                    outputBlockCoordinates(clickedPos.below(i), player, state.getBlock());
                    blockFound = true;
                    break;

                }
            }

            if (!blockFound) {

                player.sendSystemMessage(Component.literal(I18n.get(ModBlocks.CHEESE_BLOCK.get().getDescriptionId()) + " not found!"));

            }
        }

        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(), player -> player.broadcastBreakEvent(player.getUsedItemHand()));

        return InteractionResult.SUCCESS; // shows animation on interaction

    }

    private void outputBlockCoordinates(BlockPos blockPos, Player player, Block block) {

        player.sendSystemMessage(Component.literal(I18n.get(block.getDescriptionId()) + " found!"));

    }

    private boolean isCheeseBlock(BlockState state) {

        return state.is(ModBlocks.CHEESE_BLOCK.get());

    }
}
