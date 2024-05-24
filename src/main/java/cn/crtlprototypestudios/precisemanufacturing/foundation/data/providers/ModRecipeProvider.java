package cn.crtlprototypestudios.precisemanufacturing.foundation.data.providers;

import cn.crtlprototypestudios.precisemanufacturing.foundation.ModBlocks;
import cn.crtlprototypestudios.precisemanufacturing.foundation.ModItems;
import cn.crtlprototypestudios.precisemanufacturing.foundation.data.builders.recipe.DecomponentalizingRecipeBuilder;
import cn.crtlprototypestudios.precisemanufacturing.foundation.data.generators.recipe.ModDecomponentalizingRecipesGen;
import cn.crtlprototypestudios.precisemanufacturing.foundation.data.generators.recipe.create_compat.ModMechanicalCraftingRecipeGen;
import cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.ammunition.CartridgeBase;
import cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.ammunition.CartridgeModule;
import cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.weapon.RifleBase;
import com.simibubi.create.content.processing.recipe.ProcessingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.simibubi.create.content.processing.sequenced.SequencedAssemblyRecipeBuilder;
import com.simibubi.create.foundation.data.recipe.CreateRecipeProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static List<RecipeBuilder> recipeBuilders = new ArrayList<>();
    private static List<ProcessingRecipeBuilder<?>> createCompatRecipeBuilders = new ArrayList<>();
    private static List<SequencedAssemblyRecipeBuilder> sequencedAssemblyRecipeBuilders = new ArrayList<>();
    private static List<RifleBase> rifleBases = new ArrayList<>();
    private static List<CartridgeBase> cartridgeBases = new ArrayList<>();

    public ModRecipeProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        rifleBases.forEach(RifleBase::registerRecipes);
        cartridgeBases.forEach(CartridgeBase::registerRecipes);

        recipeBuilders.forEach(i -> i.save(pFinishedRecipeConsumer));
        createCompatRecipeBuilders.forEach(i -> i.build(pFinishedRecipeConsumer));
        sequencedAssemblyRecipeBuilders.forEach(i -> i.build(pFinishedRecipeConsumer));

        ModMechanicalCraftingRecipeGen.register(pFinishedRecipeConsumer);
        ModDecomponentalizingRecipesGen.register(pFinishedRecipeConsumer);
    }

    public static void add(RecipeBuilder builder){
        recipeBuilders.add(builder);
    }

    public static void addCreateRecipeBuilder(ProcessingRecipeBuilder<?> generatedRecipe){
        createCompatRecipeBuilders.add(generatedRecipe);
    }

    public static void addSequencedAssemblyBuilder(SequencedAssemblyRecipeBuilder generatedRecipe){
        sequencedAssemblyRecipeBuilders.add(generatedRecipe);
    }

    public static RifleBase addRifleBase(RifleBase rb){
        rifleBases.add(rb);
        return rb;
    }

    public static CartridgeBase addCartridgeBase(CartridgeBase cb){
        cartridgeBases.add(cb);
        return cb;
    }
}
