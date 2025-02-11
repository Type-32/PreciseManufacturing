package cn.crtlprototypestudios.prma.foundation.neo.fluid;

import cn.crtlprototypestudios.prma.foundation.PrmaItems;
import com.simibubi.create.content.fluids.VirtualFluid;
import net.minecraft.world.item.Item;

public class MoltenMetalAlloyFluid extends VirtualFluid {

    public MoltenMetalAlloyFluid(Properties properties) {
        super(properties);
    }

    @Override
    public Item getBucket() {
        return PrmaItems.MOLTEN_METAL_ALLOY_BUCKET.get();
    }
}
