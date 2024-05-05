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
                    .register();


    // Ammunition Parts
    // Casings
    public static final RegistryEntry<Item>
            NINE_MIL_CASING = Main.REGISTRATE.item("9mm_casing", Item::new).register(),
            FOUR_FIVE_ACP_CASING = Main.REGISTRATE.item("45acp_casing", Item::new).register(),
            FIFTY_AE_CASING = Main.REGISTRATE.item("50ae_casing", Item::new).register();

    // Heads
    public static final RegistryEntry<Item>
            NINE_MIL_HEAD = Main.REGISTRATE.item("9mm_head", Item::new).register(),
            FOUR_FIVE_ACP_HEAD = Main.REGISTRATE.item("45acp_head", Item::new).register(),
            FIFTY_AE_HEAD = Main.REGISTRATE.item("50ae_head", Item::new).register();

    // Unfinished
    public static final RegistryEntry<Item>
            NINE_MIL_UNFINISHED = Main.REGISTRATE.item("9mm_unfinished", Item::new).register(),
            FOUR_FIVE_ACP_UNFINISHED = Main.REGISTRATE.item("45acp_unfinished", Item::new).register(),
            FIFTY_AE_UNFINISHED = Main.REGISTRATE.item("50ae_unfinished", Item::new).register();

    public static void register(){
        // Do no delete this function; This is for loading this class.
        Main.LOGGER.info("Registering Mod Items");
    }
}
