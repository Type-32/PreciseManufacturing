package cn.crtlprototypestudios.precisemanufacturing.foundation.handler;

import cn.crtlprototypestudios.precisemanufacturing.foundation.network.packets.AcknowledgeHandshake;
import cn.crtlprototypestudios.precisemanufacturing.foundation.network.packets.C2SSetDecomponentalizerCurrentRecipePacket;
import cn.crtlprototypestudios.precisemanufacturing.foundation.util.ResourceHelper;
import cn.crtlprototypestudios.precisemanufacturing.util.Reference;
import cn.crtlprototypestudios.precisemanufacturing.util.annotations.ClientSide;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.HandshakeHandler;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

import javax.swing.text.html.Option;
import java.util.Optional;

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
        CHANNEL.registerMessage(
                id(),
                C2SSetDecomponentalizerCurrentRecipePacket.class,
                C2SSetDecomponentalizerCurrentRecipePacket::encode,
                C2SSetDecomponentalizerCurrentRecipePacket::new,
                C2SSetDecomponentalizerCurrentRecipePacket::handle,
                Optional.of(NetworkDirection.PLAY_TO_SERVER)
        );

        AcknowledgeHandshake handshake = new AcknowledgeHandshake();
        HANDSHAKE.messageBuilder(AcknowledgeHandshake.class, handshakeId())
                .decoder(handshake::decode)
                .encoder(handshake::encode)
                .consumerNetworkThread(handshake::handle)
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
