package cn.crtlprototypestudios.precisemanufacturing.foundation;

import cn.crtlprototypestudios.precisemanufacturing.Main;
import cn.crtlprototypestudios.precisemanufacturing.foundation.data.providers.ModItemModelProvider;
import cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.ammunition.CartridgeAssemblySequence;
import cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.ammunition.CartridgeBase;
import cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.ammunition.CartridgeModuleBuilder;
import cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.weapon.RifleBase;
import cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.weapon.RifleModule;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ModItems {

    static {
        // Set that all registered Items and Blocks defaults under this Mod's creative tabs
        Main.REGISTRATE.creativeModeTab(() -> ModCreativeModTabs.MOD_TAB);
    }

//    public static final ItemStack TACZ_AMMO_ITEM_TEMPLATE = new ItemStack(ModCompatItems.AMMO);
//    public static final ItemStack TACZ_GUN_ITEM_TEMPLATE = new ItemStack(ModCompatItems.MODERN_KINETIC_GUN);

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
            THICK_SMALL_ROD = Main.REGISTRATE.item("thick_small_rod", Item::new).register(),
            BLANK_BLUEPRINT = Main.REGISTRATE.item("blank_blueprint", Item::new).register(),
            BLANK_CAST = Main.REGISTRATE.item("blank_cast", Item::new).register();


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
                    .insertAssemblySequence(1, CartridgeAssemblySequence.GUNPOWDER_PELLET))
                    .setModuleData(0, d -> d.setFillingAmount(75)),

            TWELVE_G = new CartridgeBase("12g", CartridgeBase.SHOTGUN_CARTRIDGE)
                    .setModuleData(0, d -> d.setFillingAmount(75))
                    .setModuleData(1, d -> d.setFillingAmount(40)),

            THIRTY_ZERO_SIX = new CartridgeBase("30_06", CartridgeBase.STANDARD_CARTRIDGE
                    .replaceAssemblySequence(CartridgeAssemblySequence.GUNPOWDER, CartridgeAssemblySequence.GUNPOWDER_PELLET)
                    .insertAssemblySequence(1, CartridgeAssemblySequence.GUNPOWDER_PELLET)
                    .insertAssemblySequence(1, CartridgeAssemblySequence.GUNPOWDER))
                    .setModuleData(0, d -> d.setFillingAmount(100))
                    .setModuleData(1, d -> d.setFillingAmount(80)),

            FOUR_SIX_X_THIRTY = new CartridgeBase("46x30", CartridgeBase.STANDARD_CARTRIDGE
                    .replaceAssemblySequence(CartridgeAssemblySequence.GUNPOWDER, CartridgeAssemblySequence.GUNPOWDER_PELLET)
                    .insertAssemblySequence(1, CartridgeAssemblySequence.GUNPOWDER_PELLET))
                    .setModuleData(0, d -> d.setFillingAmount(90))
                    .setModuleData(1, d -> d.setFillingAmount(50)),

            FIFTY_BMG = new CartridgeBase("50bmg", CartridgeBase.STANDARD_CARTRIDGE
                    .replaceAssemblySequence(CartridgeAssemblySequence.GUNPOWDER, CartridgeAssemblySequence.GUNPOWDER_PELLET)
                    .insertAssemblySequence(1, CartridgeAssemblySequence.GUNPOWDER_PELLET)
                    .insertAssemblySequence(1, CartridgeAssemblySequence.GUNPOWDER_PELLET))
                    .setModuleData(0, d -> d.setFillingAmount(110).setFillingFluid(ModFluids.MOLTEN_BASALT_INFUSED_IRON))
                    .setModuleData(1, d -> d.setFillingAmount(90)),

            FIVE_EIGHT_X_FOUR_TWO = new CartridgeBase("58x42", CartridgeBase.STANDARD_CARTRIDGE
                    .replaceAssemblySequence(CartridgeAssemblySequence.GUNPOWDER, CartridgeAssemblySequence.GUNPOWDER_PELLET)
                    .insertAssemblySequence(1, CartridgeAssemblySequence.GUNPOWDER_PELLET))
                    .setModuleData(0, d -> d.setFillingAmount(100).setFillingFluid(ModFluids.MOLTEN_BASALT_INFUSED_IRON))
                    .setModuleData(1, d -> d.setFillingAmount(70)),

            SIX_EIGHT_X_FIVE_ONE_FURY = new CartridgeBase("68x51fury", CartridgeBase.STANDARD_CARTRIDGE
                    .replaceAssemblySequence(CartridgeAssemblySequence.GUNPOWDER, CartridgeAssemblySequence.GUNPOWDER_PELLET)
                    .insertAssemblySequence(1, CartridgeAssemblySequence.GUNPOWDER_PELLET))
                    .setModuleData(0, d -> d.setFillingAmount(90).setFillingFluid(ModFluids.MOLTEN_BASALT_INFUSED_IRON))
                    .setModuleData(1, d -> d.setFillingAmount(80)),

            RPG_ROCKET = new CartridgeBase("rpg_rocket", CartridgeBase.ROCKET_CARTRIDGE),

            THREE_ZERO_EIGHT = new CartridgeBase("308", CartridgeBase.STANDARD_CARTRIDGE
                    .replaceAssemblySequence(CartridgeAssemblySequence.GUNPOWDER, CartridgeAssemblySequence.GUNPOWDER_PELLET)
                    .insertAssemblySequence(1, CartridgeAssemblySequence.GUNPOWDER_PELLET)
                    .insertAssemblySequence(1, CartridgeAssemblySequence.GUNPOWDER))
                    .setModuleData(0, d -> d.setFillingAmount(100))
                    .setModuleData(1, d -> d.setFillingAmount(80)),

            THREE_THREE_EIGHT = new CartridgeBase("338", CartridgeBase.STANDARD_CARTRIDGE
                    .replaceAssemblySequence(CartridgeAssemblySequence.GUNPOWDER, CartridgeAssemblySequence.GUNPOWDER_PELLET)
                    .insertAssemblySequence(1, CartridgeAssemblySequence.GUNPOWDER_PELLET)
                    .insertAssemblySequence(1, CartridgeAssemblySequence.GUNPOWDER))
                    .setModuleData(0, d -> d.setFillingAmount(100))
                    .setModuleData(1, d -> d.setFillingAmount(80)),

            FIVE_FIVE_SIX_X_FOUR_FIVE = new CartridgeBase("556x45", CartridgeBase.STANDARD_CARTRIDGE
                    .replaceAssemblySequence(CartridgeAssemblySequence.GUNPOWDER, CartridgeAssemblySequence.GUNPOWDER_PELLET)
                    .insertAssemblySequence(1, CartridgeAssemblySequence.GUNPOWDER_PELLET))
                    .setModuleData(0, d -> d.setFillingAmount(90))
                    .setModuleData(1, d -> d.setFillingAmount(60)),

            SEVEN_SIX_TWO_X_TWO_FIVE = new CartridgeBase("762x25", CartridgeBase.STANDARD_CARTRIDGE
                    .replaceAssemblySequence(CartridgeAssemblySequence.GUNPOWDER, CartridgeAssemblySequence.GUNPOWDER_PELLET)
                    .insertAssemblySequence(1, CartridgeAssemblySequence.GUNPOWDER_PELLET))
                    .setModuleData(0, d -> d.setFillingAmount(90))
                    .setModuleData(1, d -> d.setFillingAmount(60)),

            SEVEN_SIX_TWO_X_THREE_NINE = new CartridgeBase("762x39", CartridgeBase.STANDARD_CARTRIDGE
                    .replaceAssemblySequence(CartridgeAssemblySequence.GUNPOWDER, CartridgeAssemblySequence.GUNPOWDER_PELLET)
                    .insertAssemblySequence(1, CartridgeAssemblySequence.GUNPOWDER_PELLET))
                    .setModuleData(0, d -> d.setFillingAmount(100).setFillingFluid(ModFluids.MOLTEN_BASALT_INFUSED_IRON))
                    .setModuleData(1, d -> d.setFillingAmount(60)),

            SEVEN_SIX_TWO_X_FIVE_FOUR = new CartridgeBase("762x54", CartridgeBase.STANDARD_CARTRIDGE
                    .replaceAssemblySequence(CartridgeAssemblySequence.GUNPOWDER, CartridgeAssemblySequence.GUNPOWDER_PELLET)
                    .insertAssemblySequence(1, CartridgeAssemblySequence.GUNPOWDER_PELLET))
                    .setModuleData(0, d -> d.setFillingAmount(100))
                    .setModuleData(1, d -> d.setFillingAmount(80));

    // Weapons
    // Guns
    public static final RifleBase
            M4A1 = new RifleBase("m4a1", RifleBase.STANDARD_RIFLE_MODULES)
            .setModuleData(0, d -> d.setCastFillingAmount(150)) // Grip
            .setModuleData(1, d -> d.setCastFillingAmount(500)) // Lower Receiver
            .setModuleData(2, d -> d.setCastFillingAmount(550)) // Upper Receiver
            .setModuleData(3, d -> d.setCastFillingAmount(300)) // Handguard
            .setModuleData(4, d -> d.setCastFillingAmount(250)) // Barrel
            .setModuleData(5, d -> d.setCastFillingAmount(200)) // Magazine
            .setModuleData(6, d -> d.setCastFillingAmount(250)) // Fire Control Group
            .setModuleData(7, d -> d.setCastFillingAmount(80)) // Fire Selector
            .setModuleData(8, d -> d.setCastFillingAmount(80)) // Trigger
            .setModuleData(9, d -> d.setCastFillingAmount(300)), // Stock

    HK_G3 = new RifleBase("hk_g3", RifleBase.STANDARD_RIFLE_MODULES.remove(RifleModule.STOCK_MODULE))
            .setModuleData(0, d -> d.setCastFillingAmount(300)) // Grip
            .setModuleData(1, d -> d.setCastFillingAmount(600)) // Lower Receiver
            .setModuleData(2, d -> d.setCastFillingAmount(650)) // Upper Receiver
            .setModuleData(3, d -> d.setCastFillingAmount(350)) // Handguard
            .setModuleData(4, d -> d.setCastFillingAmount(320)) // Barrel
            .setModuleData(5, d -> d.setCastFillingAmount(180)) // Magazine
            .setModuleData(6, d -> d.setCastFillingAmount(350)) // Fire Control Group
            .setModuleData(7, d -> d.setCastFillingAmount(100)) // Fire Selector
            .setModuleData(8, d -> d.setCastFillingAmount(70)), // Trigger

    AK47 = new RifleBase("ak47", RifleBase.STANDARD_RIFLE_MODULES.remove(RifleModule.STOCK_MODULE))
            .setModuleData(0, d -> d.setCastFillingAmount(150)) // Grip
            .setModuleData(1, d -> d.setCastFillingAmount(350)) // Lower Receiver
            .setModuleData(2, d -> d.setCastFillingAmount(450)) // Upper Receiver
            .setModuleData(3, d -> d.setCastFillingAmount(200)) // Handguard
            .setModuleData(4, d -> d.setCastFillingAmount(200)) // Barrel
            .setModuleData(5, d -> d.setCastFillingAmount(100)) // Magazine
            .setModuleData(6, d -> d.setCastFillingAmount(300)) // Fire Control Group
            .setModuleData(7, d -> d.setCastFillingAmount(110)) // Fire Selector
            .setModuleData(8, d -> d.setCastFillingAmount(70)), // Trigger

    SCAR_H = new RifleBase("scar_h", RifleBase.STANDARD_RIFLE_MODULES)
            .setModuleData(0, d -> d.setCastFillingAmount(350)) // Grip
            .setModuleData(1, d -> d.setCastFillingAmount(800)) // Lower Receiver
            .setModuleData(2, d -> d.setCastFillingAmount(700)) // Upper Receiver
            .setModuleData(3, d -> d.setCastFillingAmount(400)) // Handguard
            .setModuleData(4, d -> d.setCastFillingAmount(360)) // Barrel
            .setModuleData(5, d -> d.setCastFillingAmount(140)) // Magazine
            .setModuleData(6, d -> d.setCastFillingAmount(450)) // Fire Control Group
            .setModuleData(7, d -> d.setCastFillingAmount(100)) // Fire Selector
            .setModuleData(8, d -> d.setCastFillingAmount(80)) // Trigger
            .setModuleData(9, d -> d.setCastFillingAmount(220)), // Stock

    GLOCK_17 = new RifleBase("glock_17", RifleBase.STANDARD_PISTOL_MODULES)
            .setModuleData(0, d -> d.setCastFillingAmount(280)) // Receiver
            .setModuleData(1, d -> d.setCastFillingAmount(260)) // Upper Receiver
            .setModuleData(2, d -> d.setCastFillingAmount(180)) // Magazine
            .setModuleData(3, d -> d.setCastFillingAmount(100)) // Trigger
            .setModuleData(4, d -> d.setCastFillingAmount(80)) // Fire Selector
            .setModuleData(5, d -> d.setCastFillingAmount(220)) // Barrel
            .setModuleData(6, d -> d.setCastFillingAmount(180)), // Grip

    DEAGLE = new RifleBase("deagle", RifleBase.STANDARD_PISTOL_MODULES)
            .setModuleData(0, d -> d.setCastFillingAmount(320)) // Receiver
            .setModuleData(1, d -> d.setCastFillingAmount(300)) // Upper Receiver
            .setModuleData(2, d -> d.setCastFillingAmount(220)) // Magazine
            .setModuleData(3, d -> d.setCastFillingAmount(120)) // Trigger
            .setModuleData(4, d -> d.setCastFillingAmount(80)) // Fire Selector
            .setModuleData(5, d -> d.setCastFillingAmount(260)) // Barrel
            .setModuleData(6, d -> d.setCastFillingAmount(240)), // Grip

    CZ_75 = new RifleBase("cz75", RifleBase.STANDARD_PISTOL_MODULES)
            .setModuleData(0, d -> d.setCastFillingAmount(320)) // Receiver
            .setModuleData(1, d -> d.setCastFillingAmount(220)) // Upper Receiver
            .setModuleData(2, d -> d.setCastFillingAmount(160)) // Magazine
            .setModuleData(3, d -> d.setCastFillingAmount(100)) // Trigger
            .setModuleData(4, d -> d.setCastFillingAmount(100)) // Fire Selector
            .setModuleData(5, d -> d.setCastFillingAmount(260)) // Barrel
            .setModuleData(6, d -> d.setCastFillingAmount(200)), // Grip


    HK_MP5A5 = new RifleBase("hk_mp5a5", RifleBase.STANDARD_RIFLE_MODULES.remove(RifleModule.STOCK_MODULE).remove(RifleModule.HANDGUARD_MODULE))
            .setModuleData(0, d -> d.setCastFillingAmount(150)) // Grip
            .setModuleData(1, d -> d.setCastFillingAmount(300)) // Lower Receiver
            .setModuleData(2, d -> d.setCastFillingAmount(400)) // Upper Receiver
            .setModuleData(3, d -> d.setCastFillingAmount(180)) // Barrel
            .setModuleData(4, d -> d.setCastFillingAmount(110)) // Magazine
            .setModuleData(5, d -> d.setCastFillingAmount(250)) // Fire Control Group
            .setModuleData(6, d -> d.setCastFillingAmount(100)) // Fire Selector
            .setModuleData(7, d -> d.setCastFillingAmount(70)), // Trigger

    VECTOR_45 = new RifleBase("vector45", RifleBase.STANDARD_RIFLE_MODULES.remove(RifleModule.STOCK_MODULE).remove(RifleModule.HANDGUARD_MODULE))
            .setModuleData(0, d -> d.setCastFillingAmount(160)) // Grip
            .setModuleData(1, d -> d.setCastFillingAmount(420)) // Lower Receiver
            .setModuleData(2, d ->   d.setCastFillingAmount(200)) // Upper Receiver
            .setModuleData(3, d -> d.setCastFillingAmount(160)) // Barrel
            .setModuleData(4, d -> d.setCastFillingAmount(150)) // Magazine
            .setModuleData(5, d -> d.setCastFillingAmount(220)) // Fire Control Group
            .setModuleData(6, d -> d.setCastFillingAmount(80)) // Fire Selector
            .setModuleData(7, d -> d.setCastFillingAmount(100)), // Trigger

    UZI = new RifleBase("uzi", RifleBase.STANDARD_RIFLE_MODULES.remove(RifleModule.STOCK_MODULE).remove(RifleModule.HANDGUARD_MODULE).remove(RifleModule.LOWER_RECEIVER_MODULE).remove(RifleModule.UPPER_RECEIVER_MODULE).add(RifleModule.RECEIVER_MODULE))
            .setModuleData(0, d -> d.setCastFillingAmount(160)) // Grip
            .setModuleData(1, d -> d.setCastFillingAmount(150)) // Barrel
            .setModuleData(2, d -> d.setCastFillingAmount(100)) // Magazine
            .setModuleData(3, d -> d.setCastFillingAmount(250)) // Fire Control Group
            .setModuleData(4, d -> d.setCastFillingAmount(100)) // Fire Selector
            .setModuleData(5, d -> d.setCastFillingAmount(80)) // Trigger
            .setModuleData(6, d -> d.setCastFillingAmount(300)), // Receiver

    AWP = new RifleBase("ai_awp", RifleBase.BOLT_ACTION_MODULES.remove(RifleModule.CARTRIDGE_WELL_MODULE).remove(RifleModule.GRIP_MODULE).add(RifleModule.MAGAZINE_MODULE))
            .setModuleData(0, d -> d.setCastFillingAmount(160)) // Bolt
            .setModuleData(1, d -> d.setCastFillingAmount(340)) // Barrel
            .setModuleData(2, d -> d.setCastFillingAmount(780)) // Long Body
            .setModuleData(3, d -> d.setCastFillingAmount(100)) // Trigger
            .setModuleData(4, d -> d.setCastFillingAmount(300)) // Stock
            .setModuleData(5, d -> d.setCastFillingAmount(240)), // Magazine

    M95 = new RifleBase("m95", RifleBase.BOLT_ACTION_MODULES.remove(RifleModule.CARTRIDGE_WELL_MODULE).remove(RifleModule.GRIP_MODULE).add(RifleModule.MAGAZINE_MODULE))
            .setModuleData(0, d -> d.setCastFillingAmount(2000)) // Bolt
            .setModuleData(1, d -> d.setCastFillingAmount(420)) // Barrel
            .setModuleData(2, d -> d.setCastFillingAmount(880)) // Long Body
            .setModuleData(3, d -> d.setCastFillingAmount(120)) // Trigger
            .setModuleData(4, d -> d.setCastFillingAmount(400)) // Stock
            .setModuleData(5, d -> d.setCastFillingAmount(240)), // Magazine

    SKS = new RifleBase("sks_tactical", RifleBase.STANDARD_RIFLE_MODULES.remove(RifleModule.LOWER_RECEIVER_MODULE).remove(RifleModule.UPPER_RECEIVER_MODULE).add(RifleModule.LONG_BODY_MODULE))
            .setModuleData(0, d -> d.setCastFillingAmount(150)) // Grip
            .setModuleData(1, d -> d.setCastFillingAmount(200)) // Handguard
            .setModuleData(2, d -> d.setCastFillingAmount(200)) // Barrel
            .setModuleData(3, d -> d.setCastFillingAmount(100)) // Magazine
            .setModuleData(4, d -> d.setCastFillingAmount(300)) // Fire Control Group
            .setModuleData(5, d -> d.setCastFillingAmount(110)) // Fire Selector
            .setModuleData(6, d -> d.setCastFillingAmount(70)) // Trigger
            .setModuleData(7, d -> d.setCastFillingAmount(180)) // Stock
            .setModuleData(8, d -> d.setCastFillingAmount(580)), // Long Body

    M249 = new RifleBase("m249", RifleBase.STANDARD_RIFLE_MODULES.remove(RifleModule.FIRE_SELECTOR_MODULE).remove(RifleModule.FIRE_CONTROL_GROUP_MODULE))
            .setModuleData(0, d -> d.setCastFillingAmount(250)) // Grip
            .setModuleData(1, d -> d.setCastFillingAmount(600)) // Lower Receiver
            .setModuleData(2, d -> d.setCastFillingAmount(600)) // Upper Receiver
            .setModuleData(3, d -> d.setCastFillingAmount(400)) // Handguard
            .setModuleData(4, d -> d.setCastFillingAmount(340)) // Barrel
            .setModuleData(5, d -> d.setCastFillingAmount(380)) // Magazine
            .setModuleData(6, d -> d.setCastFillingAmount(100)) // Trigger
            .setModuleData(7, d -> d.setCastFillingAmount(400)) // Stock
            ;


    public static void register(){
        // Do no delete this function; This is for loading this class.
        Main.LOGGER.info("Registering Mod Items");
    }

//    public static ItemStack buildAmmo(String ammoId){
//        CompoundTag itemTag = new CompoundTag();
//        itemTag.putString("AmmoId", ammoId);
//        ItemStack result = new ItemStack(com.tacz.guns.init.ModItems.AMMO.get());
//        result.setTag(itemTag);
//
//        return result;
//    }
//
//    public static ItemStack buildGun(String gunId){
//        CompoundTag itemTag = new CompoundTag();
//        itemTag.putString("GunId", gunId);
//        ItemStack result = new ItemStack(com.tacz.guns.init.ModItems.MODERN_KINETIC_GUN.get());
//        result.setTag(itemTag);
//
//        return result;
//    }
}
