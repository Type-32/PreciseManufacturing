package cn.crtlprototypestudios.precisemanufacturing.foundation.data.generators.recipe;

import cn.crtlprototypestudios.precisemanufacturing.foundation.ModItems;
import cn.crtlprototypestudios.precisemanufacturing.foundation.ModTags;
import cn.crtlprototypestudios.precisemanufacturing.foundation.data.builders.recipe.DecomponentalizingRecipeBuilder;
import cn.crtlprototypestudios.precisemanufacturing.foundation.data.providers.ModRecipeProvider;
import cn.crtlprototypestudios.precisemanufacturing.foundation.util.ResourceHelper;
import com.simibubi.create.content.kinetics.fan.processing.SplashingRecipe;
import com.simibubi.create.content.kinetics.millstone.MillingRecipe;
import com.simibubi.create.content.kinetics.mixer.MixingRecipe;
import com.simibubi.create.content.kinetics.saw.CuttingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.simibubi.create.foundation.data.recipe.CreateRecipeProvider;
import com.simibubi.create.foundation.data.recipe.WashingRecipeGen;
import net.minecraft.data.recipes.CraftingRecipeBuilder;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ModRecipesGen {
    private static List<ShapelessRecipeBuilder> craftingRecipeBuilders = new ArrayList<>();

    public static void register(Consumer<FinishedRecipe> pFinishedRecipeConsumer){
        // Sulfur Powder Washing
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(SplashingRecipe::new, ResourceHelper.find("splashing/raw_sulfur_powder_splashing")).output(ModItems.SULFUR_POWDER.get()).require(ModItems.RAW_SULFUR_POWDER.get()).duration(80));

        // Small Ammunition Gunpowder Crafting and Uncrafting
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(MixingRecipe::new, ResourceHelper.find("mixing/small_ammunition_gunpowder_mixing"))
                .output(ModItems.SMALL_AMMUNITION_GUNPOWDER.get())
                .require(Tags.Items.GUNPOWDER)
                .require(ModItems.FLINT_POWDER.get())
                .duration(100)
        );
        craftingRecipeBuilders.add(ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.GUNPOWDER, 1).requires(ModTags.smallAmmunitionGunpowdersTag()));

        // Medium Ammunition Gunpowder Crafting and Uncrafting
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(MixingRecipe::new, ResourceHelper.find("mixing/medium_ammunition_gunpowder_mixing"))
                .output(ModItems.MEDIUM_AMMUNITION_GUNPOWDER.get())
                .require(Tags.Items.GUNPOWDER)
                .require(ModItems.RAW_SULFUR_POWDER.get())
                .duration(100)
        );
        craftingRecipeBuilders.add(ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.GUNPOWDER, 1).requires(ModTags.mediumAmmunitionGunpowdersTag()));

        // Long Ammunition Gunpowder Crafting and Uncrafting
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(MixingRecipe::new, ResourceHelper.find("mixing/long_ammunition_gunpowder_mixing"))
                .output(ModItems.LONG_AMMUNITION_GUNPOWDER.get())
                .require(Tags.Items.GUNPOWDER)
                .require(ModItems.ROCK_POWDER.get())
                .duration(100)
        );
        craftingRecipeBuilders.add(ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.GUNPOWDER, 1).requires(ModTags.longAmmunitionGunpowdersTag()));

        // basalt powder milling
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(MillingRecipe::new, ResourceHelper.find("milling/basalt_powder"))
                .output(ModItems.BASALT_POWDER.get(), 3)
                .require(ModItems.CRUSHED_BASALT.get())
                .duration(300)
        );

        // flint powder milling
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(MillingRecipe::new, ResourceHelper.find("milling/flint_powder"))
                .output(ModItems.FLINT_POWDER.get(), 3)
                .require(Items.FLINT)
                .duration(100)
        );

        // rock and sulfur powder milling
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(MillingRecipe::new, ResourceHelper.find("milling/rock_and_sulfur_powder"))
                .output(ModItems.ROCK_POWDER.get(), 3)
                .output(50, ModItems.RAW_SULFUR_POWDER.get(), 3)
                .output(5, ModItems.FLINT_POWDER.get(), 1)
                .require(ModTags.millableRocksTag())
                .duration(100)
        );

        // Iron ingot to Blank Cast
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(CuttingRecipe::new, ResourceHelper.find("cutting/iron_ingot_to_cast"))
                .output(ModItems.BLANK_CAST.get(), 5)
                .require(Items.IRON_INGOT)
                .duration(100)
        );

        craftingRecipeBuilders.add(ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BLANK_BLUEPRINT.get(), 3)
                .requires(Items.PAPER)
                .requires(Items.WHITE_DYE)
                .requires(Items.BLUE_DYE));

        craftingRecipeBuilders.forEach(p -> p.save(pFinishedRecipeConsumer));
    }
}
