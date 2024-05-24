package cn.crtlprototypestudios.precisemanufacturing.foundation;

import cn.crtlprototypestudios.precisemanufacturing.Main;
import cn.crtlprototypestudios.precisemanufacturing.foundation.block.decomponentalizer.DecomponentalizerBlock;
import com.simibubi.create.content.kinetics.BlockStressDefaults;
import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.SharedProperties;
import com.simibubi.create.foundation.data.TagGen;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.material.MaterialColor;

import static com.simibubi.create.foundation.data.ModelGen.customItemModel;


public class ModBlocks {
    public static final BlockEntry<DecomponentalizerBlock> DECOMPONENTALIZER = Main.REGISTRATE.block("decomponentalizer", DecomponentalizerBlock::new)
            .initialProperties(SharedProperties::softMetal)
            .properties(p -> p.color(MaterialColor.TERRACOTTA_GRAY))
            .transform(TagGen.pickaxeOnly())
            .item()
            .transform(customItemModel())
            .register();

    public static void register(){

    }
}
