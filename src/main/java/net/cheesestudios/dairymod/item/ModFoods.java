package net.cheesestudios.dairymod.item;

import net.minecraft.world.food.FoodProperties;

public class ModFoods {

    public static final FoodProperties CHEESE = new FoodProperties.Builder().nutrition(4)
            .saturationMod(1.8f).build();
    public static final FoodProperties ICE_CREAM = new FoodProperties.Builder().nutrition(8)
            .saturationMod(3f).build();
    public static final FoodProperties NACHOS = new FoodProperties.Builder().nutrition(12)
            .saturationMod(6f).build();

}
