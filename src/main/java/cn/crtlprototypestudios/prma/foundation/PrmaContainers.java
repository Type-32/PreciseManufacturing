package cn.crtlprototypestudios.prma.foundation;

import cn.crtlprototypestudios.prma.lib.Reference;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PrmaContainers {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, Reference.MOD_ID);

//    public static final RegistryObject<MenuType<DecomponentalizerContainerMenu>> DECOMPONENTALIZER_CONTAINER_MENU = registerMenuType(DecomponentalizerContainerMenu::new, "decomponentalizer_container");
//
    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory, String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        // TODO: Soft delete. removing everything related to Decomponentalizers.
//        MENUS.register(eventBus);
    }
}
