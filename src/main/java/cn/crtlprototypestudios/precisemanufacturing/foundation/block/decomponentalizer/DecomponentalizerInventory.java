package cn.crtlprototypestudios.precisemanufacturing.foundation.block.decomponentalizer;

import cn.crtlprototypestudios.precisemanufacturing.foundation.ModTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

public class DecomponentalizerInventory extends ItemStackHandler {
    private final DecomponentalizerBlockEntity blockEntity;
    public DecomponentalizerInventory(DecomponentalizerBlockEntity blockEntity) {
        super(4);
        this.blockEntity = blockEntity;
    }

    @Override
    protected void onContentsChanged(int slot) {
        super.onContentsChanged(slot);
        blockEntity.setChanged();
    }

    @Override
    public boolean isItemValid(int slot, @NotNull ItemStack stack) {
        return switch (slot) {
            case 0 -> // Paper slot
                    stack.sameItem(new ItemStack(Items.PAPER));
            case 1 -> // Ink Slot
                    stack.sameItem(new ItemStack(Items.INK_SAC));
            case 2 -> // Gun Slot
                    stack.getItem().getRegistryName().toString().equals("tacz:modern_kinetic_gun");
            case 3 -> false;
            default -> super.isItemValid(slot, stack);
        };
    }
}
