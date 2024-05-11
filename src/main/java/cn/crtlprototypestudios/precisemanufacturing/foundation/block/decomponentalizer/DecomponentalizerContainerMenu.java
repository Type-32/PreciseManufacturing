package cn.crtlprototypestudios.precisemanufacturing.foundation.block.decomponentalizer;

import cn.crtlprototypestudios.precisemanufacturing.foundation.ModBlocks;
import cn.crtlprototypestudios.precisemanufacturing.foundation.ModContainers;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.items.SlotItemHandler;

public class DecomponentalizerContainerMenu extends AbstractContainerMenu {
    private final DecomponentalizerBlockEntity blockEntity;
    private final ContainerData data;

    public DecomponentalizerContainerMenu(int id, Inventory inventory, FriendlyByteBuf extraData){
        this(id, inventory, (DecomponentalizerBlockEntity) inventory.player.level.getBlockEntity(extraData.readBlockPos()));
    }

    public DecomponentalizerContainerMenu(int id, Inventory playerInventory, DecomponentalizerBlockEntity blockEntity) {
        super(ModContainers.DECOMPONENTALIZER.get(), id);
        this.blockEntity = blockEntity;
        this.data = new ContainerData() {
            public int get(int index) {
                switch (index) {
                    case 0:
                        return DecomponentalizerContainerMenu.this.blockEntity.getProcessingTime();
                    case 1:
                        return DecomponentalizerContainerMenu.this.blockEntity.getTotalProcessingTime();
                    default:
                        return 0;
                }
            }

            public void set(int index, int value) {
                switch (index) {
                    case 0:
                        DecomponentalizerContainerMenu.this.blockEntity.setProcessingTime(value);
                        break;
                    case 1:
                        DecomponentalizerContainerMenu.this.blockEntity.setTotalProcessingTime(value);
                        break;
                }
            }

            public int getCount() {
                return 2;
            }
        };

        addSlot(new SlotItemHandler(blockEntity.getItemHandler(), 0, 56, 35));
        addSlot(new SlotItemHandler(blockEntity.getItemHandler(), 1, 79, 58));
        addSlot(new SlotItemHandler(blockEntity.getItemHandler(), 2, 102, 35));
        addSlot(new SlotItemHandler(blockEntity.getItemHandler(), 3, 140, 35));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int k = 0; k < 9; k++) {
            addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
        }

        addDataSlots(data);
    }

    public boolean isCrafting() {
        return data.get(0) > 0;
    }

    public int getProcessingTime() {
        return data.get(0);
    }

    public int getTotalProcessingTime() {
        return data.get(1);
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos()), player, ModBlocks.DECOMPONENTALIZER.get());
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = slots.get(index);
        if (slot.hasItem()) {
            ItemStack stack = slot.getItem();
            itemstack = stack.copy();
            if (index == 3) {
                if (!moveItemStackTo(stack, 4, 40, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(stack, itemstack);
            } else if (index != 0 && index != 1 && index != 2) {
                if (ForgeHooks.getBurnTime(stack, null) > 0) {
                    if (!moveItemStackTo(stack, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < 31) {
                    if (!moveItemStackTo(stack, 31, 40, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < 40 && !moveItemStackTo(stack, 4, 31, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!moveItemStackTo(stack, 4, 40, false)) {
                return ItemStack.EMPTY;
            }

            if (stack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (stack.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, stack);
        }

        return itemstack;
    }
}
