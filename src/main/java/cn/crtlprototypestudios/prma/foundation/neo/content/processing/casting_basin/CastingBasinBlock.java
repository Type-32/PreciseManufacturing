package cn.crtlprototypestudios.prma.foundation.neo.content.processing.casting_basin;

import com.simibubi.create.content.equipment.wrench.IWrenchable;
import com.simibubi.create.foundation.block.IBE;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

public class CastingBasinBlock extends Block implements IBE<>, IWrenchable {
    public static final DirectionProperty FACING = BlockStateProperties.FACING_HOPPER;

    public CastingBasinBlock(Properties pProperties) {
        super(pProperties);
        registerDefaultState(defaultBlockState().setValue(FACING, Direction.DOWN));
    }

    @Override
    public Class getBlockEntityClass() {
        return CastingBasinBlock.class;
    }

    @Override
    public BlockEntityType getBlockEntityType() {
        return null;
    }
}
