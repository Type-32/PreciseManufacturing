package cn.crtlprototypestudios.prma.foundation.neo.complex.content.processing.casting_basin.recipe;

import cn.crtlprototypestudios.prma.foundation.PrmaRecipeTypes;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.compat.jei.category.sequencedAssembly.SequencedAssemblySubCategory;
import com.simibubi.create.content.processing.recipe.ProcessingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.simibubi.create.content.processing.sequenced.IAssemblyRecipe;
import com.simibubi.create.foundation.fluid.FluidIngredient;
import net.createmod.catnip.lang.Lang;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class CastingRecipe extends ProcessingRecipe<RecipeWrapper> implements IAssemblyRecipe {

    public CastingRecipe(ProcessingRecipeBuilder.ProcessingRecipeParams params) {
        super(PrmaRecipeTypes.CASTING, params);
    }

    @Override
    public boolean matches(RecipeWrapper inv, @NotNull Level worldIn) {
        if (inv.getContainerSize() < 1)
            return false;

        ItemStack castItem = inv.getItem(0);
        return ingredients.get(0).test(castItem);
    }

    @Override
    public @NotNull ItemStack assemble(RecipeWrapper recipeWrapper, RegistryAccess registryAccess) {
        return recipeWrapper.getItem(1); // TODO: CHANGE THIS. THIS IS TEMPORARY PATCH.
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return false;
    }

    @Override
    public @NotNull ItemStack getResultItem(RegistryAccess registryAccess) {
        return this.results.get(1).getStack(); // TODO: CHANGE THIS. THIS IS TEMPORARY PATCH.
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return PrmaRecipeTypes.CASTING.getId();
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return PrmaRecipeTypes.CASTING.getSerializer();
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return PrmaRecipeTypes.CASTING.getType();
    }

    @Override
    protected int getMaxInputCount() {
        return 1; // Only cast item
    }

    @Override
    protected int getMaxOutputCount() {
        return 1; // Only output item
    }

    @Override
    protected int getMaxFluidInputCount() {
        return 1; // Only one fluid input
    }

    @Override
    public boolean canSpecifyDuration() {
        return true;
    }

    public boolean hasNoResult() {
        return results.isEmpty();
    }

    public FluidIngredient getRequiredFluid() {
        if (fluidIngredients.isEmpty())
            throw new IllegalStateException("Filling Recipe: " + id.toString() + " has no fluid ingredient!");
        return fluidIngredients.get(0);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public Component getDescriptionForAssembly() {
        List<FluidStack> matchingFluidStacks = fluidIngredients.get(0)
                .getMatchingFluidStacks();
        if (matchingFluidStacks.isEmpty())
            return Component.literal("Invalid");
        return Lang.builder("prma").translate("recipe.assembly.spout_filling_fluid",
                matchingFluidStacks.get(0).getDisplayName().getString()).component();
    }

    @Override
    public void addRequiredMachines(Set<ItemLike> list) {
        list.add(AllBlocks.SPOUT.get());
    }

    @Override
    public void addAssemblyIngredients(List<Ingredient> list) {}

    @Override
    public void addAssemblyFluidIngredients(List<FluidIngredient> list) {
        list.add(getRequiredFluid());
    }

    @Override
    public Supplier<Supplier<SequencedAssemblySubCategory>> getJEISubCategory() {
        // TODO Remember to change this later
        return () -> SequencedAssemblySubCategory.AssemblySpouting::new;
    }
}