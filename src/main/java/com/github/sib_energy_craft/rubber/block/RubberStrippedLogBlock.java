package com.github.sib_energy_craft.rubber.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import org.jetbrains.annotations.NotNull;

/**
 * @author sibmaks
 * @since 0.0.7
 */
public class RubberStrippedLogBlock extends PillarBlock implements StrippedBlock {
    public RubberStrippedLogBlock(@NotNull Settings settings) {
        super(settings);
    }

    @Override
    public @NotNull BlockState getStrippedState(@NotNull BlockState state) {
        var logAxisFacing = state.get(RubberLogBlock.AXIS_FACING);
        return getDefaultState().with(PillarBlock.AXIS, logAxisFacing.axis);
    }
}
