package io.github.tbh_mod;

import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.quiltmc.qsl.entity.api.QuiltEntityTypeBuilder;

public class BtwEntity extends TbhEntity {

	public static final EntityType<BtwEntity> BTW_ENTITY_TYPE = Registry.register(Registry.ENTITY_TYPE, new Identifier(Main.MOD_ID, "btw_entity"), QuiltEntityTypeBuilder.create(SpawnGroup.CREATURE, BtwEntity::new).setDimensions(EntityDimensions.changing(0.5F, 1.0F)).build());

	protected BtwEntity(EntityType<? extends TameableEntity> entityType, World world) {
		super(entityType, world);
	}
}
