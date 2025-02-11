package cn.crtlprototypestudios.prma.foundation;

import cn.crtlprototypestudios.prma.Main;
import cn.crtlprototypestudios.prma.foundation.data.providers.ModItemModelProvider;
import cn.crtlprototypestudios.prma.foundation.neo.item.collection.AmmoCartridgeComponents;
import cn.crtlprototypestudios.prma.foundation.neo.item.type.AmmoCasingType;
import cn.crtlprototypestudios.prma.foundation.neo.item.type.AmmoHeadType;
import cn.crtlprototypestudios.prma.foundation.neo.item.type.AmmoMaterialType;
import cn.crtlprototypestudios.prma.foundation.neo.item.type.AmmoSizeType;
import cn.crtlprototypestudios.prma.foundation.util.PreciseManufacturingRegistrate;
import com.simibubi.create.AllTags;
import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ModItems {

    static {
        // Set that all registered Items and Blocks defaults under this Mod's creative tabs
        assert ModCreativeModTabs.MOD_TAB.getKey() != null;
        Main.REGISTRATE.defaultCreativeTab(ModCreativeModTabs.MOD_TAB.getKey());
    }

//    public static final Set<ItemStack> taczGuns = TaczAPIBridge.getTaczGuns();
//    public static final Set<ItemStack> taczAmmo = TaczAPIBridge.getTaczAmmo();

    public static RegistryEntry<Item> addToList(RegistryEntry<Item> entry, ModCreativeModTabs.Tabs tabs) {
        ALL_ITEMS.add(entry);
        if (Objects.requireNonNull(tabs) == ModCreativeModTabs.Tabs.Materials) {
            ALL_MATERIALS.add(entry);
        }
        return entry;
    }

    public static RegistryEntry<Item> addToMaterials(RegistryEntry<Item> entry) {
        return addToList(entry, ModCreativeModTabs.Tabs.Materials);
    }

    public static RegistryEntry<Item> addToList(RegistryEntry<Item> entry) {
        return addToList(entry, ModCreativeModTabs.Tabs.Main);
    }

    public static List<RegistryEntry<Item>>
            ALL_ITEMS = new ArrayList<RegistryEntry<Item>>(),
            ALL_MATERIALS = new ArrayList<RegistryEntry<Item>>();

//    public static final ItemStack TACZ_AMMO_ITEM_TEMPLATE = new ItemStack(ModCompatItems.AMMO);
//    public static final ItemStack TACZ_GUN_ITEM_TEMPLATE = new ItemStack(ModCompatItems.MODERN_KINETIC_GUN);

    public static class Ammo {
        public static ItemBuilder<Item, PreciseManufacturingRegistrate> ammoComponent(String name) {
            return Main.REGISTRATE.item(name, Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "ammo", "components", "_"))
                    .tag(ModTags.ammunitionComponentsTag());
        }

        public static final AmmoCartridgeComponents
                SMALL_COMPONENTS = new AmmoCartridgeComponents(AmmoSizeType.Small, AmmoMaterialType.Copper, AmmoMaterialType.Iron, AmmoMaterialType.Brass).build(),
                MEDIUM_COMPONENTS = new AmmoCartridgeComponents(AmmoSizeType.Medium, AmmoMaterialType.Copper, AmmoMaterialType.Iron, AmmoMaterialType.Brass).build(),
                LONG_COMPONENTS = new AmmoCartridgeComponents(AmmoSizeType.Long, AmmoMaterialType.Copper, AmmoMaterialType.Iron, AmmoMaterialType.Brass).build();

        public static final RegistryEntry<Item>
                CARTRIDGE_PRIMER = addToMaterials(ammoComponent("cartridge_primer").register()),
                IRON_PELLETS = addToMaterials(ammoComponent("iron_pellets").register()),
                ASSEMBLING_SMALL_CARTRIDGE = Main.REGISTRATE.item("assembling_small_cartridge", Item::new).register(),
                ASSEMBLING_MEDIUM_CARTRIDGE = Main.REGISTRATE.item("assembling_medium_cartridge", Item::new).register(),
                ASSEMBLING_LONG_CARTRIDGE = Main.REGISTRATE.item("assembling_long_cartridge", Item::new).register(),
                ASSEMBLING_SHOTGUN_CARTRIDGE = Main.REGISTRATE.item("assembling_shotgun_cartridge", Item::new).register();

        public static RegistryEntry<Item> getCasingByTypes(AmmoCasingType casingType, AmmoMaterialType materialType){
            return switch (casingType) {
                case Small -> SMALL_COMPONENTS.getCasing(materialType);
                case Medium -> MEDIUM_COMPONENTS.getCasing(materialType);
                case Long -> LONG_COMPONENTS.getCasing(materialType);
                default -> null;
            };
        }

        public static RegistryEntry<Item> getHeadByTypes(AmmoHeadType headType, AmmoMaterialType materialType){
            return switch (headType) {
                case Small -> SMALL_COMPONENTS.getHead(materialType);
                case Medium -> MEDIUM_COMPONENTS.getHead(materialType);
                case Long -> LONG_COMPONENTS.getHead(materialType);
                default -> null;
            };
        }

        public static void register() {

        }
    }

    // Powders
    public static final RegistryEntry<Item>
            BASALT_POWDER = addToMaterials(Main.REGISTRATE.item("basalt_powder", Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "powders", "_"))
                    .tag(ModTags.materialsTag()).register()), // Basalt Powder is practically useless, a way to add complexity to automation lines
            SULFUR_POWDER = addToMaterials(Main.REGISTRATE.item("sulfur_powder", Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "powders", "_"))
                    .tag(ModTags.materialsTag()).register()),
            FLINT_POWDER = addToMaterials(Main.REGISTRATE.item("flint_powder", Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "powders", "_"))
                    .tag(ModTags.materialsTag()).register()),
            ROCK_POWDER = addToMaterials(Main.REGISTRATE.item("rock_powder", Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "powders", "_"))
                    .tag(ModTags.materialsTag()).register()), // Rock Powder is also practically useless, a way to add complexity to automation lines
            SMALL_AMMUNITION_GUNPOWDER = addToMaterials(Main.REGISTRATE.item("small_ammunition_gunpowder", Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "powders", "_"))
                    .tag(ModTags.materialsTag())
                    .tag(ModTags.smallAmmunitionGunpowdersTag())
                    .register()),
            MEDIUM_AMMUNITION_GUNPOWDER = addToMaterials(Main.REGISTRATE.item("medium_ammunition_gunpowder", Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "powders", "_"))
                    .tag(ModTags.materialsTag())
                    .tag(ModTags.mediumAmmunitionGunpowdersTag())
                    .register()),
            LONG_AMMUNITION_GUNPOWDER = addToMaterials(Main.REGISTRATE.item("long_ammunition_gunpowder", Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "powders", "_"))
                    .tag(ModTags.materialsTag())
                    .tag(ModTags.longAmmunitionGunpowdersTag())
                    .register());

    // Misc Items
    public static final RegistryEntry<Item>
            STRAIGHT_SMALL_COIL = addToMaterials(Main.REGISTRATE.item("straight_small_coil", Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "components", "_"))
                    .tag(ModTags.componentsTag()).register()),
            STRAIGHT_LARGE_COIL = addToMaterials(Main.REGISTRATE.item("straight_large_coil", Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "components", "_"))
                    .tag(ModTags.componentsTag()).register()),
            STRAIGHT_FLAT_COIL = addToMaterials(Main.REGISTRATE.item("straight_flat_coil", Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "components", "_"))
                    .tag(ModTags.componentsTag()).register()),
            LOCKING_RETURN_COIL = addToMaterials(Main.REGISTRATE.item("locking_return_coil", Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "components", "_"))
                    .tag(ModTags.componentsTag()).register()),
            FLAT_HEAD_SCREW = addToMaterials(Main.REGISTRATE.item("flat_head_screw", Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "components", "_"))
                    .tag(ModTags.componentsTag()).register()),
            M_SCREW = addToMaterials(Main.REGISTRATE.item("m_screw", Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "components", "_"))
                    .tag(ModTags.componentsTag()).register()),
            THIN_SMALL_ROD = addToMaterials(Main.REGISTRATE.item("thin_small_rod", Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "components", "_"))
                    .tag(ModTags.componentsTag()).register()),
            THICK_SMALL_ROD = addToMaterials(Main.REGISTRATE.item("thick_small_rod", Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "components", "_"))
                    .tag(ModTags.componentsTag()).register()),
            CRUSHED_BASALT = addToMaterials(Main.REGISTRATE.item("crushed_basalt", Item::new)
                    .register()),
            BLANK_BLUEPRINT = addToMaterials(Main.REGISTRATE.item("blank_blueprint", Item::new)
                    .register()),
            BLANK_CAST = addToMaterials(Main.REGISTRATE.item("blank_cast", Item::new)
                    .register());

    // Metals
    public static final RegistryEntry<Item>
            ALUMINUM_INGOT = addToMaterials(Main.REGISTRATE.item("aluminum_ingot", Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "metals", "_"))
                    .tag(ModTags.ingotsTag()).register()),
            CRUSHED_ALUMINUM = addToMaterials(Main.REGISTRATE.item("crushed_aluminum", Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "metals", "_"))
                    .tag(ModTags.crushedOresTag()).tag(AllTags.AllItemTags.CRUSHED_RAW_MATERIALS.tag).register()),
            CRUSHED_LEAD = addToMaterials(Main.REGISTRATE.item("crushed_lead", Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "metals", "_"))
                    .register());

    // Buckets
    public static final RegistryEntry<Item>
            MOLTEN_BRASS_BUCKET = addToList(Main.REGISTRATE.item("molten_brass_bucket", Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "buckets", "_"))
                    .properties(p -> p.stacksTo(1))
                    .register()),
            MOLTEN_COPPER_BUCKET = addToList(Main.REGISTRATE.item("molten_copper_bucket", Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "buckets", "_"))
                    .properties(p -> p.stacksTo(1))
                    .register()),
            MOLTEN_IRON_BUCKET = addToList(Main.REGISTRATE.item("molten_iron_bucket", Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "buckets", "_"))
                    .properties(p -> p.stacksTo(1))
                    .register()),
            MOLTEN_ALUMINUM_BUCKET = addToList(Main.REGISTRATE.item("molten_aluminum_bucket", Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "buckets", "_"))
                    .properties(p -> p.stacksTo(1))
                    .register()),
            MOLTEN_METAL_ALLOY_BUCKET = addToList(Main.REGISTRATE.item("molten_metal_alloy_bucket", Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "buckets", "_"))
                    .properties(p -> p.stacksTo(1))
                    .register());


    // Cartrige Casts and Components
