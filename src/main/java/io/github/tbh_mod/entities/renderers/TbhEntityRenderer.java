package io.github.tbh_mod.entities.renderers;

import io.github.tbh_mod.Main;
import io.github.tbh_mod.entities.TbhEntity;
import io.github.tbh_mod.entities.models.TbhEntityModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class TbhEntityRenderer extends MobEntityRenderer<TbhEntity, TbhEntityModel<TbhEntity>> {

	private final Identifier TBH_TEXTURE = new Identifier(Main.MOD_ID, "textures/entities/tbh_texture.png");

	public TbhEntityRenderer(EntityRendererFactory.Context context) {
		super(context, new TbhEntityModel(context.getPart(TbhEntityModel.TBH_MODEL_LAYER)), 0.5f);
	}

	@Override
	public Identifier getTexture(TbhEntity entity) {
		return TBH_TEXTURE;
	}
}
