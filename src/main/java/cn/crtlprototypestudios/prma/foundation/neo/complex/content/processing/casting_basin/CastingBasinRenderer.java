package cn.crtlprototypestudios.prma.foundation.neo.complex.content.processing.casting_basin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.content.redstone.displayLink.source.ItemThroughputDisplaySource;
import com.simibubi.create.foundation.blockEntity.renderer.SafeBlockEntityRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.item.ItemStack;

public class CastingBasinRenderer extends SafeBlockEntityRenderer<CastingBasinBlockEntity> {

    public CastingBasinRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    protected void renderSafe(CastingBasinBlockEntity be, float partialTicks, PoseStack ms, MultiBufferSource buffer,
                              int light, int overlay) {
        if (be.inventory == null)
            return;

        // Render input item
        ItemStack input = be.inventory.getStackInSlot(0);
        if (!input.isEmpty()) {
            ms.pushPose();
            ms.translate(0.5, 0.9375, 0.5);
            ms.scale(0.5f, 0.5f, 0.5f);
            Minecraft.getInstance().getItemRenderer().renderStatic(input, ItemTransforms.TransformType.GROUND, light, overlay, ms, buffer, 0);
            ms.popPose();
        }

        // Render output item
        ItemStack output = be.inventory.getStackInSlot(1);
        if (!output.isEmpty()) {
            ms.pushPose();
            ms.translate(0.5, 0.9375, 0.5);
            ms.scale(0.5f, 0.5f, 0.5f);
            Minecraft.getInstance().getItemRenderer().renderStatic(input, ItemTransforms.TransformType.GROUND, light, overlay, ms, buffer, 0);
            ms.popPose();
        }
    }
}
