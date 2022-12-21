package io.github.tbh_mod;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.quiltmc.qsl.entity.api.QuiltEntityTypeBuilder;

public class BtwEntity extends TameableEntity {

	public static final EntityType<BtwEntity> BTW_ENTITY_TYPE = Registry.register(Registry.ENTITY_TYPE, new Identifier(Main.MOD_ID, "btw_entity"), QuiltEntityTypeBuilder.create(SpawnGroup.CREATURE, BtwEntity::new).setDimensions(EntityDimensions.changing(0.5F, 1.0F)).build());

	protected BtwEntity(EntityType<? extends TameableEntity> entityType, World world) {
		super(entityType, world);
		this.setTamed(false);
	}

	@Nullable
	@Override
	public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
		return entity;
	}

	public static void registerBtwEntityAttributes() {
		FabricDefaultAttributeRegistry.register(BTW_ENTITY_TYPE, createMobAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5f).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 5.0f).add(EntityAttributes.GENERIC_ATTACK_SPEED, 4.0f).add(EntityAttributes.GENERIC_ATTACK_DAMAGE,  2.0f).add(EntityAttributes.GENERIC_MAX_HEALTH, 12f));
	}

	@Override
	protected void initGoals() {
		this.goalSelector.add(1, new WanderAroundFarGoal(this, 0.8f));
		this.goalSelector.add(1, new FollowOwnerGoal(this, 0.5f, 1.0f, 20.f, false));
		this.goalSelector.add(0, new TemptGoal(this, 0.5f, Ingredient.ofItems(TbhRegistry.COLA_BOTTLE_ITEM), false));
		this.goalSelector.add(0, new AttackWithOwnerGoal(this));
		this.goalSelector.add(0, new AttackGoal(this));
		super.initGoals();
	}

	@Override
	public ActionResult interactMob(PlayerEntity player, Hand hand) {
		ItemStack itemStack = player.getStackInHand(hand);
		if (this.isTamed()) {
			if (this.isBreedingItem(itemStack) && this.getHealth() < this.getMaxHealth()) {
				if (!player.getAbilities().creativeMode) {
					itemStack.decrement(1);
				}

				this.heal(0.5f);
				return ActionResult.SUCCESS;
			}
		}
		else if (itemStack.isOf(TbhRegistry.COLA_BOTTLE_ITEM)) {
			if (!player.isCreative())
				itemStack.decrement(1);
			if (this.random.nextInt(3) == 0) {
				this.setOwner(player);
				this.navigation.stop();
				this.setTarget(null);
				this.setTamed(true);
				this.world.sendEntityStatus(this, EntityStatuses.ADD_POSITIVE_PLAYER_REACTION_PARTICLES);
			} else {
				this.world.sendEntityStatus(this, EntityStatuses.ADD_NEGATIVE_PLAYER_REACTION_PARTICLES);
			}
		}
		return super.interactMob(player, hand);
	}


}
