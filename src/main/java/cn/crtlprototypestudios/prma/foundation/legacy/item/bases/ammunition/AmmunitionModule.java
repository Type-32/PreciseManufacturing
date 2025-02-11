package cn.crtlprototypestudios.prma.foundation.legacy.item.bases.ammunition;

import cn.crtlprototypestudios.prma.PreciseManufacturing;
import cn.crtlprototypestudios.prma.foundation.PrmaCreativeModTabs;
import cn.crtlprototypestudios.prma.foundation.PrmaItems;
import cn.crtlprototypestudios.prma.foundation.PrmaTags;
import cn.crtlprototypestudios.prma.foundation.data.providers.ModItemModelProvider;
import cn.crtlprototypestudios.prma.foundation.data.providers.ModRecipeProvider;
import cn.crtlprototypestudios.prma.foundation.utility.ResourceHelper;
import com.simibubi.create.AllTags;
import com.simibubi.create.content.fluids.transfer.FillingRecipe;
import com.simibubi.create.content.kinetics.deployer.DeployerApplicationRecipe;
import com.simibubi.create.content.kinetics.saw.CuttingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluid;

import java.util.Objects;

public class AmmunitionModule {
    private String coreId;
    private AmmunitionSize size;
    private AmmunitionMaterialType type;
    private TagKey<Fluid> fillingFluid;
    private int decomponentalizingTime, castFillingAmount, castResultCount; // the castResultCount here refers to how many "main" module items will be resulted from filling in the cast once.

    public RegistryEntry<Item> item, blueprint, cast;

    public AmmunitionModule(AmmunitionSize size, AmmunitionMaterialType type, TagKey<Fluid> fillingFluid) {
        this(size, type, fillingFluid, 400, 80, 4);
    }

    public AmmunitionModule(AmmunitionSize size, AmmunitionMaterialType type, TagKey<Fluid> fillingFluid, int decomponentalizingTime) {
        this(size, type, fillingFluid, decomponentalizingTime, 80, 4);
    }

    public AmmunitionModule(AmmunitionSize size, AmmunitionMaterialType type, TagKey<Fluid> fillingFluid, int decomponentalizingTime, int castFillingAmount, int castResultCount) {
        this.size = size;
        this.type = type;
        this.fillingFluid = fillingFluid;
        this.decomponentalizingTime = decomponentalizingTime;
        this.castFillingAmount = castFillingAmount;
        this.castResultCount = castResultCount;

        AmmunitionRegistryManager.register(register());
    }

    public AmmunitionSize getSize() {
        return size;
    }

    public AmmunitionModule setSize(AmmunitionSize size) {
        this.size = size;
        return new AmmunitionModule(size, type, fillingFluid);
    }

    public AmmunitionMaterialType getType() {
        return type;
    }

    public AmmunitionModule setType(AmmunitionMaterialType type) {
        this.type = type;
        return new AmmunitionModule(size, type, fillingFluid);
    }

    public TagKey<Fluid> getFillingFluid() {
        return fillingFluid;
    }

