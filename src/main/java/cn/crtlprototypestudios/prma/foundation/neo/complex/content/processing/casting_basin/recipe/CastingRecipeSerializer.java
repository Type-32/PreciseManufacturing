package cn.crtlprototypestudios.prma.foundation.neo.complex.content.processing.casting_basin.recipe;

import com.google.gson.JsonObject;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeSerializer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;

public class CastingRecipeSerializer extends ProcessingRecipeSerializer<CastingRecipe> {

    public CastingRecipeSerializer(ProcessingRecipeBuilder.ProcessingRecipeFactory<CastingRecipe> factory) {
        super(factory);
    }

    @Override
    public CastingRecipe fromJson(ResourceLocation recipeId, JsonObject json, ICondition.IContext context) {
        CastingRecipe recipe = super.fromJson(recipeId, json);
//         Additional validation
        if (recipe.getIngredients().size() != 1)
            throw new IllegalArgumentException("Casting recipe must have exactly one item ingredient (cast)");
        if (recipe.getFluidIngredients().size() != 1)
            throw new IllegalArgumentException("Casting recipe must have exactly one fluid ingredient");
        return recipe;
    }
}
