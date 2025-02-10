package cn.crtlprototypestudios.prma.foundation.data.generators.recipe;

import cn.crtlprototypestudios.prma.foundation.ModFluids;
import cn.crtlprototypestudios.prma.foundation.ModItems;
import cn.crtlprototypestudios.prma.foundation.ModTags;
import cn.crtlprototypestudios.prma.foundation.data.providers.ModRecipeProvider;
import cn.crtlprototypestudios.prma.foundation.util.ResourceHelper;
import com.simibubi.create.AllItems;
import com.simibubi.create.content.fluids.transfer.EmptyingRecipe;
import com.simibubi.create.content.fluids.transfer.FillingRecipe;
import com.simibubi.create.content.kinetics.crusher.CrushingRecipe;
import com.simibubi.create.content.kinetics.fan.processing.SplashingRecipe;
import com.simibubi.create.content.kinetics.millstone.MillingRecipe;
import com.simibubi.create.content.kinetics.mixer.CompactingRecipe;
import com.simibubi.create.content.kinetics.mixer.MixingRecipe;
import com.simibubi.create.content.kinetics.saw.CuttingRecipe;
import com.simibubi.create.content.processing.recipe.HeatCondition;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class ModRecipesGen {

    public static void register(Consumer<FinishedRecipe> pFinishedRecipeConsumer){
        // Sulfur Powder Washing
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(SplashingRecipe::new, ResourceHelper.find("raw_sulfur_powder_splashing")).output(ModItems.SULFUR_POWDER.get()).require(ModItems.SULFUR_POWDER.get()).duration(80));

        // Small Ammunition Gunpowder Crafting and Uncrafting
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(MixingRecipe::new, ResourceHelper.find("small_ammunition_gunpowder_mixing"))
                .output(ModItems.SMALL_AMMUNITION_GUNPOWDER.get())
                .require(Items.GUNPOWDER)
                .require(ModItems.FLINT_POWDER.get())
                .require(ModItems.FLINT_POWDER.get())
                .duration(100)
        );
//        ModRecipeProvider.add(ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.GUNPOWDER, 2).requires(ModTags.smallAmmunitionGunpowdersTag()).group("small_downgrade").unlockedBy(RegistrateRecipeProvider.getHasName(Items.GUNPOWDER) + "small", RegistrateRecipeProvider.has(Items.GUNPOWDER)));

        // Medium Ammunition Gunpowder Crafting and Uncrafting
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(MixingRecipe::new, ResourceHelper.find("medium_ammunition_gunpowder_mixing"))
                .output(ModItems.MEDIUM_AMMUNITION_GUNPOWDER.get())
                .require(Items.GUNPOWDER)
                .require(ModItems.SULFUR_POWDER.get())
                .duration(100)
        );
//        ModRecipeProvider.add(ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.GUNPOWDER, 2).requires(ModTags.mediumAmmunitionGunpowdersTag()).group("medium_downgrade").unlockedBy(RegistrateRecipeProvider.getHasName(Items.GUNPOWDER) + "_medium", RegistrateRecipeProvider.has(Items.GUNPOWDER)));

        // Long Ammunition Gunpowder Crafting and Uncrafting
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(MixingRecipe::new, ResourceHelper.find("long_ammunition_gunpowder_mixing"))
                .output(ModItems.LONG_AMMUNITION_GUNPOWDER.get())
                .require(Items.GUNPOWDER)
                .require(Items.GUNPOWDER)
                .require(ModItems.SULFUR_POWDER.get())
                .duration(100)
        );
