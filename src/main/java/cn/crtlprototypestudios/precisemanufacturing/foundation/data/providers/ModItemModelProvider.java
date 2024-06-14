package cn.crtlprototypestudios.precisemanufacturing.foundation.data.providers;

import cn.crtlprototypestudios.precisemanufacturing.foundation.ModItems;
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
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.ArrayList;
import java.util.List;

public class ModItemModelProvider extends ItemModelProvider {
    public static List<String> storedItemResourceLocations = new ArrayList<>();
    public static List<Item> storedItemLocations = new ArrayList<>();

    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Reference.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        simpleItem(ModItems.RAW_ZINC_POWDER.get());
        simpleItem(ModItems.RAW_COPPER_POWDER.get());
        simpleItem(ModItems.BASALT_POWDER.get());
        simpleItem(ModItems.RAW_SULFUR_POWDER.get());
        simpleItem(ModItems.GUNPOWDER_PELLETS.get());
        simpleItem(ModItems.CRUSHED_BASALT.get());
        simpleItem(ModItems.UNFORMED_BASALT.get());
        simpleItem(ModItems.STRAIGHT_SMALL_COIL.get());
        simpleItem(ModItems.STRAIGHT_LARGE_COIL.get());
        simpleItem(ModItems.STRAIGHT_FLAT_COIL.get());
        simpleItem(ModItems.LOCKING_RETURN_COIL.get());
        simpleItem(ModItems.FLAT_HEAD_SCREW.get());
        simpleItem(ModItems.M_SCREW.get());
        simpleItem(ModItems.THIN_SMALL_ROD.get());
        simpleItem(ModItems.THICK_SMALL_ROD.get());

        for(int i = 0; i < storedItemResourceLocations.size(); i++) {
            customSimpleItem(storedItemLocations.get(i), storedItemResourceLocations.get(i));
        }
    }

    private ItemModelBuilder simpleItem(Item item) {
        return withExistingParent(item.toString(),
                new ResourceLocation("item/generated")).texture("layer0",
                ResourceHelper.find("item/" + item.toString()));
    }

    private ItemModelBuilder handheldItem(Item item) {
        return withExistingParent(item.toString(),
                new ResourceLocation("item/handheld")).texture("layer0",
                ResourceHelper.find("item/" + item.toString()));
    }

    private ItemModelBuilder customSimpleItem(Item item, String... folders) {
        StringBuilder path = new StringBuilder("item");
        for (String string : folders)
            path.append("/").append("_".equals(string) ? item.toString() : string);
        return withExistingParent(item.toString(),
                new ResourceLocation("item/generated")).texture("layer0",
                ResourceHelper.find(path.toString()));
    }

    private ItemModelBuilder modelBuilder(String path, Item item){
        return withExistingParent(item.toString(),
                new ResourceLocation("item/generated")).texture("layer0",
                ResourceHelper.find(path));
    }

    private ItemModelBuilder modelBuilder(ResourceLocation path, Item item){
        return withExistingParent(item.toString(),
                new ResourceLocation("item/generated")).texture("layer0",
                path);
    }

    public static <I extends Item> NonNullBiConsumer<DataGenContext<Item, I>, RegistrateItemModelProvider> genericItemModel(boolean generateModel, String... folders) {
        return (c, p) -> {
            String path = "item";
            for (String string : folders)
                path += "/" + ("_".equals(string) ? c.getName() : string);
            storedItemResourceLocations.add(path);
            storedItemLocations.add(c.getEntry());
            if(generateModel)
                p.generated(c::getEntry, ResourceHelper.find(path));
            else
                p.withExistingParent(c.getName(), p.modLoc(path));
        };
    }

    public static <I extends Item> NonNullBiConsumer<DataGenContext<Item, I>, RegistrateItemModelProvider> genericItemModel(String... folders) {
        return (c, p) -> {
            String path = "item";
            for (String string : folders)
                path += "/" + ("_".equals(string) ? c.getName() : string);
            p.withExistingParent(c.getName(), new ResourceLocation("item/generated")).texture("layer0",
                    ResourceHelper.find(path));
        };
    }

    public static <I extends Item> NonNullBiConsumer<DataGenContext<Item, I>, RegistrateItemModelProvider> genericExistingParentedItemModel(String... folders) {
        return (c, p) -> {
            String path = "item";
            for (String string : folders)
                path += "/" + ("_".equals(string) ? c.getName() : string);
            p.withExistingParent(c.getName(), p.modLoc(path));
        };
    }

    public static <I extends Item> NonNullBiConsumer<DataGenContext<Item, I>, RegistrateItemModelProvider> genericItemModel(boolean generateModel, String[] modelFolders, String[] texturesFolders) {
        return (c, p) -> {
            String modelPath = "item", texturePath = "item";
            for (String string : modelFolders)
                modelPath += "/" + ("_".equals(string) ? c.getName() : string);
            for (String string : texturesFolders)
                texturePath += "/" + ("_".equals(string) ? c.getName() : string);
            storedItemResourceLocations.add(modelPath);
            storedItemLocations.add(c.getEntry());
            if(generateModel)
                p.withExistingParent(c.getName(), new ResourceLocation("item/generated")).texture("layer0",
                        ResourceHelper.find(texturePath));
            else
                p.withExistingParent(c.getName(), p.modLoc(modelPath));
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
