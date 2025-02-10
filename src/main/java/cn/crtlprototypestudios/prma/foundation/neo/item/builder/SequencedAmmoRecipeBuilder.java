package cn.crtlprototypestudios.prma.foundation.neo.item.builder;

import cn.crtlprototypestudios.prma.foundation.ModItems;
import cn.crtlprototypestudios.prma.foundation.neo.item.type.AmmoCasingType;
import cn.crtlprototypestudios.prma.foundation.neo.item.type.AmmoMaterialType;
import cn.crtlprototypestudios.prma.util.Reference;
import com.simibubi.create.content.kinetics.deployer.DeployerApplicationRecipe;
import com.simibubi.create.content.kinetics.press.PressingRecipe;
import com.simibubi.create.content.processing.sequenced.SequencedAssemblyRecipeBuilder;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;

public class SequencedAmmoRecipeBuilder {
    protected final RegistryEntry<? extends Item> baseCartridgePiece;
    protected final SequencedAssemblyRecipeBuilder builder;
    protected SequencedAmmoRecipeBuilder(RegistryEntry<? extends Item> baseCartridgePiece, RegistryEntry<Item> transitionItem, String namespaceId, String ammoId) {
        this.baseCartridgePiece = baseCartridgePiece;
        this.builder = new SequencedAssemblyRecipeBuilder(new ResourceLocation(namespaceId, ammoId + "_sequenced_assembly"))
                .require(baseCartridgePiece.get())
                .transitionTo(transitionItem.get())
                .loops(1);
    }

    public static SequencedAmmoRecipeBuilder create(RegistryEntry<? extends Item> baseCartridgePiece, RegistryEntry<Item> transitionItem, String namespaceId, String ammoId) {
        return new SequencedAmmoRecipeBuilder(baseCartridgePiece, transitionItem, namespaceId, ammoId);
    }

    public static SequencedAmmoRecipeBuilder create(RegistryEntry<? extends Item> baseCartridgePiece, RegistryEntry<Item> transitionItem, String ammoId) {
        return create(baseCartridgePiece, transitionItem, Reference.MOD_ID, ammoId);
    }

    /*
   TODO: Implement the functions
    */

    public SequencedAmmoRecipeBuilder deployerApply(ItemLike item, int times) {
        for (int i = 0; i < times; i++){
            builder.addStep(DeployerApplicationRecipe::new, d -> d.require(item));
        }
        return this;
    }

    public SequencedAmmoRecipeBuilder deployerApply(ItemLike item) {
        return deployerApply(item, 1);
    }

    public SequencedAmmoRecipeBuilder pressingApply(int times) {
        for (int i = 0; i < times; i++){
            builder.addStep(PressingRecipe::new, p -> p);
        }
        return this;
    }

    public SequencedAmmoRecipeBuilder pressingApply() {
        return pressingApply(1);
    }

    public SequencedAmmoRecipeBuilder applyPrimer() {
        return deployerApply(ModItems.PRIMER.get());
    }

    public SequencedAmmoRecipeBuilder applyGunpowder(int times) {
        return deployerApply(Items.GUNPOWDER, times);
    }

    public SequencedAmmoRecipeBuilder applyGunpowder(AmmoCasingType casingType, AmmoMaterialType materialType, int times) {
        return deployerApply();
    }
}
