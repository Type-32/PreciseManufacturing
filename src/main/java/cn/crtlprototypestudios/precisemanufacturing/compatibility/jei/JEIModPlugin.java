package cn.crtlprototypestudios.precisemanufacturing.compatibility.jei;

import cn.crtlprototypestudios.precisemanufacturing.Main;
import cn.crtlprototypestudios.precisemanufacturing.foundation.recipe.decomponentalizing.DecomponentalizingRecipe;
import cn.crtlprototypestudios.precisemanufacturing.foundation.recipe.decomponentalizing.DecomponentalizingRecipeType;
import cn.crtlprototypestudios.precisemanufacturing.foundation.util.ResourceHelper;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;
import java.util.Objects;

@JeiPlugin
public class JEIModPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return ResourceHelper.find("jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new DecomponentalizingRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        assert Minecraft.getInstance().level != null;
        RecipeManager rm = Minecraft.getInstance().level.getRecipeManager();

        List<DecomponentalizingRecipe> recipes = rm.getAllRecipesFor(DecomponentalizingRecipeType.INSTANCE);
        assert !recipes.isEmpty();
        Main.LOGGER.debug("Registered {} custom recipes in JEI", recipes.size());
        registration.addRecipes(new RecipeType<DecomponentalizingRecipe>(DecomponentalizingRecipeCategory.UID, DecomponentalizingRecipe.class), recipes);
    }
}
