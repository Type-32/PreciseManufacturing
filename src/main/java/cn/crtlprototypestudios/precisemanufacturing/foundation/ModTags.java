package cn.crtlprototypestudios.precisemanufacturing.foundation;

import cn.crtlprototypestudios.precisemanufacturing.util.Reference;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Collections;

public class ModTags {
    public static <T> TagKey<T> optionalTag(IForgeRegistry<T> registry,
                                                                           ResourceLocation id) {
        return registry.tags()
                .createOptionalTagKey(id, Collections.emptySet());
    }

    public static <T> TagKey<T> forgeTag(IForgeRegistry<T> registry, String path) {
        return optionalTag(registry, new ResourceLocation("forge", path));
    }

    public static <T> TagKey<T> modTag(IForgeRegistry<T> registry, String path) {
        return optionalTag(registry, new ResourceLocation(Reference.MOD_ID, path));
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

    public static TagKey<Item> modItemTag(String path) {
        return modTag(ForgeRegistries.ITEMS, path);
    }

    public static TagKey<Fluid> modFluidTag(String path){
        return modTag(ForgeRegistries.FLUIDS, path);
    }

    public static void register(){
        // Do not delete; for loading the class
    }

    public static TagKey<Item> weaponBlueprintTag(){
        return modItemTag("weapon_blueprints");
    }

    public static TagKey<Item> ammoBlueprintTag(){
        return modItemTag("ammo_blueprints");
    }

    public static TagKey<Item> weaponCastTag(){
        return modItemTag("weapon_casts");
    }

    public static TagKey<Item> ammoCastTag(){
        return modItemTag("ammo_casts");
    }

    public static TagKey<Item> weaponComponentTag(){
        return modItemTag("weapon_components");
    }

    public static TagKey<Item> ammoHeadTag(){
        return modItemTag("ammo_head_components");
    }

    public static TagKey<Item> ammoCasingTag(){
        return modItemTag("ammo_casing_components");
    }

    public static TagKey<Item> componentsTag(){
        return modItemTag("components");
    }

    public static TagKey<Item> materialsTag(){
        return modItemTag("materials");
    }

    public static TagKey<Fluid> moltenIronsTag(){
        return modFluidTag("molten_iron_fluids");
    }

    public static TagKey<Fluid> moltenCoppersTag(){
        return modFluidTag("molten_copper_fluids");
    }

    public static TagKey<Fluid> moltenBrassesTag(){
        return modFluidTag("molten_brass_fluids");
    }
}
