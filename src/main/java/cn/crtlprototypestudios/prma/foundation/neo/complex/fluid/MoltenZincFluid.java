package cn.crtlprototypestudios.prma.foundation.neo.complex.fluid;

import cn.crtlprototypestudios.prma.foundation.PrmaItems;
import com.simibubi.create.content.fluids.VirtualFluid;
import net.minecraft.world.item.Item;

public class MoltenZincFluid extends VirtualFluid {

    public MoltenZincFluid(Properties properties) {
        super(properties);
    }

    @Override
    public Item getBucket() {
        return PrmaItems.MOLTEN_ZINC_BUCKET.get();
    }
}
