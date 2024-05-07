package cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.weapon;

import cn.crtlprototypestudios.precisemanufacturing.Main;
import cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.weapon.util.RifleModuleBuilder;
import cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.weapon.util.RifleModuleType;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

import java.util.Hashtable;

// TODO Write Documentations
public class RifleBase extends WeaponBase {
    public static final RifleModuleBuilder
            STANDARD_RIFLE_MODULES = new RifleModuleBuilder(
                    RifleModuleType.GRIP,
                    RifleModuleType.LOWER_RECEIVER,
                    RifleModuleType.UPPER_RECEIVER,
                    RifleModuleType.HANDGUARD,
                    RifleModuleType.BARREL,
                    RifleModuleType.MAGAZINE,
                    RifleModuleType.FIRE_CONTROL_GROUP,
                    RifleModuleType.FIRE_SELECTOR,
                    RifleModuleType.TRIGGER,
                    RifleModuleType.STOCK
            ),
            BULLPUP_RIFLE_MODULES = new RifleModuleBuilder(
                    RifleModuleType.GRIP,
                    RifleModuleType.BULLPUP_BODY,
                    RifleModuleType.MAGAZINE,
                    RifleModuleType.FIRE_CONTROL_GROUP,
                    RifleModuleType.GRIP,
                    RifleModuleType.TRIGGER,
                    RifleModuleType.BULLPUP_BODY

                    // To Future me: Do not add an UNFINISHED type in the Rifle Module Types. Autonomous Rifle Module
                    // registration works differently compared to Cartridge Autonomous registrations, as each Cartridge
                    // uses only one Unfinished item variant to represent the modules of the Cartridge during the
                    // sequenced assemblies, while Rifle Modules has an unfinished item variant for each rifle module,
                    // as each module should only be crafted via a sequenced assembly.
            );


    public Hashtable<RifleModuleType, RegistryEntry<Item>> registry = new Hashtable<>();

    public RifleBase(String coreId, Item.Properties properties, RifleModuleBuilder moduleBuilder) {
        super(coreId, properties.stacksTo(1));

        // Put all the registered modules in the Hashtable for later use
        for (RifleModuleType i : moduleBuilder.get()) {
            registry.put(i, registerModule(coreId, i, properties));
        }
    }

    /**
     * The <code>RifleBase</code> constructor essentially creates the modules that are included in the <code>RifleModuleBuilder</code>
     * and their unfinished variants into items.
     * @param coreId The main id of the modules' parent gun, i.e. "m4a1"
     * @param moduleBuilder The modules stored in <code>RifleModuleBuilder</code>
     * @implNote The corresponding item, if the <code>coreId = "m4a1"</code> and the <code>moduleBuilder = new RifleModuleBuilder(RifleModuleType.LOWER_RECEIVER)</code>,
     * two of these item entries will be generated:
     * <ul>
     *     <li><code>prma:m4a1_lower_receiver_unfinished</code></li>
     *     <li><code>prma:m4a1_lower_receiver</code></li>
     * </ul>
     * <br>
     * These item entries will have the item model resource location of:
     * <ul>
     *     <li><code>prma:item/weapons/guns/m4a1/m4a1_lower_receiver_unfinished</code></li>
     *     <li><code>prma:item/weapons/guns/m4a1/m4a1_lower_receiver</code></li>
     * </ul>
     */
    public RifleBase(String coreId, RifleModuleBuilder moduleBuilder) {
        this(coreId, new Item.Properties(), moduleBuilder);
    }

    public RifleBase(String coreId, RifleModuleType... modules) {
        this(coreId, new RifleModuleBuilder(modules));
    }

    /**
     * The <code>registerModule()</code> function registers the Gun Assembly Module Items. Each Module will also have a
     * corresponding "unfinished" variant of the item registered.
     *
     * @param id The id of the module's assembled gun, i.e. "m4a1"
     * @param module The module id of the gun's module, i.e. "lower_receiver"
     * @param properties The properties of the module
     * @return A registry entry of the module item, not the unfinished module variant's module
     *
     * @implNote The corresponding item, if the <code>id = "m4a1"</code> and <code>moduleId = "lower_receiver"</code>,
     * two of these item entries will be generated:
     * <ul>
     *     <li><code>prma:m4a1_lower_receiver_unfinished</code></li>
     *     <li><code>prma:m4a1_lower_receiver</code></li>
     * </ul>
     * <br>
     * These item entries will have the item model resource location of:
     * <ul>
     *     <li><code>prma:item/weapons/guns/m4a1/m4a1_lower_receiver_unfinished</code></li>
     *     <li><code>prma:item/weapons/guns/m4a1/m4a1_lower_receiver</code></li>
     * </ul>
     */
    private RegistryEntry<Item> registerModule(String id, RifleModuleType module, Item.Properties properties) {
        String name = String.format("%s_%s", id, module.toString());

        // Register the unfinished module variant, and make it invisible in the creative tab
        Main.REGISTRATE.item(name + "_unfinished", Item::new).model((c, p) -> p.getExistingFile(p.modLoc(String.format("item/weapons/guns/%s/%s", id, name)))).properties(p -> properties.tab(CreativeModeTab.TAB_SEARCH)).register();

        // Register the module
        return Main.REGISTRATE.item(name, Item::new).model((c, p) -> p.getExistingFile(p.modLoc(String.format("item/weapons/guns/%s/%s", id, name)))).properties(p -> properties).register();
    }
}
