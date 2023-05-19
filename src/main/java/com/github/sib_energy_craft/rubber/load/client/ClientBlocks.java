package com.github.sib_energy_craft.rubber.load.client;

import com.github.sib_energy_craft.rubber.load.Blocks;
import com.github.sib_energy_craft.sec_utils.load.DefaultClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;


/**
 * @since 0.0.6
 * @author sibmaks
 */
public final class ClientBlocks implements DefaultClientModInitializer {

    static {
        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.RUBBER_SAPLING.entity(), RenderLayer.getTranslucent());

        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.POTTED_RUBBER_SAPLING.entity(), RenderLayer.getTranslucent());

        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.RUBBER_DOOR.entity(), RenderLayer.getTranslucent());

        BlockRenderLayerMap.INSTANCE.putBlock(Blocks.RUBBER_TRAPDOOR.entity(), RenderLayer.getTranslucent());
    }

}
