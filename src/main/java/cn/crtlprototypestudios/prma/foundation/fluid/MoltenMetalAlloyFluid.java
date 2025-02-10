package cn.crtlprototypestudios.prma.foundation.fluid;

import cn.crtlprototypestudios.prma.foundation.ModItems;
import com.simibubi.create.content.fluids.VirtualFluid;
import net.minecraft.world.item.Item;

public class MoltenMetalAlloyFluid extends VirtualFluid {

    public MoltenMetalAlloyFluid(Properties properties) {
        super(properties);
    }

    @Override
    public Item getBucket() {
        return ModItems.MOLTEN_METAL_ALLOY_BUCKET.get();
    }
}
