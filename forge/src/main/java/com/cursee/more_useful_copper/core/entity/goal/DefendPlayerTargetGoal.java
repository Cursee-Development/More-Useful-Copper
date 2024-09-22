package com.cursee.more_useful_copper.core.entity.goal;

import com.cursee.more_useful_copper.core.entity.custom.CopperGolemEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

public class DefendPlayerTargetGoal extends TargetGoal {
	private final CopperGolemEntity golem;
	@Nullable
	private LivingEntity potentialTarget;
	private final TargetingConditions attackTargeting = TargetingConditions.forCombat().range(64.0);
	
	public DefendPlayerTargetGoal(CopperGolemEntity p_26029_) {
		super(p_26029_, false, true);
		this.golem = p_26029_;
		this.setFlags(EnumSet.of(Flag.TARGET));
	}
	
	public boolean canUse() {
		AABB targetedBoundingBox = this.golem.getBoundingBox().inflate(10.0, 8.0, 10.0);
		List<Player> playersNearby = this.golem.level().getNearbyPlayers(this.attackTargeting, this.golem, targetedBoundingBox);
		Iterator players = playersNearby.iterator();
		
		while (players.hasNext()) {
			Player player = (Player) players.next();
			
			LivingEntity lastAttacker = player.getLastAttacker() == null? player.getLastAttacker() : null;
			
			// if (player.getLastAttacker() != null) {
			// 	lastAttacker = player.getLastAttacker();
			// } else {
			// 	lastAttacker = null;
			// }
			
			if (lastAttacker != null && lastAttacker.isDeadOrDying()) {
				this.potentialTarget = lastAttacker;
			}
		}
		
		if (this.potentialTarget == null) {
			return false;
		} else if (!(this.potentialTarget instanceof Player) || !this.potentialTarget.isSpectator() && !((Player)this.potentialTarget).isCreative()) {
			return true;
		} else {
			return false;
		}
	}
	
	public void start() {
		this.golem.setTarget(this.potentialTarget);
		super.start();
	}
}

