package net.cheesestudios.dairymod.item;

import net.cheesestudios.dairymod.DairyMod;
import net.cheesestudios.dairymod.util.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ModToolTiers {

    public static final Tier CHEESE = TierSortingRegistry.registerTier(
            new ForgeTier(1, 50, 7f, 1f, 16, ModTags.Blocks.NEEDS_CHEESE_TOOL, () -> Ingredient.of(ModItems.CHEESE.get())),
            new ResourceLocation(DairyMod.MOD_ID, "cheese"), List.of(Tiers.WOOD), List.of(Tiers.STONE));

}
