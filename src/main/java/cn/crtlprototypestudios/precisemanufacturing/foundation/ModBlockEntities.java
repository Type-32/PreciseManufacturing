package cn.crtlprototypestudios.precisemanufacturing.foundation;

import cn.crtlprototypestudios.precisemanufacturing.Main;
import cn.crtlprototypestudios.precisemanufacturing.foundation.block.decomponentalizer.DecomponentalizerBlockEntity;
import com.tterrag.registrate.util.entry.BlockEntityEntry;

public class ModBlockEntities {
    public static final BlockEntityEntry<DecomponentalizerBlockEntity> DECOMPONENTALIZER = Main.REGISTRATE
            .blockEntity("decomponentalizer", DecomponentalizerBlockEntity::new)
            .validBlocks(ModBlocks.DECOMPONENTALIZER)
            .register();
}
