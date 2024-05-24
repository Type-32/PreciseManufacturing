package cn.crtlprototypestudios.precisemanufacturing.foundation.fluid;

import cn.crtlprototypestudios.precisemanufacturing.foundation.ModItems;
import com.simibubi.create.content.fluids.VirtualFluid;
import net.minecraft.world.item.Item;

public class MoltenBasaltInfusedIronFluid extends VirtualFluid {

    public MoltenBasaltInfusedIronFluid(Properties properties) {
        super(properties);
    }

    @Override
    public Item getBucket() {
        return ModItems.MOLTEN_BASALT_INFUSED_IRON_BUCKET.get();
    }
}
