package cn.crtlprototypestudios.precisemanufacturing.foundation.recipe.decomponentalizing;

import cn.crtlprototypestudios.precisemanufacturing.Main;
import cn.crtlprototypestudios.precisemanufacturing.util.Reference;
import com.google.gson.JsonObject;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.crafting.PartialNBTIngredient;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class DecomponentalizingRecipe implements Recipe<SimpleContainer> {
    private final ResourceLocation id;
    private final PartialNBTIngredient ingredient;
    private final ItemStack result;
    private final int processingTime;

    public DecomponentalizingRecipe(ResourceLocation id, PartialNBTIngredient ingredient, ItemStack result, int processingTime) {
        this.id = id;
        this.ingredient = ingredient;
        this.result = result;
        this.processingTime = processingTime;
    }

    @Override
    public boolean matches(SimpleContainer inventory, Level level) {
        ItemStack itemStack = inventory.getItem(0);
//        Main.LOGGER.debug("detecting recipe.... for item with nbt {}", itemStack.getTag());
//        Main.LOGGER.debug("detecting recipe.... for ingredient with nbt {}", ingredient.getItems()[0].getTag());

        return ingredient.test(itemStack);
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

    public PartialNBTIngredient getIngredient() {
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
//            Ingredient ingredient = Ingredient.fromJson(json.get("ingredient"));
            PartialNBTIngredient ingredient = PartialNBTIngredient.Serializer.INSTANCE.parse(json.get("ingredient").getAsJsonObject());
            ItemStack result = ShapedRecipe.itemStackFromJson(json.getAsJsonObject("result"));
            int processingTime = json.get("processingTime").getAsInt();

            return new DecomponentalizingRecipe(recipeId, ingredient, result, processingTime);
        }

        @Nullable
        @Override
        public DecomponentalizingRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            PartialNBTIngredient ingredient = PartialNBTIngredient.Serializer.INSTANCE.parse(buffer);
            ItemStack result = buffer.readItem();
            int processingTime = buffer.readInt();

            return new DecomponentalizingRecipe(recipeId, ingredient, result, processingTime);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, DecomponentalizingRecipe recipe) {
            PartialNBTIngredient.Serializer.INSTANCE.write(buffer, recipe.getIngredient());
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
