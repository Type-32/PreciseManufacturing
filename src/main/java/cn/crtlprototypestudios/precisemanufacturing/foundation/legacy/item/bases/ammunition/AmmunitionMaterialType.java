package cn.crtlprototypestudios.precisemanufacturing.foundation.legacy.item.bases.ammunition;

public enum AmmunitionMaterialType {
    CASING("casing"),
    HEAD("head"),
    PELLETS("pellets"),
    UNFINISHED("unfinished");

    private final String moduleId;

    AmmunitionMaterialType(String moduleId) {
        this.moduleId = moduleId;
    }

    @Override
    public String toString() {
        return moduleId;
    }
}
