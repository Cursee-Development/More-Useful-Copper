package com.cursee.more_useful_copper.core.registry;

import com.cursee.more_useful_copper.Constants;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class ModHorseArmorItem extends Item {
	private static final String TEX_FOLDER = "textures/entity/horse/";
	private final int protection;
	private final ResourceLocation texture;
	
	public ModHorseArmorItem(int p_41364_, String material, Properties properties) {
		this(p_41364_, new ResourceLocation(Constants.MOD_ID, "textures/entity/horse/armor/horse_armor_" + material + ".png"), properties);
	}
	
	public ModHorseArmorItem(int p_41364_, ResourceLocation p_41365_, Properties p_41366_) {
		super(p_41366_);
		this.protection = p_41364_;
		this.texture = p_41365_;
	}
	
	public ResourceLocation getTexture() {
		return this.texture;
	}
	
	public int getProtection() {
		return this.protection;
	}
}