package net.cheesestudios.dairymod.item.custom;

import com.google.common.collect.ImmutableMap;
import net.cheesestudios.dairymod.item.ModArmorMaterials;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Map;

public class ModArmorItem extends ArmorItem {

    private static final Map<ArmorMaterial, MobEffectInstance> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<ArmorMaterial, MobEffectInstance>())
                    .put(ModArmorMaterials.CHEESE, new MobEffectInstance(MobEffects.SATURATION, 100, 1,
                            false, false, true)).build(); // saturation while full cheese armor is equipped

    // pVisible: particles shown
    // pShowIcon: inventory icon shown

    public ModArmorItem(ArmorMaterial pMaterial, Type pType, Properties pProperties) {

        super(pMaterial, pType, pProperties);

    }

    @Override
    public void onInventoryTick(ItemStack stack, Level level, Player player, int slotIndex, int selectedIndex) {

        if (!level.isClientSide())
            if (hasFullSuitOfArmorOn(player))
                evaluateArmorEffects(player);

    }

    private boolean hasFullSuitOfArmorOn(Player player) {

        ItemStack boots = player.getInventory().getArmor(0);
        ItemStack leggings = player.getInventory().getArmor(1);
        ItemStack chestplate = player.getInventory().getArmor(2);
        ItemStack helmet = player.getInventory().getArmor(3);

        return !helmet.isEmpty() && !chestplate.isEmpty()
                && !leggings.isEmpty() && !boots.isEmpty();

    }

    private void evaluateArmorEffects(Player player) {

        // if not done, game will crash if player uses elytra or non-armor item
        for (Map.Entry<ArmorMaterial, MobEffectInstance> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {

            ArmorMaterial mapArmorMaterial = entry.getKey();
            MobEffectInstance mapStatusEffect = entry.getValue();

            if (hasCorrectArmorOn(mapArmorMaterial, player))
                addStatusEffectForMaterial(player, mapArmorMaterial, mapStatusEffect);

        }
    }

    private boolean hasCorrectArmorOn(ArmorMaterial material, Player player) {

        for (ItemStack armorStack : player.getInventory().armor)
            if (!(armorStack.getItem() instanceof ArmorItem))
                return false;

        ArmorItem boots = ((ArmorItem) player.getInventory().getArmor(0).getItem());
        ArmorItem leggings = ((ArmorItem) player.getInventory().getArmor(1).getItem());
        ArmorItem chestplate = ((ArmorItem) player.getInventory().getArmor(2).getItem());
        ArmorItem helmet = ((ArmorItem) player.getInventory().getArmor(3).getItem());

        return helmet.getMaterial() == material && chestplate.getMaterial() == material &&
                leggings.getMaterial() == material && boots.getMaterial() == material;

    }

    private void addStatusEffectForMaterial(Player player, ArmorMaterial mapArmorMaterial,
                                            MobEffectInstance mapStatusEffect) {

        if (hasCorrectArmorOn(mapArmorMaterial, player) && !player.hasEffect(mapStatusEffect.getEffect())) // make sure player doesn't have effect already
            player.addEffect(new MobEffectInstance(mapStatusEffect)); // create new instance everytime

    }
}
