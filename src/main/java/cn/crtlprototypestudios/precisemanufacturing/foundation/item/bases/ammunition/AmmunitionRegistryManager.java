package cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.ammunition;

import cn.crtlprototypestudios.precisemanufacturing.Main;
import cn.crtlprototypestudios.precisemanufacturing.foundation.ModCreativeModTabs;
import cn.crtlprototypestudios.precisemanufacturing.foundation.ModItems;
import cn.crtlprototypestudios.precisemanufacturing.foundation.ModTags;
import cn.crtlprototypestudios.precisemanufacturing.foundation.data.providers.ModItemModelProvider;
import cn.crtlprototypestudios.precisemanufacturing.foundation.data.providers.ModRecipeProvider;
import cn.crtlprototypestudios.precisemanufacturing.foundation.util.ResourceHelper;
import com.simibubi.create.content.fluids.transfer.FillingRecipe;
import com.simibubi.create.content.kinetics.saw.CuttingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.tacz.guns.init.ModCreativeTabs;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AmmunitionRegistryManager {
    public static Map<AmmunitionModule, RegistryEntry<Item>> items = new HashMap<>();
    public static Map<AmmunitionModule, RegistryEntry<Item>> casts = new HashMap<>();
    public static Map<AmmunitionModule, RegistryEntry<Item>> blueprints = new HashMap<>();
    public static List<AmmunitionModule> modules = new ArrayList<>();

    public static AmmunitionModule register(AmmunitionModule module){
        String fluidTagType = "";
        String[] splitted = module.getFillingFluid().toString().split("_"); // e.g. ["molten", "iron", "fluids"]
        for(int i = 1; i < splitted.length - 1; i++){
            fluidTagType += splitted[i];
            if(i < splitted.length - 2) fluidTagType += "_";
        }

        String itemId = String.format("%s_%s_%s_component", module.getSize(), fluidTagType, module.getType());
        RegistryEntry<Item> item = ModItems.addToList(Main.REGISTRATE.item(itemId, Item::new)
                .tag(
                        ModTags.ammunitionComponentsTag(),
                        (
                                module.getFillingFluid() == ModTags.moltenIronsTag() ? ModTags.ironAmmunitionComponentsTag() :
                                module.getFillingFluid() == ModTags.moltenCoppersTag() ? ModTags.copperAmmunitionComponentsTag() :
                                ModTags.brassAmmunitionComponentsTag()
                        ),
                        (
                                module.getType() == AmmunitionMaterialType.CASING ? ModTags.ammunitionCasingComponentsTag() :
                                module.getType() == AmmunitionMaterialType.HEAD ? ModTags.ammunitionHeadComponentsTag() :
                                module.getType() == AmmunitionMaterialType.PELLETS ? ModTags.ammunitionPelletsComponentsTag() :
                                ModTags.ammunitionWasteComponentsTag()
                        ),
                        (
                                module.getSize() == AmmunitionSize.SMALL ? ModTags.smallAmmunitionComponentsTag() :
                                module.getSize() == AmmunitionSize.MEDIUM ? ModTags.mediumAmmunitionComponentsTag() :
                                module.getSize() == AmmunitionSize.LONG ? ModTags.longAmmunitionComponentsTag() :
                                ModTags.shellAmmunitionComponentsTag()
                        )
                )
                .tab(ModCreativeModTabs.MOD_COMPONENTS_TAB.getKey())
                .model(ModItemModelProvider.genericItemModel(true, "ammunition_components", "modules", itemId))
                .register(), ModCreativeModTabs.Tabs.Components);
        RegistryEntry<Item> cast = ModItems.addToList(Main.REGISTRATE.item(itemId + "_cast", Item::new)
                .tag(ModTags.ammunitionComponentCastsTag())
                .tab(ModCreativeModTabs.MOD_CASTS_TAB.getKey())
                .model(ModItemModelProvider.genericItemModel(true, "ammunition_components", "casts", itemId))
                .register(), ModCreativeModTabs.Tabs.Casts);
        RegistryEntry<Item> blueprint = ModItems.addToList(Main.REGISTRATE.item(itemId + "_blueprint", Item::new)
                .tag(ModTags.ammunitionComponentBlueprintsTag())
                .tab(ModCreativeModTabs.MOD_BLUEPRINTS_TAB.getKey())
                .model(ModItemModelProvider.genericItemModel(true, "ammunition_components", "blueprints", itemId))
                .register(), ModCreativeModTabs.Tabs.Blueprints);

        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(FillingRecipe::new, ResourceHelper.find("filling/ammunition_modules/" + itemId + "_cast_filling"))
                .output(item.get(), module.getCastResultCount())
                .require(cast.get())
                .require(module.getFillingFluid(), module.getCastFillingAmount()));
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(CuttingRecipe::new, ResourceHelper.find("cutting/ammunition_modules/" + itemId + "_cast_cutting"))
                .output(item.get(), 1)
                .require(ModItems.BLANK_CAST.get()));

        items.put(module, item);
        casts.put(module, cast);
        blueprints.put(module, blueprint);

        modules.add(module);

        return module;
    }

    public static AmmunitionItems getAmmunitionItems(AmmunitionModule module){
        return new AmmunitionItems(items.get(module), casts.get(module), blueprints.get(module));
    }
}
