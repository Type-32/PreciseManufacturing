package cn.crtlprototypestudios.prma.foundation.neo.complex.content.item.type.standard;

public enum AmmoCasingType {
    Small("small"),
    Medium("medium"),
    Long("long"),
    Shell("shell");
    private final String name;
    AmmoCasingType(String name) {
        this.name = name;
    }
    @Override
    public String toString(){
        return name;
    }
}
