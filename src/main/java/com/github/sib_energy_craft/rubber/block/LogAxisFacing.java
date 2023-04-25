package com.github.sib_energy_craft.rubber.block;

import lombok.AllArgsConstructor;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

/**
 * @since 0.0.1
 * @author sibmaks
 */
@AllArgsConstructor
public enum LogAxisFacing implements StringIdentifiable {
    Y_NORTH(Direction.Axis.Y, Direction.NORTH),
    Y_SOUTH(Direction.Axis.Y, Direction.SOUTH),
    Y_EAST(Direction.Axis.Y, Direction.EAST),
    Y_WEST(Direction.Axis.Y, Direction.WEST),
    Z_EAST(Direction.Axis.Z, Direction.EAST),
    Z_WEST(Direction.Axis.Z, Direction.WEST),
    Z_UP(Direction.Axis.Z, Direction.UP),
    Z_DOWN(Direction.Axis.Z, Direction.DOWN),
    X_NORTH(Direction.Axis.X, Direction.NORTH),
    X_SOUTH(Direction.Axis.X, Direction.SOUTH),
    X_UP(Direction.Axis.X, Direction.UP),
    X_DOWN(Direction.Axis.X, Direction.DOWN);

    public final Direction.Axis axis;
    public final Direction facing;

    /**
     * Find log axis facing by axis and facing
     *
     * @param axis axis direction
     * @param facing block facing
     * @return found log axis facing or null
     */
    @Nullable
    public static LogAxisFacing find(@NotNull Direction.Axis axis,
                                     @NotNull Direction facing) {
        for (var axisFacing : values()) {
            if(axisFacing.axis.equals(axis) && axisFacing.facing.equals(facing)) {
                return axisFacing;
            }
        }
        return null;
    }

    @NotNull
    @Override
    public String asString() {
        return toString().toLowerCase(Locale.ROOT);
    }
}