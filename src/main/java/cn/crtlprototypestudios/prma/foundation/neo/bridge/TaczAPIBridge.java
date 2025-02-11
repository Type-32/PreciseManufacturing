package cn.crtlprototypestudios.prma.foundation.neo.bridge;

import cn.crtlprototypestudios.prma.PreciseManufacturing;
import com.tacz.guns.api.TimelessAPI;
import com.tacz.guns.api.item.builder.AmmoItemBuilder;
import com.tacz.guns.api.item.builder.GunItemBuilder;
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
}
