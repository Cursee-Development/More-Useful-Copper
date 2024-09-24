package com.cursee.more_useful_copper.core.content;

import com.cursee.more_useful_copper.core.registry.RegistryNeoForge;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class CopperMilkBucketItem extends Item {
	private static final int DRINK_DURATION = 32;
	
	public CopperMilkBucketItem(Properties pProperties) {
		super(pProperties);
	}
	
	public ItemStack finishUsingItem(ItemStack pItemStack, Level pLevel, LivingEntity pLivingEntity) {
		if (pLivingEntity instanceof ServerPlayer $$3) {
			CriteriaTriggers.CONSUME_ITEM.trigger($$3, pItemStack);
			$$3.awardStat(Stats.ITEM_USED.get(this));
		}
		
		if (pLivingEntity instanceof Player && !((Player)pLivingEntity).getAbilities().instabuild) {
			pItemStack.shrink(1);
		}
		
		if (!pLevel.isClientSide) {
			pLivingEntity.removeAllEffects();
		}
		
		return pItemStack.isEmpty() ? new ItemStack(RegistryNeoForge.COPPER_BUCKET.get()) : pItemStack;
	}
	
	public int getUseDuration(ItemStack $$0) {
		return DRINK_DURATION;
	}
	
	public UseAnim getUseAnimation(ItemStack $$0) {
		return UseAnim.DRINK;
	}
	
	public InteractionResultHolder<ItemStack> use(Level $$0, Player $$1, InteractionHand $$2) {
		return ItemUtils.startUsingInstantly($$0, $$1, $$2);
	}
}

