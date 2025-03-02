package cn.crtlprototypestudios.prma.foundation.neo.simple.item;

import cn.crtlprototypestudios.prma.PreciseManufacturing;
import cn.crtlprototypestudios.prma.foundation.PrmaItems;
import cn.crtlprototypestudios.prma.foundation.PrmaTags;
import cn.crtlprototypestudios.prma.foundation.data.generators.recipe.ModRecipesGen;
import cn.crtlprototypestudios.prma.foundation.data.providers.ModItemModelProvider;
import cn.crtlprototypestudios.prma.foundation.neo.complex.bridge.TaczAPIBridge;
import cn.crtlprototypestudios.prma.foundation.neo.complex.content.item.type.standard.AmmoHeadType;
import cn.crtlprototypestudios.prma.foundation.neo.complex.content.item.type.standard.AmmoMaterialType;
import cn.crtlprototypestudios.prma.foundation.utility.ResourceHelper;
import com.simibubi.create.AllTags;
import com.simibubi.create.content.kinetics.press.PressingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.simibubi.create.content.processing.sequenced.SequencedAssemblyRecipeBuilder;
import com.tacz.guns.api.item.builder.AmmoItemBuilder;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class SimpleAmmo {
    protected final SimpleCartridge cartridge;
    protected final String ammoId;
    protected final AmmoHeadType headType;
    protected final AmmoMaterialType headMaterial;
    protected final RegistryEntry<Item> bulletHead;

    public SimpleAmmo(SimpleCartridge cartridge, AmmoHeadType headType, AmmoMaterialType headMaterial, String ammoId) {
        PreciseManufacturing.LOGGER.debug("cartridge null? {}", cartridge.item == null);
        this.cartridge = cartridge;
        this.ammoId = ammoId;
        this.headType = headType;
        this.headMaterial = headMaterial;

        this.bulletHead = PrmaItems.addToMaterials(PreciseManufacturing.REGISTRATE.item(String.format("%s_%s_%s", ammoId, headMaterial.toString(), "head"), Item::new)
                .model(ModItemModelProvider.genericItemModel(true, "simple", "ammo", "head", "_"))
                .tag(PrmaTags.ItemTag.MATERIALS.tag, PrmaTags.ItemTag.AMMO_HEADS.tag, AllTags.AllItemTags.UPRIGHT_ON_BELT.tag).register());

        ModRecipesGen.addSimpleAmmo(this);
    }

    public static SimpleAmmo create(SimpleCartridge cartridge, AmmoHeadType headType, AmmoMaterialType headMaterial, String ammoId){
        return new SimpleAmmo(cartridge, headType, headMaterial, ammoId);
    }

    public void registerRecipes() {
        assert cartridge.item != null;
        ModRecipesGen.addSequencedAssemblyRecipe(new SequencedAssemblyRecipeBuilder(ResourceHelper.find(String.format("sequenced_assembly/simple/ammo/%s", ammoId)))
                .require(cartridge.item.get())
                .transitionTo(cartridge.item.get())
                .loops(1)
                .addStep(PressingRecipe::new, p -> p)
                .addOutput(AmmoItemBuilder.create()
                        .setId(new ResourceLocation("tacz", ammoId))
                        .setCount(1)
                        .build().getItem(), 100));
    }
}
