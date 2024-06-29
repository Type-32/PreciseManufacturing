package cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.ammunition;

import cn.crtlprototypestudios.precisemanufacturing.foundation.ModFluids;
import cn.crtlprototypestudios.precisemanufacturing.foundation.ModTags;
import cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.ModuleBase;
import com.simibubi.create.content.fluids.VirtualFluid;
import com.tterrag.registrate.util.entry.FluidEntry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class CartridgeModule extends ModuleBase<CartridgeModuleType, CartridgeModule.Data> {

    public CartridgeModule(CartridgeModuleType type, Data data) {
        super(type, data);
    }

    @Override
    public CartridgeModule setType(CartridgeModuleType type) {
        return new CartridgeModule(type, getData());
    }

    @Override
    public CartridgeModule setData(Data data) {
        return new CartridgeModule(getType(), data);
    }

    @Override
    public CartridgeModule setData(Consumer<Data> dataConsumer) {
        Data newData = getData();
        dataConsumer.accept(newData);
        return new CartridgeModule(getType(), newData);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CartridgeModule) {
            CartridgeModule other = (CartridgeModule) obj;
            return getType().equals(other.getType());
        } else if (obj instanceof CartridgeModuleType) {
            CartridgeModuleType other = (CartridgeModuleType) obj;
            return getType().equals(other);
        }
        return false;
    }

    public static class Data {
        private int fillingAmount;
        private @NotNull TagKey<Fluid> fillingFluid;

        public Data(int fillingAmount, TagKey<Fluid> fillingFluid){
            this.fillingAmount = fillingAmount;
            this.fillingFluid = fillingFluid;
        }

        public int getFillingAmount() {
            return fillingAmount;
        }

        public Data setFillingAmount(int fillingAmount) {
            return new Data(fillingAmount, fillingFluid);
        }

        public TagKey<Fluid> getFillingFluid() {
            return fillingFluid;
        }

        public Data setFillingFluid(TagKey<Fluid> fillingFluid) {
            return new Data(fillingAmount, fillingFluid);
        }
    }

    public static final CartridgeModule
        CASING_MODULE = new CartridgeModule(CartridgeModuleType.CASING, new Data(100, ModTags.moltenBrassesTag())),
        HEAD_MODULE = new CartridgeModule(CartridgeModuleType.HEAD, new Data(80, ModTags.moltenCoppersTag())),
        PELLET_MODULE = new CartridgeModule(CartridgeModuleType.PELLET, new Data(60, ModTags.moltenIronsTag())),
        UNFINISHED_MODULE = new CartridgeModule(CartridgeModuleType.UNFINISHED, new Data(0, ModTags.moltenBrassesTag()));
}
