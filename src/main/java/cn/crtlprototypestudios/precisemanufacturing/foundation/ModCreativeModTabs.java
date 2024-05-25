package cn.crtlprototypestudios.precisemanufacturing.foundation;

import cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.ammunition.CartridgeModuleType;
import cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.weapon.RifleModule;
import cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.weapon.RifleModuleType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ModCreativeModTabs {

    // A Note to future me:
    // The time that when this class is initialized, the RifleBase and CartridgeBases are not yet generated, thus no
    // stored sub-registry items for those corresponding items. One probable fix for this might be moving the items
    // registry in front of this registry, but that wouldn't make sense since the items registry are already dependent
    // on the mod tab at the start.

    public static final CreativeModeTab MOD_TAB = new CreativeModeTab("prma_tab") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(ModItems.CRUSHED_BASALT.get());
        }
    };

    public static final CreativeModeTab MOD_CASTS_TAB = new CreativeModeTab("prma_casts_tab") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(ModItems.BLANK_CAST.get());
        }
    };

    public static final CreativeModeTab MOD_COMPONENTS_TAB = new CreativeModeTab("prma_components_tab") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(ModItems.M_SCREW.get());
        }
    };

    public static final CreativeModeTab MOD_BLUEPRINTS_TAB = new CreativeModeTab("prma_blueprints_tab") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(ModItems.BLANK_BLUEPRINT.get());
        }
    };
}
