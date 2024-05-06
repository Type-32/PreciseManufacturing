package cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.ammunition;

import cn.crtlprototypestudios.precisemanufacturing.Main;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.item.Item;

public class ShotgunShellBase extends AmmunitionBase {
    public RegistryEntry<Item> CASING_CAST, PELLET_CAST, CASING, PELLET, UNFINISHED;
    public ShotgunShellBase(String coreId) {
        super(coreId);
        CASING_CAST = Main.REGISTRATE.item(String.format("%s_casing_cast", coreId), Item::new).properties(p -> p.fireResistant()).register();
        PELLET_CAST = Main.REGISTRATE.item(String.format("%s_pellet_cast", coreId), Item::new).properties(p -> p.fireResistant()).register();
        CASING = Main.REGISTRATE.item(String.format("%s_casing", coreId), Item::new).register();
        PELLET = Main.REGISTRATE.item(String.format("%s_head", coreId), Item::new).register();
        UNFINISHED = Main.REGISTRATE.item(String.format("%s_unfinished", coreId), Item::new).register();
    }
}
