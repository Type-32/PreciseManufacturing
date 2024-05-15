package cn.crtlprototypestudios.precisemanufacturing.foundation.client.gui.decomponentalizer;

import cn.crtlprototypestudios.precisemanufacturing.foundation.client.gui.widgets.RecipeListWidget;
import cn.crtlprototypestudios.precisemanufacturing.foundation.recipe.decomponentalizing.DecomponentalizingRecipe;
import cn.crtlprototypestudios.precisemanufacturing.foundation.util.ResourceHelper;
import cn.crtlprototypestudios.precisemanufacturing.util.Reference;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

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
        inventoryLabelY = 132;
        inventoryLabelX = 8;
        titleLabelY = 160;
        titleLabelX = 33;
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        this.blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight);

        TranslatableComponent terminalText = new TranslatableComponent("gui.prma.decomponentalizer.idle");
        RenderSystem.setShaderTexture(1, WIDGET_TEXTURE);
        if(menu.isCrafting()) {
            blit(pPoseStack, x + 76, y + 27, 0, 140, menu.getScaledProgress(), 9);
            terminalText = new TranslatableComponent("gui.prma.decomponentalizer.processing", getProcessPercentage(), "%");
        } else {
            List<DecomponentalizingRecipe> recipes = this.getMenu().getBlockEntity().getAvailableRecipes();
            if (recipes != null && !recipes.isEmpty() && this.recipesPanel != null && this.recipesPanel.getSelectedIndex() != -1) {
                terminalText = new TranslatableComponent("gui.prma.decomponentalizer.time_estimate", recipes.get(this.recipesPanel.getSelectedIndex()).getProcessingTime() / 20);
            }
        }

        Minecraft.getInstance().font.draw(pPoseStack, terminalText, x + 119, y + 54, 0x00FF00);
    }

    @Override
    public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(stack);
        super.render(stack, mouseX, mouseY, partialTicks);
        this.recipesPanel.render(stack, mouseX, mouseY, partialTicks);
        this.renderTooltip(stack, mouseX, mouseY);
    }

    @Override
    protected void containerTick() {
        super.containerTick();
        this.recipesPanel.setRecipes(this.getMenu().getBlockEntity().getAvailableRecipes());
        if(menu.isCrafting()) {
            craftButton.active = false;
            getMenu().lockInputSlots();
        } else {
            craftButton.active = true;
            getMenu().unlockInputSlots();
        }
    }

    @Override
    protected void init() {
        super.init();

        this.leftPos = (width - imageWidth) / 2;
        this.topPos = (height - imageHeight) / 2;

        this.craftButton = addRenderableWidget(
                new Button(leftPos + 117, topPos + 130, 102, 20, new TranslatableComponent("gui.prma.decomponentalizer.analyze"), this::onAnalyzeButtonPressed)
        );

        this.recipesPanel = addRenderableWidget(
                new RecipeListWidget(this.getMenu(), leftPos + 10, topPos + 50, 102, 100, 20)
        );
    }

    private void onAnalyzeButtonPressed(Button button) {
        getMenu().startRecipeProcess();
    }

    private int getProcessPercentage(){
        return (int)(((double) getMenu().getProcessingTime() / getMenu().getTotalProcessingTime()) * 100);
    }
}
