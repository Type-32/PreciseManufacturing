package cn.crtlprototypestudios.prma.foundation;

import cn.crtlprototypestudios.prma.lib.Reference;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PrmaRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Reference.MOD_ID);

//    public static final RegistryObject<RecipeSerializer<DecomponentalizingRecipe>> DECOMPONENTALIZING_SERIALIZER = SERIALIZERS.register(DecomponentalizingRecipe.Type.ID, () -> DecomponentalizingRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
