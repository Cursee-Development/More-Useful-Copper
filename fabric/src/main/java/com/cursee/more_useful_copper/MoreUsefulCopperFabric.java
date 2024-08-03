package com.cursee.more_useful_copper;

import com.cursee.monolib.core.sailing.Sailing;
import com.cursee.more_useful_copper.core.entity.custom.CopperGolemEntity;
import com.cursee.more_useful_copper.core.loot.ModLootTableModifiers;
import com.cursee.more_useful_copper.core.registry.FabricRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;

public class MoreUsefulCopperFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {

        MoreUsefulCopper.init();
        Sailing.register(Constants.MOD_NAME, Constants.MOD_ID, Constants.MOD_VERSION, Constants.MC_VERSION_RAW, Constants.PUBLISHER_AUTHOR, Constants.PRIMARY_CURSEFORGE_MODRINTH);

        FabricRegistry.register();

        registerModEntityAttributes();

        ModLootTableModifiers.modifyLootTables();
    }

    private static void registerModEntityAttributes() {
        FabricDefaultAttributeRegistry.register(FabricRegistry.COPPER_GOLEM, CopperGolemEntity.createAttributes());
    }
}
