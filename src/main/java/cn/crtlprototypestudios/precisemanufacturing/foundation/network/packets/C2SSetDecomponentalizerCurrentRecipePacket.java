package cn.crtlprototypestudios.precisemanufacturing.foundation.network.packets;

import cn.crtlprototypestudios.precisemanufacturing.Main;
import cn.crtlprototypestudios.precisemanufacturing.foundation.ModBlockEntities;
import cn.crtlprototypestudios.precisemanufacturing.foundation.block.decomponentalizer.DecomponentalizerBlockEntity;
import cn.crtlprototypestudios.precisemanufacturing.foundation.recipe.decomponentalizing.DecomponentalizingRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

public class C2SSetDecomponentalizerCurrentRecipePacket {
    private final BlockPos position;
    private final short recipeIndex;
    private final ItemStack decompositionStack;

    public C2SSetDecomponentalizerCurrentRecipePacket(BlockPos position, ItemStack itemStack, short recipeIndex) {
        this.position = position;
        this.recipeIndex = recipeIndex;
        this.decompositionStack = itemStack;
    }

    public C2SSetDecomponentalizerCurrentRecipePacket(FriendlyByteBuf buf) {
        this(buf.readBlockPos(), buf.readItem(), buf.readShort());
        Main.LOGGER.debug("C2SSetDecomponentalizerCurrentRecipePacket received, receiving Byte Buffer instead of manual creation");
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeBlockPos(position);
        buf.writeItem(decompositionStack);
        buf.writeShort(recipeIndex);
    }

    public static void handle(C2SSetDecomponentalizerCurrentRecipePacket msg, Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();

        if(context.getDirection().getReceptionSide().isServer()) {
            handleOnServer(msg, ctx);
        }

        ctx.get().setPacketHandled(true);
    }

    public static void handleOnServer(C2SSetDecomponentalizerCurrentRecipePacket msg, Supplier<NetworkEvent.Context> ctx){
        ServerPlayer player = ctx.get().getSender();
        Main.LOGGER.debug("Server Handle Packet: Player exists? {}", player != null);
        assert player != null;
        ServerLevel world = player.serverLevel();
        Main.LOGGER.debug("Server Handle Packet: Has chunk at world {}? {}", msg.position.toString(), world.hasChunkAt(msg.position));
        assert world.hasChunkAt(msg.position);

        List<DecomponentalizingRecipe> availableRecipes = new ArrayList<>();

        Main.LOGGER.debug("Server Handle Packet: Item Stack from packet: {}", msg.decompositionStack.toString());
        try {
            availableRecipes = world.getRecipeManager().getRecipesFor(DecomponentalizingRecipe.Type.INSTANCE, new SimpleContainer(msg.decompositionStack), world);
            Main.LOGGER.debug("Server Handle Packet: Fetched recipes: {}", availableRecipes.size());
            world.getBlockEntity(msg.position, ModBlockEntities.DECOMPONENTALIZER.get()).get().startDecomponentalizationProcess(availableRecipes, msg.recipeIndex);
        } catch (Exception e) {
            Main.LOGGER.error("Server Handle Packet: Decomponentalization packet failed to set", e);
        }
    }
}