//        ModRecipeProvider.add(ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.GUNPOWDER, 2).requires(ModTags.longAmmunitionGunpowdersTag()).group("long_downgrade").unlockedBy(RegistrateRecipeProvider.getHasName(Items.GUNPOWDER) + "_long", RegistrateRecipeProvider.has(Items.GUNPOWDER)));

        // basalt crushing
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(CrushingRecipe::new, ResourceHelper.find("basalt_to_powder"))
                .output(40, AllItems.CRUSHED_ZINC.get(), 3)
                .output(40, AllItems.CRUSHED_COPPER.get(), 3)
                .output(40, AllItems.CRUSHED_IRON.get(), 3)
                .output(60, ModItems.BASALT_POWDER.get(), 6) // Basalt Powder added here as a useless junk to occupy the chances of crafting
                .output(ModItems.CRUSHED_BASALT.get())
                .require(Items.BASALT)
                .duration(200)
        );

        // crushing for rock, sulfur, and flint powder from crushed basalt
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(CrushingRecipe::new, ResourceHelper.find("crushed_basalt_to_powder"))
                .output(50, ModItems.ROCK_POWDER.get(), 2) // Rock Powder added here as a useless junk to occupy the chances of crafting
                .output(30, ModItems.SULFUR_POWDER.get(), 3)
                .output(30, ModItems.FLINT_POWDER.get(), 3)
                .require(ModItems.CRUSHED_BASALT.get())
                .duration(200)
        );

        // milling for sulfur and flint powder from flint
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(MillingRecipe::new, ResourceHelper.find("flint_powder"))
                .output(ModItems.FLINT_POWDER.get(), 3)
                .output(70, ModItems.SULFUR_POWDER.get(), 2)
                .output(30, ModItems.SULFUR_POWDER.get(), 1)
                .require(Items.FLINT)
                .duration(100)
        );

        // Iron ingot to Blank Cast
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(CuttingRecipe::new, ResourceHelper.find("iron_ingot_to_cast"))
                .output(ModItems.BLANK_CAST.get(), 5)
                .require(Items.IRON_INGOT)
                .duration(100)
        );

        // Melting Copper Nuggets
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(MixingRecipe::new, ResourceHelper.find("melting/copper_nugget_to_molten_copper"))
                .output(ModFluids.MOLTEN_COPPER.get(), 40)
                .require(AllItems.COPPER_NUGGET.get())
                .requiresHeat(HeatCondition.HEATED)
                .duration(100));

        // Melting Copper
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(MixingRecipe::new, ResourceHelper.find("melting/copper_ingot_to_molten_copper"))
                .output(ModFluids.MOLTEN_COPPER.get(), 360)
                .require(Items.COPPER_INGOT)
                .requiresHeat(HeatCondition.HEATED)
                .duration(200));

        // Melting Brass Nuggets
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(MixingRecipe::new, ResourceHelper.find("melting/brass_nugget_to_molten_brass"))
                .output(ModFluids.MOLTEN_BRASS.get(), 60)
                .require(AllItems.BRASS_NUGGET.get())
                .requiresHeat(HeatCondition.HEATED)
                .duration(100));

        // Melting Brass Ingots
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(MixingRecipe::new, ResourceHelper.find("melting/brass_ingot_to_molten_brass"))
                .output(ModFluids.MOLTEN_BRASS.get(), 540)
                .require(AllItems.BRASS_INGOT.get())
                .requiresHeat(HeatCondition.HEATED)
                .duration(200));

        // Melting Iron Nuggets
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(MixingRecipe::new, ResourceHelper.find("melting/iron_nugget_to_molten_iron"))
                .output(ModFluids.MOLTEN_METAL_ALLOY.get(), 40)
                .require(Items.IRON_NUGGET)
                .requiresHeat(HeatCondition.HEATED)
                .duration(100));

        // Melting Iron Ingots
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(MixingRecipe::new, ResourceHelper.find("melting/iron_ingot_to_molten_iron"))
                .output(ModFluids.MOLTEN_METAL_ALLOY.get(), 360)
                .require(ModItems.CRUSHED_BASALT.get())
                .require(Items.IRON_INGOT)
                .requiresHeat(HeatCondition.HEATED)
                .duration(200));

        // Misc Mixing to Gunpowder
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(MixingRecipe::new, ResourceHelper.find("powders_to_gunpowder"))
                .output(Items.GUNPOWDER, 2)
                .require(ModItems.FLINT_POWDER.get())
                .require(Items.SUGAR)
                .require(Items.CHARCOAL)
                .duration(100));

        // Ammo Waste to Molten Fluids
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(MixingRecipe::new, ResourceHelper.find("wasted_cartridges_to_fluids"))
                .output(ModFluids.MOLTEN_COPPER.get(), 50)
                .output(ModFluids.MOLTEN_METAL_ALLOY.get(), 50)
                .duration(200)
                .require(ModTags.ammunitionWasteComponentsTag())
                .output(ModFluids.MOLTEN_BRASS.get(), 50));

        // Craft Blank Blueprint from Paper and Dye
        ModRecipeProvider.add(ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BLANK_BLUEPRINT.get(), 3)
                .requires(Items.PAPER)
                .requires(Items.WHITE_DYE)
                .requires(Items.BLUE_DYE)
                .unlockedBy(RegistrateRecipeProvider.getHasName(Items.PAPER), RegistrateRecipeProvider.has(Items.PAPER)));

        // Fill Molten Basalt Infused Iron Bucket
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(FillingRecipe::new, ResourceHelper.find("buckets/molten_metal_alloy_bucket"))
                .output(ModItems.MOLTEN_METAL_ALLOY_BUCKET.get())
                .require(Items.BUCKET)
                .require(ModFluids.MOLTEN_METAL_ALLOY.get(), 250));

        // Fill Molten Brass Bucket
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(FillingRecipe::new, ResourceHelper.find("buckets/molten_brass_bucket"))
                .output(ModItems.MOLTEN_BRASS_BUCKET.get())
                .require(Items.BUCKET)
                .require(ModFluids.MOLTEN_BRASS.get(), 250));

        // Fill Molten Copper Bucket
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(FillingRecipe::new, ResourceHelper.find("buckets/molten_copper_bucket"))
                .output(ModItems.MOLTEN_COPPER_BUCKET.get())
                .require(Items.BUCKET)
                .require(ModFluids.MOLTEN_COPPER.get(), 250));

