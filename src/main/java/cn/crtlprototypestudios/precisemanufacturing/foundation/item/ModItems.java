package cn.crtlprototypestudios.precisemanufacturing.foundation.item;

import cn.crtlprototypestudios.precisemanufacturing.Main;
import cn.crtlprototypestudios.precisemanufacturing.util.Reference;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

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

    public static final RegistryEntry<Item>
            CRUSHED_BASALT = Main.REGISTRATE.item("crushed_basalt", Item::new).register(),
            UNFORMED_BASALT = Main.REGISTRATE.item("unformed_basalt", Item::new).register();


    // Buckets
    public static final RegistryEntry<Item>
            MOLTEN_BRASS_BUCKET = Main.REGISTRATE.item("molten_brass_bucket", Item::new)
                    .properties(p -> p.stacksTo(1))
                    .register(),
            MOLTEN_COPPER_BUCKET = Main.REGISTRATE.item("molten_copper_bucket", Item::new)
                    .properties(p -> p.stacksTo(1))
                    .register(),
            MOLTEN_BASALT_INFUSED_IRON_BUCKET = Main.REGISTRATE.item("molten_basalt_infused_bucket", Item::new)
                    .properties(p -> p.stacksTo(1))
                    .register();


    // Casts
    // Ammunition Casings
    public static final RegistryEntry<Item>
            NINE_MIL_CASING_CAST = Main.REGISTRATE.item("9mm_casing_cast", Item::new)
                    .properties(p -> p.fireResistant())
                    .register(),
            FOUR_FIVE_ACP_CASING_CAST = Main.REGISTRATE.item("45acp_casing_cast", Item::new)
                    .properties(p -> p.fireResistant())
                    .register(),
            FIFTY_AE_CASING_CAST = Main.REGISTRATE.item("50acp_casing_cast", Item::new)
                    .properties(p -> p.fireResistant())
                    .register(),
            MAGNUM_R_CASING_CAST = Main.REGISTRATE.item("magnum_r_casing_cast", Item::new)
                    .properties(p -> p.fireResistant())
                    .register(),
            TWELVE_G_CASING_CAST = Main.REGISTRATE.item("12g_casing_cast", Item::new)
                    .properties(p -> p.fireResistant())
                    .register(),
            THIRTY_ZERO_SIX_CASING_CAST = Main.REGISTRATE.item("30_06_casing_cast", Item::new)
                    .properties(p -> p.fireResistant())
                    .register(),
            FOUR_SIX_X_THIRTY_CASING_CAST = Main.REGISTRATE.item("46x30_casing_cast", Item::new)
                    .properties(p -> p.fireResistant())
                    .register(),
            FIFTY_BMG_CASING_CAST = Main.REGISTRATE.item("50bmg_casing_cast", Item::new)
                    .properties(p -> p.fireResistant())
                    .register(),
            FIVE_EIGHT_X_FOUR_TWO_CASING_CAST = Main.REGISTRATE.item("58x42_casing_cast", Item::new)
                    .properties(p -> p.fireResistant())
                    .register(),
            SIX_EIGHT_X_FIVE_ONE_FURY_CASING_CAST = Main.REGISTRATE.item("68x51fury_casing_cast", Item::new)
                    .properties(p -> p.fireResistant())
                    .register(),
            RPG_ROCKET_CASING_CAST = Main.REGISTRATE.item("rpg_rocket_casing_cast", Item::new)
                    .properties(p -> p.fireResistant())
                    .register(),
            THREE_ZERO_EIGHT_CASING_CAST = Main.REGISTRATE.item("308_casing_cast", Item::new)
                    .properties(p -> p.fireResistant())
                    .register(),
            THREE_THREE_EIGHT_CASING_CAST = Main.REGISTRATE.item("338_casing_cast", Item::new)
                    .properties(p -> p.fireResistant())
                    .register(),
            FIVE_FIVE_SIX_X_FOUR_FIVE_CASING_CAST = Main.REGISTRATE.item("556x45_casing_cast", Item::new)
                    .properties(p -> p.fireResistant())
                    .register(),
            SEVEN_SIX_TWO_X_TWO_FIVE_CASING_CAST = Main.REGISTRATE.item("762x25_casing_cast", Item::new)
                    .properties(p -> p.fireResistant())
                    .register(),
            SEVEN_SIX_TWO_X_THREE_NINE_CASING_CAST = Main.REGISTRATE.item("762x39_casing_cast", Item::new)
                    .properties(p -> p.fireResistant())
                    .register(),
            SEVEN_SIX_TWO_X_FIVE_FOUR_CASING_CAST = Main.REGISTRATE.item("762x54_casing_cast", Item::new)
                    .properties(p -> p.fireResistant())
                    .register();

    // Ammunition Heads
    public static final RegistryEntry<Item>
            NINE_MIL_HEAD_CAST = Main.REGISTRATE.item("9mm_head_cast", Item::new)
                    .properties(p -> p.fireResistant())
                    .register(),
            FOUR_FIVE_ACP_HEAD_CAST = Main.REGISTRATE.item("45acp_head_cast", Item::new)
                    .properties(p -> p.fireResistant())
                    .register(),
            FIFTY_AE_HEAD_CAST = Main.REGISTRATE.item("50ae_head_cast", Item::new)
                    .properties(p -> p.fireResistant())
                    .register(),
            MAGNUM_R_HEAD_CAST = Main.REGISTRATE.item("magnum_r_head_cast", Item::new)
                    .properties(p -> p.fireResistant())
                    .register(),
            TWELVE_G_HEAD_CAST = Main.REGISTRATE.item("12g_head_cast", Item::new)
                    .properties(p -> p.fireResistant())
                    .register(),
            THIRTY_ZERO_SIX_HEAD_CAST = Main.REGISTRATE.item("30_06_head_cast", Item::new)
                    .properties(p -> p.fireResistant())
                    .register(),
            FOUR_SIX_X_THIRTY_HEAD_CAST = Main.REGISTRATE.item("46x30_head_cast", Item::new)
                    .properties(p -> p.fireResistant())
                    .register(),
            FIFTY_BMG_HEAD_CAST = Main.REGISTRATE.item("50bmg_head_cast", Item::new)
                    .properties(p -> p.fireResistant())
                    .register(),
            FIVE_EIGHT_X_FOUR_TWO_HEAD_CAST = Main.REGISTRATE.item("58x42_head_cast", Item::new)
                    .properties(p -> p.fireResistant())
                    .register(),
            SIX_EIGHT_X_FIVE_ONE_FURY_HEAD_CAST = Main.REGISTRATE.item("68x51fury_head_cast", Item::new)
                    .properties(p -> p.fireResistant())
                    .register(),
            RPG_ROCKET_HEAD_CAST = Main.REGISTRATE.item("rpg_rocket_head_cast", Item::new)
                    .properties(p -> p.fireResistant())
                    .register(),
            THREE_ZERO_EIGHT_HEAD_CAST = Main.REGISTRATE.item("308_head_cast", Item::new)
                    .properties(p -> p.fireResistant())
                    .register(),
            THREE_THREE_EIGHT_HEAD_CAST = Main.REGISTRATE.item("338_head_cast", Item::new)
                    .properties(p -> p.fireResistant())
                    .register(),
            FIVE_FIVE_SIX_X_FOUR_FIVE_HEAD_CAST = Main.REGISTRATE.item("556x45_head_cast", Item::new)
                    .properties(p -> p.fireResistant())
                    .register(),
            SEVEN_SIX_TWO_X_TWO_FIVE_HEAD_CAST = Main.REGISTRATE.item("762x25_head_cast", Item::new)
                    .properties(p -> p.fireResistant())
                    .register(),
            SEVEN_SIX_TWO_X_THREE_NINE_HEAD_CAST = Main.REGISTRATE.item("762x39_head_cast", Item::new)
                    .properties(p -> p.fireResistant())
                    .register(),
            SEVEN_SIX_TWO_X_FIVE_FOUR_HEAD_CAST = Main.REGISTRATE.item("762x54_head_cast", Item::new)
                    .properties(p -> p.fireResistant())
                    .register();


    // Ammunition Parts
    // Casings
    public static final RegistryEntry<Item>
            NINE_MIL_CASING = Main.REGISTRATE.item("9mm_casing", Item::new).register(),
            FOUR_FIVE_ACP_CASING = Main.REGISTRATE.item("45acp_casing", Item::new).register(),
            FIFTY_AE_CASING = Main.REGISTRATE.item("50ae_casing", Item::new).register(),
            MAGNUM_R_CASING = Main.REGISTRATE.item("magnum_r_casing", Item::new).register(),
            TWELVE_G_CASING = Main.REGISTRATE.item("12g_casing", Item::new).register(),
            THIRTY_ZERO_SIX_CASING = Main.REGISTRATE.item("30_06_casing", Item::new).register(),
            FOUR_SIX_X_THIRTY_CASING = Main.REGISTRATE.item("46x30_casing", Item::new).register(),
            FIFTY_BMG_CASING = Main.REGISTRATE.item("50bmg_casing", Item::new).register(),
            FIVE_EIGHT_X_FOUR_TWO_CASING = Main.REGISTRATE.item("58x42_casing", Item::new).register(),
            SIX_EIGHT_X_FIVE_ONE_FURY_CASING = Main.REGISTRATE.item("68x51fury_casing", Item::new).register(),
            RPG_ROCKET_CASING = Main.REGISTRATE.item("rpg_rocket_casing", Item::new).register(),
            THREE_ZERO_EIGHT_CASING = Main.REGISTRATE.item("308_casing", Item::new).register(),
            THREE_THREE_EIGHT_CASING = Main.REGISTRATE.item("338_casing", Item::new).register(),
            FIVE_FIVE_SIX_X_FOUR_FIVE_CASING = Main.REGISTRATE.item("556x45_casing", Item::new).register(),
            SEVEN_SIX_TWO_X_TWO_FIVE_CASING = Main.REGISTRATE.item("762x25_casing", Item::new).register(),
            SEVEN_SIX_TWO_X_THREE_NINE_CASING = Main.REGISTRATE.item("762x39_casing", Item::new).register(),
            SEVEN_SIX_TWO_X_FIVE_FOUR_CASING = Main.REGISTRATE.item("762x54_casing", Item::new).register();

    // Heads
    public static final RegistryEntry<Item>
            NINE_MIL_HEAD = Main.REGISTRATE.item("9mm_head", Item::new).register(),
            FOUR_FIVE_ACP_HEAD = Main.REGISTRATE.item("45acp_head", Item::new).register(),
            FIFTY_AE_HEAD = Main.REGISTRATE.item("50ae_head", Item::new).register(),
            MAGNUM_R_HEAD = Main.REGISTRATE.item("magnum_r_head", Item::new).register(),
            TWELVE_G_HEAD = Main.REGISTRATE.item("12g_head", Item::new).register(),
            THIRTY_ZERO_SIX_HEAD = Main.REGISTRATE.item("30_06_head", Item::new).register(),
            FOUR_SIX_X_THIRTY_HEAD = Main.REGISTRATE.item("46x30_head", Item::new).register(),
            FIFTY_BMG_HEAD = Main.REGISTRATE.item("50bmg_head", Item::new).register(),
            FIVE_EIGHT_X_FOUR_TWO_HEAD = Main.REGISTRATE.item("58x42_head", Item::new).register(),
            SIX_EIGHT_X_FIVE_ONE_FURY_HEAD = Main.REGISTRATE.item("68x51fury_head", Item::new).register(),
            RPG_ROCKET_HEAD = Main.REGISTRATE.item("rpg_rocket_head", Item::new).register(),
            THREE_ZERO_EIGHT_HEAD = Main.REGISTRATE.item("308_head", Item::new).register(),
            THREE_THREE_EIGHT_HEAD = Main.REGISTRATE.item("338_head", Item::new).register(),
            FIVE_FIVE_SIX_X_FOUR_FIVE_HEAD = Main.REGISTRATE.item("556x45_head", Item::new).register(),
            SEVEN_SIX_TWO_X_TWO_FIVE_HEAD = Main.REGISTRATE.item("762x25_head", Item::new).register(),
            SEVEN_SIX_TWO_X_THREE_NINE_HEAD = Main.REGISTRATE.item("762x39_head", Item::new).register(),
            SEVEN_SIX_TWO_X_FIVE_FOUR_HEAD = Main.REGISTRATE.item("762x54_head", Item::new).register();

    // Unfinished
    public static final RegistryEntry<Item>
            NINE_MIL_UNFINISHED = Main.REGISTRATE.item("9mm_unfinished", Item::new).register(),
            FOUR_FIVE_ACP_UNFINISHED = Main.REGISTRATE.item("45acp_unfinished", Item::new).register(),
            FIFTY_AE_UNFINISHED = Main.REGISTRATE.item("50ae_unfinished", Item::new).register(),
            MAGNUM_R_UNFINISHED = Main.REGISTRATE.item("magnum_r_unfinished", Item::new).register(),
            TWELVE_G_UNFINISHED = Main.REGISTRATE.item("12g_unfinished", Item::new).register(),
            THIRTY_ZERO_SIX_UNFINISHED = Main.REGISTRATE.item("30_06_unfinished", Item::new).register(),
            FOUR_SIX_X_THIRTY_UNFINISHED = Main.REGISTRATE.item("46x30_unfinished", Item::new).register(),
            FIFTY_BMG_UNFINISHED = Main.REGISTRATE.item("50bmg_unfinished", Item::new).register(),
            FIVE_EIGHT_X_FOUR_TWO_UNFINISHED = Main.REGISTRATE.item("58x42_unfinished", Item::new).register(),
            SIX_EIGHT_X_FIVE_ONE_FURY_UNFINISHED = Main.REGISTRATE.item("68x51fury_unfinished", Item::new).register(),
            RPG_ROCKET_UNFINISHED = Main.REGISTRATE.item("rpg_rocket_unfinished", Item::new).register(),
            THREE_ZERO_EIGHT_UNFINISHED = Main.REGISTRATE.item("308_unfinished", Item::new).register(),
            THREE_THREE_EIGHT_UNFINISHED = Main.REGISTRATE.item("338_unfinished", Item::new).register(),
            FIVE_FIVE_SIX_X_FOUR_FIVE_UNFINISHED = Main.REGISTRATE.item("556x45_unfinished", Item::new).register(),
            SEVEN_SIX_TWO_X_TWO_FIVE_UNFINISHED = Main.REGISTRATE.item("762x25_unfinished", Item::new).register(),
            SEVEN_SIX_TWO_X_THREE_NINE_UNFINISHED = Main.REGISTRATE.item("762x39_unfinished", Item::new).register(),
            SEVEN_SIX_TWO_X_FIVE_FOUR_UNFINISHED = Main.REGISTRATE.item("762x54_unfinished", Item::new).register();

    public static void register(){
        // Do no delete this function; This is for loading this class.
        Main.LOGGER.info("Registering Mod Items");
    }
}
