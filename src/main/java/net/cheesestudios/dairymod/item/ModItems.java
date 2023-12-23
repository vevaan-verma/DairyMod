package net.cheesestudios.dairymod.item;

import net.cheesestudios.dairymod.DairyMod;
import net.cheesestudios.dairymod.item.custom.CheeseDetectorItem;
import net.cheesestudios.dairymod.item.custom.FuelItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DairyMod.MOD_ID);

    // items
    public static final RegistryObject<Item> CONCENTRATED_CHEESE = ITEMS.register("concentrated_cheese",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MILK_SOLIDS = ITEMS.register("milk_solids",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SALT = ITEMS.register("salt",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PINECONE = ITEMS.register("pinecone",
            () -> new FuelItem(new Item.Properties(), 120));

    // advanced items
    public static final RegistryObject<Item> CHEESE_DETECTOR = ITEMS.register("cheese_detector",
            () -> new CheeseDetectorItem(new Item.Properties().durability(100)));

    // foods
    public static final RegistryObject<Item> CHEESE = ITEMS.register("cheese",
            () -> new Item(new Item.Properties().food(ModFoods.CHEESE)));
    public static final RegistryObject<Item> ICE_CREAM = ITEMS.register("ice_cream",
            () -> new Item(new Item.Properties().food(ModFoods.ICE_CREAM)));
    public static final RegistryObject<Item> NACHOS = ITEMS.register("nachos",
            () -> new Item(new Item.Properties().food(ModFoods.NACHOS)));

    public static void register(IEventBus eventBus) {

        ITEMS.register(eventBus);

    }
}
