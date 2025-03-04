package cn.crtlprototypestudios.prma.foundation.neo.complex.bridge;

import cn.crtlprototypestudios.prma.PreciseManufacturing;
import com.tacz.guns.api.TimelessAPI;
import com.tacz.guns.api.item.builder.AmmoItemBuilder;
import com.tacz.guns.api.item.builder.GunItemBuilder;
import com.tacz.guns.init.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

import java.util.Set;
import java.util.stream.Collectors;

public class TaczAPIBridge {
    public static Set<ItemStack> getTaczGuns() {
        return TimelessAPI.getAllCommonGunIndex().stream().map(i -> GunItemBuilder.create()
                        .setAmmoCount(0)
                        .setCount(1)
                        .setAmmoInBarrel(false)
                        .setId(i.getKey()).build())
                .collect(Collectors.toSet());
    }

    public static Set<ItemStack> getTaczAmmo() {
        return TimelessAPI.getAllCommonAmmoIndex().stream().map(i -> {
                    PreciseManufacturing.LOGGER.debug(i.toString());
                    return AmmoItemBuilder.create()
                            .setCount(1)
                            .setId(i.getKey()).build();
                })
                .collect(Collectors.toSet());
//        ServerGamePacketListenerImplMixin
    }

    public static ItemStack getAmmo(String ammoId) {
        ItemStack stack = new ItemStack(ModItems.AMMO.get());
        stack.setCount(1);
        CompoundTag tag = new CompoundTag();
        tag.putString("AmmoId", String.format("tacz:%s", ammoId));
        stack.setTag(tag);
        return stack;
    }
}
