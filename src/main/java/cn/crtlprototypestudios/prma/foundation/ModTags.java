package cn.crtlprototypestudios.prma.foundation;

import cn.crtlprototypestudios.prma.util.Reference;
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

    public static TagKey<Item> weaponCastTag(){
        return modItemTag("weapon_casts");
    }

    public static TagKey<Item> weaponComponentTag(){
        return modItemTag("weapon_components");
    }

    public static TagKey<Item> componentsTag(){
        return modItemTag("components");
    }

    public static TagKey<Item> materialsTag(){
        return modItemTag("materials");
    }

    public static TagKey<Item> cartridgeBlueprintTag() {
        return modItemTag("cartridge_blueprints");
    }

    public static TagKey<Item> smallAmmunitionComponentsTag(){
        return modItemTag("small_ammunition_components");
    }

    public static TagKey<Item> mediumAmmunitionComponentsTag(){
        return modItemTag("medium_ammunition_components");
    }

    public static TagKey<Item> longAmmunitionComponentsTag(){
        return modItemTag("long_ammunition_components");
    }

    public static TagKey<Item> shellAmmunitionComponentsTag(){
        return modItemTag("shell_ammunition_components");
    }

    public static TagKey<Item> copperAmmunitionComponentsTag(){
        return modItemTag("copper_ammunition_components");
    }

    public static TagKey<Item> brassAmmunitionComponentsTag(){
        return modItemTag("brass_ammunition_components");
    }

    public static TagKey<Item> ironAmmunitionComponentsTag(){
        return modItemTag("iron_ammunition_components");
    }

    public static TagKey<Item> ammunitionComponentsTag(){
        return modItemTag("ammunition_components");
    }

    public static TagKey<Item> ammunitionComponentBlueprintsTag(){
        return modItemTag("ammunition_component_blueprints");
    }

    public static TagKey<Item> ammunitionComponentCastsTag(){
        return modItemTag("ammunition_component_casts");
    }

    public static TagKey<Item> smallAmmunitionGunpowdersTag(){
        return modItemTag("small_ammunition_gunpowders");
    }

    public static TagKey<Item> mediumAmmunitionGunpowdersTag(){
        return modItemTag("medium_ammunition_gunpowders");
    }

    public static TagKey<Item> longAmmunitionGunpowdersTag(){
        return modItemTag("long_ammunition_gunpowders");
    }

    public static TagKey<Item> shellAmmunitionGunpowdersTag(){
        return modItemTag("shell_ammunition_gunpowders");
    }

    public static TagKey<Item> ammunitionCasingComponentsTag(){
        return modItemTag("ammunition_casing_components");
    }

    public static TagKey<Item> ammunitionHeadComponentsTag(){
        return modItemTag("ammunition_head_components");
    }

    public static TagKey<Item> ammunitionPelletsComponentsTag(){
        return modItemTag("ammunition_pellets_components");
    }

    public static TagKey<Item> ammunitionWasteComponentsTag(){
        return modItemTag("ammunition_waste_components");
    }

    public static TagKey<Item> millableRocksTag(){
        return modItemTag("millable_rocks");
    }

    public static TagKey<Fluid> moltenIronTag(){
        return modFluidTag("molten_iron");
    }

    public static TagKey<Fluid> moltenAluminumTag(){
        return modFluidTag("molten_aluminum");
    }

    public static TagKey<Fluid> moltenCoppersTag(){
        return modFluidTag("molten_copper_fluids");
    }

    public static TagKey<Fluid> moltenBrassesTag(){
        return modFluidTag("molten_brass_fluids");
    }

    public static TagKey<Item> ingotsTag() {
        return modItemTag("ingots");
    }

    public static TagKey<Item> crushedOresTag(){
        return modItemTag("crushed_ores_tag");
    }

    public static TagKey<Fluid> moltenFluidsTag() {
        return modFluidTag("molten_fluids");
    }
}
