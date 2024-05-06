package cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.ammunition;

import cn.crtlprototypestudios.precisemanufacturing.Main;
import cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.ammunition.util.CartridgeModuleBuilder;
import cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.ammunition.util.CartridgeModuleType;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.item.Item;

import java.util.Hashtable;

// TODO Write Documentations
public class CartridgeBase extends AmmunitionBase {
    public Hashtable<CartridgeModuleType, RegistryEntry<Item>> registry;

    public final static CartridgeModuleBuilder
            STANDARD_CARTRIDGE = new CartridgeModuleBuilder(
                    CartridgeModuleType.CASING,
                    CartridgeModuleType.CASING_CAST,
                    CartridgeModuleType.HEAD,
                    CartridgeModuleType.HEAD_CAST,
                    CartridgeModuleType.UNFINISHED
                    // To future me: Do not delete the UNFINISHED type. Cartridge Autonomous Registrations
                    // works differently than Rifle Registrations. Rifle Modules has unfinished item variants for each module,
                    // while Cartridge Modules doesn't and only requires one unfinished item variant to represent the whole
                    // cartridge item during assembly sequences.
            ),
            SHOTGUN_CARTRIDGE = new CartridgeModuleBuilder(
                    CartridgeModuleType.CASING,
                    CartridgeModuleType.CASING_CAST,
                    CartridgeModuleType.PELLET,
                    CartridgeModuleType.PELLET_CAST,
                    CartridgeModuleType.UNFINISHED
                    // To future me: Do not delete the UNFINISHED type. Cartridge Autonomous Registrations
                    // works differently than Rifle Registrations. Rifle Modules has unfinished item variants for each
                    // module, while Cartridge Modules doesn't and only requires one unfinished item variant to
                    // represent the whole cartridge item during assembly sequences.
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

    /**
     * The <code>registerModule()</code> function registers the Cartridge Assembly Module Items.
     *
     * @param id The id of the cartridge module's assembled cartridge, i.e. "9mm"
     * @param module The module id of the cartridge's module, i.e. "casing_cast"
     * @return A registry entry of the cartridge module item.
     *
     * @implNote The corresponding item, if the <code>id = "9mm"</code> and <code>moduleId = "casing_cast"</code>,
     * this item entry will be generated:
     * <ul>
     *     <li><code>prma:9mm_casing_cast</code></li>
     * </ul>
     * <br>
     * The item entry will have the item model resource location of:
     * <ul>
     *     <li><code>prma:item/cartridges/9mm/9mm_casing_cast</code></li>
     * </ul>
     */
    private RegistryEntry<Item> registerModule(String id, CartridgeModuleType module) {
        String name = String.format("%s_%s", id, module.toString());
        return Main.REGISTRATE.item(name, Item::new).model((c, p) -> p.getExistingFile(p.modLoc(String.format("item/cartridges/%s/%s", id, name)))).register();
    }
}
