package io.github.tbh_mod.entities.ai;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

import java.util.EnumSet;

public class JumpAroundGoal extends Goal {

	private PathAwareEntity entity;
	private float height;

	private int counter;

	private int groundTicks;

	private Direction direction;

	public JumpAroundGoal(PathAwareEntity entity, float height) {
		this.entity = entity;
		this.height = height;
		this.setControls(EnumSet.of(Goal.Control.MOVE, Goal.Control.LOOK, Goal.Control.JUMP));
	}

	@Override
	public boolean canStart() {
		if(entity.isOnGround()) {
			return true;
		}
		return false;
	}

	@Override
	public void start() {
		super.start();
	}

	@Override
	public void tick() {
		if(entity.isOnGround()) {
			groundTicks++;
		}
		if(groundTicks > this.getTickCount(40)) {
			this.entity.setYaw(360 * this.entity.getRandom().nextFloat());
			direction = this.entity.getMovementDirection();
			Vec3d vec3d = new Vec3d(entity.getX(), entity.getY(), entity.getZ()).normalize();
			this.entity.setVelocity(direction.getOffsetX() * vec3d.x, height, direction.getOffsetZ() * vec3d.z);
			groundTicks = 0;
		}
		super.tick();
	}
}
