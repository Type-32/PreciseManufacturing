package cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.ammunition;

import cn.crtlprototypestudios.precisemanufacturing.Main;
import cn.crtlprototypestudios.precisemanufacturing.foundation.ModCreativeModTabs;
import cn.crtlprototypestudios.precisemanufacturing.foundation.ModItems;
import cn.crtlprototypestudios.precisemanufacturing.foundation.ModTags;
import cn.crtlprototypestudios.precisemanufacturing.foundation.data.generators.recipe.ModDecomponentalizingRecipesGen;
import cn.crtlprototypestudios.precisemanufacturing.foundation.data.providers.ModItemModelProvider;
import cn.crtlprototypestudios.precisemanufacturing.foundation.data.providers.ModRecipeProvider;
import cn.crtlprototypestudios.precisemanufacturing.foundation.util.ResourceHelper;
import com.simibubi.create.AllItems;
import com.simibubi.create.content.kinetics.mixer.CompactingRecipe;
import com.simibubi.create.content.kinetics.press.PressingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static com.tterrag.registrate.providers.RegistrateRecipeProvider.inventoryTrigger;

public class CartridgeBase extends AmmunitionBase {
    public final RegistryEntry<Item> cartridgeBlueprint;
    public final AmmunitionModule[] ammunitionModules;
    public final AmmunitionSize categorizingSize;

    /**
     * Outdated Documentation. TODO Need to update documentation.
     */
    protected CartridgeBase(String coreId, AmmunitionSize categorizingSize, RegistryEntry<Item> blueprint, AmmunitionModule... ammunitionModules) {
        super(coreId);

        this.cartridgeBlueprint = blueprint;

        this.ammunitionModules = ammunitionModules;

        this.categorizingSize = categorizingSize;

        ModRecipeProvider.addCartridgeBase(this);
    }

    //TODO Update Documentation
    /**
     *
     */
    public static CartridgeBase register(String id, AmmunitionSize categorizingSize, AmmunitionModule... ammunitionModules) {
        ItemStack ammoStack = new ItemStack(com.tacz.guns.init.ModItems.AMMO.get());
        CompoundTag itemTag = new CompoundTag();
        itemTag.putString("AmmoId", "tacz:" + id);
        ammoStack.setTag(itemTag);

        RegistryEntry<Item> cartridgeBlueprint = Main.REGISTRATE.item(id + "_blueprint", Item::new)
                .model(ModItemModelProvider.genericItemModel(true, "cartridge_blueprint", id + "_blueprint"))
                .tag(ModTags.cartridgeBlueprintTag())
                .tab(ModCreativeModTabs.MOD_BLUEPRINTS_TAB.getKey())
                .register();
        ModItems.addToList(cartridgeBlueprint, ModCreativeModTabs.Tabs.Blueprints);

        List<RegistryEntry<Item>> mainItems = new ArrayList<>();
        for(AmmunitionModule m : ammunitionModules) {
            RegistryEntry<Item> blueprint = AmmunitionRegistryManager.blueprints.get(m);
            RegistryEntry<Item> cast = AmmunitionRegistryManager.casts.get(m);
            RegistryEntry<Item> main = AmmunitionRegistryManager.items.get(m);

            ModDecomponentalizingRecipesGen.add(ammoStack, blueprint.get(), 400);
            ModRecipeProvider.add(ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, cast.get(), 1).requires(Items.IRON_INGOT).requires(blueprint.get()));

            mainItems.add(main);
        }

        ProcessingRecipeBuilder<PressingRecipe> pressingRecipe = new ProcessingRecipeBuilder<>(PressingRecipe::new, ResourceHelper.find("pressing/cartridges/" + id))
                .output(ammoStack)
                .require(
                        categorizingSize == AmmunitionSize.SMALL ? ModItems.SMALL_AMMUNITION_GUNPOWDER.get() :
                        categorizingSize == AmmunitionSize.MEDIUM ? ModItems.MEDIUM_AMMUNITION_GUNPOWDER.get() :
                        categorizingSize == AmmunitionSize.LONG ? ModItems.LONG_AMMUNITION_GUNPOWDER.get() :
                        Items.GUNPOWDER
                );

        for(RegistryEntry<Item> m : mainItems) {
            pressingRecipe.require(m.get());
        }

        ModRecipeProvider.addCreateRecipeBuilder(pressingRecipe);

        return new CartridgeBase(id, categorizingSize, cartridgeBlueprint, ammunitionModules);
    }
}
