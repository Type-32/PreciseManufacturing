package cn.crtlprototypestudios.prma.foundation;

import cn.crtlprototypestudios.prma.PreciseManufacturing;
import cn.crtlprototypestudios.prma.foundation.data.providers.ModItemModelProvider;
import cn.crtlprototypestudios.prma.foundation.neo.complex.content.item.collection.StandardCartridgeComponents;
import cn.crtlprototypestudios.prma.foundation.neo.complex.content.item.type.standard.AmmoCasingType;
import cn.crtlprototypestudios.prma.foundation.neo.complex.content.item.type.standard.AmmoHeadType;
import cn.crtlprototypestudios.prma.foundation.neo.complex.content.item.type.standard.AmmoMaterialType;
import cn.crtlprototypestudios.prma.foundation.neo.complex.content.item.type.standard.AmmoSizeType;
import cn.crtlprototypestudios.prma.foundation.neo.simple.content.type.standard.SimpleAmmoGunpowderAmountStandard;
import cn.crtlprototypestudios.prma.foundation.neo.simple.item.SimpleAmmo;
import cn.crtlprototypestudios.prma.foundation.neo.simple.item.SimpleCartridge;
import cn.crtlprototypestudios.prma.foundation.neo.simple.recipe.SimpleCartridgeBuilder;
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

    public static final StandardCartridgeComponents
            SMALL_COMPONENTS = new StandardCartridgeComponents(AmmoSizeType.Small, AmmoMaterialType.Copper, AmmoMaterialType.Iron, AmmoMaterialType.Brass);
    public static final StandardCartridgeComponents MEDIUM_COMPONENTS = new StandardCartridgeComponents(AmmoSizeType.Medium, AmmoMaterialType.Copper, AmmoMaterialType.Iron, AmmoMaterialType.Brass);
    public static final StandardCartridgeComponents LONG_COMPONENTS = new StandardCartridgeComponents(AmmoSizeType.Long, AmmoMaterialType.Copper, AmmoMaterialType.Iron, AmmoMaterialType.Brass);

    public static class Ammo {

        public static final RegistryEntry<Item>
                CARTRIDGE_PRIMER = addToMaterials(ammoComponent("cartridge_primer").register());

        public static final RegistryEntry<Item>
                FORTY_MIL_CASING = addToMaterials(PreciseManufacturing.REGISTRATE.item("40mm_casing", Item::new)
                        .model(ModItemModelProvider.genericItemModel(true, "ammo", "casing", "special", "_"))
                        .tag(PrmaTags.ItemTag.AMMO_CASINGS.tag, AllTags.AllItemTags.UPRIGHT_ON_BELT.tag)
                        .register()),
                FORTY_MIL_SLUG = addToMaterials(PreciseManufacturing.REGISTRATE.item("40mm_slug", Item::new)
                        .model(ModItemModelProvider.genericItemModel(true, "ammo", "head", "special", "_"))
                        .tag(PrmaTags.ItemTag.AMMO_CASINGS.tag, AllTags.AllItemTags.UPRIGHT_ON_BELT.tag)
                        .register()),
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

        public static final SimpleCartridge SMALL_BRASS_LOW_GUNPOWDER_CARTRIDGE = SimpleCartridgeBuilder.create(AmmoCasingType.Small, AmmoMaterialType.Brass, SimpleAmmoGunpowderAmountStandard.Low);
        public static final SimpleCartridge SMALL_BRASS_MEDIUM_GUNPOWDER_CARTRIDGE = SimpleCartridgeBuilder.create(AmmoCasingType.Small, AmmoMaterialType.Brass, SimpleAmmoGunpowderAmountStandard.Medium);
        public static final SimpleCartridge SMALL_BRASS_HIGH_GUNPOWDER_CARTRIDGE = SimpleCartridgeBuilder.create(AmmoCasingType.Small, AmmoMaterialType.Brass, SimpleAmmoGunpowderAmountStandard.High);

        public static final SimpleCartridge MEDIUM_BRASS_LOW_GUNPOWDER_CARTRIDGE = SimpleCartridgeBuilder.create(AmmoCasingType.Medium, AmmoMaterialType.Brass, SimpleAmmoGunpowderAmountStandard.Low);
        public static final SimpleCartridge MEDIUM_BRASS_MEDIUM_GUNPOWDER_CARTRIDGE = SimpleCartridgeBuilder.create(AmmoCasingType.Medium, AmmoMaterialType.Brass, SimpleAmmoGunpowderAmountStandard.Medium);
        public static final SimpleCartridge MEDIUM_BRASS_HIGH_GUNPOWDER_CARTRIDGE = SimpleCartridgeBuilder.create(AmmoCasingType.Medium, AmmoMaterialType.Brass, SimpleAmmoGunpowderAmountStandard.High);

        public static final SimpleCartridge LONG_BRASS_LOW_GUNPOWDER_CARTRIDGE = SimpleCartridgeBuilder.create(AmmoCasingType.Long, AmmoMaterialType.Brass, SimpleAmmoGunpowderAmountStandard.Low);
        public static final SimpleCartridge LONG_BRASS_MEDIUM_GUNPOWDER_CARTRIDGE = SimpleCartridgeBuilder.create(AmmoCasingType.Long, AmmoMaterialType.Brass, SimpleAmmoGunpowderAmountStandard.Medium);
        public static final SimpleCartridge LONG_BRASS_HIGH_GUNPOWDER_CARTRIDGE = SimpleCartridgeBuilder.create(AmmoCasingType.Long, AmmoMaterialType.Brass, SimpleAmmoGunpowderAmountStandard.High);

        public static final SimpleCartridge SMALL_IRON_LOW_GUNPOWDER_CARTRIDGE = SimpleCartridgeBuilder.create(AmmoCasingType.Small, AmmoMaterialType.Iron, SimpleAmmoGunpowderAmountStandard.Low);
        public static final SimpleCartridge SMALL_IRON_MEDIUM_GUNPOWDER_CARTRIDGE = SimpleCartridgeBuilder.create(AmmoCasingType.Small, AmmoMaterialType.Iron, SimpleAmmoGunpowderAmountStandard.Medium);
        public static final SimpleCartridge SMALL_IRON_HIGH_GUNPOWDER_CARTRIDGE = SimpleCartridgeBuilder.create(AmmoCasingType.Small, AmmoMaterialType.Iron, SimpleAmmoGunpowderAmountStandard.High);

        public static final SimpleCartridge MEDIUM_IRON_LOW_GUNPOWDER_CARTRIDGE = SimpleCartridgeBuilder.create(AmmoCasingType.Medium, AmmoMaterialType.Iron, SimpleAmmoGunpowderAmountStandard.Low);
        public static final SimpleCartridge MEDIUM_IRON_MEDIUM_GUNPOWDER_CARTRIDGE = SimpleCartridgeBuilder.create(AmmoCasingType.Medium, AmmoMaterialType.Iron, SimpleAmmoGunpowderAmountStandard.Medium);
        public static final SimpleCartridge MEDIUM_IRON_HIGH_GUNPOWDER_CARTRIDGE = SimpleCartridgeBuilder.create(AmmoCasingType.Medium, AmmoMaterialType.Iron, SimpleAmmoGunpowderAmountStandard.High);

        public static final SimpleCartridge LONG_IRON_LOW_GUNPOWDER_CARTRIDGE = SimpleCartridgeBuilder.create(AmmoCasingType.Long, AmmoMaterialType.Iron, SimpleAmmoGunpowderAmountStandard.Low);
        public static final SimpleCartridge LONG_IRON_MEDIUM_GUNPOWDER_CARTRIDGE = SimpleCartridgeBuilder.create(AmmoCasingType.Long, AmmoMaterialType.Iron, SimpleAmmoGunpowderAmountStandard.Medium);
        public static final SimpleCartridge LONG_IRON_HIGH_GUNPOWDER_CARTRIDGE = SimpleCartridgeBuilder.create(AmmoCasingType.Long, AmmoMaterialType.Iron, SimpleAmmoGunpowderAmountStandard.High);

        public static final SimpleCartridge SMALL_COPPER_LOW_GUNPOWDER_CARTRIDGE = SimpleCartridgeBuilder.create(AmmoCasingType.Small, AmmoMaterialType.Copper, SimpleAmmoGunpowderAmountStandard.Low);
        public static final SimpleCartridge SMALL_COPPER_MEDIUM_GUNPOWDER_CARTRIDGE = SimpleCartridgeBuilder.create(AmmoCasingType.Small, AmmoMaterialType.Copper, SimpleAmmoGunpowderAmountStandard.Medium);
        public static final SimpleCartridge SMALL_COPPER_HIGH_GUNPOWDER_CARTRIDGE = SimpleCartridgeBuilder.create(AmmoCasingType.Small, AmmoMaterialType.Copper, SimpleAmmoGunpowderAmountStandard.High);

        public static final SimpleCartridge MEDIUM_COPPER_LOW_GUNPOWDER_CARTRIDGE = SimpleCartridgeBuilder.create(AmmoCasingType.Medium, AmmoMaterialType.Copper, SimpleAmmoGunpowderAmountStandard.Low);
        public static final SimpleCartridge MEDIUM_COPPER_MEDIUM_GUNPOWDER_CARTRIDGE = SimpleCartridgeBuilder.create(AmmoCasingType.Medium, AmmoMaterialType.Copper, SimpleAmmoGunpowderAmountStandard.Medium);
        public static final SimpleCartridge MEDIUM_COPPER_HIGH_GUNPOWDER_CARTRIDGE = SimpleCartridgeBuilder.create(AmmoCasingType.Medium, AmmoMaterialType.Copper, SimpleAmmoGunpowderAmountStandard.High);

        public static final SimpleCartridge LONG_COPPER_LOW_GUNPOWDER_CARTRIDGE = SimpleCartridgeBuilder.create(AmmoCasingType.Long, AmmoMaterialType.Copper, SimpleAmmoGunpowderAmountStandard.Low);
        public static final SimpleCartridge LONG_COPPER_MEDIUM_GUNPOWDER_CARTRIDGE = SimpleCartridgeBuilder.create(AmmoCasingType.Long, AmmoMaterialType.Copper, SimpleAmmoGunpowderAmountStandard.Medium);
        public static final SimpleCartridge LONG_COPPER_HIGH_GUNPOWDER_CARTRIDGE = SimpleCartridgeBuilder.create(AmmoCasingType.Long, AmmoMaterialType.Copper, SimpleAmmoGunpowderAmountStandard.High);

        public static final SimpleAmmo NINE_MIL = SimpleAmmo.create(SMALL_BRASS_LOW_GUNPOWDER_CARTRIDGE, AmmoHeadType.Small, AmmoMaterialType.Copper, "9mm", 6);
        public static final SimpleAmmo THIRTY_ZEO_SIX = SimpleAmmo.create(LONG_IRON_MEDIUM_GUNPOWDER_CARTRIDGE, AmmoHeadType.Long, AmmoMaterialType.Iron, "30_06", 4);
        public static final SimpleAmmo FOUR_FIVE_ACP = SimpleAmmo.create(SMALL_BRASS_MEDIUM_GUNPOWDER_CARTRIDGE, AmmoHeadType.Small, AmmoMaterialType.Copper, "45acp", 5);
        public static final SimpleAmmo FOUR_SIX_THIRTY = SimpleAmmo.create(MEDIUM_BRASS_MEDIUM_GUNPOWDER_CARTRIDGE, AmmoHeadType.Medium, AmmoMaterialType.Copper, "46x30", 3);
        public static final SimpleAmmo FIFTY_AE = SimpleAmmo.create(SMALL_BRASS_HIGH_GUNPOWDER_CARTRIDGE, AmmoHeadType.Small, AmmoMaterialType.Copper, "50ae", 3);
        public static final SimpleAmmo FIFTY_BMG = SimpleAmmo.create(LONG_IRON_HIGH_GUNPOWDER_CARTRIDGE, AmmoHeadType.Long, AmmoMaterialType.Iron, "50bmg", 2);
        public static final SimpleAmmo FIVE_SEVEN_TWO_EIGHT = SimpleAmmo.create(MEDIUM_BRASS_LOW_GUNPOWDER_CARTRIDGE, AmmoHeadType.Medium, AmmoMaterialType.Copper, "57x28", 2);
        public static final SimpleAmmo FIVE_EIGHT_FOUR_TWO = SimpleAmmo.create(MEDIUM_IRON_MEDIUM_GUNPOWDER_CARTRIDGE, AmmoHeadType.Medium, AmmoMaterialType.Copper, "58x42", 3);
        public static final SimpleAmmo SIX_EIGHT_FIFTY_ONE_FURY = SimpleAmmo.create(MEDIUM_IRON_HIGH_GUNPOWDER_CARTRIDGE, AmmoHeadType.Medium, AmmoMaterialType.Iron, "68x51fury", 2);
        public static final SimpleAmmo THREE_ZERO_EIGHT = SimpleAmmo.create(LONG_IRON_HIGH_GUNPOWDER_CARTRIDGE, AmmoHeadType.Long, AmmoMaterialType.Iron, "308", 2);
        public static final SimpleAmmo THREE_THIRTY_EIGHT = SimpleAmmo.create(LONG_BRASS_HIGH_GUNPOWDER_CARTRIDGE, AmmoHeadType.Long, AmmoMaterialType.Copper, "338", 2);
        public static final SimpleAmmo THREE_FIVE_SEVEN_MAGNUM = SimpleAmmo.create(MEDIUM_IRON_MEDIUM_GUNPOWDER_CARTRIDGE, AmmoHeadType.Medium, AmmoMaterialType.Iron, "357mag", 2);
        public static final SimpleAmmo FIVE_FIVE_SIX_FOUR_FIVE = SimpleAmmo.create(MEDIUM_BRASS_MEDIUM_GUNPOWDER_CARTRIDGE, AmmoHeadType.Medium, AmmoMaterialType.Copper, "556x45", 3);
        public static final SimpleAmmo SEVEN_SIX_TWO_TWO_FIVE = SimpleAmmo.create(MEDIUM_IRON_LOW_GUNPOWDER_CARTRIDGE, AmmoHeadType.Medium, AmmoMaterialType.Iron, "762x25", 4);
        public static final SimpleAmmo SEVEN_SIX_TWO_THREE_NINE = SimpleAmmo.create(MEDIUM_IRON_MEDIUM_GUNPOWDER_CARTRIDGE, AmmoHeadType.Medium, AmmoMaterialType.Iron, "762x39", 3);
        public static final SimpleAmmo SEVEN_SIX_TWO_FIVE_FOUR = SimpleAmmo.create(MEDIUM_IRON_HIGH_GUNPOWDER_CARTRIDGE, AmmoHeadType.Medium, AmmoMaterialType.Iron, "762x54", 3);

        public static SimpleCartridge getCartridge(AmmoCasingType casingType, AmmoMaterialType materialType, SimpleAmmoGunpowderAmountStandard simpleAmmoGunpowderAmountStandard){
            return switch (casingType) {
                case Small -> switch (materialType) {
                    case Brass -> switch (simpleAmmoGunpowderAmountStandard) {
                        case Low -> SMALL_BRASS_LOW_GUNPOWDER_CARTRIDGE;
                        case Medium -> SMALL_BRASS_MEDIUM_GUNPOWDER_CARTRIDGE;
                        case High -> SMALL_BRASS_HIGH_GUNPOWDER_CARTRIDGE;
                        default -> SMALL_BRASS_LOW_GUNPOWDER_CARTRIDGE;
                    };
                    case Copper -> switch (simpleAmmoGunpowderAmountStandard) {
                        case Low -> SMALL_COPPER_LOW_GUNPOWDER_CARTRIDGE;
                        case Medium -> SMALL_COPPER_MEDIUM_GUNPOWDER_CARTRIDGE;
                        case High -> SMALL_COPPER_HIGH_GUNPOWDER_CARTRIDGE;
                        default -> SMALL_COPPER_LOW_GUNPOWDER_CARTRIDGE;
                    };
                    case Iron -> switch (simpleAmmoGunpowderAmountStandard) {
                        case Low -> SMALL_IRON_LOW_GUNPOWDER_CARTRIDGE;
                        case Medium -> SMALL_IRON_MEDIUM_GUNPOWDER_CARTRIDGE;
                        case High -> SMALL_IRON_HIGH_GUNPOWDER_CARTRIDGE;
                        default -> SMALL_IRON_LOW_GUNPOWDER_CARTRIDGE;
                    };
                    case Plastic -> null;
                };
                case Medium -> switch (materialType) {
                    case Brass -> switch (simpleAmmoGunpowderAmountStandard) {
                        case Low -> MEDIUM_BRASS_LOW_GUNPOWDER_CARTRIDGE;
                        case Medium -> MEDIUM_BRASS_MEDIUM_GUNPOWDER_CARTRIDGE;
                        case High -> MEDIUM_BRASS_HIGH_GUNPOWDER_CARTRIDGE;
                        default -> MEDIUM_BRASS_LOW_GUNPOWDER_CARTRIDGE;
                    };
                    case Copper -> switch (simpleAmmoGunpowderAmountStandard) {
                        case Low -> MEDIUM_COPPER_LOW_GUNPOWDER_CARTRIDGE;
                        case Medium -> MEDIUM_COPPER_MEDIUM_GUNPOWDER_CARTRIDGE;
                        case High -> MEDIUM_COPPER_HIGH_GUNPOWDER_CARTRIDGE;
                        default -> MEDIUM_COPPER_LOW_GUNPOWDER_CARTRIDGE;
                    };
                    case Iron -> switch (simpleAmmoGunpowderAmountStandard) {
                        case Low -> MEDIUM_IRON_LOW_GUNPOWDER_CARTRIDGE;
                        case Medium -> MEDIUM_IRON_MEDIUM_GUNPOWDER_CARTRIDGE;
                        case High -> MEDIUM_IRON_HIGH_GUNPOWDER_CARTRIDGE;
                        default -> MEDIUM_IRON_LOW_GUNPOWDER_CARTRIDGE;
                    };
                    case Plastic -> null;
                };
                case Long -> switch (materialType) {
                    case Brass -> switch (simpleAmmoGunpowderAmountStandard) {
                        case Low -> LONG_BRASS_LOW_GUNPOWDER_CARTRIDGE;
                        case Medium -> LONG_BRASS_MEDIUM_GUNPOWDER_CARTRIDGE;
                        case High -> LONG_BRASS_HIGH_GUNPOWDER_CARTRIDGE;
                        default -> LONG_BRASS_LOW_GUNPOWDER_CARTRIDGE;
                    };
                    case Copper -> switch (simpleAmmoGunpowderAmountStandard) {
                        case Low -> LONG_COPPER_LOW_GUNPOWDER_CARTRIDGE;
                        case Medium -> LONG_COPPER_MEDIUM_GUNPOWDER_CARTRIDGE;
                        case High -> LONG_COPPER_HIGH_GUNPOWDER_CARTRIDGE;
                        default -> LONG_COPPER_LOW_GUNPOWDER_CARTRIDGE;
                    };
                    case Iron -> switch (simpleAmmoGunpowderAmountStandard) {
                        case Low -> LONG_IRON_LOW_GUNPOWDER_CARTRIDGE;
                        case Medium -> LONG_IRON_MEDIUM_GUNPOWDER_CARTRIDGE;
                        case High -> LONG_IRON_HIGH_GUNPOWDER_CARTRIDGE;
                        default -> LONG_IRON_LOW_GUNPOWDER_CARTRIDGE;
                    };
                    case Plastic -> null;
                };
                default -> SMALL_BRASS_LOW_GUNPOWDER_CARTRIDGE;
            };
        }

//        public static RegistryEntry<Item> getGunpowderByTypes(AmmoCasingType casingType){
//            return switch (casingType) {
//                case Small -> SMALL_AMMUNITION_GUNPOWDER;
//                case Medium -> MEDIUM_AMMUNITION_GUNPOWDER;
//                case Long -> LONG_AMMUNITION_GUNPOWDER;
//                case Shell -> HIGH_POWER_AMMUNITION_GUNPOWDER;
//                default -> SMALL_AMMUNITION_GUNPOWDER;
//            };
//        }
//
//        public static RegistryEntry<Item> getGunpowderByTypes(SimpleAmmoGunpowderAmountStandard simpleAmmoGunpowderAmountStandard){
//            return switch (simpleAmmoGunpowderAmountStandard) {
//                case Low -> SMALL_AMMUNITION_GUNPOWDER;
//                case Medium -> MEDIUM_AMMUNITION_GUNPOWDER;
//                case High -> LONG_AMMUNITION_GUNPOWDER;
//                case HighPower -> HIGH_POWER_AMMUNITION_GUNPOWDER;
//                default -> SMALL_AMMUNITION_GUNPOWDER;
//            };
//        }

        public static RegistryEntry<Item> getCasingByTypes(AmmoCasingType casingType, AmmoMaterialType materialType){
            return switch (casingType) {
                case Small -> SMALL_COMPONENTS.getCasing(materialType);
                case Medium -> MEDIUM_COMPONENTS.getCasing(materialType);
                case Long -> LONG_COMPONENTS.getCasing(materialType);
                default -> SMALL_COMPONENTS.getCasing(materialType);
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

        public static void register() {

        }
    }

    public static class Cast {
        public static ItemBuilder<Item, CreateRegistrate> castItem(String name) {
            return PreciseManufacturing.REGISTRATE.item(name, Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "casts", "_"))
                    .tag(PrmaTags.ItemTag.CASTS.tag, PrmaTags.ItemTag.CASTING_BASIN_PLACEABLE.tag);
        }

        public static final RegistryEntry<Item> INGOT_CAST = addToMaterials(castItem("ingot_cast").register());

        public static void register(){

        }
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
            HIGH_AMMUNITION_GUNPOWDER = addToMaterials(PreciseManufacturing.REGISTRATE.item("long_ammunition_gunpowder", Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "powders", "_"))
                    .tag(PrmaTags.ItemTag.MATERIALS.tag, PrmaTags.ItemTag.LONG_AMMO_PROPELLANTS.tag)
                    .register()),
            HIGH_POWER_AMMUNITION_GUNPOWDER = addToMaterials(PreciseManufacturing.REGISTRATE.item("high_power_ammunition_gunpowder", Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "powders", "_"))
                    .tag(PrmaTags.ItemTag.MATERIALS.tag, PrmaTags.ItemTag.HIGH_POWER_AMMO_PROPELLANTS.tag)
                    .register());

    // Misc Items
