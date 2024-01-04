package net.cheesestudios.dairymod.worldgen.tree;

import net.cheesestudios.dairymod.DairyMod;
import net.cheesestudios.dairymod.worldgen.tree.custom.CheeseTrunkPlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModTrunkPlacerTypes {

    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACER =
            DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, DairyMod.MOD_ID);

    public static final RegistryObject<TrunkPlacerType<CheeseTrunkPlacer>> CHEESE_TRUNK_PLACER =
            TRUNK_PLACER.register("cheese_trunk_placer", () -> new TrunkPlacerType<>(CheeseTrunkPlacer.CODEC));

    public static void register(IEventBus eventBus) {

        TRUNK_PLACER.register(eventBus);

    }
}
