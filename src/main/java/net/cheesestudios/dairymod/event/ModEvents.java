package net.cheesestudios.dairymod.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.cheesestudios.dairymod.DairyMod;
import net.cheesestudios.dairymod.block.ModBlocks;
import net.cheesestudios.dairymod.item.ModItems;
import net.cheesestudios.dairymod.villager.ModVillagers;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = DairyMod.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {

        /*
        GUIDE:
            .get(index): index is villager level
            first itemstack: item to be traded
            second itemstack: item to trade for
            pMaxUses: maximum trades
            pXp: villager xp gained from trade
            pPriceMultiplier: multiplier based on demand
            more trades = less likely for one trade to show up
        */

        if (event.getType() == ModVillagers.CHEESE_LORD.get()) {

            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            // level 1
            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(

                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(ModItems.CHEESE_SEEDS.get(), 12),
                    12, 3, 0.02f

            ));

            // level 2
            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(

                    new ItemStack(Items.EMERALD, 2),
                    new ItemStack(ModItems.CHEESE.get(), 8),
                    15, 6, 0.03f

            ));

            // level 3
            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(

                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(ModBlocks.CHEESE_BLOCK.get(), 2),
                    10, 8, 0.035f

            ));

            // level 4
            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(

                    new ItemStack(Items.EMERALD, 4),
                    new ItemStack(ModItems.ICE_CREAM.get(), 3),
                    8, 10, 0.035f

            ));

            // level 5
            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(

                    new ItemStack(Items.EMERALD, 4),
                    new ItemStack(ModItems.NACHOS.get(), 3),
                    10, 12, 0.02f

            ));
        }

        if (event.getType() == VillagerProfession.ARMORER) {

            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            // level 1
            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(

                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(ModItems.CHEESE_HOE.get(), 1),
                    3, 3, 0.02f

            ));

            // level 2
            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(

                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(ModItems.CHEESE_SWORD.get(), 1),
                    2, 6, 0.035f

            ));

            // level 3
            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(

                    new ItemStack(Items.EMERALD, 2),
                    new ItemStack(ModItems.CHEESE_BOOTS.get(), 1),
                    3, 8, 0.03f

            ));

            // level 4
            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(

                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(ModItems.CHEESE_CHESTPLATE.get(), 1),
                    4, 10, 0.03f

            ));

            // level 5
            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(

                    new ItemStack(Items.EMERALD, 6),
                    EnchantedBookItem.createForEnchantment(new EnchantmentInstance(Enchantments.MENDING, 1)),
                    3, 12, 0.04f

            ));
        }
    }

    @SubscribeEvent
    public static void addCustomWandererTrades(WandererTradesEvent event) {

        List<VillagerTrades.ItemListing> genericTrades = event.getGenericTrades();
        List<VillagerTrades.ItemListing> rareTrades = event.getRareTrades();

        /*
        GUIDE:
            xp doesn't impact wandering traders
            more trades = less likely for one trade to show up
        */

        // generic trades
        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(

                new ItemStack(Items.EMERALD, 2),
                new ItemStack(ModItems.CHEESE.get(), 12),
                8, 0, 0.2f

        ));

        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(

                new ItemStack(Items.EMERALD, 2),
                new ItemStack(ModItems.CHEESE_AXE.get(), 1),
                3, 0, 0.15f

        ));

        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(

                new ItemStack(Items.EMERALD, 2),
                new ItemStack(ModItems.CHEESE_HELMET.get(), 1),
                2, 0, 0.25f

        ));

        rareTrades.add((pTrader, pRandom) -> new MerchantOffer(

                new ItemStack(Items.EMERALD, 1),
                new ItemStack(ModItems.CHEESE.get(), 64),
                2, 0, 0.2f

        ));

        rareTrades.add((pTrader, pRandom) -> new MerchantOffer(

                new ItemStack(Items.EMERALD, 2),
                new ItemStack(ModItems.NACHOS.get(), 12),
                4, 0, 0.25f

        ));

        rareTrades.add((pTrader, pRandom) -> new MerchantOffer(

                new ItemStack(Items.EMERALD, 8),
                EnchantedBookItem.createForEnchantment(new EnchantmentInstance(Enchantments.SHARPNESS, 5)),
                1, 0, 0f

        ));
    }
}
