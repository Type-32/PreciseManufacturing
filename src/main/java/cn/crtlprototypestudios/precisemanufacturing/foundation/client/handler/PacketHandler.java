package cn.crtlprototypestudios.precisemanufacturing.foundation.client.handler;

import cn.crtlprototypestudios.precisemanufacturing.foundation.network.packets.C2SSetDecomponentalizerCurrentRecipePacket;
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
    private static final String VERSION = "1.0.0";

    private static final SimpleChannel CHANNEL = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation(Reference.MOD_ID, Reference.Network.NETWORK_RL))
            .serverAcceptedVersions((status) -> true)
            .clientAcceptedVersions((status) -> true)
            .networkProtocolVersion(() -> Reference.Network.NETWORK_CHANNEL_VERSION)
            .simpleChannel();

    public static void register() {
        // NetworkDirection.PLAY_TO_SERVER
        CHANNEL.messageBuilder(C2SSetDecomponentalizerCurrentRecipePacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .encoder(C2SSetDecomponentalizerCurrentRecipePacket::encode)
                .decoder(C2SSetDecomponentalizerCurrentRecipePacket::new)
                .consumerNetworkThread(C2SSetDecomponentalizerCurrentRecipePacket::handle)
                .add();
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
