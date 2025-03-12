package cn.crtlprototypestudios.prma.foundation.neo.complex.recipe;

import cn.crtlprototypestudios.prma.foundation.PrmaItems;
import cn.crtlprototypestudios.prma.foundation.neo.complex.content.item.type.standard.AmmoCasingType;
import cn.crtlprototypestudios.prma.foundation.neo.complex.content.item.type.standard.AmmoHeadType;
import cn.crtlprototypestudios.prma.foundation.neo.complex.content.item.type.standard.AmmoMaterialType;
import cn.crtlprototypestudios.prma.foundation.neo.complex.content.item.type.standard.AmmoSizeType;
import cn.crtlprototypestudios.prma.lib.Reference;
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
        this.builder = new SequencedAssemblyRecipeBuilder(new ResourceLocation(namespaceId, String.format("sequenced_assembly/ammo/%s", ammoId)))
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

    public static SequencedAmmoRecipeBuilder create(AmmoCasingType baseCasingType, AmmoMaterialType baseCasingMaterialType, String namespaceId, String ammoId) {
        return create(
                PrmaItems.Ammo.getCasingByTypes(baseCasingType, baseCasingMaterialType),
                switch(baseCasingType) {
                    case Small -> PrmaItems.SMALL_COMPONENTS.getTransition(baseCasingMaterialType);
                    case Medium -> PrmaItems.MEDIUM_COMPONENTS.getTransition(baseCasingMaterialType);
                    case Long -> PrmaItems.LONG_COMPONENTS.getTransition(baseCasingMaterialType);
                    case Shell -> PrmaItems.Ammo.SHOTGUN_SHELL_TRANSITION;
                },
                namespaceId,
                ammoId
        );
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
        return deployerApply(PrmaItems.Ammo.CARTRIDGE_PRIMER.get());
    }

    public SequencedAmmoRecipeBuilder applyGunpowder(int times) {
        return deployerApply(Items.GUNPOWDER, times);
    }

    public SequencedAmmoRecipeBuilder applyHead(AmmoHeadType headType, AmmoMaterialType materialType) {
        return deployerApply(PrmaItems.Ammo.getHeadByTypes(headType, materialType).get());
    }

    public SequencedAmmoRecipeBuilder applyCasing(AmmoCasingType casingType, AmmoMaterialType materialType) {
        return deployerApply(PrmaItems.Ammo.getCasingByTypes(casingType, materialType).get());
    }

    public SequencedAmmoRecipeBuilder applyShotgunBearings(int times) {
        return deployerApply(PrmaItems.Ammo.SHOTGUN_BEARING.get(), times);
    }

    public SequencedAmmoRecipeBuilder applyShotgunShellBase() {
        return deployerApply(PrmaItems.Ammo.SHOTGUN_SHELL_BASE.get());
    }

    public SequencedAmmoRecipeBuilder applyShotgunShell() {
        return deployerApply(PrmaItems.Ammo.SHOTGUN_SHELL.get());
    }
}
