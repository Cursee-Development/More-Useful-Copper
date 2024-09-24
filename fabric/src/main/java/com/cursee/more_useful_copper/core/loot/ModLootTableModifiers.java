package com.cursee.more_useful_copper.core.loot;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.resources.ResourceLocation;

public class ModLootTableModifiers {
	
	private static final ResourceLocation DESERT_PYRAMID = ResourceLocation.fromNamespaceAndPath("minecraft", "chests/desert_pyramid");
	private static final ResourceLocation END_CITY_TREASURE = ResourceLocation.fromNamespaceAndPath("minecraft", "chests/end_city_treasure");
	private static final ResourceLocation JUNGLE_TEMPLE = ResourceLocation.fromNamespaceAndPath("minecraft", "chests/jungle_temple");
	private static final ResourceLocation NETHER_BRIDGE = ResourceLocation.fromNamespaceAndPath("minecraft", "chests/nether_bridge");
	private static final ResourceLocation SIMPLE_DUNGEON = ResourceLocation.fromNamespaceAndPath("minecraft", "chests/simple_dungeon");
	private static final ResourceLocation STRONGHOLD_CORRIDOR = ResourceLocation.fromNamespaceAndPath("minecraft", "chests/stronghold_corridor");
	private static final ResourceLocation VILLAGE_WEAPONSMITH = ResourceLocation.fromNamespaceAndPath("minecraft", "chests/village/village_weaponsmith");
	