    public AmmunitionModule setFillingFluid(TagKey<Fluid> fillingFluid) {
        this.fillingFluid = fillingFluid;
        return new AmmunitionModule(size, type, fillingFluid);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AmmunitionModule that)) return false;
        return size == that.size && type == that.type && Objects.equals(fillingFluid, that.fillingFluid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, type, fillingFluid);
    }

    public int getDecomponentalizingTime() {
        return decomponentalizingTime;
    }

    public void setDecomponentalizingTime(int decomponentalizingTime) {
        this.decomponentalizingTime = decomponentalizingTime;
    }

    public int getCastFillingAmount() {
        return castFillingAmount;
    }

    public void setCastFillingAmount(int castFillingAmount) {
        this.castFillingAmount = castFillingAmount;
    }

    public int getCastResultCount() {
        return castResultCount;
    }

    public void setCastResultCount(int castResultCount) {
        this.castResultCount = castResultCount;
    }
    
    public AmmunitionModule register(){
        String fluidTagType = "";
        String[] splitted = getFillingFluid().toString().split("_"); // e.g. ["molten", "iron", "fluids"]
        for(int i = 1; i < splitted.length - 1; i++){
            fluidTagType += splitted[i];
            if(i < splitted.length - 2) fluidTagType += "_";
        }

        String itemId = String.format("%s_%s_%s_component", getSize(), fluidTagType, getType());
        this.coreId = itemId;
        assert PrmaCreativeModTabs.MOD_MATERIALS_TAB.getKey() != null;
        this.item = PreciseManufacturing.REGISTRATE.item(itemId, Item::new)
                .tag(
                        PrmaTags.ammunitionComponentsTag(),
                        (
                                getFillingFluid() == PrmaTags.moltenIronTag() ? PrmaTags.ironAmmunitionComponentsTag() :
                                getFillingFluid() == PrmaTags.moltenCoppersTag() ? PrmaTags.copperAmmunitionComponentsTag() :
                                PrmaTags.brassAmmunitionComponentsTag()
                        ),
                        (
                                getType() == AmmunitionMaterialType.CASING ? PrmaTags.ammunitionCasingComponentsTag() :
                                getType() == AmmunitionMaterialType.HEAD ? PrmaTags.ammunitionHeadComponentsTag() :
                                getType() == AmmunitionMaterialType.PELLETS ? PrmaTags.ammunitionPelletsComponentsTag() :
                                PrmaTags.ammunitionWasteComponentsTag()
                        ),
                        (
                                getSize() == AmmunitionSize.SMALL ? PrmaTags.smallAmmunitionComponentsTag() :
                                getSize() == AmmunitionSize.MEDIUM ? PrmaTags.mediumAmmunitionComponentsTag() :
                                getSize() == AmmunitionSize.LONG ? PrmaTags.longAmmunitionComponentsTag() :
                                PrmaTags.shellAmmunitionComponentsTag()
                        ),
                        AllTags.AllItemTags.UPRIGHT_ON_BELT.tag
                )
                .tab(PrmaCreativeModTabs.MOD_MATERIALS_TAB.getKey())
                .model(ModItemModelProvider.genericItemModel(true, "ammunition_components", "modules", itemId))
                .register();
        this.cast = PreciseManufacturing.REGISTRATE.item(itemId + "_cast", Item::new)
                .tag(PrmaTags.ammunitionComponentCastsTag())
                .tab(PrmaCreativeModTabs.MOD_MATERIALS_TAB.getKey())
                .model(ModItemModelProvider.genericItemModel(true, "ammunition_components", "casts", itemId + "_cast"))
                .register();
        this.blueprint = PreciseManufacturing.REGISTRATE.item(itemId + "_blueprint", Item::new)
                .tag(PrmaTags.ammunitionComponentBlueprintsTag())
                .tab(PrmaCreativeModTabs.MOD_MATERIALS_TAB.getKey())
                .model(ModItemModelProvider.genericItemModel(true, "ammunition_components", "blueprints", itemId + "_blueprint"))
                .register();

        PrmaItems.addToMaterials(this.item);
        PrmaItems.addToMaterials(this.cast);
        PrmaItems.addToMaterials(this.blueprint);

        return this;
    }

    public void registerRecipes(){
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(FillingRecipe::new, ResourceHelper.find("filling/ammunition_modules/" + coreId + "_cast_filling"))
                .output(item.get(), getCastResultCount())
                .require(cast.get())
                .require(getFillingFluid(), getCastFillingAmount()));
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(CuttingRecipe::new, ResourceHelper.find("cutting/ammunition_modules/" + coreId + "_cast_cutting"))
                .output(cast.get(), 1)
                .require(PrmaItems.BLANK_CAST.get()));
        ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<>(DeployerApplicationRecipe::new, ResourceHelper.find("application/ammunition_modules/" + coreId + "_apply_to_cast"))
                .output(cast.get(), 1)
                .require(Items.IRON_INGOT)
                .require(blueprint.get()));
    }
}
