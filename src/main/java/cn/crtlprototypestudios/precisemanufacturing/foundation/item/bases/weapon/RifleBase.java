package cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.weapon;

import cn.crtlprototypestudios.precisemanufacturing.Main;
import cn.crtlprototypestudios.precisemanufacturing.foundation.ModCreativeModTabs;
import cn.crtlprototypestudios.precisemanufacturing.foundation.ModFluids;
import cn.crtlprototypestudios.precisemanufacturing.foundation.data.generators.recipe.ModDecomponentalizingRecipesGen;
import cn.crtlprototypestudios.precisemanufacturing.foundation.data.providers.ModItemModelProvider;
import cn.crtlprototypestudios.precisemanufacturing.foundation.data.providers.ModRecipeProvider;
import cn.crtlprototypestudios.precisemanufacturing.foundation.util.ResourceHelper;
import com.simibubi.create.AllItems;
import com.simibubi.create.content.fluids.transfer.FillingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.Holder;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.function.Consumer;

import static com.tterrag.registrate.providers.RegistrateRecipeProvider.inventoryTrigger;

public class RifleBase extends WeaponBase {
    public static final RifleModuleBuilder
        STANDARD_RIFLE_MODULES = new RifleModuleBuilder(
                RifleModule.GRIP_MODULE,
                RifleModule.LOWER_RECEIVER_MODULE,
                RifleModule.UPPER_RECEIVER_MODULE,
                RifleModule.HANDGUARD_MODULE,
                RifleModule.BARREL_MODULE,
                RifleModule.MAGAZINE_MODULE,
                RifleModule.FIRE_CONTROL_GROUP_MODULE,
                RifleModule.FIRE_SELECTOR_MODULE,
                RifleModule.TRIGGER_MODULE,
                RifleModule.STOCK_MODULE
        ),
        BULLPUP_RIFLE_MODULES = new RifleModuleBuilder(
                RifleModule.GRIP_MODULE,
                RifleModule.BULLPUP_BODY_MODULE,
                RifleModule.MAGAZINE_MODULE,
                RifleModule.FIRE_CONTROL_GROUP_MODULE,
                RifleModule.TRIGGER_MODULE,
                RifleModule.FIRE_SELECTOR_MODULE

                // To Future me: Do not add an UNFINISHED type in the Rifle Module Types. Autonomous Rifle Module
                // registration works differently compared to Cartridge Autonomous registrations, as each Cartridge
                // uses only one Unfinished item variant to represent the modules of the Cartridge during the
                // sequenced assemblies, while Rifle Modules has an unfinished item variant for each rifle module,
                // as each module should only be crafted via a sequenced assembly.
        ),
        PUMP_ACTION_MODULES = new RifleModuleBuilder(
                RifleModule.RECEIVER_MODULE,
                RifleModule.SHELL_TUBE_MODULE,
                RifleModule.BARREL_MODULE,
                RifleModule.TRIGGER_MODULE,
                RifleModule.PUMP_MODULE,
                RifleModule.FIRE_CONTROL_GROUP_MODULE,
                RifleModule.TRIGGER_MODULE,
                RifleModule.STOCK_MODULE
        ),
        BOLT_ACTION_MODULES = new RifleModuleBuilder(
                RifleModule.BOLT_MODULE,
                RifleModule.BARREL_MODULE,
                RifleModule.LONG_BODY_MODULE,
                RifleModule.TRIGGER_MODULE,
                RifleModule.STOCK_MODULE,
                RifleModule.CARTRIDGE_WELL_MODULE,
                RifleModule.GRIP_MODULE
        );


    public Hashtable<RifleModuleType, RegistryEntry<Item>> registry = new Hashtable<>();
    public Hashtable<RifleModuleType, RegistryEntry<Item>> castsRegistry = new Hashtable<>();
    public Hashtable<RifleModuleType, RegistryEntry<Item>> blueprintsRegistry = new Hashtable<>();
    public RifleModuleBuilder givenModuleBuilder;

