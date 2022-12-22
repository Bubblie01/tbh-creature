package io.github.tbh_mod.entities.renderers;

import io.github.tbh_mod.Main;
import io.github.tbh_mod.entities.ColaEntity;
import io.github.tbh_mod.entities.models.ColaEntityModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class ColaEntityRenderer extends MobEntityRenderer<ColaEntity, ColaEntityModel<ColaEntity>> {
	private final Identifier COLA_TEXTURE = new Identifier(Main.MOD_ID, "textures/entities/cola_entity_texture.png");

	public ColaEntityRenderer(EntityRendererFactory.Context context) {
		super(context, new ColaEntityModel(context.getPart(ColaEntityModel.COLA_ENTITY_MODEL_LAYER)), 0.5f);
	}

	@Override
	protected void scale(ColaEntity entity, MatrixStack matrices, float amount) {
		float g = 0.999F;
		matrices.scale(g, g, g);
		matrices.translate(0.0, 0.001F, 0.0);
		float i = MathHelper.lerp(amount, entity.lastStretch, entity.stretch) / (1.5f);
		float j = 1.0F / (i + 1.0F);
		matrices.scale(j, 1.0F / j, j);
	}

	@Override
	public Identifier getTexture(ColaEntity entity) {
		return COLA_TEXTURE;
	}
}
