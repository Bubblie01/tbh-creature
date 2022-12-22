package io.github.tbh_mod;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.data.client.model.TexturedModel;
import net.minecraft.util.Identifier;

public class ColaEntityModel <T extends ColaEntity> extends EntityModel<T> {

	public static final EntityModelLayer COLA_ENTITY_MODEL_LAYER = new EntityModelLayer(new Identifier(Main.MOD_ID, "cola_entity_model"), "cola_entity_model");
	private final ModelPart eyes;

	private final ModelPart body;

	private final ModelPart cap;

	public ColaEntityModel(ModelPart root) {
		this.eyes = root.getChild("eyes");
		this.body = root.getChild(EntityModelPartNames.BODY);
		this.cap = root.getChild("cap");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();

		modelPartData.addChild("eyes", ModelPartBuilder.create().uv(16, 0).cuboid(-2.0F, -2.0F, 0.0F, 4.0F, 8.0F, 4.0F, new Dilation(0.2F)), ModelTransform.pivot(0.0F, 18.0F, -2.0F));

		modelPartData.addChild(EntityModelPartNames.BODY, ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, -2.0F, 4.0F, 8.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 20.0F, 0.0F));

		modelPartData.addChild("cap", ModelPartBuilder.create().uv(12, 0).cuboid(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F)).uv(0, 12).cuboid(-1.5F, -1.5F, -1.5F, 3.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 16.0F, 0.0F));

		return TexturedModelData.of(modelData, 32, 32);
	}


	@Override
	public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
		eyes.render(matrices, vertices, light, overlay, red, green, blue, alpha);
		body.render(matrices, vertices, light, overlay, red, green, blue, alpha);
		cap.render(matrices, vertices, light, overlay, red, green, blue, alpha);
	}
}
