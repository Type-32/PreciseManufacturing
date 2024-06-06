package cn.crtlprototypestudios.precisemanufacturing.foundation.gui.decomponentalizer;

import cn.crtlprototypestudios.precisemanufacturing.Main;
import cn.crtlprototypestudios.precisemanufacturing.foundation.ModBlocks;
import cn.crtlprototypestudios.precisemanufacturing.foundation.ModContainers;
import cn.crtlprototypestudios.precisemanufacturing.foundation.block.decomponentalizer.DecomponentalizerBlockEntity;
import cn.crtlprototypestudios.precisemanufacturing.foundation.gui.LockableInputSlot;
import cn.crtlprototypestudios.precisemanufacturing.foundation.gui.ModResultSlot;
import cn.crtlprototypestudios.precisemanufacturing.foundation.handler.PacketHandler;
import cn.crtlprototypestudios.precisemanufacturing.foundation.network.packets.C2SSetDecomponentalizerCurrentRecipePacket;
import cn.crtlprototypestudios.precisemanufacturing.util.annotations.ClientServerSide;
import cn.crtlprototypestudios.precisemanufacturing.util.annotations.ClientSide;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import org.jetbrains.annotations.NotNull;

@ClientSide
public class DecomponentalizerContainerMenu extends AbstractContainerMenu {
    private final DecomponentalizerBlockEntity blockEntity;
    private final ContainerData data;
    private final Level level;

    public DecomponentalizerContainerMenu(int id, Inventory inventory, FriendlyByteBuf extraData){
        this(id, inventory, inventory.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(3));
    }

    public DecomponentalizerContainerMenu(int id, Inventory playerInventory, BlockEntity blockEntity, ContainerData data) {
        super(ModContainers.DECOMPONENTALIZER_CONTAINER_MENU.get(), id);
        checkContainerSize(playerInventory, 4);
        this.blockEntity = (DecomponentalizerBlockEntity) blockEntity;
        this.level = playerInventory.player.level();
        this.data = data;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlot(new Slot(playerInventory, j + i * 9 + 9, 34 + j * 18, 172 + i * 18));
            }
        }

        for (int k = 0; k < 9; k++) {
            addSlot(new Slot(playerInventory, k, 34 + k * 18, 230));
        }

        blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
            addSlot(new LockableInputSlot(handler, 0, 7, 23));
            addSlot(new LockableInputSlot(handler, 1, 25, 23));
            addSlot(new LockableInputSlot(handler, 2, 55, 23));
            addSlot(new ModResultSlot(handler, 3, 205, 23));
//            addSlot(new ModResultSlot(handler, 4, 200, 35));
        });

        addDataSlots(data);
    }

    @ClientSide
    public int getScaledProgress() {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);  // Max Progress
        int progressArrowSize = 148; // This is the height in pixels of your arrow

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    @ClientServerSide
    public boolean isCrafting() {
        return data.get(0) > 0;
    }

    @ClientSide
    public int getProcessingTime() {
        return data.get(0);
    }

    @ClientSide
    public int getTotalProcessingTime() {
        return data.get(1);
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()), player, ModBlocks.DECOMPONENTALIZER.get());
    }

    // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    // must assign a slot number to each of the slots used by the GUI.
    // For this container, we can see both the tile inventory's slots as well as the player inventory slots and the hotbar.
    // Each time we add a Slot to the container, it automatically increases the slotIndex, which means
    //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
    //  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
    //  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 0;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT;

    // THIS YOU HAVE TO DEFINE!
    private static final int TE_INVENTORY_SLOT_COUNT = 4;  // must be the number of slots you have!

    @ClientSide
    @Override
    public @NotNull ItemStack quickMoveStack(Player playerIn, int index) {
        Slot sourceSlot = slots.get(index);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (index < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (index < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + index);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }

    @ClientSide
    public void startRecipeProcess(int selectedIndex) {
//        Main.LOGGER.debug("Decomponentalizing Selected Recipe is null? {}", blockEntity.getCurrentRecipe() == null);
        if (!isCrafting()) {
            // These Logics are handled on server side; many thanks to @xjqsh for helping me fix this

            PacketHandler.sendToServer(new C2SSetDecomponentalizerCurrentRecipePacket(blockEntity.getBlockPos(), (short) selectedIndex));

            Main.LOGGER.debug("Starting Decomponentalizing Process");
        }
    }

    public DecomponentalizerBlockEntity getBlockEntity() {
        return blockEntity;
    }
}
