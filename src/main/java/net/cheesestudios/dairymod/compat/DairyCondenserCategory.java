package net.cheesestudios.dairymod.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.cheesestudios.dairymod.DairyMod;
import net.cheesestudios.dairymod.block.ModBlocks;
import net.cheesestudios.dairymod.recipe.DairyCondensingRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class DairyCondenserCategory implements IRecipeCategory<DairyCondensingRecipe> {

    public static final ResourceLocation UID = new ResourceLocation(DairyMod.MOD_ID, "dairy_condensing");
    public static final ResourceLocation TEXTURE = new ResourceLocation(DairyMod.MOD_ID,
            "textures/gui/dairy_condenser_gui.png");

    public static final RecipeType<DairyCondensingRecipe> DAIRY_CONDENSING_TYPE =
            new RecipeType<>(UID, DairyCondensingRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public DairyCondenserCategory(IGuiHelper helper) {

        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.DAIRY_CONDENSER.get()));

    }

    @Override
    public RecipeType<DairyCondensingRecipe> getRecipeType() {

        return DAIRY_CONDENSING_TYPE;

    }

    @Override
    public Component getTitle() {

        return Component.translatable("block.dairymod.dairy_condenser");

    }

    @Override
    public IDrawable getBackground() {

        return this.background;

    }

    @Override
    public IDrawable getIcon() {

        return this.icon;

    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, DairyCondensingRecipe recipe, IFocusGroup focuses) {

        builder.addSlot(RecipeIngredientRole.INPUT, 67, 13).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 94, 13).addIngredients(recipe.getIngredients().get(1));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 80, 61).addItemStack(recipe.getResultItem(null));

    }
}
