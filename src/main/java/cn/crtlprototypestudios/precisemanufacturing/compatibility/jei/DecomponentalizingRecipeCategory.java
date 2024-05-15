package cn.crtlprototypestudios.precisemanufacturing.compatibility.jei;

import cn.crtlprototypestudios.precisemanufacturing.foundation.ModBlocks;
import cn.crtlprototypestudios.precisemanufacturing.foundation.ModItems;
import cn.crtlprototypestudios.precisemanufacturing.foundation.recipe.decomponentalizing.DecomponentalizingRecipe;
import cn.crtlprototypestudios.precisemanufacturing.foundation.util.ResourceHelper;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public class DecomponentalizingRecipeCategory implements IRecipeCategory<DecomponentalizingRecipe> {
    public final static ResourceLocation UID = ResourceHelper.find("decomponentalizing");
    public final static ResourceLocation TEXTURE = ResourceHelper.find("textures/gui/decomponentalizer_gui.png");
    public final static ResourceLocation WIDGET_TEXTURE = ResourceHelper.find("textures/gui/decomponentalizer_gui_widgets.png");

    private final IDrawable background;
    private final IDrawable icon;

    public DecomponentalizingRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(TEXTURE, 0, 16, 228, 30);
        this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.DECOMPONENTALIZER.get()));
    }

    @Override
    public Component getTitle() {
        return new TranslatableComponent("recipe_category.prma.decomponentalizing");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @SuppressWarnings("removal")
    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @SuppressWarnings("removal")
    @Override
    public Class<? extends DecomponentalizingRecipe> getRecipeClass() {
        return DecomponentalizingRecipe.class;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, DecomponentalizingRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 54, 22).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 204, 22).addItemStack(recipe.getResultItem());
    }
}
