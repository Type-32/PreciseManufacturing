package cn.crtlprototypestudios.precisemanufacturing.foundation.client.gui.widgets;

import cn.crtlprototypestudios.precisemanufacturing.Main;
import cn.crtlprototypestudios.precisemanufacturing.foundation.block.decomponentalizer.DecomponentalizerBlockEntity;
import cn.crtlprototypestudios.precisemanufacturing.foundation.client.gui.decomponentalizer.DecomponentalizerContainerMenu;
import cn.crtlprototypestudios.precisemanufacturing.foundation.recipe.decomponentalizing.DecomponentalizingRecipe;
import cn.crtlprototypestudios.precisemanufacturing.foundation.util.ResourceHelper;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.Arrays;
import java.util.List;

public class RecipeListWidget extends AbstractWidget {
    private static final ResourceLocation TEXTURE = ResourceHelper.find("textures/gui/decomponentalizer_gui_widgets.png");
    private final DecomponentalizerBlockEntity blockEntity;
    private final DecomponentalizerContainerMenu containerMenu;
    private List<DecomponentalizingRecipe> recipes;
    private final int listWidth;
    private final int listHeight;
    private final int entryHeight;
    private int scrollOffset;
    private int selectedIndex;

    public RecipeListWidget(DecomponentalizerContainerMenu containerMenu, int x, int y, int width, int height, int entryHeight) {
        super(x, y, width, height, Component.empty());
        this.blockEntity = containerMenu.getBlockEntity();
        this.containerMenu = containerMenu;
        this.listWidth = width;
        this.listHeight = height;
        this.entryHeight = entryHeight;
        this.scrollOffset = 0;
        this.selectedIndex = -1;
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);

        // Render the list background
        guiGraphics.blit(TEXTURE, getX(), getY(), 0, 0, listWidth, listHeight);

        // Apply the mask
//        RenderSystem.enableScissor((int) (x * Minecraft.getInstance().getWindow().getGuiScale()), (int) ((Minecraft.getInstance().getWindow().getHeight() - (y + listHeight)) * Minecraft.getInstance().getWindow().getGuiScale()), (int) (listWidth * Minecraft.getInstance().getWindow().getGuiScale()), (int) (listHeight * Minecraft.getInstance().getWindow().getGuiScale()));

        // Render the recipe entries
        if(this.recipes != null && !recipes.isEmpty()) {
            int y = getY() + 1 - scrollOffset;
            for (int i = 0; i < recipes.size(); i++) {
                if (y >= getY() && y + entryHeight <= getY() + listHeight) {
                    DecomponentalizingRecipe recipe = recipes.get(i);
                    Main.LOGGER.info("Recipe: {}, {}", recipe.toString(), Arrays.stream(recipe.getIngredient().getItems()).toArray()[0].toString());

                    if(containerMenu.getBlockEntity().getSelectedRecipeIndex() == i)
                        selectedIndex = i;

                    renderRecipeEntry(guiGraphics, recipe, getX() + 1, y, listWidth, entryHeight, i == selectedIndex, false);
                } else if (y >= getY() && y + entryHeight > getY() + listHeight && (y + entryHeight) - (getY() + listHeight) <= entryHeight) {
                    DecomponentalizingRecipe recipe = recipes.get(i);

                    if(containerMenu.getBlockEntity().getSelectedRecipeIndex() == i)
                        selectedIndex = i;
                    renderRecipeEntry(guiGraphics, recipe, getX() + 1, y, listWidth, entryHeight - ((y + entryHeight) - (getY() + listHeight)), i == selectedIndex, false);
                } else if (y < getY() && y + entryHeight > getY() && (y + entryHeight) - (getY()) <= entryHeight) {
                    DecomponentalizingRecipe recipe = recipes.get(i);

                    if(containerMenu.getBlockEntity().getSelectedRecipeIndex() == i)
                        selectedIndex = i;
                    renderRecipeEntry(guiGraphics, recipe, getX() + 1, y, listWidth, (getY() - y), i == selectedIndex, true);
                }
                y += entryHeight + 1;
            }
        }

        // Disable the mask
//        RenderSystem.disableScissor();
    }

    private void renderRecipeEntry(GuiGraphics guiGraphics, DecomponentalizingRecipe recipe, int x, int y, int width, int height, boolean selected, boolean upperOverflow) {
        // Render the recipe entry background
        RenderSystem.setShaderTexture(0, TEXTURE);
        guiGraphics.blit(TEXTURE, x , y, 0, upperOverflow ? (selected ? 120 + Math.abs(height) : 100 + Math.abs(height)) : (selected ? 120 : 100), width, upperOverflow ? (entryHeight - Math.abs(height)) : height);

        // Render the recipe item
        ItemStack resultStack = recipe.getResultItem();
        assert resultStack != null;
        guiGraphics.renderItem(resultStack, x + 2, y + 2);

        // Render the recipe duration
        MutableComponent textComponent = Component.literal(resultStack.getDisplayName()
                .getString()
                .substring(1, resultStack.getDisplayName().getString().length() - 1));
        String truncatedText = Minecraft.getInstance().font.plainSubstrByWidth(textComponent.getString(), width - 24);
        guiGraphics.drawString(Minecraft.getInstance().font, truncatedText, x + 22, y + 6, 0xFFFFFF);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (isMouseOver(mouseX, mouseY) && !containerMenu.isCrafting()) {
            int index = (int) ((mouseY - getY() - 4 + scrollOffset) / entryHeight);
            if (recipes != null && index >= 0 && index < recipes.size()) {
                selectedIndex = index;
//                containerMenu.getBlockEntity().setSelectedRecipeIndex(index);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (isMouseOver(mouseX, mouseY)) {
            scrollOffset = (int) (scrollOffset - dragY);
            scrollOffset = clamp(scrollOffset, 0, getMaxScroll());
            return true;
        }
        return false;
    }

    @Override
    public boolean mouseScrolled(double pMouseX, double pMouseY, double pDelta) {
        if (isMouseOver(pMouseX, pMouseY)) {
            scrollOffset = (int) (scrollOffset - pDelta);
            scrollOffset = clamp(scrollOffset, 0, getMaxScroll());
            return true;
        }
        return false;
    }

    private int getMaxScroll() {
        return Math.max(0, (recipes.size() * entryHeight) - listHeight + 8);
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {

    }

    public int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(value, max));
    }

    public void setRecipes(List<DecomponentalizingRecipe> recipes) {
        this.recipes = recipes;
    }

    public int getSelectedIndex(){
        return selectedIndex;
    }
}
