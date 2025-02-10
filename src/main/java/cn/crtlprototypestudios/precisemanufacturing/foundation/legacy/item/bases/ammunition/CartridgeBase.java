package cn.crtlprototypestudios.precisemanufacturing.foundation.legacy.item.bases.ammunition;

import cn.crtlprototypestudios.precisemanufacturing.Main;
import cn.crtlprototypestudios.precisemanufacturing.foundation.ModCreativeModTabs;
import cn.crtlprototypestudios.precisemanufacturing.foundation.ModItems;
import cn.crtlprototypestudios.precisemanufacturing.foundation.ModTags;
import cn.crtlprototypestudios.precisemanufacturing.foundation.data.providers.ModItemModelProvider;
import cn.crtlprototypestudios.precisemanufacturing.foundation.data.providers.ModRecipeProvider;
import cn.crtlprototypestudios.precisemanufacturing.foundation.util.ResourceHelper;
import com.simibubi.create.content.kinetics.mixer.CompactingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.List;

public class CartridgeBase extends AmmunitionBase {
    public final RegistryEntry<Item> cartridgeBlueprint;
    public final AmmunitionModule[] ammunitionModules;
    public final AmmunitionSize categorizingSize;
    public final int requiredGunpowderCount;

    /**
     * Outdated Documentation. TODO Need to update documentation.
     */
    public CartridgeBase(String coreId, AmmunitionSize categorizingSize, int requiredGunpowderCount, AmmunitionModule... ammunitionModules) {
        super(coreId);

        RegistryEntry<Item> cartridgeBlueprint = Main.REGISTRATE.item(coreId + "_blueprint", Item::new)
                .model(ModItemModelProvider.genericItemModel(true, "cartridge_blueprint", coreId + "_blueprint"))
                .tag(ModTags.cartridgeBlueprintTag())
//                .tab(ModCreativeModTabs.MOD_BLUEPRINTS_TAB.getKey())
                .register();

//        ModItems.addToList(cartridgeBlueprint, ModCreativeModTabs.Tabs.Blueprints);

        this.cartridgeBlueprint = cartridgeBlueprint;

        this.ammunitionModules = ammunitionModules;

        this.categorizingSize = categorizingSize;

        this.requiredGunpowderCount = requiredGunpowderCount;

        ModRecipeProvider.addCartridgeBase(this);
    }

    public void registerRecipes(){
        ItemStack ammoStack = new ItemStack(com.tacz.guns.init.ModItems.AMMO.get());
        CompoundTag itemTag = new CompoundTag();
        itemTag.putString("AmmoId", "tacz:" + getCoreId());
        ammoStack.setTag(itemTag);

        List<RegistryEntry<Item>> mainItems = new ArrayList<>();
        for(AmmunitionModule m : ammunitionModules) {
            RegistryEntry<Item> blueprint = AmmunitionRegistryManager.blueprints.get(m);
//            RegistryEntry<Item> cast = AmmunitionRegistryManager.casts.get(m);
            RegistryEntry<Item> main = AmmunitionRegistryManager.items.get(m);

//            ModDecomponentalizingRecipesGen.add(ammoStack, blueprint.get(), 400);

            mainItems.add(main);
        }

        Item temoGunpowder = (categorizingSize == AmmunitionSize.SMALL ? ModItems.SMALL_AMMUNITION_GUNPOWDER.get() :
                                categorizingSize == AmmunitionSize.MEDIUM ? ModItems.MEDIUM_AMMUNITION_GUNPOWDER.get() :
                                categorizingSize == AmmunitionSize.LONG ? ModItems.LONG_AMMUNITION_GUNPOWDER.get() :
                                Items.GUNPOWDER);
        ProcessingRecipeBuilder<CompactingRecipe> pressingRecipe = new ProcessingRecipeBuilder<>(CompactingRecipe::new, ResourceHelper.find("cartridges/" + getCoreId()))
                .output(ammoStack).output(cartridgeBlueprint.get(), 1);

        for(int i = 0; i < requiredGunpowderCount; i++) {
            pressingRecipe.require(temoGunpowder);
        }

        if(categorizingSize == AmmunitionSize.SHELL){
            for(AmmunitionModule m : ammunitionModules) {
                if(m.getType() == AmmunitionMaterialType.PELLETS){
                    for(int i = 0; i < 5; i++) {
                        pressingRecipe.require(m.item.get());
                    }
                    break;
                }
            }
        }

        for(RegistryEntry<Item> m : mainItems) {
            pressingRecipe.require(m.get());
        }

        ModRecipeProvider.addCreateRecipeBuilder(pressingRecipe);
    }
}
