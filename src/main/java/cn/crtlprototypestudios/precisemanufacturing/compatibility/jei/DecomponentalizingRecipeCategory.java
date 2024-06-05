package cn.crtlprototypestudios.precisemanufacturing.compatibility.jei;

import cn.crtlprototypestudios.precisemanufacturing.foundation.ModBlocks;
import cn.crtlprototypestudios.precisemanufacturing.foundation.ModItems;
import cn.crtlprototypestudios.precisemanufacturing.foundation.recipe.decomponentalizing.DecomponentalizingRecipe;
import cn.crtlprototypestudios.precisemanufacturing.foundation.util.ResourceHelper;
import cn.crtlprototypestudios.precisemanufacturing.util.Reference;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Objects;

public class DecomponentalizingRecipeCategory implements IRecipeCategory<DecomponentalizingRecipe> {
    public static final RecipeType<DecomponentalizingRecipe> DECOMPONENTALIZING_RECIPE_RECIPE_TYPE = RecipeType.create(Reference.MOD_ID, DecomponentalizingRecipe.Type.ID, DecomponentalizingRecipe.class);
    public final static ResourceLocation UID = ResourceHelper.find("decomponentalizing");
    public final static ResourceLocation TEXTURE = ResourceHelper.find("textures/gui/decomponentalizer_recipe_jei_compat.png");

    private final IDrawableStatic background;
    private final IDrawable slot;
    private final IDrawable icon;

    public DecomponentalizingRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createBlankDrawable(176, 30);
        this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.DECOMPONENTALIZER.get()));
        this.slot = guiHelper.getSlotDrawable();
    }

    @Override
    public @NotNull RecipeType<DecomponentalizingRecipe> getRecipeType() {
        return DECOMPONENTALIZING_RECIPE_RECIPE_TYPE;
    }

    @Override
    public @NotNull Component getTitle() {
        return Component.translatable("recipe_category.prma.decomponentalizing");
    }

    @Override
    public @NotNull IDrawable getBackground() {
        return this.background;
    }

    @Override
    public @NotNull IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayoutBuilder builder, @Nonnull DecomponentalizingRecipe recipe, @Nonnull IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 6, 6).addIngredients(recipe.getIngredient()).setBackground(slot, -1, -1);
        builder.addSlot(RecipeIngredientRole.OUTPUT, 152, 6).addItemStack(Objects.requireNonNull(recipe.getResultItem())).setBackground(slot, -1, -1);
    }
}
