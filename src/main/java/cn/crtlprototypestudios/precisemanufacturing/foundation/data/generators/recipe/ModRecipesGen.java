package cn.crtlprototypestudios.precisemanufacturing.foundation.data.generators.recipe;

import cn.crtlprototypestudios.precisemanufacturing.foundation.ModBlocks;
import cn.crtlprototypestudios.precisemanufacturing.foundation.ModFluids;
import cn.crtlprototypestudios.precisemanufacturing.foundation.ModItems;
import cn.crtlprototypestudios.precisemanufacturing.foundation.ModTags;
import cn.crtlprototypestudios.precisemanufacturing.foundation.data.builders.recipe.DecomponentalizingRecipeBuilder;
import cn.crtlprototypestudios.precisemanufacturing.foundation.data.providers.ModRecipeProvider;
import cn.crtlprototypestudios.precisemanufacturing.foundation.util.ResourceHelper;
import com.simibubi.create.AllBlocks;
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
import com.simibubi.create.content.processing.recipe.ProcessingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.simibubi.create.foundation.data.recipe.CreateRecipeProvider;
import com.simibubi.create.foundation.data.recipe.MechanicalCraftingRecipeBuilder;
import com.simibubi.create.foundation.data.recipe.WashingRecipeGen;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static com.tterrag.registrate.providers.RegistrateRecipeProvider.inventoryTrigger;

public class ModRecipesGen {

