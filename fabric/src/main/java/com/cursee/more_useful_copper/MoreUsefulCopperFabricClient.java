package com.cursee.more_useful_copper;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import com.cursee.more_useful_copper.core.entity.client.CopperGolemModel;
import com.cursee.more_useful_copper.core.entity.client.CopperGolemRenderer;
import com.cursee.more_useful_copper.core.entity.layer.ModModelLayers;
import com.cursee.more_useful_copper.core.registry.RegistryFabric;

public class MoreUsefulCopperFabricClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		EntityModelLayerRegistry.registerModelLayer(ModModelLayers.COPPER_GOLEM, CopperGolemModel::createBodyLayer);
		
		EntityRendererRegistry.register(RegistryFabric.COPPER_GOLEM, CopperGolemRenderer::new);
	}
}
