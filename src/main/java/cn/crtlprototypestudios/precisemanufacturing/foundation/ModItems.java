package cn.crtlprototypestudios.precisemanufacturing.foundation;

import cn.crtlprototypestudios.precisemanufacturing.Main;
import cn.crtlprototypestudios.precisemanufacturing.foundation.data.providers.ModItemModelProvider;
import cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.ammunition.CartridgeAssemblySequence;
import cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.ammunition.CartridgeBase;
import cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.ammunition.CartridgeModuleBuilder;
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
                    .model(ModItemModelProvider.genericItemModel(true, "buckets", "_"))
                    .properties(p -> p.stacksTo(1))
                    .register(),
            MOLTEN_COPPER_BUCKET = Main.REGISTRATE.item("molten_copper_bucket", Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "buckets", "_"))
                    .properties(p -> p.stacksTo(1))
                    .register(),
            MOLTEN_BASALT_INFUSED_IRON_BUCKET = Main.REGISTRATE.item("molten_basalt_infused_iron_bucket", Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "buckets", "_"))
                    .properties(p -> p.stacksTo(1))
                    .register();


    // Cartrige Casts and Components
    public static final CartridgeBase
            NINE_MIL = new CartridgeBase("9mm", CartridgeBase.STANDARD_CARTRIDGE)
                    .setModuleData(0, d -> d.setFillingAmount(50))
                    .setModuleData(1, d -> d.setFillingAmount(25)),

            FOUR_FIVE_ACP = new CartridgeBase("45acp", CartridgeBase.STANDARD_CARTRIDGE)
                    .setModuleData(0, d -> d.setFillingAmount(50))
                    .setModuleData(1, d -> d.setFillingAmount(30)),

            FIFTY_AE = new CartridgeBase("50ae", CartridgeBase.STANDARD_CARTRIDGE
                    .insertAssemblySequence(1, CartridgeAssemblySequence.GUNPOWDER))
                    .setModuleData(0, d -> d.setFillingAmount(80))
                    .setModuleData(1, d -> d.setFillingAmount(40)),

            MAGNUM_R = new CartridgeBase("magnum_r", CartridgeBase.STANDARD_CARTRIDGE
                    .insertAssemblySequence(0, CartridgeAssemblySequence.GUNPOWDER_PELLET))
                    .setModuleData(0, d -> d.setFillingAmount(75)),

            TWELVE_G = new CartridgeBase("12g", CartridgeBase.SHOTGUN_CARTRIDGE)
                    .setModuleData(0, d -> d.setFillingAmount(75))
                    .setModuleData(1, d -> d.setFillingAmount(40)),

            THIRTY_ZERO_SIX = new CartridgeBase("30_06", CartridgeBase.STANDARD_CARTRIDGE
                    .replaceAssemblySequence(CartridgeAssemblySequence.GUNPOWDER, CartridgeAssemblySequence.GUNPOWDER_PELLET)
                    .insertAssemblySequence(1, CartridgeAssemblySequence.GUNPOWDER_PELLET)
                    .insertAssemblySequence(1, CartridgeAssemblySequence.GUNPOWDER))
                    .setModuleData(0, d -> d.setFillingAmount(100)),

            FOUR_SIX_X_THIRTY = new CartridgeBase("46x30", CartridgeBase.STANDARD_CARTRIDGE
                    .replaceAssemblySequence(CartridgeAssemblySequence.GUNPOWDER, CartridgeAssemblySequence.GUNPOWDER_PELLET)
                    .insertAssemblySequence(1, CartridgeAssemblySequence.GUNPOWDER_PELLET))
                    .setModuleData(0, d -> d.setFillingAmount(75)),

            FIFTY_BMG = new CartridgeBase("50bmg", CartridgeBase.STANDARD_CARTRIDGE
                    .replaceAssemblySequence(CartridgeAssemblySequence.GUNPOWDER, CartridgeAssemblySequence.GUNPOWDER_PELLET)
                    .insertAssemblySequence(1, CartridgeAssemblySequence.GUNPOWDER_PELLET)
                    .insertAssemblySequence(1, CartridgeAssemblySequence.GUNPOWDER_PELLET))
                    .setModuleData(0, d -> d.setFillingAmount(120).setFillingFluid(ModFluids.MOLTEN_BASALT_INFUSED_IRON)),

            FIVE_EIGHT_X_FOUR_TWO = new CartridgeBase("58x42", CartridgeBase.STANDARD_CARTRIDGE
                    .replaceAssemblySequence(CartridgeAssemblySequence.GUNPOWDER, CartridgeAssemblySequence.GUNPOWDER_PELLET)
                    .insertAssemblySequence(1, CartridgeAssemblySequence.GUNPOWDER_PELLET))
                    .setModuleData(0, d -> d.setFillingAmount(100).setFillingFluid(ModFluids.MOLTEN_BASALT_INFUSED_IRON)),

            SIX_EIGHT_X_FIVE_ONE_FURY = new CartridgeBase("68x51fury", CartridgeBase.STANDARD_CARTRIDGE
                    .replaceAssemblySequence(CartridgeAssemblySequence.GUNPOWDER, CartridgeAssemblySequence.GUNPOWDER_PELLET)
                    .insertAssemblySequence(1, CartridgeAssemblySequence.GUNPOWDER_PELLET))
                    .setModuleData(0, d -> d.setFillingAmount(90).setFillingFluid(ModFluids.MOLTEN_BASALT_INFUSED_IRON)),

            RPG_ROCKET = new CartridgeBase("rpg_rocket", CartridgeBase.ROCKET_CARTRIDGE),

            THREE_ZERO_EIGHT = new CartridgeBase("308", CartridgeBase.STANDARD_CARTRIDGE
                    .replaceAssemblySequence(CartridgeAssemblySequence.GUNPOWDER, CartridgeAssemblySequence.GUNPOWDER_PELLET)
                    .insertAssemblySequence(1, CartridgeAssemblySequence.GUNPOWDER_PELLET)
                    .insertAssemblySequence(1, CartridgeAssemblySequence.GUNPOWDER))
                    .setModuleData(0, d -> d.setFillingAmount(100)),

            THREE_THREE_EIGHT = new CartridgeBase("338", CartridgeBase.STANDARD_CARTRIDGE
                    .replaceAssemblySequence(CartridgeAssemblySequence.GUNPOWDER, CartridgeAssemblySequence.GUNPOWDER_PELLET)
                    .insertAssemblySequence(1, CartridgeAssemblySequence.GUNPOWDER_PELLET)
                    .insertAssemblySequence(1, CartridgeAssemblySequence.GUNPOWDER))
                    .setModuleData(0, d -> d.setFillingAmount(100)),

            FIVE_FIVE_SIX_X_FOUR_FIVE = new CartridgeBase("556x45", CartridgeBase.STANDARD_CARTRIDGE
                    .replaceAssemblySequence(CartridgeAssemblySequence.GUNPOWDER, CartridgeAssemblySequence.GUNPOWDER_PELLET)
                    .insertAssemblySequence(1, CartridgeAssemblySequence.GUNPOWDER_PELLET))
                    .setModuleData(0, d -> d.setFillingAmount(90)),

            SEVEN_SIX_TWO_X_TWO_FIVE = new CartridgeBase("762x25", CartridgeBase.STANDARD_CARTRIDGE
                    .replaceAssemblySequence(CartridgeAssemblySequence.GUNPOWDER, CartridgeAssemblySequence.GUNPOWDER_PELLET)
                    .insertAssemblySequence(1, CartridgeAssemblySequence.GUNPOWDER_PELLET))
                    .setModuleData(0, d -> d.setFillingAmount(100)),

            SEVEN_SIX_TWO_X_THREE_NINE = new CartridgeBase("762x39", CartridgeBase.STANDARD_CARTRIDGE
                    .replaceAssemblySequence(CartridgeAssemblySequence.GUNPOWDER, CartridgeAssemblySequence.GUNPOWDER_PELLET)
                    .insertAssemblySequence(1, CartridgeAssemblySequence.GUNPOWDER_PELLET))
                    .setModuleData(0, d -> d.setFillingAmount(100).setFillingFluid(ModFluids.MOLTEN_BASALT_INFUSED_IRON)),

            SEVEN_SIX_TWO_X_FIVE_FOUR = new CartridgeBase("762x54", CartridgeBase.STANDARD_CARTRIDGE
                    .replaceAssemblySequence(CartridgeAssemblySequence.GUNPOWDER, CartridgeAssemblySequence.GUNPOWDER_PELLET)
                    .insertAssemblySequence(1, CartridgeAssemblySequence.GUNPOWDER_PELLET))
                    .setModuleData(0, d -> d.setFillingAmount(100));

    // Weapons
    // Guns
    public static final RifleBase
        M4A1 = new RifleBase("m4a1", RifleBase.STANDARD_RIFLE_MODULES);


    public static void register(){
        // Do no delete this function; This is for loading this class.
        Main.LOGGER.info("Registering Mod Items");
    }
}
