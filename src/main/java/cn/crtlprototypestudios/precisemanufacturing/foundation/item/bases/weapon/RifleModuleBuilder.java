package cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.weapon;

import cn.crtlprototypestudios.precisemanufacturing.foundation.item.util.ModuleBuilder;

import java.util.ArrayList;

public class RifleModuleBuilder extends ModuleBuilder<RifleModuleType> {

    public RifleModuleBuilder(){
        super();
    }

    public RifleModuleBuilder(RifleModuleType... modules) {
        super(modules);
    }

    public RifleModuleBuilder(ArrayList<RifleModuleType> modules) {
        super(modules);
    }

    public RifleModuleBuilder(RifleModuleBuilder module) {
        super(module);
    }
}
