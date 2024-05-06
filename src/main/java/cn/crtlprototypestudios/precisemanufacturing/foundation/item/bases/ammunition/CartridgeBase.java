package cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.ammunition;

import cn.crtlprototypestudios.precisemanufacturing.Main;
import cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.ammunition.util.CartridgeModuleBuilder;
import cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.ammunition.util.CartridgeModuleType;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.item.Item;

import java.util.Hashtable;

public class CartridgeBase extends AmmunitionBase {
    public Hashtable<CartridgeModuleType, RegistryEntry<Item>> registry;

    public final static CartridgeModuleBuilder
            STANDARD_CARTRIDGE = new CartridgeModuleBuilder(
                    CartridgeModuleType.CASING,
                    CartridgeModuleType.CASING_CAST,
                    CartridgeModuleType.HEAD,
                    CartridgeModuleType.HEAD_CAST,
                    CartridgeModuleType.UNFINISHED
            ),
            SHOTGUN_CARTRIDGE = new CartridgeModuleBuilder(
                    CartridgeModuleType.CASING,
                    CartridgeModuleType.CASING_CAST,
                    CartridgeModuleType.PELLET,
                    CartridgeModuleType.PELLET_CAST,
                    CartridgeModuleType.UNFINISHED
            );

    public CartridgeBase(String coreId, CartridgeModuleBuilder moduleBuilder) {
        super(coreId);
        registry = new Hashtable<>();

        for (CartridgeModuleType type : moduleBuilder.get()) {
            registry.put(type, registerModule(coreId, type));
        }
    }

    public CartridgeBase(String coreId, CartridgeModuleType... modules){
        this(coreId, new CartridgeModuleBuilder(modules));
    }

    public CartridgeBase(String coreId) {
        this(coreId, STANDARD_CARTRIDGE);
    }

    private RegistryEntry<Item> registerModule(String id, CartridgeModuleType module) {
        return Main.REGISTRATE.item(String.format("%s_%s", id, module.toString()), Item::new).register();
    }
}
