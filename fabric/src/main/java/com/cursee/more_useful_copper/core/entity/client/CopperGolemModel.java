package com.cursee.more_useful_copper.core.entity.client;// Made with Blockbench 4.9.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.cursee.more_useful_copper.core.entity.animations.GolemAnimations;
import com.cursee.more_useful_copper.core.entity.custom.CopperGolemEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class CopperGolemModel<T extends CopperGolemEntity> extends HierarchicalModel<T> {
	private final ModelPart golem;
	private final ModelPart head;

	public CopperGolemModel(ModelPart root) {
		this.golem = root.getChild("body");
		this.head = root.getChild("body").getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		
		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 40).addBox(-9.0F, -33.0F, -6.0F, 18.0F, 12.0F, 11.0F, new CubeDeformation(0.0F))
			.texOffs(0, 70).addBox(-4.5F, -21.0F, -3.0F, 9.0F, 5.0F, 6.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 24.0F, 0.0F));
		
		PartDefinition head =
			body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0)
				.addBox(-4.0F, -43.0F, -7.5F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(24, 0)
				.addBox(-1.0F, -36.0F, -9.5F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		
		PartDefinition legRight =
			body.addOrReplaceChild("legRight", CubeListBuilder.create().texOffs(37, 0)
				.addBox(-7.5F, 0.0F, -3.0F, 6.0F, 16.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -16.0F, 0.0F));
		
		PartDefinition legLeft = body.addOrReplaceChild("legLeft", CubeListBuilder.create().texOffs(60, 0).addBox(1.5F, 0.0F, -3.0F, 6.0F, 16.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -16.0F, 0.0F));
		
		PartDefinition armRight = body.addOrReplaceChild("armRight", CubeListBuilder.create().texOffs(60, 21).addBox(-13.0F, -0.5F, -3.0F, 4.0F, 30.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -33.0F, 0.0F));
		
		PartDefinition armLeft = body.addOrReplaceChild("armLeft", CubeListBuilder.create().texOffs(60, 58).addBox(9.0F, -0.5F, -3.0F, 4.0F, 30.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -33.0F, 0.0F));
		
		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(CopperGolemEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		
		this.applyHeadRotation(entity, netHeadYaw, headPitch, ageInTicks);
		
		this.animateWalk(GolemAnimations.WALK, limbSwing, limbSwingAmount, 1.0F, 1.0F);
		
		this.animate(entity.attackAnimationState, GolemAnimations.ATTACK, ageInTicks, 1.0F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		golem.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
	
	@Override
	public ModelPart root() {
		return golem;
	}
	
	private void applyHeadRotation(CopperGolemEntity pEntity, float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -2.5F, 2.5F);
		
		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}
}