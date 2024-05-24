package cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.weapon;

import cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.ModuleBase;
import cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.ammunition.CartridgeModule;

import java.util.function.Consumer;

public class RifleModule extends ModuleBase<RifleModuleType, RifleModule.Data> {

    public RifleModule(RifleModuleType type, Data data){
        super(type, data);
    }

    @Override
    public RifleModule setData(Consumer<RifleModule.Data> dataConsumer) {
        Data newData = getData();
        dataConsumer.accept(newData);
        return new RifleModule(getType(), newData);
    }

    public static class Data {
        private int decompTime, castFillingAmount, castCuttingTime;
        public Data(int decompTime, int castFillingAmount, int castCuttingTime){
            this.decompTime = decompTime;
            this.castFillingAmount = castFillingAmount;
            this.castCuttingTime = castCuttingTime;
        }

        public int getDecompTime() {
            return decompTime;
        }

        public Data setDecompTime(int decompTime) {
            return new Data(decompTime, castFillingAmount, castCuttingTime);
        }

        public int getCastFillingAmount() {
            return castFillingAmount;
        }

        public Data setCastFillingAmount(int castFillingAmount) {
            return new Data(decompTime, castFillingAmount, castCuttingTime);
        }

        public int getCastCuttingTime() {
            return castCuttingTime;
        }

        public Data setCastCuttingTime(int castCuttingTime) {
            return new Data(decompTime, castFillingAmount, castCuttingTime);
        }
    }

    public static final RifleModule
            // decompTime: 1000~6000 Ticks; castFillingAmount: 100~1000mb; castCuttingTime: 600~3500 Time Notches;
            LOWER_RECEIVER_MODULE = new RifleModule(RifleModuleType.LOWER_RECEIVER, new Data(6000, 600, 1000)),
            UPPER_RECEIVER_MODULE = new RifleModule(RifleModuleType.UPPER_RECEIVER, new Data(5500, 580, 1400)),
            LONG_BODY_MODULE = new RifleModule(RifleModuleType.LONG_BODY, new Data(4800, 750, 2600)),
            HANDGUARD_MODULE = new RifleModule(RifleModuleType.HANDGUARD, new Data(4000, 650, 2600)),
            STOCK_MODULE = new RifleModule(RifleModuleType.STOCK, new Data(3000, 600, 2800)),
            MAGAZINE_MODULE = new RifleModule(RifleModuleType.MAGAZINE, new Data(3200, 400, 2000)),
            BARREL_MODULE = new RifleModule(RifleModuleType.BARREL, new Data(1000, 400, 2400)),
            GRIP_MODULE = new RifleModule(RifleModuleType.GRIP, new Data(3000, 200, 800)),
            TRIGGER_MODULE = new RifleModule(RifleModuleType.TRIGGER, new Data(2000, 120, 1000)),
            FIRE_CONTROL_GROUP_MODULE = new RifleModule(RifleModuleType.FIRE_CONTROL_GROUP, new Data(5300, 480, 2800)),
            PUMP_MODULE = new RifleModule(RifleModuleType.PUMP, new Data(3000, 280, 2000)),
            FIRE_SELECTOR_MODULE = new RifleModule(RifleModuleType.FIRE_SELECTOR, new Data(1600, 100, 700)),
            CARTRIDGE_WELL_MODULE = new RifleModule(RifleModuleType.CARTRIDGE_WELL, new Data(2400, 300, 1000)),
            SHELL_TUBE_MODULE = new RifleModule(RifleModuleType.SHELL_TUBE, new Data(1600, 150, 1800)),
            BULLPUP_BODY_MODULE = new RifleModule(RifleModuleType.BULLPUP_BODY, new Data(5200, 750, 3400)),
            RECEIVER_MODULE = new RifleModule(RifleModuleType.RECEIVER, new Data(4800, 680, 3200)),
            BOLT_MODULE = new RifleModule(RifleModuleType.BOLT, new Data(1200, 110, 500));
}
