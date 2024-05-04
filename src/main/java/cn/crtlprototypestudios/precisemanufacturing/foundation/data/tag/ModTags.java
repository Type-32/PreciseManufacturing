package cn.crtlprototypestudios.precisemanufacturing.foundation.data.tag;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.Collections;

public class ModTags {
    public static <T extends IForgeRegistryEntry<T>> TagKey<T> optionalTag(IForgeRegistry<T> registry,
                                                                           ResourceLocation id) {
        return registry.tags()
                .createOptionalTagKey(id, Collections.emptySet());
    }

    public static <T extends IForgeRegistryEntry<T>> TagKey<T> forgeTag(IForgeRegistry<T> registry, String path) {
        return optionalTag(registry, new ResourceLocation("forge", path));
    }

    public static TagKey<Block> forgeBlockTag(String path) {
        return forgeTag(ForgeRegistries.BLOCKS, path);
    }

    public static TagKey<Item> forgeItemTag(String path) {
        return forgeTag(ForgeRegistries.ITEMS, path);
    }

    public static TagKey<Fluid> forgeFluidTag(String path) {
        return forgeTag(ForgeRegistries.FLUIDS, path);
    }

    public static void register(){
        // Do not delete; for loading the class
    }
}
