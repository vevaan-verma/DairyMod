package net.cheesestudios.dairymod.loot;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

public class AddSuspiciousBlockItemModifier extends LootModifier {

    public static final Supplier<Codec<AddSuspiciousBlockItemModifier>> CODEC = Suppliers.memoize(()
            -> RecordCodecBuilder.create(inst -> codecStart(inst).and(ForgeRegistries.ITEMS.getCodec()
            .fieldOf("item").forGetter(m -> m.item)).apply(inst, AddSuspiciousBlockItemModifier::new)));
    private final Item item;

    public AddSuspiciousBlockItemModifier(LootItemCondition[] conditionsIn, Item item) {

        super(conditionsIn);
        this.item = item;

    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext lootContext) {

        // allows loot item conditions to be added to the global loot modifier
        for (LootItemCondition condition : this.conditions)
            if (!condition.test(lootContext))
                return generatedLoot;

        if (lootContext.getRandom().nextFloat() < 0.1f) { // chance = 1 / total entries including this one

            generatedLoot.clear(); // MUST CLEAR LOOT BEFORE ADDING
            generatedLoot.add(new ItemStack(this.item));

        }

        return generatedLoot;

    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {

        return CODEC.get();

    }
}
