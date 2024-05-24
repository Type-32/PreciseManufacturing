package cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.ammunition;

import cn.crtlprototypestudios.precisemanufacturing.foundation.item.util.ModuleBuilder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class CartridgeModuleBuilder extends ModuleBuilder<CartridgeModule> {

    private List<CartridgeAssemblySequence> assemblySequence = new ArrayList<>();

    public CartridgeModuleBuilder(){
        super();
    }

    public CartridgeModuleBuilder(CartridgeModule[] modules, CartridgeAssemblySequence[] assemblySequence){
        super(modules);
        this.assemblySequence = Arrays.asList(assemblySequence);
    }

    public CartridgeModuleBuilder(ArrayList<CartridgeModule> modules, List<CartridgeAssemblySequence> assemblySequence){
        super(modules);
        this.assemblySequence = assemblySequence;
    }

    public CartridgeModuleBuilder(CartridgeModuleBuilder builder){
        super(builder);
    }

    public List<CartridgeAssemblySequence> getAssemblySequence() {
        return assemblySequence;
    }

    public CartridgeModuleBuilder setAssemblySequence(List<CartridgeAssemblySequence> assemblySequence) {
        return new CartridgeModuleBuilder(modules, assemblySequence);
    }

    public CartridgeModuleBuilder setAssemblySequence(CartridgeAssemblySequence... assemblySequence) {
        return new CartridgeModuleBuilder(modules, Arrays.asList(assemblySequence));
    }

    public CartridgeModuleBuilder clearAssemblySequence(){
        return new CartridgeModuleBuilder(modules, new ArrayList<>());
    }

    public CartridgeModuleBuilder addAssemblySequence(CartridgeAssemblySequence sequence){
        List<CartridgeAssemblySequence> temp = new ArrayList<>(assemblySequence);
        temp.add(sequence);
        return new CartridgeModuleBuilder(modules, temp);
    }

    public CartridgeModuleBuilder insertAssemblySequence(int index, CartridgeAssemblySequence sequence){
        List<CartridgeAssemblySequence> temp = new ArrayList<>(assemblySequence);
        temp.add(index, sequence);
        return new CartridgeModuleBuilder(modules, temp);
    }

    public CartridgeModuleBuilder replaceAssemblySequence(CartridgeAssemblySequence replaced, CartridgeAssemblySequence replacing){
        List<CartridgeAssemblySequence> temp = new ArrayList<>(assemblySequence);
        for(int i = 0; i < temp.size(); i++){
            if(temp.get(i).equals(replaced))
                temp.set(i, replacing);
        }
        return new CartridgeModuleBuilder(modules, temp);
    }

    public CartridgeModuleBuilder removeLastAssemblySequence(){
        List<CartridgeAssemblySequence> temp = new ArrayList<>(assemblySequence);
        temp.remove(temp.size() - 1);
        return new CartridgeModuleBuilder(modules, temp);
    }
}
