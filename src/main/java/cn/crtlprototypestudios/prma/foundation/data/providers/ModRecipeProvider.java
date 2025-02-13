package cn.crtlprototypestudios.prma.foundation.data.providers;

import cn.crtlprototypestudios.prma.foundation.data.generators.recipe.ModRecipesGen;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
//    private static List<RifleBase> rifleBases = new ArrayList<>();
//    private static List<CartridgeBase> cartridgeBases = new ArrayList<>();
//    private static List<AmmunitionModule> ammunitionModules = new ArrayList<>();

    public ModRecipeProvider(DataGenerator pGenerator) {
        super(pGenerator.getPackOutput());
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
//        rifleBases.forEach(RifleBase::registerRecipes);
//        ammunitionModules.forEach(AmmunitionModule::registerRecipes);
//        cartridgeBases.forEach(CartridgeBase::registerRecipes);

//        ModDecomponentalizingRecipesGen.register(pFinishedRecipeConsumer);
        ModRecipesGen.register(pFinishedRecipeConsumer);


    }

    //    public static <T extends ProcessingRecipe<?>> void addCreateRecipeBuilder(ProcessingRecipeBuilder.ProcessingRecipeFactory<T> factory, ResourceLocation location){
//        addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(factory, location));
//    }

    //    public static RifleBase addRifleBase(RifleBase rb){
//        rifleBases.add(rb);
//        return rb;
//    }
//
//    public static CartridgeBase addCartridgeBase(CartridgeBase cb){
//        cartridgeBases.add(cb);
//        return cb;
//    }
//
//    public static AmmunitionModule addAmmunitionModule(AmmunitionModule module){
//        ammunitionModules.add(module);
//        return module;
//    }
}
