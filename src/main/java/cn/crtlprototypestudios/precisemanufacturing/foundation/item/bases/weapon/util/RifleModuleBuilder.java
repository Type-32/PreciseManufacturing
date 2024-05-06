package cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.weapon.util;

import java.util.ArrayList;
import java.util.Arrays;

public class RifleModuleBuilder {

    private ArrayList<RifleModuleType> modules;

    public RifleModuleBuilder(){
        modules = new ArrayList<>();
    }

    public RifleModuleBuilder(RifleModuleType... modules){
        this.modules = new ArrayList<>(Arrays.asList(modules));
    }

    public RifleModuleBuilder(ArrayList<RifleModuleType> modules){
        this.modules = modules;
    }

    public RifleModuleType[] get(){
        return modules.toArray(new RifleModuleType[0]);
    }

    public RifleModuleBuilder add(RifleModuleType module){
        modules.add(module);
        return this;
    }

    public RifleModuleBuilder remove(RifleModuleType module){
        modules.remove(module);
        return this;
    }
}
