package cn.crtlprototypestudios.prma.foundation.legacy.item.bases.ammunition;

import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.item.Item;

public class AmmunitionItems {
    public RegistryEntry<Item> item, cast, blueprint;
    public AmmunitionItems(RegistryEntry<Item> item, RegistryEntry<Item> cast, RegistryEntry<Item> blueprint) {
        this.item = item;
        this.cast = cast;
        this.blueprint = blueprint;
    }
}
