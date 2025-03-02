package cn.crtlprototypestudios.prma.foundation.neo.complex.fluid;

import cn.crtlprototypestudios.prma.foundation.PrmaItems;
import com.simibubi.create.content.fluids.VirtualFluid;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;

public class MoltenCopperFluid extends VirtualFluid {

    public MoltenCopperFluid(Properties properties) {
        super(properties, false);
    }

    @Override
    public @NotNull Item getBucket() {
        return PrmaItems.MOLTEN_COPPER_BUCKET.get();
    }
}
