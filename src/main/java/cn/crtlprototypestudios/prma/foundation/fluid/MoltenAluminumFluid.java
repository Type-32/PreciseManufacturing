package cn.crtlprototypestudios.prma.foundation.fluid;

import cn.crtlprototypestudios.prma.foundation.ModItems;
import com.simibubi.create.content.fluids.VirtualFluid;
import net.minecraft.world.item.Item;

public class MoltenAluminumFluid extends VirtualFluid {

    public MoltenAluminumFluid(Properties properties) {
        super(properties);
    }

    @Override
    public Item getBucket() {
        return ModItems.MOLTEN_ALUMINUM_BUCKET.get();
    }
}
