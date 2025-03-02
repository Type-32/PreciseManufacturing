package cn.crtlprototypestudios.prma.foundation.neo.complex.content.processing.casting_basin;

import cn.crtlprototypestudios.prma.foundation.PrmaRecipeTypes;
import cn.crtlprototypestudios.prma.foundation.neo.complex.content.processing.casting_basin.recipe.CastingRecipe;
import cn.crtlprototypestudios.prma.mixin.SpoutBlockEntityAccessor;
import com.simibubi.create.content.fluids.spout.SpoutBlockEntity;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class CastingBasinBlockEntity extends SmartBlockEntity {
    protected CastingBasinInventory inventory;
    protected LazyOptional<IItemHandler> itemCapability;

    public CastingBasinBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        inventory = new CastingBasinInventory(this);
        itemCapability = LazyOptional.of(() -> inventory);
    }

    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
    }

    // Instead of overriding setRemoved(), we'll override invalidate()
    @Override
    public void invalidate() {
        super.invalidate();
        itemCapability.invalidate();
    }

    public void dropInventory() {
        if (level != null && !level.isClientSide) {
            for (int i = 0; i < inventory.getSlots(); i++) {
                ItemStack stack = inventory.extractItem(i, 64, false);
                if (!stack.isEmpty()) {
                    net.minecraft.world.entity.item.ItemEntity itementity = new ItemEntity(
                            level,
                            worldPosition.getX() + 0.5D,
                            worldPosition.getY() + 0.5D,
                            worldPosition.getZ() + 0.5D,
                            stack
                    );
                    itementity.setDeltaMovement(
                            level.random.nextDouble() * 0.2D - 0.1D,
                            0.2D,
                            level.random.nextDouble() * 0.2D - 0.1D
                    );
                    level.addFreshEntity(itementity);
                }
            }
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (level == null || level.isClientSide)
            return;

        // Check for spout above and valid input
        if (!inventory.getStackInSlot(0).isEmpty() && inventory.getStackInSlot(1).isEmpty()) {
            BlockPos above = worldPosition.above();
            if (level.getBlockEntity(above) instanceof SpoutBlockEntity spout) {
                FluidStack availableFluid = ((SpoutBlockEntityAccessor) spout).getTank().getPrimaryHandler().getFluid();
                if (!availableFluid.isEmpty()) {
                    handleRecipe(availableFluid);
                }
            }
        }
    }

    public void onInteract(Player player, InteractionHand hand) {
        assert level != null;
        if (level.isClientSide)
            return;

        ItemStack heldItem = player.getItemInHand(hand);
        boolean wasEmpty = heldItem.isEmpty();

        // Try to extract from output first
        if (wasEmpty) {
            ItemStack output = inventory.extractItem(1, 64, false);
            if (!output.isEmpty()) {
                player.setItemInHand(hand, output);
                notifyUpdate();
                return;
            }
        }

        // Then try to extract from input if output is empty
        if (wasEmpty && inventory.getStackInSlot(1).isEmpty()) {
            ItemStack input = inventory.extractItem(0, 64, false);
            if (!input.isEmpty()) {
                player.setItemInHand(hand, input);
                notifyUpdate();
                return;
            }
        }

        // Finally try to insert into input slot
        if (!wasEmpty) {
            ItemStack remainder = inventory.insertItem(0, heldItem, false);
            player.setItemInHand(hand, remainder);
            notifyUpdate();
        }
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER)
            return itemCapability.cast();
        return super.getCapability(cap, side);
    }

    private CastingRecipe findMatchingRecipe() {
        if (level == null || inventory.getStackInSlot(0).isEmpty())
            return null;

        for (Recipe<?> recipe : level.getRecipeManager().getAllRecipesFor(PrmaRecipeTypes.CASTING.getType())) {
            if (recipe instanceof CastingRecipe castingRecipe) {
                if (castingRecipe.matches(new RecipeWrapper(inventory), level)) {
                    return castingRecipe;
                }
            }
        }
        return null;
    }

    public void handleRecipe(FluidStack incomingFluid) {
        if (level == null || level.isClientSide)
            return;

        CastingRecipe recipe = findMatchingRecipe();
        if (recipe == null)
            return;

        // Check if fluid matches recipe
        if (!recipe.getFluidIngredients().get(0).test(incomingFluid))
            return;

        // Process recipe
        ItemStack result = recipe.getResultItem(level.registryAccess()).copy();
        if (!inventory.getStackInSlot(1).isEmpty())
            return;

        // Set the output
        inventory.setStackInSlot(1, result);
        // Don't consume the cast - it stays in slot 0

        notifyUpdate();
    }
}
