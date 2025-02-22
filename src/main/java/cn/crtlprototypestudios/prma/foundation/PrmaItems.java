package cn.crtlprototypestudios.prma.foundation;

import cn.crtlprototypestudios.prma.PreciseManufacturing;
import cn.crtlprototypestudios.prma.foundation.data.providers.ModItemModelProvider;
import cn.crtlprototypestudios.prma.foundation.neo.content.item.collection.StandardCartridgeComponents;
import cn.crtlprototypestudios.prma.foundation.neo.content.item.type.standard.AmmoCasingType;
import cn.crtlprototypestudios.prma.foundation.neo.content.item.type.standard.AmmoHeadType;
import cn.crtlprototypestudios.prma.foundation.neo.content.item.type.standard.AmmoMaterialType;
import cn.crtlprototypestudios.prma.foundation.neo.content.item.type.standard.AmmoSizeType;
import com.simibubi.create.AllTags;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PrmaItems {

    static {
        // Set that all registered Items and Blocks defaults under this Mod's creative tabs
        assert PrmaCreativeModTabs.MOD_TAB.getKey() != null;
        PreciseManufacturing.REGISTRATE.defaultCreativeTab(PrmaCreativeModTabs.MOD_TAB.getKey());
    }

//    public static final Set<ItemStack> taczGuns = TaczAPIBridge.getTaczGuns();
//    public static final Set<ItemStack> taczAmmo = TaczAPIBridge.getTaczAmmo();

    public static RegistryEntry<Item> addToList(RegistryEntry<Item> entry, PrmaCreativeModTabs.Tabs tabs) {
        ALL_ITEMS.add(entry);
        if (Objects.requireNonNull(tabs) == PrmaCreativeModTabs.Tabs.Materials) {
            ALL_MATERIALS.add(entry);
        }
        return entry;
    }

    public static RegistryEntry<Item> addToMaterials(RegistryEntry<Item> entry) {
        return addToList(entry, PrmaCreativeModTabs.Tabs.Materials);
    }

    public static RegistryEntry<Item> addToList(RegistryEntry<Item> entry) {
        return addToList(entry, PrmaCreativeModTabs.Tabs.Main);
    }

    public static ItemBuilder<Item, CreateRegistrate> bucketItem(String name) {
        return PreciseManufacturing.REGISTRATE.item(name, Item::new)
                .model(ModItemModelProvider.genericItemModel(true, "buckets", "_"))
                .properties(p -> p.stacksTo(1));
    }

    public static List<RegistryEntry<Item>>
            ALL_ITEMS = new ArrayList<RegistryEntry<Item>>(),
            ALL_MATERIALS = new ArrayList<RegistryEntry<Item>>();

    public static List<StandardCartridgeComponents> ALL_CARTRIDGE_COMPONENTS = new ArrayList<>();

    public static class Ammo {

        public static final StandardCartridgeComponents
                SMALL_COMPONENTS = new StandardCartridgeComponents(AmmoSizeType.Small, AmmoMaterialType.Copper, AmmoMaterialType.Iron, AmmoMaterialType.Brass).build(),
                MEDIUM_COMPONENTS = new StandardCartridgeComponents(AmmoSizeType.Medium, AmmoMaterialType.Copper, AmmoMaterialType.Iron, AmmoMaterialType.Brass).build(),
                LONG_COMPONENTS = new StandardCartridgeComponents(AmmoSizeType.Long, AmmoMaterialType.Copper, AmmoMaterialType.Iron, AmmoMaterialType.Brass).build();

        public static final RegistryEntry<Item>
                CARTRIDGE_PRIMER = addToMaterials(ammoComponent("cartridge_primer").register());

        public static final RegistryEntry<Item>
                SHOTGUN_SHELL_TRANSITION = addToMaterials(PreciseManufacturing.REGISTRATE.item("shotgun_shell_transition", Item::new)
                        .model(ModItemModelProvider.genericItemModel(true, "ammo", "transition", "shotgun", "_"))
                        .tag(PrmaTags.ItemTag.AMMO_WASTE.tag, AllTags.AllItemTags.UPRIGHT_ON_BELT.tag)
                        .register()),
                SHOTGUN_SHELL_BASE = addToMaterials(PreciseManufacturing.REGISTRATE.item("shotgun_shell_base", Item::new)
                        .model(ModItemModelProvider.genericItemModel(true, "ammo", "casing", "shotgun", "_"))
                        .tag(PrmaTags.ItemTag.AMMO_CASINGS.tag, AllTags.AllItemTags.UPRIGHT_ON_BELT.tag)
                        .register()),
                SHOTGUN_SHELL = addToMaterials(PreciseManufacturing.REGISTRATE.item("shotgun_shell", Item::new)
                        .model(ModItemModelProvider.genericItemModel(true, "ammo", "casing", "shotgun", "_"))
                        .tag(PrmaTags.ItemTag.AMMO_CASINGS.tag, AllTags.AllItemTags.UPRIGHT_ON_BELT.tag)
                        .register()),
                SHOTGUN_BEARING = addToMaterials(PreciseManufacturing.REGISTRATE.item("shotgun_bearing", Item::new)
                        .model(ModItemModelProvider.genericItemModel(true, "ammo", "head", "shotgun", "_"))
                        .tag(PrmaTags.ItemTag.AMMO_HEADS.tag, AllTags.AllItemTags.UPRIGHT_ON_BELT.tag)
                        .register());

        public static ItemBuilder<Item, CreateRegistrate> ammoComponent(String name) {
            return PreciseManufacturing.REGISTRATE.item(name, Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "ammo", "components", "_"))
                    .tag(PrmaTags.ItemTag.AMMO_COMPONENTS.tag);
        }

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
                default -> SMALL_COMPONENTS.getHead(materialType);
            };
        }

        public static RegistryEntry<Item> getTransitionByTypes(AmmoHeadType headType, AmmoMaterialType materialType){
            return switch (headType) {
                case Small -> SMALL_COMPONENTS.getTransition(materialType);
                case Medium -> MEDIUM_COMPONENTS.getTransition(materialType);
                case Long -> LONG_COMPONENTS.getTransition(materialType);
                default -> SMALL_COMPONENTS.getTransition(materialType);
            };
        }
    }

    public static class Cast {
        public static ItemBuilder<Item, CreateRegistrate> castItem(String name) {
            return PreciseManufacturing.REGISTRATE.item(name, Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "casts", "_"))
                    .tag(PrmaTags.ItemTag.CASTS.tag, PrmaTags.ItemTag.CASTING_BASIN_PLACEABLE.tag);
        }

        public static final RegistryEntry<Item> INGOT_CAST = addToMaterials(castItem("ingot_cast").register());
    }

    // Powders
    public static final RegistryEntry<Item>
            BASALT_POWDER = addToMaterials(PreciseManufacturing.REGISTRATE.item("basalt_powder", Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "powders", "_"))
                    .tag(PrmaTags.ItemTag.MATERIALS.tag).register()), // Basalt Powder is practically useless, a way to add complexity to automation lines
            SULFUR_POWDER = addToMaterials(PreciseManufacturing.REGISTRATE.item("sulfur_powder", Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "powders", "_"))
                    .tag(PrmaTags.ItemTag.MATERIALS.tag).register()),
            FLINT_POWDER = addToMaterials(PreciseManufacturing.REGISTRATE.item("flint_powder", Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "powders", "_"))
                    .tag(PrmaTags.ItemTag.MATERIALS.tag).register()),
            ROCK_POWDER = addToMaterials(PreciseManufacturing.REGISTRATE.item("rock_powder", Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "powders", "_"))
                    .tag(PrmaTags.ItemTag.MATERIALS.tag).register()), // Rock Powder is also practically useless, a way to add complexity to automation lines
            SMALL_AMMUNITION_GUNPOWDER = addToMaterials(PreciseManufacturing.REGISTRATE.item("small_ammunition_gunpowder", Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "powders", "_"))
                    .tag(PrmaTags.ItemTag.MATERIALS.tag, PrmaTags.ItemTag.SMALL_AMMO_PROPELLANTS.tag)
                    .register()),
            MEDIUM_AMMUNITION_GUNPOWDER = addToMaterials(PreciseManufacturing.REGISTRATE.item("medium_ammunition_gunpowder", Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "powders", "_"))
                    .tag(PrmaTags.ItemTag.MATERIALS.tag, PrmaTags.ItemTag.MEDIUM_AMMO_PROPELLANTS.tag)
                    .register()),
            LONG_AMMUNITION_GUNPOWDER = addToMaterials(PreciseManufacturing.REGISTRATE.item("long_ammunition_gunpowder", Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "powders", "_"))
                    .tag(PrmaTags.ItemTag.MATERIALS.tag, PrmaTags.ItemTag.LONG_AMMO_PROPELLANTS.tag)
                    .register());

    // Misc Items
    public static final RegistryEntry<Item>
            STRAIGHT_SMALL_COIL = addToMaterials(PreciseManufacturing.REGISTRATE.item("straight_small_coil", Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "components", "_"))
                    .tag(PrmaTags.ItemTag.WEAPON_COMPONENTS.tag).register()),
            STRAIGHT_LARGE_COIL = addToMaterials(PreciseManufacturing.REGISTRATE.item("straight_large_coil", Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "components", "_"))
                    .tag(PrmaTags.ItemTag.WEAPON_COMPONENTS.tag).register()),
            STRAIGHT_FLAT_COIL = addToMaterials(PreciseManufacturing.REGISTRATE.item("straight_flat_coil", Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "components", "_"))
                    .tag(PrmaTags.ItemTag.WEAPON_COMPONENTS.tag).register()),
            LOCKING_RETURN_COIL = addToMaterials(PreciseManufacturing.REGISTRATE.item("locking_return_coil", Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "components", "_"))
                    .tag(PrmaTags.ItemTag.WEAPON_COMPONENTS.tag).register()),
            FLAT_HEAD_SCREW = addToMaterials(PreciseManufacturing.REGISTRATE.item("flat_head_screw", Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "components", "_"))
                    .tag(PrmaTags.ItemTag.WEAPON_COMPONENTS.tag).register()),
            M_SCREW = addToMaterials(PreciseManufacturing.REGISTRATE.item("m_screw", Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "components", "_"))
                    .tag(PrmaTags.ItemTag.WEAPON_COMPONENTS.tag).register()),
            THIN_SMALL_ROD = addToMaterials(PreciseManufacturing.REGISTRATE.item("thin_small_rod", Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "components", "_"))
                    .tag(PrmaTags.ItemTag.WEAPON_COMPONENTS.tag).register()),
            THICK_SMALL_ROD = addToMaterials(PreciseManufacturing.REGISTRATE.item("thick_small_rod", Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "components", "_"))
                    .tag(PrmaTags.ItemTag.WEAPON_COMPONENTS.tag).register()),
            CRUSHED_BASALT = addToMaterials(PreciseManufacturing.REGISTRATE.item("crushed_basalt", Item::new)
                    .register()),
            BLANK_BLUEPRINT = addToMaterials(PreciseManufacturing.REGISTRATE.item("blank_blueprint", Item::new)
                    .register()),
            BLANK_CAST = addToMaterials(PreciseManufacturing.REGISTRATE.item("blank_cast", Item::new)
                    .register());

    // Metals
    public static final RegistryEntry<Item>
            LEAD_INGOT = addToMaterials(PreciseManufacturing.REGISTRATE.item("lead_ingot", Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "metals", "_"))
                    .tag(PrmaTags.ItemTag.INGOTS.tag).register()),
            ALUMINUM_INGOT = addToMaterials(PreciseManufacturing.REGISTRATE.item("aluminum_ingot", Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "metals", "_"))
                    .tag(PrmaTags.ItemTag.INGOTS.tag).register()),
            STRONG_ALUMINUM_INGOT = addToMaterials(PreciseManufacturing.REGISTRATE.item("strong_aluminum_ingot", Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "metals", "_"))
                    .tag(PrmaTags.ItemTag.INGOTS.tag).register()),
            CRUSHED_RAW_ALUMINUM = addToMaterials(PreciseManufacturing.REGISTRATE.item("crushed_raw_aluminum", Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "metals", "_"))
                    .tag(PrmaTags.ItemTag.CRUSHED_ORES.tag).tag(AllTags.AllItemTags.CRUSHED_RAW_MATERIALS.tag)
                    .register()),
            CRUSHED_RAW_LEAD = addToMaterials(PreciseManufacturing.REGISTRATE.item("crushed_raw_lead", Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "metals", "_"))
                    .tag(PrmaTags.ItemTag.CRUSHED_ORES.tag).tag(AllTags.AllItemTags.CRUSHED_RAW_MATERIALS.tag)
                    .register());

    // Buckets
    public static final RegistryEntry<Item>
            MOLTEN_COPPER_BUCKET = addToList(bucketItem("molten_copper_bucket").register()),
            MOLTEN_ZINC_BUCKET = addToList(bucketItem("molten_zinc_bucket").register()),
            MOLTEN_IRON_BUCKET = addToList(bucketItem("molten_iron_bucket").register()),
            MOLTEN_ALUMINUM_BUCKET = addToList(bucketItem("molten_aluminum_bucket").register()),
            MOLTEN_STRONG_ALUMINUM_BUCKET = addToList(bucketItem("molten_strong_aluminum_bucket").register()),
            MOLTEN_METAL_ALLOY_BUCKET = addToList(bucketItem("molten_metal_alloy_bucket").register());


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
//        PreciseManufacturing.LOGGER.debug("{}", PrmaItems.SMALL_AMMUNITION_GUNPOWDER.get());
        PreciseManufacturing.LOGGER.info("Registering Mod Items");
    }

}
