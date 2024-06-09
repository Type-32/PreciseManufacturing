package cn.crtlprototypestudios.precisemanufacturing.foundation;

import cn.crtlprototypestudios.precisemanufacturing.Main;
import cn.crtlprototypestudios.precisemanufacturing.foundation.block.decomponentalizer.DecomponentalizerBlockEntity;
import cn.crtlprototypestudios.precisemanufacturing.util.Reference;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Reference.MOD_ID);

    public static final RegistryObject<BlockEntityType<DecomponentalizerBlockEntity>> DECOMPONENTALIZER =
            BLOCK_ENTITIES.register("decomponentalizer_block_entity", () ->
                    BlockEntityType.Builder.of(DecomponentalizerBlockEntity::new,
                            ModBlocks.DECOMPONENTALIZER.get()).build(null));


//    public static final BlockEntityEntry<? extends BlockEntity> DECOMPONENTALIZER =
//            Main.REGISTRATE.blockEntity("decomponentalizer", DecomponentalizerBlockEntity::new).register();

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
