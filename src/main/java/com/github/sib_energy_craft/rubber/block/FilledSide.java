package com.github.sib_energy_craft.rubber.block;

import net.minecraft.util.StringIdentifiable;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

/**
 * @author sibmaks
 * @since 0.0.9
 */
public enum FilledSide implements StringIdentifiable {
    NONE,
    A,
    B,
    C,
    D;

    @NotNull
    @Override
    public String asString() {
        return toString().toLowerCase(Locale.ROOT);
    }
}