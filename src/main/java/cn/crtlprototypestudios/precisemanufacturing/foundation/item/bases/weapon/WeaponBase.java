package cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.weapon;

import net.minecraft.world.item.Item;

public class WeaponBase {
    private final String coreId;
    private final Item.Properties properties;
    public WeaponBase(String coreId, Item.Properties properties) {
        this.coreId = coreId;
        this.properties = properties;
    }

    public String getCoreId() {
        return coreId;
    }

    public Item.Properties getProperties() {
        return properties;
    }
}
