package cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.weapon.util;

public enum RifleModuleType {
    LOWER_RECEIVER("lower_receiver"),
    UPPER_RECEIVER("upper_receiver"),
    HANDGUARD("handguard"),
    STOCK("stock"),
    MAGAZINE("magazine"),
    BARREL("barrel"),
    GRIP("grip"),
    TRIGGER("trigger"),
    FIRE_CONTROL_GROUP("fire_control_group"),
    PUMP("pump"),
    FIRE_SELECTOR("fire_selector"),
    CARTRIDGE_WELL("cartridge_well"),
    SHELL_TUBE("shell_tube"),
    BULLPUP_BODY("bullpup_body"),
    RECEIVER("receiver"),
    BOLT("bolt");

    private final String moduleId;

    RifleModuleType(String moduleId) {
        this.moduleId = moduleId;
    }

    @Override
    public String toString() {
        return moduleId;
    }
}
