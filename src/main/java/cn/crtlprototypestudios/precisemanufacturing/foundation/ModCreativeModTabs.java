package cn.crtlprototypestudios.precisemanufacturing.foundation;

import cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.ammunition.CartridgeModuleType;
import cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.weapon.RifleModuleType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModTabs {
    public static final CreativeModeTab MOD_TAB = new CreativeModeTab("prma_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.CRUSHED_BASALT.get());
        }
    };

    public static final CreativeModeTab MOD_CASTS_TAB = new CreativeModeTab("prma_casts_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.FOUR_FIVE_ACP.registry.get(CartridgeModuleType.HEAD).get());
        }
    };

    public static final CreativeModeTab MOD_COMPONENTS_TAB = new CreativeModeTab("prma_components_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.M4A1.registry.get(RifleModuleType.GRIP).get());
        }
    };

    public static void register(){
        // Do not delete
    }
}
