package cn.crtlprototypestudios.precisemanufacturing.foundation.network.packets;

import cn.crtlprototypestudios.precisemanufacturing.foundation.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class C2SSetDecomponentalizerSelectedRecipePacket {
    private final BlockPos position;
    private final byte recipeIndex;

    public C2SSetDecomponentalizerSelectedRecipePacket(BlockPos position, byte recipeIndex) {
        this.position = position;
        this.recipeIndex = recipeIndex;
    }

    public C2SSetDecomponentalizerSelectedRecipePacket(FriendlyByteBuf buf) {
        this(buf.readBlockPos(), buf.readByte());
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeBlockPos(position);
        buf.writeByte(recipeIndex);
    }

    public static void handle(C2SSetDecomponentalizerSelectedRecipePacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
//            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> C2SSetDecomponentalizerCurrentRecipePacket.handleOnClient(msg, ctx));
//            DistExecutor.unsafeRunWhenOn(Dist.DEDICATED_SERVER, () -> () -> C2SSetDecomponentalizerCurrentRecipePacket.handleOnServer(msg, ctx));
            handleOnServer(msg, ctx);
        });

        ctx.get().setPacketHandled(true);
    }

    public static void handleOnClient(C2SSetDecomponentalizerSelectedRecipePacket msg, Supplier<NetworkEvent.Context> ctx){

    }

    public static void handleOnServer(C2SSetDecomponentalizerSelectedRecipePacket msg, Supplier<NetworkEvent.Context> ctx){
        ServerPlayer player = ctx.get().getSender();
        assert player != null;
        ServerLevel world = player.serverLevel();
        assert world.hasChunkAt(msg.position);
        world.getBlockEntity(msg.position, ModBlockEntities.DECOMPONENTALIZER.get()).get().setSelectedRecipeIndex(msg.recipeIndex);
    }
}
