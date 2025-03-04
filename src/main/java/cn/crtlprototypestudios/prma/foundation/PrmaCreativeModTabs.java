package cn.crtlprototypestudios.prma.foundation;

import cn.crtlprototypestudios.prma.lib.Reference;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

public class PrmaCreativeModTabs {

    // A Note to future me:
    // The time that when this class is initialized, the RifleBase and CartridgeBases are not yet generated, thus no
    // stored sub-registry items for those corresponding items. One probable fix for this might be moving the items
    // registry in front of this registry, but that wouldn't make sense since the items registry are already dependent
    // on the mod tab at the start.

    public static final CreativeModeTab MOD_TAB = new CreativeModeTab("prma_tab") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(PrmaItems.CRUSHED_BASALT.get());
        }

        @Override
        public @NotNull Component getDisplayName() {
            return new TranslatableComponent("itemGroup.prma_tab");
        }

        @Override
        public void fillItemList(@NotNull NonNullList<ItemStack> pItems) {
            NonNullList<ItemStack> items = NonNullList.create();
            PrmaItems.ALL_ITEMS.forEach(i -> items.add(i.get().getDefaultInstance()));
            pItems.addAll(items);
        }
    };

    public static final CreativeModeTab MOD_MATERIALS_TAB = new CreativeModeTab("prma_materials_tab") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(PrmaItems.BLANK_CAST.get());
        }

        @Override
        public @NotNull Component getDisplayName() {
            return new TranslatableComponent("itemGroup.prma_materials_tab");
        }

        @Override
        public void fillItemList(@NotNull NonNullList<ItemStack> pItems) {
            NonNullList<ItemStack> items = NonNullList.create();
            PrmaItems.ALL_MATERIALS.forEach(i -> items.add(i.get().getDefaultInstance()));
            pItems.addAll(items);
        }
    };

    public static void register(IEventBus eventBus) {

    }

    public static enum Tabs {
        Main,
        Materials
    }
}
