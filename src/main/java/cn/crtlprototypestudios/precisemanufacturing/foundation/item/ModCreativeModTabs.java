package cn.crtlprototypestudios.precisemanufacturing.foundation.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModCreativeModTabs {
    public static final CreativeModeTab MOD_TAB = new CreativeModeTab("prma_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.MEDIUM_SIZED_ROLLER.get());
        }
    };
}
