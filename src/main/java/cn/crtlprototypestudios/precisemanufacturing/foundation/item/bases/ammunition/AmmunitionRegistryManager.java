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
        items.put(module, module.item);
        casts.put(module, module.cast);
        blueprints.put(module, module.blueprint);

        modules.add(module);

        return module;
    }

    public static AmmunitionItems getAmmunitionItems(AmmunitionModule module){
        return new AmmunitionItems(items.get(module), casts.get(module), blueprints.get(module));
    }
}
