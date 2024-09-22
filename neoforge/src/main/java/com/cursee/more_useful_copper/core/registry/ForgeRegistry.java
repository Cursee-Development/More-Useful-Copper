package com.cursee.more_useful_copper.core.registry;

import com.cursee.more_useful_copper.Constants;
import com.cursee.more_useful_copper.core.content.CopperBucketItem;
import com.cursee.more_useful_copper.core.content.CopperMilkBucketItem;
import com.cursee.more_useful_copper.core.content.CopperSolidBucketItem;
import com.cursee.more_useful_copper.core.entity.custom.CopperGolemEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ForgeRegistry {
	
	// Registries
	public static DeferredRegister<Block> BLOCK;
	public static DeferredRegister<Item> ITEM;
	public static DeferredRegister<EntityType<?>> ENTITY_TYPE;
	public static DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB;
	
	// BlockItem Registration Methods
	private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
		RegistryObject<T> toReturn = BLOCK.register(name, block);
		registerBlockItem(name, toReturn);
		return toReturn;
	}
	private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
		return ITEM.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
	}
	// Item Registration Method
	private static <T extends Item> RegistryObject<T> registerItem(String name, Supplier<T> item) {
		return ITEM.register(name, item);
	}
	
	// Define Blocks
	public static final RegistryObject<Block> COPPER_CHAIN;
	public static final RegistryObject<Block> COPPER_BUTTON;
	public static final RegistryObject<Block> COPPER_PRESSURE_PLATE;
	
	// Define Entity
	public static final RegistryObject<EntityType<CopperGolemEntity>> COPPER_GOLEM;
	
	// Define Items
	public static final RegistryObject<Item> COPPER_SWORD;
	public static final RegistryObject<Item> COPPER_SHOVEL;
	public static final RegistryObject<Item> COPPER_PICKAXE;
	public static final RegistryObject<Item> COPPER_AXE;
	public static final RegistryObject<Item> COPPER_HOE;
	
	public static final RegistryObject<Item> COPPER_BUCKET;
	public static final RegistryObject<Item> COPPER_WATER_BUCKET;
	public static final RegistryObject<Item> COPPER_MILK_BUCKET;
	public static final RegistryObject<Item> COPPER_POWDER_SNOW_BUCKET;
	
	public static final RegistryObject<Item> COPPER_SHEARS;
	public static final RegistryObject<Item> COPPER_GOLEM_SPAWN_EGG;
	
	public static final RegistryObject<Item> COPPER_HORSE_ARMOR;
	public static final RegistryObject<Item> COPPER_HELMET;
	public static final RegistryObject<Item> COPPER_CHESTPLATE;
	public static final RegistryObject<Item> COPPER_LEGGINGS;
	public static final RegistryObject<Item> COPPER_BOOTS;
	
	// Define CreativeModeTab
	public static final RegistryObject<CreativeModeTab> MOREUSEFULCOPPER_TAB;
	
	// Actual Registration
	static {
		BLOCK = DeferredRegister.create(ForgeRegistries.BLOCKS, Constants.MOD_ID);
		ITEM = DeferredRegister.create(ForgeRegistries.ITEMS, Constants.MOD_ID);
		ENTITY_TYPE = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Constants.MOD_ID);
		CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Constants.MOD_ID);
		
		// Blocks
		COPPER_CHAIN = registerBlock("copper_chain", () -> new ChainBlock(BlockBehaviour.Properties.copy(Blocks.CHAIN)));
		COPPER_BUTTON = registerBlock("copper_button", () -> new ButtonBlock(BlockBehaviour.Properties.of().noCollission().requiresCorrectToolForDrops() .strength(0.5F).pushReaction(PushReaction.DESTROY), BlockSetType.STONE, 20, false));
		COPPER_PRESSURE_PLATE = registerBlock("copper_pressure_plate", () -> new WeightedPressurePlateBlock(150, BlockBehaviour.Properties.of().mapColor(MapColor.METAL).forceSolidOn().requiresCorrectToolForDrops().noCollission().strength(0.5F).pushReaction(PushReaction.DESTROY), BlockSetType.IRON));
		
		// Entity
		COPPER_GOLEM = ENTITY_TYPE.register("", () -> EntityType.Builder.of(CopperGolemEntity::new, MobCategory.MISC).sized(1.4F, 2.7F).clientTrackingRange(10).build("copper_golem"));
		
		// Items
		COPPER_SWORD = registerItem("copper_sword", () -> new SwordItem(ModWeaponTiers.COPPER, 3, -2.4F, new Item.Properties().stacksTo(1)));
		COPPER_SHOVEL = registerItem("copper_shovel", () -> new ShovelItem(ModWeaponTiers.COPPER, 1.5F, -3.0F, new Item.Properties().stacksTo(1)));
		COPPER_PICKAXE = registerItem("copper_pickaxe", () -> new PickaxeItem(ModWeaponTiers.COPPER, 1, -2.8F, new Item.Properties().stacksTo(1)));
		COPPER_AXE = registerItem("copper_axe", () -> new AxeItem(ModWeaponTiers.COPPER, 6.0F, -3.1F, new Item.Properties().stacksTo(1)));
		COPPER_HOE = registerItem("copper_hoe", () -> new HoeItem(ModWeaponTiers.COPPER, -2, -1.0F, new Item.Properties().stacksTo(1)));
		
		
		COPPER_BUCKET = registerItem("copper_bucket", () -> new CopperBucketItem(Fluids.EMPTY, new Item.Properties().stacksTo(16)));
		COPPER_WATER_BUCKET = registerItem("copper_water_bucket", () -> new CopperBucketItem(Fluids.WATER, new Item.Properties().craftRemainder(COPPER_BUCKET.get()).stacksTo(1)));
		COPPER_MILK_BUCKET = registerItem("copper_milk_bucket", () -> new CopperMilkBucketItem(new Item.Properties().craftRemainder(COPPER_BUCKET.get()).stacksTo(1)));
		COPPER_POWDER_SNOW_BUCKET = registerItem((String)"copper_powder_snow_bucket", () -> new CopperSolidBucketItem(Blocks.POWDER_SNOW, SoundEvents.BUCKET_EMPTY_POWDER_SNOW, (new Item.Properties()).stacksTo(1)));
		COPPER_SHEARS = registerItem("copper_shears", () -> new ShearsItem(new Item.Properties().durability(238)));
		COPPER_GOLEM_SPAWN_EGG = registerItem("copper_golem_spawn_egg", () -> new ForgeSpawnEggItem(COPPER_GOLEM, 0x6D3421, 0xE77C56, new Item.Properties()));
		
		COPPER_HORSE_ARMOR = registerItem("copper_horse_armor", () -> new HorseArmorItem(5, new ResourceLocation(Constants.MOD_ID, "textures/entity/horse/armor/horse_armor_copper.png"), new Item.Properties().stacksTo(1)));
		COPPER_HELMET = registerItem("copper_helmet", () -> new ArmorItem(ModArmorMaterials.COPPER, ArmorItem.Type.HELMET, new Item.Properties().stacksTo(1)));
		COPPER_CHESTPLATE = registerItem("copper_chestplate", () -> new ArmorItem(ModArmorMaterials.COPPER, ArmorItem.Type.CHESTPLATE, new Item.Properties().stacksTo(1)));
		COPPER_LEGGINGS = registerItem("copper_leggings", () -> new ArmorItem(ModArmorMaterials.COPPER, ArmorItem.Type.LEGGINGS, new Item.Properties().stacksTo(1)));
		COPPER_BOOTS = registerItem("copper_boots", () -> new ArmorItem(ModArmorMaterials.COPPER, ArmorItem.Type.BOOTS, new Item.Properties().stacksTo(1)));
		
		
		
		// CreativeModeTab
		MOREUSEFULCOPPER_TAB = CREATIVE_MODE_TAB.register("moreusefulcopper_tab", () -> CreativeModeTab.builder()
			.withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
			.icon(() -> new ItemStack(COPPER_PICKAXE.get()))
			.title(Component.translatable("itemGroup.moreUsefulCopper"))
			.displayItems((itemDisplayParameters, output) -> {
				output.accept(COPPER_SWORD.get());
				output.accept(COPPER_SHOVEL.get());
				output.accept(COPPER_PICKAXE.get());
				output.accept(COPPER_AXE.get());
				output.accept(COPPER_HOE.get());
				
				output.accept(COPPER_BUCKET.get());
				output.accept(COPPER_WATER_BUCKET.get());
				output.accept(COPPER_MILK_BUCKET.get());
				output.accept(COPPER_POWDER_SNOW_BUCKET.get());
				output.accept(COPPER_SHEARS.get());
				output.accept(COPPER_GOLEM_SPAWN_EGG.get());
				
				output.accept(COPPER_HORSE_ARMOR.get());
				output.accept(COPPER_HELMET.get());
				output.accept(COPPER_CHESTPLATE.get());
				output.accept(COPPER_LEGGINGS.get());
				output.accept(COPPER_BOOTS.get());
				
				output.accept(COPPER_CHAIN.get());
				output.accept(COPPER_BUTTON.get());
				output.accept(COPPER_PRESSURE_PLATE.get());
			})
			.build());
	}
	
	// Registering DeferredRegisters to the IEventBus for our context
	public static void register(IEventBus bus) {
		BLOCK.register(bus);
		ITEM.register(bus);
		ENTITY_TYPE.register(bus);
		CREATIVE_MODE_TAB.register(bus);
	}
	
	private static ButtonBlock copperButton() {
		return new ButtonBlock(BlockBehaviour.Properties.of().noCollission().requiresCorrectToolForDrops() .strength(0.5F).pushReaction(PushReaction.DESTROY), BlockSetType.STONE, 20, false);
	}
}
