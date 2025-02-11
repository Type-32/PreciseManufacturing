package cn.crtlprototypestudios.prma.foundation.neo.content.processing.casting_basin;

import cn.crtlprototypestudios.prma.foundation.PrmaTags;
import com.simibubi.create.content.equipment.goggles.IHaveGoggleInformation;
import com.simibubi.create.content.equipment.goggles.IHaveHoveringInformation;
import com.simibubi.create.content.fluids.spout.SpoutBlockEntity;
import com.simibubi.create.content.kinetics.belt.behaviour.DirectBeltInputBehaviour;
import com.simibubi.create.content.kinetics.belt.transport.TransportedItemStack;
import com.simibubi.create.content.processing.basin.BasinInventory;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.ValueBoxTransform;
import com.simibubi.create.foundation.blockEntity.behaviour.filtering.FilteringBehaviour;
import com.simibubi.create.foundation.item.ItemHelper;
import com.simibubi.create.foundation.item.SmartInventory;
import com.simibubi.create.foundation.utility.*;
import com.simibubi.create.foundation.utility.animation.LerpedFloat;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;

import javax.annotation.Nonnull;
import java.util.List;

public class CastingBasinBlockEntity extends SmartBlockEntity implements IHaveGoggleInformation {

    private boolean areFluidsMoving;

    public CastingBasinInventory inputInventory;
    protected SmartInventory outputInventory;
    private FilteringBehaviour filtering;
    private boolean contentsChanged;
    protected List<ItemStack> spoutputBuffer;

    private Couple<SmartInventory> invs;

    protected LazyOptional<IItemHandlerModifiable> itemCapability;
    int recipeBackupCheck;

    public static final int OUTPUT_ANIMATION_TIME = 10;
    List<IntAttached<ItemStack>> visualizedOutputItems;

    public CastingBasinBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        inputInventory = new CastingBasinInventory(this);
        inputInventory.whenContentsChanged($ -> contentsChanged = true);
        outputInventory = new CastingBasinInventory(this).forbidInsertion().withMaxStackSize(64);
        areFluidsMoving = false;
        itemCapability = LazyOptional.of(() -> new CombinedInvWrapper(inputInventory, outputInventory));
        contentsChanged = true;
    }

    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
        // Add filtering for the input slot
        filtering = new FilteringBehaviour(this, new CastingBasinValueBox())
                .withCallback(newFilter -> contentsChanged = true)
                .forRecipes();
        behaviours.add(filtering);

        // Direct belt input handling
        behaviours.add(new DirectBeltInputBehaviour(this)
                .allowingBeltFunnels()
                .setInsertionHandler(this::tryInsertingFromSide)
                .considerOccupiedWhen(this::isOccupied));
    }

    private boolean isOccupied(Direction side) {
        return !inputInventory.getStackInSlot(0).isEmpty() && !inputInventory.getStackInSlot(1).isEmpty();
    }

    private ItemStack tryInsertingFromSide(TransportedItemStack transportedStack, Direction side, boolean simulate) {
        if (isOccupied(side))
            return transportedStack.stack;

        if (!transportedStack.stack.is(PrmaTags.ItemTag.CASTING_BASIN_PLACEABLE.tag))
            return transportedStack.stack;

        ItemStack remainder = inputInventory.insertItem(0, transportedStack.stack.copy(), simulate);
        if (!remainder.equals(transportedStack.stack))
            notifyUpdate();

        return remainder;
    }

    @Override
    public void tick() {
        super.tick();
        if (!level.isClientSide && contentsChanged) {
            contentsChanged = false;
            // Check for recipes when input changes
            if (!inputInventory.getStackInSlot(0).isEmpty()) {
                checkForProcessingRecipes();
            }
        }
    }

    protected void checkForProcessingRecipes() {
        if (level.isClientSide)
            return;

        // Get the block above
        BlockPos abovePos = worldPosition.above();
        BlockEntity be = level.getBlockEntity(abovePos);
        if (!(be instanceof SpoutBlockEntity))
            return;

        // Check for valid recipe with current cast and incoming fluid
        // Implementation depends on your recipe system
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, Direction side) {
//        if (cap == ForgeCapabilities.ITEM_HANDLER)
//            return itemHandler.cast();
        return super.getCapability(cap, side);
    }

    @Override
    public void invalidate() {
        super.invalidate();
//        itemHandler.invalidate();
    }

    @Override
    public void write(CompoundTag compound, boolean clientPacket) {
        super.write(compound, clientPacket);
        compound.put("Inventory", inputInventory.serializeNBT());
    }

    @Override
    public void read(CompoundTag compound, boolean clientPacket) {
        super.read(compound, clientPacket);
        inputInventory.deserializeNBT(compound.getCompound("Inventory"));
    }

    @Override
    public void destroy() {
        super.destroy();
        ItemHelper.dropContents(level, worldPosition, inputInventory);
    }

    @Override
    public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
        Lang.translate("gui.goggles.casting_basin_contents").forGoggles(tooltip);

        // Show input slot
        ItemStack input = inputInventory.getStackInSlot(0);
        if (!input.isEmpty()) {
            Lang.text("")
                    .add(Components.translatable(input.getDescriptionId())
                            .withStyle(ChatFormatting.GRAY))
                    .forGoggles(tooltip, 1);
        }

        // Show output slot
        ItemStack output = outputInventory.getStackInSlot(1);
        if (!output.isEmpty()) {
            Lang.text("")
                    .add(Components.translatable(output.getDescriptionId())
                            .withStyle(ChatFormatting.GREEN))
                    .forGoggles(tooltip, 1);
        }

        return true;
    }

    static class CastingBasinValueBox extends ValueBoxTransform.Sided {
        @Override
        protected Vec3 getSouthLocation() {
            return VecHelper.voxelSpace(8, 12, 16.05);
        }

        @Override
        protected boolean isSideActive(BlockState state, Direction direction) {
            return direction.getAxis().isHorizontal();
        }
    }

    public void notifyChangeOfContents() {
        contentsChanged = true;
    }
}
