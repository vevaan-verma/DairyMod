package net.cheesestudios.dairymod.item;

import net.minecraft.world.food.FoodProperties;

public class ModFoods {

    // foods
    public static final FoodProperties CHEESE = new FoodProperties.Builder().nutrition(4)
            .saturationMod(1.8f).build();
    public static final FoodProperties ICE_CREAM = new FoodProperties.Builder().nutrition(8)
            .saturationMod(3f).build();
    public static final FoodProperties NACHOS = new FoodProperties.Builder().nutrition(12)
            .saturationMod(6f).build();

    // snakcs
    public static final FoodProperties CREEPER_SNAKC = new FoodProperties.Builder().nutrition(16)
            .saturationMod(8f).build();
    public static final FoodProperties ENDERMAN_SNAKC = new FoodProperties.Builder().nutrition(16)
            .saturationMod(8f).build();
    public static final FoodProperties PIGLIN_SNAKC = new FoodProperties.Builder().nutrition(16)
            .saturationMod(8f).build();
    public static final FoodProperties SKELETON_SNAKC = new FoodProperties.Builder().nutrition(16)
            .saturationMod(8f).build();
    public static final FoodProperties WITHER_SKELETON_SNAKC = new FoodProperties.Builder().nutrition(16)
            .saturationMod(8f).build();

}
