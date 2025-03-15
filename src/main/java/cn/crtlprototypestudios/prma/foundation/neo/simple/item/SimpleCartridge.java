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
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

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
        // TODO: Note to future self: Casings as a Small, Medium, and Long Length. Cartridges has a Small, Medium, and Long Length as well, but, for each length, there has to be different gunpowders; small/medium/high gunpowder cartridges is for all lengths, and the small/medium/high refers to not the amount, but the type of ammo gunpowder loaded in said length-ed cartridge.
        ItemLike baseItem = null;
        if (amountStandard == SimpleAmmoGunpowderAmountStandard.Low)
            baseItem = getBaseCasing().get();
        else if(amountStandard == SimpleAmmoGunpowderAmountStandard.Medium)
            baseItem = PrmaItems.Ammo.getCartridge(this.casingType, this.materialType, SimpleAmmoGunpowderAmountStandard.Low).getItem().get();
        else if(amountStandard == SimpleAmmoGunpowderAmountStandard.High)
            baseItem = PrmaItems.Ammo.getCartridge(this.casingType, this.materialType, SimpleAmmoGunpowderAmountStandard.Medium).getItem().get();
        SimpleCartridgeRecipeBuilder builder = SimpleCartridgeRecipeBuilder.createWithCustomBase(this, baseItem);
        builder.deployerApply(Items.GUNPOWDER);
        builder.build();
    }

    public RegistryEntry<? extends Item> getBaseCasing() {
        return PrmaItems.Ammo.getCasingByTypes(casingType, materialType);
    }

    public String getCartridgeName() {
        return String.format("%s_%s_%s_%s", casingType.toString(), materialType.toString(), amountStandard.toString(), "gunpowder_cartridge");
    }
}
