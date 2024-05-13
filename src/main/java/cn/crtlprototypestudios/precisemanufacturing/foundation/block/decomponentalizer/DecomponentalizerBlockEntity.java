package cn.crtlprototypestudios.precisemanufacturing.foundation.block.decomponentalizer;

import cn.crtlprototypestudios.precisemanufacturing.Main;
import cn.crtlprototypestudios.precisemanufacturing.foundation.ModBlockEntities;
import cn.crtlprototypestudios.precisemanufacturing.foundation.gui.decomponentalizer.DecomponentalizerContainerMenu;
import cn.crtlprototypestudios.precisemanufacturing.foundation.recipe.decomponentalizing.DecomponentalizingRecipe;
import cn.crtlprototypestudios.precisemanufacturing.foundation.recipe.decomponentalizing.DecomponentalizingRecipeType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class DecomponentalizerBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler;
    private final LazyOptional<IItemHandler> itemHandlerCap;
    private int processingTime;
    private int totalProcessingTime;
    private int isProcessing = 0;
    private DecomponentalizingRecipe currentRecipe, selectedRecipe;
    private ContainerData data;

    public DecomponentalizerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DECOMPONENTALIZER.get(), pos, state);
        itemHandler = createHandler();
        itemHandlerCap = LazyOptional.of(() -> itemHandler);
        this.data = new ContainerData() {
            public int get(int index) {
                switch (index) {
                    case 0:
                        return DecomponentalizerBlockEntity.this.getProcessingTime();
                    case 1:
                        return DecomponentalizerBlockEntity.this.getTotalProcessingTime();
                    case 2:
                        return DecomponentalizerBlockEntity.this.isProcessing();
                    default:
                        return 0;
                }
            }

            public void set(int index, int value) {
                switch (index) {
                    case 0:
                        DecomponentalizerBlockEntity.this.setProcessingTime(value);
                        break;
                    case 1:
                        DecomponentalizerBlockEntity.this.setTotalProcessingTime(value);
                        break;
                    case 2:
                        DecomponentalizerBlockEntity.this.setProcessing(value);
                        break;
                }
            }

            public int getCount() {
                return 3;
            }
        };
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(4) {
            @Override
            protected void onContentsChanged(int slot) {
                if (slot == 2){
                    updateAvailableRecipes();
                }
                setChanged();
            }
        };
    }

    private void updateAvailableRecipes() {
        if (this.level != null && !this.level.isClientSide) {
//            // Notify the container menu about the updated recipes
//            ContainerHelper.updateAvailableRecipes(this, availableRecipes);
        }
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(level, worldPosition, inventory);
    }

    @Override
    public @NotNull Component getDisplayName() {
        return new TranslatableComponent("block.prma.container.decomponentalizer");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new DecomponentalizerContainerMenu(id, inventory, this, this.data);
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

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, DecomponentalizerBlockEntity pBlockEntity) {
        if (pLevel == null || pLevel.isClientSide) {
            return;
        }

        if (hasRecipe(pBlockEntity)) {
            if (pBlockEntity.getProcessingTime() < pBlockEntity.getTotalProcessingTime()) {
                pBlockEntity.setProcessingTime(pBlockEntity.getProcessingTime() + 1);
                setChanged(pLevel, pPos, pState);
            } else {
                pBlockEntity.craftItem(pBlockEntity);
                pBlockEntity.setProcessingTime(0);
                pBlockEntity.setCurrentRecipe(null);
                setChanged(pLevel, pPos, pState);
            }
        } else {
            pBlockEntity.setProcessingTime(0);
            setChanged(pLevel, pPos, pState);
        }
    }

    private static boolean hasRecipe(DecomponentalizerBlockEntity pBlockEntity) {
        if (pBlockEntity.getItemHandler().getStackInSlot(2).isEmpty() && pBlockEntity.getCurrentRecipe() == null) {
            return false;
        }

        Level level = pBlockEntity.level;
        pBlockEntity.setCurrentRecipe(level.getRecipeManager().getRecipeFor(DecomponentalizingRecipeType.INSTANCE, new SimpleContainer(pBlockEntity.getItemHandler().getStackInSlot(2)), level).orElse(null));

        return pBlockEntity.getCurrentRecipe() != null && pBlockEntity.canOutput(pBlockEntity.getCurrentRecipe().getResultItem());
    }

    private boolean canOutput(ItemStack output) {
        return itemHandler.getStackInSlot(3).isEmpty() && (!itemHandler.getStackInSlot(0).isEmpty() && itemHandler.getStackInSlot(0).equals(new ItemStack(Items.PAPER))) && (!itemHandler.getStackInSlot(1).isEmpty() && itemHandler.getStackInSlot(1).equals(new ItemStack(Items.INK_SAC)));
    }

    private void craftItem(DecomponentalizerBlockEntity pBlockEntity) {
        if (pBlockEntity.getCurrentRecipe() != null && canOutput(pBlockEntity.getCurrentRecipe().getResultItem())) {
            itemHandler.extractItem(0, 1, false);
            itemHandler.extractItem(1, 1, false);
//            itemHandler.extractItem(2, 1, false);
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

    public DecomponentalizingRecipe getCurrentRecipe() {
        return currentRecipe;
    }

    public void setCurrentRecipe(DecomponentalizingRecipe currentRecipe) {
        this.currentRecipe = currentRecipe;
    }

    public int isProcessing() {
        return isProcessing;
    }

    public void setProcessing(int processing) {
        isProcessing = processing;
    }

    public List<DecomponentalizingRecipe> getAvailableRecipes() {
        Level level = this.level;
        if (level == null) {
            Main.LOGGER.info("level is Empty");
            return Collections.emptyList();
        }

        ItemStack componentStack = itemHandler.getStackInSlot(2);
        if (componentStack.isEmpty()) {
            Main.LOGGER.info("componentStack is Empty");
            return Collections.emptyList();
        }

        return level.getRecipeManager().getRecipesFor(DecomponentalizingRecipeType.INSTANCE, new SimpleContainer(componentStack), level);
    }

    public void setSelectedRecipe(DecomponentalizingRecipe recipe) {
        this.selectedRecipe = recipe;
    }

    public DecomponentalizingRecipe getSelectedRecipe() {
        return selectedRecipe;
    }
}
