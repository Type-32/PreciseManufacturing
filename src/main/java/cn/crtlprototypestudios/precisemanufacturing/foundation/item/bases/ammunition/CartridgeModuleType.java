package cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.ammunition;

import cn.crtlprototypestudios.precisemanufacturing.foundation.ModFluids;
import com.simibubi.create.content.fluids.VirtualFluid;

public enum CartridgeModuleType {
    CASING("casing"),
    HEAD("head"),
    PELLET("pellet"),
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
