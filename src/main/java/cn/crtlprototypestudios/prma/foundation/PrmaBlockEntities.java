package cn.crtlprototypestudios.prma.foundation;

import cn.crtlprototypestudios.prma.PreciseManufacturing;
import cn.crtlprototypestudios.prma.foundation.neo.complex.content.processing.casting_basin.CastingBasinBlockEntity;
import cn.crtlprototypestudios.prma.foundation.neo.complex.content.processing.casting_basin.CastingBasinRenderer;
import cn.crtlprototypestudios.prma.lib.Reference;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PrmaBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Reference.MOD_ID);

//    public static final RegistryObject<BlockEntityType<DecomponentalizerBlockEntity>> DECOMPONENTALIZER =
//            BLOCK_ENTITIES.register("decomponentalizer_block_entity", () ->
//                    BlockEntityType.Builder.of(DecomponentalizerBlockEntity::new,
//                            ModBlocks.DECOMPONENTALIZER.get()).build(null));


//    public static final BlockEntityEntry<? extends BlockEntity> DECOMPONENTALIZER =
//            Main.REGISTRATE.blockEntity("decomponentalizer", DecomponentalizerBlockEntity::new).register();

    public static final BlockEntityEntry<CastingBasinBlockEntity> CASTING_BASIN = PreciseManufacturing.REGISTRATE
            .blockEntity("casting_basin", CastingBasinBlockEntity::new)
            .validBlocks()
            .renderer(() -> CastingBasinRenderer::new)
            .register();

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
