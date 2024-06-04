package cn.crtlprototypestudios.precisemanufacturing;

import cn.crtlprototypestudios.precisemanufacturing.foundation.*;
import cn.crtlprototypestudios.precisemanufacturing.foundation.client.gui.decomponentalizer.DecomponentalizerScreen;
import cn.crtlprototypestudios.precisemanufacturing.foundation.client.handler.PacketHandler;
import cn.crtlprototypestudios.precisemanufacturing.foundation.util.PreciseManufacturingRegistrate;
import cn.crtlprototypestudios.precisemanufacturing.util.Reference;
import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Reference.MOD_ID)
public class Main {

    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final PreciseManufacturingRegistrate REGISTRATE = PreciseManufacturingRegistrate.create(Reference.MOD_ID);

    public Main() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModBlocks.register();
        ModBlockEntities.register(eventBus);
        ModContainers.register(eventBus);
        ModItems.register();
        ModFluids.register();
        ModTags.register();
        ModRecipes.register(eventBus);
        ModCreativeModTabs.register(eventBus);

        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // Some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        event.enqueueWork(PacketHandler::register);
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        // Some example code to dispatch IMC to another mod
        InterModComms.sendTo("prma", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event) {
        // Some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.messageSupplier().get()).
                collect(Collectors.toList()));
    }

    private void clientSetup(final FMLClientSetupEvent event) {
//        event.enqueueWork(() -> );
        MenuScreens.register(ModContainers.DECOMPONENTALIZER.get(), DecomponentalizerScreen::new);
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.DECOMPONENTALIZER.get(), RenderType.translucent());
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
//        @SubscribeEvent
//        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
//            // Register a new block here
//            LOGGER.info("HELLO from Register Block");
//        }
//
//        @SubscribeEvent
//        public static void onItemsRegistry(final RegistryEvent.Register<Item> itemRegistryEvent) {
//            LOGGER.info("M4A1 registry size {}; cast {}; blueprint {}", ModItems.M4A1.registry.size(), ModItems.M4A1.castsRegistry.size(), ModItems.M4A1.blueprintsRegistry.size());
//        }
    }
}
