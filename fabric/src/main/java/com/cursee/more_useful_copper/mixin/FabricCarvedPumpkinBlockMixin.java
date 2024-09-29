package com.cursee.more_useful_copper.mixin;

//import net.jason13.moreusefulcopper.entity.custom.CopperGolemEntity;
//import net.jason13.moreusefulcopper.registry.ForgeRegistry;
import com.cursee.more_useful_copper.core.entity.custom.CopperGolemEntity;
import com.cursee.more_useful_copper.core.registry.FabricRegistry;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarvedPumpkinBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.function.Predicate;

import static net.minecraft.world.level.block.CarvedPumpkinBlock.clearPatternBlocks;
import static net.minecraft.world.level.block.CarvedPumpkinBlock.updatePatternBlocks;

@Mixin(CarvedPumpkinBlock.class)
public class FabricCarvedPumpkinBlockMixin {
	
	private static final Predicate<BlockState> PUMPKINS_PREDICATE = (state) -> state != null && (state.is(Blocks.CARVED_PUMPKIN) || state.is(Blocks.JACK_O_LANTERN));
	
	@Unique @Nullable private BlockPattern ForgeCarvedPumpkinBlockMixin$ironGolemBase;
	@Unique @Nullable private BlockPattern ForgeCarvedPumpkinBlockMixin$ironGolemFull;
	
	@Inject(method = "trySpawnGolem", at = @At("HEAD"))
	private void injected(Level p_51379_, BlockPos p_51380_, CallbackInfo ci) {
		BlockPattern.BlockPatternMatch blocksInPattern = this.moreUsefulCopper$_getOrCreateIronGolemFull().find(p_51379_, p_51380_); // specifically references this Mixin class, not the Block's instance
		if (blocksInPattern != null) {
			CopperGolemEntity golem = (CopperGolemEntity) FabricRegistry.COPPER_GOLEM.create(p_51379_);
			if (golem != null) {
				// golem.setPlayerCreated(true);
				moreUsefulCopper$_spawnGolemInWorld(p_51379_, blocksInPattern, golem, blocksInPattern.getBlock(1, 2, 0).getPos());
			}
		}
	}
	
	@Unique
	private BlockPattern moreUsefulCopper$_getOrCreateIronGolemFull() {
		if (this.ForgeCarvedPumpkinBlockMixin$ironGolemFull == null) {
			this.ForgeCarvedPumpkinBlockMixin$ironGolemFull = BlockPatternBuilder.start().aisle(new String[]{"~^~", "###", "~#~"}).where('^', BlockInWorld.hasState(PUMPKINS_PREDICATE)).where('#', BlockInWorld.hasState(BlockStatePredicate.forBlock(Blocks.COPPER_BLOCK))).where('~', (p_284868_) -> {
				return p_284868_.getState().isAir();
			}).build();
		}
		
		return this.ForgeCarvedPumpkinBlockMixin$ironGolemFull;
	}
	
	@Unique
	private static void moreUsefulCopper$_spawnGolemInWorld(Level p_249110_, BlockPattern.BlockPatternMatch p_251293_, Entity p_251251_, BlockPos p_251189_) {
		clearPatternBlocks(p_249110_, p_251293_);
		p_251251_.moveTo((double)p_251189_.getX() + 0.5, (double)p_251189_.getY() + 0.05, (double)p_251189_.getZ() + 0.5, 0.0F, 0.0F);
		p_249110_.addFreshEntity(p_251251_);
		Iterator<ServerPlayer> var4 = p_249110_.getEntitiesOfClass(ServerPlayer.class, p_251251_.getBoundingBox().inflate(5.0)).iterator();
		
		while(var4.hasNext()) {
			ServerPlayer $$4 = (ServerPlayer)var4.next();
			CriteriaTriggers.SUMMONED_ENTITY.trigger($$4, p_251251_);
		}
		
		updatePatternBlocks(p_249110_, p_251293_);
	}
}
