package cn.crtlprototypestudios.precisemanufacturing.foundation.item;

import cn.crtlprototypestudios.precisemanufacturing.Main;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModCreativeModTabs {
    public static final CreativeModeTab MOD_TAB = new CreativeModeTab("prma_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.CRUSHED_BASALT.get());
        }
    };

    public static void register(){
        // Do not delete
    }
}