//    public static final RegistryEntry<Item>
//            STRAIGHT_SMALL_COIL = addToMaterials(PreciseManufacturing.REGISTRATE.item("straight_small_coil", Item::new)
//                    .model(ModItemModelProvider.genericItemModel(true, "components", "_"))
//                    .tag(PrmaTags.ItemTag.WEAPON_COMPONENTS.tag).register()),
//            STRAIGHT_LARGE_COIL = addToMaterials(PreciseManufacturing.REGISTRATE.item("straight_large_coil", Item::new)
//                    .model(ModItemModelProvider.genericItemModel(true, "components", "_"))
//                    .tag(PrmaTags.ItemTag.WEAPON_COMPONENTS.tag).register()),
//            STRAIGHT_FLAT_COIL = addToMaterials(PreciseManufacturing.REGISTRATE.item("straight_flat_coil", Item::new)
//                    .model(ModItemModelProvider.genericItemModel(true, "components", "_"))
//                    .tag(PrmaTags.ItemTag.WEAPON_COMPONENTS.tag).register()),
//            LOCKING_RETURN_COIL = addToMaterials(PreciseManufacturing.REGISTRATE.item("locking_return_coil", Item::new)
//                    .model(ModItemModelProvider.genericItemModel(true, "components", "_"))
//                    .tag(PrmaTags.ItemTag.WEAPON_COMPONENTS.tag).register()),
//            FLAT_HEAD_SCREW = addToMaterials(PreciseManufacturing.REGISTRATE.item("flat_head_screw", Item::new)
//                    .model(ModItemModelProvider.genericItemModel(true, "components", "_"))
//                    .tag(PrmaTags.ItemTag.WEAPON_COMPONENTS.tag).register()),
//            M_SCREW = addToMaterials(PreciseManufacturing.REGISTRATE.item("m_screw", Item::new)
//                    .model(ModItemModelProvider.genericItemModel(true, "components", "_"))
//                    .tag(PrmaTags.ItemTag.WEAPON_COMPONENTS.tag).register()),
//            THIN_SMALL_ROD = addToMaterials(PreciseManufacturing.REGISTRATE.item("thin_small_rod", Item::new)
//                    .model(ModItemModelProvider.genericItemModel(true, "components", "_"))
//                    .tag(PrmaTags.ItemTag.WEAPON_COMPONENTS.tag).register()),
//            THICK_SMALL_ROD = addToMaterials(PreciseManufacturing.REGISTRATE.item("thick_small_rod", Item::new)
//                    .model(ModItemModelProvider.genericItemModel(true, "components", "_"))
//                    .tag(PrmaTags.ItemTag.WEAPON_COMPONENTS.tag).register()),
//            CRUSHED_BASALT = addToMaterials(PreciseManufacturing.REGISTRATE.item("crushed_basalt", Item::new)
//                    .register()),
//            BLANK_BLUEPRINT = addToMaterials(PreciseManufacturing.REGISTRATE.item("blank_blueprint", Item::new)
//                    .register()),
//            BLANK_CAST = addToMaterials(PreciseManufacturing.REGISTRATE.item("blank_cast", Item::new)
//                    .register());

    // Metals
