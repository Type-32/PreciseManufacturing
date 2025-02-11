package cn.crtlprototypestudios.prma.foundation.neo.item.collection;

import cn.crtlprototypestudios.prma.Main;
import cn.crtlprototypestudios.prma.foundation.ModTags;
import cn.crtlprototypestudios.prma.foundation.data.providers.ModItemModelProvider;
import cn.crtlprototypestudios.prma.foundation.neo.item.type.AmmoMaterialType;
import cn.crtlprototypestudios.prma.foundation.neo.item.type.AmmoSizeType;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.item.Item;

import java.util.Map;

public class AmmoCartridgeComponents {
    protected AmmoMaterialType[] materialTypes;
    protected AmmoSizeType sizeType;
    protected Map<AmmoMaterialType, RegistryEntry<Item>> casings, heads;

    public AmmoCartridgeComponents(AmmoSizeType sizeType, AmmoMaterialType... materialTypes) {
        this.materialTypes = materialTypes;
        this.sizeType = sizeType;
    }

    public AmmoCartridgeComponents build(){

        for (var mat : materialTypes) {
            var casing = Main.REGISTRATE.item(String.format("%s_%s_%s", sizeType, mat, "casing"), Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "ammo", "casing", mat.toString(), "_"))
                    .tag(ModTags.materialsTag(), ModTags.ammunitionComponentsTag());

            var head = Main.REGISTRATE.item(String.format("%s_%s_%s", sizeType, mat, "head"), Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "ammo", "head", mat.toString(), "_"))
                    .tag(ModTags.materialsTag(), ModTags.ammunitionComponentsTag());

            switch (sizeType) {
                case Small -> {
                    casing.tag(ModTags.smallAmmunitionComponentsTag());
                    head.tag(ModTags.smallAmmunitionComponentsTag());
                }
                case Medium -> {
                    casing.tag(ModTags.mediumAmmunitionComponentsTag());
                    head.tag(ModTags.mediumAmmunitionComponentsTag());
                }
                case Long -> {
                    casing.tag(ModTags.longAmmunitionComponentsTag());
                    head.tag(ModTags.longAmmunitionComponentsTag());
                }
            }

            switch (mat) {
                case Brass -> {
                    casing.tag(ModTags.brassAmmunitionComponentsTag());
                    head.tag(ModTags.brassAmmunitionComponentsTag());
                }
                case Copper -> {
                    casing.tag(ModTags.copperAmmunitionComponentsTag());
                    head.tag(ModTags.copperAmmunitionComponentsTag());
                }
                case Iron -> {
                    casing.tag(ModTags.ironAmmunitionComponentsTag());
                    head.tag(ModTags.ironAmmunitionComponentsTag());
                }
            }

            this.heads.put(mat, head.register());
            this.casings.put(mat, casing.register());
        }

        return this;
    }

    public RegistryEntry<Item> getCasing(AmmoMaterialType type) {
        return casings.get(type);
    }

    public RegistryEntry<Item> getHead(AmmoMaterialType type) {
        return heads.get(type);
    }
}
