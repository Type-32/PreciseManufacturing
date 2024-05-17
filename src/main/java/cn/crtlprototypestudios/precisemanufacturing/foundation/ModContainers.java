package cn.crtlprototypestudios.precisemanufacturing.foundation;

import cn.crtlprototypestudios.precisemanufacturing.foundation.client.gui.decomponentalizer.DecomponentalizerContainerMenu;
import cn.crtlprototypestudios.precisemanufacturing.util.Reference;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModContainers {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.CONTAINERS, Reference.MOD_ID);

    public static final RegistryObject<MenuType<DecomponentalizerContainerMenu>> DECOMPONENTALIZER = registerMenuType(DecomponentalizerContainerMenu::new, "decomponentalizer_container");

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory, String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}