//    public static final CartridgeBase
//            NINE_MIL = new CartridgeBase("9mm", AmmunitionSize.SMALL, 1, AmmunitionBase.getSmallCartridgeModules()),
//
//    FOUR_FIVE_ACP = new CartridgeBase("45acp", AmmunitionSize.SMALL, 1, AmmunitionBase.getSmallCartridgeModules()),
//
//    FIFTY_AE = new CartridgeBase("50ae", AmmunitionSize.SMALL, 2, AmmunitionBase.getPreset(0), AmmunitionBase.getPreset(3)),
//
//    MAGNUM_R = new CartridgeBase("magnum_r", AmmunitionSize.SMALL, 2, AmmunitionBase.getPreset(0), AmmunitionBase.getPreset(3)),
//
//    TWELVE_G = new CartridgeBase("12g", AmmunitionSize.SHELL, 2, AmmunitionBase.getShellCartridgeModules()),
//
//    THIRTY_ZERO_SIX = new CartridgeBase("30_06", AmmunitionSize.MEDIUM, 1, AmmunitionBase.getMediumCartridgeModules()),
//
//    FOUR_SIX_X_THIRTY = new CartridgeBase("46x30", AmmunitionSize.MEDIUM, 1, AmmunitionBase.getMediumCartridgeModules()),
//
//    FIFTY_BMG = new CartridgeBase("50bmg", AmmunitionSize.LONG, 2, AmmunitionBase.getLongCartridgeModules(ModTags.moltenIronTag())),
//
//    FIVE_EIGHT_X_FOUR_TWO = new CartridgeBase("58x42", AmmunitionSize.MEDIUM, 1, AmmunitionBase.getMediumCartridgeModules(ModTags.moltenIronTag())),
//
//    SIX_EIGHT_X_FIVE_ONE_FURY = new CartridgeBase("68x51fury", AmmunitionSize.MEDIUM, 2, AmmunitionBase.getMediumCartridgeModules(ModTags.moltenIronTag())),
//
//    THREE_ZERO_EIGHT = new CartridgeBase("308", AmmunitionSize.LONG, 2, AmmunitionBase.getLongCartridgeModules()),
//
//    THREE_THREE_EIGHT = new CartridgeBase("338", AmmunitionSize.LONG, 2, AmmunitionBase.getLongCartridgeModules()),
//
//    FIVE_FIVE_SIX_X_FOUR_FIVE = new CartridgeBase("556x45", AmmunitionSize.MEDIUM, 1, AmmunitionBase.getMediumCartridgeModules()),
//
//    SEVEN_SIX_TWO_X_TWO_FIVE = new CartridgeBase("762x25", AmmunitionSize.MEDIUM, 1, AmmunitionBase.getMediumCartridgeModules()),
//
//    SEVEN_SIX_TWO_X_THREE_NINE = new CartridgeBase("762x39", AmmunitionSize.MEDIUM, 1, AmmunitionBase.getMediumCartridgeModules(ModTags.moltenIronTag())),
//
//    SEVEN_SIX_TWO_X_FIVE_FOUR = new CartridgeBase("762x54", AmmunitionSize.MEDIUM, 1, AmmunitionBase.getMediumCartridgeModules()),
//
//    THREE_FIVE_SEVEN = new CartridgeBase("357", AmmunitionSize.SMALL, 2, AmmunitionBase.getPreset(0), AmmunitionBase.getPreset(3));

    // Weapons
    // Guns
//    public static final RifleBase
//        M4A1 = new RifleBase("m4a1", RifleBase.STANDARD_RIFLE_MODULES)
//                .setModuleData(0, d -> d.setCastFillingAmount(150)) // Grip
//                .setModuleData(1, d -> d.setCastFillingAmount(500)) // Lower Receiver
//                .setModuleData(2, d -> d.setCastFillingAmount(550)) // Upper Receiver
//                .setModuleData(3, d -> d.setCastFillingAmount(300)) // Handguard
//                .setModuleData(4, d -> d.setCastFillingAmount(250)) // Barrel
//                .setModuleData(5, d -> d.setCastFillingAmount(200)) // Magazine
//                .setModuleData(6, d -> d.setCastFillingAmount(250)) // Fire Control Group
//                .setModuleData(7, d -> d.setCastFillingAmount(80)) // Fire Selector
//                .setModuleData(8, d -> d.setCastFillingAmount(80)) // Trigger
//                .setModuleData(9, d -> d.setCastFillingAmount(300)), // Stock


    public static void register() {
        // Do no delete this function; This is for loading this class.
        Ammo.register();
        Main.LOGGER.info("Registering Mod Items");
    }

}
