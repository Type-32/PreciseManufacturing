package cn.crtlprototypestudios.prma.foundation.legacy.item.bases.ammunition;

import cn.crtlprototypestudios.prma.foundation.ModTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class AmmunitionBase {
    private final String coreId;
    private static final AmmunitionModule[] presetModules = new AmmunitionModule[]{
            new AmmunitionModule(AmmunitionSize.SMALL, AmmunitionMaterialType.HEAD, ModTags.moltenCoppersTag(), 100, 80, 4), // small_copper_head_component, 0
            new AmmunitionModule(AmmunitionSize.SMALL, AmmunitionMaterialType.CASING, ModTags.moltenBrassesTag(), 300, 120, 3), // small_brass_casing_component, 1
            new AmmunitionModule(AmmunitionSize.MEDIUM, AmmunitionMaterialType.HEAD, ModTags.moltenCoppersTag(), 200, 200, 5), // medium_copper_head_component, 2
            new AmmunitionModule(AmmunitionSize.MEDIUM, AmmunitionMaterialType.CASING, ModTags.moltenBrassesTag(), 500, 300, 5), // medium_brass_casing_component, 3
            new AmmunitionModule(AmmunitionSize.MEDIUM, AmmunitionMaterialType.CASING, ModTags.moltenIronTag(), 600, 300, 5), // medium_iron_casing_component, 4
            new AmmunitionModule(AmmunitionSize.LONG, AmmunitionMaterialType.HEAD, ModTags.moltenCoppersTag(), 300, 180, 3), // long_copper_head_component, 5
            new AmmunitionModule(AmmunitionSize.LONG, AmmunitionMaterialType.CASING, ModTags.moltenBrassesTag(), 500, 350, 5), // long_brass_casing_component, 6
            new AmmunitionModule(AmmunitionSize.LONG, AmmunitionMaterialType.CASING, ModTags.moltenIronTag(), 600, 350, 5), // long_iron_casing_component, 7
            new AmmunitionModule(AmmunitionSize.SHELL, AmmunitionMaterialType.PELLETS, ModTags.moltenIronTag(), 60, 60, 6), // shell_iron_pellets_component, 8
            new AmmunitionModule(AmmunitionSize.SHELL, AmmunitionMaterialType.CASING, ModTags.moltenBrassesTag(), 200, 120, 4) // shell_brass_casing_component, 9
    };

    public AmmunitionBase(String coreId) {
        this.coreId = coreId;
    }

    public String getCoreId() {
        return coreId;
    }

    public static List<AmmunitionModule> findAmmunitionModuleOfSize(AmmunitionSize size, AmmunitionModule[] source){
        List<AmmunitionModule> modules = new ArrayList<>();
        for(AmmunitionModule module : source){
            if(module.getSize() == size){
                modules.add(module);
            }
        }
        return modules;
    }

    public static List<AmmunitionModule> findAmmunitionModuleOfType(AmmunitionMaterialType type, AmmunitionModule[] source){
        List<AmmunitionModule> modules = new ArrayList<>();
        for(AmmunitionModule module : source){
            if(module.getType() == type){
                modules.add(module);
            }
        }
        return modules;
    }

    public static List<AmmunitionModule> findAmmunitionModuleOfFillingFluis(TagKey<Fluid> fluid, AmmunitionModule[] source){
        List<AmmunitionModule> modules = new ArrayList<>();
        for(AmmunitionModule module : source){
            if(module.getFillingFluid().equals(fluid)){
                modules.add(module);
            }
        }
        return modules;
    }

    public static @Nullable AmmunitionModule findModule(AmmunitionSize size, AmmunitionMaterialType type, TagKey<Fluid> fluidTag){
        AmmunitionModule[] modules = findAmmunitionModuleOfSize(size, presetModules).toArray(new AmmunitionModule[0]);
        modules = findAmmunitionModuleOfType(type, modules).toArray(new AmmunitionModule[0]);
        modules = findAmmunitionModuleOfFillingFluis(fluidTag, modules).toArray(new AmmunitionModule[0]);
        if(modules.length <= 0){
            return null;
        }
        return modules[0];
    }

    public static List<AmmunitionModule> findPresetOfSize(AmmunitionSize size){
        return findAmmunitionModuleOfSize(size, presetModules);
    }

    public static AmmunitionModule[] findPresetOfSizeArray(AmmunitionSize size){
        return findPresetOfSizeArray(size, -1);
    }

    public static AmmunitionModule[] findPresetOfSizeArray(AmmunitionSize size, int returnCount){
        if(returnCount <= 0){
            return findAmmunitionModuleOfSize(size, presetModules).toArray(new AmmunitionModule[0]);
        } else {
            return findAmmunitionModuleOfSize(size, presetModules).subList(0, returnCount).toArray(new AmmunitionModule[0]);
        }
    }

    public static @Nullable AmmunitionModule getPreset(int index){
        return presetModules[index];
    }

    public static AmmunitionModule[] getSmallCartridgeModules(){
        return new AmmunitionModule[]{presetModules[0], presetModules[1]};
    }

    public static AmmunitionModule[] getMediumCartridgeModules(TagKey<Fluid> casingFluidTag){
        return new AmmunitionModule[]{
                presetModules[2],
                presetModules[casingFluidTag.equals(ModTags.moltenIronTag()) ? 4 : 3]
        };
    }

    public static AmmunitionModule[] getMediumCartridgeModules(){
        return getMediumCartridgeModules(ModTags.moltenBrassesTag());
    }

    public static AmmunitionModule[] getLongCartridgeModules(TagKey<Fluid> casingFluidTag){
        return new AmmunitionModule[]{
                presetModules[5],
                presetModules[casingFluidTag.equals(ModTags.moltenIronTag()) ? 7 : 6]
        };
    }

    public static AmmunitionModule[] getLongCartridgeModules(){
        return getMediumCartridgeModules(ModTags.moltenBrassesTag());
    }

    public static AmmunitionModule[] getShellCartridgeModules(){
        return new AmmunitionModule[]{presetModules[8], presetModules[9]};
    }
}
