package cn.crtlprototypestudios.prma.foundation.neo.simple.content.type.standard;

public enum SimpleAmmoGunpowderAmountStandard {
    Low("low"),
    Medium("medium"),
    High("high"),
    HighPower("hp");
    private final String name;
    SimpleAmmoGunpowderAmountStandard(String name) {
        this.name = name;
    }
    @Override
    public String toString(){
        return name;
    }
}
