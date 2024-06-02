package cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.ammunition;

import cn.crtlprototypestudios.precisemanufacturing.Main;
import cn.crtlprototypestudios.precisemanufacturing.foundation.ModCreativeModTabs;
import cn.crtlprototypestudios.precisemanufacturing.foundation.ModFluids;
import cn.crtlprototypestudios.precisemanufacturing.foundation.ModItems;
import cn.crtlprototypestudios.precisemanufacturing.foundation.data.generators.recipe.ModDecomponentalizingRecipesGen;
import cn.crtlprototypestudios.precisemanufacturing.foundation.data.providers.ModItemModelProvider;
import cn.crtlprototypestudios.precisemanufacturing.foundation.data.providers.ModRecipeProvider;
import cn.crtlprototypestudios.precisemanufacturing.foundation.util.ResourceHelper;
import com.simibubi.create.AllItems;
import com.simibubi.create.content.fluids.transfer.FillingRecipe;
import com.simibubi.create.content.kinetics.deployer.DeployerApplicationRecipe;
import com.simibubi.create.content.kinetics.press.PressingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.simibubi.create.content.processing.sequenced.SequencedAssemblyRecipeBuilder;
import com.simibubi.create.foundation.data.recipe.SequencedAssemblyRecipeGen;
import com.simibubi.create.foundation.utility.RegisteredObjects;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.Registry;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.function.Consumer;

import static com.tterrag.registrate.providers.RegistrateRecipeProvider.inventoryTrigger;

public class CartridgeBase extends AmmunitionBase {
    public Hashtable<CartridgeModule, RegistryEntry<Item>> registry = new Hashtable<>();
    public Hashtable<CartridgeModule, RegistryEntry<Item>> blueprintsRegistry = new Hashtable<>();
    public Hashtable<CartridgeModule, RegistryEntry<Item>> castsRegistry = new Hashtable<>();
    public CartridgeModuleBuilder givenModuleBuilder;

    // To future me: Do not delete, nor add the UNFINISHED module. Cartridge Autonomous Registrations
    // works differently than Rifle Registrations. Rifle Modules has unfinished item variants for each module,
    // while Cartridge Modules doesn't and only requires one unfinished item variant to represent the whole
    // cartridge item during assembly sequences.
    public final static CartridgeModuleBuilder
            STANDARD_CARTRIDGE = new CartridgeModuleBuilder(
                    new CartridgeModule[]{
                            CartridgeModule.CASING_MODULE,
                            CartridgeModule.HEAD_MODULE
                    },
                    new CartridgeAssemblySequence[]{
                            CartridgeAssemblySequence.NUGGET,
                            CartridgeAssemblySequence.GUNPOWDER,
                            CartridgeAssemblySequence.HEAD,
                            CartridgeAssemblySequence.PRESSING
                    }
            ),
            SHOTGUN_CARTRIDGE = new CartridgeModuleBuilder(
                    new CartridgeModule[]{
                            CartridgeModule.CASING_MODULE,
                            CartridgeModule.PELLET_MODULE
                    },
                    new CartridgeAssemblySequence[]{
                            CartridgeAssemblySequence.NUGGET,
                            CartridgeAssemblySequence.GUNPOWDER_PELLET,
                            CartridgeAssemblySequence.GUNPOWDER,
                            CartridgeAssemblySequence.SHOTGUN_PELLETS,
                            CartridgeAssemblySequence.PRESSING
                    }
            ),
            ROCKET_CARTRIDGE = new CartridgeModuleBuilder(
                    new CartridgeModule[]{
                            CartridgeModule.CASING_MODULE.setData(d -> d
                                    .setFillingFluid(ModFluids.MOLTEN_BASALT_INFUSED_IRON)
                                    .setFillingAmount(150)),
                            CartridgeModule.HEAD_MODULE.setData(d -> d
                                    .setFillingFluid(ModFluids.MOLTEN_BASALT_INFUSED_IRON)
                                    .setFillingAmount(100))
                    },
                    new CartridgeAssemblySequence[]{
                            CartridgeAssemblySequence.GUNPOWDER_PELLET,
                            CartridgeAssemblySequence.GUNPOWDER_PELLET,
                            CartridgeAssemblySequence.PRESSING,
                            CartridgeAssemblySequence.HEAD,
                            CartridgeAssemblySequence.GUNPOWDER,
                            CartridgeAssemblySequence.GUNPOWDER,
                            CartridgeAssemblySequence.PRESSING
                    }
            );


