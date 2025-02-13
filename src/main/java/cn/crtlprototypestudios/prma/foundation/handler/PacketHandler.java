package cn.crtlprototypestudios.prma.foundation.handler;

import cn.crtlprototypestudios.prma.foundation.utility.ResourceHelper;
import cn.crtlprototypestudios.prma.lib.Reference;
import cn.crtlprototypestudios.prma.util.annotations.ClientSide;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class PacketHandler {
    private static int packetId = 1;
    private static int id(){
        return packetId++;
    }
    private static int handshakeId = 1;
    private static int handshakeId() { return handshakeId++; }

    private static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            ResourceHelper.find(Reference.Network.NETWORK_CHANNEL),
            () -> Reference.Network.NETWORK_CHANNEL_VERSION,
            Reference.Network.NETWORK_CHANNEL_VERSION::equals,
            Reference.Network.NETWORK_CHANNEL_VERSION::equals
    );

    private static final SimpleChannel HANDSHAKE = NetworkRegistry.newSimpleChannel(
            ResourceHelper.find(Reference.Network.HANDSHAKE_CHANNEL),
            () -> Reference.Network.HANDSHAKE_CHANNEL_VERSION,
            Reference.Network.HANDSHAKE_CHANNEL_VERSION::equals,
            Reference.Network.HANDSHAKE_CHANNEL_VERSION::equals
    );

    public static void register() {
        // NetworkDirection.PLAY_TO_SERVER
//        CHANNEL.messageBuilder(C2SSetDecomponentalizerCurrentRecipePacket.class, id())
//                .decoder(C2SSetDecomponentalizerCurrentRecipePacket::new)
//                .encoder(C2SSetDecomponentalizerCurrentRecipePacket::encode)
//                .consumerNetworkThread(C2SSetDecomponentalizerCurrentRecipePacket::handle)
//                .add();

//        AcknowledgeHandshake handshake = new AcknowledgeHandshake();
//        HANDSHAKE.messageBuilder(AcknowledgeHandshake.class, handshakeId())
//                .decoder(handshake::decode)
//                .encoder(handshake::encode)
//                .consumerNetworkThread(handshake::handle)
//                .add();
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
