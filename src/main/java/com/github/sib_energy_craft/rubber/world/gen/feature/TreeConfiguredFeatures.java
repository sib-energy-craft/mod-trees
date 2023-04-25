package com.github.sib_energy_craft.rubber.world.gen.feature;

import com.github.sib_energy_craft.energy_api.utils.Identifiers;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;

/**
 * @since 0.0.1
 * @author sibmaks
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TreeConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> RUBBER;

    static {
        RUBBER = ConfiguredFeatures.of(Identifiers.asString("rubber_tree"));
    }
}
