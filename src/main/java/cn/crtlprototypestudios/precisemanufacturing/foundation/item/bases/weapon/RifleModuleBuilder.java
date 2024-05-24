package cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.weapon;

import cn.crtlprototypestudios.precisemanufacturing.foundation.item.util.ModuleBuilder;

import java.util.ArrayList;

public class RifleModuleBuilder extends ModuleBuilder<RifleModule> {

    public RifleModuleBuilder(){
        super();
    }

    public RifleModuleBuilder(RifleModule... modules) {
        super(modules);
    }

    public RifleModuleBuilder(ArrayList<RifleModule> modules) {
        super(modules);
    }

    public RifleModuleBuilder(RifleModuleBuilder module) {
        super(module);
    }
}
