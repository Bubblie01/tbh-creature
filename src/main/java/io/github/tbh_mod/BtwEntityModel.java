package io.github.tbh_mod;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.render.entity.model.QuadrupedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import javax.swing.text.html.parser.Entity;

public class BtwEntityModel<T extends BtwEntity> extends QuadrupedEntityModel<T> {

	public static final EntityModelLayer BTW_MODEL_LAYER = new EntityModelLayer(new Identifier(Main.MOD_ID, "btw_entity_model"), "btw_entity_model");
	protected final ModelPart head;

	protected final ModelPart body;

	protected final ModelPart rightHindLeg;

	protected final ModelPart leftHindLeg;

	protected final ModelPart rightFrontLeg;

	protected final ModelPart leftFrontLeg;

	//private final ModelPart rightEar;

	//private ModelPart rightEar_rotate;

	//private final ModelPart leftEar;

	//private ModelPart leftEar_rotate;

	protected BtwEntityModel(ModelPart root) {
		super(root, true, 0.0f, 0.0f, 0.0f, 0.0f, 0);
		this.head = root.getChild(EntityModelPartNames.HEAD);
		this.body = root.getChild(EntityModelPartNames.BODY);
		this.rightFrontLeg = root.getChild(EntityModelPartNames.RIGHT_FRONT_LEG);
		this.rightHindLeg = root.getChild(EntityModelPartNames.RIGHT_HIND_LEG);
		this.leftFrontLeg = root.getChild(EntityModelPartNames.LEFT_FRONT_LEG);
		this.leftHindLeg = root.getChild(EntityModelPartNames.LEFT_HIND_LEG);
		//this.rightEar = root.getChild(EntityModelPartNames.RIGHT_EAR);
		//this.leftEar = root.getChild(EntityModelPartNames.LEFT_EAR);
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();

		ModelPartData head = modelPartData.addChild(EntityModelPartNames.HEAD, ModelPartBuilder.create().uv(0, 10).cuboid(-3.0F, -5.0F, -4.0F, 6.0F, 5.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 18.0F, -2.0F));
		head.addChild("ears", ModelPartBuilder.create().uv(17, 10).cuboid(-0.5F, -1.0F, -1.5F, 1.0F, 2.0F, 3.0F, new Dilation(0.0F))
				.uv(19, 0).cuboid(2.5F, -1.0F, -1.5F, 1.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-1.5F, -5.0F, -1.5F, 0.6109F, 0.0F, 0.0F));

		modelPartData.addChild(EntityModelPartNames.RIGHT_FRONT_LEG, ModelPartBuilder.create().uv(0, 20).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, 21.0F, -2.0F));

		modelPartData.addChild(EntityModelPartNames.RIGHT_HIND_LEG, ModelPartBuilder.create().uv(22, 15).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, 21.0F, 3.0F));

		modelPartData.addChild(EntityModelPartNames.LEFT_FRONT_LEG, ModelPartBuilder.create().uv(8, 20).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, 21.0F, -2.0F));

		modelPartData.addChild(EntityModelPartNames.LEFT_HIND_LEG, ModelPartBuilder.create().uv(16, 20).cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, 21.0F, 3.0F));

		modelPartData.addChild(EntityModelPartNames.BODY, ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -2.0F, -3.0F, 6.0F, 3.0F, 7.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 20.0F, 0.0F));

		//ModelPartData rightEar = modelPartData.addChild(EntityModelPartNames.RIGHT_EAR, ModelPartBuilder.create(), ModelTransform.pivot(2.0F, 13.0F, -3.0F));

		//ModelPartData rightEar_rotate = rightEar.addChild("rightEar_rotate", ModelPartBuilder.create().uv(19, 0).cuboid(-0.5F, -1.0F, -1.5F, 1.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-0.5F, 0.0F, -0.5F, 0.6109F, 0.0F, 0.0F));

		//ModelPartData leftEar = modelPartData.addChild(EntityModelPartNames.LEFT_EAR, ModelPartBuilder.create(), ModelTransform.pivot(-1.5F, 13.0F, -3.5F));

		//ModelPartData leftEar_rotate = leftEar.addChild("leftEar_rotate", ModelPartBuilder.create().uv(17, 10).cuboid(-0.5F, -1.0F, -1.5F, 1.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.6109F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 32, 32);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
		super.render(matrices, vertices, light, overlay, red, green, blue, alpha);

	}

	@Override
	protected Iterable<ModelPart> getHeadParts() {
		return ImmutableList.<ModelPart>of(this.head);
	}
	@Override
	protected Iterable<ModelPart> getBodyParts() {
		return ImmutableList.<ModelPart>of(this.body, this.rightHindLeg, this.leftHindLeg, this.rightFrontLeg, this.leftFrontLeg);
	}

}
