package com.cursee.more_useful_copper._archive;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.level.Level;

@Deprecated
public class ExtendoCat extends Cat {

    public ExtendoCat(EntityType<? extends Cat> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void tick() {
        super.tick();

        System.out.println("Extendo Cat Ticking!");
    }
}
