package com.cursee.more_useful_copper;

import com.cursee.monolib.core.sailing.Sailing;
import com.cursee.more_useful_copper.core.entity.client.CopperGolemModel;
import com.cursee.more_useful_copper.core.entity.client.CopperGolemRenderer;
import com.cursee.more_useful_copper.core.entity.custom.CopperGolemEntity;
import com.cursee.more_useful_copper.core.entity.layer.ModModelLayers;
import com.cursee.more_useful_copper.core.loot.ModLootModifiers;
import com.cursee.more_useful_copper.core.registry.ForgeRegistry;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Constants.MOD_ID)
public class MoreUsefulCopperForge {
    
    public MoreUsefulCopperForge() {
    
        MoreUsefulCopper.init();
        Sailing.register(Constants.MOD_NAME, Constants.MOD_ID, Constants.MOD_VERSION, Constants.MC_VERSION_RAW, Constants.PUBLISHER_AUTHOR, Constants.PRIMARY_CURSEFORGE_MODRINTH);

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ForgeRegistry.register(bus);

        ModLootModifiers.register(bus);
    }

    @Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public class ClientEvents{
        @SubscribeEvent
        public static void onClientSetup(final FMLClientSetupEvent event) {
            event.enqueueWork(() -> {
                EntityRenderers.register(ForgeRegistry.COPPER_GOLEM.get(), CopperGolemRenderer::new);
            });
        }
    }

    @Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public class ModEvents{
        @SubscribeEvent
        public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(ModModelLayers.COPPER_GOLEM_LAYER, CopperGolemModel::createBodyLayer);
        }

        @SubscribeEvent
        public static void registerAttributes(EntityAttributeCreationEvent event) {
            event.put(ForgeRegistry.COPPER_GOLEM.get(), CopperGolemEntity.createAttributes().build());
        }
    }
}