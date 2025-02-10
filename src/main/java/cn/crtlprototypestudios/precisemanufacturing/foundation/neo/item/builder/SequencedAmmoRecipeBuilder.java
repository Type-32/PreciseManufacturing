package cn.crtlprototypestudios.precisemanufacturing.foundation.neo.item.builder;

import cn.crtlprototypestudios.precisemanufacturing.util.Reference;
import com.simibubi.create.content.kinetics.deployer.DeployerApplicationRecipe;
import com.simibubi.create.content.processing.sequenced.SequencedAssemblyRecipeBuilder;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

public class SequencedAmmoRecipeBuilder {
    protected final RegistryEntry<? extends Item> baseCartridgePiece;
    protected final SequencedAssemblyRecipeBuilder builder;
    protected SequencedAmmoRecipeBuilder(RegistryEntry<? extends Item> baseCartridgePiece, String namespaceId, String ammoId) {
        this.baseCartridgePiece = baseCartridgePiece;
        this.builder = new SequencedAssemblyRecipeBuilder(new ResourceLocation(namespaceId, ammoId + "_sequenced_assembly"));
    }
    public static SequencedAmmoRecipeBuilder create(RegistryEntry<? extends Item> baseCartridgePiece, String namespaceId, String ammoId) {
        return new SequencedAmmoRecipeBuilder(baseCartridgePiece, namespaceId, ammoId);
    }
    public static SequencedAmmoRecipeBuilder create(RegistryEntry<? extends Item> baseCartridgePiece, String ammoId) {
        return create(baseCartridgePiece, Reference.MOD_ID, ammoId);
    }
    /*
   TODO: Implement the functions
    */
    public SequencedAmmoRecipeBuilder deployerApply(ItemLike item) {
        builder.addStep(DeployerApplicationRecipe::new, )
    }
}
