package com.github.sib_energy_craft.rubber.block;

import net.minecraft.block.BlockState;
import org.jetbrains.annotations.NotNull;

/**
 * @author sibmaks
 * @since 0.0.7
 */
public interface StrippedBlock {

    /**
     * Get stripped block state
     *
     * @param state base block state
     * @return stripped block state
     */
    @NotNull BlockState getStrippedState(@NotNull BlockState state);

}
