package com.github.sib_energy_craft.rubber.load;

import com.github.sib_energy_craft.energy_api.utils.Identifiers;
import com.github.sib_energy_craft.rubber.block.RubberLogBlock;
import com.github.sib_energy_craft.rubber.block.RubberSaplingGenerator;
import com.github.sib_energy_craft.rubber.block.RubberStrippedLogBlock;
import com.github.sib_energy_craft.sec_utils.Hooks;
import com.github.sib_energy_craft.sec_utils.common.Identified;
import com.github.sib_energy_craft.sec_utils.load.DefaultModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

import static com.github.sib_energy_craft.sec_utils.utils.BlockUtils.register;


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

    public static final Identified<DoorBlock> RUBBER_DOOR;
    public static final Identified<ButtonBlock> RUBBER_BUTTON;
    public static final Identified<FenceBlock> RUBBER_FENCE;
    public static final Identified<FenceGateBlock> RUBBER_FENCE_GATE;
    public static final Identified<PressurePlateBlock> RUBBER_PRESSURE_PLATE;
    public static final Identified<SlabBlock> RUBBER_SLAB;
    public static final Identified<StairsBlock> RUBBER_STAIRS;
    public static final Identified<TrapdoorBlock> RUBBER_TRAPDOOR;

    static {
        var leavesSettings = FabricBlockSettings.of(Material.LEAVES)
                .sounds(BlockSoundGroup.GRASS)
                .strength(0.2f)
                .ticksRandomly()
                .nonOpaque()
                .allowsSpawning((state, world, pos, type) -> type == EntityType.OCELOT || type == EntityType.PARROT)
                .suffocates((state, world, pos) -> false)
                .blockVision((state, world, pos) -> false);

        RUBBER_LEAVES = register(Identifiers.of("rubber_leaves"), new LeavesBlock(leavesSettings));

       var saplingSettings = FabricBlockSettings.of(Material.PLANT)
               .noCollision()
               .nonOpaque()
               .ticksRandomly()
               .breakInstantly()
               .sounds(BlockSoundGroup.GRASS);
        var saplingBlock = new SaplingBlock(new RubberSaplingGenerator(), saplingSettings);
        RUBBER_SAPLING = register(Identifiers.of("rubber_sapling"), saplingBlock);

        var pottedSaplingSettings = FabricBlockSettings.of(Material.DECORATION)
                .breakInstantly()
                .nonOpaque();
        var flowerPotBlock = new FlowerPotBlock(RUBBER_SAPLING.entity(), pottedSaplingSettings);
        POTTED_RUBBER_SAPLING = register(Identifiers.of("potted_rubber_sapling"), flowerPotBlock);

        var rubberLogSettings = getLogSettings(MapColor.WHITE_GRAY, MapColor.SPRUCE_BROWN);
        var rubberLogBlock = new RubberLogBlock(rubberLogSettings);
        RUBBER_LOG = register(Identifiers.of("rubber_log"), rubberLogBlock);

        var strippedRubberLogSettings = getWoodSettings(MapColor.OAK_TAN, MapColor.OAK_TAN);
        var strippedLogBlock = new RubberStrippedLogBlock(strippedRubberLogSettings);
        STRIPPED_RUBBER_LOG = register(Identifiers.of("stripped_rubber_log"), strippedLogBlock);

        var planksSettings = AbstractBlock.Settings.of(Material.WOOD, MapColor.BROWN)
                .strength(2.0f, 3.0f)
                .sounds(BlockSoundGroup.WOOD);
        var rubberPlank = new Block(planksSettings);
        RUBBER_PLANKS = register(Identifiers.of("rubber_planks"), rubberPlank);

        var rubberWoodSettings = getWoodSettings(MapColor.WHITE_GRAY, MapColor.WHITE_GRAY);
        var rubberWood = new PillarBlock(rubberWoodSettings);
        RUBBER_WOOD = register(Identifiers.of("rubber_wood"), rubberWood);

        var strippedRubberWood = new PillarBlock(strippedRubberLogSettings);
        STRIPPED_RUBBER_WOOD = register(Identifiers.of("stripped_rubber_wood"), strippedRubberWood);

        Hooks.AxeItemClassInit.accept(Map.of(
                Blocks.RUBBER_LOG.entity(), Blocks.STRIPPED_RUBBER_LOG.entity(),
                Blocks.RUBBER_WOOD.entity(), Blocks.STRIPPED_RUBBER_WOOD.entity()
        ));

        var rubberItemsSettings = AbstractBlock.Settings.of(Material.WOOD, MapColor.WHITE_GRAY)
                .strength(2.0F, 3.0F)
                .sounds(BlockSoundGroup.WOOD);

        var rubberBlockSetType = BlockSetType.BIRCH;

        var rubberDoorSettings = AbstractBlock.Settings
                .of(Material.WOOD, RUBBER_PLANKS.entity().getDefaultMapColor())
                .strength(3.0F)
                .nonOpaque();
        var rubberDoor = new DoorBlock(rubberDoorSettings, rubberBlockSetType);
        RUBBER_DOOR = register(Identifiers.of("rubber_door"), rubberDoor);

        var rubberButton = createWoodenButtonBlock(rubberBlockSetType);
        RUBBER_BUTTON = register(Identifiers.of("rubber_button"), rubberButton);

        var rubberFence = new FenceBlock(rubberItemsSettings);
        RUBBER_FENCE = register(Identifiers.of("rubber_fence"), rubberFence);

        var rubberFenceGate = new FenceGateBlock(rubberItemsSettings, WoodType.BIRCH);
        RUBBER_FENCE_GATE = register(Identifiers.of("rubber_fence_gate"), rubberFenceGate);

        var rubberPressurePlateSettings = AbstractBlock.Settings
                .of(Material.WOOD, RUBBER_PLANKS.entity().getDefaultMapColor())
                .noCollision()
                .strength(0.5F);
        var rubberPressurePlate = new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING,
                rubberPressurePlateSettings,
                rubberBlockSetType);
        RUBBER_PRESSURE_PLATE = register(Identifiers.of("rubber_pressure_plate"), rubberPressurePlate);

        var rubberSlabBlock = new SlabBlock(rubberItemsSettings);
        RUBBER_SLAB = register(Identifiers.of("rubber_slab"), rubberSlabBlock);

        var rubberStairsBlock = new StairsBlock(RUBBER_PLANKS.entity().getDefaultState(),
                AbstractBlock.Settings.copy(RUBBER_PLANKS.entity()));
        RUBBER_STAIRS = register(Identifiers.of("rubber_stairs"), rubberStairsBlock);

        var rubberTrapdoorSettings = AbstractBlock.Settings
                .of(Material.WOOD, MapColor.PALE_YELLOW)
                .strength(3.0F)
                .nonOpaque()
                .allowsSpawning((state, world, pos, type) -> false);
        var rubberTrapdoor = new TrapdoorBlock(rubberTrapdoorSettings, rubberBlockSetType);
        RUBBER_TRAPDOOR = register(Identifiers.of("rubber_trapdoor"), rubberTrapdoor);
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

    private static boolean isUpDownFacing(@NotNull BlockState state) {
        var axisFacing = state.get(RubberLogBlock.AXIS_FACING);
        return axisFacing.axis == Direction.Axis.Y;
    }

    private static ButtonBlock createWoodenButtonBlock(@NotNull BlockSetType blockSetType) {
        return new ButtonBlock(AbstractBlock.Settings.of(Material.DECORATION)
                .noCollision()
                .strength(0.5F), blockSetType, 30, true
        );
    }
}
