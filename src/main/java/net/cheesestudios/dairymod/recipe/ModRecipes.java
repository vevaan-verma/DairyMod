package net.cheesestudios.dairymod.recipe;

import net.cheesestudios.dairymod.DairyMod;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {

    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, DairyMod.MOD_ID);

    public static final RegistryObject<RecipeSerializer<DairyCondensingRecipe>> DAIRY_CONDENSING_SERIALIZER =
            SERIALIZERS.register("dairy_condensing", () -> DairyCondensingRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {

        SERIALIZERS.register(eventBus);

    }
}
