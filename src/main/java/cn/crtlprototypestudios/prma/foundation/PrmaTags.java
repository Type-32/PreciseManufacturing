package cn.crtlprototypestudios.prma.foundation;

import cn.crtlprototypestudios.prma.lib.Reference;
import com.simibubi.create.Create;
import com.tacz.guns.GunMod;
import com.tacz.guns.api.TimelessAPI;
import net.createmod.catnip.lang.Lang;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Collections;

public class PrmaTags {
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
        ItemTag.init();
    }

    public enum NameSpace {
        MOD(Reference.MOD_ID, false, true),
        CREATE(Create.ID, false, true),
        TACZ(GunMod.MOD_ID),
        FORGE("forge");

        public final String id;
        public final boolean optionalDefault;
        public final boolean alwaysDatagenDefault;

        NameSpace(String id) {
            this(id, true, false);
        }
        NameSpace(String id, boolean optionalDefault, boolean alwaysDatagenDefault) {
            this.id = id;
            this.optionalDefault = optionalDefault;
            this.alwaysDatagenDefault = alwaysDatagenDefault;
        }
    }

    public enum ItemTag {
        CASTING_BASIN_PLACEABLE(true),

        MATERIALS,
        INGOTS,
        CRUSHED_ORES,
        CASTS,

        AMMO_COMPONENTS,

        AMMO_CASINGS,
        AMMO_HEADS,
        AMMO_PELLETS,
        AMMO_WASTE,

        SMALL_AMMO_COMPONENTS,
        MEDIUM_AMMO_COMPONENTS,
        LONG_AMMO_COMPONENTS,

        BRASS_AMMO_COMPONENTS(true),
        COPPER_AMMO_COMPONENTS(true),
        IRON_AMMO_COMPONENTS(true),

        SMALL_BRASS_AMMO_COMPONENTS(true),
        SMALL_COPPER_AMMO_COMPONENTS(true),
        SMALL_IRON_AMMO_COMPONENTS(true),
        MEDIUM_BRASS_AMMO_COMPONENTS(true),
        MEDIUM_COPPER_AMMO_COMPONENTS(true),
        MEDIUM_IRON_AMMO_COMPONENTS(true),
        LONG_BRASS_AMMO_COMPONENTS(true),
        LONG_COPPER_AMMO_COMPONENTS(true),
        LONG_IRON_AMMO_COMPONENTS(true),

        SMALL_AMMO_PROPELLANTS(true),
        MEDIUM_AMMO_PROPELLANTS(true),
        LONG_AMMO_PROPELLANTS(true),
        HIGH_POWER_AMMO_PROPELLANTS(true),

        WEAPON_COMPONENTS,
        WEAPON_BLUEPRINTS,
        WEAPON_COMPONENT_CASTS;

        ItemTag() {
            this(NameSpace.MOD);
        }

        ItemTag(NameSpace namespace) {
            this(namespace, namespace.optionalDefault, namespace.alwaysDatagenDefault);
        }

        ItemTag(boolean alwaysDatagen) {
            this(NameSpace.MOD, NameSpace.MOD.optionalDefault, alwaysDatagen);
        }

        ItemTag(NameSpace namespace, boolean alwaysDatagen) {
            this(namespace, namespace.optionalDefault, alwaysDatagen);
        }

        ItemTag(NameSpace namespace, String path) {
            this(namespace, path, namespace.optionalDefault, namespace.alwaysDatagenDefault);
        }

        ItemTag(NameSpace namespace, boolean optional, boolean alwaysDatagen) {
            this(namespace, null, optional, alwaysDatagen);
        }

        ItemTag(NameSpace namespace, String path, boolean optional, boolean alwaysDatagen) {
            ResourceLocation id = new ResourceLocation(namespace.id, path == null ? Lang.asId(name()) : path);
            if (optional) {
                tag = optionalTag(ForgeRegistries.ITEMS, id);
            } else {
                tag = ItemTags.create(id);
            }
            this.alwaysDatagen = alwaysDatagen;
        }

        public final TagKey<Item> tag;
        public final boolean alwaysDatagen;
        public boolean matches(ItemStack stack) {
            return stack.is(tag);
        }

        private static void init() {

        }
    }

    public enum FluidTag {
        MOLTEN_IRON_FLUIDS,
        MOLTEN_BRASS_FLUIDS,
        MOLTEN_COPPER_FLUIDS,
        MOLTEN_ZINC_FLUIDS,
        MOLTEN_ALUMINUM_FLUIDS,
        MOLTEN_METAL_ALLOY_FLUIDS,
        MOLTEN_STRONG_ALUMINUM_ALLOY_FLUIDS,
        MOLTEN_METALS(true),;

        FluidTag() {
            this(NameSpace.MOD);
        }

        FluidTag(NameSpace namespace) {
            this(namespace, namespace.optionalDefault, namespace.alwaysDatagenDefault);
        }

        FluidTag(boolean alwaysDatagen) {
            this(NameSpace.MOD, NameSpace.MOD.optionalDefault, alwaysDatagen);
        }

        FluidTag(NameSpace namespace, boolean alwaysDatagen) {
            this(namespace, namespace.optionalDefault, alwaysDatagen);
        }

        FluidTag(NameSpace namespace, String path) {
            this(namespace, path, namespace.optionalDefault, namespace.alwaysDatagenDefault);
        }

        FluidTag(NameSpace namespace, boolean optional, boolean alwaysDatagen) {
            this(namespace, null, optional, alwaysDatagen);
        }

        FluidTag(NameSpace namespace, String path, boolean optional, boolean alwaysDatagen) {
            ResourceLocation id = new ResourceLocation(namespace.id, path == null ? Lang.asId(name()) : path);
            if (optional) {
                tag = optionalTag(ForgeRegistries.FLUIDS, id);
            } else {
                tag = FluidTags.create(id);
            }
            this.alwaysDatagen = alwaysDatagen;
        }

        public final TagKey<Fluid> tag;
        public final boolean alwaysDatagen;
        public boolean matches(FluidState stack) {
            return stack.is(tag);
        }

        private static void init() {

        }
    }

    public static TagKey<Item> ingotsTag() {
        return modItemTag("ingots");
    }

    public static TagKey<Item> crushedOresTag(){
        return modItemTag("crushed_ores_tag");
    }
}
