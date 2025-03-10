package cn.crtlprototypestudios.prma.foundation.neo.simple.item;

import cn.crtlprototypestudios.prma.PreciseManufacturing;
import cn.crtlprototypestudios.prma.foundation.PrmaItems;
import cn.crtlprototypestudios.prma.foundation.PrmaTags;
import cn.crtlprototypestudios.prma.foundation.data.generators.recipe.ModRecipesGen;
import cn.crtlprototypestudios.prma.foundation.data.providers.ModItemModelProvider;
import cn.crtlprototypestudios.prma.foundation.neo.complex.content.item.type.standard.AmmoCasingType;
import cn.crtlprototypestudios.prma.foundation.neo.complex.content.item.type.standard.AmmoMaterialType;
import cn.crtlprototypestudios.prma.foundation.neo.complex.content.item.type.standard.AmmoSizeType;
import cn.crtlprototypestudios.prma.foundation.neo.simple.content.type.standard.SimpleAmmoGunpowderAmountStandard;
import cn.crtlprototypestudios.prma.foundation.neo.simple.recipe.SimpleCartridgeRecipeBuilder;
import com.simibubi.create.AllTags;
import com.simibubi.create.content.processing.sequenced.SequencedAssemblyRecipeBuilder;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.item.Item;

public class SimpleCartridge {
    protected final RegistryEntry<? extends Item> item, transition;
    protected AmmoCasingType casingType;
    protected AmmoMaterialType materialType;
    protected SimpleAmmoGunpowderAmountStandard amountStandard;

    public SimpleCartridge(AmmoCasingType casingType, AmmoMaterialType materialType, SimpleAmmoGunpowderAmountStandard amountStandard) {
        this.casingType = casingType;
        this.materialType = materialType;
        this.amountStandard = amountStandard;
        this.item = PrmaItems.addToMaterials(PreciseManufacturing.REGISTRATE.item(String.format("%s_%s_%s_%s", casingType.toString(), materialType.toString(), amountStandard.toString(), "gunpowder_cartridge"), Item::new)
                .model(ModItemModelProvider.genericItemModel(true, "simple", "ammo", "cartridge", materialType.toString(), "_"))
                .tag(PrmaTags.ItemTag.MATERIALS.tag, PrmaTags.ItemTag.AMMO_CASINGS.tag, AllTags.AllItemTags.UPRIGHT_ON_BELT.tag).register());
        this.transition = PreciseManufacturing.REGISTRATE.item(String.format("%s_%s_%s_%s", casingType.toString(), materialType.toString(), amountStandard.toString(), "gunpowder_cartridge_transition"), Item::new)
                .model(ModItemModelProvider.genericItemModel(true, "simple", "ammo", "cartridge", materialType.toString(), "_"))
                .tag(PrmaTags.ItemTag.AMMO_WASTE.tag, AllTags.AllItemTags.UPRIGHT_ON_BELT.tag).register();
        ModRecipesGen.addSimpleCartridge(this);
    }

    public RegistryEntry<? extends Item> getItem(){
        return item;
    }

    public RegistryEntry<? extends Item> getTransition() {
        return transition;
    }

    public void registerStandardRecipes() {
        SimpleCartridgeRecipeBuilder.create(casingType, materialType, amountStandard, this).standard().build();
    }
}
