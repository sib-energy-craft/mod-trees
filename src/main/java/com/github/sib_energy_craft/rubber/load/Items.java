package com.github.sib_energy_craft.rubber.load;

import com.github.sib_energy_craft.energy_api.utils.Identifiers;
import com.github.sib_energy_craft.sec_utils.load.DefaultModInitializer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.util.Rarity;

import static com.github.sib_energy_craft.sec_utils.utils.ItemUtils.register;
import static com.github.sib_energy_craft.sec_utils.utils.ItemUtils.registerBlockItem;


/**
 * @since 0.0.1
 * @author sibmaks
 */
public final class Items implements DefaultModInitializer {
    public static final BlockItem RUBBER_LEAVES;

    public static final BlockItem RUBBER_SAPLING;

    public static final BlockItem RUBBER_LOG;
    public static final BlockItem STRIPPED_RUBBER_LOG;

    public static final BlockItem RUBBER_PLANKS;

    public static final BlockItem RUBBER_WOOD;
    public static final BlockItem STRIPPED_RUBBER_WOOD;

    public static final Item RUBBER;
    public static final Item VULCANIZED_RUBBER;

    public static final BlockItem RUBBER_BUTTON;
    public static final BlockItem RUBBER_FENCE;
    public static final BlockItem RUBBER_FENCE_GATE;
    public static final BlockItem RUBBER_PRESSURE_PLATE;
    public static final BlockItem RUBBER_SLAB;
    public static final BlockItem RUBBER_STAIRS;

    static {
        RUBBER_SAPLING = registerBlockItem(ItemGroups.NATURAL, Blocks.RUBBER_SAPLING);
        RUBBER_LEAVES = registerBlockItem(ItemGroups.NATURAL, Blocks.RUBBER_LEAVES);

        RUBBER_LOG = registerBlockItem(ItemGroups.BUILDING_BLOCKS, Blocks.RUBBER_LOG);
        STRIPPED_RUBBER_LOG = registerBlockItem(ItemGroups.BUILDING_BLOCKS, Blocks.STRIPPED_RUBBER_LOG);
        RUBBER_PLANKS = registerBlockItem(ItemGroups.BUILDING_BLOCKS, Blocks.RUBBER_PLANKS);
        RUBBER_WOOD = registerBlockItem(ItemGroups.BUILDING_BLOCKS, Blocks.RUBBER_WOOD);
        STRIPPED_RUBBER_WOOD = registerBlockItem(ItemGroups.BUILDING_BLOCKS, Blocks.STRIPPED_RUBBER_WOOD);

        RUBBER_BUTTON = registerBlockItem(ItemGroups.REDSTONE, Blocks.RUBBER_BUTTON);
        RUBBER_FENCE = registerBlockItem(ItemGroups.BUILDING_BLOCKS, Blocks.RUBBER_FENCE);
        RUBBER_FENCE_GATE = registerBlockItem(ItemGroups.REDSTONE, Blocks.RUBBER_FENCE_GATE);
        RUBBER_PRESSURE_PLATE = registerBlockItem(ItemGroups.REDSTONE, Blocks.RUBBER_PRESSURE_PLATE);
        RUBBER_STAIRS = registerBlockItem(ItemGroups.BUILDING_BLOCKS, Blocks.RUBBER_STAIRS);
        RUBBER_SLAB = registerBlockItem(ItemGroups.BUILDING_BLOCKS, Blocks.RUBBER_SLAB);

        var commonItem = new Item.Settings()
                .rarity(Rarity.COMMON);
        RUBBER = register(ItemGroups.INGREDIENTS, Identifiers.of("rubber"), commonItem);
        VULCANIZED_RUBBER = register(ItemGroups.INGREDIENTS, Identifiers.of("vulcanized_rubber"), commonItem);
    }
}
