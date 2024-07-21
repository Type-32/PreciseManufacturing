package cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.ammunition;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;

import java.util.Objects;

public class AmmunitionModule {
    private AmmunitionSize size;
    private AmmunitionMaterialType type;
    private TagKey<Fluid> fillingFluid;
    private int decomponentalizingTime, castFillingAmount, castResultCount; // the castResultCount here refers to how many "main" module items will be resulted from filling in the cast once.

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
}
