package cn.crtlprototypestudios.precisemanufacturing.foundation.item.bases.weapon;

import cn.crtlprototypestudios.precisemanufacturing.Main;
import cn.crtlprototypestudios.precisemanufacturing.foundation.ModCreativeModTabs;
import cn.crtlprototypestudios.precisemanufacturing.foundation.ModFluids;
import cn.crtlprototypestudios.precisemanufacturing.foundation.ModTags;
import cn.crtlprototypestudios.precisemanufacturing.foundation.data.generators.recipe.ModDecomponentalizingRecipesGen;
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
import net.minecraft.core.Holder;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Hashtable;

import static com.tterrag.registrate.providers.RegistrateRecipeProvider.inventoryTrigger;

// TODO Write Documentations
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
                RifleModule.GRIP_MODULE,
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


    public Hashtable<RifleModule, RegistryEntry<Item>> registry = new Hashtable<>();
    public Hashtable<RifleModule, RegistryEntry<Item>> castsRegistry = new Hashtable<>();
    public Hashtable<RifleModule, RegistryEntry<Item>> blueprintsRegistry = new Hashtable<>();
    public RifleModuleBuilder givenModuleBuilder;

    public RifleBase(String coreId, Item.Properties properties, RifleModuleBuilder moduleBuilder) {
        super(coreId, properties.stacksTo(1));
        givenModuleBuilder = moduleBuilder;

        // Put all the registered modules in the Hashtable for later use
        for (RifleModule i : moduleBuilder.get()) {
            registry.put(i, registerModule(coreId, i, properties));
        }
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
    private RegistryEntry<Item> registerModule(String id, RifleModule module, Item.Properties properties, boolean registerBlueprint, boolean registerCast) {
        String name = String.format("%s_%s", id, module.getType().toString());

        // Register the module
        RegistryEntry<Item> mainModule = Main.REGISTRATE.item(name, Item::new)
                .model(ModItemModelProvider.genericItemModel("weapons","guns", id, "modules", name))
                .properties(p -> properties.tab(ModCreativeModTabs.MOD_COMPONENTS_TAB))
                .register();

        RegistryEntry<Item> blueprintModule = Main.REGISTRATE.item(name + "_blueprint", Item::new)
                .model(ModItemModelProvider.genericItemModel("weapons", "guns", id, "blueprints", name + "_blueprint"))
                .properties(p -> properties.tab(ModCreativeModTabs.MOD_BLUEPRINTS_TAB))
                .register();

        RegistryEntry<Item> castModule = Main.REGISTRATE.item(name + "_cast", Item::new)
                .model(ModItemModelProvider.genericItemModel("weapons","guns", id, "casts", name + "_cast"))
                .properties(p -> properties.tab(ModCreativeModTabs.MOD_CASTS_TAB))
                .register();

        // Register the unfinished module
        // dule variant, and make it invisible in the creative tab
        blueprintsRegistry.put(module, blueprintModule);
        ModRecipeProvider.add(ShapelessRecipeBuilder
                .shapeless(blueprintModule.get(), 2)
                .requires(Items.PAPER)
                .requires(Items.INK_SAC)
                .requires(Items.WHITE_DYE)
                .requires(blueprintModule.get())
                .group("prma:blueprint_regen"));

//            Do not delete these comments, these will be implemented later
//            For now, the gun module decomp recipes will be data-driven instead of code-driven

//            CompoundTag gunNbt = new CompoundTag();
//            gunNbt.putString("GunId", id);
//            CompoundTag itemNbt = new CompoundTag();
//            itemNbt.putString("item", "tacz:modern_kinetic_gun");
//            itemNbt.put("nbt", gunNbt);
//
//            ItemStack gunItem = new ItemStack(new Holder<Item>());
//            gunItem.setTag(itemNbt);
//            ModDecomponentalizingRecipesGen.add(, mainModule.get(), module.getData().getDecompTime());

        // Register the module's cast
        castsRegistry.put(module, blueprintModule);

        if(registerBlueprint && registerCast) {

//            TODO Delayed Recipe Provider Register Impl.
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

    private RegistryEntry<Item> registerModule(String id, RifleModule module, Item.Properties properties) {

        return registerModule(id, module, properties, true, true);
    }


}
