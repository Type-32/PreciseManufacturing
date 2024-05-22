package cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.weapon;

import cn.crtlprototypestudios.precisemanufacturing.Main;
import cn.crtlprototypestudios.precisemanufacturing.foundation.ModCreativeModTabs;
import cn.crtlprototypestudios.precisemanufacturing.foundation.ModFluids;
import cn.crtlprototypestudios.precisemanufacturing.foundation.data.providers.ModItemModelProvider;
import cn.crtlprototypestudios.precisemanufacturing.foundation.data.providers.ModRecipeProvider;
import cn.crtlprototypestudios.precisemanufacturing.foundation.util.ResourceHelper;
import com.simibubi.create.AllItems;
import com.simibubi.create.AllRecipeTypes;
import com.simibubi.create.content.fluids.spout.FillingBySpout;
import com.simibubi.create.content.fluids.transfer.FillingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.simibubi.create.foundation.data.recipe.CrushingRecipeGen;
import com.simibubi.create.foundation.data.recipe.FillingRecipeGen;
import com.simibubi.create.foundation.data.recipe.ProcessingRecipeGen;
import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateItemModelProvider;
import com.tterrag.registrate.util.entry.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.Hashtable;

import static com.tterrag.registrate.providers.RegistrateRecipeProvider.inventoryTrigger;

// TODO Write Documentations
public class RifleBase extends WeaponBase {
    public static final RifleModuleBuilder
        STANDARD_RIFLE_MODULES = new RifleModuleBuilder(
                RifleModuleType.GRIP,
                RifleModuleType.LOWER_RECEIVER,
                RifleModuleType.UPPER_RECEIVER,
                RifleModuleType.HANDGUARD,
                RifleModuleType.BARREL,
                RifleModuleType.MAGAZINE,
                RifleModuleType.FIRE_CONTROL_GROUP,
                RifleModuleType.FIRE_SELECTOR,
                RifleModuleType.TRIGGER,
                RifleModuleType.STOCK
        ),
        BULLPUP_RIFLE_MODULES = new RifleModuleBuilder(
                RifleModuleType.GRIP,
                RifleModuleType.BULLPUP_BODY,
                RifleModuleType.MAGAZINE,
                RifleModuleType.FIRE_CONTROL_GROUP,
                RifleModuleType.GRIP,
                RifleModuleType.TRIGGER,
                RifleModuleType.FIRE_SELECTOR

                // To Future me: Do not add an UNFINISHED type in the Rifle Module Types. Autonomous Rifle Module
                // registration works differently compared to Cartridge Autonomous registrations, as each Cartridge
                // uses only one Unfinished item variant to represent the modules of the Cartridge during the
                // sequenced assemblies, while Rifle Modules has an unfinished item variant for each rifle module,
                // as each module should only be crafted via a sequenced assembly.
        ),
        PUMP_ACTION_MODULES = new RifleModuleBuilder(
                RifleModuleType.RECEIVER,
                RifleModuleType.SHELL_TUBE,
                RifleModuleType.BARREL,
                RifleModuleType.TRIGGER,
                RifleModuleType.PUMP,
                RifleModuleType.FIRE_CONTROL_GROUP,
                RifleModuleType.TRIGGER,
                RifleModuleType.STOCK
        ),
        BOLT_ACTION_MODULES = new RifleModuleBuilder(
                RifleModuleType.BOLT,
                RifleModuleType.BARREL,
                RifleModuleType.LONG_BODY,
                RifleModuleType.TRIGGER,
                RifleModuleType.STOCK,
                RifleModuleType.CARTRIDGE_WELL,
                RifleModuleType.GRIP
        );


    public Hashtable<RifleModuleType, RegistryEntry<Item>> registry = new Hashtable<>();
    public Hashtable<RifleModuleType, RegistryEntry<Item>> castsRegistry = new Hashtable<>();
    public Hashtable<RifleModuleType, RegistryEntry<Item>> blueprintsRegistry = new Hashtable<>();
    public RifleModuleBuilder givenModuleBuilder;

