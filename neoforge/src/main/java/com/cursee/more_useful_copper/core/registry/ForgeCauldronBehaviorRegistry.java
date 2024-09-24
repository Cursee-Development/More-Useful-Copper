package com.cursee.more_useful_copper.core.registry;

import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMaps;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.world.item.Item;

import java.util.Map;

import static net.minecraft.core.cauldron.CauldronInteraction.FILL_WATER;

public class ForgeCauldronBehaviorRegistry {
	public static Object2ObjectMap<Item, CauldronInteraction> CAULDRON_WATER_MAP = new Object2ObjectOpenHashMap();
	
	public static void registerCopperBucketToWaterMap() {
		Map<Item, CauldronInteraction> syncronizedCauldronMap = Object2ObjectMaps.synchronize(CAULDRON_WATER_MAP);
		
		// CAULDRON_WATER_MAP.put(Items.BUCKET, (p_175725_, p_175726_, p_175727_, p_175728_, p_175729_, p_175730_) -> {
		// 	return fillBucket(p_175725_, p_175726_, p_175727_, p_175728_, p_175729_, p_175730_, new ItemStack(ForgeRegistry.COPPER_WATER_BUCKET.get()), (p_175660_) -> {
		// 		return (Integer)p_175660_.getValue(LayeredCauldronBlock.LEVEL) == 3;
		// 	}, SoundEvents.BUCKET_FILL);
		// });
		
		CAULDRON_WATER_MAP.put(RegistryNeoForge.COPPER_WATER_BUCKET.get(), FILL_WATER);
		
		// CauldronInteraction.WATER.putAll(syncronizedCauldronMap);
	}
}
