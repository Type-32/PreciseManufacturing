package cn.crtlprototypestudios.precisemanufacturing.foundation.legacy.item.bases.ammunition;

import cn.crtlprototypestudios.precisemanufacturing.foundation.data.providers.ModRecipeProvider;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.item.Item;

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

        return ModRecipeProvider.addAmmunitionModule(module);
    }

    public static AmmunitionItems getAmmunitionItems(AmmunitionModule module){
        return new AmmunitionItems(items.get(module), casts.get(module), blueprints.get(module));
    }
}
