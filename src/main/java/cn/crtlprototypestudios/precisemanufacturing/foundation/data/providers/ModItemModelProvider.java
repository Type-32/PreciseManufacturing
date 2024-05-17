package cn.crtlprototypestudios.precisemanufacturing.foundation.data.providers;

import cn.crtlprototypestudios.precisemanufacturing.foundation.util.ResourceHelper;
import cn.crtlprototypestudios.precisemanufacturing.util.Reference;
import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateItemModelProvider;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Reference.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

    }

    private ItemModelBuilder simpleItem(Item item) {
        return withExistingParent(item.getRegistryName().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                ResourceHelper.find("item/" + item.getRegistryName().getPath()));
    }

    private ItemModelBuilder handheldItem(Item item) {
        return withExistingParent(item.getRegistryName().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                ResourceHelper.find("item/" + item.getRegistryName().getPath()));
    }

    private ItemModelBuilder rifleItem(Item item) {
        return withExistingParent(item.getRegistryName().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                ResourceHelper.find("item/" + item.getRegistryName().getPath()));
    }

    public static <I extends Item> NonNullBiConsumer<DataGenContext<Item, I>, RegistrateItemModelProvider> genericItemModel(String... folders) {
        return (c, p) -> {
            String path = "item";
            for (String string : folders)
                path += "/" + ("_".equals(string) ? c.getName() : string);
            p.withExistingParent(c.getName(), p.modLoc(path));
        };
    }

    public static <I extends Item> NonNullBiConsumer<DataGenContext<Item, I>, RegistrateItemModelProvider> genericBlockModel(String... folders) {
        return (c, p) -> {
            String path = "block";
            for (String string : folders)
                path += "/" + ("_".equals(string) ? c.getName() : string);
            p.withExistingParent(c.getName(), p.modLoc(path));
        };
    }
}
