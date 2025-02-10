package cn.crtlprototypestudios.prma.foundation.data.providers;

import cn.crtlprototypestudios.prma.foundation.data.generators.recipe.ModRecipesGen;
import cn.crtlprototypestudios.prma.foundation.legacy.item.bases.ammunition.AmmunitionModule;
import cn.crtlprototypestudios.prma.foundation.legacy.item.bases.ammunition.CartridgeBase;
import cn.crtlprototypestudios.prma.foundation.legacy.item.bases.weapon.RifleBase;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.simibubi.create.content.processing.sequenced.SequencedAssemblyRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
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
    private static List<AmmunitionModule> ammunitionModules = new ArrayList<>();

    public ModRecipeProvider(DataGenerator pGenerator) {
        super(pGenerator.getPackOutput());
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        rifleBases.forEach(RifleBase::registerRecipes);
        ammunitionModules.forEach(AmmunitionModule::registerRecipes);
        cartridgeBases.forEach(CartridgeBase::registerRecipes);

//        ModDecomponentalizingRecipesGen.register(pFinishedRecipeConsumer);
        ModRecipesGen.register(pFinishedRecipeConsumer);

        recipeBuilders.forEach(i -> i.save(pFinishedRecipeConsumer));
        createCompatRecipeBuilders.forEach(i -> i.build(pFinishedRecipeConsumer));
        sequencedAssemblyRecipeBuilders.forEach(i -> i.build(pFinishedRecipeConsumer));
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

    public static AmmunitionModule addAmmunitionModule(AmmunitionModule module){
        ammunitionModules.add(module);
        return module;
    }
}
