package com.cursee.more_useful_copper;

import com.cursee.monolib.core.sailing.Sailing;
import com.cursee.more_useful_copper.core.entity.client.CopperGolemModel;
import com.cursee.more_useful_copper.core.entity.client.CopperGolemRenderer;
import com.cursee.more_useful_copper.core.entity.custom.CopperGolemEntity;
import com.cursee.more_useful_copper.core.entity.layer.ModModelLayers;
import com.cursee.more_useful_copper.core.registry.RegistryNeoForge;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@Mod(Constants.MOD_ID)
public class MoreUsefulCopperNeoForge {

    public MoreUsefulCopperNeoForge(IEventBus modEventBus) {

        MoreUsefulCopper.init();
        Sailing.register(Constants.MOD_NAME, Constants.MOD_ID, Constants.MOD_VERSION, Constants.MC_VERSION_RAW, Constants.PUBLISHER_AUTHOR, Constants.PRIMARY_CURSEFORGE_MODRINTH);

        RegistryNeoForge.register(modEventBus);
    }

    @EventBusSubscriber(modid = Constants.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientEvents {
        @SubscribeEvent
        public static void onClientSetup(final FMLClientSetupEvent event) {
            event.enqueueWork(() -> {
                EntityRenderers.register(RegistryNeoForge.COPPER_GOLEM.get(), CopperGolemRenderer::new);
            });
        }
    }

    @EventBusSubscriber(modid = Constants.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
    public static class ModEvents {
        @SubscribeEvent
        public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(ModModelLayers.COPPER_GOLEM_LAYER, CopperGolemModel::createBodyLayer);
        }

        @SubscribeEvent
        public static void registerAttributes(EntityAttributeCreationEvent event) {
            event.put(RegistryNeoForge.COPPER_GOLEM.get(), CopperGolemEntity.createAttributes().build());
        }
    }
}