    /**
     * The Main Constructor that registers the cartridge module items that are recorded in <code>moduleBuilder</code>.
     * @param coreId The cartridge id, i.e. "9mm"
     * @param moduleBuilder The cartridge module builder, a builder utility class that holds a list of modules for initialization.
     * @implNote When a <code>CartridgeBase</code> object is created, the constructor method registers all the given modules
     * in the module builder into items. For example, when the constructor is invoked with <code>coreId = "9mm"</code>
     * and <code>moduleBuilder = CartridgeBase.STANDARD_CARTRIDGE</code>, the following item registry entries are created:
     * <ul>
     *     <li><code>prma:9mm_casing</code></li>
     *     <li><code>prma:9mm_casing_cast</code></li>
     *     <li><code>prma:9mm_head</code></li>
     *     <li><code>prma:9mm_head_cast</code></li>
     *     <li><code>prma:9mm_unfinished</code></li>
     * </ul>
     *
     * The corresponding item model resources generated are these:
     * <ul>
     *     <li><code>prma:item/cartridges/9mm/9mm_casing</code></li>
     *     <li><code>prma:item/cartridges/9mm/9mm_casing_cast</code></li>
     *     <li><code>prma:item/cartridges/9mm/9mm_head</code></li>
     *     <li><code>prma:item/cartridges/9mm/9mm_head_cast</code></li>
     *     <li><code>prma:item/cartridges/9mm/9mm_unfinished</code></li>
     * </ul>
     */
    public CartridgeBase(String coreId, CartridgeModuleBuilder moduleBuilder) {
        super(coreId);
        givenModuleBuilder = moduleBuilder;

        for (CartridgeModule type : moduleBuilder.get()) {
            registry.put(type, registerModule(coreId, type));
        }

        assert ModCreativeModTabs.MOD_COMPONENTS_TAB.getKey() != null;
        registry.put(CartridgeModule.UNFINISHED_MODULE,
                Main.REGISTRATE.item(coreId + "_unfinished", Item::new)
                        .model(ModItemModelProvider.genericItemModel(true, "cartridges", coreId, coreId + "_unfinished"))
                        .tab(ModCreativeModTabs.MOD_COMPONENTS_TAB.getKey())
                        .register()
        );

        ModRecipeProvider.addCartridgeBase(this);
    }

    /**
     * The Main Constructor that registers the cartridge module items that are recorded in <code>moduleBuilder</code>.
     * @param coreId The cartridge id, i.e. "9mm"
     */
    public CartridgeBase(String coreId) {
        this(coreId, STANDARD_CARTRIDGE);
    }

    /**
     * The <code>registerModule()</code> function registers the Cartridge Assembly Module Items.
     *
     * @param id The id of the cartridge module's assembled cartridge, i.e. "9mm"
     * @param module The module id of the cartridge's module, i.e. "casing_cast"
     * @return A registry entry of the cartridge module item.
     *
     * @implNote The corresponding item, if the <code>id = "9mm"</code> and <code>moduleId = "casing_cast"</code>,
     * this item entry will be generated:
     * <ul>
     *     <li><code>prma:9mm_casing_cast</code></li>
     * </ul>
     * <br>
     * The item entry will have the item model resource location of:
     * <ul>
     *     <li><code>prma:item/cartridges/9mm/9mm_casing_cast</code></li>
     * </ul>
     */
    private RegistryEntry<Item> registerModule(String id, CartridgeModule module) {
        String name = String.format("%s_%s", id, module.toString());

        assert ModCreativeModTabs.MOD_CASTS_TAB.getKey() != null;
        assert ModCreativeModTabs.MOD_COMPONENTS_TAB.getKey() != null;
        assert ModCreativeModTabs.MOD_BLUEPRINTS_TAB.getKey() != null;
        RegistryEntry<Item>
                blueprintModule = Main.REGISTRATE.item(name + "_blueprint", Item::new)
                        .model(ModItemModelProvider.genericItemModel(true, "cartridges", String.format("general_%s_blueprint", module)))
                        .tab(ModCreativeModTabs.MOD_BLUEPRINTS_TAB.getKey())
                        .register(),
                castModule = Main.REGISTRATE.item(name + "_cast", Item::new)
                        .model(ModItemModelProvider.genericItemModel(true, "cartridges", String.format("general_%s_cast", module)))
                        .tab(ModCreativeModTabs.MOD_CASTS_TAB.getKey())
                        .register(),
                mainModule = Main.REGISTRATE.item(name, Item::new)
                        .model(ModItemModelProvider.genericItemModel(true, "cartridges", id, name))
                        .tab(ModCreativeModTabs.MOD_COMPONENTS_TAB.getKey())
                        .register();

        blueprintsRegistry.put(module, blueprintModule);

        castsRegistry.put(module, castModule);

        ModItems.addToList(mainModule, ModCreativeModTabs.Tabs.Components);
        ModItems.addToList(blueprintModule, ModCreativeModTabs.Tabs.Blueprints);
        ModItems.addToList(castModule, ModCreativeModTabs.Tabs.Casts);

        return mainModule;
    }

