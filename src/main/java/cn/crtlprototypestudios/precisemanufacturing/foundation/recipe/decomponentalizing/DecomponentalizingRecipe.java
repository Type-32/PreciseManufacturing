package cn.crtlprototypestudios.precisemanufacturing.foundation.recipe.decomponentalizing;

import cn.crtlprototypestudios.precisemanufacturing.Main;
import cn.crtlprototypestudios.precisemanufacturing.util.Reference;
import cn.crtlprototypestudios.precisemanufacturing.util.annotations.ClientServerSide;
import com.google.gson.JsonObject;
import net.minecraft.core.RegistryAccess;
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
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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

    @ClientServerSide
    @Override
    public boolean matches(SimpleContainer inventory, Level level) {
//        if(level.isClientSide()){
//            return false;
//        }
        ItemStack itemStack = inventory.getItem(0);
        return ingredient.test(itemStack);
    }

    @Override
    public @NotNull ItemStack assemble(SimpleContainer simpleContainer, RegistryAccess registryAccess) {
        return result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public @NotNull ItemStack getResultItem(RegistryAccess registryAccess) {
        return result.copy();
    }

    public @Nullable ItemStack getResultItem(){
        return getResultItem(null);
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
        return Type.INSTANCE;
    }

    public PartialNBTIngredient getIngredient() {
        return ingredient;
    }

    public int getProcessingTime() {
        return processingTime;
    }

    public static class Type implements RecipeType<DecomponentalizingRecipe> {
        private Type() {
            super();
        }

        public static final Type INSTANCE = new Type();
        public static final String ID = "decomponentalizing";
    }

    public static class Serializer implements RecipeSerializer<DecomponentalizingRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(Reference.MOD_ID, Type.ID);

        @Override
        public @NotNull DecomponentalizingRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            PartialNBTIngredient ingredient = PartialNBTIngredient.Serializer.INSTANCE.parse(json.get("ingredient").getAsJsonObject());
//            Main.LOGGER.debug("Ingredient {}, {}", ingredient.toString(), Arrays.toString(ingredient.getItems()));
            ItemStack result = ShapedRecipe.itemStackFromJson(json.getAsJsonObject("result"));
            int processingTime = json.get("processingTime").getAsInt();

            return new DecomponentalizingRecipe(recipeId, ingredient, result, processingTime);
        }

        @Nullable
        @Override
        public DecomponentalizingRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            PartialNBTIngredient ingredient = PartialNBTIngredient.Serializer.INSTANCE.parse(buffer);
//            Main.LOGGER.debug("Ingredient {}, {}", ingredient.toString(), Arrays.toString(ingredient.getItems()));
            ItemStack result = buffer.readItem();
            int processingTime = buffer.readInt();

            return new DecomponentalizingRecipe(recipeId, ingredient, result, processingTime);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, DecomponentalizingRecipe recipe) {
            recipe.getIngredient().toNetwork(buffer);
            buffer.writeItemStack(Objects.requireNonNull(recipe.getResultItem()), false);
            buffer.writeInt(recipe.getProcessingTime());
        }
    }
}
