package net.cheesestudios.dairymod.worldgen;

import net.cheesestudios.dairymod.DairyMod;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBiomeModifiers {

    public static final ResourceKey<BiomeModifier> ADD_SALT_ORE = registerKey("add_salt_ore");
    public static final ResourceKey<BiomeModifier> ADD_NETHER_SALT_ORE = registerKey("add_nether_salt_ore");
    public static final ResourceKey<BiomeModifier> ADD_END_SALT_ORE = registerKey("add_end_salt_ore");

    private static ResourceKey<BiomeModifier> registerKey(String name) {

        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(DairyMod.MOD_ID, name));

    }
}
