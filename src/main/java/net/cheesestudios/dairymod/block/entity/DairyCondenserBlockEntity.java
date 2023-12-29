package net.cheesestudios.dairymod.block.entity;

import net.cheesestudios.dairymod.recipe.DairyCondensingRecipe;
import net.cheesestudios.dairymod.screen.DairyCondenserMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class DairyCondenserBlockEntity extends BlockEntity implements MenuProvider {

    private static final int INPUT_SLOT1 = 0;
    private static final int INPUT_SLOT2 = 1;
    private static final int OUTPUT_SLOT = 2;

    private final ItemStackHandler itemHandler = new ItemStackHandler(3); // amount of slots in gui

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 78;

    public DairyCondenserBlockEntity(BlockPos pPos, BlockState pBlockState) {

        super(ModBlockEntities.DAIRY_CONDENSER_BLOCK_ENTITY.get(), pPos, pBlockState);
        this.data = new ContainerData() { // for the arrow progress
            @Override
            public int get(int pIndex) {

                return switch (pIndex) {

                    case 0 -> DairyCondenserBlockEntity.this.progress;
                    case 1 -> DairyCondenserBlockEntity.this.maxProgress;
                    default -> 0;

                };
            }

            @Override
            public void set(int pIndex, int pValue) {

                switch (pIndex) {

                    case 0 -> DairyCondenserBlockEntity.this.progress = pValue;
                    case 1 -> DairyCondenserBlockEntity.this.maxProgress = pValue;

                }
            }

            @Override
            public int getCount() {

                return 2;

            }
        };
    }

    // allows other mods and the game to know that this block has a certain capability
    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {

        if (cap == ForgeCapabilities.ITEM_HANDLER)
            return lazyItemHandler.cast();

        return super.getCapability(cap, side);

    }

    @Override
    public void onLoad() {

        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> this.itemHandler);

    }

    @Override
    public void invalidateCaps() {

        super.invalidateCaps();
        lazyItemHandler.invalidate();

    }

    public void drops() {

        SimpleContainer inventory = new SimpleContainer(this.itemHandler.getSlots());

        for (int i = 0; i < this.itemHandler.getSlots(); i++)
            inventory.setItem(i, this.itemHandler.getStackInSlot(i));

        Containers.dropContents(this.level, this.worldPosition, inventory);

    }

    @Override

    public Component getDisplayName() {

        return Component.translatable("block.dairymod.dairy_condenser");

    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {

        return new DairyCondenserMenu(pContainerId, pPlayerInventory, this, this.data);

    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {

        // saves inventory items & progress (serialization)
        pTag.put("inventory", this.itemHandler.serializeNBT());
        pTag.putInt("dairy_condenser.progress", progress);

        super.saveAdditional(pTag);

    }

    @Override
    public void load(CompoundTag pTag) {

        super.load(pTag);

        // loads inventory items & progress (deserialization)
        this.itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("dairy_condenser.progress");

    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {

        if (hasRecipe()) {

            incrementCraftingProgress();
            setChanged(pLevel, pPos, pState);

            if (progressFinished()) {

                craftItem();
                resetProgress();

            }
        } else {

            resetProgress();

        }
    }

    private boolean hasRecipe() {

        Optional<DairyCondensingRecipe> recipe = getCurrentRecipe();

        if (recipe.isEmpty())
            return false;

        ItemStack result = recipe.get().getResultItem(null);

        return canInsertItemInOutput(result.getItem()) && canInsertResultInOutput(result.getCount()); // make sure item can be added to output slot

    }

    private Optional<DairyCondensingRecipe> getCurrentRecipe() {

        SimpleContainer inventory = new SimpleContainer(this.itemHandler.getSlots());

        for (int i = 0; i < this.itemHandler.getSlots(); i++)
            inventory.setItem(i, this.itemHandler.getStackInSlot(i));

        return this.level.getRecipeManager().getRecipeFor(DairyCondensingRecipe.Type.INSTANCE, inventory, level);

    }

    private boolean canInsertItemInOutput(Item item) {

        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() // output slot is empty
                || this.itemHandler.getStackInSlot(OUTPUT_SLOT).is(item); // output slot contains same item already

    }

    private boolean canInsertResultInOutput(int count) {

        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + count <= this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize(); // slot still has space for item stack

    }

    private void incrementCraftingProgress() {

        progress++;

    }

    private boolean progressFinished() {

        return progress >= maxProgress;

    }

    private void craftItem() {

        Optional<DairyCondensingRecipe> recipe = getCurrentRecipe();
        ItemStack result = recipe.get().getResultItem(null);

        this.itemHandler.extractItem(INPUT_SLOT1, 1, false);
        this.itemHandler.extractItem(INPUT_SLOT2, 1, false);

        this.itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(result.getItem(),
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + result.getCount())); // add item stack to output

    }

    private void resetProgress() {

        progress = 0;

    }
}
