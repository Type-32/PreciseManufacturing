package cn.crtlprototypestudios.precisemanufacturing.foundation.util;

import cn.crtlprototypestudios.precisemanufacturing.foundation.ModCreativeModTabs;
import com.simibubi.create.content.fluids.VirtualFluid;
import com.simibubi.create.foundation.data.CreateBlockEntityBuilder;
import com.simibubi.create.foundation.data.VirtualFluidBuilder;
import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.builders.BlockEntityBuilder;
import com.tterrag.registrate.builders.FluidBuilder;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.function.Consumer;

public class PreciseManufacturingRegistrate extends AbstractRegistrate<PreciseManufacturingRegistrate> {
    /**
     * Construct a new Registrate for the given mod ID.
     *
     * @param modid The mod ID for which objects will be registered
     */
    protected PreciseManufacturingRegistrate(String modid) {
        super(modid);
//        set(ModCreativeModTabs.MOD_TAB.get());
    }

    public static PreciseManufacturingRegistrate create(String modid) {
        return new PreciseManufacturingRegistrate(modid).registerEventListeners(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public FluidBuilder<VirtualFluid, PreciseManufacturingRegistrate> virtualFluid(String name) {
        return entry(name,
                c -> new VirtualFluidBuilder<VirtualFluid, PreciseManufacturingRegistrate>(self(), self(), name, c, new ResourceLocation(getModid(), "fluid/" + name + "_still"),
                        new ResourceLocation(getModid(), "fluid/" + name + "_flow"), null, VirtualFluid::new));
    }

    public <T extends VirtualFluid> FluidBuilder<T, PreciseManufacturingRegistrate> extendedVirtualFluid(
            String name,
            NonNullFunction<ForgeFlowingFluid.Properties, T> fluidFactory
    ) {
        return entry(name,
                c -> new VirtualFluidBuilder<>(self(), self(), name, c, new ResourceLocation(getModid(), "fluid/" + name + "_still"),
                        new ResourceLocation(getModid(), "fluid/" + name + "_flow"), PreciseManufacturingRegistrate::defaultFluidType, fluidFactory));
    }

    public static FluidType defaultFluidType(FluidType.Properties properties, ResourceLocation stillTexture,
                                             ResourceLocation flowingTexture) {
        return new FluidType(properties) {
            @Override
            public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
                consumer.accept(new IClientFluidTypeExtensions() {
                    @Override
                    public ResourceLocation getStillTexture() {
                        return stillTexture;
                    }

                    @Override
                    public ResourceLocation getFlowingTexture() {
                        return flowingTexture;
                    }
                });
            }
        };
    }
}
