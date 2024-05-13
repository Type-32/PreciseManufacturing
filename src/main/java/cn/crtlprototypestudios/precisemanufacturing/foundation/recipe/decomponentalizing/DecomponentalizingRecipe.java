package cn.crtlprototypestudios.precisemanufacturing.foundation.recipe.decomponentalizing;

import cn.crtlprototypestudios.precisemanufacturing.util.Reference;
import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

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
        ItemStack itemStack = inventory.getItem(0);

        // Check if the item matches the ingredient
        if (ingredient.test(itemStack)) {
            // Check if the ingredient has NBT data
            if (ingredient.getItems()[0].hasTag()) {
                // Compare the NBT data of the ingredient and the item in the inventory
                CompoundTag ingredientTag = ingredient.getItems()[0].getTag();
                CompoundTag itemTag = itemStack.getTag();

                if (ingredientTag != null && itemTag != null) {
                    // Iterate over the keys in the ingredient tag and compare with the item tag
                    for (String key : ingredientTag.getAllKeys()) {
                        if (!itemTag.contains(key) || !ingredientTag.get(key).equals(itemTag.get(key))) {
                            return false;
                        }
                    }
                    return true;
                }
            } else {
                // If the ingredient doesn't have NBT data, only check the item ID
                return true;
            }
        }

        return false;
    }

    @Override
    public @NotNull ItemStack assemble(SimpleContainer inventory) {
        return result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public @NotNull ItemStack getResultItem() {
        return result;
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return id;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public @NotNull RecipeType<?> getType() {
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

    public static class Serializer implements RecipeSerializer<DecomponentalizingRecipe> {
        public static final Serializer INSTANCE = new Serializer();
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
            return INSTANCE;
        }

        @org.jetbrains.annotations.Nullable
        @Override
        public ResourceLocation getRegistryName() {
            return ID;
        }

        @Override
        public Class<RecipeSerializer<?>> getRegistryType() {
            return Serializer.castClass(RecipeSerializer.class);
        }

        private static <G> Class<G> castClass(Class<?> cls){
            return (Class<G>)cls;
        }
    }
}