    public RifleBase(String coreId, Item.Properties properties, RifleModuleBuilder moduleBuilder) {
        super(coreId, properties.stacksTo(1));
        givenModuleBuilder = moduleBuilder;

        // Put all the registered modules in the Hashtable for later use
        for (RifleModule i : moduleBuilder.get()) {
            registry.put(i.getType(), registerModule(coreId, i, properties));
        }

        ModRecipeProvider.addRifleBase(this);
    }

    /**
     * The <code>RifleBase</code> constructor essentially creates the modules that are included in the <code>RifleModuleBuilder</code>
     * and their unfinished variants into items.
     * @param coreId The main id of the modules' parent gun, i.e. "m4a1"
     * @param moduleBuilder The modules stored in <code>RifleModuleBuilder</code>
     * @implNote The corresponding item, if the <code>coreId = "m4a1"</code> and the <code>moduleBuilder = new RifleModuleBuilder(RifleModule.LOWER_RECEIVER)</code>,
     * three item entries will be generated:
     * <ul>
     *     <li><code>prma:m4a1_lower_receiver</code></li>
     *     <li><code>prma:m4a1_lower_receiver_cast</code></li>
     * </ul>
     * <br>
     * These item entries will have the item model resource location of:
     * <ul>
     *     <li><code>prma:item/weapons/guns/m4a1/m4a1_lower_receiver</code></li>
     *     <li><code>prma:item/weapons/guns/m4a1/m4a1_lower_receiver_cast</code></li>
     * </ul>
     */
    public RifleBase(String coreId, RifleModuleBuilder moduleBuilder) {
        this(coreId, new Item.Properties(), moduleBuilder);
    }

    public RifleBase(String coreId, RifleModule... modules) {
        this(coreId, new RifleModuleBuilder(modules));
    }

    /**
     * The <code>registerModule()</code> function registers the Gun Assembly Module Items. Each Module will also have a
     * corresponding "unfinished" variant of the item registered.
     *
     * @param id The id of the module's assembled gun, i.e. "m4a1"
     * @param module The module id of the gun's module, i.e. "lower_receiver"
     * @param properties The properties of the module
     * @return A registry entry of the module item, not the unfinished module variant's module
     *
     * @implNote The corresponding item, if the <code>id = "m4a1"</code> and <code>moduleId = "lower_receiver"</code>,
     * three item entries will be generated:
     * <ul>
     *     <li><code>prma:m4a1_lower_receiver</code></li>
     *     <li><code>prma:m4a1_lower_receiver_cast</code></li>
     * </ul>
     * <br>
     * These item entries will have the item model resource location of:
     * <ul>
     *     <li><code>prma:item/weapons/guns/m4a1/m4a1_lower_receiver</code></li>
     *     <li><code>prma:item/casts/weapons/guns/m4a1/m4a1_lower_receiver_cast</code></li>
     * </ul>
     */
    private RegistryEntry<Item> registerModule(String id, RifleModule module, Item.Properties properties) {
        String name = String.format("%s_%s", id, module.toString());

        // Register the module
        RegistryEntry<Item> mainModule = Main.REGISTRATE.item(name, Item::new)
                .model(ModItemModelProvider.genericItemModel(true, "weapons", "general","guns", "modules", "general_" + module))
                .properties(p -> properties.tab(ModCreativeModTabs.MOD_COMPONENTS_TAB))
                .register();

        registry.put(module.getType(), mainModule);
        // Register the unfinished module
        // module variant, and make it invisible in the creative tab
        blueprintsRegistry.put(module.getType(), Main.REGISTRATE.item(name + "_blueprint", Item::new)
                .model(ModItemModelProvider.genericItemModel(true, "weapons", "general", "guns", "blueprints", "general_" + module + "_blueprint"))
                .properties(p -> properties.tab(ModCreativeModTabs.MOD_BLUEPRINTS_TAB))
                .register());

        // Register the module's cast
        castsRegistry.put(module.getType(), Main.REGISTRATE.item(name + "_cast", Item::new)
                .model(ModItemModelProvider.genericItemModel(true, "weapons", "general", "guns", "casts", "general_" + module + "_cast"))
                .properties(p -> properties.tab(ModCreativeModTabs.MOD_CASTS_TAB))
                .register());

        return mainModule;
    }

