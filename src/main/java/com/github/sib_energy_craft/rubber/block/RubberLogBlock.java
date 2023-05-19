package com.github.sib_energy_craft.rubber.block;

import com.github.sib_energy_craft.rubber.load.Items;
import com.github.sib_energy_craft.tools.item.tree_tap.TreeTap;
import lombok.extern.slf4j.Slf4j;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @since 0.0.1
 * @author sibmaks
 */
@Slf4j
public class RubberLogBlock extends Block {
    private static final int CHANCE_TO_FILL = 66;

    public static final EnumProperty<LogAxisFacing> AXIS_FACING = EnumProperty.of("axis_facing", LogAxisFacing.class);
    public static final BooleanProperty FILLED = BooleanProperty.of("filled");

    public RubberLogBlock(@NotNull Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState()
                .with(AXIS_FACING, LogAxisFacing.Y_NORTH)
                .with(FILLED, Boolean.FALSE));
    }

    @NotNull
    @Override
    public BlockState rotate(@NotNull BlockState state, @NotNull BlockRotation rotation) {
        var axisFacing = state.get(AXIS_FACING);
        var axis = axisFacing.axis;
        var facing = axisFacing.facing;
        if(axis == Direction.Axis.Y) {
            facing = rotation.rotate(facing);
            return state.with(AXIS_FACING, LogAxisFacing.find(axis, facing));
        } else if(axis == Direction.Axis.Z) {
            int rotations = getRotations(rotation);
            while (rotations-- > 0) {
                facing = switch (facing) {
                    case DOWN -> Direction.WEST;
                    case UP -> Direction.EAST;
                    case WEST, NORTH -> Direction.UP;
                    case EAST, SOUTH -> Direction.DOWN;
                };
            }
            return state.with(AXIS_FACING, LogAxisFacing.find(axis, facing));
        } else if(axis == Direction.Axis.X) {
            int rotations = getRotations(rotation);
            while (rotations-- > 0) {
                facing = switch (facing) {
                    case DOWN, EAST -> Direction.NORTH;
                    case NORTH -> Direction.UP;
                    case UP, WEST -> Direction.SOUTH;
                    case SOUTH -> Direction.DOWN;
                };
            }
            return state.with(AXIS_FACING, LogAxisFacing.find(axis, facing));
        }
        return state;
    }

    private static int getRotations(@NotNull BlockRotation rotation) {
        return switch (rotation) {
            case NONE -> 0;
            case CLOCKWISE_90 -> 1;
            case CLOCKWISE_180 -> 2;
            case COUNTERCLOCKWISE_90 -> 3;
        };
    }

    @NotNull
    @Override
    public BlockState mirror(@NotNull BlockState state, @NotNull BlockMirror mirror) {
        var axisFacing = state.get(AXIS_FACING);
        var rotation = mirror.getRotation(axisFacing.facing);
        return state.rotate(rotation);
    }

    @Override
    protected void appendProperties(@NotNull StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(AXIS_FACING, FILLED);
    }

    @NotNull
    @Override
    public BlockState getPlacementState(@NotNull ItemPlacementContext ctx) {
        var axis = ctx.getSide().getAxis();
        var axisUp = LogAxisFacing.find(axis, Direction.UP);
        var logAxisFacing = axis != Direction.Axis.Y ? axisUp : LogAxisFacing.Y_NORTH;
        return this.getDefaultState()
                .with(AXIS_FACING, logAxisFacing);
    }

    @Override
    public boolean onSyncedBlockEvent(@NotNull BlockState state,
                                      @NotNull World world,
                                      @NotNull BlockPos pos,
                                      int type,
                                      int data) {
        var blockEntity = world.getBlockEntity(pos);
        if (blockEntity == null) {
            return false;
        }
        return blockEntity.onSyncedBlockEvent(type, data);
    }

    @NotNull
    @Override
    public ActionResult onUse(@NotNull BlockState state,
                              @NotNull World world,
                              @NotNull BlockPos pos,
                              @Nullable PlayerEntity player,
                              @Nullable Hand hand,
                              @NotNull BlockHitResult hit) {
        if (player == null || hand == null) {
            return ActionResult.PASS;
        }
        var filled = isFilled(state);
        if(!filled) {
            return ActionResult.PASS;
        }

        var stackInHand = player.getStackInHand(hand);

        if (stackInHand == null || !(stackInHand.getItem() instanceof TreeTap treeTap)) {
            return ActionResult.PASS;
        }
        if (world.isClient) {
            world.playSound(player, pos, SoundEvents.ENTITY_FISHING_BOBBER_RETRIEVE, SoundCategory.BLOCKS, 1.0f, 1.0f);
        } else if(treeTap.onUse(player, hand, stackInHand)) {
            var itemStack = new ItemStack(Items.RUBBER);
            if (!player.getInventory().insertStack(itemStack)) {
                player.dropItem(itemStack, false);
            }
            var newState = state.with(FILLED, false);
            world.setBlockState(pos, newState, Block.NOTIFY_LISTENERS);
        }
        return ActionResult.CONSUME;
    }

    @Override
    public void randomTick(@NotNull BlockState state,
                           @NotNull ServerWorld world,
                           @NotNull BlockPos pos,
                           @NotNull Random random) {
        if(isFilled(state)) {
            return;
        }
        Stream<Supplier<BlockPos>> neighbors = Stream.of(
                pos::up, pos::down,
                pos::north, pos::south,
                pos::west, pos::east
        );
        boolean isNeighborFilled = neighbors
                .map(Supplier::get)
                .map(world::getBlockState)
                .filter(Objects::nonNull)
                .filter(it -> it.isOf(this))
                .anyMatch(RubberLogBlock::isFilled);
        if(isNeighborFilled) {
            return;
        }
        var filled = random.nextInt() % 100 > CHANCE_TO_FILL;
        var newState = state.with(FILLED, filled);
        if(filled) {
            newState = newState.rotate(BlockRotation.random(random));
            world.setBlockState(pos, newState, Block.NOTIFY_LISTENERS);
        }
    }

    /**
     * Check is block filled of rubber or not
     *
     * @param blockState block state
     * @return true - block filled, false - otherwise
     */
    private static boolean isFilled(@Nullable BlockState blockState) {
        if(blockState == null) {
            return false;
        }
        var filled = blockState.get(FILLED);
        return filled != null && filled;
    }
}
