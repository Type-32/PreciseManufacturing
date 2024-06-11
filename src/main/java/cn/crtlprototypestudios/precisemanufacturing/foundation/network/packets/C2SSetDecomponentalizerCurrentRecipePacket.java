package cn.crtlprototypestudios.precisemanufacturing.foundation.network.packets;

import cn.crtlprototypestudios.precisemanufacturing.Main;
import cn.crtlprototypestudios.precisemanufacturing.foundation.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.Objects;
import java.util.function.Supplier;

public class C2SSetDecomponentalizerCurrentRecipePacket {
    private final BlockPos position;
    private final short recipeIndex;
    private final ItemStack stack;

    public C2SSetDecomponentalizerCurrentRecipePacket(BlockPos position, ItemStack stack, short recipeIndex) {
        this.position = position;
        this.recipeIndex = recipeIndex;
        this.stack = stack;
    }

    public C2SSetDecomponentalizerCurrentRecipePacket(FriendlyByteBuf buf) {
        this(buf.readBlockPos(), buf.readItem(), buf.readByte());
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeBlockPos(position);
        buf.writeItem(stack);
        buf.writeByte(recipeIndex);
    }

    public static void handle(C2SSetDecomponentalizerCurrentRecipePacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            handleOnServer(msg, ctx);
        });

        ctx.get().setPacketHandled(true);
    }

    public static void handleOnServer(C2SSetDecomponentalizerCurrentRecipePacket msg, Supplier<NetworkEvent.Context> ctx){
        ServerPlayer player = ctx.get().getSender();
        assert player != null;
        ServerLevel world = player.getLevel();
        assert world.hasChunkAt(msg.position);
        try {
            Objects.requireNonNull(world.getBlockEntity(msg.position, ModBlockEntities.DECOMPONENTALIZER.get()).orElse(null)).startDecomponentalizationProcess(msg.stack, msg.recipeIndex);
        } catch(Exception e) {
            Main.LOGGER.error("Error occurred receiving packet: ", e);
        }
    }


}
