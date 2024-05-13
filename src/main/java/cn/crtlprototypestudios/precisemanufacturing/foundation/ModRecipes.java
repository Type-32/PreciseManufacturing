package cn.crtlprototypestudios.precisemanufacturing.foundation;

import cn.crtlprototypestudios.precisemanufacturing.foundation.recipe.decomponentalizing.DecomponentalizingRecipe;
import cn.crtlprototypestudios.precisemanufacturing.util.Reference;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Reference.MOD_ID);

    public static final RegistryObject<RecipeSerializer<DecomponentalizingRecipe>> DECOMPONENTALIZING_SERIALIZER = SERIALIZERS.register("decomponentalizing", () -> DecomponentalizingRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