    public void registerRecipes(){
        ItemStack gunItem = new ItemStack(new Item(new Item.Properties()).setRegistryName("tacz","modern_kinetic_gun"));
        CompoundTag itemTag = new CompoundTag();
        itemTag.putString("GunId", "tacz:" + getCoreId());
        gunItem.setTag(itemTag);

        Main.LOGGER.debug("givenModuleBuilder: {}", givenModuleBuilder.get().length);
        for(RifleModule m : givenModuleBuilder.get()){
            RegistryEntry<Item> mainModule = registry.get(m.getType());
            RegistryEntry<Item> castModule = castsRegistry.get(m.getType());
            RegistryEntry<Item> blueprintModule = blueprintsRegistry.get(m.getType());
            String name = String.format("%s_%s", getCoreId(), m.getType().toString());

            ModRecipeProvider.add(ShapedRecipeBuilder
                    .shaped(castModule.get())
                    .unlockedBy(String.format("has_%s_blueprint", name), inventoryTrigger(ItemPredicate.Builder.item().of(blueprintModule.get()).build()))
                    .pattern("PIP")
                    .pattern("IBI")
                    .pattern("PIP")
                    .define('P', AllItems.IRON_SHEET.get())
                    .define('I', Items.IRON_INGOT)
                    .define('B', blueprintModule.get()));
            ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<FillingRecipe>(FillingRecipe::new, ResourceHelper.find(String.format("weapons/guns/%s/%s", getCoreId(), name + "_castmaking")))
                    .output(castModule.get())
                    .require(mainModule.get())
                    .require(ModFluids.MOLTEN_BASALT_INFUSED_IRON.get(), 1000));
            ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<FillingRecipe>(FillingRecipe::new, ResourceHelper.find(String.format("weapons/guns/%s/%s", getCoreId(), name)))
                    .output(mainModule.get())
                    .require(castModule.get())
                    .require(m.getData().getFillCastFluid().get(), m.getData().getCastFillingAmount()));
            ModRecipeProvider.add(ShapelessRecipeBuilder
                    .shapeless(blueprintModule.get(), 2)
                    .unlockedBy(String.format("has_%s_blueprint", name), inventoryTrigger(ItemPredicate.Builder.item().of(blueprintModule.get()).build()))
                    .requires(Items.PAPER)
                    .requires(Items.INK_SAC)
                    .requires(Items.WHITE_DYE)
                    .requires(blueprintModule.get())
                    .group("prma:blueprint_regen"));

//            ItemStack gunItem = new ItemStack(new Item(new Item.Properties()).setRegistryName("tacz","modern_kinetic_gun"));

            ModDecomponentalizingRecipesGen.add(gunItem, blueprintModule.get(), m.getData().getDecompTime());
        }
    }

    @Nullable
    private RifleModule getModuleByType(RifleModuleType type){
        for(RifleModule m : givenModuleBuilder.get()){
            if(m.getType() == type)
                return m;
        }
        return null;
    }

    public RegistryEntry<Item> getModuleFromBlueprintRegistryByType(RifleModuleType type){
        return blueprintsRegistry.getOrDefault(type, null);
    }

    public RegistryEntry<Item> getModuleFromCastRegistryByType(RifleModuleType type){
        return castsRegistry.getOrDefault(type, null);
    }

    public RegistryEntry<Item> getModuleFromBlueprintRegistryByType(RifleModule module){
        return blueprintsRegistry.getOrDefault(module, null);
    }

    public RegistryEntry<Item> getModuleFromCastRegistryByType(RifleModule module){
        return castsRegistry.getOrDefault(module, null);
    }

    public RifleBase setModuleData(int index, Consumer<RifleModule.Data> dataConsumer) {
        List<RifleModule> modules = Arrays.asList(givenModuleBuilder.get());

        RifleModule module = modules.get(index);
        assert module != null;

        RifleModule updatedModule = module.setData(dataConsumer);
        int ind = modules.indexOf(module);
        modules.set(ind, updatedModule);
        givenModuleBuilder = new RifleModuleBuilder(modules.toArray(new RifleModule[0]));

        return this;
    }
}
