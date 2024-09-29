package com.cursee.more_useful_copper.mixin;

import com.cursee.more_useful_copper.core.registry.FabricRegistry;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Cow.class)
public class FabricCowMixin {
	
	@Inject(method = "mobInteract", at = @At("HEAD"))
	private void injected(Player p_28298_, InteractionHand p_28299_, CallbackInfoReturnable<InteractionResult> cir) {
		ItemStack $$2 = p_28298_.getItemInHand(p_28299_);
		   if ($$2.is(FabricRegistry.COPPER_BUCKET) && !((Cow)(Object)this).isBaby()) {
		     p_28298_.playSound(SoundEvents.COW_MILK, 1.0F, 1.0F);
		     ItemStack $$3 = ItemUtils.createFilledResult($$2, p_28298_, FabricRegistry.COPPER_MILK_BUCKET.getDefaultInstance());
		     p_28298_.setItemInHand(p_28299_, $$3);
		   }
	}
	
	// public InteractionResult mobInteract(Player p_28298_, InteractionHand p_28299_) {
	//    ItemStack $$2 = p_28298_.getItemInHand(p_28299_);
	//    if ($$2.is(Items.BUCKET) && !this.isBaby()) {
	//      p_28298_.playSound(SoundEvents.COW_MILK, 1.0F, 1.0F);
	//      ItemStack $$3 = ItemUtils.createFilledResult($$2, p_28298_, Items.MILK_BUCKET.getDefaultInstance());
	//      p_28298_.setItemInHand(p_28299_, $$3);
	//      return InteractionResult.sidedSuccess(this.level().isClientSide);
	//    } else {
	//      return super.mobInteract(p_28298_, p_28299_);
	//    }
	//  }
}
