package cn.crtlprototypestudios.precisemanufacturing.foundation.util;

import cn.crtlprototypestudios.precisemanufacturing.util.Reference;
import net.minecraft.resources.ResourceLocation;

import java.util.Hashtable;

public class ResourceHelper {
    private static Hashtable<String, ResourceLocation> table = new Hashtable<>();

    public static ResourceLocation find(String filePath){
        if(table.containsKey(filePath))
            return table.get(filePath);

        ResourceLocation loc = new ResourceLocation(Reference.MOD_ID, filePath);
        table.put(filePath, loc);
        return loc;
    }

    public static ResourceLocation findAndRegisterItemModel(String filePath){
        ResourceLocation result = find(filePath);

        return result;
    }
}
