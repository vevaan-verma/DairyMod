package net.cheesestudios.dairymod.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.cheesestudios.dairymod.DairyMod;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class DairyCondensingRecipe implements Recipe<SimpleContainer> {

    private final NonNullList<Ingredient> inputItems;
    private final ItemStack output;
    private final ResourceLocation id;

    public DairyCondensingRecipe(NonNullList<Ingredient> inputItems, ItemStack output, ResourceLocation id) {

        this.inputItems = inputItems;
        this.output = output;
        this.id = id;

    }

    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {

        if (pLevel.isClientSide)
            return false;

        return (inputItems.get(0).test(pContainer.getItem(0)) &&
                inputItems.get(1).test(pContainer.getItem(1))) ||
                (inputItems.get(0).test(pContainer.getItem(1)) &&
                        inputItems.get(1).test(pContainer.getItem(0))); // important to know which item is which (match multiple input slots properly)

    }

    // VERY IMPORTANT FOR JEI COMPATIBILITY
    @Override
    public NonNullList<Ingredient> getIngredients() {

        return inputItems;

    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer, RegistryAccess pRegistryAccess) {

        return output.copy();

    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {

        return true;

    }

    @Override
    public ItemStack getResultItem(RegistryAccess pRegistryAccess) {

        return output.copy();

    }

    @Override
    public ResourceLocation getId() {

        return id;

    }

    @Override
    public RecipeSerializer<?> getSerializer() {

        return Serializer.INSTANCE;

    }

    @Override
    public RecipeType<?> getType() {

        return Type.INSTANCE;

    }

    public static class Type implements RecipeType<DairyCondensingRecipe> {

        public static final Type INSTANCE = new Type();
        public static final String ID = "dairy_condensing";

    }

    public static class Serializer implements RecipeSerializer<DairyCondensingRecipe> {

        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(DairyMod.MOD_ID, "dairy_condensing");

        @Override
        public DairyCondensingRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {

            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "output"));
            JsonArray ingredients = GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredients");

            NonNullList<Ingredient> inputs = NonNullList.withSize(2, Ingredient.EMPTY); // size should be block entity input size

            for (int i = 0; i < inputs.size(); i++)
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));

            return new DairyCondensingRecipe(inputs, output, pRecipeId);

        }

        // KEEP SAME FORMATTING
        @Override
        public @Nullable DairyCondensingRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {

            NonNullList<Ingredient> inputs = NonNullList.withSize(pBuffer.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++)
                inputs.set(i, Ingredient.fromNetwork(pBuffer));

            ItemStack output = pBuffer.readItem();

            return new DairyCondensingRecipe(inputs, output, pRecipeId);

        }

        // KEEP SAME FORMATTING
        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, DairyCondensingRecipe pRecipe) {

            pBuffer.writeInt(pRecipe.inputItems.size());

            for (Ingredient ingredient : pRecipe.getIngredients())
                ingredient.toNetwork(pBuffer);

            pBuffer.writeItemStack(pRecipe.getResultItem(null), false);

        }
    }
}
