package cn.crtlprototypestudios.precisemanufacturing.foundation.block.decomponentalizer;

import cn.crtlprototypestudios.precisemanufacturing.Main;
import cn.crtlprototypestudios.precisemanufacturing.foundation.ModBlockEntities;
import cn.crtlprototypestudios.precisemanufacturing.foundation.client.gui.decomponentalizer.DecomponentalizerContainerMenu;
import cn.crtlprototypestudios.precisemanufacturing.foundation.recipe.decomponentalizing.DecomponentalizingRecipe;
import cn.crtlprototypestudios.precisemanufacturing.foundation.recipe.decomponentalizing.DecomponentalizingRecipeType;
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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
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

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

public class DecomponentalizerBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler;
    private final LazyOptional<IItemHandler> itemHandlerCap;
    private int processingTime;
    private int totalProcessingTime;
    private int isProcessing = 0;
    private int currentRecipeIndex, selectedRecipeIndex;
    private ContainerData data;

    public DecomponentalizerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DECOMPONENTALIZER.get(), pos, state);
        currentRecipeIndex = -1;
        selectedRecipeIndex = -1;
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
    public @NotNull Component getDisplayName() {
        return Component.translatable("block.prma.container.decomponentalizer");
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

    @Override
    public void setChanged() {
        super.setChanged();

        if(this.level != null && this.level.isClientSide){
            this.level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), DecomponentalizerBlock.UPDATE_ALL);
        }
    }

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, DecomponentalizerBlockEntity pBlockEntity) {
        if (pLevel == null || pLevel.isClientSide) {
            return;
        }

//        Main.LOGGER.debug("Decomponentalizer block entity states: currentRecipe: {};   selectedRecipe: {};    posInWorld: {};     maxProcessingTime: {}", pBlockEntity.currentRecipeIndex, pBlockEntity.selectedRecipeIndex, pPos, pBlockEntity.processingTime);

        if (hasRecipe(pBlockEntity)) {
            if (pBlockEntity.getProcessingTime() < pBlockEntity.getTotalProcessingTime()) {
                pBlockEntity.setProcessingTime(pBlockEntity.getProcessingTime() + 1);
//                Main.LOGGER.debug("ProcessingTime: {}", pBlockEntity.getProcessingTime());
                setChanged(pLevel, pPos, pState);
            } else {
                pBlockEntity.craftItem(pBlockEntity);
                pBlockEntity.setProcessingTime(0);
                pBlockEntity.setSelectedRecipeIndex(-1);
                pBlockEntity.setCurrentRecipeIndex(-1);
                setChanged(pLevel, pPos, pState);
            }
        } else {
            pBlockEntity.setProcessingTime(0);
            pBlockEntity.setSelectedRecipeIndex(-1);
            pBlockEntity.setCurrentRecipeIndex(-1);
            setChanged(pLevel, pPos, pState);
        }
    }

    private static boolean hasRecipe(DecomponentalizerBlockEntity pBlockEntity) {
        if (pBlockEntity.getItemHandler().getStackInSlot(2).isEmpty() || pBlockEntity.getCurrentRecipeIndex() == -1) {
            return false;
        }

//        Level level = pBlockEntity.level;
//        pBlockEntity.setCurrentRecipe(level.getRecipeManager().getRecipeFor(DecomponentalizingRecipeType.INSTANCE, new SimpleContainer(pBlockEntity.getItemHandler().getStackInSlot(2)), level).orElse(null));
        boolean results = pBlockEntity.getCurrentRecipe() != null && pBlockEntity.canOutput(pBlockEntity.getCurrentRecipe().getResultItem());
        Main.LOGGER.debug("Processing hasRecipe() after results: {}", results);
        return results;
    }

    private boolean canOutput(ItemStack output) {
        boolean paperInSlot = !itemHandler.getStackInSlot(0).isEmpty() && itemHandler.getStackInSlot(0).getItem().equals(Items.PAPER),
        inkInSlot = !itemHandler.getStackInSlot(1).isEmpty() && itemHandler.getStackInSlot(1).getItem().equals(Items.INK_SAC),
        outputSlotEmpty = itemHandler.getStackInSlot(3).isEmpty();

        Main.LOGGER.debug("CanOutput() testing:  output Slot empty: {};   inkIsInSlot: {};   Paper in slot: {}", outputSlotEmpty, inkInSlot, paperInSlot);
        return outputSlotEmpty && (paperInSlot && inkInSlot);
    }

    private void craftItem(DecomponentalizerBlockEntity pBlockEntity) {
        if (pBlockEntity.getCurrentRecipeIndex() != -1 && canOutput(pBlockEntity.getCurrentRecipe().getResultItem())) {
            itemHandler.extractItem(0, 1, false);
            itemHandler.extractItem(1, 1, false);
//            itemHandler.extractItem(2, 1, false);
            itemHandler.setStackInSlot(3, new ItemStack(getCurrentRecipe().getResultItem().getItem(), itemHandler.getStackInSlot(3).getCount() + 1));
        }
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
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

    public void setProcessingTime(int processingTime) {
        this.processingTime = processingTime;
    }


    public int getTotalProcessingTime() {
        return totalProcessingTime;
    }

    public void setTotalProcessingTime(int totalProcessingTime) {
        this.totalProcessingTime = totalProcessingTime;
    }

    public ItemStackHandler getItemHandler() {
        return itemHandler;
    }

    public int getCurrentRecipeIndex() {
        return currentRecipeIndex;
    }

    public void setCurrentRecipeIndex(int availableRecipeIndex) {
        setCurrentRecipeIndex(availableRecipeIndex, getAvailableRecipes());
    }

    public void setCurrentRecipeIndex(int availableRecipeIndex, List<DecomponentalizingRecipe> availableRecipes) {
        this.currentRecipeIndex = availableRecipeIndex >= availableRecipes.size() || availableRecipeIndex < 0 ? -1 : availableRecipeIndex;
    }

    public void setCurrentRecipeIndex(int availableRecipeIndex, boolean setDurationTime) {
        List<DecomponentalizingRecipe> availableRecipes = getAvailableRecipes();
        setCurrentRecipeIndex(availableRecipeIndex, availableRecipes);
        if(!(availableRecipeIndex >= availableRecipes.size() || availableRecipeIndex < 0) && setDurationTime) {
            setTotalProcessingTime(availableRecipes.get(availableRecipeIndex).getProcessingTime());
        }
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
        } else {
            Main.LOGGER.info("componentStack is NOT EMPTY");
        }

        List<DecomponentalizingRecipe> recipes = level.getRecipeManager().getRecipesFor(DecomponentalizingRecipeType.INSTANCE, new SimpleContainer(componentStack), level);

        Main.LOGGER.info("Recognized Recipes: {}", recipes);
        return recipes;
    }

    public void setSelectedRecipeIndex(int availableRecipeIndex) {
        List<DecomponentalizingRecipe> availableRecipes = getAvailableRecipes();
        this.selectedRecipeIndex = availableRecipeIndex >= availableRecipes.size() || availableRecipeIndex < 0 ? -1 : availableRecipeIndex;
    }

    public int getSelectedRecipeIndex() {
        return selectedRecipeIndex;
    }

    public DecomponentalizingRecipe getAvailableRecipeFromIndex(int index){
        if(index < 0 || index >= getAvailableRecipes().size()){
            return null;
        }
        return getAvailableRecipes().get(index);
    }

    public DecomponentalizingRecipe getCurrentRecipe(){
        return getAvailableRecipeFromIndex(currentRecipeIndex);
    }

    public DecomponentalizingRecipe getSelectedRecipe(){
        return getAvailableRecipeFromIndex(selectedRecipeIndex);
    }

    /**
     * Sets the current worked-on recipe
     * @param recipe The recipe in the list of available recipes
     * @return Returns true if success and that the given recipe is in the list of available recipes, false if otherwise
     */
    public boolean setCurrentRecipe(DecomponentalizingRecipe recipe) {
        List<DecomponentalizingRecipe> availableRecipes = getAvailableRecipes();

        if(availableRecipes.contains(recipe)){
            currentRecipeIndex = availableRecipes.indexOf(recipe);
            return true;
        }else{
            currentRecipeIndex = -1;
            return false;
        }
    }

    /**
     * Sets the selected recipe
     * @param recipe The recipe in the list of available recipes
     * @return Returns true if success and that the given recipe is in the list of available recipes, false if otherwise
     */
    public boolean setSelectedRecipe(DecomponentalizingRecipe recipe) {
        List<DecomponentalizingRecipe> availableRecipes = getAvailableRecipes();

        if(availableRecipes.contains(recipe)){
            selectedRecipeIndex = availableRecipes.indexOf(recipe);
            return true;
        }else{
            selectedRecipeIndex = -1;
            return false;
        }
    }
}
