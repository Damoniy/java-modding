package com.damoniy.common.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import com.damoniy.api.events.TeleporterManager;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BlockPortal extends BlockBase {
	public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;
	protected static final VoxelShape X_AABB = Block.makeCuboidShape(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D);
	protected static final VoxelShape Z_AABB = Block.makeCuboidShape(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D);

	public BlockPortal() {
		super(Properties.create(Material.PORTAL).doesNotBlockMovement().tickRandomly().hardnessAndResistance(-1.0F)
				.sound(SoundType.GLASS).lightValue(11).noDrops(), "block_portal", ItemGroup.BREWING);
		this.setDefaultState(this.stateContainer.getBaseState().with(AXIS, Direction.Axis.X));
	}

	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		switch ((Direction.Axis) state.get(AXIS)) {
		case Z:
			return Z_AABB;
		case X:
		default:
			return X_AABB;
		}
	}

	public boolean isFullCube(BlockState state) {
		return false;
	}

	public boolean trySpawnPortal(IWorld worldIn, BlockPos pos) {
		BlockPortal.Size blockportal$size = this.isPortal(worldIn, pos);
		if (blockportal$size != null) {
			blockportal$size.placePortalBlocks();
			return true;
		} else {
			return false;
		}
	}

	@Nullable
	public BlockPortal.Size isPortal(IWorld p_201816_1_, BlockPos p_201816_2_) {
		BlockPortal.Size blockportal$size = new BlockPortal.Size(p_201816_1_, p_201816_2_, Direction.Axis.X);
		if (blockportal$size.isValid() && blockportal$size.portalBlockCount == 0) {
			return blockportal$size;
		} else {
			BlockPortal.Size blockportal$size1 = new BlockPortal.Size(p_201816_1_, p_201816_2_, Direction.Axis.Z);
			return blockportal$size1.isValid() && blockportal$size1.portalBlockCount == 0 ? blockportal$size1 : null;
		}
	}

	@SuppressWarnings("deprecation")
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn,
			BlockPos currentPos, BlockPos facingPos) {
		Direction.Axis enumfacing$axis = facing.getAxis();
		Direction.Axis enumfacing$axis1 = stateIn.get(AXIS);
		boolean flag = enumfacing$axis1 != enumfacing$axis && enumfacing$axis.isHorizontal();
		return flag || facingState.getBlock() == this
				|| (new BlockPortal.Size(worldIn, currentPos, enumfacing$axis1)).func_208508_f()
						? super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos)
						: Blocks.AIR.getDefaultState();
	}

	public int quantityDropped(BlockState state, Random random) {
		return 0;
	}

	public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
		if (!entity.isPassenger() && !entity.isBeingRidden() && entity.isNonBoss()) {
			TeleporterManager.teleport(entity);
		}
	}

	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		if (rand.nextInt(100) == 0) {
			worldIn.playSound((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D,
					SoundEvents.BLOCK_PORTAL_AMBIENT, SoundCategory.BLOCKS, 0.5F, rand.nextFloat() * 0.4F + 0.8F,
					false);
		}

		for (int i = 0; i < 4; ++i) {
			double d0 = (double) ((float) pos.getX() + rand.nextFloat());
			double d1 = (double) ((float) pos.getY() + rand.nextFloat());
			double d2 = (double) ((float) pos.getZ() + rand.nextFloat());
			double d3 = ((double) rand.nextFloat() - 0.5D) * 0.5D;
			double d4 = ((double) rand.nextFloat() - 0.5D) * 0.5D;
			double d5 = ((double) rand.nextFloat() - 0.5D) * 0.5D;
			int j = rand.nextInt(2) * 2 - 1;
			if (worldIn.getBlockState(pos.west()).getBlock() != this
					&& worldIn.getBlockState(pos.east()).getBlock() != this) {
				d0 = (double) pos.getX() + 0.5D + 0.25D * (double) j;
				d3 = (double) (rand.nextFloat() * 2.0F * (float) j);
			} else {
				d2 = (double) pos.getZ() + 0.5D + 0.25D * (double) j;
				d5 = (double) (rand.nextFloat() * 2.0F * (float) j);
			}

			worldIn.addParticle(ParticleTypes.TOTEM_OF_UNDYING, d0, d1, d2, d3, d4, d5);
		}
	}

	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return ItemStack.EMPTY;
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		switch (rot) {
		case COUNTERCLOCKWISE_90:
		case CLOCKWISE_90:
			switch ((Direction.Axis) state.get(AXIS)) {
			case Z:
				return state.with(AXIS, Direction.Axis.X);
			case X:
				return state.with(AXIS, Direction.Axis.Z);
			default:
				return state;
			}
		default:
			return state;
		}
	}

	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(AXIS);
	}

	public static class Size {
		private final IWorld world;
		private final Direction.Axis axis;
		private final Direction rightDir;
		private final Direction leftDir;
		private int portalBlockCount;
		@Nullable
		private BlockPos bottomLeft;
		private int height;
		private int width;

		public Size(IWorld worldIn, BlockPos pos, Direction.Axis axisIn) {
			this.world = worldIn;
			this.axis = axisIn;
			if (axisIn == Direction.Axis.X) {
				this.leftDir = Direction.EAST;
				this.rightDir = Direction.WEST;
			} else {
				this.leftDir = Direction.NORTH;
				this.rightDir = Direction.SOUTH;
			}

			for (BlockPos blockpos = pos; pos.getY() > blockpos.getY() - 21 && pos.getY() > 0
					&& this.func_196900_a(worldIn.getBlockState(pos.down())); pos = pos.down()) {
				;
			}

			int i = this.getDistanceUntilEdge(pos, this.leftDir) - 1;
			if (i >= 0) {
				this.bottomLeft = pos.offset(this.leftDir, i);
				this.width = this.getDistanceUntilEdge(this.bottomLeft, this.rightDir);
				if (this.width < 2 || this.width > 21) {
					this.bottomLeft = null;
					this.width = 0;
				}
			}

			if (this.bottomLeft != null) {
				this.height = this.calculatePortalHeight();
			}

		}

		protected int getDistanceUntilEdge(BlockPos pos, Direction directionIn) {
			int i;
			for (i = 0; i < 22; ++i) {
				BlockPos blockpos = pos.offset(directionIn, i);
				if (!this.func_196900_a(this.world.getBlockState(blockpos))
						|| !this.world.getBlockState(blockpos.down()).isPortalFrame(this.world, blockpos.down())) {
					break;
				}
			}

			BlockPos framePos = pos.offset(directionIn, i);
			return this.world.getBlockState(framePos).isPortalFrame(this.world, framePos) ? i : 0;
		}

		public int getHeight() {
			return this.height;
		}

		public int getWidth() {
			return this.width;
		}

		protected int calculatePortalHeight() {
			label56: for (this.height = 0; this.height < 21; ++this.height) {
				for (int i = 0; i < this.width; ++i) {
					BlockPos blockpos = this.bottomLeft.offset(this.rightDir, i).up(this.height);
					BlockState blockstate = this.world.getBlockState(blockpos);
					if (!this.func_196900_a(blockstate)) {
						break label56;
					}

					Block block = blockstate.getBlock();
					if (block == Blocks.NETHER_PORTAL) {
						++this.portalBlockCount;
					}

					if (i == 0) {
						BlockPos framePos = blockpos.offset(this.leftDir);
						if (!this.world.getBlockState(framePos).isPortalFrame(this.world, framePos)) {
							break label56;
						}
					} else if (i == this.width - 1) {
						BlockPos framePos = blockpos.offset(this.rightDir);
						if (!this.world.getBlockState(framePos).isPortalFrame(this.world, framePos)) {
							break label56;
						}
					}
				}
			}

			for (int j = 0; j < this.width; ++j) {
				BlockPos framePos = this.bottomLeft.offset(this.rightDir, j).up(this.height);
				if (!this.world.getBlockState(framePos).isPortalFrame(this.world, framePos)) {
					this.height = 0;
					break;
				}
			}

			if (this.height <= 21 && this.height >= 3) {
				return this.height;
			} else {
				this.bottomLeft = null;
				this.width = 0;
				this.height = 0;
				return 0;
			}
		}

		@SuppressWarnings("deprecation")
		protected boolean func_196900_a(BlockState pos) {
			Block block = pos.getBlock();
			return pos.isAir() || block == Blocks.FIRE || block == Blocks.NETHER_PORTAL;
		}

		public boolean isValid() {
			return this.bottomLeft != null && this.width >= 2 && this.width <= 21 && this.height >= 3
					&& this.height <= 21;
		}

		public void placePortalBlocks() {
			for (int i = 0; i < this.width; ++i) {
				BlockPos blockpos = this.bottomLeft.offset(this.rightDir, i);

				for (int j = 0; j < this.height; ++j) {
					this.world.setBlockState(blockpos.up(j),
							Blocks.NETHER_PORTAL.getDefaultState().with(BlockPortal.AXIS, this.axis), 18);
				}
			}

		}

		private boolean func_196899_f() {
			return this.portalBlockCount >= this.width * this.height;
		}

		public boolean func_208508_f() {
			return this.isValid() && this.func_196899_f();
		}
	}
}