package cn.crtlprototypestudios.prma.foundation.data.generators.recipe;

import cn.crtlprototypestudios.prma.PreciseManufacturing;
import cn.crtlprototypestudios.prma.foundation.PrmaFluids;
import cn.crtlprototypestudios.prma.foundation.PrmaItems;
import cn.crtlprototypestudios.prma.foundation.PrmaTags;
import cn.crtlprototypestudios.prma.foundation.neo.complex.bridge.TaczAPIBridge;
import cn.crtlprototypestudios.prma.foundation.neo.complex.content.item.collection.StandardCartridgeComponents;
import cn.crtlprototypestudios.prma.foundation.neo.complex.content.processing.casting_basin.recipe.CastingRecipe;
import cn.crtlprototypestudios.prma.foundation.neo.simple.item.SimpleAmmo;
import cn.crtlprototypestudios.prma.foundation.neo.simple.item.SimpleCartridge;
import cn.crtlprototypestudios.prma.foundation.utility.ResourceHelper;
import com.simibubi.create.AllItems;
import com.simibubi.create.content.decoration.palettes.AllPaletteStoneTypes;
import com.simibubi.create.content.fluids.transfer.EmptyingRecipe;
import com.simibubi.create.content.fluids.transfer.FillingRecipe;
import com.simibubi.create.content.kinetics.crusher.CrushingRecipe;
import com.simibubi.create.content.kinetics.deployer.DeployerApplicationRecipe;
import com.simibubi.create.content.kinetics.millstone.MillingRecipe;
import com.simibubi.create.content.kinetics.mixer.MixingRecipe;
import com.simibubi.create.content.kinetics.press.PressingRecipe;
import com.simibubi.create.content.kinetics.saw.CuttingRecipe;
import com.simibubi.create.content.processing.recipe.HeatCondition;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.simibubi.create.content.processing.sequenced.SequencedAssemblyRecipeBuilder;
import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ModRecipesGen {
    private static List<RecipeBuilder> recipeBuilders = new ArrayList<>();
    private static List<ProcessingRecipeBuilder<?>> createCompatRecipeBuilders = new ArrayList<>();
    private static List<SequencedAssemblyRecipeBuilder> sequencedAssemblyRecipeBuilders = new ArrayList<>();
    private static List<SimpleCartridge> simpleCartridges = new ArrayList<>();
    private static List<SimpleAmmo> simpleAmmos = new ArrayList<>();

    public static void register(Consumer<FinishedRecipe> pFinishedRecipeConsumer){
        registerCreateRecipes();
        registerVanillaRecipes();
        PrmaItems.ALL_CARTRIDGE_COMPONENTS.forEach(StandardCartridgeComponents::registerRecipes);
        simpleCartridges.forEach(SimpleCartridge::registerStandardRecipes);
        simpleAmmos.forEach(SimpleAmmo::registerRecipes);

        PreciseManufacturing.LOGGER.debug("assemblies {}", sequencedAssemblyRecipeBuilders.size());

        recipeBuilders.forEach(i -> i.save(pFinishedRecipeConsumer));
        createCompatRecipeBuilders.forEach(i -> i.build(pFinishedRecipeConsumer));
        sequencedAssemblyRecipeBuilders.forEach(i -> i.build(pFinishedRecipeConsumer));
    }

    public static void registerCreateRecipes() {
        registerMillingRecipes();
        registerCrushingRecipes();
        registerCuttingRecipes();
        registerEmptyingRecipes();
        registerFillingRecipes();
        registerMixingRecipes();
//        registerCastingRecipes();

        addSequencedAssemblyRecipe(new SequencedAssemblyRecipeBuilder(ResourceHelper.find("ammo/12g"))
                .require(PrmaItems.Ammo.SHOTGUN_SHELL_BASE.get())
                .transitionTo(PrmaItems.Ammo.SHOTGUN_SHELL_TRANSITION.get())
                .loops(1)
                .addStep(DeployerApplicationRecipe::new, p -> p.require(PrmaItems.Ammo.SHOTGUN_SHELL.get()))
                .addStep(DeployerApplicationRecipe::new, p -> p.require(PrmaItems.MEDIUM_AMMUNITION_GUNPOWDER.get()))
                .addStep(DeployerApplicationRecipe::new, p -> p.require(PrmaItems.Ammo.SHOTGUN_BEARING.get()))
                .addStep(DeployerApplicationRecipe::new, p -> p.require(PrmaItems.Ammo.SHOTGUN_BEARING.get()))
                .addStep(PressingRecipe::new, p -> p)
                .addOutput(TaczAPIBridge.getAmmo("12g"), 1)
        );

        addSequencedAssemblyRecipe(new SequencedAssemblyRecipeBuilder(ResourceHelper.find("ammo/40mm"))
                .require(PrmaItems.Ammo.FORTY_MIL_CASING.get())
                .transitionTo(PrmaItems.Ammo.FORTY_MIL_CASING.get())
                .loops(1)
                .addStep(DeployerApplicationRecipe::new, p -> p.require(PrmaItems.HIGH_POWER_AMMUNITION_GUNPOWDER.get()))
                .addStep(DeployerApplicationRecipe::new, p -> p.require(PrmaItems.Ammo.FORTY_MIL_SLUG.get()))
                .addStep(PressingRecipe::new, p -> p)
                .addOutput(TaczAPIBridge.getAmmo("40mm"), 1)
        );

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
//        addCreateRecipe(new ProcessingRecipeBuilder<>(CompactingRecipe::new, ResourceHelper.find("compacting_to_iron_pellet_cluster")));
    }

    public static void registerCastingRecipes() {
        // Casting Molten Copper to Ingot
        addCreateRecipe(new ProcessingRecipeBuilder<>(CastingRecipe::new, ResourceHelper.find("casting/casting_molten_copper_to_ingot"))
                .output(Items.COPPER_INGOT)
                .require(PrmaTags.FluidTag.MOLTEN_COPPER_FLUIDS.tag, 100)
                .require(PrmaItems.Cast.INGOT_CAST.get())
                .duration(100));

        // Casting Molten Zinc to Ingot
        addCreateRecipe(new ProcessingRecipeBuilder<>(CastingRecipe::new, ResourceHelper.find("casting/casting_molten_zinc_to_ingot"))
                .output(AllItems.ZINC_INGOT)
                .require(PrmaTags.FluidTag.MOLTEN_ZINC_FLUIDS.tag, 100)
                .require(PrmaItems.Cast.INGOT_CAST.get())
                .duration(100));

        // Casting Molten Iron to Ingot
        addCreateRecipe(new ProcessingRecipeBuilder<>(CastingRecipe::new, ResourceHelper.find("casting/casting_molten_iron_to_ingot"))
                .output(Items.IRON_INGOT)
                .require(PrmaTags.FluidTag.MOLTEN_IRON_FLUIDS.tag, 100)
                .require(PrmaItems.Cast.INGOT_CAST.get())
                .duration(100));

//        // Casting Molten Aluminum to Ingot
//        addCreateRecipe(new ProcessingRecipeBuilder<>(CastingRecipe::new, ResourceHelper.find("casting/casting_molten_aluminum_to_ingot"))
//                .output(PrmaItems.ALUMINUM_INGOT.get())
//                .require(PrmaTags.FluidTag.MOLTEN_ALUMINUM_FLUIDS.tag, 100)
//                .require(PrmaItems.Cast.INGOT_CAST.get())
//                .duration(100));
//
//        // Casting Molten Strong Aluminum to Ingot
//        addCreateRecipe(new ProcessingRecipeBuilder<>(CastingRecipe::new, ResourceHelper.find("casting/casting_molten_aluminum_to_ingot"))
//                .output(PrmaItems.STRONG_ALUMINUM_ALLOY_INGOT.get())
//                .require(PrmaTags.FluidTag.MOLTEN_STRONG_ALUMINUM_ALLOY_FLUIDS.tag, 100)
//                .require(PrmaItems.Cast.INGOT_CAST.get())
//                .duration(100));
    }

    public static void registerCuttingRecipes() {
        addCreateRecipe(new ProcessingRecipeBuilder<>(CuttingRecipe::new, ResourceHelper.find("cartridge_primer"))
                .output(PrmaItems.Ammo.CARTRIDGE_PRIMER.get(), 8)
                .require(AllItems.IRON_SHEET)
                .duration(100));

//        // Iron ingot to Blank Cast
//        addCreateRecipe(new ProcessingRecipeBuilder<>(CuttingRecipe::new, ResourceHelper.find("iron_ingot_to_cast"))
//                .output(PrmaItems.BLANK_CAST.get(), 5)
//                .require(Items.IRON_INGOT)
//                .duration(100)
//        );

        // Paper to Shotgun Shell
        addCreateRecipe(new ProcessingRecipeBuilder<>(CuttingRecipe::new, ResourceHelper.find("shotgun_shell"))
                .output(PrmaItems.Ammo.SHOTGUN_SHELL.get(), 2)
                .require(Items.PAPER)
                .duration(100)
        );

        // Brass Sheet to Shotgun Shell Base
        addCreateRecipe(new ProcessingRecipeBuilder<>(CuttingRecipe::new, ResourceHelper.find("shotgun_shell_base"))
                .output(PrmaItems.Ammo.SHOTGUN_SHELL_BASE.get(), 1)
                .require(AllItems.BRASS_SHEET)
                .duration(100)
        );

        // Iron Sheet to Shotgun bearing
        addCreateRecipe(new ProcessingRecipeBuilder<>(CuttingRecipe::new, ResourceHelper.find("shotgun_bearing"))
                .output(PrmaItems.Ammo.SHOTGUN_BEARING.get(), 4)
                .require(AllItems.IRON_SHEET)
                .duration(80)
        );

        // 40mm Casing
        addCreateRecipe(new ProcessingRecipeBuilder<>(CuttingRecipe::new, ResourceHelper.find("40mm_casing"))
                .output(PrmaItems.Ammo.FORTY_MIL_CASING.get())
                .require(AllItems.IRON_SHEET)
                .duration(160)
        );

        // 40mm Slug
        addCreateRecipe(new ProcessingRecipeBuilder<>(CuttingRecipe::new, ResourceHelper.find("40mm_slug"))
                .output(PrmaItems.Ammo.FORTY_MIL_SLUG.get(), 2)
                .require(AllItems.BRASS_INGOT)
                .duration(160)
        );
    }

    public static void registerCrushingRecipes() {

        // Crushing for Crushed zinc, crushed copper, crushed iron, crushed aluminum, and basalt powder from basalt
        addCreateRecipe(new ProcessingRecipeBuilder<>(CrushingRecipe::new, ResourceHelper.find("basalt_to_powder"))
                .output(0.4f, AllItems.CRUSHED_ZINC.get(), 5)
                .output(0.4f, AllItems.CRUSHED_COPPER.get(), 3)
                .output(0.2f, AllItems.CRUSHED_IRON.get(), 1)
                .output(0.6f, PrmaItems.SULFUR_POWDER.get(), 3)
                .require(Items.BASALT)
                .duration(200)
        );

        // The same as above but faster because its smooth basalt lol
        addCreateRecipe(new ProcessingRecipeBuilder<>(CrushingRecipe::new, ResourceHelper.find("smooth_basalt_to_powder"))
                .output(0.4f, AllItems.CRUSHED_ZINC.get(), 6)
                .output(0.4f, AllItems.CRUSHED_COPPER.get(), 4)
                .output(0.2f, AllItems.CRUSHED_IRON.get(), 1)
                .output(0.6f, PrmaItems.SULFUR_POWDER.get(), 4)
                .require(Items.SMOOTH_BASALT)
                .duration(140)
        );

        // Crushing for Rock powder, crushed copper, crushed zinc, and crushed aluminum from limestone
        addCreateRecipe(new ProcessingRecipeBuilder<>(CrushingRecipe::new, ResourceHelper.find("limestone_to_powder"))
                .output(0.6f, PrmaItems.ROCK_POWDER.get(), 2) // Rock Powder added here as a useless junk to occupy the chances of crafting
                .output(0.3f, AllItems.CRUSHED_COPPER.get(), 2)
                .output(0.3f, AllItems.CRUSHED_ZINC.get(), 2)
                .output(0.3f, PrmaItems.SULFUR_POWDER.get(), 2)
                .require(AllPaletteStoneTypes.LIMESTONE.baseBlock.get())
                .duration(140)
        );

        // crushing for rock, sulfur, and flint powder from crushed basalt
//        addCreateRecipe(new ProcessingRecipeBuilder<>(CrushingRecipe::new, ResourceHelper.find("crushed_basalt_to_powder"))
//                .output(0.5f, PrmaItems.ROCK_POWDER.get(), 2) // Rock Powder added here as a useless junk to occupy the chances of crafting
//                .output(0.3f, PrmaItems.SULFUR_POWDER.get(), 4)
//                .output(0.3f, PrmaItems.FLINT_POWDER.get(), 3)
//                .require(0.3f, PrmaItems.CRUSHED_BASALT.get())
//                .duration(140)
//        );
    }

    public static void registerEmptyingRecipes() {
        // Empty Molten Metal Alloy Bucket
//        addCreateRecipe(new ProcessingRecipeBuilder<>(EmptyingRecipe::new, ResourceHelper.find("buckets/empty_molten_metal_alloy_bucket"))
//                .output(Items.BUCKET)
//                .require(PrmaItems.MOLTEN_METAL_ALLOY_BUCKET.get())
//                .output(PrmaFluids.MOLTEN_METAL_ALLOY.get(), 250));
//
//        // Empty Molten Copper Bucket
//        addCreateRecipe(new ProcessingRecipeBuilder<>(EmptyingRecipe::new, ResourceHelper.find("buckets/empty_molten_copper_bucket"))
//                .output(Items.BUCKET)
//                .require(PrmaItems.MOLTEN_COPPER_BUCKET.get())
//                .output(PrmaFluids.MOLTEN_COPPER.get(), 250));
//
//        // Empty Molten Iron Bucket
//        addCreateRecipe(new ProcessingRecipeBuilder<>(EmptyingRecipe::new, ResourceHelper.find("buckets/empty_molten_iron_bucket"))
//                .output(Items.BUCKET)
//                .require(PrmaItems.MOLTEN_IRON_BUCKET.get())
//                .output(PrmaFluids.MOLTEN_IRON.get(), 250));
//
//        // Empty Molten Aluminum Bucket
//        addCreateRecipe(new ProcessingRecipeBuilder<>(EmptyingRecipe::new, ResourceHelper.find("buckets/empty_molten_aluminum_bucket"))
//                .output(Items.BUCKET)
//                .require(PrmaItems.MOLTEN_ALUMINUM_BUCKET.get())
//                .output(PrmaFluids.MOLTEN_ALUMINUM.get(), 250));
//
//        // Empty Molten Strong Aluminum Bucket
//        addCreateRecipe(new ProcessingRecipeBuilder<>(EmptyingRecipe::new, ResourceHelper.find("buckets/empty_molten_strong_aluminum_bucket"))
//                .output(Items.BUCKET)
//                .require(PrmaItems.MOLTEN_STRONG_ALUMINUM_ALLOY_BUCKET.get())
//                .output(PrmaFluids.MOLTEN_STRONG_ALUMINUM_ALLOY.get(), 250));
//
//        // Empty Molten Strong Aluminum Bucket
//        addCreateRecipe(new ProcessingRecipeBuilder<>(EmptyingRecipe::new, ResourceHelper.find("buckets/empty_molten_zinc_bucket"))
//                .output(Items.BUCKET)
//                .require(PrmaItems.MOLTEN_ZINC_BUCKET.get())
//                .output(PrmaFluids.MOLTEN_ZINC.get(), 250));
    }

    public static void registerFillingRecipes() {
//        // Fill Molten Metal Alloy Bucket
//        addCreateRecipe(new ProcessingRecipeBuilder<>(FillingRecipe::new, ResourceHelper.find("buckets/molten_metal_alloy_bucket"))
//                .output(PrmaItems.MOLTEN_METAL_ALLOY_BUCKET.get())
//                .require(Items.BUCKET)
//                .require(PrmaFluids.MOLTEN_METAL_ALLOY.get(), 250));
//
//        // Fill Molten Copper Bucket
//        addCreateRecipe(new ProcessingRecipeBuilder<>(FillingRecipe::new, ResourceHelper.find("buckets/molten_copper_bucket"))
//                .output(PrmaItems.MOLTEN_COPPER_BUCKET.get())
//                .require(Items.BUCKET)
//                .require(PrmaFluids.MOLTEN_COPPER.get(), 250));
//
//        // Fill Molten Aluminum Bucket
//        addCreateRecipe(new ProcessingRecipeBuilder<>(FillingRecipe::new, ResourceHelper.find("buckets/molten_aluminum_bucket"))
//                .output(PrmaItems.MOLTEN_ALUMINUM_BUCKET.get())
//                .require(Items.BUCKET)
//                .require(PrmaFluids.MOLTEN_ALUMINUM.get(), 250));
//
//        // Fill Molten Strong Aluminum Bucket
//        addCreateRecipe(new ProcessingRecipeBuilder<>(FillingRecipe::new, ResourceHelper.find("buckets/molten_strong_aluminum_bucket"))
//                .output(PrmaItems.MOLTEN_STRONG_ALUMINUM_ALLOY_BUCKET.get())
//                .require(Items.BUCKET)
//                .require(PrmaFluids.MOLTEN_STRONG_ALUMINUM_ALLOY.get(), 250));
//
//        // Fill Molten Zinc Bucket
//        addCreateRecipe(new ProcessingRecipeBuilder<>(FillingRecipe::new, ResourceHelper.find("buckets/molten_zinc_bucket"))
//                .output(PrmaItems.MOLTEN_ZINC_BUCKET.get())
//                .require(Items.BUCKET)
//                .require(PrmaFluids.MOLTEN_ZINC.get(), 250));
    }

    public static void registerMillingRecipes() {
        // milling for sulfur and flint powder from flint
        addCreateRecipe(new ProcessingRecipeBuilder<>(MillingRecipe::new, ResourceHelper.find("flint_powder"))
                .output(PrmaItems.FLINT_POWDER.get(), 3)
                .output(0.7f, PrmaItems.SULFUR_POWDER.get(), 2)
                .output(0.3f, PrmaItems.SULFUR_POWDER.get(), 1)
                .require(Items.FLINT)
                .duration(100)
        );
    }

    public static void registerMixingRecipes() {
        // Small Ammunition Gunpowder Crafting and Uncrafting
        addCreateRecipe(new ProcessingRecipeBuilder<>(MixingRecipe::new, ResourceHelper.find("small_ammunition_gunpowder_mixing"))
                .output(PrmaItems.SMALL_AMMUNITION_GUNPOWDER.get())
                .require(Items.GUNPOWDER)
                .require(PrmaItems.FLINT_POWDER.get())
                .require(PrmaItems.FLINT_POWDER.get())
                .duration(100)
        );
//        ModRecipeProvider.add(ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.GUNPOWDER, 2).requires(ModTags.smallAmmunitionGunpowdersTag()).group("small_downgrade").unlockedBy(RegistrateRecipeProvider.getHasName(Items.GUNPOWDER) + "small", RegistrateRecipeProvider.has(Items.GUNPOWDER)));

        // Medium Ammunition Gunpowder Crafting and Uncrafting
        addCreateRecipe(new ProcessingRecipeBuilder<>(MixingRecipe::new, ResourceHelper.find("medium_ammunition_gunpowder_mixing"))
                .output(PrmaItems.MEDIUM_AMMUNITION_GUNPOWDER.get())
                .require(Items.GUNPOWDER)
                .require(PrmaItems.SULFUR_POWDER.get())
                .duration(100)
        );
//        ModRecipeProvider.add(ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.GUNPOWDER, 2).requires(ModTags.mediumAmmunitionGunpowdersTag()).group("medium_downgrade").unlockedBy(RegistrateRecipeProvider.getHasName(Items.GUNPOWDER) + "_medium", RegistrateRecipeProvider.has(Items.GUNPOWDER)));

        // Long Ammunition Gunpowder Crafting and Uncrafting
        addCreateRecipe(new ProcessingRecipeBuilder<>(MixingRecipe::new, ResourceHelper.find("long_ammunition_gunpowder_mixing"))
                .output(PrmaItems.HIGH_AMMUNITION_GUNPOWDER.get())
                .require(Items.GUNPOWDER)
                .require(Items.GUNPOWDER)
                .require(PrmaItems.SULFUR_POWDER.get())
                .duration(100)
        );

        // High Power Ammunition Gunpowder Crafting and Uncrafting
        addCreateRecipe(new ProcessingRecipeBuilder<>(MixingRecipe::new, ResourceHelper.find("high_power_ammunition_gunpowder_mixing"))
                .output(PrmaItems.HIGH_POWER_AMMUNITION_GUNPOWDER.get())
                .require(Items.GUNPOWDER)
                .require(Items.FLINT)
                .require(PrmaItems.SULFUR_POWDER.get())
                .duration(160)
        );

        // Heated Melting from Copper Nuggets and Basalt Powder to Molten Copper
//        addCreateRecipe(new ProcessingRecipeBuilder<>(MixingRecipe::new, ResourceHelper.find("melting/copper_nugget_to_molten_copper"))
//                .output(PrmaFluids.MOLTEN_COPPER.get(), 40)
//                .require(AllItems.COPPER_NUGGET.get())
//                .require(PrmaItems.BASALT_POWDER.get())
//                .requiresHeat(HeatCondition.HEATED)
//                .duration(100));
//
//        // Heated Melting from Copper Ingots and Basalt Powder to Molten Copper
//        addCreateRecipe(new ProcessingRecipeBuilder<>(MixingRecipe::new, ResourceHelper.find("melting/copper_ingot_to_molten_copper"))
//                .output(PrmaFluids.MOLTEN_COPPER.get(), 360)
//                .require(Items.COPPER_INGOT)
//                .require(PrmaItems.BASALT_POWDER.get())
//                .requiresHeat(HeatCondition.HEATED)
//                .duration(200));
//
//        // Heated Melting from Iron Nuggets and Basalt Powder to Molten Iron
//        addCreateRecipe(new ProcessingRecipeBuilder<>(MixingRecipe::new, ResourceHelper.find("melting/iron_nugget_to_molten_iron"))
//                .output(PrmaFluids.MOLTEN_IRON.get(), 40)
//                .require(Items.IRON_NUGGET)
//                .require(PrmaItems.BASALT_POWDER.get())
//                .requiresHeat(HeatCondition.HEATED)
//                .duration(100));
//
//        // Heated Mealting from Iron Ingots and Crushed Basalt to Molten Iron
//        addCreateRecipe(new ProcessingRecipeBuilder<>(MixingRecipe::new, ResourceHelper.find("melting/iron_ingot_to_molten_iron"))
//                .output(PrmaFluids.MOLTEN_IRON.get(), 360)
//                .require(Items.IRON_INGOT)
//                .require(PrmaItems.BASALT_POWDER.get())
//                .requiresHeat(HeatCondition.HEATED)
//                .duration(200));
//
//        // Heated Mealting from Aluminum Ingots and Crushed Basalt to Molten Aluminum
//        addCreateRecipe(new ProcessingRecipeBuilder<>(MixingRecipe::new, ResourceHelper.find("melting/aluminum_ingot_to_molten_aluminum"))
//                .output(PrmaFluids.MOLTEN_ALUMINUM.get(), 360)
//                .require(PrmaItems.ALUMINUM_INGOT.get())
//                .require(PrmaItems.BASALT_POWDER.get())
//                .requiresHeat(HeatCondition.HEATED)
//                .duration(200));
//
//        // Heated Mealting from Strong Aluminum Ingots and Crushed Basalt to Molten Strong Aluminum
//        addCreateRecipe(new ProcessingRecipeBuilder<>(MixingRecipe::new, ResourceHelper.find("melting/strong_aluminum_ingot_to_molten_strong_aluminum"))
//                .output(PrmaFluids.MOLTEN_STRONG_ALUMINUM_ALLOY.get(), 360)
//                .require(PrmaItems.STRONG_ALUMINUM_ALLOY_INGOT.get())
//                .require(PrmaItems.BASALT_POWDER.get())
//                .requiresHeat(HeatCondition.HEATED)
//                .duration(200));
//
//        // Heated Mixing from Molten
//        addCreateRecipe(new ProcessingRecipeBuilder<>(MixingRecipe::new, ResourceHelper.find("alloying/molten_copper_and_iron_and_zinc_to_metal_alloy"))
//                .output(PrmaFluids.MOLTEN_METAL_ALLOY.get(), 250)
//                .require(PrmaFluids.MOLTEN_COPPER.get(), 100)
//                .require(PrmaFluids.MOLTEN_IRON.get(), 100)
//                .require(PrmaFluids.MOLTEN_ZINC.get(), 50)
//                .requiresHeat(HeatCondition.HEATED)
//                .duration(200));
//
//        // Heated Mixing from Molten Aluminum and Molten Metal Alloy to Molten Strong Aluminum
//        addCreateRecipe(new ProcessingRecipeBuilder<>(MixingRecipe::new, ResourceHelper.find("alloying/molten_aluminum_and_metal_alloy_to_strong_aluminum"))
//                .output(PrmaFluids.MOLTEN_STRONG_ALUMINUM_ALLOY.get(), 200)
//                .require(PrmaFluids.MOLTEN_ALUMINUM.get(), 100)
//                .require(PrmaFluids.MOLTEN_METAL_ALLOY.get(), 100)
//                .requiresHeat(HeatCondition.HEATED)
//                .duration(200));

        // Mixing from Charcoal, Sugar, and Flint powder to gunpowder
        addCreateRecipe(new ProcessingRecipeBuilder<>(MixingRecipe::new, ResourceHelper.find("powders_to_gunpowder"))
                .output(Items.GUNPOWDER, 4)
                .require(PrmaItems.FLINT_POWDER.get())
                .require(Items.SUGAR)
                .require(Items.CHARCOAL)
                .duration(100));

        // Ammo Waste to Molten Fluids
//        addCreateRecipe(new ProcessingRecipeBuilder<>(MixingRecipe::new, ResourceHelper.find("wasted_cartridges_to_fluids"))
//                .output(PrmaFluids.MOLTEN_METAL_ALLOY.get(), 50)
//                .duration(200)
//                .require(PrmaTags.ItemTag.AMMO_WASTE.tag));
    }

    public static void registerVanillaRecipes() {
        // Craft Blank Blueprint from Paper and Dye
//        add(ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, PrmaItems.BLANK_BLUEPRINT.get(), 3)
//                .requires(Items.PAPER)
//                .requires(Items.WHITE_DYE)
//                .requires(Items.BLUE_DYE)
//                .unlockedBy(RegistrateRecipeProvider.getHasName(Items.PAPER), RegistrateRecipeProvider.has(Items.PAPER)));
    }

    public static void addCreateRecipe(ProcessingRecipeBuilder<?> generatedRecipe){
        createCompatRecipeBuilders.add(generatedRecipe);
    }

    public static void addSequencedAssemblyRecipe(SequencedAssemblyRecipeBuilder generatedRecipe){
        sequencedAssemblyRecipeBuilders.add(generatedRecipe);
    }

    public static void addSimpleCartridge(SimpleCartridge simpleCartridge){
        simpleCartridges.add(simpleCartridge);
    }

    public static void addSimpleAmmo(SimpleAmmo simpleAmmo){
        simpleAmmos.add(simpleAmmo);
    }

    public static void add(RecipeBuilder builder){
        recipeBuilders.add(builder);
    }
}
