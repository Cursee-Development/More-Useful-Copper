package com.cursee.more_useful_copper.core.entity.client;

import com.cursee.more_useful_copper.Constants;
import com.cursee.more_useful_copper.core.entity.custom.CopperGolemEntity;
import com.cursee.more_useful_copper.core.entity.layer.ModModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CopperGolemRenderer extends MobRenderer<CopperGolemEntity, CopperGolemModel<CopperGolemEntity>> {
	
	public static final ResourceLocation COPPER_GOLEM_LOCATION = new ResourceLocation(Constants.MOD_ID, "textures/entity/copper_golem/copper_golem.png");
	
	public CopperGolemRenderer(EntityRendererProvider.Context pContext) {
		super(pContext, new CopperGolemModel<>(pContext.bakeLayer(ModModelLayers.COPPER_GOLEM)), 1.0F);
	}
	
	@Override
	public ResourceLocation getTextureLocation(CopperGolemEntity copperGolemEntity) {
		return COPPER_GOLEM_LOCATION;
	}
}
