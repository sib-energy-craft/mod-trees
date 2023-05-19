package com.github.sib_energy_craft.rubber;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.block.Block;

import java.util.Map;
import java.util.function.Consumer;

/**
 * @author sibmaks
 * @since 0.0.7
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Hooks {
    public static Consumer<Map<Block, Block>> AxeItemClassInit;
}
