package cn.crtlprototypestudios.prma.foundation.neo.complex.fluid;

import cn.crtlprototypestudios.prma.foundation.PrmaItems;
import com.simibubi.create.content.fluids.VirtualFluid;
import net.minecraft.world.item.Item;

public class MoltenCopperFluid extends VirtualFluid {

    public MoltenCopperFluid(Properties properties) {
        super(properties);
    }

    @Override
    public Item getBucket() {
        return PrmaItems.MOLTEN_COPPER_BUCKET.get();
    }
}
