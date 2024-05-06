package cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.weapon;

import net.minecraft.world.item.Item;

public class AssaultRifleBase extends WeaponBase{
    public AssaultRifleBase(String coreId, Item.Properties properties) {
        super(coreId, properties);

    }

    public class WeaponModules {
        public boolean hasLowerReceiver = false;
        public boolean hasUpperReceiver = false;
        public boolean hasHandguard = false;
        public boolean hasStock = false;
        public boolean isBullpup = false;
        public boolean hasMagazine = false;
        public boolean hasBarrel = false;
        public boolean hasGrip = false;
        public WeaponModules(boolean lr, boolean ur, boolean h, boolean s, boolean i, boolean m, boolean b, boolean g){
            hasLowerReceiver = lr;
            hasUpperReceiver = ur;
            hasHandguard = h;
            hasStock = s;

        }
    }
}
