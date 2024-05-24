package cn.crtlprototypestudios.precisemanufacturing.foundation.data.generators.recipe;

import cn.crtlprototypestudios.precisemanufacturing.foundation.data.builders.recipe.DecomponentalizingRecipeBuilder;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
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

    public static DecomponentalizingRecipeBuilder add(TagKey<Item> tag, ItemLike result, int processingTime){
//        ItemStack stack = new ItemStack();
        DecomponentalizingRecipeBuilder builder = new DecomponentalizingRecipeBuilder(tag, result, processingTime);
        decomponentalizingRecipeBuilders.add(builder);
        return builder;
    }

    public static DecomponentalizingRecipeBuilder add(ItemStack itemStack, ItemLike result, int processingTime){
        DecomponentalizingRecipeBuilder builder = new DecomponentalizingRecipeBuilder(itemStack, result, processingTime);
        decomponentalizingRecipeBuilders.add(builder);
        return builder;
    }

    public static void register(Consumer<FinishedRecipe> pFinishedRecipeConsumer){
        decomponentalizingRecipeBuilders.forEach(i -> i.save(pFinishedRecipeConsumer));
    }
}
