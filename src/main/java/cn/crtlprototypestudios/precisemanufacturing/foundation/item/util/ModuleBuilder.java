package cn.crtlprototypestudios.precisemanufacturing.foundation.item.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModuleBuilder<T> {
    protected ArrayList<T> modules;

    public ModuleBuilder() {
        modules = new ArrayList<>();
    }

    @SafeVarargs
    public ModuleBuilder(T... modules) {
        this.modules = new ArrayList<>(Arrays.asList(modules));
    }

    public ModuleBuilder(ArrayList<T> modules) {
        this.modules = new ArrayList<>(modules);
    }

    public ModuleBuilder(ModuleBuilder<T> builder) {
        this.modules = new ArrayList<>(List.of(builder.get()));
    }

    public T[] get() {
        return modules.toArray((T[]) Array.newInstance(modules.get(0).getClass(), 0));
    }

    public ModuleBuilder<T> add(T module) {
        ArrayList<T> newModules = new ArrayList<>(modules);
        newModules.add(module);
        return new ModuleBuilder<>(newModules);
    }

    public ModuleBuilder<T> remove(T module) {
        ArrayList<T> newModules = new ArrayList<>(modules);
        newModules.remove(module);
        return new ModuleBuilder<>(newModules);
    }
}