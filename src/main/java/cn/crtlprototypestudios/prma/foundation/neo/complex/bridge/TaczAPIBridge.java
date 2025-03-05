package cn.crtlprototypestudios.prma.foundation.neo.complex.bridge;

import cn.crtlprototypestudios.prma.PreciseManufacturing;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Set;
import java.util.stream.Collectors;

public class TaczAPIBridge {
    public static ItemStack getAmmo(String ammoId) {
        ItemStack stack = new ItemStack(new Item(new Item.Properties()).setRegistryName("tacz","ammo"));
        stack.setCount(1);
        CompoundTag tag = new CompoundTag();
        tag.putString("AmmoId", String.format("tacz:%s", ammoId));
        stack.setTag(tag);
        return stack;
    }
}
