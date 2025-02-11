package cn.crtlprototypestudios.prma.foundation.fluid;

import cn.crtlprototypestudios.prma.foundation.PrmaItems;
import com.simibubi.create.content.fluids.VirtualFluid;
import net.minecraft.world.item.Item;

public class MoltenBrassFluid extends VirtualFluid {

    public MoltenBrassFluid(Properties properties) {
        super(properties);
    }

    @Override
    public Item getBucket() {
        return PrmaItems.MOLTEN_BRASS_BUCKET.get();
    }
}