//        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(ProcessingRecipe::new, ));

        // Empty Molten Basalt Infused Iron Bucket
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(EmptyingRecipe::new, ResourceHelper.find("buckets/empty_molten_metal_alloy_bucket"))
                .output(Items.BUCKET)
                .require(ModItems.MOLTEN_METAL_ALLOY_BUCKET.get())
                .output(ModFluids.MOLTEN_METAL_ALLOY.get(), 250));

        // Empty Molten Brass Bucket
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(EmptyingRecipe::new, ResourceHelper.find("buckets/empty_molten_brass_bucket"))
                .output(Items.BUCKET)
                .require(ModItems.MOLTEN_BRASS_BUCKET.get())
                .output(ModFluids.MOLTEN_BRASS.get(), 250));

        // Empty Molten Copper Bucket
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(EmptyingRecipe::new, ResourceHelper.find("buckets/empty_molten_copper_bucket"))
                .output(Items.BUCKET)
                .require(ModItems.MOLTEN_COPPER_BUCKET.get())
                .output(ModFluids.MOLTEN_COPPER.get(), 250));

        // Decomponentalizer Crafting Recipe
//        MechanicalCraftingRecipeBuilder.shapedRecipe(ModBlocks.DECOMPONENTALIZER.get())
//                .key('E', AllItems.ELECTRON_TUBE.get())
//                .key('P', AllItems.IRON_SHEET.get())
//                .key('H', AllItems.BRASS_HAND.get())
//                .key('S', AllBlocks.ANDESITE_CASING.get())
//                .key('I', Blocks.IRON_BLOCK)
//                .key('M', AllItems.PRECISION_MECHANISM.get())
//                .key('G', Blocks.GLASS_PANE)
//                .patternLine("PPPP")
//                .patternLine("PHEP")
//                .patternLine("PMGP")
//                .patternLine("SIIS").build(pFinishedRecipeConsumer);

        // Compacting Pellets
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(CompactingRecipe::new, ResourceHelper.find("compacting_to_iron_pellet_cluster")));
    }
}
