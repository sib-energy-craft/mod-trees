package com.github.sib_energy_craft.rubber.mixin;

import com.github.sib_energy_craft.rubber.Hooks;
import com.github.sib_energy_craft.rubber.block.StrippedBlock;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.AxeItem;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;
import java.util.Optional;

/**
 * @author drobyshev-ma
 * @since 0.0.7
 */
@Slf4j
@Mixin(AxeItem.class)
public class AxeItemMixin {
    @Mutable
    @Shadow @Final protected static Map<Block, Block> STRIPPED_BLOCKS;

    static {
        Hooks.AxeItemClassInit = (value) -> AxeItemMixin.STRIPPED_BLOCKS = new ImmutableMap.Builder<Block, Block>()
                        .putAll(STRIPPED_BLOCKS)
                        .putAll(value)
                        .build();
    }

    @Inject(method = "getStrippedState", at = @At("HEAD"), cancellable = true)
    private void getStrippedState(BlockState state,
                                  CallbackInfoReturnable<Optional<BlockState>> callbackInfoReturnable) {
        var strippedBlock = STRIPPED_BLOCKS.get(state.getBlock());
        if(strippedBlock instanceof StrippedBlock block) {
            callbackInfoReturnable.setReturnValue(Optional.of(block.getStrippedState(state)));
        }
    }
}
