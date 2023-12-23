package net.cheesestudios.dairymod.item;

import net.cheesestudios.dairymod.DairyMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DairyMod.MOD_ID);

    // mod items
    public static final RegistryObject<Item> CHEESE = ITEMS.register("cheese",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ICE_CREAM = ITEMS.register("ice_cream",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> NACHOS = ITEMS.register("nachos",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {

        ITEMS.register(eventBus);

    }
}
