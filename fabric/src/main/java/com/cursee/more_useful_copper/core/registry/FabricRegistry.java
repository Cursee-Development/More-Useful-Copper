package com.cursee.more_useful_copper.core.registry;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import com.cursee.more_useful_copper.Constants;
import com.cursee.more_useful_copper.core.content.CopperBucketItem;
import com.cursee.more_useful_copper.core.content.CopperMilkBucketItem;
import com.cursee.more_useful_copper.core.content.CopperSolidBucketItem;
import com.cursee.more_useful_copper.core.entity.custom.CopperGolemEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class FabricRegistry {
	
	// Registration Methods
	private static Block registerBlock(String name, Block block) {
		Block toReturn = Registry.register(BuiltInRegistries.BLOCK,  new ResourceLocation(Constants.MOD_ID, name), block);
		registerBlockItem(name, toReturn);
		return toReturn;
	}
	private static Item registerBlockItem(String name, Block block) {
		return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Constants.MOD_ID, name), new BlockItem(block, new Item.Properties()));
	}
	public static Item registerItem(String name, Item item) {
		return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Constants.MOD_ID, name), item);
	}
	
	// Define Blocks
	public static final Block COPPER_CHAIN;
	public static final Block COPPER_BUTTON;
	public static final Block COPPER_PRESSURE_PLATE;
	
	// Define Entity
	public static final EntityType<CopperGolemEntity> COPPER_GOLEM;
	
	// Define Items
	public static final Item COPPER_SWORD;
	public static final Item COPPER_SHOVEL;
	public static final Item COPPER_PICKAXE;
	public static final Item COPPER_AXE;
	public static final Item COPPER_HOE;
	
	public static final Item COPPER_BUCKET;
	public static final Item COPPER_WATER_BUCKET;
	public static final Item COPPER_MILK_BUCKET;
	public static final Item COPPER_POWDER_SNOW_BUCKET;
	
	public static final Item COPPER_SHEARS;
	public static final Item COPPER_GOLEM_SPAWN_EGG;
	
	public static final Item COPPER_HORSE_ARMOR;
	public static final Item COPPER_HELMET;
	public static final Item COPPER_CHESTPLATE;
	public static final Item COPPER_LEGGINGS;
	public static final Item COPPER_BOOTS;

	public static final Item COPPER_NUGGET;

	// Define CreativeModeTab
	public static final CreativeModeTab MOREUSEFULCOPPER_TAB;
	
	// Actual Registration
	static {
		// Blocks
		COPPER_CHAIN = registerBlock("copper_chain", new ChainBlock(BlockBehaviour.Properties.copy(Blocks.CHAIN)));
		COPPER_BUTTON = registerBlock("copper_button", copperButton());
		COPPER_PRESSURE_PLATE = registerBlock("copper_pressure_plate", new WeightedPressurePlateBlock(150, BlockBehaviour.Properties.of().mapColor(MapColor.METAL).forceSolidOn().requiresCorrectToolForDrops().noCollission().strength(0.5F).pushReaction(PushReaction.DESTROY), BlockSetType.IRON));
		
		// Entity
		COPPER_GOLEM = Registry.register(BuiltInRegistries.ENTITY_TYPE, new ResourceLocation(Constants.MOD_ID, "copper_golem").toString(),
			FabricEntityTypeBuilder.create(MobCategory.MISC, CopperGolemEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).trackRangeBlocks(10).build());
		
		// Items
		COPPER_SWORD = registerItem("copper_sword", new SwordItem(ModWeaponTiers.COPPER, 3, -2.4F, new Item.Properties().stacksTo(1)));
		COPPER_SHOVEL = registerItem("copper_shovel", new ShovelItem(ModWeaponTiers.COPPER, 1.5F, -3.0F, new Item.Properties().stacksTo(1)));
		COPPER_PICKAXE = registerItem("copper_pickaxe", new PickaxeItem(ModWeaponTiers.COPPER, 1, -2.8F, new Item.Properties().stacksTo(1)));
		COPPER_AXE = registerItem("copper_axe", new AxeItem(ModWeaponTiers.COPPER, 6.0F, -3.1F, new Item.Properties().stacksTo(1)));
		COPPER_HOE = registerItem("copper_hoe", new HoeItem(ModWeaponTiers.COPPER, -2, -1.0F, new Item.Properties().stacksTo(1)));
		
		COPPER_BUCKET = registerItem("copper_bucket", new CopperBucketItem(Fluids.EMPTY, new Item.Properties().stacksTo(16)));
		COPPER_WATER_BUCKET = registerItem("copper_water_bucket", new CopperBucketItem(Fluids.WATER, new Item.Properties().craftRemainder(COPPER_BUCKET).stacksTo(1)));
		COPPER_MILK_BUCKET = registerItem("copper_milk_bucket", new CopperMilkBucketItem(new Item.Properties().craftRemainder(COPPER_BUCKET).stacksTo(1)));
		COPPER_POWDER_SNOW_BUCKET = registerItem((String)"copper_powder_snow_bucket", new CopperSolidBucketItem(Blocks.POWDER_SNOW, SoundEvents.BUCKET_EMPTY_POWDER_SNOW, (new Item.Properties()).stacksTo(1)));
		COPPER_SHEARS = registerItem("copper_shears", new ShearsItem(new Item.Properties().durability(238)));
		COPPER_GOLEM_SPAWN_EGG = registerItem("copper_golem_spawn_egg", new SpawnEggItem(COPPER_GOLEM, 0x6D3421, 0xE77C56, new Item.Properties()));
		
		COPPER_HORSE_ARMOR = registerItem("copper_horse_armor", new HorseArmorItem(5, "copper", new Item.Properties().stacksTo(1)));
		COPPER_HELMET = registerItem("copper_helmet", new ArmorItem(ModArmorMaterials.COPPER, ArmorItem.Type.HELMET, new Item.Properties().stacksTo(1)));
		COPPER_CHESTPLATE = registerItem("copper_chestplate", new ArmorItem(ModArmorMaterials.COPPER, ArmorItem.Type.CHESTPLATE, new Item.Properties().stacksTo(1)));
		COPPER_LEGGINGS = registerItem("copper_leggings", new ArmorItem(ModArmorMaterials.COPPER, ArmorItem.Type.LEGGINGS, new Item.Properties().stacksTo(1)));
		COPPER_BOOTS = registerItem("copper_boots", new ArmorItem(ModArmorMaterials.COPPER, ArmorItem.Type.BOOTS, new Item.Properties().stacksTo(1)));

		COPPER_NUGGET = registerItem("copper_nugget", new Item(new Item.Properties()));
		
		
		// CreativeModeTab
		MOREUSEFULCOPPER_TAB = FabricItemGroup.builder()
			.icon(() -> new ItemStack(COPPER_PICKAXE))
			.title(Component.translatable("itemGroup.moreUsefulCopper"))
			.displayItems((itemDisplayParameters, output) -> {
				output.accept(COPPER_SWORD);
				output.accept(COPPER_SHOVEL);
				output.accept(COPPER_PICKAXE);
				output.accept(COPPER_AXE);
				output.accept(COPPER_HOE);
				
				output.accept(COPPER_BUCKET);
				output.accept(COPPER_WATER_BUCKET);
				output.accept(COPPER_MILK_BUCKET);
				output.accept(COPPER_POWDER_SNOW_BUCKET);
				output.accept(COPPER_SHEARS);
				output.accept(COPPER_GOLEM_SPAWN_EGG);
				
				output.accept(COPPER_HORSE_ARMOR);
				output.accept(COPPER_HELMET);
				output.accept(COPPER_CHESTPLATE);
				output.accept(COPPER_LEGGINGS);
				output.accept(COPPER_BOOTS);
				
				output.accept(COPPER_CHAIN);
				output.accept(COPPER_BUTTON);
				output.accept(COPPER_PRESSURE_PLATE);
				output.accept(COPPER_NUGGET);
			})
			.build();
	}
	
	public static void register() {
		Constants.LOG.info("FabricRegistry for " + Constants.MOD_NAME);
		Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, new ResourceLocation(Constants.MOD_ID, "moreusefulcopper_tab"), MOREUSEFULCOPPER_TAB);
	}
	
	private static ButtonBlock copperButton() {
		return new ButtonBlock(BlockBehaviour.Properties.of().noCollission().requiresCorrectToolForDrops() .strength(0.5F).pushReaction(PushReaction.DESTROY), BlockSetType.STONE, 20, false);
	}
}