//    public static final RegistryEntry<Item>
//            LEAD_INGOT = addToMaterials(PreciseManufacturing.REGISTRATE.item("lead_ingot", Item::new)
//                    .model(ModItemModelProvider.genericItemModel(true, "metals", "_"))
//                    .tag(PrmaTags.ItemTag.INGOTS.tag).register()),
//            ALUMINUM_INGOT = addToMaterials(PreciseManufacturing.REGISTRATE.item("aluminum_ingot", Item::new)
//                    .model(ModItemModelProvider.genericItemModel(true, "metals", "_"))
//                    .tag(PrmaTags.ItemTag.INGOTS.tag).register()),
//            STRONG_ALUMINUM_ALLOY_INGOT = addToMaterials(PreciseManufacturing.REGISTRATE.item("strong_aluminum_alloy_ingot", Item::new)
//                    .model(ModItemModelProvider.genericItemModel(true, "metals", "_"))
//                    .tag(PrmaTags.ItemTag.INGOTS.tag).register()),
//            CRUSHED_RAW_ALUMINUM = addToMaterials(PreciseManufacturing.REGISTRATE.item("crushed_raw_aluminum", Item::new)
//                    .model(ModItemModelProvider.genericItemModel(true, "metals", "_"))
//                    .tag(PrmaTags.ItemTag.CRUSHED_ORES.tag).tag(AllTags.AllItemTags.CRUSHED_RAW_MATERIALS.tag)
//                    .register()),
//            CRUSHED_RAW_LEAD = addToMaterials(PreciseManufacturing.REGISTRATE.item("crushed_raw_lead", Item::new)
//                    .model(ModItemModelProvider.genericItemModel(true, "metals", "_"))
//                    .tag(PrmaTags.ItemTag.CRUSHED_ORES.tag).tag(AllTags.AllItemTags.CRUSHED_RAW_MATERIALS.tag)
//                    .register());

    // Buckets
//    public static final RegistryEntry<Item>
//            MOLTEN_COPPER_BUCKET = addToList(bucketItem("molten_copper_bucket").register()),
//            MOLTEN_ZINC_BUCKET = addToList(bucketItem("molten_zinc_bucket").register()),
//            MOLTEN_IRON_BUCKET = addToList(bucketItem("molten_iron_bucket").register()),
//            MOLTEN_ALUMINUM_BUCKET = addToList(bucketItem("molten_aluminum_bucket").register()),
//            MOLTEN_STRONG_ALUMINUM_ALLOY_BUCKET = addToList(bucketItem("molten_strong_aluminum_alloy_bucket").register()),
//            MOLTEN_METAL_ALLOY_BUCKET = addToList(bucketItem("molten_metal_alloy_bucket").register());

    public static void register() {
        // Do no delete this function; This is for loading this class.
//        PreciseManufacturing.LOGGER.debug("{}", PrmaItems.SMALL_AMMUNITION_GUNPOWDER.get());
        Ammo.register();
        Cast.register();
        PreciseManufacturing.LOGGER.info("Registering Mod Items");
    }

}
