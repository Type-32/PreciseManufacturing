package cn.crtlprototypestudios.precisemanufacturing.foundation.fluid;

import cn.crtlprototypestudios.precisemanufacturing.foundation.item.ModItems;
import com.simibubi.create.content.fluids.VirtualFluid;
import net.minecraft.world.item.Item;

public class MoltenCopperFluid extends VirtualFluid {

    public MoltenCopperFluid(Properties properties) {
        super(properties);
    }

    @Override
    public Item getBucket() {
        return ModItems.MOLTEN_COPPER_BUCKET.get();
    }
}
