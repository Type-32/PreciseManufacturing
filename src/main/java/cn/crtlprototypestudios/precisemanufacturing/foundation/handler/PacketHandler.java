package cn.crtlprototypestudios.precisemanufacturing.foundation.handler;

import cn.crtlprototypestudios.precisemanufacturing.foundation.network.packets.C2SSetDecomponentalizerCurrentRecipePacket;
import cn.crtlprototypestudios.precisemanufacturing.foundation.util.ResourceHelper;
import cn.crtlprototypestudios.precisemanufacturing.util.Reference;
import cn.crtlprototypestudios.precisemanufacturing.util.annotations.ClientSide;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class PacketHandler {
    private static int packetId = 0;
    private static int id(){
        return packetId++;
    }

    private static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            ResourceHelper.find(Reference.Network.NETWORK_CHANNEL),
            () -> Reference.Network.NETWORK_CHANNEL_VERSION,
            Reference.Network.NETWORK_CHANNEL_VERSION::equals,
            Reference.Network.NETWORK_CHANNEL_VERSION::equals
    );

    public static void register() {
        // NetworkDirection.PLAY_TO_SERVER
        CHANNEL.registerMessage(
                id(),
                C2SSetDecomponentalizerCurrentRecipePacket.class,
                C2SSetDecomponentalizerCurrentRecipePacket::encode,
                C2SSetDecomponentalizerCurrentRecipePacket::new,
                C2SSetDecomponentalizerCurrentRecipePacket::handle
        );
    }

    @ClientSide
    public static <MSG> void sendToServer(final MSG message) {
        CHANNEL.sendToServer(message);
    }

    @ClientSide
    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

    @ClientSide
    public static <MSG> void sendToAllAround(final MSG message) {
        CHANNEL.send(PacketDistributor.ALL.noArg(), message);
    }
}
