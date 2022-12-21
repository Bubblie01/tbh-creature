package io.github.tbh_mod;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class BtwEntityRenderer extends MobEntityRenderer<BtwEntity, BtwEntityModel<BtwEntity>> {

	private final Identifier BTW_TEXTURE = new Identifier(Main.MOD_ID, "textures/entities/btw_texture.png");

	public BtwEntityRenderer(EntityRendererFactory.Context context) {
		super(context, new BtwEntityModel(context.getPart(BtwEntityModel.BTW_MODEL_LAYER)), 0.5f);
	}

	@Override
	public Identifier getTexture(BtwEntity entity) {
		return BTW_TEXTURE;
	}
}
