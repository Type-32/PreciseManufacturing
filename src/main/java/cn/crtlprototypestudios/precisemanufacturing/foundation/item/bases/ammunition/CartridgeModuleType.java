package cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.ammunition;

public enum CartridgeModuleType {
    CASING("casing"),
    CASING_CAST("casing_cast"),
    HEAD("head"),
    HEAD_CAST("head_cast"),
    PELLET("pellet"),
    PELLET_CAST("pellet_cast"),
    UNFINISHED("unfinished");

    private final String moduleId;

    CartridgeModuleType(String moduleId) {
        this.moduleId = moduleId;
    }

    @Override
    public String toString() {
        return moduleId;
    }
}
