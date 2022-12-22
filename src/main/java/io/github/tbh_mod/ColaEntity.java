package io.github.tbh_mod;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.ai.control.JumpControl;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.quiltmc.qsl.entity.api.QuiltEntityTypeBuilder;

import java.util.EnumSet;

public class ColaEntity extends PathAwareEntity {

	public float targetStretch;
	public float stretch;
	public float lastStretch;
	private boolean onGroundLastTick;
	public static final EntityType<ColaEntity> COLA_ENTITY_TYPE = Registry.register(Registry.ENTITY_TYPE, new Identifier(Main.MOD_ID, "cola_entity"), QuiltEntityTypeBuilder.create(SpawnGroup.CREATURE, ColaEntity::new).setDimensions(EntityDimensions.changing(0.5F, 1.0F)).build());

	protected ColaEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
		super(entityType, world);
		//this.moveControl = new ColaEntity.ColaMoveControl(this);
		//this.jumpControl = new ColaEntity.ColaJumpControl(this);
	}

	/*
	@Override
	protected void mobTick() {
		if (this.onGround) {
			this.setJumping(false);
			ColaEntity.ColaJumpControl colaJumpControl = (ColaEntity.ColaJumpControl) this.jumpControl;
			if (!colaJumpControl.isActive()) {
				if (this.moveControl.isMoving()) {
					Path path = this.navigation.getCurrentPath();
					Vec3d vec3d = new Vec3d(this.moveControl.getTargetX(), this.moveControl.getTargetY(), this.moveControl.getTargetZ());
					if (path != null && !path.isFinished()) {
						vec3d = path.getNodePosition(this);
					}

					this.lookTowards(vec3d.x, vec3d.z);
					this.setJumping(true);
				}
			}
			super.mobTick();
		}
	}

	 */

	@Override
	public void tick() {
		//System.out.println(this.getYaw());
		this.stretch += (this.targetStretch - this.stretch) * 0.5F;
		this.lastStretch = this.stretch;
		super.tick();
		if (this.onGround && !this.onGroundLastTick) {

			for(int j = 0; j < 8; ++j) {
				float f = this.random.nextFloat() * (float) (Math.PI * 2);
				float g = this.random.nextFloat() * 0.5F + 0.5F;
				float h = MathHelper.sin(f) * 0.5F * g;
				float k = MathHelper.cos(f) * 0.5F * g;
				this.world.addParticle(this.getParticles(), this.getX() + (double)h, this.getY(), this.getZ() + (double)k, 0.0, 0.0, 0.0);
			}

			this.playSound(SoundEvents.ENTITY_SLIME_SQUISH, this.getSoundVolume(), ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) / 0.8F);
			this.targetStretch = -0.5F;
		} else if (!this.onGround && this.onGroundLastTick) {
			this.targetStretch = 1.0F;
		}

		this.onGroundLastTick = this.onGround;
		this.updateStretch();

		//this.setYaw(this.random.nextFloat());
	}

	protected ParticleEffect getParticles() {
		return ParticleTypes.BUBBLE;
	}

	protected void updateStretch() {
		this.targetStretch *= 0.6F;
	}

	@Override
	protected void initGoals() {
		this.goalSelector.add(1, new WanderAroundFarGoal(this, 0.4f));
		this.goalSelector.add(1, new LookAroundGoal(this));
		this.goalSelector.add(1, new JumpAroundGoal(this, 0.4f));
		super.initGoals();
	}

	/*
	@Override
	protected void jump() {
		super.jump();
		double d = this.moveControl.getSpeed();
		if (d > 0.0) {
			double e = this.getVelocity().horizontalLengthSquared();
			if (e < 0.01) {
				this.updateVelocity(0.1F, new Vec3d(0.0, 0.0, 1.0));
			}
		}
	}

	 */
	/*
	static class ColaMoveControl extends MoveControl {
		private final ColaEntity cola;
		private double colaSpeed;

		public ColaMoveControl(ColaEntity owner) {
			super(owner);
			this.cola = owner;
		}

		/*
		@Override
		public void tick() {
			if (this.cola.onGround && !this.cola.jumping && !((ColaEntity.ColaJumpControl)this.cola.jumpControl).isActive()) {
				this.cola.setSpeed(0.0);
			} else if (this.isMoving()) {
				this.cola.setSpeed(this.colaSpeed);
			}

			super.tick();
		}

		 */

		/*
		@Override
		public void moveTo(double x, double y, double z, double speed) {
			if (this.cola.isTouchingWater()) {
				speed = 1.5;
			}

			super.moveTo(x, y, z, speed);
			if (speed > 0.0) {
				this.colaSpeed = speed;
			}
		}
	}

		 */

	/*
	public static class ColaJumpControl extends JumpControl {
			private final ColaEntity cola;
			private boolean enabled;

			public ColaJumpControl(ColaEntity cola) {
				super(cola);
				this.cola = cola;
			}

			public boolean isActive() {
				return this.active;
			}

			public boolean isEnabled() {
				return this.enabled;
			}

			public void setEnabled(boolean enabled) {
				this.enabled = enabled;
			}

			@Override
			public void tick() {
				if (this.active) {
					this.cola.setJumping(true);
					this.active = false;
				}
			}
		}

	private void lookTowards(double x, double z) {
		this.setYaw((float)(MathHelper.atan2(z - this.getZ(), x - this.getX()) * 180.0F / (float)Math.PI) - 90.0F);
	}
	public void setSpeed(double speed) {
		this.getNavigation().setSpeed(speed);
		this.moveControl.moveTo(this.moveControl.getTargetX(), this.moveControl.getTargetY(), this.moveControl.getTargetZ(), speed);
	}

	 */


	public static void registerColaEntityAttributes() {
		FabricDefaultAttributeRegistry.register(COLA_ENTITY_TYPE, createMobAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3f).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 5.0f).add(EntityAttributes.GENERIC_MAX_HEALTH, 10f));
	}

}
