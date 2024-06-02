package cn.crtlprototypestudios.precisemanufacturing.foundation.data.builders.recipe;

import cn.crtlprototypestudios.precisemanufacturing.foundation.recipe.decomponentalizing.DecomponentalizingRecipe;
import cn.crtlprototypestudios.precisemanufacturing.foundation.util.ResourceHelper;
import com.google.gson.JsonObject;
import com.simibubi.create.foundation.utility.RegisteredObjects;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.Registry;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.PartialNBTIngredient;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.Consumer;

public class DecomponentalizingRecipeBuilder implements RecipeBuilder {
    private final Item result;
    private final Ingredient ingredient;
    private final int processingTime;
    private final Advancement.Builder advancement = Advancement.Builder.advancement();

    public DecomponentalizingRecipeBuilder(ItemStack ingredient, ItemLike result, int processingTime) {
        this.ingredient = PartialNBTIngredient.of(ingredient.getTag(), ingredient.getItem());
        this.result = result.asItem();
        this.processingTime = processingTime;
    }

    public DecomponentalizingRecipeBuilder(TagKey<Item> tag, ItemLike result, int processingTime) {
        this.ingredient = Ingredient.of(tag);
        this.result = result.asItem();
        this.processingTime = processingTime;
    }

    @Override
    public RecipeBuilder unlockedBy(String pCriterionName, CriterionTriggerInstance pCriterionTrigger) {
        this.advancement.addCriterion(pCriterionName, pCriterionTrigger);
        return this;
    }

    @Override
    public RecipeBuilder group(@Nullable String pGroupName) {
        return this;
    }

    @Override
    public Item getResult() {
        return result;
    }

    @Override
    public void save(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ResourceLocation pRecipeId) {
        this.advancement.parent(new ResourceLocation("recipes/root"))
                .addCriterion("has_the_recipe",
                        RecipeUnlockedTrigger.unlocked(pRecipeId))
                .rewards(AdvancementRewards.Builder.recipe(pRecipeId)).requirements(RequirementsStrategy.OR);

        pFinishedRecipeConsumer.accept(new DecomponentalizingRecipeBuilder.Result(pRecipeId, this.ingredient, this.result, this.processingTime,
                this.advancement, new ResourceLocation(pRecipeId.getNamespace(), "recipes/" +
                "decomponentalizing" + "/" + pRecipeId.getPath())));
    }

    @Override
    public void save(Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        this.save(pFinishedRecipeConsumer, ResourceHelper.find("decomponentalizing"));
    }

    public static class Result implements FinishedRecipe {
        private final ResourceLocation id;
        private final Ingredient ingredient;
        private final Item result;
        private final int processingTime;
        private final Advancement.Builder advancement;
        private final ResourceLocation advancementId;

        public Result(ResourceLocation pId, Ingredient pIngredient, Item pResult, int pProcessingTime, Advancement.Builder pAdvancement, ResourceLocation pAdvancementId) {
            this.id = pId;
            this.ingredient = pIngredient;
            this.result = pResult;
            this.processingTime = pProcessingTime;
            this.advancement = pAdvancement;
            this.advancementId = pAdvancementId;
        }

        public void serializeRecipeData(JsonObject pJson) {
            pJson.add("ingredient", this.ingredient.toJson());
            pJson.addProperty("result", RegisteredObjects.getKeyOrThrow(this.result).toString());
            pJson.addProperty("processingTime", this.processingTime);

            JsonObject jsonObj = new JsonObject();
            jsonObj.addProperty("item", this.result.toString());
            pJson.add("result", jsonObj);
        }

        /**
         * Gets the ID for the recipe.
         */
        public ResourceLocation getId() {
            return ResourceHelper.find(this.result.toString() + "_from_decomponentalizing");
        }

        public RecipeSerializer<?> getType() {
            return DecomponentalizingRecipe.Serializer.INSTANCE;
        }

        /**
         * Gets the JSON for the advancement that unlocks this recipe. Null if there is no advancement.
         */
        @javax.annotation.Nullable
        public JsonObject serializeAdvancement() {
            return this.advancement.serializeToJson();
        }

        /**
         * Gets the ID for the advancement associated with this recipe. Should not be null if {Something here}
         * is non-null.
         */
        @javax.annotation.Nullable
        public ResourceLocation getAdvancementId() {
            return this.advancementId;
        }
    }
}
