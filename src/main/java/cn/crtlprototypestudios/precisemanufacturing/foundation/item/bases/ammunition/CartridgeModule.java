package cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.ammunition;

import cn.crtlprototypestudios.precisemanufacturing.foundation.ModFluids;
import cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.ModuleBase;
import com.simibubi.create.content.fluids.VirtualFluid;
import com.tterrag.registrate.util.entry.FluidEntry;
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

    public static class Data {
        private int fillingAmount;
        private @NotNull FluidEntry<? extends VirtualFluid> fillingFluid;

        public Data(int fillingAmount, FluidEntry<? extends VirtualFluid> fillingFluid){
            this.fillingAmount = fillingAmount;
            this.fillingFluid = fillingFluid;
        }

        public int getFillingAmount() {
            return fillingAmount;
        }

        public Data setFillingAmount(int fillingAmount) {
            return new Data(fillingAmount, fillingFluid);
        }

        public FluidEntry<? extends VirtualFluid> getFillingFluid() {
            return fillingFluid;
        }

        public Data setFillingFluid(FluidEntry<? extends VirtualFluid> fillingFluid) {
            return new Data(fillingAmount, fillingFluid);
        }
    }

    public static final CartridgeModule
        CASING_MODULE = new CartridgeModule(CartridgeModuleType.CASING, new Data(100, ModFluids.MOLTEN_BRASS)),
        HEAD_MODULE = new CartridgeModule(CartridgeModuleType.HEAD, new Data(80, ModFluids.MOLTEN_COPPER)),
        PELLET_MODULE = new CartridgeModule(CartridgeModuleType.PELLET, new Data(60, ModFluids.MOLTEN_BASALT_INFUSED_IRON)),
        UNFINISHED_MODULE = new CartridgeModule(CartridgeModuleType.UNFINISHED, new Data(0, ModFluids.MOLTEN_BRASS));
}
