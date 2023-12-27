package net.cheesestudios.dairymod.item;

import net.cheesestudios.dairymod.DairyMod;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum ModArmorMaterials implements ArmorMaterial {
    
    CHEESE("cheese", 1, new int[]{1, 1, 1, 1}, 6,
            SoundEvents.ARMOR_EQUIP_TURTLE, 1f, 0f, () -> Ingredient.of(ModItems.CHEESE.get()));

    private final String name;
    private final int durabilityMultiplier;
    private final int[] protectionAmount;
    private final int enchantmentValue;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;

    private static final int[] BASE_DURABILITY = {11, 16, 15, 13};

    ModArmorMaterials(String name, int durabilityMultiplier, int[] protectionAmount, int enchantmentValue, SoundEvent equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {

        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmount = protectionAmount;
        this.enchantmentValue = enchantmentValue;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;

    }

    @Override
    public int getDurabilityForType(ArmorItem.Type type) {

        return BASE_DURABILITY[type.ordinal()] * this.durabilityMultiplier;

    }

    @Override
    public int getDefenseForType(ArmorItem.Type type) {

        return this.protectionAmount[type.ordinal()];

    }

    @Override
    public int getEnchantmentValue() {

        return this.enchantmentValue;

    }

    @Override
    public SoundEvent getEquipSound() {

        return this.equipSound;

    }

    @Override
    public Ingredient getRepairIngredient() {

        return this.repairIngredient.get();

    }

    @Override
    public String getName() {

        return DairyMod.MOD_ID + ":" + this.name;

    }

    @Override
    public float getToughness() {

        return this.toughness;

    }

    @Override
    public float getKnockbackResistance() {

        return this.knockbackResistance;

    }
}
