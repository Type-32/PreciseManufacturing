package cn.crtlprototypestudios.prma.foundation;

import cn.crtlprototypestudios.prma.Main;
import cn.crtlprototypestudios.prma.foundation.fluid.*;
import com.simibubi.create.content.fluids.VirtualFluid;
import com.tterrag.registrate.util.entry.FluidEntry;

public class ModFluids {
    static {
        // Set that all registered Items and Blocks defaults under this Mod's creative tabs
        Main.REGISTRATE.defaultCreativeTab(ModCreativeModTabs.MOD_TAB.getKey());
    }

    public static final FluidEntry<? extends VirtualFluid>
            MOLTEN_COPPER = Main.REGISTRATE.extendedVirtualFluid("molten_copper", MoltenCopperFluid::new)
                    .lang("Molten Copper")
                    .tag(ModTags.forgeFluidTag("molten_metals"), ModTags.moltenCoppersTag())
                    .register(),
            MOLTEN_BRASS = Main.REGISTRATE.extendedVirtualFluid("molten_brass", MoltenBrassFluid::new)
                    .lang("Molten Brass")
                    .tag(ModTags.forgeFluidTag("molten_metals"), ModTags.moltenBrassesTag())
                    .register(),
            MOLTEN_IRON = Main.REGISTRATE.extendedVirtualFluid("molten_iron", MoltenIronFluid::new)
                    .lang("Molten Iron")
                    .tag(ModTags.moltenFluidsTag(), ModTags.moltenIronTag())
                    .register(),
            MOLTEN_ALUMINUM = Main.REGISTRATE.extendedVirtualFluid("molten_aluminum", MoltenAluminumFluid::new)
                    .lang("Molten Aluminum")
                    .tag(ModTags.moltenFluidsTag(), ModTags.moltenAluminumTag())
                    .register(),
            MOLTEN_METAL_ALLOY = Main.REGISTRATE.extendedVirtualFluid("molten_metal_alloy", MoltenMetalAlloyFluid::new)
                    .lang("Molten Metal Alloy")
                    .tag(ModTags.forgeFluidTag("molten_metals"))
                    .register(),
            MOLTEN_STRONG_ALUMINUM_ALLOY = Main.REGISTRATE.extendedVirtualFluid("molten_strong_aluminum_alloy", MoltenStrongAluminumFluid::new)
                    .lang("Molten Strong Aluminum Alloy")
                    .tag(ModTags.forgeFluidTag("molten_metals"))
                    .register();

    public static void register() {
        // Do not delete; for loading the class
    }
}
