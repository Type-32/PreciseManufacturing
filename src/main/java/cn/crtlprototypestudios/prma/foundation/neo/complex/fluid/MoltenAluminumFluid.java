package cn.crtlprototypestudios.prma.foundation.neo.complex.fluid;

import cn.crtlprototypestudios.prma.foundation.PrmaItems;
import com.simibubi.create.content.fluids.VirtualFluid;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;

public class MoltenAluminumFluid extends VirtualFluid {

    public MoltenAluminumFluid(Properties properties) {
        super(properties, false);
    }

    @Override
    public @NotNull Item getBucket() {
        return PrmaItems.MOLTEN_ALUMINUM_BUCKET.get();
    }
}
