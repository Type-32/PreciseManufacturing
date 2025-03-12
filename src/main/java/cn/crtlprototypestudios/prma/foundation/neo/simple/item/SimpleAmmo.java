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
import com.simibubi.create.AllItems;
import com.simibubi.create.AllTags;
import com.simibubi.create.content.kinetics.deployer.DeployerApplicationRecipe;
import com.simibubi.create.content.kinetics.press.PressingRecipe;
import com.simibubi.create.content.kinetics.saw.CuttingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.simibubi.create.content.processing.sequenced.SequencedAssemblyRecipeBuilder;
import com.tacz.guns.api.item.builder.AmmoItemBuilder;
import com.tacz.guns.init.ModItems;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

import java.util.Objects;

// SimpleAmmo registers the recipes from the gunpowder_cartridges to the actual bullet ammo.
public class SimpleAmmo {
    protected final SimpleCartridge cartridge;
    protected final String ammoId;
    protected final AmmoHeadType headType;
    protected final AmmoMaterialType headMaterial;
    protected final RegistryEntry<Item> bulletHead, transitionItem;
    protected final int resultAmount;

    public SimpleAmmo(SimpleCartridge cartridge, AmmoHeadType headType, AmmoMaterialType headMaterial, String ammoId, int resultAmount) {
        PreciseManufacturing.LOGGER.debug("cartridge null? {}", cartridge.item == null);
        this.cartridge = cartridge;
        this.ammoId = ammoId;
        this.headType = headType;
        this.headMaterial = headMaterial;
        this.resultAmount = resultAmount;

        this.bulletHead = PrmaItems.addToMaterials(PreciseManufacturing.REGISTRATE.item(String.format("%s_%s", ammoId, "head"), Item::new)
                .model(ModItemModelProvider.genericItemModel(true, "simple", "ammo", "head", "_"))
                .tag(PrmaTags.ItemTag.MATERIALS.tag, PrmaTags.ItemTag.AMMO_HEADS.tag, AllTags.AllItemTags.UPRIGHT_ON_BELT.tag).register());
        this.transitionItem = PreciseManufacturing.REGISTRATE.item(String.format("%s_%s", ammoId, "transition"), Item::new)
                .model(ModItemModelProvider.genericItemModel(true, "simple", "ammo", "ammo_transition", "_"))
                .tag(PrmaTags.ItemTag.AMMO_WASTE.tag, AllTags.AllItemTags.UPRIGHT_ON_BELT.tag).register();

        ModRecipesGen.addSimpleAmmo(this);
    }

    public static SimpleAmmo create(SimpleCartridge cartridge, AmmoHeadType headType, AmmoMaterialType headMaterial, String ammoId, int resultAmount){
        return new SimpleAmmo(cartridge, headType, headMaterial, ammoId, resultAmount);
    }

    public void registerRecipes() {
        assert cartridge.item != null;

        ItemStack v = new ItemStack((ItemLike) ModItems.AMMO.get());
        CompoundTag taczTag = new CompoundTag(); // TODO: Not a great way but it works.
        taczTag.putString("AmmoId", String.format("tacz:%s", ammoId));
        v.setTag(taczTag);
        v.setCount(1);

        ModRecipesGen.addSequencedAssemblyRecipe(new SequencedAssemblyRecipeBuilder(ResourceHelper.find(String.format("simple/ammo/%s_head", ammoId)))
                .require(cartridge.item.get())
                .transitionTo(transitionItem.get()) // TODO: change this to unfinished gunpowder cartridge
                .loops(1)
                .addStep(DeployerApplicationRecipe::new, p -> p.require(bulletHead.get()))
                .addStep(PressingRecipe::new, p -> p)
//                .addOutput(AmmoItemBuilder.create()
//                        .setId(new ResourceLocation("tacz", ammoId))
//                        .setCount(1)
//                        .build().getItem(), 1)
                .addOutput(v, 1) // TODO: Use AmmoItemBuilder later. Right now their builder API Doesn't work for some reason.
        );

        ModRecipesGen.addCreateRecipe(new ProcessingRecipeBuilder<>(CuttingRecipe::new, ResourceHelper.find(String.format("simple/ammo/%s_head", ammoId)))
                .require(switch(headMaterial){
                    case Iron -> AllItems.IRON_SHEET;
                    case Brass -> AllItems.BRASS_SHEET;
                    case Copper -> AllItems.COPPER_SHEET;
                    case Plastic -> Items.PAPER;
                })
                .duration(100)
                .output(bulletHead.get(), resultAmount));
    }
}
