package cn.crtlprototypestudios.prma.foundation.neo.content.processing.casting_basin.recipe;

import cn.crtlprototypestudios.prma.foundation.PrmaRecipeTypes;
import com.simibubi.create.content.processing.recipe.ProcessingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import org.jetbrains.annotations.NotNull;

public class CastingRecipe extends ProcessingRecipe<RecipeWrapper> {

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
}
