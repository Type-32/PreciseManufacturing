package cn.crtlprototypestudios.precisemanufacturing.foundation.recipe.decomponentalizing;

import cn.crtlprototypestudios.precisemanufacturing.util.Reference;
import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;

import javax.annotation.Nullable;

public class DecomponentalizingRecipeSerializer implements RecipeSerializer<DecomponentalizingRecipe> {
    private DecomponentalizingRecipeSerializer() {
    }

    public static final DecomponentalizingRecipeSerializer INSTANCE = new DecomponentalizingRecipeSerializer();
    public static final ResourceLocation ID = new ResourceLocation(Reference.MOD_ID, "decomponentalizing");

    @Override
    public DecomponentalizingRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
        Ingredient ingredient = Ingredient.fromJson(json.get("ingredient"));
        ItemStack result = ShapedRecipe.itemStackFromJson(json.getAsJsonObject("result"));
        int processingTime = json.get("processingTime").getAsInt();

        return new DecomponentalizingRecipe(recipeId, ingredient, result, processingTime);
    }

    @Nullable
    @Override
    public DecomponentalizingRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
        Ingredient ingredient = Ingredient.fromNetwork(buffer);
        ItemStack result = buffer.readItem();
        int processingTime = buffer.readInt();

        return new DecomponentalizingRecipe(recipeId, ingredient, result, processingTime);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buffer, DecomponentalizingRecipe recipe) {
        recipe.getIngredient().toNetwork(buffer);
        buffer.writeItem(recipe.getResultItem());
        buffer.writeInt(recipe.getProcessingTime());
    }

    @Override
    public RecipeSerializer<?> setRegistryName(ResourceLocation resourceLocation) {
        return null;
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public ResourceLocation getRegistryName() {
        return null;
    }

    @Override
    public Class<RecipeSerializer<?>> getRegistryType() {
        return null;
    }
}
