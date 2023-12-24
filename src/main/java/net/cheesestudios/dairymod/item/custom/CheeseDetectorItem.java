package net.cheesestudios.dairymod.item.custom;

import net.cheesestudios.dairymod.block.ModBlocks;
import net.cheesestudios.dairymod.util.ModTags;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;

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

                if (isDetectable(state)) {

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

    private boolean isDetectable(BlockState state) {

        return state.is(ModTags.Blocks.CHEESE_DETECTOR_DETECTABLES);

    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {

        pTooltipComponents.add(Component.translatable("tooltip.dairymod.cheese_detector.tooltip"));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);

    }
}
