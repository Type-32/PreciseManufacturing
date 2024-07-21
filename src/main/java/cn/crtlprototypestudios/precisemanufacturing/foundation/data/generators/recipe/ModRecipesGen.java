package cn.crtlprototypestudios.precisemanufacturing.foundation.data.generators.recipe;

import cn.crtlprototypestudios.precisemanufacturing.foundation.ModFluids;
import cn.crtlprototypestudios.precisemanufacturing.foundation.ModItems;
import cn.crtlprototypestudios.precisemanufacturing.foundation.ModTags;
import cn.crtlprototypestudios.precisemanufacturing.foundation.data.builders.recipe.DecomponentalizingRecipeBuilder;
import cn.crtlprototypestudios.precisemanufacturing.foundation.data.providers.ModRecipeProvider;
import cn.crtlprototypestudios.precisemanufacturing.foundation.util.ResourceHelper;
import com.simibubi.create.AllItems;
import com.simibubi.create.content.kinetics.crusher.CrushingRecipe;
import com.simibubi.create.content.kinetics.fan.processing.SplashingRecipe;
import com.simibubi.create.content.kinetics.millstone.MillingRecipe;
import com.simibubi.create.content.kinetics.mixer.MixingRecipe;
import com.simibubi.create.content.kinetics.saw.CuttingRecipe;
import com.simibubi.create.content.processing.recipe.HeatCondition;
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
                .require(ModItems.SULFUR_POWDER.get())
                .duration(100)
        );
        craftingRecipeBuilders.add(ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.GUNPOWDER, 1).requires(ModTags.longAmmunitionGunpowdersTag()));

        // basalt crushing
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(CrushingRecipe::new, ResourceHelper.find("crushing/basalt_to_powder"))
                .output(20, ModItems.RAW_ZINC_POWDER.get(), 4)
                .output(20, ModItems.RAW_COPPER_POWDER.get(), 4)
                .output(40, ModItems.BASALT_POWDER.get(), 6)
                .output(40, ModItems.CRUSHED_BASALT.get())
                .require(Items.BASALT)
                .duration(300)
        );

        // crushed basalt milling
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(MillingRecipe::new, ResourceHelper.find("milling/crushed_basalt_to_powder"))
                .output(50, ModItems.BASALT_POWDER.get(), 2)
                .output(50, ModItems.RAW_SULFUR_POWDER.get(), 6)
                .require(ModItems.CRUSHED_BASALT.get())
                .duration(300)
        );

        // crushed basalt crushing
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(CrushingRecipe::new, ResourceHelper.find("crushing/crushed_basalt_to_powder"))
                .output(50, ModItems.BASALT_POWDER.get(), 2)
                .output(50, ModItems.RAW_SULFUR_POWDER.get(), 6)
                .output(10, ModItems.FLINT_POWDER.get(), 2)
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
                .output(20, ModItems.RAW_COPPER_POWDER.get(), 2)
                .output(20, ModItems.RAW_ZINC_POWDER.get(), 2)
                .require(ModTags.millableRocksTag())
                .duration(200)
        );

        // Iron ingot to Blank Cast
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(CuttingRecipe::new, ResourceHelper.find("cutting/iron_ingot_to_cast"))
                .output(ModItems.BLANK_CAST.get(), 5)
                .require(Items.IRON_INGOT)
                .duration(100)
        );

        // Melting Copper Nuggets
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(MixingRecipe::new, ResourceHelper.find("mixing/melting/copper_nugget_to_molten_copper"))
                .output(ModFluids.MOLTEN_COPPER.get(), 80)
                .require(ModItems.BASALT_POWDER.get())
                .require(AllItems.COPPER_NUGGET.get())
                .requiresHeat(HeatCondition.HEATED));

        // Melting Copper Ingots
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(MixingRecipe::new, ResourceHelper.find("mixing/melting/copper_ingot_to_molten_copper"))
                .output(ModFluids.MOLTEN_COPPER.get(), 720)
                .require(ModItems.CRUSHED_BASALT.get())
                .require(Items.COPPER_INGOT)
                .requiresHeat(HeatCondition.HEATED));

        // Melting Brass Nuggets
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(MixingRecipe::new, ResourceHelper.find("mixing/melting/brass_nugget_to_molten_brass"))
                .output(ModFluids.MOLTEN_BRASS.get(), 80)
                .require(ModItems.BASALT_POWDER.get())
                .require(AllItems.BRASS_NUGGET.get())
                .requiresHeat(HeatCondition.HEATED));

        // Melting Brass Ingots
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(MixingRecipe::new, ResourceHelper.find("mixing/melting/brass_ingot_to_molten_brass"))
                .output(ModFluids.MOLTEN_BRASS.get(), 720)
                .require(ModItems.CRUSHED_BASALT.get())
                .require(AllItems.BRASS_INGOT.get())
                .requiresHeat(HeatCondition.HEATED));

        // Melting Iron Nuggets
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(MixingRecipe::new, ResourceHelper.find("mixing/melting/iron_nugget_to_molten_iron"))
                .output(ModFluids.MOLTEN_BASALT_INFUSED_IRON.get(), 80)
                .require(ModItems.BASALT_POWDER.get())
                .require(Items.IRON_NUGGET)
                .requiresHeat(HeatCondition.HEATED));

        // Melting Iron Ingots
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(MixingRecipe::new, ResourceHelper.find("mixing/melting/iron_ingot_to_molten_iron"))
                .output(ModFluids.MOLTEN_BASALT_INFUSED_IRON.get(), 720)
                .require(ModItems.CRUSHED_BASALT.get())
                .require(Items.IRON_INGOT)
                .requiresHeat(HeatCondition.HEATED));

        // Flint and Rock Powder mix to Basalt Powder
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(MixingRecipe::new, ResourceHelper.find("mixing/solids/flint_and_rock_to_basalt_powder"))
                .output(ModItems.BASALT_POWDER.get(), 2)
                .require(ModItems.FLINT_POWDER.get())
                .require(ModItems.ROCK_POWDER.get()));

        // Misc Mixing to Gunpowder
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(MixingRecipe::new, ResourceHelper.find("mixing/gunpowder"))
                .output(Items.GUNPOWDER, 3)
                .require(ModItems.BASALT_POWDER.get())
                .require(ModItems.RAW_SULFUR_POWDER.get())
                .require(Items.SUGAR)
                .require(Items.CHARCOAL));

        // Ammo Waste to Molten Fluids
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(MixingRecipe::new, ResourceHelper.find("mixing/wasted_cartridges_to_fluids"))
                .output(ModFluids.MOLTEN_COPPER.get(), 50)
                .output(ModFluids.MOLTEN_BASALT_INFUSED_IRON.get(), 50)
                .output(ModFluids.MOLTEN_BRASS.get(), 50));

        craftingRecipeBuilders.add(ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BLANK_BLUEPRINT.get(), 3)
                .requires(Items.PAPER)
                .requires(Items.WHITE_DYE)
                .requires(Items.BLUE_DYE));

        craftingRecipeBuilders.forEach(p -> p.save(pFinishedRecipeConsumer));
    }
}