    public static void register(Consumer<FinishedRecipe> pFinishedRecipeConsumer){
        // Sulfur Powder Washing
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(SplashingRecipe::new, ResourceHelper.find("raw_sulfur_powder_splashing")).output(ModItems.SULFUR_POWDER.get()).require(ModItems.RAW_SULFUR_POWDER.get()).duration(80));

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
                .require(ModItems.RAW_SULFUR_POWDER.get())
                .require(ModItems.RAW_SULFUR_POWDER.get())
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
                .output(20, ModItems.RAW_ZINC_POWDER.get(), 4)
                .output(20, ModItems.RAW_COPPER_POWDER.get(), 4)
                .output(40, ModItems.BASALT_POWDER.get(), 6)
                .output(40, ModItems.CRUSHED_BASALT.get())
                .require(Items.BASALT)
                .duration(300)
        );

        // crushed basalt milling
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(MillingRecipe::new, ResourceHelper.find("crushed_basalt_to_powder"))
                .output(50, ModItems.BASALT_POWDER.get(), 2)
                .output(50, ModItems.RAW_SULFUR_POWDER.get(), 6)
                .require(ModItems.CRUSHED_BASALT.get())
                .duration(300)
        );

        // crushed basalt crushing
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(CrushingRecipe::new, ResourceHelper.find("crushed_basalt_to_powder"))
                .output(50, ModItems.BASALT_POWDER.get(), 2)
                .output(50, ModItems.RAW_SULFUR_POWDER.get(), 6)
                .output(10, ModItems.FLINT_POWDER.get(), 2)
                .require(ModItems.CRUSHED_BASALT.get())
                .duration(300)
        );

        // flint powder milling
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(MillingRecipe::new, ResourceHelper.find("flint_powder"))
                .output(ModItems.FLINT_POWDER.get(), 3)
                .require(Items.FLINT)
                .duration(100)
        );

        // rock and sulfur powder milling
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(MillingRecipe::new, ResourceHelper.find("rock_and_sulfur_powder"))
                .output(ModItems.ROCK_POWDER.get(), 3)
                .output(50, ModItems.RAW_SULFUR_POWDER.get(), 3)
                .output(5, ModItems.FLINT_POWDER.get(), 1)
                .output(20, ModItems.RAW_COPPER_POWDER.get(), 2)
                .output(20, ModItems.RAW_ZINC_POWDER.get(), 2)
                .require(ModTags.millableRocksTag())
                .duration(200)
        );

        // Iron ingot to Blank Cast
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(CuttingRecipe::new, ResourceHelper.find("iron_ingot_to_cast"))
                .output(ModItems.BLANK_CAST.get(), 5)
                .require(Items.IRON_INGOT)
                .duration(100)
        );

        // Melting Copper Nuggets
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(MixingRecipe::new, ResourceHelper.find("melting/copper_nugget_to_molten_copper"))
                .output(ModFluids.MOLTEN_COPPER.get(), 80)
                .require(ModItems.BASALT_POWDER.get())
                .require(AllItems.COPPER_NUGGET.get())
                .requiresHeat(HeatCondition.HEATED)
                .duration(160));

        // Melting Copper Ingots
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(MixingRecipe::new, ResourceHelper.find("melting/copper_ingot_to_molten_copper"))
                .output(ModFluids.MOLTEN_COPPER.get(), 720)
                .require(ModItems.CRUSHED_BASALT.get())
                .require(Items.COPPER_INGOT)
                .requiresHeat(HeatCondition.HEATED)
                .duration(360));

        // Melting Brass Nuggets
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(MixingRecipe::new, ResourceHelper.find("melting/brass_nugget_to_molten_brass"))
                .output(ModFluids.MOLTEN_BRASS.get(), 80)
                .require(ModItems.BASALT_POWDER.get())
                .require(AllItems.BRASS_NUGGET.get())
                .requiresHeat(HeatCondition.HEATED)
                .duration(160));

        // Melting Brass Ingots
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(MixingRecipe::new, ResourceHelper.find("melting/brass_ingot_to_molten_brass"))
                .output(ModFluids.MOLTEN_BRASS.get(), 720)
                .require(ModItems.CRUSHED_BASALT.get())
                .require(AllItems.BRASS_INGOT.get())
                .requiresHeat(HeatCondition.HEATED)
                .duration(360));

        // Melting Iron Nuggets
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(MixingRecipe::new, ResourceHelper.find("melting/iron_nugget_to_molten_iron"))
                .output(ModFluids.MOLTEN_BASALT_INFUSED_IRON.get(), 80)
                .require(ModItems.BASALT_POWDER.get())
                .require(Items.IRON_NUGGET)
                .requiresHeat(HeatCondition.HEATED)
                .duration(160));

        // Melting Iron Ingots
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(MixingRecipe::new, ResourceHelper.find("melting/iron_ingot_to_molten_iron"))
                .output(ModFluids.MOLTEN_BASALT_INFUSED_IRON.get(), 720)
                .require(ModItems.CRUSHED_BASALT.get())
                .require(Items.IRON_INGOT)
                .requiresHeat(HeatCondition.HEATED)
                .duration(360));

        // Flint and Rock Powder mix to Basalt Powder
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(MixingRecipe::new, ResourceHelper.find("solids/flint_and_rock_to_basalt_powder"))
                .output(ModItems.BASALT_POWDER.get(), 2)
                .require(ModItems.FLINT_POWDER.get())
                .require(ModItems.ROCK_POWDER.get())
                .duration(300));

        // Misc Mixing to Gunpowder
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(MixingRecipe::new, ResourceHelper.find("powders_to_gunpowder"))
                .output(Items.GUNPOWDER, 2)
                .require(ModItems.BASALT_POWDER.get())
                .require(ModItems.FLINT_POWDER.get())
                .require(Items.SUGAR)
                .require(Items.CHARCOAL)
                .duration(100));

        // Ammo Waste to Molten Fluids
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(MixingRecipe::new, ResourceHelper.find("wasted_cartridges_to_fluids"))
                .output(ModFluids.MOLTEN_COPPER.get(), 50)
                .output(ModFluids.MOLTEN_BASALT_INFUSED_IRON.get(), 50)
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
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(FillingRecipe::new, ResourceHelper.find("buckets/molten_basalt_infused_iron_bucket"))
                .output(ModItems.MOLTEN_BASALT_INFUSED_IRON_BUCKET.get())
                .require(Items.BUCKET)
                .require(ModFluids.MOLTEN_BASALT_INFUSED_IRON.get(), 250));

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

        // Empty Molten Basalt Infused Iron Bucket
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(EmptyingRecipe::new, ResourceHelper.find("buckets/empty_molten_basalt_infused_iron_bucket"))
                .output(Items.BUCKET)
                .require(ModItems.MOLTEN_BASALT_INFUSED_IRON_BUCKET.get())
                .output(ModFluids.MOLTEN_BASALT_INFUSED_IRON.get(), 250));

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
        MechanicalCraftingRecipeBuilder.shapedRecipe(ModBlocks.DECOMPONENTALIZER.get())
                .key('E', AllItems.ELECTRON_TUBE.get())
                .key('P', AllItems.IRON_SHEET.get())
                .key('H', AllItems.BRASS_HAND.get())
                .key('S', AllBlocks.ANDESITE_CASING.get())
                .key('I', Blocks.IRON_BLOCK)
                .key('M', AllItems.PRECISION_MECHANISM.get())
                .key('G', Blocks.GLASS_PANE)
                .patternLine("PPPP")
                .patternLine("PHEP")
                .patternLine("PMGP")
                .patternLine("SIIS").build(pFinishedRecipeConsumer);

        // Copper Dust Mixing into Nugget
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(MixingRecipe::new, ResourceHelper.find("melting/copper_dust_to_nugget"))
                .output(AllItems.COPPER_NUGGET.get(), 2)
                .require(ModItems.RAW_COPPER_POWDER.get())
                .require(ModItems.RAW_COPPER_POWDER.get())
                .duration(200)
                .require(Items.IRON_NUGGET));

        // Zinc Dust Mixing into Nugget
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(MixingRecipe::new, ResourceHelper.find("melting/zinc_dust_to_nugget"))
                .output(AllItems.ZINC_NUGGET.get(), 2)
                .require(ModItems.RAW_ZINC_POWDER.get())
                .require(ModItems.RAW_ZINC_POWDER.get())
                .duration(200)
                .require(Items.IRON_NUGGET));

        // Compacting Pellets
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(CompactingRecipe::new, ResourceHelper.find("compacting_to_iron_pellet_cluster")));
    }
}
