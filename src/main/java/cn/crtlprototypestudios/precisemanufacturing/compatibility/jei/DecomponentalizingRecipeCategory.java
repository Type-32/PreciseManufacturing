package cn.crtlprototypestudios.precisemanufacturing.compatibility.jei;

import cn.crtlprototypestudios.precisemanufacturing.foundation.ModBlocks;
import cn.crtlprototypestudios.precisemanufacturing.foundation.ModItems;
import cn.crtlprototypestudios.precisemanufacturing.foundation.recipe.decomponentalizing.DecomponentalizingRecipe;
import cn.crtlprototypestudios.precisemanufacturing.foundation.recipe.decomponentalizing.DecomponentalizingRecipeType;
import cn.crtlprototypestudios.precisemanufacturing.foundation.util.ResourceHelper;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import javax.annotation.Nonnull;
import java.util.Objects;

public class DecomponentalizingRecipeCategory implements IRecipeCategory<DecomponentalizingRecipe> {
    public final static ResourceLocation UID = ResourceHelper.find("decomponentalizing");
    public final static ResourceLocation TEXTURE = ResourceHelper.find("textures/gui/decomponentalizer_recipe_jei_compat.png");

    private final IDrawable background;
    private final IDrawable icon;

    public DecomponentalizingRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(TEXTURE, 0, 0, 176, 30);
        this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.DECOMPONENTALIZER.get()));
    }

    @Override
    public RecipeType<DecomponentalizingRecipe> getRecipeType() {
        return new RecipeType<>(ResourceHelper.find(DecomponentalizingRecipeType.ID), DecomponentalizingRecipe.class);
    }

    @Override
    public Component getTitle() {
        return Component.translatable("recipe_category.prma.decomponentalizing");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

//    @SuppressWarnings("removal")
//    @Override
//    public ResourceLocation getUid() {
//        return UID;
//    }
//
//    @SuppressWarnings("removal")
//    @Override
//    public Class<? extends DecomponentalizingRecipe> getRecipeClass() {
//        return DecomponentalizingRecipe.class;
//    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayoutBuilder builder, @Nonnull DecomponentalizingRecipe recipe, @Nonnull IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 6, 6).addIngredients(recipe.getIngredient());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 152, 6).addItemStack(Objects.requireNonNull(recipe.getResultItem()));
    }
}
