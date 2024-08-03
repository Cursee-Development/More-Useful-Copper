package com.cursee.more_useful_copper.core.entity.goal;

import com.cursee.more_useful_copper.core.entity.custom.CopperGolemEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class CopperGolemAttackGoal extends MeleeAttackGoal {
	private final CopperGolemEntity entity;
	private int attackDelay = 20;
	private int ticksUntilNextAttack = 20;
	private boolean shouldCountTillNextAttack = false;
	
	public CopperGolemAttackGoal(PathfinderMob pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
		super(pMob, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
		entity = ((CopperGolemEntity) pMob);
	}
	
	@Override
	public void start() {
		super.start();
		attackDelay = 20;
		ticksUntilNextAttack = 20;
	}

	@Override
	protected void checkAndPerformAttack(LivingEntity pEnemy, double d) {
		if (isEnemyWithinAttackDistance(pEnemy, 2.5D)) {
			shouldCountTillNextAttack = true;

			if(isTimeToStartAttackAnimation()) {
				entity.setAttacking(true);
			}

			if(isTimeToAttack()) {
				this.mob.getLookControl().setLookAt(pEnemy.getX(), pEnemy.getEyeY(), pEnemy.getZ());
				performAttack(pEnemy);
			}
		} else {
			resetAttackCooldown();
			shouldCountTillNextAttack = false;
			entity.setAttacking(false);
			entity.attackAnimationTimeout = 0;
		}
	}

//	@Override
//	protected void checkAndPerformAttack(LivingEntity pEnemy) {
//		if (isEnemyWithinAttackDistance(pEnemy, 2.5D)) {
//			shouldCountTillNextAttack = true;
//
//			if(isTimeToStartAttackAnimation()) {
//				entity.setAttacking(true);
//			}
//
//			if(isTimeToAttack()) {
//				this.mob.getLookControl().setLookAt(pEnemy.getX(), pEnemy.getEyeY(), pEnemy.getZ());
//				performAttack(pEnemy);
//			}
//		} else {
//			resetAttackCooldown();
//			shouldCountTillNextAttack = false;
//			entity.setAttacking(false);
//			entity.attackAnimationTimeout = 0;
//		}
//	}
	
	private boolean isEnemyWithinAttackDistance(LivingEntity pEnemy, double pDistToEnemySqr) {
		return pDistToEnemySqr <= 2.5D;
	}
	
	protected void resetAttackCooldown() {
		this.ticksUntilNextAttack = this.adjustedTickDelay(attackDelay * 2);
	}
	
	protected boolean isTimeToAttack() {
		return this.ticksUntilNextAttack <= 0;
	}
	
	protected boolean isTimeToStartAttackAnimation() {
		return this.ticksUntilNextAttack <= attackDelay;
	}
	
	protected int getTicksUntilNextAttack() {
		return this.ticksUntilNextAttack;
	}
	
	
	protected void performAttack(LivingEntity pEnemy) {
		this.resetAttackCooldown();
		this.mob.swing(InteractionHand.MAIN_HAND);
		// this.mob.swing(InteractionHand.OFF_HAND);
		this.mob.doHurtTarget(pEnemy);
	}
	
	@Override
	public void tick() {
		super.tick();
		if(shouldCountTillNextAttack) {
			this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
		}
	}
	
	
	@Override
	public void stop() {
		entity.setAttacking(false);
		super.stop();
	}
}
