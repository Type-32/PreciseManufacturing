package cn.crtlprototypestudios.prma.foundation.neo.complex.content.item.type.standard;

public enum AmmoSizeType {
    Small("small"),
    Medium("medium"),
    Long("long");

    private final String name;
    AmmoSizeType(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }
}
