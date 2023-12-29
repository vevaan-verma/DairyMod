package net.cheesestudios.dairymod.datagen;

import net.cheesestudios.dairymod.DairyMod;
import net.cheesestudios.dairymod.block.ModBlocks;
import net.cheesestudios.dairymod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    private static final List<ItemLike> SALT_SMELTABLES = List.of(ModItems.SALT.get(),
            ModBlocks.SALT_ORE.get());

    public ModRecipeProvider(PackOutput pOutput) {

        super(pOutput);

    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pConsumer) {

        oreSmelting(pConsumer, SALT_SMELTABLES, RecipeCategory.MISC, ModItems.SALT.get(), 0.25f, 200, "salt");
        oreBlasting(pConsumer, SALT_SMELTABLES, RecipeCategory.MISC, ModItems.SALT.get(), 0.25f, 100, "salt");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SALT_BLOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.SALT.get())
                .unlockedBy(getHasName(ModItems.SALT.get()), has(ModItems.SALT.get()))
                .save(pConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.CHEESE_BLOCK.get(), 1)
                .requires(ModItems.CHEESE.get())
                .unlockedBy(getHasName(ModItems.CHEESE.get()), has(ModItems.CHEESE.get()))
                .save(pConsumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.CHEESE.get(), 4)
                .requires(ModBlocks.CHEESE_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.CHEESE_BLOCK.get()), has(ModBlocks.CHEESE_BLOCK.get()))
                .save(pConsumer);

    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {

        oreCooking(pConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_smelting");

    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {

        oreCooking(pConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");

    }

    protected static void oreCooking(Consumer<FinishedRecipe> pConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pSuffix) {

        Iterator var9 = pIngredients.iterator();

        while (var9.hasNext()) {

            ItemLike itemlike = (ItemLike) var9.next();
            SimpleCookingRecipeBuilder.generic(Ingredient.of(new ItemLike[]{itemlike}), pCategory, pResult, pExperience, pCookingTime, pSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pConsumer, DairyMod.MOD_ID + ":" + getItemName(pResult) + pSuffix + "_" + getItemName(itemlike)); // add mod id

        }
    }
}
