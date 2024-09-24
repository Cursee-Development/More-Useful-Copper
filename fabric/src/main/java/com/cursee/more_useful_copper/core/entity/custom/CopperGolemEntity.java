package com.cursee.more_useful_copper.core.entity.custom;

import com.cursee.more_useful_copper.core.entity.goal.CopperGolemAttackGoal;
import com.cursee.more_useful_copper.core.entity.goal.DefendPlayerTargetGoal;
import com.cursee.more_useful_copper.core.entity.goal.FollowPlayerGoal;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MoveTowardsTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class CopperGolemEntity extends PathfinderMob implements NeutralMob {
	
	private static final EntityDataAccessor<Boolean> ATTACKING = SynchedEntityData.defineId(CopperGolemEntity.class, EntityDataSerializers.BOOLEAN);
	
	public final AnimationState attackAnimationState = new AnimationState();
	public int attackAnimationTimeout = 0;
	
	public static final  ItemLike[] tempting = new ItemLike[]{
		Items.DANDELION,
		Items.POPPY,
		Items.BLUE_ORCHID,
		Items.ALLIUM,
		Items.AZURE_BLUET,
		Items.RED_TULIP,
		Items.ORANGE_TULIP,
		Items.WHITE_TULIP,
		Items.PINK_TULIP,
		Items.OXEYE_DAISY,
		Items.CORNFLOWER,
		Items.LILY_OF_THE_VALLEY,
		Items.SUNFLOWER,
		Items.LILAC,
		Items.ROSE_BUSH,
		Items.PEONY
	};
	
	public CopperGolemEntity(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
		super(p_21683_, p_21684_);
	}
	
	@Override
	protected void registerGoals() {
		//
		// Golem should follow player and attack nearby hostile mobs. That's it.
		//
		this.goalSelector.addGoal(1, new CopperGolemAttackGoal(this, 1.0, true));
		this.goalSelector.addGoal(2, new MoveTowardsTargetGoal(this, 0.9, 16.0F));
		
		this.goalSelector.addGoal(3, new FollowPlayerGoal(this, 1.05D));
		
		// this.goalSelector.addGoal(3, new TemptGoal(this, 1.1, Ingredient.of(tempting), false));
		
		// this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D, 1.0F));
		
		// this.goalSelector.addGoal(3, new MoveTowardsClosestPlayerGoal(this, 0.9, 4.0F));
		// this.goalSelector.addGoal(2, new MoveBackToVillageGoal(this, 0.6, false));
		// this.goalSelector.addGoal(4, new GolemRandomStrollInVillageGoal(this, 0.6));
		this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
		
		
		this.targetSelector.addGoal(1, new DefendPlayerTargetGoal(this));
		
		
		this.targetSelector.addGoal(2, new HurtByTargetGoal(this, new Class[0]));
		
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, Mob.class, 5, false, false, ($$0) -> {
			return $$0 instanceof Enemy && !($$0 instanceof Creeper);
		}));
		this.targetSelector.addGoal(4, new ResetUniversalAngerTargetGoal(this, false));
	}
	
	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes()
			.add(Attributes.MAX_HEALTH, 100.0)
			.add(Attributes.MOVEMENT_SPEED, 0.25)
			.add(Attributes.KNOCKBACK_RESISTANCE, 1.0)
			.add(Attributes.ATTACK_DAMAGE, 15.0);
	}
	
	@Override
	public int getRemainingPersistentAngerTime() {
		return 0;
	}
	
	@Override
	public void setRemainingPersistentAngerTime(int i) {
	
	}
	
	@Nullable
	@Override
	public UUID getPersistentAngerTarget() {
		return null;
	}
	
	@Override
	public void setPersistentAngerTarget(@Nullable UUID uuid) {
	
	}
	
	@Override
	public void startPersistentAngerTimer() {
	
	}
	
	private void setupAnimationStates() {
		if (this.isAttacking() && attackAnimationTimeout <=0) {
			attackAnimationTimeout = 20;
			attackAnimationState.start(this.tickCount);
		}
		else {
			this.attackAnimationTimeout--;
		}
		
		if (!this.isAttacking()) {
			attackAnimationState.stop();
		}
	}
	
	protected void updateWalkAnimation(float v) {
		float f;
		if (this.getPose() == Pose.STANDING) {
			f = Math.min(v * 6.0F, 1.0F);
		}
		else {
			f = 0.0F;
		}
		
		this.walkAnimation.update(f, 0.2f);
	}
	
	@Override
	public void tick() {
		super.tick();
		
		if (this.level().isClientSide()) {
			this.setupAnimationStates();
		}
	}
	
	public void setAttacking(boolean attacking) {
		this.entityData.set(ATTACKING, attacking);
	}
	
	public boolean isAttacking() {
		return this.entityData.get(ATTACKING);
	}
	
//	@Override
//	protected void defineSynchedData() {
//		super.defineSynchedData();
//		this.entityData.define(ATTACKING, false);
//	}


	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		super.defineSynchedData(builder);
		builder.define(ATTACKING, false);
	}
}
