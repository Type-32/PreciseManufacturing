package cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.ammunition;

import cn.crtlprototypestudios.precisemanufacturing.foundation.item.util.ModuleBuilder;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CartridgeModuleBuilder extends ModuleBuilder<CartridgeModuleType> {

    public CartridgeModuleBuilder(){
        super();
    }

    public CartridgeModuleBuilder(CartridgeModuleType... modules){
        super(modules);
    }

    public CartridgeModuleBuilder(ArrayList<CartridgeModuleType> modules){
        super(modules);
    }

    public CartridgeModuleBuilder(CartridgeModuleBuilder builder){
        super(builder);
    }

}
