package com.github.sib_energy_craft.rubber.load;

import com.github.sib_energy_craft.energy_api.utils.Identifiers;
import com.github.sib_energy_craft.rubber.block.RubberLogBlock;
import com.github.sib_energy_craft.rubber.block.RubberSaplingGenerator;
import com.github.sib_energy_craft.sec_utils.common.Identified;
import com.github.sib_energy_craft.sec_utils.load.DefaultModInitializer;
import com.github.sib_energy_craft.sec_utils.utils.BlockUtils;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.Direction;


/**
 * @since 0.0.1
 * @author sibmaks
 */
public final class Blocks implements DefaultModInitializer {
    public static final Identified<LeavesBlock> RUBBER_LEAVES;

    public static final Identified<SaplingBlock> RUBBER_SAPLING;
    public static final Identified<FlowerPotBlock> POTTED_RUBBER_SAPLING;

    public static final Identified<RubberLogBlock> RUBBER_LOG;
    public static final Identified<PillarBlock> STRIPPED_RUBBER_LOG;

    public static final Identified<Block> RUBBER_PLANKS;

    public static final Identified<PillarBlock> RUBBER_WOOD;
    public static final Identified<PillarBlock> STRIPPED_RUBBER_WOOD;

    static {
        var leavesSettings = FabricBlockSettings.of(Material.LEAVES)
                .sounds(BlockSoundGroup.GRASS)
                .strength(0.2f)
                .ticksRandomly()
                .nonOpaque()
                .allowsSpawning((state, world, pos, type) -> type == EntityType.OCELOT || type == EntityType.PARROT)
                .suffocates((state, world, pos) -> false)
                .blockVision((state, world, pos) -> false);

        RUBBER_LEAVES = BlockUtils.register(Identifiers.of("rubber_leaves"), new LeavesBlock(leavesSettings));

       var saplingSettings = FabricBlockSettings.of(Material.PLANT)
               .noCollision()
               .nonOpaque()
               .ticksRandomly()
               .breakInstantly()
               .sounds(BlockSoundGroup.GRASS);
        var saplingBlock = new SaplingBlock(new RubberSaplingGenerator(), saplingSettings);
        RUBBER_SAPLING = BlockUtils.register(Identifiers.of("rubber_sapling"), saplingBlock);

        var pottedSaplingSettings = FabricBlockSettings.of(Material.DECORATION)
                .breakInstantly()
                .nonOpaque();
        var flowerPotBlock = new FlowerPotBlock(RUBBER_SAPLING.entity(), pottedSaplingSettings);
        POTTED_RUBBER_SAPLING = BlockUtils.register(Identifiers.of("potted_rubber_sapling"), flowerPotBlock);

        var rubberLogSettings = getLogSettings(MapColor.DIRT_BROWN, MapColor.SPRUCE_BROWN);
        var rubberLogBlock = new RubberLogBlock(rubberLogSettings);
        RUBBER_LOG = BlockUtils.register(Identifiers.of("rubber_log"), rubberLogBlock);

        var strippedRubberLogSettings = getWoodSettings(MapColor.OAK_TAN, MapColor.OAK_TAN);
        var pillarBlock = new PillarBlock(strippedRubberLogSettings);
        STRIPPED_RUBBER_LOG = BlockUtils.register(Identifiers.of("stripped_rubber_log"), pillarBlock);

        var planksSettings = AbstractBlock.Settings.of(Material.WOOD, MapColor.BROWN)
                .strength(2.0f, 3.0f)
                .sounds(BlockSoundGroup.WOOD);
        var rubberPlank = new Block(planksSettings);
        RUBBER_PLANKS = BlockUtils.register(Identifiers.of("rubber_planks"), rubberPlank);

        var rubberWoodSettings = getWoodSettings(MapColor.DIRT_BROWN, MapColor.DIRT_BROWN);
        var rubberWood = new PillarBlock(rubberWoodSettings);
        RUBBER_WOOD = BlockUtils.register(Identifiers.of("rubber_wood"), rubberWood);

        var strippedRubberWood = new PillarBlock(strippedRubberLogSettings);
        STRIPPED_RUBBER_WOOD = BlockUtils.register(Identifiers.of("stripped_rubber_wood"), strippedRubberWood);
    }

    private static AbstractBlock.Settings getWoodSettings(MapColor topMapColor, MapColor sideMapColor) {
        return FabricBlockSettings.of(Material.WOOD, state -> state.get(PillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor)
                .strength(2.f)
                .sounds(BlockSoundGroup.WOOD);
    }

    private static AbstractBlock.Settings getLogSettings(MapColor topMapColor, MapColor sideMapColor) {
        return FabricBlockSettings.of(Material.WOOD, state -> isUpDownFacing(state) ? topMapColor : sideMapColor)
                .strength(2.f)
                .sounds(BlockSoundGroup.WOOD)
                .ticksRandomly();
    }

    private static boolean isUpDownFacing(BlockState state) {
        var axisFacing = state.get(RubberLogBlock.AXIS_FACING);
        return axisFacing.axis == Direction.Axis.Y;
    }
}
