package cn.crtlprototypestudios.prma.foundation;

import cn.crtlprototypestudios.prma.PreciseManufacturing;
import cn.crtlprototypestudios.prma.foundation.fluid.*;
import com.simibubi.create.content.fluids.VirtualFluid;
import com.tterrag.registrate.util.entry.FluidEntry;

public class PrmaFluids {
    static {
        // Set that all registered Items and Blocks defaults under this Mod's creative tabs
        PreciseManufacturing.REGISTRATE.defaultCreativeTab(PrmaCreativeModTabs.MOD_TAB.getKey());
    }

    public static final FluidEntry<? extends VirtualFluid>
            MOLTEN_COPPER = PreciseManufacturing.REGISTRATE.extendedVirtualFluid("molten_copper", MoltenCopperFluid::new)
                    .lang("Molten Copper")
                    .tag(PrmaTags.forgeFluidTag("molten_metals"), PrmaTags.moltenCoppersTag())
                    .register(),
            MOLTEN_BRASS = PreciseManufacturing.REGISTRATE.extendedVirtualFluid("molten_brass", MoltenBrassFluid::new)
                    .lang("Molten Brass")
                    .tag(PrmaTags.forgeFluidTag("molten_metals"), PrmaTags.moltenBrassesTag())
                    .register(),
            MOLTEN_IRON = PreciseManufacturing.REGISTRATE.extendedVirtualFluid("molten_iron", MoltenIronFluid::new)
                    .lang("Molten Iron")
                    .tag(PrmaTags.moltenFluidsTag(), PrmaTags.moltenIronTag())
                    .register(),
            MOLTEN_ALUMINUM = PreciseManufacturing.REGISTRATE.extendedVirtualFluid("molten_aluminum", MoltenAluminumFluid::new)
                    .lang("Molten Aluminum")
                    .tag(PrmaTags.moltenFluidsTag(), PrmaTags.moltenAluminumTag())
                    .register(),
            MOLTEN_METAL_ALLOY = PreciseManufacturing.REGISTRATE.extendedVirtualFluid("molten_metal_alloy", MoltenMetalAlloyFluid::new)
                    .lang("Molten Metal Alloy")
                    .tag(PrmaTags.forgeFluidTag("molten_metals"))
                    .register(),
            MOLTEN_STRONG_ALUMINUM_ALLOY = PreciseManufacturing.REGISTRATE.extendedVirtualFluid("molten_strong_aluminum_alloy", MoltenStrongAluminumFluid::new)
                    .lang("Molten Strong Aluminum Alloy")
                    .tag(PrmaTags.forgeFluidTag("molten_metals"))
                    .register(); //灵感来源: LDT的LDX 7075波壳, 就是7075铝合金

    public static void register() {
        // Do not delete; for loading the class
    }
}
