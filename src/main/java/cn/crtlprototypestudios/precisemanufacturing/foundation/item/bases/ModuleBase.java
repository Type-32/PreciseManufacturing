package cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases;

import java.util.function.Consumer;

public class ModuleBase<T, D> {
    private T type;
    private D data;

    public ModuleBase(T type, D data){
        this.type = type;
        this.data = data;
    }

    public T getType() {
        return type;
    }

    public ModuleBase<T, D> setType(T type) {
        return new ModuleBase<>(type, data);
    }

    public D getData() {
        return data;
    }

    public ModuleBase<T, D> setData(D data) {
        return new ModuleBase<>(type, data);
    }

    public ModuleBase<T, D> setData(Consumer<D> dataConsumer) {
        D newData = getData();
        dataConsumer.accept(newData);
        return new ModuleBase<>(type, newData);
    }

    @Override
    public String toString() {
        return String.valueOf(type);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ModuleBase) {
            ModuleBase<?, ?> module = (ModuleBase<?, ?>) obj;
            return module.getType().equals(type);
        }

        return false;
    }
}
