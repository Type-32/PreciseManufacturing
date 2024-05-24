package cn.crtlprototypestudios.precisemanufacturing.foundation.recipe.decomponentalizing;

import net.minecraft.world.item.crafting.RecipeType;

public class DecomponentalizingRecipeType implements RecipeType<DecomponentalizingRecipe> {
    private DecomponentalizingRecipeType() {
    }

    public static final DecomponentalizingRecipeType INSTANCE = new DecomponentalizingRecipeType();
    public static final String ID = "decomponentalizing";
}
