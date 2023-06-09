package com.github.sib_energy_craft.rubber.block;

import lombok.AllArgsConstructor;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

/**
 * @author sibmaks
 * @since 0.0.9
 */
@AllArgsConstructor
public enum FilledSide implements StringIdentifiable {
    NONE(null, null, null),
    A(Direction.NORTH, Direction.NORTH, Direction.UP),
    B(Direction.SOUTH, Direction.SOUTH, Direction.DOWN),
    C(Direction.DOWN, Direction.EAST, Direction.EAST),
    D(Direction.UP, Direction.WEST, Direction.WEST);

    public final Direction xSide;
    public final Direction ySide;
    public final Direction zSide;

    @NotNull
    @Override
    public String asString() {
        return toString().toLowerCase(Locale.ROOT);
    }
}