package cn.crtlprototypestudios.precisemanufacturing.foundation.gui;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class LockableInputSlot extends SlotItemHandler {
    private boolean locked = false;

    public LockableInputSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        if(!locked)
            return super.mayPlace(stack);
        return false; // If it is locked, then cannot place
    }

    @Override
    public boolean mayPickup(Player playerIn) {
        if(!locked)
            return super.mayPickup(playerIn);
        return false; // If it is locked, then cannot pick up
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }
    public boolean isLocked() {
        return locked;
    }
}