    public RifleBase(String coreId, Item.Properties properties, RifleModuleBuilder moduleBuilder) {
        super(coreId, properties.stacksTo(1));
        givenModuleBuilder = moduleBuilder;

        // Put all the registered modules in the Hashtable for later use
        for (RifleModuleType i : moduleBuilder.get()) {
            registry.put(i, registerModule(coreId, i, properties));
        }
    }

    /**
     * The <code>RifleBase</code> constructor essentially creates the modules that are included in the <code>RifleModuleBuilder</code>
     * and their unfinished variants into items.
     * @param coreId The main id of the modules' parent gun, i.e. "m4a1"
     * @param moduleBuilder The modules stored in <code>RifleModuleBuilder</code>
     * @implNote The corresponding item, if the <code>coreId = "m4a1"</code> and the <code>moduleBuilder = new RifleModuleBuilder(RifleModuleType.LOWER_RECEIVER)</code>,
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

    public RifleBase(String coreId, RifleModuleType... modules) {
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
    private RegistryEntry<Item> registerModule(String id, RifleModuleType module, Item.Properties properties, boolean registerBlueprint, boolean registerCast) {
        String name = String.format("%s_%s", id, module.toString());

        // Register the module
        RegistryEntry<Item> mainModule = Main.REGISTRATE.item(name, Item::new)
                .model(ModItemModelProvider.genericItemModel("weapons","guns", id, "modules", name))
                .properties(p -> properties.tab(ModCreativeModTabs.MOD_COMPONENTS_TAB))
                .register();

        // Register the unfinished module variant, and make it invisible in the creative tab
        if(registerBlueprint) {
            RegistryEntry<Item> item = blueprintsRegistry.put(
                    module,
                    Main.REGISTRATE.item(name + "_blueprint", Item::new)
                            .model(ModItemModelProvider.genericItemModel("weapons", "guns", id, "blueprints", name + "_blueprint"))
                            .properties(p -> properties.tab(ModCreativeModTabs.MOD_BLUEPRINTS_TAB))
                            .register()
            );
            ModRecipeProvider.add(ShapelessRecipeBuilder
                    .shapeless(item.get(), 2)
                    .requires(Items.PAPER)
                    .requires(Items.INK_SAC)
                    .requires(Items.WHITE_DYE)
                    .requires(item.get())
                    .group("prma:blueprint_regen"));
        }

        // Register the module's cast
        if(registerCast)
            castsRegistry.put(
                    module,
                    Main.REGISTRATE.item(name + "_cast", Item::new)
                            .model(ModItemModelProvider.genericItemModel("weapons","guns", id, "casts", name + "_cast"))
                            .properties(p -> properties.tab(ModCreativeModTabs.MOD_CASTS_TAB))
                            .register()
            );

        if(registerBlueprint && registerCast) {
            ModRecipeProvider.add(ShapedRecipeBuilder
                    .shaped(castsRegistry.get(module).get())
                    .unlockedBy(String.format("has_%s_blueprint", name), inventoryTrigger(ItemPredicate.Builder.item().of(blueprintsRegistry.get(module).get()).build()))
                    .pattern("PIP")
                    .pattern("IBI")
                    .pattern("PIP")
                    .define('P', AllItems.IRON_SHEET.get())
                    .define('I', Items.IRON_INGOT)
                    .define('B', blueprintsRegistry.get(module).get()));
            ModRecipeProvider.addCreateRecipeBuilder(new ProcessingRecipeBuilder<FillingRecipe>(FillingRecipe::new, ResourceHelper.find(String.format("filling/weapons/guns/%s/%s", id, name + "_castmaking")))
                    .output(castsRegistry.get(module).get())
                    .require(mainModule.get())
                    .require(ModFluids.MOLTEN_BASALT_INFUSED_IRON.get(), 500));
        }


        return mainModule;
    }

    private RegistryEntry<Item> registerModule(String id, RifleModuleType module, Item.Properties properties) {

        return registerModule(id, module, properties, true, true);
    }


}
