package cn.crtlprototypestudios.precisemanufacturing.foundation.block.decomponentalizer;

import cn.crtlprototypestudios.precisemanufacturing.foundation.ModBlockEntities;
import cn.crtlprototypestudios.precisemanufacturing.foundation.recipe.decomponentalizing.DecomponentalizingRecipe;
import cn.crtlprototypestudios.precisemanufacturing.foundation.recipe.decomponentalizing.DecomponentalizingRecipeType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Objects;

public class DecomponentalizerBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler;
    private final LazyOptional<IItemHandler> itemHandlerCap;
    private int processingTime;
    private int totalProcessingTime;
    private DecomponentalizingRecipe currentRecipe;

    public DecomponentalizerBlockEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
        super(ModBlockEntities.DECOMPONENTALIZER.get(), pos, state);
        itemHandler = createHandler();
        itemHandlerCap = LazyOptional.of(() -> itemHandler);
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(4) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }
        };
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(level, worldPosition, inventory);
    }

    @Override
    public Component getDisplayName() {
        return new TranslatableComponent("block.prma.container.decomponentalizer");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new DecomponentalizerContainerMenu(id, inventory, this);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        itemHandler.deserializeNBT(tag.getCompound("Inventory"));
        processingTime = tag.getInt("ProcessingTime");
        totalProcessingTime = tag.getInt("TotalProcessingTime");
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("Inventory", itemHandler.serializeNBT());
        tag.putInt("ProcessingTime", processingTime);
        tag.putInt("TotalProcessingTime", totalProcessingTime);
    }

    public void tick() {
        if (level == null || level.isClientSide) {
            return;
        }

        if (hasRecipe()) {
            if (processingTime < totalProcessingTime) {
                processingTime++;
                setChanged();
            } else {
                craftItem();
                processingTime = 0;
                setChanged();
            }
        } else {
            processingTime = 0;
            setChanged();
        }
    }

    private boolean hasRecipe() {
        if (itemHandler.getStackInSlot(0).isEmpty()) {
            return false;
        }

        Level level = Objects.requireNonNull(this.level);
        currentRecipe = level.getRecipeManager().getRecipeFor(DecomponentalizingRecipeType.INSTANCE, new SimpleContainer(itemHandler.getStackInSlot(0)), level).orElse(null);

        return currentRecipe != null && canOutput(currentRecipe.getResultItem());
    }

    private boolean canOutput(ItemStack output) {
        return itemHandler.getStackInSlot(3).getItem() == output.getItem() || itemHandler.getStackInSlot(3).isEmpty();
    }

    private void craftItem() {
        if (currentRecipe != null && canOutput(currentRecipe.getResultItem())) {
            itemHandler.extractItem(0, 1, false);
            itemHandler.setStackInSlot(3, new ItemStack(currentRecipe.getResultItem().getItem(), itemHandler.getStackInSlot(3).getCount() + 1));
        }
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return itemHandlerCap.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        itemHandlerCap.invalidate();
    }

    public int getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(int processingTime) {this.processingTime = processingTime;}


    public int getTotalProcessingTime() {
        return totalProcessingTime;
    }

    public void setTotalProcessingTime(int totalProcessingTime) {this.totalProcessingTime = totalProcessingTime;}

    public ItemStackHandler getItemHandler() {
        return itemHandler;
    }
}
