package com.cursee.more_useful_copper.core.entity.goal;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.List;

public class MoveTowardsClosestPlayerGoal extends Goal {
	private final PathfinderMob mob;
	@Nullable
	private LivingEntity target;
	private double wantedX;
	private double wantedY;
	private double wantedZ;
	private final double speedModifier;
	private final float within;
	
	public MoveTowardsClosestPlayerGoal(PathfinderMob p_25646_, double p_25647_, float p_25648_) {
		this.mob = p_25646_;
		this.speedModifier = p_25647_;
		this.within = p_25648_;
		this.setFlags(EnumSet.of(Flag.MOVE));
	}
	
	public boolean canUse() {
		this.target = this.mob.getTarget();
		
		if (this.target == null) {
			return false;
		}
		else if (this.target.distanceToSqr(this.mob) > (double)(this.within * this.within)) {
			return false;
		} // if there was no target or we're close enough to the target, return false
		else {
			
			AABB targetedBoundingBox = this.mob.getBoundingBox().inflate(10.0, 8.0, 10.0);
			List<Player> playersNearby = this.mob.level().getNearbyPlayers(TargetingConditions.DEFAULT, this.mob, targetedBoundingBox);
			
			if (!playersNearby.isEmpty()) {
				this.target = playersNearby.get(0);
			}
			
			Vec3 towardsTargetPosition = DefaultRandomPos.getPosTowards(this.mob, 16, 16, this.target.position(), 1.5707963705062866);
			if (towardsTargetPosition == null) {
				return false;
			} else {
				this.wantedX = towardsTargetPosition.x;
				this.wantedY = towardsTargetPosition.y;
				this.wantedZ = towardsTargetPosition.z;
				return true;
			}
		}
	}
	
	public boolean canContinueToUse() {
		return !this.mob.getNavigation().isDone() && this.target.isAlive() && this.target.distanceToSqr(this.mob) < (double)(this.within * this.within);
	}
	
	public void stop() {
		this.target = null;
	}
	
	public void start() {
		this.mob.getNavigation().moveTo(this.wantedX, this.wantedY, this.wantedZ, this.speedModifier);
	}
}

