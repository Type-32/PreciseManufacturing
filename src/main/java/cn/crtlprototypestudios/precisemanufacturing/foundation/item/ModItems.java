package cn.crtlprototypestudios.precisemanufacturing.foundation.item;

import cn.crtlprototypestudios.precisemanufacturing.Main;
import cn.crtlprototypestudios.precisemanufacturing.util.Reference;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);

    public static final RegistryObject<Item> MEDIUM_SIZED_ROLLER = ITEMS.register("medium_sized_roller", () -> new Item(new Item.Properties()
            .defaultDurability(200)
            .durability(200)
            .fireResistant()
            .stacksTo(1)
            .tab(ModCreativeModTabs.MOD_TAB)));

    public static void register(IEventBus bus){
        ITEMS.register(bus);
    }
}
