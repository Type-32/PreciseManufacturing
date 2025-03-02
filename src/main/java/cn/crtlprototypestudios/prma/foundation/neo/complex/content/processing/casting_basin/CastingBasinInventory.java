package cn.crtlprototypestudios.prma.foundation.neo.complex.content.processing.casting_basin;

import cn.crtlprototypestudios.prma.foundation.PrmaTags;
import com.simibubi.create.foundation.blockEntity.SyncedBlockEntity;
import com.simibubi.create.foundation.item.SmartInventory;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CastingBasinInventory extends SmartInventory {
    private CastingBasinBlockEntity blockEntity;

    public CastingBasinInventory(SyncedBlockEntity be) {
        // Two slots
        super(2, be, 1, false);
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
        // Only allow insertion into the first slot
        if (slot != 0) {
            return stack;
        }

        // Ensure the item has the required tag
        if (!stack.is(PrmaTags.ItemTag.CASTING_BASIN_PLACEABLE.tag)) {
            return stack;
        }

        // Only allow one item to be inserted
        if (stack.getCount() > 1) {
            ItemStack singleItemStack = stack.copy();
            singleItemStack.setCount(1);
            return singleItemStack;
        }

        // Check if the first slot is empty
        if (!inv.getStackInSlot(0).isEmpty()) {
            return stack;
        }

        return super.insertItem(slot, stack, simulate);
    }

    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        // Only allow extraction from the second slot first if it is not empty
        if (slot == 0 && !inv.getStackInSlot(1).isEmpty()) {
            return ItemStack.EMPTY;
        }

        ItemStack extractItem = super.extractItem(slot, amount, simulate);
        if (!simulate && !extractItem.isEmpty()) {
//            blockEntity.notifyChangeOfContents();
        }
        return extractItem;
    }

    public void dropContents(Level level, BlockPos worldPosition) {

    }
}
