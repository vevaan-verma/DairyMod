package net.cheesestudios.dairymod.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.cheesestudios.dairymod.DairyMod;
import net.cheesestudios.dairymod.recipe.DairyCondensingRecipe;
import net.cheesestudios.dairymod.screen.DairyCondenserScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;

@JeiPlugin
public class JEIDairyModPlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {

        return new ResourceLocation(DairyMod.MOD_ID, "jei_plugin");

    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {

        registration.addRecipeCategories(new DairyCondenserCategory(registration.getJeiHelpers().getGuiHelper()));

    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {

        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<DairyCondensingRecipe> condensingRecipes = recipeManager.getAllRecipesFor(DairyCondensingRecipe.Type.INSTANCE);
        registration.addRecipes(DairyCondenserCategory.DAIRY_CONDENSING_TYPE, condensingRecipes); // links category to recipes

    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {

        registration.addRecipeClickArea(DairyCondenserScreen.class, 81, 34, 15, 22,
                DairyCondenserCategory.DAIRY_CONDENSING_TYPE);

    }
}
