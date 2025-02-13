package cn.crtlprototypestudios.prma.foundation.neo.content.item.collection;

import cn.crtlprototypestudios.prma.PreciseManufacturing;
import cn.crtlprototypestudios.prma.foundation.PrmaItems;
import cn.crtlprototypestudios.prma.foundation.PrmaTags;
import cn.crtlprototypestudios.prma.foundation.data.generators.recipe.ModRecipesGen;
import cn.crtlprototypestudios.prma.foundation.data.providers.ModItemModelProvider;
import cn.crtlprototypestudios.prma.foundation.data.providers.ModRecipeProvider;
import cn.crtlprototypestudios.prma.foundation.neo.content.item.type.standard.AmmoMaterialType;
import cn.crtlprototypestudios.prma.foundation.neo.content.item.type.standard.AmmoSizeType;
import cn.crtlprototypestudios.prma.foundation.utility.ResourceHelper;
import com.simibubi.create.AllItems;
import com.simibubi.create.AllTags;
import com.simibubi.create.content.kinetics.saw.CuttingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StandardCartridgeComponents {
    protected AmmoMaterialType[] materialTypes;
    protected AmmoSizeType sizeType;
    protected Map<AmmoMaterialType, RegistryEntry<Item>> casings, heads, transitions;

    public StandardCartridgeComponents(AmmoSizeType sizeType, AmmoMaterialType... materialTypes) {
        casings = new ConcurrentHashMap<>();
        heads = new ConcurrentHashMap<>();
        this.materialTypes = materialTypes;
        this.sizeType = sizeType;
    }

    public StandardCartridgeComponents build(){
        for (var mat : materialTypes) {
            var casing = PreciseManufacturing.REGISTRATE.item(String.format("%s_%s_%s", sizeType.toString(), mat.toString(), "casing"), Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "ammo", "casing", mat.toString(), "_"))
                    .tag(PrmaTags.ItemTag.MATERIALS.tag, PrmaTags.ItemTag.AMMO_CASINGS.tag, AllTags.AllItemTags.UPRIGHT_ON_BELT.tag);

            var head = PreciseManufacturing.REGISTRATE.item(String.format("%s_%s_%s", sizeType.toString(), mat.toString(), "head"), Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "ammo", "head", mat.toString(), "_"))
                    .tag(PrmaTags.ItemTag.MATERIALS.tag, PrmaTags.ItemTag.AMMO_HEADS.tag, AllTags.AllItemTags.UPRIGHT_ON_BELT.tag);

            var transition = PreciseManufacturing.REGISTRATE.item(String.format("%s_%s_%s", sizeType.toString(), mat.toString(), "transition"), Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "ammo", "transition", mat.toString(), "_"))
                    .tag(PrmaTags.ItemTag.AMMO_WASTE.tag, AllTags.AllItemTags.UPRIGHT_ON_BELT.tag);

            switch (sizeType) {
                case Small -> {
                    casing.tag(PrmaTags.ItemTag.SMALL_AMMO_COMPONENTS.tag);
                    head.tag(PrmaTags.ItemTag.SMALL_AMMO_COMPONENTS.tag);
                }
                case Medium -> {
                    casing.tag(PrmaTags.ItemTag.MEDIUM_AMMO_COMPONENTS.tag);
                    head.tag(PrmaTags.ItemTag.MEDIUM_AMMO_COMPONENTS.tag);
                }
                case Long -> {
                    casing.tag(PrmaTags.ItemTag.LONG_AMMO_COMPONENTS.tag);
                    head.tag(PrmaTags.ItemTag.LONG_AMMO_COMPONENTS.tag);
                }
            }

            switch (mat) {
                case Brass -> {
                    casing.tag(PrmaTags.ItemTag.BRASS_AMMO_COMPONENTS.tag);
                    head.tag(PrmaTags.ItemTag.BRASS_AMMO_COMPONENTS.tag);
                }
                case Copper -> {
                    casing.tag(PrmaTags.ItemTag.COPPER_AMMO_COMPONENTS.tag);
                    head.tag(PrmaTags.ItemTag.COPPER_AMMO_COMPONENTS.tag);
                }
                case Iron -> {
                    casing.tag(PrmaTags.ItemTag.IRON_AMMO_COMPONENTS.tag);
                    head.tag(PrmaTags.ItemTag.IRON_AMMO_COMPONENTS.tag);
                }
            }

            this.heads.put(mat, head.register());
            this.casings.put(mat, casing.register());
            this.transitions.put(mat, transition.register());
        }
        PrmaItems.ALL_CARTRIDGE_COMPONENTS.add(this);
        return this;
    }

    public void registerRecipes() {
        for (var mat : materialTypes) {
            // e.g. cutting/ammo_components/small/brass_sheet_to_small_brass_casing
            ModRecipesGen.addCreateRecipe(new ProcessingRecipeBuilder<>(CuttingRecipe::new, ResourceHelper.find(String.format("cutting/ammo_components/%s/%s_sheet_to_%s_%s_casing",
                    sizeType.toString(), mat.toString(), sizeType.toString(), mat.toString())))
                    .require(getMaterialSheet(mat))
                    .output(getCasing(mat).get(), getCasingSizeOutputNumber(sizeType))
                    .output(getHead(mat).get(), getHeadSizeOutputNumber(sizeType)));
        }
    }

    public RegistryEntry<Item> getCasing(AmmoMaterialType type) {
        return casings.get(type);
    }

    public RegistryEntry<Item> getHead(AmmoMaterialType type) {
        return heads.get(type);
    }

    public RegistryEntry<Item> getTransition(AmmoMaterialType type) {
        return transitions.get(type);
    }

    private Item getMaterialSheet(AmmoMaterialType type){
        return switch(type) {
            case Iron -> AllItems.IRON_SHEET.get();
            case Brass -> AllItems.BRASS_INGOT.get();
            case Copper -> AllItems.COPPER_SHEET.get();
            case Plastic -> Items.GLASS; // TODO: change this in future
        };
    }

    private int getHeadSizeOutputNumber(AmmoSizeType type){
        return switch(type) {
            case Small -> 9;
            case Medium -> 7;
            case Long -> 5;
        };
    }

    private int getCasingSizeOutputNumber(AmmoSizeType type){
        return switch(type) {
            case Small -> 7;
            case Medium -> 5;
            case Long -> 3;
        };
    }
}
