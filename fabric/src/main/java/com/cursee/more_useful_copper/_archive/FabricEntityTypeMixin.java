package com.cursee.more_useful_copper._archive;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Deprecated
@Mixin(EntityType.class)
public class FabricEntityTypeMixin {

    @Inject(method = "register", at = @At("HEAD"), cancellable = true)
    private static void  injected(String string, EntityType.Builder<ExtendoCat> builder, CallbackInfoReturnable<EntityType<ExtendoCat>> cir) {


        builder = EntityType.Builder.of(ExtendoCat::new, MobCategory.CREATURE).sized(0.6F, 0.7F).clientTrackingRange(8);

        if (string.equalsIgnoreCase("cat")) {
            cir.setReturnValue(Registry.register(BuiltInRegistries.ENTITY_TYPE, string, builder.build(string)));
        }
    }

}
