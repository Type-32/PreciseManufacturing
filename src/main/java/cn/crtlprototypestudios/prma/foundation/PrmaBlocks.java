package cn.crtlprototypestudios.prma.foundation;

import cn.crtlprototypestudios.prma.PreciseManufacturing;
import cn.crtlprototypestudios.prma.foundation.neo.complex.content.processing.casting_basin.CastingBasinBlock;
import com.simibubi.create.AllDisplaySources;
import com.simibubi.create.content.processing.AssemblyOperatorBlockItem;
import com.simibubi.create.content.redstone.displayLink.source.ItemNameDisplaySource;
import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.SharedProperties;
import com.simibubi.create.foundation.data.TagGen;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.world.level.material.MapColor;

import static com.simibubi.create.foundation.data.ModelGen.customItemModel;


public class PrmaBlocks {

    static {
        // Set that all registered Items and Blocks defaults under this Mod's creative tabs
//        Main.REGISTRATE.defaultCreativeTab(ModCreativeModTabs.MOD_TAB.getKey());
    }

//    public static final BlockEntry<DecomponentalizerBlock> DECOMPONENTALIZER = Main.REGISTRATE.block("decomponentalizer", DecomponentalizerBlock::new)
//            .initialProperties(SharedProperties::softMetal)
//            .properties(p -> p.mapColor(MapColor.COLOR_GRAY))
//            .transform(TagGen.pickaxeOnly())
//            .item()
//            .tab(ModCreativeModTabs.MOD_TAB.getKey())
//            .transform(ModelGen.customItemModel())
//            .register();

    public static final BlockEntry<CastingBasinBlock> CASTING_BASIN = PreciseManufacturing.REGISTRATE
            .block("casting_basin", CastingBasinBlock::new)
            .initialProperties(SharedProperties::stone)
            .properties(p -> p.mapColor(MapColor.COLOR_GRAY))
            .transform(TagGen.axeOrPickaxe())
            .blockstate((c, p) -> p.simpleBlock(c.getEntry(), AssetLookup.partialBaseModel(c, p)))
//            .onRegister(AllDisplaySources.assignDataBehaviour(new ItemNameDisplaySource(), "combine_item_names"))
            .item(AssemblyOperatorBlockItem::new)
            .model(AssetLookup::customItemModel)
            .build()
            .register();

    public static void register(){

    }
}
