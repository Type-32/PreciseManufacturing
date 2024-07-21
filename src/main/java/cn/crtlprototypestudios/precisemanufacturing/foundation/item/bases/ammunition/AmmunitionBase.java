package cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.ammunition;

import cn.crtlprototypestudios.precisemanufacturing.foundation.ModTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class AmmunitionBase {
    private final String coreId;
    private static final List<AmmunitionModule> presetModules = new ArrayList<>(List.of(
            AmmunitionRegistryManager.register(new AmmunitionModule(AmmunitionSize.SMALL, AmmunitionMaterialType.HEAD, ModTags.moltenCoppersTag(), 100, 80, 4)), // small_copper_head_component, 0
            AmmunitionRegistryManager.register(new AmmunitionModule(AmmunitionSize.SMALL, AmmunitionMaterialType.CASING, ModTags.moltenBrassesTag(), 300, 120, 3)), // small_brass_casing_component, 1
            AmmunitionRegistryManager.register(new AmmunitionModule(AmmunitionSize.MEDIUM, AmmunitionMaterialType.HEAD, ModTags.moltenCoppersTag(), 200, 200, 5)), // medium_copper_head_component, 2
            AmmunitionRegistryManager.register(new AmmunitionModule(AmmunitionSize.MEDIUM, AmmunitionMaterialType.CASING, ModTags.moltenBrassesTag(), 500, 300, 5)), // medium_brass_casing_component, 3
            AmmunitionRegistryManager.register(new AmmunitionModule(AmmunitionSize.MEDIUM, AmmunitionMaterialType.CASING, ModTags.moltenIronsTag(), 600, 300, 5)), // medium_iron_casing_component, 4
            AmmunitionRegistryManager.register(new AmmunitionModule(AmmunitionSize.LONG, AmmunitionMaterialType.HEAD, ModTags.moltenCoppersTag(), 300, 180, 3)), // long_copper_head_component, 5
            AmmunitionRegistryManager.register(new AmmunitionModule(AmmunitionSize.LONG, AmmunitionMaterialType.CASING, ModTags.moltenBrassesTag(), 500, 350, 5)), // long_brass_casing_component, 6
            AmmunitionRegistryManager.register(new AmmunitionModule(AmmunitionSize.LONG, AmmunitionMaterialType.CASING, ModTags.moltenIronsTag(), 600, 350, 5)), // long_iron_casing_component, 7
            AmmunitionRegistryManager.register(new AmmunitionModule(AmmunitionSize.SHELL, AmmunitionMaterialType.PELLETS, ModTags.moltenIronsTag(), 60, 60, 6)), // shell_iron_pellets_component, 8
            AmmunitionRegistryManager.register(new AmmunitionModule(AmmunitionSize.SHELL, AmmunitionMaterialType.CASING, ModTags.moltenBrassesTag(), 200, 120, 4)) // shell_brass_casing_component, 9
    ));

    public AmmunitionBase(String coreId) {
        this.coreId = coreId;
    }

    public String getCoreId() {
        return coreId;
    }

    public static List<AmmunitionModule> findAmmunitionModuleOfSize(AmmunitionSize size, List<AmmunitionModule> source){
        List<AmmunitionModule> modules = new ArrayList<>();
        for(AmmunitionModule module : source){
            if(module.getSize() == size){
                modules.add(module);
            }
        }
        return modules;
    }

    public static List<AmmunitionModule> findAmmunitionModuleOfType(AmmunitionMaterialType type, List<AmmunitionModule> source){
        List<AmmunitionModule> modules = new ArrayList<>();
        for(AmmunitionModule module : source){
            if(module.getType() == type){
                modules.add(module);
            }
        }
        return modules;
    }

    public static List<AmmunitionModule> findAmmunitionModuleOfFillingFluis(TagKey<Fluid> fluid, List<AmmunitionModule> source){
        List<AmmunitionModule> modules = new ArrayList<>();
        for(AmmunitionModule module : source){
            if(module.getFillingFluid().equals(fluid)){
                modules.add(module);
            }
        }
        return modules;
    }

    public static @Nullable AmmunitionModule findModule(AmmunitionSize size, AmmunitionMaterialType type, TagKey<Fluid> fluidTag){
        List<AmmunitionModule> modules = findAmmunitionModuleOfSize(size, presetModules);
        modules = findAmmunitionModuleOfType(type, modules);
        modules = findAmmunitionModuleOfFillingFluis(fluidTag, modules);
        if(modules.isEmpty()){
            return null;
        }
        return modules.get(0);
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
        return presetModules.get(index);
    }

    public static AmmunitionModule[] getSmallCartridgeModules(){
        return new AmmunitionModule[]{presetModules.get(0), presetModules.get(1)};
    }

    public static AmmunitionModule[] getMediumCartridgeModules(TagKey<Fluid> casingFluidTag){
        return new AmmunitionModule[]{
                presetModules.get(2),
                presetModules.get(casingFluidTag.equals(ModTags.moltenIronsTag()) ? 4 : 3)
        };
    }

    public static AmmunitionModule[] getMediumCartridgeModules(){
        return getMediumCartridgeModules(ModTags.moltenBrassesTag());
    }

    public static AmmunitionModule[] getLongCartridgeModules(TagKey<Fluid> casingFluidTag){
        return new AmmunitionModule[]{
                presetModules.get(5),
                presetModules.get(casingFluidTag.equals(ModTags.moltenIronsTag()) ? 7 : 6)
        };
    }

    public static AmmunitionModule[] getLongCartridgeModules(){
        return getMediumCartridgeModules(ModTags.moltenBrassesTag());
    }

    public static AmmunitionModule[] getShellCartridgeModules(){
        return new AmmunitionModule[]{presetModules.get(8), presetModules.get(9)};
    }
}
