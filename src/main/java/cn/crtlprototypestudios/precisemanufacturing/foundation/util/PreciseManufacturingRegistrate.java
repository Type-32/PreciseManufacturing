package cn.crtlprototypestudios.precisemanufacturing.foundation.util;

import cn.crtlprototypestudios.precisemanufacturing.foundation.fluid.MoltenBrassFluid;
import cn.crtlprototypestudios.precisemanufacturing.foundation.item.ModCreativeModTabs;
import com.simibubi.create.content.fluids.VirtualFluid;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.VirtualFluidBuilder;
import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.builders.FluidBuilder;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.function.Supplier;

public class PreciseManufacturingRegistrate extends AbstractRegistrate<PreciseManufacturingRegistrate> {
    /**
     * Construct a new Registrate for the given mod ID.
     *
     * @param modid The mod ID for which objects will be registered
     */
    protected PreciseManufacturingRegistrate(String modid) {
        super(modid);
        creativeModeTab(() -> ModCreativeModTabs.MOD_TAB);
    }

    public static PreciseManufacturingRegistrate create(String modid) {
        return new PreciseManufacturingRegistrate(modid).registerEventListeners(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public FluidBuilder<VirtualFluid, PreciseManufacturingRegistrate> virtualFluid(String name) {
        return entry(name,
                c -> new VirtualFluidBuilder<>(self(), self(), name, c, new ResourceLocation(getModid(), "fluid/" + name + "_still"),
                        new ResourceLocation(getModid(), "fluid/" + name + "_flow"), null, VirtualFluid::new));
    }

    public <T extends VirtualFluid> FluidBuilder<T, PreciseManufacturingRegistrate> extendedVirtualFluid(
            String name,
            NonNullFunction<ForgeFlowingFluid.Properties, T> fluidFactory
    ) {
        return entry(name,
                c -> new VirtualFluidBuilder<>(self(), self(), name, c, new ResourceLocation(getModid(), "fluid/" + name + "_still"),
                        new ResourceLocation(getModid(), "fluid/" + name + "_flow"), null, fluidFactory));
    }
}