	public static void modifyLootTables() {
		LootTableEvents.MODIFY.register(((resourceManager, lootManager, id, tableBuilder) -> {
			
//			if(DESERT_PYRAMID.equals(id.name())) {
//				LootPool.Builder poolBuilder = LootPool.lootPool()
//					.setRolls(ConstantValue.exactly(1.0F))
//					.conditionally(LootItemRandomChanceCondition.randomChance(0.35f).build()) // Drops 35% of the time
//					.with(LootItem.lootTableItem(FabricRegistry.COPPER_HORSE_ARMOR).build())
//					.apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f)).build());
//
//				tableBuilder.pool(poolBuilder.build());
//			}
//			if(END_CITY_TREASURE.equals(id)) {
//				LootPool.Builder poolBuilder = LootPool.lootPool()
//					.setRolls(ConstantValue.exactly(1.0F))
//					.conditionally(LootItemRandomChanceCondition.randomChance(0.35f).build()) // Drops 35% of the time
//					.with(LootItem.lootTableItem(FabricRegistry.COPPER_HORSE_ARMOR).build())
//					.apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f)).build());
//
//				tableBuilder.pool(poolBuilder.build());
//			}
//			if(JUNGLE_TEMPLE.equals(id)) {
//				LootPool.Builder poolBuilder = LootPool.lootPool()
//					.setRolls(ConstantValue.exactly(1.0F))
//					.conditionally(LootItemRandomChanceCondition.randomChance(0.35f).build()) // Drops 35% of the time
//					.with(LootItem.lootTableItem(FabricRegistry.COPPER_HORSE_ARMOR).build())
//					.apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f)).build());
//
//				tableBuilder.pool(poolBuilder.build());
//			}
//			if(NETHER_BRIDGE.equals(id)) {
//				LootPool.Builder poolBuilder = LootPool.lootPool()
//					.setRolls(ConstantValue.exactly(1.0F))
//					.conditionally(LootItemRandomChanceCondition.randomChance(0.35f).build()) // Drops 35% of the time
//					.with(LootItem.lootTableItem(FabricRegistry.COPPER_HORSE_ARMOR).build())
//					.apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f)).build());
//
//				tableBuilder.pool(poolBuilder.build());
//			}
//			if(SIMPLE_DUNGEON.equals(id)) {
//				LootPool.Builder poolBuilder = LootPool.lootPool()
//					.setRolls(ConstantValue.exactly(1.0F))
//					.conditionally(LootItemRandomChanceCondition.randomChance(0.35f).build()) // Drops 35% of the time
//					.with(LootItem.lootTableItem(FabricRegistry.COPPER_HORSE_ARMOR).build())
//					.apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f)).build());
//
//				tableBuilder.pool(poolBuilder.build());
//			}
//			if(STRONGHOLD_CORRIDOR.equals(id)) {
//				LootPool.Builder poolBuilder = LootPool.lootPool()
//					.setRolls(ConstantValue.exactly(1.0F))
//					.conditionally(LootItemRandomChanceCondition.randomChance(0.35f).build()) // Drops 35% of the time
//					.with(LootItem.lootTableItem(FabricRegistry.COPPER_HORSE_ARMOR).build())
//					.apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f)).build());
//
//				tableBuilder.pool(poolBuilder.build());
//			}
//			if(VILLAGE_WEAPONSMITH.equals(id)) {
//				LootPool.Builder poolBuilder = LootPool.lootPool()
//					.setRolls(ConstantValue.exactly(1.0F))
//					.conditionally(LootItemRandomChanceCondition.randomChance(0.35f).build()) // Drops 35% of the time
//					.with(LootItem.lootTableItem(FabricRegistry.COPPER_HORSE_ARMOR).build())
//					.apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f)).build());
//
//				tableBuilder.pool(poolBuilder.build());
//			}
			
			
			
//			if (DESERT_PYRAMID.equals(id)) {
//				LootPool.Builder builder = LootPool.lootPool()
//					.setRolls(ConstantValue.exactly(1))
//					.conditionally((LootItemCondition) LootItemRandomChanceCondition.randomChance(1.0F))
//					.with((Collection<? extends LootPoolEntryContainer>) LootItem.lootTableItem(FabricRegistry.COPPER_HORSE_ARMOR))
//					.apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f)));
//				tableBuilder.pool(builder.build());
//			}
//			if (END_CITY_TREASURE.equals(id)) {
//				LootPool.Builder builder = LootPool.lootPool()
//					.setRolls(ConstantValue.exactly(1))
//					.conditionally((LootItemCondition) LootItemRandomChanceCondition.randomChance(1.0F))
//					.with((Collection<? extends LootPoolEntryContainer>) LootItem.lootTableItem(FabricRegistry.COPPER_HORSE_ARMOR))
//					.apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f)));
//				tableBuilder.pool(builder.build());
//			}
//			if (JUNGLE_TEMPLE.equals(id)) {
//				LootPool.Builder builder = LootPool.lootPool()
//					.setRolls(ConstantValue.exactly(1))
//					.conditionally((LootItemCondition) LootItemRandomChanceCondition.randomChance(1.0F))
//					.with((Collection<? extends LootPoolEntryContainer>) LootItem.lootTableItem(FabricRegistry.COPPER_HORSE_ARMOR))
//					.apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f)));
//				tableBuilder.pool(builder.build());
//			}
//			if (NETHER_BRIDGE.equals(id)) {
//				LootPool.Builder builder = LootPool.lootPool()
//					.setRolls(ConstantValue.exactly(1))
//					.conditionally((LootItemCondition) LootItemRandomChanceCondition.randomChance(1.0F))
//					.with((Collection<? extends LootPoolEntryContainer>) LootItem.lootTableItem(FabricRegistry.COPPER_HORSE_ARMOR))
//					.apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f)));
//				tableBuilder.pool(builder.build());
//			}
//			if (SIMPLE_DUNGEON.equals(id)) {
//				LootPool.Builder builder = LootPool.lootPool()
//					.setRolls(ConstantValue.exactly(1))
//					.conditionally((LootItemCondition) LootItemRandomChanceCondition.randomChance(1.0F))
//					.with((Collection<? extends LootPoolEntryContainer>) LootItem.lootTableItem(FabricRegistry.COPPER_HORSE_ARMOR))
//					.apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f)));
//				tableBuilder.pool(builder.build());
//			}
//			if (STRONGHOLD_CORRIDOR.equals(id)) {
//				LootPool.Builder builder = LootPool.lootPool()
//					.setRolls(ConstantValue.exactly(1))
//					.conditionally((LootItemCondition) LootItemRandomChanceCondition.randomChance(1.0F))
//					.with((Collection<? extends LootPoolEntryContainer>) LootItem.lootTableItem(FabricRegistry.COPPER_HORSE_ARMOR))
//					.apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f)));
//				tableBuilder.pool(builder.build());
//			}
//			if (VILLAGE_WEAPONSMITH.equals(id)) {
//				LootPool.Builder builder = LootPool.lootPool()
//					.setRolls(ConstantValue.exactly(1))
//					.conditionally((LootItemCondition) LootItemRandomChanceCondition.randomChance(1.0F))
//					.with((Collection<? extends LootPoolEntryContainer>) LootItem.lootTableItem(FabricRegistry.COPPER_HORSE_ARMOR))
//					.apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f, 2.0f)));
//				tableBuilder.pool(builder.build());
//			}
		}));
	}
}
