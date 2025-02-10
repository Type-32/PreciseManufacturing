package cn.crtlprototypestudios.prma.foundation.legacy.item.bases.ammunition;

public enum AmmunitionSize {
    SMALL("small"), // Small Cartridges
    MEDIUM("medium"), // Rifle Cartridges
    LONG("long"), // Sniper Cartridges
    SHELL("shell"); // Shotgun Shells

    private final String moduleId;

    AmmunitionSize(String moduleId) {
        this.moduleId = moduleId;
    }

    @Override
    public String toString() {
        return moduleId;
    }
}
