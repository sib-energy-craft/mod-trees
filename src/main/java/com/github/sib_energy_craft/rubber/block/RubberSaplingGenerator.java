package com.github.sib_energy_craft.rubber.block;

import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import com.github.sib_energy_craft.rubber.world.gen.feature.TreeConfiguredFeatures;
import org.jetbrains.annotations.NotNull;

/**
 * @since 0.0.1
 * @author sibmaks
 */
public class RubberSaplingGenerator extends SaplingGenerator {
    @NotNull
    @Override
    protected RegistryKey<ConfiguredFeature<?, ?>> getTreeFeature(@NotNull Random random, boolean bees) {
        return TreeConfiguredFeatures.RUBBER;
    }
}
