package cn.crtlprototypestudios.precisemanufacturing.foundation;


import cn.crtlprototypestudios.precisemanufacturing.foundation.recipe.decomponentalizing.DecomponentalizingRecipe;
import cn.crtlprototypestudios.precisemanufacturing.foundation.recipe.decomponentalizing.DecomponentalizingRecipeType;
import cn.crtlprototypestudios.precisemanufacturing.util.Reference;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerRecipeTypes(final RegistryEvent.Register<RecipeSerializer<?>> event) {
        Registry.register(Registry.RECIPE_TYPE, DecomponentalizingRecipe.Type.ID, DecomponentalizingRecipe.Type.INSTANCE);
        Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(Reference.MOD_ID, DecomponentalizingRecipeType.ID), DecomponentalizingRecipeType.INSTANCE);
    }
}
