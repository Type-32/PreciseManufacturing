package cn.crtlprototypestudios.prma.foundation.neo.content.item.collection;

import cn.crtlprototypestudios.prma.PreciseManufacturing;
import cn.crtlprototypestudios.prma.foundation.PrmaTags;
import cn.crtlprototypestudios.prma.foundation.data.providers.ModItemModelProvider;
import cn.crtlprototypestudios.prma.foundation.neo.content.item.type.AmmoMaterialType;
import cn.crtlprototypestudios.prma.foundation.neo.content.item.type.AmmoSizeType;
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
            var casing = PreciseManufacturing.REGISTRATE.item(String.format("%s_%s_%s", sizeType, mat, "casing"), Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "ammo", "casing", mat.toString(), "_"))
                    .tag(PrmaTags.materialsTag(), PrmaTags.ammunitionComponentsTag());

            var head = PreciseManufacturing.REGISTRATE.item(String.format("%s_%s_%s", sizeType, mat, "head"), Item::new)
                    .model(ModItemModelProvider.genericItemModel(true, "ammo", "head", mat.toString(), "_"))
                    .tag(PrmaTags.materialsTag(), PrmaTags.ammunitionComponentsTag());

            switch (sizeType) {
                case Small -> {
                    casing.tag(PrmaTags.smallAmmunitionComponentsTag());
                    head.tag(PrmaTags.smallAmmunitionComponentsTag());
                }
                case Medium -> {
                    casing.tag(PrmaTags.mediumAmmunitionComponentsTag());
                    head.tag(PrmaTags.mediumAmmunitionComponentsTag());
                }
                case Long -> {
                    casing.tag(PrmaTags.longAmmunitionComponentsTag());
                    head.tag(PrmaTags.longAmmunitionComponentsTag());
                }
            }

            switch (mat) {
                case Brass -> {
                    casing.tag(PrmaTags.brassAmmunitionComponentsTag());
                    head.tag(PrmaTags.brassAmmunitionComponentsTag());
                }
                case Copper -> {
                    casing.tag(PrmaTags.copperAmmunitionComponentsTag());
                    head.tag(PrmaTags.copperAmmunitionComponentsTag());
                }
                case Iron -> {
                    casing.tag(PrmaTags.ironAmmunitionComponentsTag());
                    head.tag(PrmaTags.ironAmmunitionComponentsTag());
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
