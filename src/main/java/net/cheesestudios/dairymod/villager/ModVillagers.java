package net.cheesestudios.dairymod.villager;

import com.google.common.collect.ImmutableSet;
import net.cheesestudios.dairymod.DairyMod;
import net.cheesestudios.dairymod.block.ModBlocks;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModVillagers {

    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(ForgeRegistries.POI_TYPES, DairyMod.MOD_ID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS =
            DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, DairyMod.MOD_ID);

    /*
    GUIDE:
        new PoiType(ImmutableSet.copyOf(BLOCK.get().getStateDefinition().getPossibleStates()): BLOCK is villager workstation
        maxTickets: number of villagers that can take the job from the workstation
        validRange: max range of villager to take the job
     */

    public static final RegistryObject<PoiType> CHEESE_POI = POI_TYPES.register("cheese_poi",
            () -> new PoiType(ImmutableSet.copyOf(ModBlocks.CHEESE_BLOCK.get().getStateDefinition().getPossibleStates()), // get all blockstates to make sure any state of a block can act as a workstation
                    1, 1));

    public static final RegistryObject<VillagerProfession> CHEESE_LORD =
            VILLAGER_PROFESSIONS.register("cheese_lord",
                    () -> new VillagerProfession("cheese_lord", holder -> holder.get() == CHEESE_POI.get(),
                            holder -> holder.get() == CHEESE_POI.get(),
                            ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_FARMER));

    public static void register(IEventBus eventBus) {

        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);

    }
}
