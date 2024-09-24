package com.cursee.more_useful_copper.core.content;

import com.cursee.more_useful_copper.core.registry.RegistryFabric;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult.Type;
import org.jetbrains.annotations.Nullable;

public class CopperBucketItem extends Item implements DispensibleContainerItem {
	private final Fluid content;
	
	public CopperBucketItem(Fluid fluid, Properties properties) {
		super(properties);
		this.content = fluid;
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pInteractionHand) {
		ItemStack vItemStack = pPlayer.getItemInHand(pInteractionHand);
		BlockHitResult vBlockHitResult = getPlayerPOVHitResult(pLevel, pPlayer, this.content == Fluids.EMPTY ? ClipContext.Fluid.SOURCE_ONLY : ClipContext.Fluid.NONE);
		
		if (vBlockHitResult.getType() == Type.MISS) {
			return InteractionResultHolder.pass(vItemStack);
		} else if (vBlockHitResult.getType() != Type.BLOCK) {
			return InteractionResultHolder.pass(vItemStack);
		} else {
			BlockPos vBlockPos = vBlockHitResult.getBlockPos();
			Direction vDirection = vBlockHitResult.getDirection();
			BlockPos vBlockPos1 = vBlockPos.relative(vDirection);
			
			if (pLevel.mayInteract(pPlayer, vBlockPos) && pPlayer.mayUseItemAt(vBlockPos1, vDirection, vItemStack)) {
				BlockState vBlockState;
				
				// check if our bucket is currently empty
				if (this.content == Fluids.EMPTY) {
					vBlockState = pLevel.getBlockState(vBlockPos);
					
					// cauldron?
					ItemStack full = new ItemStack(RegistryFabric.COPPER_WATER_BUCKET);
					if (vBlockState.getBlock() == Blocks.WATER_CAULDRON) {
						// empty the cauldron and replace item in hand with full water bucket
						
						if (vItemStack.getCount() > 1) {
							vItemStack.setCount(vItemStack.getCount()-1);
							pPlayer.setItemInHand(pInteractionHand, vItemStack);
							pPlayer.addItem(full);
							pLevel.setBlock(vBlockPos, Blocks.CAULDRON.defaultBlockState(), 3);
							return InteractionResultHolder.sidedSuccess(vItemStack, pLevel.isClientSide());
						}
						else {
							pPlayer.setItemInHand(pInteractionHand, full);
							pLevel.setBlock(vBlockPos, Blocks.CAULDRON.defaultBlockState(), 3);
							
							return InteractionResultHolder.sidedSuccess(full, pLevel.isClientSide());
						}
					}
					
					// powder snow?
					ItemStack filled = RegistryFabric.COPPER_POWDER_SNOW_BUCKET.getDefaultInstance();
					if (vBlockState.getBlock() == Blocks.POWDER_SNOW) {
						// empty the snow and replace item in hand with full snow bucket
						
						if (vItemStack.getCount() > 1) {
							vItemStack.setCount(vItemStack.getCount()-1);
							pPlayer.setItemInHand(pInteractionHand, vItemStack);
							pPlayer.addItem(filled);
							pLevel.setBlock(vBlockPos, Blocks.AIR.defaultBlockState(), 3);
							return InteractionResultHolder.sidedSuccess(vItemStack, pLevel.isClientSide());
						}
						else {
							pPlayer.setItemInHand(pInteractionHand, filled);
							pLevel.setBlock(vBlockPos, Blocks.AIR.defaultBlockState(), 3);
							
							return InteractionResultHolder.sidedSuccess(filled, pLevel.isClientSide());
						}
					}
					
					// should handle picking up and placing water
					if (vBlockState.getBlock() instanceof BucketPickup && vBlockState.getBlock() != Blocks.LAVA && vBlockState.getBlock() != Blocks.LAVA_CAULDRON) {
						BucketPickup vBucketPickup = (BucketPickup)vBlockState.getBlock();
//						ItemStack vItemStack1 = vBucketPickup.pickupBlock(pPlayer, pLevel, vBlockPos, vBlockState);
						ItemStack vItemStack1 = vBucketPickup.pickupBlock(pPlayer, pLevel, vBlockPos, vBlockState);

						/* ADDED */
						if (vItemStack1.getItem() == Items.WATER_BUCKET) {
							vItemStack1 = new ItemStack(RegistryFabric.COPPER_WATER_BUCKET);
						}
						
						if (!vItemStack1.isEmpty()) {
							pPlayer.awardStat(Stats.ITEM_USED.get(this));
							vBucketPickup.getPickupSound().ifPresent(($$1x) -> {
								pPlayer.playSound($$1x, 1.0F, 1.0F);
							});
							pLevel.gameEvent(pPlayer, GameEvent.FLUID_PICKUP, vBlockPos);
							ItemStack vItemStack2 = ItemUtils.createFilledResult(vItemStack, pPlayer, vItemStack1);
							if (!pLevel.isClientSide) {
								CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer)pPlayer, vItemStack1);
							}
							
							return InteractionResultHolder.sidedSuccess(vItemStack2, pLevel.isClientSide());
						}
						
					}
				} else {
					// we're sure that our bucket has water
					vBlockState = pLevel.getBlockState(vBlockPos);
					BlockPos vBlockPos2 = vBlockState.getBlock() instanceof LiquidBlockContainer && this.content == Fluids.WATER ? vBlockPos : vBlockPos1;
					
					
					// cauldron?
					if (vBlockState.getBlock() == Blocks.CAULDRON) {
						// replace water bucket with empty bucket, set block to full cauldron
						ItemStack empty = new ItemStack(RegistryFabric.COPPER_BUCKET);
						pPlayer.setItemInHand(pInteractionHand, empty);
						pLevel.setBlock(vBlockPos, Blocks.WATER_CAULDRON.defaultBlockState().setValue(LayeredCauldronBlock.LEVEL, 3), 3);
						return InteractionResultHolder.sidedSuccess(empty, pLevel.isClientSide());
					}
					
					if (this.emptyContents(pPlayer, pLevel, vBlockPos2, vBlockHitResult)) {
						this.checkExtraContent(pPlayer, pLevel, vItemStack, vBlockPos2);
						if (pPlayer instanceof ServerPlayer) {
							CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer)pPlayer, vBlockPos2, vItemStack);
						}
						
						pPlayer.awardStat(Stats.ITEM_USED.get(this));
						return InteractionResultHolder.sidedSuccess(getEmptySuccessItem(vItemStack, pPlayer), pLevel.isClientSide());
					} else {
						return InteractionResultHolder.fail(vItemStack);
					}
				}
				// end handling picking up and placing water
				
			}
		}
		
		return InteractionResultHolder.fail(vItemStack);
	}
	
	
	public InteractionResultHolder<ItemStack> useOLD(Level pLevel, Player pPlayer, InteractionHand pInteractionHand) {
		ItemStack vItemStack = pPlayer.getItemInHand(pInteractionHand);
		BlockHitResult vBlockHitResult = getPlayerPOVHitResult(pLevel, pPlayer, this.content == Fluids.EMPTY ? ClipContext.Fluid.SOURCE_ONLY : ClipContext.Fluid.NONE);
		
		
		if (vBlockHitResult.getType() == Type.MISS) {
			return InteractionResultHolder.pass(vItemStack);
		} else if (vBlockHitResult.getType() != Type.BLOCK) {
			return InteractionResultHolder.pass(vItemStack);
		} else {
			BlockPos vBlockPos = vBlockHitResult.getBlockPos();
			Direction vDirection = vBlockHitResult.getDirection();
			BlockPos vBlockPos1 = vBlockPos.relative(vDirection);
			if (pLevel.mayInteract(pPlayer, vBlockPos) && pPlayer.mayUseItemAt(vBlockPos1, vDirection, vItemStack)) {
				BlockState vBlockState;
				if (this.content == Fluids.EMPTY) {
					vBlockState = pLevel.getBlockState(vBlockPos);
					
					/* ADDED */
					Block block = vBlockState.getBlock();
					
					if (/* EDITED */ block instanceof BucketPickup /* ADDED */ && block == Blocks.WATER) {
						BucketPickup vBucketPickup = (BucketPickup)vBlockState.getBlock();
						ItemStack vItemStack1 = vBucketPickup.pickupBlock(pPlayer, pLevel, vBlockPos, vBlockState);
						
						/* ADDED */
						if (vItemStack1.getItem() == Items.WATER_BUCKET) {
							vItemStack1 = new ItemStack(RegistryFabric.COPPER_WATER_BUCKET);
						}
						
						if (!vItemStack1.isEmpty()) {
							pPlayer.awardStat(Stats.ITEM_USED.get(this));
							vBucketPickup.getPickupSound().ifPresent(($$1x) -> {
								pPlayer.playSound($$1x, 1.0F, 1.0F);
							});
							pLevel.gameEvent(pPlayer, GameEvent.FLUID_PICKUP, vBlockPos);
							ItemStack vItemStack2 = ItemUtils.createFilledResult(vItemStack, pPlayer, vItemStack1);
							if (!pLevel.isClientSide) {
								CriteriaTriggers.FILLED_BUCKET.trigger((ServerPlayer)pPlayer, vItemStack1);
							}
							
							return InteractionResultHolder.sidedSuccess(vItemStack2, pLevel.isClientSide());
						}
					}
					
					return InteractionResultHolder.fail(vItemStack);
				} else {
					vBlockState = pLevel.getBlockState(vBlockPos);
					BlockPos vBlockPos2 = vBlockState.getBlock() instanceof LiquidBlockContainer && this.content == Fluids.WATER ? vBlockPos : vBlockPos1;
					if (this.emptyContents(pPlayer, pLevel, vBlockPos2, vBlockHitResult)) {
						this.checkExtraContent(pPlayer, pLevel, vItemStack, vBlockPos2);
						if (pPlayer instanceof ServerPlayer) {
							CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer)pPlayer, vBlockPos2, vItemStack);
						}
						
						pPlayer.awardStat(Stats.ITEM_USED.get(this));
						return InteractionResultHolder.sidedSuccess(getEmptySuccessItem(vItemStack, pPlayer), pLevel.isClientSide());
					} else {
						return InteractionResultHolder.fail(vItemStack);
					}
				}
			} else {
				return InteractionResultHolder.fail(vItemStack);
			}
		}
	}
	
	public static ItemStack getEmptySuccessItem(ItemStack stack, Player player) {
		return !player.getAbilities().instabuild ? new ItemStack(RegistryFabric.COPPER_BUCKET) : stack;
	}
	
	public void checkExtraContent(@Nullable Player $$0, Level $$1, ItemStack $$2, BlockPos $$3) {
	}
	
	public boolean emptyContents(@Nullable Player player, Level level, BlockPos pos, @Nullable BlockHitResult result) {
		if (!(this.content instanceof FlowingFluid)) {
			return false;
		} else {
			BlockState state = level.getBlockState(pos);
			Block block = state.getBlock();
			boolean $$6 = state.canBeReplaced(this.content);
			boolean $$7 = state.isAir() || $$6 || block instanceof LiquidBlockContainer && ((LiquidBlockContainer)block).canPlaceLiquid(player, level, pos, state, this.content);
			if (!$$7) {
				return result != null && this.emptyContents(player, level, result.getBlockPos().relative(result.getDirection()), (BlockHitResult)null);
			} else if (level.dimensionType().ultraWarm() && this.content.is(FluidTags.WATER)) {
				int $$8 = pos.getX();
				int $$9 = pos.getY();
				int $$10 = pos.getZ();
				level.playSound(player, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 0.5F, 2.6F + (level.random.nextFloat() - level.random.nextFloat()) * 0.8F);
				
				for(int $$11 = 0; $$11 < 8; ++$$11) {
					level.addParticle(ParticleTypes.LARGE_SMOKE, (double)$$8 + Math.random(), (double)$$9 + Math.random(), (double)$$10 + Math.random(), 0.0, 0.0, 0.0);
				}
				
				return true;
			} else if (block instanceof LiquidBlockContainer && this.content == Fluids.WATER) {
				((LiquidBlockContainer)block).placeLiquid(level, pos, state, ((FlowingFluid)this.content).getSource(false));
				this.playEmptySound(player, level, pos);
				return true;
			} else {
				if (!level.isClientSide && $$6 && !state.liquid()) {
					level.destroyBlock(pos, true);
				}
				
				if (!level.setBlock(pos, this.content.defaultFluidState().createLegacyBlock(), 11) && !state.getFluidState().isSource()) {
					return false;
				} else {
					this.playEmptySound(player, level, pos);
					return true;
				}
			}
		}
	}
	
	protected void playEmptySound(@Nullable Player $$0, LevelAccessor $$1, BlockPos $$2) {
		SoundEvent $$3 = this.content.is(FluidTags.LAVA) ? SoundEvents.BUCKET_EMPTY_LAVA : SoundEvents.BUCKET_EMPTY;
		$$1.playSound($$0, $$2, $$3, SoundSource.BLOCKS, 1.0F, 1.0F);
		$$1.gameEvent($$0, GameEvent.FLUID_PLACE, $$2);
	}
}
