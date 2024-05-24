package cn.crtlprototypestudios.precisemanufacturing.foundation.data.generators.recipe;

import cn.crtlprototypestudios.precisemanufacturing.foundation.data.builders.recipe.DecomponentalizingRecipeBuilder;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ModDecomponentalizingRecipesGen {
    private static List<DecomponentalizingRecipeBuilder> decomponentalizingRecipeBuilders = new ArrayList<>();

    public static DecomponentalizingRecipeBuilder add(DecomponentalizingRecipeBuilder builder){
        decomponentalizingRecipeBuilders.add(builder);
        return builder;
    }

    public static DecomponentalizingRecipeBuilder add(ItemLike ingredient, ItemLike result, int processingTime){
        DecomponentalizingRecipeBuilder builder = new DecomponentalizingRecipeBuilder(ingredient, result, processingTime);
        decomponentalizingRecipeBuilders.add(builder);
        return builder;
    }

    public static void register(Consumer<FinishedRecipe> pFinishedRecipeConsumer){
        decomponentalizingRecipeBuilders.forEach(i -> i.save(pFinishedRecipeConsumer));
    }
}
