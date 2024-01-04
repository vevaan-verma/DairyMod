package net.cheesestudios.dairymod.worldgen.tree;

import net.cheesestudios.dairymod.DairyMod;
import net.cheesestudios.dairymod.worldgen.tree.custom.CheeseFoliagePlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModFoliagePlacers {

    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS =
            DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, DairyMod.MOD_ID);

    public static final RegistryObject<FoliagePlacerType<CheeseFoliagePlacer>> CHEESE_FOLIAGE_PLACER =
            FOLIAGE_PLACERS.register("cheese_foliage_placer", () -> new FoliagePlacerType<>(CheeseFoliagePlacer.CODEC));

    public static void register(IEventBus eventBus) {

        FOLIAGE_PLACERS.register(eventBus);

    }
}
