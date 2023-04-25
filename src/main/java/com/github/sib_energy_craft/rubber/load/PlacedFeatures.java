package com.github.sib_energy_craft.rubber.load;

import com.github.sib_energy_craft.energy_api.utils.Identifiers;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

/**
 * @since 0.0.1
 * @author sibmaks
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PlacedFeatures {
    public static final RegistryKey<PlacedFeature> RUBBER_TREE;

    static {
        var foundInOverworld = BiomeSelectors.foundInOverworld();
        RUBBER_TREE = RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifiers.of("rubber_tree"));
        BiomeModifications.addFeature(foundInOverworld, GenerationStep.Feature.VEGETAL_DECORATION, RUBBER_TREE);
    }

}
