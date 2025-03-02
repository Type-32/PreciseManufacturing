package cn.crtlprototypestudios.prma.foundation.neo.simple.recipe;

import cn.crtlprototypestudios.prma.PreciseManufacturing;
import cn.crtlprototypestudios.prma.foundation.PrmaItems;
import cn.crtlprototypestudios.prma.foundation.data.generators.recipe.ModRecipesGen;
import cn.crtlprototypestudios.prma.foundation.neo.complex.content.item.type.standard.AmmoCasingType;
import cn.crtlprototypestudios.prma.foundation.neo.complex.content.item.type.standard.AmmoMaterialType;
import cn.crtlprototypestudios.prma.foundation.neo.simple.content.type.standard.SimpleAmmoGunpowderAmountStandard;
import cn.crtlprototypestudios.prma.foundation.neo.simple.item.SimpleCartridge;
import cn.crtlprototypestudios.prma.lib.Reference;
import com.simibubi.create.content.kinetics.deployer.DeployerApplicationRecipe;
import com.simibubi.create.content.kinetics.press.PressingRecipe;
import com.simibubi.create.content.processing.sequenced.SequencedAssemblyRecipeBuilder;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

public class SimpleCartridgeBuilder {
    public static SimpleCartridge create(AmmoCasingType baseCasingType, AmmoMaterialType baseCasingMaterialType, SimpleAmmoGunpowderAmountStandard amountStandard, String namespaceId) {
        return new SimpleCartridge(baseCasingType, baseCasingMaterialType, amountStandard);
    }

    public static SimpleCartridge create(AmmoCasingType baseCasingType, AmmoMaterialType baseCasingMaterialType, SimpleAmmoGunpowderAmountStandard amountStandard) {
        return create(baseCasingType, baseCasingMaterialType, amountStandard, Reference.MOD_ID);
    }
}
