package cn.crtlprototypestudios.precisemanufacturing.foundation.recipe.decomponentalizing;

import com.simibubi.create.content.processing.recipe.ProcessingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import java.util.List;

public class DecomponentalizingRecipe implements Recipe<SimpleContainer> {
    private final ResourceLocation id;
    private final Ingredient ingredient;
    private final ItemStack result;
    private final int processingTime;

    public DecomponentalizingRecipe(ResourceLocation id, Ingredient ingredient, ItemStack result, int processingTime) {
        this.id = id;
        this.ingredient = ingredient;
        this.result = result;
        this.processingTime = processingTime;
    }

    @Override
    public boolean matches(SimpleContainer inventory, Level level) {
        return ingredient.test(inventory.getItem(0));
    }

    @Override
    public ItemStack assemble(SimpleContainer inventory) {
        return result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return result;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return DecomponentalizingRecipeSerializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return DecomponentalizingRecipeType.INSTANCE;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public int getProcessingTime() {
        return processingTime;
    }

    public static class Type implements RecipeType<DecomponentalizingRecipe> {
        private Type() {
        }

        public static final Type INSTANCE = new Type();
        public static final String ID = "decomponentalizing";
    }
}
