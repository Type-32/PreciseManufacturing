package cn.crtlprototypestudios.precisemanufacturing.foundation;

import cn.crtlprototypestudios.precisemanufacturing.Main;
import cn.crtlprototypestudios.precisemanufacturing.foundation.fluid.MoltenBasaltInfusedIronFluid;
import cn.crtlprototypestudios.precisemanufacturing.foundation.fluid.MoltenBrassFluid;
import cn.crtlprototypestudios.precisemanufacturing.foundation.fluid.MoltenCopperFluid;
import com.simibubi.create.content.fluids.VirtualFluid;
import com.tterrag.registrate.util.entry.FluidEntry;

public class ModFluids {
    public static final FluidEntry<? extends VirtualFluid>
            MOLTEN_COPPER = Main.REGISTRATE.extendedVirtualFluid("molten_copper", MoltenCopperFluid::new)
                .lang("Molten Copper")
                .tag(ModTags.forgeFluidTag("molten_metals"), ModTags.moltenCoppersTag())
                .register(),
            MOLTEN_BRASS = Main.REGISTRATE.extendedVirtualFluid("molten_brass", MoltenBrassFluid::new)
                .lang("Molten Brass")
                .tag(ModTags.forgeFluidTag("molten_metals"), ModTags.moltenBrassesTag())
                .register(),
            MOLTEN_BASALT_INFUSED_IRON = Main.REGISTRATE.extendedVirtualFluid("molten_basalt_infused_iron", MoltenBasaltInfusedIronFluid::new)
                .lang("Molten Basalt-Infused Iron")
                .tag(ModTags.forgeFluidTag("molten_metals"), ModTags.moltenIronsTag())
                .register();

    public static void register(){
        // Do not delete; for loading the class
    }
}
