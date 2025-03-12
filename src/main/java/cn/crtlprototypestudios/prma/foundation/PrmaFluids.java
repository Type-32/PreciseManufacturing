package cn.crtlprototypestudios.prma.foundation;

import cn.crtlprototypestudios.prma.PreciseManufacturing;
import cn.crtlprototypestudios.prma.foundation.utility.ResourceHelper;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.FluidEntry;
import net.minecraft.resources.ResourceLocation;

public class PrmaFluids {
    static {
        // Set that all registered Items and Blocks defaults under this Mod's creative tabs
        PreciseManufacturing.REGISTRATE.defaultCreativeTab(PrmaCreativeModTabs.MOD_TAB.getKey());
    }

//    public static final ResourceLocation
//            MOLTEN_COPPER_ID_STILL = ResourceHelper.find("fluid/molten_copper_still"),
//            MOLTEN_COPPER_ID_FLOW = ResourceHelper.find("fluid/molten_copper_flow"),
//            MOLTEN_BRASS_ID_STILL = ResourceHelper.find("fluid/molten_brass_still"),
//            MOLTEN_BRASS_ID_FLOW = ResourceHelper.find("fluid/molten_brass_flow"),
//            MOLTEN_ZINC_ID_STILL = ResourceHelper.find("fluid/molten_zinc_still"),
//            MOLTEN_ZINC_ID_FLOW = ResourceHelper.find("fluid/molten_zinc_flow"),
//            MOLTEN_IRON_ID_STILL = ResourceHelper.find("fluid/molten_iron_still"),
//            MOLTEN_IRON_ID_FLOW = ResourceHelper.find("fluid/molten_iron_flow"),
//            MOLTEN_ALUMINUM_ID_STILL = ResourceHelper.find("fluid/molten_aluminum_still"),
//            MOLTEN_ALUMINUM_ID_FLOW = ResourceHelper.find("fluid/molten_aluminum_flow"),
//            MOLTEN_METAL_ALLOY_ID_STILL = ResourceHelper.find("fluid/molten_metal_alloy_still"),
//            MOLTEN_METAL_ALLOY_ID_FLOW = ResourceHelper.find("fluid/molten_metal_alloy_flow"),
//            MOLTEN_STRONG_ALUMINUM_ALLOY_ID_STILL = ResourceHelper.find("fluid/molten_strong_aluminum_alloy_still"),
//            MOLTEN_STRONG_ALUMINUM_ALLOY_ID_FLOW = ResourceHelper.find("fluid/molten_strong_aluminum_alloy_flow");
//
//    public static final FluidEntry<MoltenCopperFluid> MOLTEN_COPPER = PreciseManufacturing.REGISTRATE.virtualFluid("molten_copper",
//                    MOLTEN_COPPER_ID_STILL, MOLTEN_COPPER_ID_FLOW,
//                    CreateRegistrate::defaultFluidType, MoltenCopperFluid::new)
//            .lang("Molten Copper")
//            .properties(b -> b.lightLevel(15).density(3000).viscosity(6000).canPushEntity(true).canDrown(true))
//            .tag(PrmaTags.FluidTag.MOLTEN_METALS.tag, PrmaTags.FluidTag.MOLTEN_COPPER_FLUIDS.tag)
//            .register();
//
//    public static final FluidEntry<MoltenZincFluid> MOLTEN_ZINC = PreciseManufacturing.REGISTRATE.virtualFluid("molten_zinc",
//                    MOLTEN_ZINC_ID_STILL, MOLTEN_ZINC_ID_FLOW,
//                    CreateRegistrate::defaultFluidType, MoltenZincFluid::new)
//            .lang("Molten Zinc")
//            .properties(b -> b.lightLevel(15).density(3000).viscosity(6000).canPushEntity(true).canDrown(true))
//            .tag(PrmaTags.FluidTag.MOLTEN_METALS.tag, PrmaTags.FluidTag.MOLTEN_ZINC_FLUIDS.tag)
//            .register();
//
//    public static final FluidEntry<MoltenIronFluid> MOLTEN_IRON = PreciseManufacturing.REGISTRATE.virtualFluid("molten_iron",
//                    MOLTEN_IRON_ID_STILL, MOLTEN_IRON_ID_FLOW,
//                    CreateRegistrate::defaultFluidType, MoltenIronFluid::new)
//            .lang("Molten Iron")
//            .properties(b -> b.lightLevel(15).density(3000).viscosity(6000).canPushEntity(true).canDrown(true))
//            .tag(PrmaTags.FluidTag.MOLTEN_METALS.tag, PrmaTags.FluidTag.MOLTEN_IRON_FLUIDS.tag)
//            .register();
//
//    public static final FluidEntry<MoltenAluminumFluid> MOLTEN_ALUMINUM = PreciseManufacturing.REGISTRATE.virtualFluid("molten_aluminum",
//                    MOLTEN_ALUMINUM_ID_STILL, MOLTEN_ALUMINUM_ID_FLOW,
//                    CreateRegistrate::defaultFluidType, MoltenAluminumFluid::new)
//            .lang("Molten Aluminum")
//            .properties(b -> b.lightLevel(15).density(3000).viscosity(6000).canPushEntity(true).canDrown(true))
//            .tag(PrmaTags.FluidTag.MOLTEN_METALS.tag, PrmaTags.FluidTag.MOLTEN_ALUMINUM_FLUIDS.tag)
//            .register();
//
//    public static final FluidEntry<MoltenMetalAlloyFluid> MOLTEN_METAL_ALLOY = PreciseManufacturing.REGISTRATE.virtualFluid("molten_metal_alloy",
//                    MOLTEN_METAL_ALLOY_ID_STILL, MOLTEN_METAL_ALLOY_ID_FLOW,
//                    CreateRegistrate::defaultFluidType, MoltenMetalAlloyFluid::new)
//            .lang("Molten Metal Alloy")
//            .properties(b -> b.lightLevel(15).density(3000).viscosity(6000).canPushEntity(true).canDrown(true))
//            .tag(PrmaTags.FluidTag.MOLTEN_METALS.tag, PrmaTags.FluidTag.MOLTEN_METAL_ALLOY_FLUIDS.tag)
//            .register();
//
//    public static final FluidEntry<MoltenStrongAluminumFluid> MOLTEN_STRONG_ALUMINUM_ALLOY = PreciseManufacturing.REGISTRATE.virtualFluid("molten_strong_aluminum_alloy",
//                    MOLTEN_STRONG_ALUMINUM_ALLOY_ID_STILL, MOLTEN_STRONG_ALUMINUM_ALLOY_ID_FLOW,
//                    CreateRegistrate::defaultFluidType, MoltenStrongAluminumFluid::new)
//            .lang("Molten Strong Aluminum Alloy")
//            .properties(b -> b.lightLevel(15).density(3000).viscosity(6000).canPushEntity(true).canDrown(true))
//            .tag(PrmaTags.FluidTag.MOLTEN_METALS.tag, PrmaTags.FluidTag.MOLTEN_STRONG_ALUMINUM_ALLOY_FLUIDS.tag)
//            .register(); //灵感来源: LDT的LDX 7075波壳, 就是7075铝合金

    public static void register() {
        // Do not delete; for loading the class
    }
}
