package cn.crtlprototypestudios.precisemanufacturing.foundation.fluid;

import cn.crtlprototypestudios.precisemanufacturing.foundation.data.tag.ModTags;
import com.simibubi.create.Create;
import com.simibubi.create.content.fluids.VirtualFluid;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.FluidEntry;

public class ModFluids {
    public static final FluidEntry<VirtualFluid>
            MOLTEN_COPPER = Create.REGISTRATE.virtualFluid("molten_copper")
                .lang("Molten Copper")
                .tag(ModTags.forgeFluidTag("molten_metals"))
                .register(),
            MOLTEN_BRASS = Create.REGISTRATE.virtualFluid("molten_brass")
                .lang("Molten Copper")
                .tag(ModTags.forgeFluidTag("molten_metals"))
                .register(),
            MOLTEN_BASALT_INFUSED_IRON = Create.REGISTRATE.virtualFluid("molten_basalt_infused_iron")
                .lang("Molten Basalt-Infused Iron")
                .tag(ModTags.forgeFluidTag("molten_metals"))
                .register();

    public static void register(){
        // Do not delete; for loading the class
    }
}
