package cn.crtlprototypestudios.prma.foundation.neo.complex.content.item.type.standard;

public enum AmmoMaterialType {
    Brass("brass"),
    Copper("copper"),
    Iron("iron"),
    Plastic("plastic");
    private final String name;
    AmmoMaterialType(String name) {
        this.name = name;
    }
    @Override
    public String toString(){
        return name;
    }
}
