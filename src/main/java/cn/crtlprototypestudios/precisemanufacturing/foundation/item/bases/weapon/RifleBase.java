package cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.weapon;

import cn.crtlprototypestudios.precisemanufacturing.Main;
import cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.weapon.util.RifleModuleBuilder;
import cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.weapon.util.RifleModuleType;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.item.Item;

import java.util.Hashtable;

public class RifleBase extends WeaponBase {
    public static final RifleModuleBuilder
            STANDARD_RIFLE_MODULES = new RifleModuleBuilder(
                    RifleModuleType.GRIP,
                    RifleModuleType.LOWER_RECEIVER,
                    RifleModuleType.UPPER_RECEIVER,
                    RifleModuleType.HANDGUARD,
                    RifleModuleType.BARREL,
                    RifleModuleType.MAGAZINE,
                    RifleModuleType.FIRE_CONTROL_GROUP,
                    RifleModuleType.FIRE_SELECTOR,
                    RifleModuleType.TRIGGER,
                    RifleModuleType.STOCK
            ),
            BULLPUP_RIFLE_MODULES = new RifleModuleBuilder(
                    RifleModuleType.GRIP,
                    RifleModuleType.BULLPUP_BODY,
                    RifleModuleType.MAGAZINE,
                    RifleModuleType.FIRE_CONTROL_GROUP,
                    RifleModuleType.GRIP,
                    RifleModuleType.TRIGGER,
                    RifleModuleType.BULLPUP_BODY
            );


    public Hashtable<RifleModuleType, RegistryEntry<Item>> registry = new Hashtable<>();

    public RifleBase(String coreId, Item.Properties properties, RifleModuleBuilder moduleBuilder) {
        super(coreId, properties.stacksTo(1));

        for (RifleModuleType i : moduleBuilder.get()) {
            registry.put(i, registerModule(coreId, i.toString(), properties));
        }
    }

    public RifleBase(String coreId, RifleModuleBuilder modules) {
        this(coreId, new Item.Properties(), modules);
    }

    public RifleBase(String coreId, RifleModuleType... modules) {
        this(coreId, new RifleModuleBuilder(modules));
    }

    private RegistryEntry<Item> registerModule(String id, String moduleId, Item.Properties properties) {
        return Main.REGISTRATE.item(String.format("%s_%s", id, moduleId), Item::new).properties(p -> properties).register();
    }
}
