package cn.crtlprototypestudios.precisemanufacturing.foundation.gui.decomponentalizer;

import cn.crtlprototypestudios.precisemanufacturing.foundation.gui.widgets.RecipeListWidget;
import cn.crtlprototypestudios.precisemanufacturing.foundation.recipe.decomponentalizing.DecomponentalizingRecipe;
import cn.crtlprototypestudios.precisemanufacturing.foundation.util.ResourceHelper;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DecomponentalizerScreen extends AbstractContainerScreen<DecomponentalizerContainerMenu> {
    private static final ResourceLocation TEXTURE = ResourceHelper.find("textures/gui/decomponentalizer_gui.png"),
            WIDGET_TEXTURE = ResourceHelper.find("textures/gui/decomponentalizer_gui_widgets.png");
    private int leftPos, topPos;
    private Button craftButton;
    private RecipeListWidget recipesPanel;

    public DecomponentalizerScreen(DecomponentalizerContainerMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        imageHeight = 254;
        imageWidth = 228;
        inventoryLabelY = 160;
        inventoryLabelX = 33;
        titleLabelY = 5;
        titleLabelX = 8;
    }

    @Override
    protected void renderBg(PoseStack guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        this.blit(guiGraphics, x, y, 0, 0, imageWidth, imageHeight);
        MutableComponent terminalText = Component.translatable("gui.prma.decomponentalizer.idle");
//        RenderSystem.setShaderTexture(0, WIDGET_TEXTURE);
        if(menu.isCrafting()) {
            this.blit(guiGraphics, x + 75, y + 21, 0, 140, menu.getScaledProgress(), 20);
            terminalText = Component.translatable("gui.prma.decomponentalizer.processing", getProcessPercentage(), "%");
        } else {
            List<DecomponentalizingRecipe>
                    recipes = this.getMenu().getBlockEntity().getAvailableRecipes();

            if (recipes != null && !recipes.isEmpty() && this.recipesPanel != null && this.recipesPanel.getSelectedIndex() != -1) {
                terminalText = Component.translatable("gui.prma.decomponentalizer.time_estimate", recipes.get(this.recipesPanel.getSelectedIndex()).getProcessingTime() / 20);
            }
        }

        drawString(guiGraphics, Minecraft.getInstance().font, terminalText, x + 120, y + 54, 0x00FF00);
    }



    @Override
    public void render(@NotNull PoseStack guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        this.recipesPanel.render(guiGraphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void containerTick() {
        super.containerTick();
        this.recipesPanel.setRecipes(this.getMenu().getBlockEntity().getAvailableRecipes());
//        Main.LOGGER.info("Is Recipes not null from Screen? {}, {}", recipes != null, !recipes.isEmpty());
        craftButton.active = !menu.isCrafting();
    }

    @Override
    protected void init() {
        super.init();

        this.leftPos = (width - imageWidth) / 2;
        this.topPos = (height - imageHeight) / 2;

        this.craftButton = addRenderableWidget(
                new Button(
                        leftPos + 116, topPos + 130, 102, 20,
                        Component.translatable("gui.prma.decomponentalizer.analyze"),
                        this::onAnalyzeButtonPressed
                )
        );

        this.recipesPanel = addRenderableWidget(
                new RecipeListWidget(this.getMenu(), leftPos + 9, topPos + 50, 102, 100, 20)
        );
    }

    private void onAnalyzeButtonPressed(Button button) {
        getMenu().startRecipeProcess(this.recipesPanel.getSelectedIndex());
        // many thanks to @xjqsh for helping me fix this
    }

    private int getProcessPercentage(){
        return (int)(((double) getMenu().getProcessingTime() / getMenu().getTotalProcessingTime()) * 100);
    }
}
