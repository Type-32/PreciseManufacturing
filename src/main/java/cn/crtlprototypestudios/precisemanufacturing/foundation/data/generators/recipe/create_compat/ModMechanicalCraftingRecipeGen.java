package cn.crtlprototypestudios.precisemanufacturing.foundation.data.generators.recipe.create_compat;

import cn.crtlprototypestudios.precisemanufacturing.foundation.ModBlocks;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import com.simibubi.create.foundation.data.recipe.MechanicalCraftingRecipeBuilder;
import com.simibubi.create.foundation.data.recipe.MechanicalCraftingRecipeGen;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Blocks;

public class ModMechanicalCraftingRecipeGen {
    public static final MechanicalCraftingRecipeBuilder

    DECOMPONENTALIZER_BLOCK = MechanicalCraftingRecipeBuilder.shapedRecipe(ModBlocks.DECOMPONENTALIZER.get())
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
            .patternLine("SIIS");

    public static void register(){
//        ModMechanicalCraftingRecipeGen.DECOMPONENTALIZER_BLOCK.r
    }
}
