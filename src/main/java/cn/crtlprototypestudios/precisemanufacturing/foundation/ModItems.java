package cn.crtlprototypestudios.precisemanufacturing.foundation;

import cn.crtlprototypestudios.precisemanufacturing.Main;
import cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.ammunition.CartridgeBase;
import cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.weapon.RifleBase;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

public class ModItems {

    static {
        // Set that all registered Items and Blocks defaults under this Mod's creative tabs
        Main.REGISTRATE.creativeModeTab(() -> ModCreativeModTabs.MOD_TAB);
    }


    // Powders
    public static final RegistryEntry<Item>
            RAW_ZINC_POWDER = Main.REGISTRATE.item("raw_zinc_powder", Item::new).register(),
            RAW_COPPER_POWDER = Main.REGISTRATE.item("raw_copper_powder", Item::new).register(),
            BASALT_POWDER = Main.REGISTRATE.item("basalt_powder",Item::new).register(),
            RAW_SULFUR_POWDER = Main.REGISTRATE.item("raw_sulfur_powder",Item::new).register(),
            GUNPOWDER_PELLETS = Main.REGISTRATE.item("gunpowder_pellets",Item::new).register();

    // Misc Items
    public static final RegistryEntry<Item>
            CRUSHED_BASALT = Main.REGISTRATE.item("crushed_basalt", Item::new).register(),
            UNFORMED_BASALT = Main.REGISTRATE.item("unformed_basalt", Item::new).register(),
            STRAIGHT_SMALL_COIL = Main.REGISTRATE.item("straight_small_coil", Item::new).register(),
            STRAIGHT_LARGE_COIL = Main.REGISTRATE.item("straight_large_coil", Item::new).register(),
            STRAIGHT_FLAT_COIL = Main.REGISTRATE.item("straight_flat_coil", Item::new).register(),
            LOCKING_RETURN_COIL = Main.REGISTRATE.item("locking_return_coil", Item::new).register(),
            FLAT_HEAD_SCREW = Main.REGISTRATE.item("flat_head_screw", Item::new).register(),
            M_SCREW = Main.REGISTRATE.item("m_screw", Item::new).register(),
            THIN_SMALL_ROD = Main.REGISTRATE.item("thin_small_rod", Item::new).register(),
            THICK_SMALL_ROD = Main.REGISTRATE.item("thick_small_rod", Item::new).register();


    // Buckets
    public static final RegistryEntry<Item>
            MOLTEN_BRASS_BUCKET = Main.REGISTRATE.item("molten_brass_bucket", Item::new)
                    .properties(p -> p.stacksTo(1))
                    .register(),
            MOLTEN_COPPER_BUCKET = Main.REGISTRATE.item("molten_copper_bucket", Item::new)
                    .properties(p -> p.stacksTo(1))
                    .register(),
            MOLTEN_BASALT_INFUSED_IRON_BUCKET = Main.REGISTRATE.item("molten_basalt_infused_iron_bucket", Item::new)
                    .properties(p -> p.stacksTo(1))
                    .register();


    // Cartrige Casts and Components
    public static final CartridgeBase
            NINE_MIL = new CartridgeBase("9mm"),
            FOUR_FIVE_ACP = new CartridgeBase("45acp"),
            FIFTY_AE = new CartridgeBase("50ae"),
            MAGNUM_R = new CartridgeBase("magnum_r"),
            TWELVE_G = new CartridgeBase("12g", CartridgeBase.SHOTGUN_CARTRIDGE),
            THIRTY_ZERO_SIX = new CartridgeBase("30_06"),
            FOUR_SIX_X_THIRTY = new CartridgeBase("46x30"),
            FIFTY_BMG = new CartridgeBase("50bmg"),
            FIVE_EIGHT_X_FOUR_TWO = new CartridgeBase("58x42"),
            SIX_EIGHT_X_FIVE_ONE_FURY = new CartridgeBase("68x51fury"),
            RPG_ROCKET = new CartridgeBase("rpg_rocket"),
            THREE_ZERO_EIGHT = new CartridgeBase("308"),
            THREE_THREE_EIGHT = new CartridgeBase("338"),
            FIVE_FIVE_SIX_X_FOUR_FIVE = new CartridgeBase("556x45"),
            SEVEN_SIX_TWO_X_TWO_FIVE = new CartridgeBase("762x25"),
            SEVEN_SIX_TWO_X_THREE_NINE = new CartridgeBase("762x39"),
            SEVEN_SIX_TWO_X_FIVE_FOUR = new CartridgeBase("762x54");

    // Weapons
    // Guns
    public static final RifleBase
        M4A1 = new RifleBase("m4a1", RifleBase.STANDARD_RIFLE_MODULES);


    public static void register(){
        // Do no delete this function; This is for loading this class.
        Main.LOGGER.info("Registering Mod Items");
    }
}
