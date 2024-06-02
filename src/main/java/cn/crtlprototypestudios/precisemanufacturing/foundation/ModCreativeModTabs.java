package cn.crtlprototypestudios.precisemanufacturing.foundation;

import cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.ammunition.CartridgeModuleType;
import cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.weapon.RifleModule;
import cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.weapon.RifleModuleType;
import cn.crtlprototypestudios.precisemanufacturing.util.Reference;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

public class ModCreativeModTabs {

    // A Note to future me:
    // The time that when this class is initialized, the RifleBase and CartridgeBases are not yet generated, thus no
    // stored sub-registry items for those corresponding items. One probable fix for this might be moving the items
    // registry in front of this registry, but that wouldn't make sense since the items registry are already dependent
    // on the mod tab at the start.

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Reference.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MOD_TAB = CREATIVE_MODE_TABS.register("prma_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.CRUSHED_BASALT.get()))
                    .title(Component.translatable("itemGroup.prma_tab"))
                    .build());

    public static final RegistryObject<CreativeModeTab> MOD_CASTS_TAB = CREATIVE_MODE_TABS.register("prma_casts_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.BLANK_CAST.get()))
                    .title(Component.translatable("itemGroup.prma_casts_tab"))
                    .build());

    public static final RegistryObject<CreativeModeTab> MOD_COMPONENTS_TAB = CREATIVE_MODE_TABS.register("prma_components_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.M_SCREW.get()))
                    .title(Component.translatable("itemGroup.prma_components_tab"))
                    .build());

    public static final RegistryObject<CreativeModeTab> MOD_BLUEPRINTS_TAB = CREATIVE_MODE_TABS.register("prma_blueprints_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.BLANK_BLUEPRINT.get()))
                    .title(Component.translatable("itemGroup.prma_blueprints_tab"))
                    .build());

    public static final RegistryObject<CreativeModeTab> MOD_HIDDEN_TAB = CREATIVE_MODE_TABS.register("prma_hidden_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(Items.AIR))
            .title(Component.translatable("itemGroup.prma_hidden_tab"))
            .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
