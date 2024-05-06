package cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.ammunition;

import cn.crtlprototypestudios.precisemanufacturing.Main;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.item.Item;

public class CartridgeBase extends AmmunitionBase {
    public RegistryEntry<Item> CASING_CAST, HEAD_CAST, CASING, HEAD, UNFINISHED;
    public CartridgeBase(String coreId) {
        super(coreId);
        CASING_CAST = Main.REGISTRATE.item(String.format("%s_casing_cast", coreId), Item::new).properties(p -> p.fireResistant()).register();
        HEAD_CAST = Main.REGISTRATE.item(String.format("%s_head_cast", coreId), Item::new).properties(p -> p.fireResistant()).register();
        CASING = Main.REGISTRATE.item(String.format("%s_casing", coreId), Item::new).register();
        HEAD = Main.REGISTRATE.item(String.format("%s_head", coreId), Item::new).register();
        UNFINISHED = Main.REGISTRATE.item(String.format("%s_unfinished", coreId), Item::new).register();
    }
}