    public void registerRecipes(){

        // TODO Add proper impl. here after TacZ Lib is published or sth
        ItemStack ammoStack = new ItemStack(ModItems.AMMO_PLACEHOLDER.get());
        CompoundTag itemTag = new CompoundTag();
        itemTag.putString("AmmoId", "tacz:" + getCoreId());
        ammoStack.setTag(itemTag);


        for(CartridgeModule m : givenModuleBuilder.get()){
            String name = String.format("%s_%s", getCoreId(), m.toString());

            RegistryEntry<Item>
                    castModule = castsRegistry.get(m),
                    mainModule = registry.get(m),
                    blueprintModule = blueprintsRegistry.get(m);

            ModRecipeProvider.add(ShapedRecipeBuilder
                    .shaped(RecipeCategory.MISC, castModule.get())
                    .unlockedBy(String.format("has_%s_blueprint", name), inventoryTrigger(ItemPredicate.Builder.item().of(blueprintModule.get()).build()))
                    .pattern("PIP")
                    .pattern("IBI")
                    .pattern("PIP")
                    .define('P', AllItems.IRON_SHEET.get())
                    .define('I', Items.IRON_INGOT)
                    .define('B', blueprintModule.get()));

            ModDecomponentalizingRecipesGen.add(ammoStack, blueprintModule.get(), 400);

            ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<FillingRecipe>(FillingRecipe::new, ResourceHelper.find(String.format("cartridges/%s/%s", getCoreId(), name)))
                    .require(castModule.get())
                    .require(m.getData().getFillingFluid().get(), m.getData().getFillingAmount())
                    .output(mainModule.get()));
        }

        RegistryEntry<Item> unfinishedModule = registry.get(CartridgeModule.UNFINISHED_MODULE);
        SequencedAssemblyRecipeBuilder builder = new SequencedAssemblyRecipeBuilder(ResourceHelper.find(String.format("cartridges/%s", getCoreId())))
                .require(registry.get(getModuleByType(CartridgeModuleType.CASING)).get())
                .transitionTo(unfinishedModule.get())
                .loops(1);

        for(CartridgeAssemblySequence seq : givenModuleBuilder.getAssemblySequence()){
            if(seq == CartridgeAssemblySequence.NUGGET)
                builder.addStep(DeployerApplicationRecipe::new, b -> b.require(Items.IRON_NUGGET));
            else if(seq == CartridgeAssemblySequence.HEAD)
                builder.addStep(DeployerApplicationRecipe::new, b -> b.require(registry.get(getModuleByType(CartridgeModuleType.HEAD)).get()));
            else if(seq == CartridgeAssemblySequence.PRESSING)
                builder.addStep(PressingRecipe::new, b -> b);
            else if(seq == CartridgeAssemblySequence.GUNPOWDER)
                builder.addStep(DeployerApplicationRecipe::new, b -> b.require(Items.GUNPOWDER));
            else if(seq == CartridgeAssemblySequence.GUNPOWDER_PELLET)
                builder.addStep(DeployerApplicationRecipe::new, b -> b.require(ModItems.GUNPOWDER_PELLETS.get()));
            else if(seq == CartridgeAssemblySequence.SHOTGUN_PELLETS)
                builder.addStep(DeployerApplicationRecipe::new, b -> b.require(registry.get(getModuleByType(CartridgeModuleType.PELLET)).get()));
        }

        ModRecipeProvider.addSequencedAssemblyBuilder(builder.addOutput(ammoStack, 94).addOutput(registry.get(getModuleByType(CartridgeModuleType.CASING)).get(), 6));
    }

    @Nullable
    private CartridgeModule getModuleByType(CartridgeModuleType type){
        for(CartridgeModule m : givenModuleBuilder.get()){
            if(m.getType() == type)
                return m;
        }
        return null;
    }

    public CartridgeBase setModuleData(int index, Consumer<CartridgeModule.Data> dataConsumer) {
        List<CartridgeModule> modules = new ArrayList<>(registry.keySet());
        CartridgeModule module = modules.get(index);

        if (module != null) {
            CartridgeModule updatedModule = module.setData(dataConsumer);
            registry.put(updatedModule, registry.get(module));
        }

        return this;
    }
}
