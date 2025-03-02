package cn.crtlprototypestudios.prma.foundation;

import cn.crtlprototypestudios.prma.foundation.neo.complex.content.processing.casting_basin.recipe.CastingRecipe;
import cn.crtlprototypestudios.prma.foundation.neo.complex.content.processing.casting_basin.recipe.CastingRecipeSerializer;
import cn.crtlprototypestudios.prma.lib.Reference;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeSerializer;
import com.simibubi.create.foundation.recipe.IRecipeTypeInfo;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public enum PrmaRecipeTypes implements IRecipeTypeInfo {
    CASTING("casting", new CastingRecipeSerializer(CastingRecipe::new)),;

    private final ResourceLocation id;
    private final RegistryObject<RecipeSerializer<?>> serializerObject;
    private final Supplier<RecipeType<?>> type;

    PrmaRecipeTypes(String name, ProcessingRecipeBuilder.ProcessingRecipeFactory<?> factory) {
        this.id = new ResourceLocation(Reference.MOD_ID, name);
        this.serializerObject = Registers.SERIALIZER_REGISTER.register(name,
                () -> new ProcessingRecipeSerializer<>(factory));
        this.type = Registers.RECIPE_TYPE_REGISTER.register(name,
                () -> RecipeType.simple(this.id));
    }

    // New constructor that takes a RecipeSerializer directly
    PrmaRecipeTypes(String name, RecipeSerializer<?> serializer) {
        this.id = new ResourceLocation(Reference.MOD_ID, name);
        this.serializerObject = Registers.SERIALIZER_REGISTER.register(name, () -> serializer);
        this.type = Registers.RECIPE_TYPE_REGISTER.register(name,
                () -> RecipeType.simple(this.id));
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends RecipeSerializer<?>> T getSerializer() {
        return (T) serializerObject.get();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends RecipeType<?>> T getType() {
        return (T) type.get();
    }

    private static class Registers {
        private static final DeferredRegister<RecipeSerializer<?>> SERIALIZER_REGISTER =
                DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Reference.MOD_ID);
        private static final DeferredRegister<RecipeType<?>> RECIPE_TYPE_REGISTER =
                DeferredRegister.create(Registries.RECIPE_TYPE, Reference.MOD_ID);

        public static void register(IEventBus modEventBus) {
            SERIALIZER_REGISTER.register(modEventBus);
            RECIPE_TYPE_REGISTER.register(modEventBus);
        }
    }

    public static void register(IEventBus modEventBus) {
        Registers.register(modEventBus);
    }
}
