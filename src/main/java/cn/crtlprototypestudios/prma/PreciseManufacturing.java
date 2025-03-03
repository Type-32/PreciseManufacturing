package cn.crtlprototypestudios.prma;

import cn.crtlprototypestudios.prma.foundation.*;
import cn.crtlprototypestudios.prma.foundation.handler.PacketHandler;
import cn.crtlprototypestudios.prma.foundation.utility.PreciseManufacturingRegistrate;
import cn.crtlprototypestudios.prma.lib.Reference;
import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
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
public class PreciseManufacturing {

    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final PreciseManufacturingRegistrate REGISTRATE = PreciseManufacturingRegistrate.create(Reference.MOD_ID);

    public PreciseManufacturing() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        registerEntries(eventBus);

        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void registerEntries(final IEventBus eventBus) {

        PrmaBlocks.register();
        PrmaBlockEntities.register(eventBus);
        PrmaContainers.register(eventBus);
        PrmaItems.register();
        PrmaFluids.register();
        PrmaTags.register();
        PrmaRecipes.register(eventBus);
        PrmaCreativeModTabs.register(eventBus);
        PrmaRecipeTypes.register(eventBus);

        REGISTRATE.registerEventListeners(eventBus);
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

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = Reference.MOD_ID, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(final FMLClientSetupEvent event) {
//            ItemBlockRenderTypes.setRenderLayer(ModBlocks.DECOMPONENTALIZER.get(), RenderType.translucent());
//            event.enqueueWork(() -> MenuScreens.register(PrmaContainers.DECOMPONENTALIZER_CONTAINER_MENU.get(), DecomponentalizerScreen::new));
        }
    }
}
