package cn.crtlprototypestudios.precisemanufacturing.foundation.gui.widgets;

import cn.crtlprototypestudios.precisemanufacturing.foundation.block.decomponentalizer.DecomponentalizerBlockEntity;
import cn.crtlprototypestudios.precisemanufacturing.foundation.gui.decomponentalizer.DecomponentalizerContainerMenu;
import cn.crtlprototypestudios.precisemanufacturing.foundation.recipe.decomponentalizing.DecomponentalizingRecipe;
import cn.crtlprototypestudios.precisemanufacturing.util.Reference;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.ComponentRenderUtils;
import net.minecraft.client.gui.components.Widget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class RecipeListWidget extends AbstractWidget implements Widget {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(Reference.MOD_ID, "textures/gui/decomponentalizer_gui.png");
    private final DecomponentalizerBlockEntity blockEntity;
    private final DecomponentalizerContainerMenu containerMenu;
    private List<DecomponentalizingRecipe> recipes;
    private final int listWidth;
    private final int listHeight;
    private final int entryHeight;
    private int scrollOffset;
    private int selectedIndex;

    public RecipeListWidget(DecomponentalizerContainerMenu containerMenu, int x, int y, int width, int height, int entryHeight) {
        super(x, y, width, height, TextComponent.EMPTY);
        this.blockEntity = containerMenu.getBlockEntity();
        this.containerMenu = containerMenu;
        this.listWidth = width;
        this.listHeight = height;
        this.entryHeight = entryHeight;
        this.scrollOffset = 0;
        this.selectedIndex = -1;
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);

        // Render the list background
        blit(poseStack, x, y, 176, 0, listWidth, listHeight);

        // Render the recipe entries
        if(this.recipes != null && !recipes.isEmpty()) {
            int y = this.y + 1 - scrollOffset;
            for (int i = 0; i < recipes.size(); i++) {
                if (y >= this.y && y + entryHeight <= this.y + listHeight) {
                    DecomponentalizingRecipe recipe = recipes.get(i);

                    if(containerMenu.getBlockEntity().getCurrentRecipe() != null && containerMenu.getBlockEntity().getCurrentRecipe().equals(recipe))
                        selectedIndex = i;

                    renderRecipeEntry(poseStack, recipe, x, y, listWidth, entryHeight, mouseX, mouseY, i == selectedIndex);
                }
                y += entryHeight + 1;
            }
        }
    }

    private void renderRecipeEntry(PoseStack poseStack, DecomponentalizingRecipe recipe, int x, int y, int width, int height, int mouseX, int mouseY, boolean selected) {
        // Render the recipe entry background
        RenderSystem.setShaderTexture(0, TEXTURE);
        blit(poseStack, x, y, 176, selected ? 92 : 72, width, height);

        // Render the recipe item
        ItemStack resultStack = recipe.getResultItem();
        Minecraft.getInstance().getItemRenderer().renderGuiItem(resultStack, x + 2, y + 2);

        // Render the recipe duration
        String itemNameText = resultStack.getDisplayName().getString();
        Minecraft.getInstance().font.draw(poseStack, itemNameText, x + 22, y + 6, 0xFFFFFF);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (isMouseOver(mouseX, mouseY)) {
            int index = (int) ((mouseY - y - 4 + scrollOffset) / entryHeight);
            if (recipes != null && index >= 0 && index < recipes.size()) {
                selectedIndex = index;
                containerMenu.setSelectedRecipe(recipes.get(index));
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

    private int getMaxScroll() {
        return Math.max(0, (recipes.size() * entryHeight) - listHeight + 8);
    }

    public void updateRecipes(List<DecomponentalizingRecipe> newRecipes) {
        this.recipes.clear();
        this.recipes.addAll(newRecipes);
        this.selectedIndex = -1;
        this.scrollOffset = 0;
    }

    @Override
    public void updateNarration(NarrationElementOutput pNarrationElementOutput) {

    }

    public int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(value, max));
    }

    public void setRecipes(List<DecomponentalizingRecipe> recipes) {
        this.recipes = recipes;
    }
}
