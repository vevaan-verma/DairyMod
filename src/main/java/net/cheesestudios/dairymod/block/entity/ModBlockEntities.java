package net.cheesestudios.dairymod.block.entity;

import net.cheesestudios.dairymod.DairyMod;
import net.cheesestudios.dairymod.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, DairyMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<DairyCondenserBlockEntity>> DAIRY_CONDENSER_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("dairy_condenser_block_entity", () ->
                    BlockEntityType.Builder.of(DairyCondenserBlockEntity::new,
                            ModBlocks.DAIRY_CONDENSER.get()).build(null));

    public static final RegistryObject<BlockEntityType<ModSignBlockEntity>> MOD_SIGN_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("mod_sign_block_entity", () ->
                    BlockEntityType.Builder.of(ModSignBlockEntity::new,
                            ModBlocks.CHEESE_SIGN.get(), ModBlocks.CHEESE_WALL_SIGN.get()).build(null));
    public static final RegistryObject<BlockEntityType<ModHangingSignBlockEntity>> MOD_HANGING_SIGN_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("mod_hanging_sign_block_entity", () ->
                    BlockEntityType.Builder.of(ModHangingSignBlockEntity::new,
                            ModBlocks.CHEESE_HANGING_SIGN.get(), ModBlocks.CHEESE_WALL_HANGING_SIGN.get()).build(null));

    public static void register(IEventBus eventBus) {

        BLOCK_ENTITIES.register(eventBus);

    }
}
