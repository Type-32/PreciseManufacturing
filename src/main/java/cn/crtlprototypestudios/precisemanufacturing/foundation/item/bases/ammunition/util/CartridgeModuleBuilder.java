package cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.ammunition.util;

import cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.weapon.util.RifleModuleBuilder;

import java.util.ArrayList;
import java.util.Arrays;

// TODO Write Documentations
public class CartridgeModuleBuilder {

    private ArrayList<CartridgeModuleType> modules;

    public CartridgeModuleBuilder(){
        modules = new ArrayList<>();
    }

    public CartridgeModuleBuilder(CartridgeModuleType... modules){
        this.modules = new ArrayList<>(Arrays.asList(modules));
    }

    public CartridgeModuleBuilder(ArrayList<CartridgeModuleType> modules){
        this.modules = modules;
    }

    public CartridgeModuleType[] get(){
        return modules.toArray(new CartridgeModuleType[0]);
    }

    public CartridgeModuleBuilder add(CartridgeModuleType module){
        modules.add(module);
        return this;
    }

    public CartridgeModuleBuilder remove(CartridgeModuleType module){
        modules.remove(module);
        return this;
    }

    public CartridgeModuleBuilder(CartridgeModuleBuilder builder){
        this(builder.get());
    }
}