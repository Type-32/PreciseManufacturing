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

public class SimpleCartridgeRecipeBuilder {
    protected final RegistryEntry<? extends Item> baseCartridgePiece;
    protected final SequencedAssemblyRecipeBuilder builder;
    protected final AmmoCasingType baseCasingType;
    protected final AmmoMaterialType baseCasingMaterialType;
    protected final SimpleAmmoGunpowderAmountStandard amountStandard;
    protected final SimpleCartridge cartridge;
    protected SimpleCartridgeRecipeBuilder(AmmoCasingType baseCasingType, AmmoMaterialType baseCasingMaterialType, SimpleAmmoGunpowderAmountStandard amountStandard, RegistryEntry<Item> transitionItem, String namespaceId, String recipeName, SimpleCartridge resultingCartridge) {
        this.baseCartridgePiece = PrmaItems.Ammo.getCasingByTypes(baseCasingType, baseCasingMaterialType);

        assert baseCartridgePiece != null;
        this.builder = new SequencedAssemblyRecipeBuilder(new ResourceLocation(namespaceId, String.format("simple/cartridge/%s", recipeName)))
                .require(baseCartridgePiece.get())
                .transitionTo(transitionItem.get())
                .loops(1);
        this.baseCasingType = baseCasingType;
        this.baseCasingMaterialType = baseCasingMaterialType;
        this.amountStandard = amountStandard;
        this.cartridge = resultingCartridge;
    }

    public static SimpleCartridgeRecipeBuilder create(AmmoCasingType baseCasingType, AmmoMaterialType baseCasingMaterialType, SimpleAmmoGunpowderAmountStandard amountStandard, String namespaceId, SimpleCartridge resultingCartridge) {
        return new SimpleCartridgeRecipeBuilder(
                baseCasingType, baseCasingMaterialType, amountStandard,
                switch(baseCasingType) {
                    case Small -> PrmaItems.SMALL_COMPONENTS.getTransition(baseCasingMaterialType);
                    case Medium -> PrmaItems.MEDIUM_COMPONENTS.getTransition(baseCasingMaterialType);
                    case Long -> PrmaItems.LONG_COMPONENTS.getTransition(baseCasingMaterialType);
                    case Shell -> PrmaItems.Ammo.SHOTGUN_SHELL_TRANSITION;
                },
                namespaceId,
                String.format("%s_%s_%s_%s", baseCasingType.toString(), baseCasingMaterialType.toString(), amountStandard.toString(), "gunpowder_cartridge"),
                resultingCartridge
        );
    }

    public static SimpleCartridgeRecipeBuilder create(AmmoCasingType baseCasingType, AmmoMaterialType baseCasingMaterialType, SimpleAmmoGunpowderAmountStandard amountStandard, SimpleCartridge resultingCartridge) {
        return create(baseCasingType, baseCasingMaterialType, amountStandard, Reference.MOD_ID, resultingCartridge);
    }

    public SimpleCartridgeRecipeBuilder deployerApply(ItemLike item, int times) {
        for (int i = 0; i < times; i++){
            builder.addStep(DeployerApplicationRecipe::new, d -> d.require(item));
        }
        return this;
    }

    public SimpleCartridgeRecipeBuilder deployerApply(ItemLike item) {
        return deployerApply(item, 1);
    }

    public SimpleCartridgeRecipeBuilder pressingApply(int times) {
        for (int i = 0; i < times; i++){
            builder.addStep(PressingRecipe::new, p -> p);
        }
        return this;
    }

    public SimpleCartridgeRecipeBuilder pressingApply() {
        return pressingApply(1);
    }

    public SimpleCartridgeRecipeBuilder applyPrimer() {
        return deployerApply(PrmaItems.Ammo.CARTRIDGE_PRIMER.get());
    }

    public SimpleCartridgeRecipeBuilder applyGunpowder(SimpleAmmoGunpowderAmountStandard sizeType) {
        return deployerApply(
                PrmaItems.Ammo.getGunpowderByTypes(baseCasingType).get(),
                switch (sizeType) {
                    case Low -> 1;
                    case Medium -> 2;
                    case High -> 3;
                    case HighPower -> 4;
                });
    }

    public SimpleCartridgeRecipeBuilder applyGunpowder(int amount) {
        return deployerApply(PrmaItems.Ammo.getGunpowderByTypes(baseCasingType).get(), amount);
    }

    public SimpleCartridgeRecipeBuilder applyGunpowder() {
        return applyGunpowder(amountStandard);
    }

    public SimpleCartridgeRecipeBuilder applyShotgunBearings(int times) {
        return deployerApply(PrmaItems.Ammo.SHOTGUN_BEARING.get(), times);
    }

    public SimpleCartridgeRecipeBuilder applyShotgunShellBase() {
        return deployerApply(PrmaItems.Ammo.SHOTGUN_SHELL_BASE.get());
    }

    public SimpleCartridgeRecipeBuilder applyShotgunShell() {
        return deployerApply(PrmaItems.Ammo.SHOTGUN_SHELL.get());
    }

    public SimpleCartridgeRecipeBuilder standard() {
        return this.applyPrimer().applyGunpowder();
    }

    public final SimpleCartridgeRecipeBuilder build(){
        ModRecipesGen.addSequencedAssemblyRecipe(builder.addOutput(cartridge.getItem().get(), 100));
        return this;
    }
}
