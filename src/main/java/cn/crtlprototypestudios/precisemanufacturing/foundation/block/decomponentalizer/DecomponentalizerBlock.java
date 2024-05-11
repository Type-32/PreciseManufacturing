package cn.crtlprototypestudios.precisemanufacturing.foundation.block.decomponentalizer;

import cn.crtlprototypestudios.precisemanufacturing.foundation.ModBlockEntities;
import com.simibubi.create.content.kinetics.base.HorizontalKineticBlock;
import com.simibubi.create.foundation.block.IBE;
import com.simibubi.create.foundation.item.ItemHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

public class DecomponentalizerBlock extends HorizontalKineticBlock implements IBE<DecomponentalizerBlockEntity> {
    public DecomponentalizerBlock(Properties properties) {
        super(properties);
    }

    @Override
    public Class<DecomponentalizerBlockEntity> getBlockEntityClass() {
        return DecomponentalizerBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends DecomponentalizerBlockEntity> getBlockEntityType() {
        return ModBlockEntities.DECOMPONENTALIZER.get();
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if(pLevel.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        withBlockEntityDo(pLevel, pPos, be -> NetworkHooks.openGui((ServerPlayer) pPlayer, be, pPos));

        return InteractionResult.SUCCESS;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof DecomponentalizerBlockEntity) {
                ((DecomponentalizerBlockEntity) blockEntity).drops();
            }
            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    @Override
    public Direction.Axis getRotationAxis(BlockState state) {
        return state.getValue(HorizontalKineticBlock.HORIZONTAL_FACING).getAxis();
    }

    @Override
    public boolean hasShaftTowards(LevelReader world, BlockPos pos, BlockState state, Direction face) {
        return face == state.getValue(HORIZONTAL_FACING);
    }
}
