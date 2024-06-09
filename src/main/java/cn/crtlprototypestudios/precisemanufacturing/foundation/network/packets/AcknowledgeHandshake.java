package cn.crtlprototypestudios.precisemanufacturing.foundation.network.packets;

import cn.crtlprototypestudios.precisemanufacturing.Main;
import com.tacz.guns.network.IMessage;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class AcknowledgeHandshake implements IMessage<AcknowledgeHandshake> {
    @Override
    public void encode(AcknowledgeHandshake acknowledgeHandshake, FriendlyByteBuf friendlyByteBuf) {

    }

    @Override
    public AcknowledgeHandshake decode(FriendlyByteBuf friendlyByteBuf) {
        return new AcknowledgeHandshake();
    }

    @Override
    public void handle(AcknowledgeHandshake acknowledgeHandshake, Supplier<NetworkEvent.Context> supplier) {
        Main.LOGGER.debug("Received acknowledge handshake");
        supplier.get().setPacketHandled(true);
    }
}
