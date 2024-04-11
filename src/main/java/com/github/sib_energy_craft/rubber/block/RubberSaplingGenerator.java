package com.github.sib_energy_craft.rubber.block;

import com.github.sib_energy_craft.energy_api.utils.Identifiers;
import com.github.sib_energy_craft.rubber.world.gen.feature.TreeConfiguredFeatures;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.block.SaplingGenerator;

import java.util.Optional;

/**
 * @author sibmaks
 * @since 0.0.1
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RubberSaplingGenerator {
    public static final SaplingGenerator RUBBER;

    static {
        RUBBER = new SaplingGenerator(
                Identifiers.asString("rubber_tree"),
                Optional.empty(),
                Optional.of(TreeConfiguredFeatures.RUBBER),
                Optional.empty()
        );
    }
}
