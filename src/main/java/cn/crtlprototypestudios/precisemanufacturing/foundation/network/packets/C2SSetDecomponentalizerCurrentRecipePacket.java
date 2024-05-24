package cn.crtlprototypestudios.precisemanufacturing.foundation.network.packets;

import cn.crtlprototypestudios.precisemanufacturing.foundation.ModBlockEntities;
import cn.crtlprototypestudios.precisemanufacturing.foundation.block.decomponentalizer.DecomponentalizerBlockEntity;
import cn.crtlprototypestudios.precisemanufacturing.foundation.recipe.decomponentalizing.DecomponentalizingRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class C2SSetDecomponentalizerCurrentRecipePacket {
    private final BlockPos position;
    private final byte recipeIndex;

    public C2SSetDecomponentalizerCurrentRecipePacket(BlockPos position, byte recipeIndex) {
        this.position = position;
        this.recipeIndex = recipeIndex;
    }

    public C2SSetDecomponentalizerCurrentRecipePacket(FriendlyByteBuf buf) {
        this(buf.readBlockPos(), buf.readByte());
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeBlockPos(position);
        buf.writeByte(recipeIndex);
    }

    public static void handle(C2SSetDecomponentalizerCurrentRecipePacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
//            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> C2SSetDecomponentalizerCurrentRecipePacket.handleOnClient(msg, ctx));
//            DistExecutor.unsafeRunWhenOn(Dist.DEDICATED_SERVER, () -> () -> C2SSetDecomponentalizerCurrentRecipePacket.handleOnServer(msg, ctx));
            handleOnServer(msg, ctx);
        });

        ctx.get().setPacketHandled(true);
    }

    public static void handleOnClient(C2SSetDecomponentalizerCurrentRecipePacket msg, Supplier<NetworkEvent.Context> ctx){

    }

    public static void handleOnServer(C2SSetDecomponentalizerCurrentRecipePacket msg, Supplier<NetworkEvent.Context> ctx){
        ServerPlayer player = ctx.get().getSender();
        assert player != null;
        ServerLevel world = player.getLevel();
        assert world.hasChunkAt(msg.position);
        world.getBlockEntity(msg.position, ModBlockEntities.DECOMPONENTALIZER.get()).get().setCurrentRecipeIndex(msg.recipeIndex, true);
    }


}
