package com.cursee.more_useful_copper.core.entity.goal;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class FollowPlayerGoal extends Goal {
	private static final TargetingConditions TEMP_TARGETING = TargetingConditions.forNonCombat().range(10.0).ignoreLineOfSight();
	private final TargetingConditions targetingConditions;
	protected final PathfinderMob mob;
	private final double speedModifier;
	private double px;
	private double py;
	private double pz;
	private double pRotX;
	private double pRotY;
	@Nullable
	protected Player player;
	private int calmDown;
	private boolean isRunning;
	
	public FollowPlayerGoal(PathfinderMob p_25939_, double p_25940_) {
		this.mob = p_25939_;
		this.speedModifier = p_25940_;
		this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
		this.targetingConditions = TEMP_TARGETING.copy().selector(this::shouldFollow);
	}
	
	public boolean canUse() {
		if (this.calmDown > 0) {
			--this.calmDown;
			return false;
		} else {
			this.player = this.mob.level().getNearestPlayer(this.targetingConditions, this.mob);
			return this.player != null;
		}
	}
	
	private boolean shouldFollow(LivingEntity pLiving) {
		return pLiving instanceof Player; // this.items.test(pLiving.getMainHandItem()) || this.items.test(pLiving.getOffhandItem());
	}
	
	public boolean canContinueToUse() {
		return this.canUse();
	}
	
	public void start() {
		this.px = this.player.getX();
		this.py = this.player.getY();
		this.pz = this.player.getZ();
		this.isRunning = true;
	}
	
	public void stop() {
		this.player = null;
		this.mob.getNavigation().stop();
		this.calmDown = reducedTickDelay(100);
		this.isRunning = false;
	}
	
	public void tick() {
		this.mob.getLookControl().setLookAt(this.player, (float)(this.mob.getMaxHeadYRot() + 20), (float)this.mob.getMaxHeadXRot());
		if (this.mob.distanceToSqr(this.player) < 8.0D) {
			this.mob.getNavigation().stop();
		} else {
			this.mob.getNavigation().moveTo(this.player, this.speedModifier);
		}
		
	}
	
	public boolean isRunning() {
		return this.isRunning;
	}
}
